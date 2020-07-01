/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Yosua
 */
public class kartu_keluarga {
    private String No_kk;
    private Integer Kode_pos;
    private String Provinsi;
    private String Kota;
    private String Alamat;
    private ArrayList<data_warga> arrdata_warga;

    public ArrayList<data_warga> getArrdata_warga() {
        return arrdata_warga;
    }

    public void setArrdata_warga(ArrayList<data_warga> arrdata_warga) {
        this.arrdata_warga = arrdata_warga;
    }

    public String getNo_kk() {
        return No_kk;
    }

    public void setNo_kk(String No_kk) {
        this.No_kk = No_kk;
    }

    public Integer getKode_pos() {
        return Kode_pos;
    }

    public void setKode_pos(Integer Kode_pos) {
        this.Kode_pos = Kode_pos;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String Provinsi) {
        this.Provinsi = Provinsi;
    }

    public String getKota() {
        return Kota;
    }

    public void setKota(String Kota) {
        this.Kota = Kota;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    
    
}
