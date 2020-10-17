/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.dao;
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
    
    public void auth(User user )  {
        try {
            this.query = "SELECT * FROM USER_PG "
                + "WHERE  USER_PG.nickname = '"+ user.getNickname() +"' "
                + "AND  USER_PG.password = '"+ user.getPassword() +"'";
            ConnectionDB.getInstance().verifyUser(this.query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
