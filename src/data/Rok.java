/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author nikol
 */
public class Rok {

    private String oznaka;
    private int trajanje;

    public Rok(String oznaka, int trajanje) {
        this.oznaka = oznaka;
        this.trajanje = trajanje;
    }

    public String getOznaka() {
        return oznaka;
    }

    public int getTrajanje() {
        return trajanje;
    }
    
    
}
