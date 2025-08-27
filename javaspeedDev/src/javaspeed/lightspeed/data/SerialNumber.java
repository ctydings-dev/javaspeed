/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.lightspeed.data;

/**
 *
 * @author rfancher
 */
public class SerialNumber {
 
    public String number;
    public String owner;

    public SerialNumber(String number, String owner) {
        this.number = number;
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }
    
    
}
