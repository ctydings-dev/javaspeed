/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaspeed.api.CustomerCaller;
import javaspeed.api.ProductCaller;
import javaspeed.lightspeed.data.Brand;
import javaspeed.lightspeed.data.Category;
import javaspeed.lightspeed.data.Customer;
import javaspeed.lightspeed.data.Inventory;
import javaspeed.lightspeed.data.Outlet;
import javaspeed.lightspeed.data.Product;
import javaspeed.lightspeed.data.Sale;
import javaspeed.lightspeed.data.SaleItem;
import javaspeed.lightspeed.data.SerialNumber;
import javaspeed.lightspeed.data.Supplier;
import javaspeed.lightspeed.data.Tag;

/**
 *
 * @author ctydi
 */
public class SqlWriter {

    private Connection conn;

    public Map<Integer, Category> categories;

    public Map<Integer, Tag> tags;

    public Map<Integer, Supplier> suppliers;

    public Map<Integer, Brand> brands;

    public ProductCaller productCaller;

    public CustomerCaller customerCaller;

    public SqlWriter(Connection con, ProductCaller product, CustomerCaller customer) throws SQLException {
        this.conn = con;
        categories = new HashMap<>();
        this.productCaller = product;
        this.customerCaller = customer;
        this.loadCategories();
        this.loadTags();
        this.loadBrands();
        this.loadSuppliers();

    }

    public ProductCaller getProductCaller() {
        return this.productCaller;
    }

    public Connection getConnection() {
        return this.conn;
    }

    public Statement getStatement() throws SQLException {
        return this.getConnection().createStatement();
    }

    public void loadProductsToDB() throws IOException, SQLException {
        List<Product> products = this.productCaller.getProducts();
        String stmt = "DELETE FROM products;";
        this.getStatement().execute(stmt);

        for (Product toAdd : products) {
            this.writeProductToDB(toAdd);
        }

    }

    public void writeProductToDB(Product toWrite) throws SQLException, IOException {

        Integer cat = this.getCategoryByKey(toWrite.getCategory().getId());
        Integer sup = this.getSupplierByName(toWrite.getSupplier().getName());
        Integer brand = this.getBrandByName(toWrite.getBrand().getName());

        String barcode = "NA";

        for (int index = 0; index < toWrite.getCodes().length; index++) {
            if (toWrite.getCodes()[index].getType().equalsIgnoreCase("UPC")) {
                barcode = toWrite.getCodes()[index].getCode();
            }
        }

        String stmt = "INSERT INTO products(is_equipment, product_key, product_name, sku, category_id, cost, price, brand_id, supplier_id, barcode, is_active,eve_id) VALUES(FALSE,'";
        stmt = stmt + toWrite.getId() + "','" + toWrite.getName() + "','" + toWrite.getSku() + "'," + cat + "," + toWrite.getSupplyPrice() + ",";
        stmt = stmt + toWrite.getPriceExcludingTax() + "," + brand + "," + sup + ",'" + barcode + "',";
        if (toWrite.isActive()) {
            stmt = stmt + "TRUE";
        } else {
            stmt = stmt + "FALSE";
        }

        stmt = stmt + ",'" + toWrite.getExternalKey() + "');";

        try {
            this.getStatement().execute(stmt);
            SerialNumber[] SNs = toWrite.getSerialNumbers();
            for (int index = 0; index < SNs.length; index++) {
                if (index == 0) {
                    stmt = "UPDATE products SET is_equipment = TRUE WHERE sku = '" + toWrite.getSku() + "';";
                    this.getStatement().execute(stmt);
                }

                addEquipment(toWrite.getSku(), SNs[index], toWrite.getExternalKey());
            }

        } catch (Throwable e) {
            System.out.println(stmt);
            throw e;
        }

    }

    private void addEquipment(String sku, SerialNumber serial, String eve) throws SQLException {
        String stmt = "SELECT customer_id FROM customers WHERE eve_id = '" + serial.getOwner() + "';";
        ResultSet rs = this.getConnection().createStatement().executeQuery(stmt);
        String customer = "0";
        if (rs.next()) {
            customer = rs.getString("customer_id");
        }
        stmt = "INSERT INTO equipment(product_id, customer_id, serial_number, eve_id) SELECT product_id, '";
        stmt = stmt + customer + "','" + serial + "','" + eve + "' FROM products WHERE sku ='" + sku + "';";
        this.getConnection().createStatement().execute(stmt);
    }

