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
import org.webapp.models.PreDate;
import org.webapp.models.User;
import org.webapp.models.Student;
import org.webapp.models.StudentAsigned;
import org.webapp.models.StudentsInQueue;
import org.webapp.models.ValueInQueue;

/**
 *
 * @author Pablo Jacobo
 */
public class ConnectionDB {
    private static ConnectionDB instance;
    String jdbUrl = "jdbc:oracle:thin:@database-1.cowbs9fgq896.us-east-1.rds.amazonaws.com:1521/oraclepg";
    String driver = "oracle.jdbc.driver.OracleDriver";
    String user = "admin";
    String pass = "Progra2020";
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    
    public static ConnectionDB getInstance(){
        if( instance == null ) {
            instance = new ConnectionDB();
        }
        return instance;
    }
    
    public boolean verifyUser(String cmd ) throws ClassNotFoundException{
        List<User> userFind = new ArrayList();
        boolean statusLogin = false;
        
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
                statusLogin = false;
            } else {
                statusLogin = true;
                System.out.println(" user logged..");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statusLogin;
    }
    
    public boolean deleteUser(String cmd ) throws ClassNotFoundException {
        boolean resp = false;
      
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            Statement stInsert = cn.createStatement();
            int rowsDeleted = stInsert.executeUpdate(cmd);
            resp = ( rowsDeleted >= 1 );
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return resp;
    }
    
    public boolean registerUser(String cmd ) throws ClassNotFoundException {
        boolean statusRegister = false;
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            Statement stInsert = cn.createStatement();
            int rows = stInsert.executeUpdate(cmd);
            statusRegister = ( rows >= 1 );
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusRegister;
    }
    
