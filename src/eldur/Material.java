/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

/**
 *
 * @author trand
 */
public class Material {
    private String name;
    private int stock;
    
    public Material (String n) {
        name = n;
        stock = 0;
    }
    
    public Material (String n, int s) {
        name = n;
        stock = s;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int s) {
        stock = s;
    }
    
    public void gain(int amount) {
        stock += amount;
    }
    
    public void consume(int amount) {
        stock -= amount;
    }
}