    public void writeCustomerToDB(Customer toWrite) throws SQLException {
        String stmt = "INSERT INTO customers(customer_key, customer_code, first_name, last_name, street, city, state, country, post_code, email, phone,eve_id,date_of_birth) VALUES(";

        String fName = "";
        String lName = "";
        String street = "";
        String email = "";
        String phone = "";
        String bday = toWrite.getBirthday();

        if (bday == null || bday.trim().equalsIgnoreCase("NULL")) {
            bday = null;
        }

        if (toWrite.getFirstName() != null) {
            fName = toWrite.getFirstName().replace("'", "\\'");
        }
        if (toWrite.getLastName() != null) {
            lName = toWrite.getLastName().replace("'", "\\'");
        }
        if (toWrite.getPhysicalAddress1() != null) {
            street = toWrite.getPhysicalAddress1().replace("'", "\\'");
        }
        if (toWrite.getEmail() != null) {
            email = toWrite.getEmail().replace("'", "\\'");
        }
        if (toWrite.getPhone() != null) {
            phone = toWrite.getPhone().replace("'", "\\'");
        }

        stmt = stmt + "'" + toWrite.getId() + "','" + toWrite.getCode() + "','" + fName + "','" + lName + "','" + street;
        stmt = stmt + "','" + toWrite.getPostalCity() + "','" + toWrite.getPostalState() + "','" + toWrite.getPostalCountry() + "','" + toWrite.getPostalPostCode() + "','" + email;
        stmt = stmt + "','" + phone + "','" + toWrite.getCustom1() + "',";

        if (bday == null) {
            stmt = stmt + "NULL);";

        } else {
            stmt = stmt + "" + bday + ");";

        }

        try {
            this.getStatement().execute(stmt);
        } catch (Throwable e) {
            System.out.println(stmt);
            e.printStackTrace();
        }
    }

    public void loadCategories() throws SQLException {
        this.categories = new HashMap();
        String stmt = "SELECT * FROM categories;";
        ResultSet rs = this.getStatement().executeQuery(stmt);
        while (rs.next()) {
            int id = rs.getInt("category_id");
            String key = rs.getString("category_key");
            String name = rs.getString("category_name");
            Category toAdd = new Category(key, name);
            this.categories.put(id, toAdd);
        }

    }

    public void loadCategoriesToDB() throws IOException, SQLException {
        String stmt = "DELETE FROM categories;";
        this.getStatement().execute(stmt);
        List<Category> categories = this.getProductCaller().getProductCategories();
        for (Category entry : categories) {
            String toAdd = "INSERT INTO categories(category_name, category_key) VALUES ('";
            toAdd = toAdd + entry.getName().replace("'", "\\'") + "','" + entry.getId() + "');";

            this.getStatement().execute(toAdd);
        }

    }

    public void loadTags() throws SQLException {
        String stmt = "SELECT * FROM tags;";
        this.tags = new HashMap();
        ResultSet rs = this.getStatement().executeQuery(stmt);
        while (rs.next()) {
            int id = rs.getInt("tag_id");
            String key = rs.getString("tag_key");
            String name = rs.getString("tag_name");
            Tag toAdd = new Tag(key, name);
            this.tags.put(id, toAdd);
        }
    }

    public void loadSuppliers() throws SQLException {
        String stmt = "SELECT * FROM suppliers;";
        this.suppliers = new HashMap();
        ResultSet rs = this.getStatement().executeQuery(stmt);
        while (rs.next()) {
            int id = rs.getInt("supplier_id");
            String key = rs.getString("supplier_key");
            String name = rs.getString("supplier_name");
            Supplier toAdd = new Supplier(key, name);

            this.suppliers.put(id, toAdd);
        }
    }

    public void loadBrands() throws SQLException {
        String stmt = "SELECT * FROM brands;";
        this.brands = new HashMap();
        ResultSet rs = this.getStatement().executeQuery(stmt);
        while (rs.next()) {
            int id = rs.getInt("brand_id");
            String key = rs.getString("brand_key");
            String name = rs.getString("brand_name");
            Brand toAdd = new Brand(key, name);
            this.brands.put(id, toAdd);
        }
    }