    public List<User> getUsers(String cmd ) throws ClassNotFoundException {
        List<User> users = new ArrayList();
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
            while( rs.next() ) {
                User u = new User();
                u.setIdUsuario( rs.getInt("ID_USUARIO"));
                u.setNickname(rs.getString("NICKNAME"));
                u.setPassword(rs.getString("PASSWORD"));
                users.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public List<Student> getAllStudents(String cmd){
        List<Student> students = new ArrayList();
        
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
            while( rs.next() ) {
                Student s = new Student();
                s.setIdStudent( rs.getInt("ID_ESTUDIANTE"));
                s.setCarnet( rs.getString("CARNET"));
                s.setNombre( rs.getString("NOMBRE"));
                s.setfNacimiento( rs.getString("FECHA_NACIMIENTO"));
                s.setfRegistro( rs.getString("FECHA_REGISTRO"));
                students.add(s);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return students;
    }
    
    
    public boolean registerStudent(String cmd ) throws ClassNotFoundException {
        boolean statusRegister = false;
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            Statement stInsert = cn.createStatement();
            int rows = stInsert.executeUpdate(cmd);
            statusRegister = ( rows >= 1 );
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusRegister;
    }
    
    public boolean deleteStudent(String cmd ) throws ClassNotFoundException {
        boolean status = false;
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            Statement stInsert = cn.createStatement();
            int rows = stInsert.executeUpdate(cmd);
            status = ( rows >= 1 );
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public List<ValueInQueue> getDatesStudents(String cmd ) {
        List<ValueInQueue> queue = new ArrayList();
        
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
            while( rs.next() ) {
                ValueInQueue qu = new ValueInQueue();
                PreDate pr = new PreDate();
                int partValue = 0;
                String val  = rs.getString("FECHA_REGISTRO");
                String[] parts =  val.split("-");
                int part1 = Integer.parseInt( parts[0]); 
                int part2 = Integer.parseInt( parts[1]); 
                int part3 = Integer.parseInt( parts[2]);
                partValue += part1;
                partValue += part2;
                partValue += part3;
                qu.setIdValue( rs.getInt("ID_ESTUDIANTE"));
                qu.setSumValue(partValue);
                queue.add(qu);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return queue;
    }
    
    public Student getOldRegisterInQueue(String cmd ) {        
        List<Student> st = new ArrayList();
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
             while( rs.next() ) {
                Student s = new Student();
                s.setIdStudent( rs.getInt("ID_ESTUDIANTE"));
                s.setCarnet( rs.getString("CARNET"));
                s.setNombre( rs.getString("NOMBRE"));
                s.setfNacimiento( rs.getString("FECHA_NACIMIENTO"));
                s.setfRegistro( rs.getString("FECHA_REGISTRO"));
                st.add(s);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return st.iterator().next();     
    }
    
    
    public boolean registerStudentInQueue(String cmd ) throws ClassNotFoundException {
        boolean statusRegister = false;
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            Statement stInsert = cn.createStatement();
            int rows = stInsert.executeUpdate(cmd);
            statusRegister = ( rows >= 1 );
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusRegister;
    }
    
    
     public List<StudentsInQueue> getAllStudentsInQueue(String cmd){
        List<StudentsInQueue> studentsQ = new ArrayList();
        
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
            while( rs.next() ) {
                StudentsInQueue s = new StudentsInQueue();
                s.setIdStudentInQueue(rs.getInt("ID_COLA_ESTUDIANTE"));
                s.setCarnet(rs.getString("CARNET"));
                s.setStatus( rs.getString("STATUS_INSCRIPCION"));
                s.setfAdicion(rs.getString("FECHA_ADICION"));
                studentsQ.add(s);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return studentsQ;
    }
     
    public List<StudentAsigned> getAllAsignedStudents(String cmd) {
        List<StudentAsigned> studentsAsigned = new ArrayList();
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
             while( rs.next() ) {
                StudentAsigned s = new StudentAsigned();
                s.setIdStudentAsigned(rs.getInt("ID_INSCRIPCION_ESTUDIANTE"));
                s.setCarnet( rs.getString("CARNET"));
                s.setStatus(rs.getString("STATUS_INSCRIPCION"));
                s.setfAdicion(rs.getString("FECHA_ADICION"));
               
                studentsAsigned .add(s);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return studentsAsigned;
    }
     
     /// last methods
     public StudentsInQueue getLastRegisterToInscripted(String cmd ) {        
        List<StudentsInQueue> studentsQ = new ArrayList();
        
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
            while( rs.next() ) {
                StudentsInQueue sf = new StudentsInQueue();
                sf.setIdStudentInQueue(rs.getInt("ID_COLA_ESTUDIANTE"));
                sf.setCarnet(rs.getString("CARNET"));
                sf.setStatus( rs.getString("STATUS_INSCRIPCION"));
                sf.setfAdicion(rs.getString("FECHA_ADICION"));
                studentsQ.add(sf);
            }
            
            System.out.println(" el size es **" + studentsQ.size()  );
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return studentsQ.get(0);  
    } 
     
     public boolean excReg(String cmd ) throws ClassNotFoundException {
        boolean statusRegister = false;
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            Statement stInsert = cn.createStatement();
            int rows = stInsert.executeUpdate(cmd);
            statusRegister = ( rows >= 1 );
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusRegister;
    }
     
     
    public List<User> getLastUserRegister(String cmd) {
        List<User> lastUser = new ArrayList();
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
             while( rs.next() ) {
                User u = new User();
                u.setIdUsuario(rs.getInt("LAST_ID"));
                lastUser.add(u);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return lastUser;
    }
    
    
     public List<Student> getLastSimpleStudent(String cmd) {
        List<Student> lastStudent = new ArrayList();
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
             while( rs.next() ) {
                Student s = new Student();
                s.setIdStudent(rs.getInt("LAST_ID"));
                lastStudent.add(s);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return lastStudent;
    } 
    
     
    public List<StudentsInQueue> getLastRegisterCola(String cmd ) {        
        List<StudentsInQueue> studentsQ = new ArrayList();
        
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
            while( rs.next() ) {
                StudentsInQueue sf = new StudentsInQueue();
                sf.setIdStudentInQueue(rs.getInt("LAST_ID"));
                studentsQ.add(sf);
            }
                        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return studentsQ;  
    }
    
    
    public List<StudentAsigned> getLastInscripcion(String cmd) {
        List<StudentAsigned> studentsAsigned = new ArrayList();
        try {
            Class.forName(driver);
            cn = java.sql.DriverManager.getConnection(jdbUrl, user, pass);
            s = cn.createStatement(); 
            
            ResultSet rs = null;
            Statement stRead = cn.createStatement();
            rs = stRead.executeQuery(cmd);
            
             while( rs.next() ) {
                StudentAsigned s = new StudentAsigned();
                s.setIdStudentAsigned(rs.getInt("LAST_ID"));
                studentsAsigned .add(s);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return studentsAsigned;
    }
 
    
}
