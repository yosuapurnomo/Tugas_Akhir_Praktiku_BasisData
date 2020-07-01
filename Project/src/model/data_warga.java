/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Yosua
 */
public class data_warga {
    private Integer Nik;
    private String No_kk;
    private String Nama;
    private String Status_keluarga;
    private Date Tanggal_lahir;
    private String Jenis_kelamin;
    private Integer Umur;
    private String No_telp;
    private String jumlah;

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
    

    public Integer getNik() {
        return Nik;
    }

    public void setNik(Integer Nik) {
        this.Nik = Nik;
    }

    public String getNo_kk() {
        return No_kk;
    }

    public void setNo_kk(String No_kk) {
        this.No_kk = No_kk;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getStatus_keluarga() {
        return Status_keluarga;
    }

    public void setStatus_keluarga(String Status_keluarga) {
        this.Status_keluarga = Status_keluarga;
    }

    public Date getTanggal_lahir() {
        return Tanggal_lahir;
    }

    public void setTanggal_lahir(Date Tanggal_lahir) {
        this.Tanggal_lahir = Tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return Jenis_kelamin;
    }

    public void setJenis_kelamin(String Jenis_kelamin) {
        this.Jenis_kelamin = Jenis_kelamin;
    }

    public Integer getUmur() {
        return Umur;
    }

    public void setUmur(Integer Umur) {
        this.Umur = Umur;
    }

    public String getNo_telp() {
        return No_telp;
    }

    public void setNo_telp(String No_telp) {
        this.No_telp = No_telp;
    }

    public void getNo_kk(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