    public void loadTagsToDB() throws IOException, SQLException {
        String stmt = "DELETE FROM tags;";
        this.getStatement().execute(stmt);
        List<Tag> categories = this.getProductCaller().getProductTags();
        categories.forEach(entry -> {
            String toAdd = "INSERT INTO tags(tag_name, tag_key) VALUES ('";
            toAdd = toAdd + entry.getName().replace("'", "\\'") + "','" + entry.getId() + "');";
            try {
                this.getStatement().execute(toAdd);
            } catch (SQLException ex) {
                Logger.getLogger(SqlWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loadTags();
    }

    public void loadSuppliersToDB() throws IOException, SQLException {
        String stmt = "DELETE FROM suppliers;";
        this.getStatement().execute(stmt);
        List<Supplier> sups = this.getProductCaller().getSuppliers();
        sups.forEach(entry -> {
            String toAdd = "INSERT INTO suppliers(supplier_name, supplier_key) VALUES ('";
            toAdd = toAdd + entry.getName().replace("'", "\\'") + "','" + entry.getId() + "');";
            try {
                this.getStatement().execute(toAdd);
            } catch (SQLException ex) {
                Logger.getLogger(SqlWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loadTags();
    }

    public void loadBrandsToDB() throws IOException, SQLException {
        List<Brand> sups = this.getProductCaller().getBrands();
        sups.forEach(entry -> {
            String toAdd = "INSERT INTO brands(brand_name, brand_key) VALUES ('";
            toAdd = toAdd + entry.getName().replace("'", "\\'") + "','" + entry.getId() + "');";
            try {
                this.getStatement().execute(toAdd);
            } catch (SQLException ex) {
                Logger.getLogger(SqlWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loadTags();
    }

    public void addTag(String category) throws IOException, SQLException {
        Tag toAdd = new Tag();
        toAdd.setName(category);
        String key = this.getProductCaller().addProductTag(toAdd);

        String stmt = "INSERT INTO tags(tag_name, tag_key) VALUES ('";
        stmt = stmt + category + "','" + key + "');";
        this.getStatement().execute(stmt);
        this.loadTags();
    }

    public void addSupplier(String name) throws IOException, SQLException {
        Supplier toAdd = new Supplier();
        toAdd.setName(name);
        String key = "" + Math.random();
        if (this.getProductCaller() != null) {
            key = this.getProductCaller().addSupplier(toAdd);
        }
        String stmt = "INSERT INTO suppliers(supplier_name, supplier_key, is_supplier) VALUES ('";
        stmt = stmt + name + "','" + key + "', TRUE);";
        this.executeStatement(stmt);
        this.loadSuppliers();
    }

    public void addBrand(String name) throws IOException, SQLException {
        Brand toAdd = new Brand();
        toAdd.setName(name);
        String key = "" + Math.random();
        if (this.getProductCaller() != null) {
            key = this.getProductCaller().addBrand(toAdd);
        }
        String stmt = "INSERT INTO companies(company_name, company_key, is_supplier) VALUES ('";
        stmt = stmt + name + "','" + key + "', FALSE);";
        this.executeStatement(stmt);
        this.loadBrands();
    }

    private ResultSet executeQuery(String stmt) throws SQLException {
        return this.getConnection().createStatement().executeQuery(stmt);
    }

    private void executeStatement(String stmt) throws SQLException {
        this.getConnection().createStatement().execute(stmt);
    }

    public void addSale(Sale toAdd, String notes) throws SQLException {
        String stmt = "SELECT customer_id FROM customers WHERE eve_id = '" + toAdd.getUserId() + "';";

        ResultSet rs = this.executeQuery(stmt);
        if (!rs.next()) {
            System.out.println("COULD NOT FIND EMPLOYEE : " + toAdd.getUserId());
            System.exit(0);
        }

        String employeeId = rs.getString("customer_id");

        stmt = "INSERT INTO sales(customer_id, register_id, employee_id, sale_date,";
        stmt = stmt + " invoice, is_service,notes) VALUES (";

        stmt = stmt + toAdd.getCustomer().getId() + ", " + toAdd.getRegisterId();
        stmt = stmt + ", " + employeeId + ", '" + toAdd.getSaleDate();
        stmt = stmt + "', " + toAdd.getInvoiceNumber() + ", FALSE, '" + notes + "');";
        this.executeStatement(stmt);

        SaleItem[] items = toAdd.getRegisterSaleProducts();

        for (int index = 0; index < items.length; index++) {
            this.addSaleItem(toAdd, items[index]);
        }
    }

    private void addSaleItem(Sale sale, SaleItem toAdd) throws SQLException {
        String stmt = "SELECT sale_id FROM sales WHERE invoice = '" + sale.getInvoiceNumber();
        stmt = stmt + "';";
        ResultSet rs = this.getConnection().createStatement().executeQuery(stmt);
        if (!rs.next()) {
            System.out.println("CANNOT FIND " + sale.getInvoiceNumber());
            return;
        }

        String saleId = rs.getString("sale_id");
        stmt = "SELECT product_id FROM products WHERE eve_id = " + toAdd.getProduct().getId() + " LIMIT 1;";

        rs = this.getConnection().createStatement().executeQuery(stmt);
        String product = "0";
        if (rs.next()) {
            product = rs.getString("product_id");
        }

        stmt = "INSERT INTO sale_items(sale_id, product_id, quantity, price)  VALUES(";
        stmt = stmt + saleId + ", " + product + ", " + toAdd.getQuantity() + ", " + toAdd.getPrice() + ");";
        this.getConnection().createStatement().execute(stmt);
    }

    public Integer getCategoryByKey(String key) {
        for (Integer id : this.categories.keySet()) {
            if (this.categories.get(id).getId().equals(key)) {
                return id;
            }
        }
        return null;
    }

    public Integer getCategoryByName(String key) {
        for (Integer id : this.categories.keySet()) {
            if (this.categories.get(id).getName().equals(key)) {
                return id;
            }

        }

        return null;
    }

    public Integer getSupplierByKey(String key) {
        for (Integer id : this.suppliers.keySet()) {
            if (this.suppliers.get(id).getId().equals(key)) {
                return id;
            }
        }
        return null;
    }

    public Integer getSupplierByName(String name) throws IOException, SQLException {
        if (name == null) {
            name = "NA";
        }

        name = name.trim().toUpperCase();
        for (Integer id : this.suppliers.keySet()) {
            if (this.suppliers.get(id).getName().equalsIgnoreCase(name)) {
                return id;
            }
        }
        this.addSupplier(name.replace("'", "\\'"));

        return this.getSupplierByName(name);
    }

    public Supplier getSupplier(Integer key) {
        return this.suppliers.get(key);
    }

    public Integer getBrandByKey(String key) {
        for (Integer id : this.brands.keySet()) {
            if (this.brands.get(id).getId().equals(key)) {
                return id;
            }
        }
        return null;
    }

    public Integer getBrandByName(String name) throws IOException, SQLException {
        if (name == null) {
            name = "NA";
        }

        name = name.trim().toUpperCase();
        for (Integer id : this.brands.keySet()) {
            if (this.brands.get(id).getName().equalsIgnoreCase(name)) {
                return id;
            }
        }
        this.addBrand(name);

        return this.getBrandByName(name.replace("'", "\\'"));
    }

    public Brand getBrand(Integer key) {
        return this.brands.get(key);
    }

    public Integer getTagByKey(String key) {
        for (Integer id : this.tags.keySet()) {
            if (this.tags.get(id).getId().equals(key)) {
                return id;
            }
        }
        return null;
    }

    public Integer getTagByName(String name) throws IOException, SQLException {
        name = name.trim().toUpperCase();
        for (Integer id : this.tags.keySet()) {
            if (this.tags.get(id).getName().equalsIgnoreCase(name)) {
                return id;
            }
        }
        this.addTag(name.replace("'", "\\'"));

        return this.getTagByName(name);
    }

    public Tag getTag(Integer key) {
        return this.tags.get(key);
    }

    public void addServiceItem(String eveId, String invoice, String customer, String employee, String date, List<String> equipIds) throws SQLException {

        String stmt = "SELECT sale_id FROM sales WHERE invoice = " + invoice + ";";

        invoice = "-1";
        ResultSet rs = this.executeQuery(stmt);
        if (rs.next()) {
            invoice = rs.getString("sale_id");
        }

        stmt = "SELECT customer_id FROM customers WHERE eve_id = " + customer + ";";
        rs = this.executeQuery(stmt);
        rs.next();
        customer = rs.getString("customer_id");
        stmt = "SELECT customer_id FROM customers WHERE eve_id = " + employee + ";";
        rs = this.executeQuery(stmt);

        employee = rs.getString("customer_id");

        stmt = "INSERT INTO services(invoice_id , customer_id, employee_id, service_date, eve_id) VALUES(";
        stmt = stmt + invoice + "," + customer + "," + employee + ",'" + date + "'," + eveId + ");";
        this.executeStatement(stmt);

        for (String id : equipIds) {

            stmt = "SELECT equipment_id FROM equipment WHERE eve_id = '" + id + "' LIMIT 1;";
            rs = this.executeQuery(stmt);
            rs.next();
            String equip = rs.getString("equipment_id");

            stmt = "INSERT INTO service_items(service_id, equipment_id) SELECT service_id, ";
            stmt = stmt + equip + " FROM services WHERE eve_id = '" + eveId + "';";
            this.executeStatement(stmt);

        }

    }

    public void uploadCustomersToLightspeed() throws SQLException, IOException {
        String stmt = "SELECT * FROM customers;";

        ResultSet rs = this.executeQuery(stmt);

        while (rs.next()) {
            Customer toAdd = new Customer();
            toAdd.setFirstName(rs.getString("first_name").trim());
            toAdd.setLastName(rs.getString("last_name").trim());
            toAdd.setCustom1(rs.getString("eve_id"));
            toAdd.setCode(rs.getString("customer_code"));
            toAdd.setPostalAddress1(rs.getString("street"));
            toAdd.setPostalCity(rs.getString("city"));
            toAdd.setPostalState(rs.getString("state"));
            toAdd.setPostalCountry(rs.getString("country"));
            toAdd.setPhone(rs.getString("phone"));
            toAdd.setPostalPostCode(rs.getString("post_code"));
            toAdd.setEmail(rs.getString("email"));
            toAdd.setBirthday(rs.getString("date_of_birth"));
            this.customerCaller.addCustomer(toAdd);
        }
    }

    public void uploadProductsToLightspeed(String outletId, String userId) throws SQLException, IOException {
        String stmt = "SELECT * FROM products AS pro INNER JOIN categories AS cat ON pro.category_id ="
                + " cat.category_id INNER JOIN suppliers AS sup on sup.supplier_id = pro.supplier_id INNER JOIN"
                + " brands AS brand ON pro.brand_id = brand.brand_id WHERE is_active = TRUE;";
        ResultSet rs = this.executeQuery(stmt);

        while (rs.next()) {

            String name = rs.getString("product_name");
            String sku = rs.getString("sku");
            String barcode = rs.getString("barcode");
            String supplier = rs.getString("supplier_key");

            String brand = rs.getString("brand_key");
            int stock = rs.getInt("stock");
            double price = rs.getDouble("price");
            double cost = rs.getDouble("cost");
            String cat = rs.getString("category_key");

            Inventory inven = new Inventory();
            inven.setOutletId(outletId);
            inven.setInventory(stock);

            Product toAdd = new Product();

            toAdd.addInventory(inven);
            toAdd.setName(name);
            toAdd.setBrand(brand);
            toAdd.setSupplier(supplier);
            toAdd.setCategory(cat);
            toAdd.setUpc(barcode);
            toAdd.setSku(sku);
            toAdd.setPriceExcludingTax(price);
            toAdd.setSupplyPrice(cost);
            this.productCaller.addProduct(toAdd);
        }

    }

    public void uploadEquipmentToLightspeed(String outletId, String userId) throws SQLException, IOException {
        String stmt = "SELECT * FROM products AS pro INNER JOIN categories AS cat ON pro.category_id = cat.category_id INNER JOIN suppliers AS sup on sup.supplier_id = pro.supplier_id INNER JOIN brands AS brand ON pro.brand_id = brand.brand_id INNER JOIN equipment AS equip ON pro.product_id = equip.product_id;";
        ResultSet rs = this.executeQuery(stmt);
        Outlet outletToUse = new Outlet();
        outletToUse.setId(outletId);
        while (rs.next()) {

            String name = rs.getString("product_name");
            String sku = rs.getString("sku");
            String barcode = rs.getString("barcode");
            String supplier = rs.getString("supplier_key");

            String brand = rs.getString("brand_key");
            int stock = rs.getInt("stock");
            double price = rs.getDouble("price");
            double cost = rs.getDouble("cost");
            String cat = rs.getString("category_key");

            String sn = rs.getString("serial_number");

            Inventory inven = new Inventory();
            inven.setOutletId(outletId);
            inven.setInventory(stock);

            Product toAdd = new Product();

            toAdd.addInventory(inven);
            toAdd.setName(name);
            toAdd.setBrand(brand);
            toAdd.setSupplier(supplier);
            toAdd.setCategory(cat);
            toAdd.setUpc(barcode);
            toAdd.setSku(sku);
            toAdd.setPriceExcludingTax(price);
            toAdd.setSupplyPrice(cost);
            toAdd.setActive(false);

            Tag rental = this.productCaller.getTagByName("RENTAL");
            if (rs.getBoolean("is_rental")) {
                toAdd.addTag(cat);
            }

            String productId = this.productCaller.addProduct(toAdd);

            Product productToSet = new Product();
            productToSet.setId(productId);
            if (sn != null) {
                SerialNumber snToAdd = new SerialNumber(sn);
                snToAdd.setProduct(productToSet);
                snToAdd.setOutlet(outletToUse);
                this.productCaller.addSerialNumber(snToAdd);
            }
        }

    }

}
