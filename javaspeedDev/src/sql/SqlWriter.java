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
import javaspeed.api.ProductCaller;
import javaspeed.lightspeed.data.Brand;
import javaspeed.lightspeed.data.Category;
import javaspeed.lightspeed.data.Customer;
import javaspeed.lightspeed.data.Product;
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

    public SqlWriter(Connection con, ProductCaller product) throws SQLException {
        this.conn = con;
        categories = new HashMap<>();
        this.productCaller = product;
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

    public void writeProductToDB(Product toWrite) throws SQLException {

        Integer cat = this.getCategoryByKey(toWrite.getCategory().getId());
        Integer sup = this.getSupplierByKey(toWrite.getSupplier().getId());
        Integer brand = this.getBrandByKey(toWrite.getBrand().getId());

        String barcode = "NA";

        for (int index = 0; index < toWrite.getCodes().length; index++) {
            if (toWrite.getCodes()[index].getType().equalsIgnoreCase("UPC")) {
                barcode = toWrite.getCodes()[index].getCode();
            }
        }

        String stmt = "INSERT INTO products(product_key, product_name, sku, category_id, cost, price, brand_id, supplier_id, barcode, is_active) VALUES('";
        stmt = stmt + toWrite.getId() + "','" + toWrite.getName() + "','" + toWrite.getSku() + "'," + cat + "," + toWrite.getSupplyPrice() + ",";
        stmt = stmt + toWrite.getPriceExcludingTax() + "," + brand + "," + sup + ",'" + barcode + "',";
        if (toWrite.isActive()) {
            stmt = stmt + "TRUE";
        } else {
            stmt = stmt + "FALSE";
        }

        stmt = stmt + ");";
        this.getStatement().execute(stmt);

    }

    public void writeCustomerToDB(Customer toWrite) throws SQLException {
        String stmt = "INSERT INTO persons(customer_id, customer_code, first_name, last_name, street, city, state, country, post_code, email, phone) VALUES(";

        String fName = toWrite.getFirstName().replace("'", "\\'");
        String lName = toWrite.getLastName().replace("'", "\\'");
        String street = toWrite.getPhysicalAddress1().replace("'", "\\'");
        String email = toWrite.getEmail().replace("'", "\\'");
        String phone = toWrite.getPhone().replace("'", "\\'");
        stmt = stmt + "'" + toWrite.getId() + "','" + toWrite.getCode() + "','" + fName + "','" + lName + "','" + street;
        stmt = stmt + "','" + toWrite.getPhysicalCity() + "','" + toWrite.getPhysicalState() + "','" + toWrite.getPhysicalCountry() + "','" + toWrite.getPhysicalPostCode() + "','" + email;
        stmt = stmt + "','" + phone + "');";
        try {
            this.getStatement().execute(stmt);
        } catch (Throwable e) {
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
        String stmt = "SELECT * FROM companies WHERE is_supplier = TRUE;";
        this.suppliers = new HashMap();
        ResultSet rs = this.getStatement().executeQuery(stmt);
        while (rs.next()) {
            int id = rs.getInt("company_id");
            String key = rs.getString("company_key");
            String name = rs.getString("company_name");
            Supplier toAdd = new Supplier(key, name);

            this.suppliers.put(id, toAdd);
        }
    }

    public void loadBrands() throws SQLException {
        String stmt = "SELECT * FROM companies WHERE is_supplier = FALSE;";
        this.brands = new HashMap();
        ResultSet rs = this.getStatement().executeQuery(stmt);
        while (rs.next()) {
            int id = rs.getInt("company_id");
            String key = rs.getString("company_key");
            String name = rs.getString("company_name");
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
        String stmt = "DELETE FROM companies;";
        this.getStatement().execute(stmt);
        List<Supplier> sups = this.getProductCaller().getSuppliers();
        sups.forEach(entry -> {
            String toAdd = "INSERT INTO companies(company_name, company_key, is_supplier) VALUES ('";
            toAdd = toAdd + entry.getName().replace("'", "\\'") + "','" + entry.getId() + "', true);";
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
            String toAdd = "INSERT INTO companies(company_name, company_key, is_supplier) VALUES ('";
            toAdd = toAdd + entry.getName().replace("'", "\\'") + "','" + entry.getId() + "',FALSE);";
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
        String key = this.getProductCaller().addSupplier(toAdd);
        String stmt = "INSERT INTO companies(company_name, company_key, is_supplier) VALUES ('";
        stmt = stmt + name + "','" + key + "', TRUE);";
        this.loadSuppliers();
    }

    public void addBrand(String name) throws IOException, SQLException {
        Brand toAdd = new Brand();
        toAdd.setName(name);
        String key = this.getProductCaller().addBrand(toAdd);
        String stmt = "INSERT INTO companies(company_name, company_key, is_supplier) VALUES ('";
        stmt = stmt + name + "','" + key + "', FALSE);";
        this.loadBrands();
    }

    public Integer getCategoryByKey(String key) {
        for (Integer id : this.categories.keySet()) {
            if (this.categories.get(id).getId().equals(key)) {
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
        name = name.trim().toUpperCase();
        for (Integer id : this.suppliers.keySet()) {
            if (this.suppliers.get(id).getName().equalsIgnoreCase(name)) {
                return id;
            }
        }
        this.addSupplier(name);

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
        name = name.trim().toUpperCase();
        for (Integer id : this.brands.keySet()) {
            if (this.brands.get(id).getName().equalsIgnoreCase(name)) {
                return id;
            }
        }
        this.addBrand(name);

        return this.getBrandByName(name);
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
        this.addTag(name);

        return this.getTagByName(name);
    }

    public Tag getTag(Integer key) {
        return this.tags.get(key);
    }

}
