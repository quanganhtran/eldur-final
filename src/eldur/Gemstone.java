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
public class Gemstone {
    private String name;
    private int type;
    public String refinementPrefix;
    
    public Gemstone(String n, int t) {
        name = n;
        type = t;
    }
    
    public Gemstone(String r) {
        refinementPrefix = r;
    }
}
