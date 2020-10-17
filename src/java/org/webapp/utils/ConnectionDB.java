/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.utils;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.webapp.models.User;

/**
 *
 * @author Pablo Jacobo
 */
public class ConnectionDB {
    private static ConnectionDB instance;
    String jdbUrl = "jdbc:oracle:thin:@127.0.0.1:1521/XE";
    String driver = "oracle.jdbc.driver.OracleDriver";
    String user = "sys as sysdba";
    String pass = "jsdeveloper";
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    
    public static ConnectionDB getInstance(){
        if( instance == null ) {
            instance = new ConnectionDB();
        }
        return instance;
    }
    
    public void verifyUser(String cmd ) throws ClassNotFoundException{
        List<User> userFind = new ArrayList();
        User userLogged = null;
        
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            String sqlRead = cmd;
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(sqlRead);
            
            if ( rs.next() == false) {
                System.out.println(" user not exist in db ... ");
                userLogged = null;
            } else {
                System.out.println(" user logged..");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
