/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.models;

/**
 *
 * @author Pablo Jacobo
 */
public class StudentAsigned {
    private int idStudentAsigned;
    private String carnet;
    private String status;
    private String fAdicion;

    public int getIdStudentAsigned() {
        return idStudentAsigned;
    }

    public void setIdStudentAsigned(int idStudentAsigned) {
        this.idStudentAsigned = idStudentAsigned;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getfAdicion() {
        return fAdicion;
    }

    public void setfAdicion(String fAdicion) {
        this.fAdicion = fAdicion;
    }
  
}
