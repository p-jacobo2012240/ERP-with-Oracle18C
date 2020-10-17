/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Jacobo
 */
public class ConnectionDBB {
    private static ConnectionDBB instance;
     static Connection cn;
    static Statement s;
    static ResultSet rs;
    
    public static ConnectionDBB getInstance(){
        if( instance == null ) {
            instance = new ConnectionDBB();
        }
        return instance;
    }
    
   
    public void cnDATA() throws ClassNotFoundException{
        String jdbUrl = "jdbc:oracle:thin:@127.0.0.1:1521/XE";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String user = "sys as sysdba";
        String pass = "jsdeveloper";
        
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            // read register
            String sqlRead = "SELECT * FROM USER_PG";
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(sqlRead);
            
            while( rs.next() ) {
                System.out.println(" nick : " + rs.getString("NICKNAME"));
                System.out.println(" pass : " + rs.getString("PASSWORD"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDBB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
