/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.Connection;
import java.sql.SQLException;
import javaspeed.lightspeed.data.Customer;

/**
 *
 * @author ctydi
 */
public class SqlWriter {

    public static void writeCustomerToDB(Connection con, Customer toWrite) throws SQLException {
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
            con.createStatement().execute(stmt);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
