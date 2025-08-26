/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.lightspeed.data;

/**
 *
 * @author ctydi
 */
public class UPCCode extends ProductCode {

    public UPCCode() {
        this.setType("UPC");
    }

    public UPCCode(String code) {
        this.setType("UPC");
        this.setCode(code);
    }

}
