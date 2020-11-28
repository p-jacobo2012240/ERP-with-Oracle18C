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
import org.webapp.models.Student;
import org.webapp.utils.ConnectionDB;
import org.webapp.models.ValueInQueue;
import org.webapp.models.StudentsInQueue;
import org.webapp.models.StudentAsigned;

/**
 *
 * @author Pablo Jacobo
 */
public class StudentDao {
     private String query = "";
    
    public List<Student> getAllStudentsRegistered(){
        List<Student> studentsList = new ArrayList();
        this.query = "SELECT * FROM ESTUDIANTES";
        studentsList =  ConnectionDB.getInstance().getAllStudents(this.query);
        return studentsList;
    }
    
    public boolean registerStudent(Student student ){
        boolean statusRegister = false;
        int value = 0;
        
        try {
            
            value = this.getLastSimpleStudent();
            
            this.query = "INSERT INTO ESTUDIANTES"
                + "(ID_ESTUDIANTE, CARNET, "
                + "NOMBRE, FECHA_NACIMIENTO, FECHA_REGISTRO ) "
                + "VALUES ("+ (value + 1) +", '"+ student.getCarnet() +"', "
                + "'"+ student.getNombre() +"', '"+ student.getfNacimiento() +"', "
                + "'"+ student.getfRegistro() +"' )";
            
             statusRegister = ConnectionDB.getInstance().registerStudent(this.query);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusRegister;
    }
    
    public boolean deleteStudent(int idStudent ) {
        boolean status = false;
        this.query = "DELETE FROM ESTUDIANTES WHERE ESTUDIANTES.ID_ESTUDIANTE = " + idStudent;
        try {           
            status = ConnectionDB.getInstance().deleteStudent(query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public List<StudentsInQueue> getAllStudentsInQueue() {
        List<StudentsInQueue> studentsQ = new ArrayList();
        this.query = "SELECT * FROM COLA_ESTUDIANTES";
        studentsQ = ConnectionDB.getInstance().getAllStudentsInQueue(this.query);
        return studentsQ;
    }
    
    // cola de inscripcion
    public boolean getDatesStudentsRegistereds() {
        boolean result = false;
        try {
             List<ValueInQueue> queue = new ArrayList();
             ValueInQueue queueObj = new ValueInQueue();
             this.query = "SELECT E.FECHA_REGISTRO, E.ID_ESTUDIANTE FROM ESTUDIANTES E";
             
             queue = ConnectionDB.getInstance().getDatesStudents(this.query);
             
             // old register in queue
             queueObj = this.getMinValue(queue);
             Student st = new Student();
             this.query = "SELECT * FROM ESTUDIANTES "
                     + "WHERE ESTUDIANTES.ID_ESTUDIANTE = "+ queueObj.getIdValue();
             st = ConnectionDB.getInstance().getOldRegisterInQueue(this.query);
                         
             this.query = "DELETE FROM ESTUDIANTES WHERE ESTUDIANTES.ID_ESTUDIANTE = " + st.getIdStudent();
             boolean resultDeletedUser = ConnectionDB
                     .getInstance().deleteStudent(this.query);
             
             int value = this.getLastRegisterStudentInCola();
             
            this.query = "INSERT INTO COLA_ESTUDIANTES"
                + "(ID_COLA_ESTUDIANTE, CARNET, "
                + "STATUS_INSCRIPCION, FECHA_ADICION) "
                + "VALUES("+ (value + 1) +", '"+ st.getCarnet() + "',"
                + " 'NO INSCRITO', '07-11-2020')";
                          
             try {
                 result = ConnectionDB.getInstance().registerStudentInQueue(this.query);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             return result;
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return result;
    }
    
    public ValueInQueue  getMinValue(List<ValueInQueue> numbers){
        ValueInQueue minValue = numbers.get(0);
        for(int i=1;i<numbers.size();i++){
            if(numbers.get(i).getSumValue() < minValue.getSumValue() ){
                minValue = numbers.get(i);
            }
        }
        return minValue;
    }
    
    public List<StudentAsigned> getAllAsignedStudents() {
        List<StudentAsigned> studentsAsigned = new ArrayList();
        this.query = "SELECT * FROM INSCRIPCION_ESTUDIANTES"; 
       
        studentsAsigned = ConnectionDB
            .getInstance().getAllAsignedStudents(this.query);
        
        return studentsAsigned;
    }
    
    public boolean registerInscription() {
        boolean result = false;
        StudentsInQueue  sQu = new StudentsInQueue();
        this.query = "SELECT  * FROM COLA_ESTUDIANTES"
            + " WHERE ID_COLA_ESTUDIANTE = "
            + "( SELECT MAX(ID_COLA_ESTUDIANTE) FROM COLA_ESTUDIANTES )";
        
        System.out.println(" value "+ sQu.getCarnet() );
        
        sQu = ConnectionDB.getInstance().getLastRegisterToInscripted(this.query);
        
        this.query = "UPDATE COLA_ESTUDIANTES "
            + "SET STATUS_INSCRIPCION = 'inscrito' "
            + "WHERE ID_COLA_ESTUDIANTE = " + sQu.getIdStudentInQueue();
        
         try {
            boolean resuQu = ConnectionDB.getInstance().excReg(this.query);
            
            int val = this.getLastInscripcion();
            
            this.query = "INSERT INTO INSCRIPCION_ESTUDIANTES"
                + "(ID_INSCRIPCION_ESTUDIANTE, "
                + "CARNET, STATUS_INSCRIPCION, "
                + "FECHA_ADICION ) "
                + "VALUES ("+ (val + 1 ) +", '"+ sQu.getCarnet() +"', "
                + "'INSCRITO', '07-11-2020' )";
                        
            result = ConnectionDB.getInstance().excReg(this.query);
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
         }
                
        return result;
    }
    
    public int getLastSimpleStudent (){
        int value = 0;
        this.query = "SELECT MAX(ID_ESTUDIANTE) AS \"LAST_ID\" FROM  ESTUDIANTES ";
        List<Student> list = ConnectionDB.getInstance().getLastSimpleStudent(this.query);
        value = list.iterator().next().getIdStudent();
        return value;
    }
    
    public int getLastRegisterStudentInCola(){
        int value = 0;
        this.query = "SELECT MAX(ID_COLA_ESTUDIANTE) AS \"LAST_ID\" FROM  COLA_ESTUDIANTES ";
        List<StudentsInQueue> studentsQ  = ConnectionDB.getInstance().getLastRegisterCola(this.query);
        value = studentsQ.iterator().next().getIdStudentInQueue();
        return value;
    }
    
    public int getLastInscripcion(){
        int value = 0;
        this.query = "SELECT MAX(ID_INSCRIPCION_ESTUDIANTE) AS \"LAST_ID\" FROM  INSCRIPCION_ESTUDIANTES";
        List<StudentAsigned> studentsAsigned = ConnectionDB.getInstance().getLastInscripcion(this.query);
        value = studentsAsigned.iterator().next().getIdStudentAsigned();
        return value;
    }
    
}
