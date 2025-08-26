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
import javaspeed.lightspeed.data.Category;
import javaspeed.lightspeed.data.Customer;
import javaspeed.lightspeed.data.Tag;

/**
 *
 * @author ctydi
 */
public class SqlWriter {

    private Connection conn;

    public Map<Integer, Category> categories;

    public Map<Integer, Tag> tags;

    public ProductCaller productCaller;

    public SqlWriter(Connection con, ProductCaller product) throws SQLException {
        this.conn = con;
        categories = new HashMap<>();
        this.productCaller = product;
        this.loadCategories();
        this.loadTags();

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
        categories.forEach(entry -> {
            String toAdd = "INSERT INTO categories(category_name, category_key) VALUES ('";
            toAdd = toAdd + entry.getName().replace("'", "\\'") + "','" + entry.getId() + "');";
            try {
                this.getStatement().execute(toAdd);
            } catch (SQLException ex) {
                Logger.getLogger(SqlWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

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

    public void addTag(String category) throws IOException, SQLException {
        Tag toAdd = new Tag();
        toAdd.setName(category);
        String key = this.getProductCaller().addProductTag(toAdd);

        String stmt = "INSERT INTO tags(tag_name, tag_key) VALUES ('";
        stmt = stmt + category + "','" + key + "');";
        this.getStatement().execute(stmt);
        this.loadTags();
    }

}
