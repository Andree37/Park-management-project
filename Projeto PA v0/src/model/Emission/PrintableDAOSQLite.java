/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Emission;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AndreLaptop
 */
public class PrintableDAOSQLite implements PrintableDAO {

    public final static String databaseName = "printables";
    private Connection con;

    public PrintableDAOSQLite(String url, String db_user, String db_pass) {
        try {
            this.con = DriverManager.getConnection(url + databaseName, db_user, db_pass);
            System.out.println(url);
            System.out.println(con.toString());

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
   

    private HashSet<Printable> querySQL(String sql){
        HashSet<Printable> list = new HashSet<>();
     try{
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()){     
             list.add((Printable)rs);     
         }
         rs.close();
     }catch (SQLException ex){
      ex.getMessage();
     }
     return list;
     }
    
    
    private boolean updateSQL(String sql) {
       try{
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        return true;
       }catch (SQLException ex){
        return false;
       }
    }


    @Override
    public Printable loadPrintable(int id, String type) {
       String sql = String.format("SELECT * FROM printables WHERE id = %d and type = %s", id, type);
        HashSet<Printable> list = querySQL(sql);
        return (list.size()>0) ? (Printable)list.toArray()[0] : null;
    }

    @Override
    public void savePrintable(Printable p) {
        String sql = String.format(
        "INSERT INTO printables (id, type, print) VALUES ('%d','%s','%s')",
         p.getID(),p.getType(),p.print());
        updateSQL(sql);
    }


}
