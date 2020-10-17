/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.utils;

import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Jacobo
 */
public class ConnectionDB {
   
    public static void executeQuerySQL(){
        System.out.println("holasss*****************");
        String urlDB = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "sys as sysdba";
        String pass = "jsdeveloper";
        
        try {
            Connection cn = DriverManager.getConnection(urlDB, user, pass );
            String sqlRead = "SELECT * FROM USER_PG";
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(sqlRead);
            while( rs.next() ) {
                System.out.println(" nick : " + rs.getString("NICKNAME"));
                System.out.println(" pass : " + rs.getString("PASSWORD"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
