/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.data_warga;
import model.jumlah_warga;
import model.kartu_keluarga;

/**
 *
 * @author Yosua
 */
public class controller {
    koneksi koneksi;
    ArrayList<kartu_keluarga> arrkartu_keluarga;
    kartu_keluarga Detail_keluarga;
    data_warga data_warga;
    ArrayList<jumlah_warga> arrJumlah_warga;
    ArrayList<data_warga> arrData_Warga;
    
    public controller(){
        this.koneksi = new koneksi();
        this.arrkartu_keluarga = new ArrayList<>();
        this.Detail_keluarga = new kartu_keluarga();
        this.arrJumlah_warga = new ArrayList<>();
        this.arrData_Warga = new ArrayList<>();
        this.data_warga = new data_warga();
    }
    
    public ArrayList<kartu_keluarga> getKartu_Keluarga() throws SQLException{
        this.arrkartu_keluarga.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM KARTU_KELUARGA ORDER BY NO_KK DESC");
        while(rs.next()){
            kartu_keluarga kk = new kartu_keluarga();
            kk.setNo_kk(rs.getString("NO_KK"));
            kk.setKode_pos(rs.getInt("KODE_POS"));
            kk.setProvinsi(rs.getString("PROVINSI"));
            kk.setKota(rs.getString("KOTA"));
            kk.setAlamat(rs.getString("ALAMAT"));
            arrkartu_keluarga.add(kk);
        }
        return this.arrkartu_keluarga;
    }
    public data_warga GetDataWargaNik(String nik) throws SQLException{
        ResultSet rs = this.koneksi.GetData("SELECT * FROM DATA_WARGA_06979 WHERE NIK = " + nik);
        rs.next();
        data_warga dw = new data_warga();
        dw.setNik(rs.getInt("NIK"));
        dw.setNo_kk(rs.getString("NO_KK"));
        dw.setNama(rs.getString("NAMA"));
        dw.setStatus_keluarga(rs.getString("STATUS_KELUARGA"));
        dw.setTanggal_lahir(new Date(rs.getString("TANGGAL_LAHIR")));
        dw.setJenis_kelamin(rs.getString("JENIS_KELAMIN"));
        dw.setUmur(rs.getInt("UMUR"));
        dw.setNo_telp(rs.getString("NO_TELP"));
        this.data_warga = dw;
        return this.data_warga;
    }
    
    public ArrayList<jumlah_warga> getJumlah_warga() throws SQLException{
        this.arrJumlah_warga.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM JUMLAH_WARGA");
        while(rs.next()){
            jumlah_warga jw = new jumlah_warga();
            jw.setNO_KK(rs.getString("NO_KK"));
            jw.setKota(rs.getString("KOTA"));
            jw.setAlamat(rs.getString("ALAMAT"));
            jw.setJumlah(rs.getString("JUMLAH_ORANG"));
            this.arrJumlah_warga.add(jw);
        }
        return this.arrJumlah_warga;
    }
    
