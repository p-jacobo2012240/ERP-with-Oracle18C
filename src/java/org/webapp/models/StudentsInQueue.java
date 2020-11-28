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
public class StudentsInQueue {
    private int idStudentInQueue;
    private String carnet;
    private String status;
    private String fAdicion;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdStudentInQueue() {
        return idStudentInQueue;
    }

    public void setIdStudentInQueue(int idStudentInQueue) {
        this.idStudentInQueue = idStudentInQueue;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getfAdicion() {
        return fAdicion;
    }

    public void setfAdicion(String fAdicion) {
        this.fAdicion = fAdicion;
    }
   
}
