/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensus;

import controller.controller;
import database.koneksi;
import java.sql.SQLException;
import view.data_warga_view;

/**
 *
 * @author Yosua
 */
public class Sensus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        new data_warga_view().show();
    }
    
}