    public kartu_keluarga getDetail_Keluarga(String no_kk) throws SQLException{
        ResultSet rs = this.koneksi.GetData("SELECT * FROM KARTU_KELUARGA WHERE NO_KK = '" + no_kk + "' ORDER BY KARTU_KELUARGA.NO_KK DESC");
        while(rs.next()){
            kartu_keluarga kk = new kartu_keluarga();
            kk.setNo_kk(rs.getString("NO_KK"));
            kk.setKode_pos(rs.getInt("KODE_POS"));
            kk.setProvinsi(rs.getString("PROVINSI"));
            kk.setKota(rs.getString("KOTA"));
            kk.setAlamat(rs.getString("ALAMAT"));
            
            ResultSet rsData = this.koneksi.GetData("SELECT JUMLAH_WARGA.JUMLAH_ORANG, DATA_WARGA_06979.* FROM JUMLAH_WARGA JOIN DATA_WARGA_06979 ON JUMLAH_WARGA.NO_KK = DATA_WARGA_06979.NO_KK WHERE JUMLAH_WARGA.NO_KK = '" + no_kk + "' ORDER BY JUMLAH_WARGA.NO_KK DESC");
            ResultSet rsJm = this.koneksi.GetData("SELECT * FROM JUMLAH_WARGA WHERE NO_KK = '" + no_kk + "'");
            ArrayList<data_warga> data = new ArrayList<>();
            rsJm.first();
            if(rsJm.getString("JUMLAH_ORANG").equals("0")){
                data_warga dw = new data_warga();
                dw.setJumlah("0");
                data.add(dw);
            }else{
                while(rsData.next()){
                data_warga dw = new data_warga();
                dw.setNik(rsData.getInt("NIK"));
                    System.out.println("Nama : " + rsData.getString("NAMA"));
                dw.setNama(rsData.getString("NAMA"));
                dw.setTanggal_lahir(new Date(rsData.getString("TANGGAL_LAHIR")));
                dw.setJenis_kelamin(rsData.getString("JENIS_KELAMIN"));
                dw.setStatus_keluarga(rsData.getString("STATUS_KELUARGA"));
                dw.setUmur(rsData.getInt("UMUR"));
                dw.setNo_telp(rsData.getString("NO_TELP"));
                dw.setJumlah(rsData.getString("JUMLAH_ORANG"));
                
                data.add(dw);
            }
            }
            
            kk.setArrdata_warga(data);
            this.Detail_keluarga = kk;
        }
        return this.Detail_keluarga;
    }
    
    public void insert_warga(data_warga data){
        String tglLahir = new SimpleDateFormat("dd/MM/yyyy").format(data.getTanggal_lahir());
        this.koneksi.ManipulasiData("INSERT INTO DATA_WARGA_06979 VALUES (" + data.getNik() + ", '" + data.getNo_kk() + "', '" + data.getNama() + "', '" + data.getStatus_keluarga() + "', TO_DATE('" + tglLahir + "', 'dd/MM/yyyy'), '" + data.getJenis_kelamin() + "', " + data.getUmur().toString() + ", " + data.getNo_telp() + ")");
        System.out.println("INSERT INTO DATA_WARGA_06979 VALUES (NIK_WARGA.NEXTVAL, '"+ data.getNo_kk() + "', '" + data.getNama() + "', '" + data.getStatus_keluarga() + "', TO_DATE('" + tglLahir + "', 'dd/MM/yyyy'), '" + data.getJenis_kelamin() + "', " + data.getUmur().toString() + ", " + data.getNo_telp() + ")");
    }
    
    public ArrayList<data_warga> getDataWarga() throws SQLException{
        this.arrData_Warga.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM DATA_WARGA_06979 ORDER BY NO_KK DESC");
        while(rs.next()){
            data_warga data = new data_warga();
            data.setNo_kk(rs.getString("NO_KK"));
            data.setNik(rs.getInt("NIK"));
            data.setNama(rs.getString("NAMA"));
            data.setStatus_keluarga(rs.getString("STATUS_KELUARGA"));
            data.setJenis_kelamin(rs.getString("JENIS_KELAMIN"));
            data.setTanggal_lahir(new Date(rs.getString("TANGGAL_LAHIR")));
            data.setUmur(rs.getInt("UMUR"));
            this.arrData_Warga.add(data);
        }
        return this.arrData_Warga;
    }
    
    public void delete_warga(String nik){
        this.koneksi.ManipulasiData("DELETE DATA_WARGA_06979 WHERE NIK = " + nik);
    }
    
    public void UpdateWarga(data_warga dw, String nik){
        String tgl = new SimpleDateFormat("dd/MM/yyyy").format(dw.getTanggal_lahir());
        this.koneksi.ManipulasiData("UPDATE DATA_WARGA_06979 SET NIK = " + dw.getNik() + ", NO_KK = '" + dw.getNo_kk() + "', NAMA = '" + dw.getNama() + "', STATUS_KELUARGA = '" + dw.getStatus_keluarga() + "', TANGGAL_LAHIR = TO_DATE('" + tgl + "', 'dd/MM/yyyy'), JENIS_KELAMIN = '" + dw.getJenis_kelamin() + "', UMUR = " + dw.getUmur() + ", NO_TELP = 0" + dw.getNo_telp() + " WHERE NIK = " + nik);
        
    }
}
