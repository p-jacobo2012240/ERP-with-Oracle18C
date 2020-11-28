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
    
    public boolean auth(User user )  {
        boolean statusLogin = false;
        try {
            this.query = "SELECT * FROM  USUARIOS "
                + "WHERE  USUARIOS.nickname = '"+ user.getNickname() +"' "
                + "AND  USUARIOS.password = '"+ user.getPassword() +"'";
            statusLogin = ConnectionDB.getInstance().verifyUser(this.query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusLogin;
    }
    
    public boolean registerUser(User user ) {
        boolean statusRegister = false;
        try {
            int value = this.getLastRegister();
           
            this.query = "INSERT INTO USUARIOS(ID_USUARIO,"
                + " NICKNAME, PASSWORD) VALUES ("+ ( value + 1 ) +", "
                 + "'"+ user.getNickname() +"', '" + user.getPassword() + "' )";
            
            statusRegister = ConnectionDB.getInstance().registerUser(this.query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusRegister;
    }
    
    public boolean deleteUser(int idUser ) {
        boolean statusUser = false;
        this.query = "DELETE FROM USUARIOS WHERE USUARIOS.ID_USUARIO = " + idUser;
        try {
            statusUser = ConnectionDB.getInstance().deleteUser(this.query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusUser;
    }
    
    public List<User> getRegisteredUsers() {
        List<User> listUsers = new ArrayList();
        this.query = "SELECT * FROM  USUARIOS";
        try {
            listUsers = ConnectionDB.getInstance().getUsers(this.query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers;
    }
    
    
    public int getLastRegister() {
        int value = 0;
        this.query = "SELECT MAX(ID_USUARIO) AS \"LAST_ID\" FROM USUARIOS";
        List<User> usrs = ConnectionDB.getInstance().getLastUserRegister(this.query);
        value = usrs.iterator().next().getIdUsuario();
        return value;
    }
    
    
    
}
