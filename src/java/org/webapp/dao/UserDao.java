/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.webapp.utils.ConnectionDB;
import org.webapp.models.User;

/**
 *
 * @author Pablo Jacobo
 */
public class UserDao {
    private String query = "";
    private ConnectionDB connection = new ConnectionDB();
     private ResultSet resultSet;
    
    public void auth(User user ) {
        List<User> userMatches = new ArrayList();
        this.query = "SELECT * FROM USER_PG";
        
        System.out.println(" el query es * " + this.query );
        
        try {
            this.connection.executeQuerySQL();
            //System.out.println(" el result set es **"+ this.resultSet );
            while( this.resultSet.next() ) {
                System.out.println("*********************");
                /*User userFind = new User();
                userFind.setNickname( this.resultSet.getString("NICKNAME"));
                userFind.setPassword( this.resultSet.getString("PASSWORD"));
                userMatches.add(user);*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(" el obj es" + userMatches.size() );
        //return userMatches.iterator().next();
    }
    
}
