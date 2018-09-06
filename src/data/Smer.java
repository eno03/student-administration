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
public class Smer {

    private int smer_id;
    private String smer_naziv;
    private String smer_oznaka;
    private int smer_espb;

    public Smer(int smer_id, String smer_naziv, String smer_oznaka, int smer_espb) {
        this.smer_id = smer_id;
        this.smer_naziv = smer_naziv;
        this.smer_oznaka = smer_oznaka;
        this.smer_espb = smer_espb;
    }

    public int getSmer_id() {
        return smer_id;
    }

    public void setSmer_id(int smer_id) {
        this.smer_id = smer_id;
    }

    public String getSmer_naziv() {
        return smer_naziv;
    }

    public void setSmer_naziv(String smer_naziv) {
        this.smer_naziv = smer_naziv;
    }

    public String getSmer_oznaka() {
        return smer_oznaka;
    }

    public void setSmer_oznaka(String smer_oznaka) {
        this.smer_oznaka = smer_oznaka;
    }

    public int getSmer_espb() {
        return smer_espb;
    }

    public void setSmer_espb(int smer_espb) {
        this.smer_espb = smer_espb;
    }

}
