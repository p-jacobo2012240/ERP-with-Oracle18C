/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.webapp.dao.StudentDao;
import org.webapp.models.Student;

/**
 *
 * @author Pablo Jacobo
 */
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {
    String accion;
    String idStudent = "";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Student student = new Student();
            StudentDao sDao = new StudentDao();
            this.accion = request.getParameter("accion");
            this.idStudent = request.getParameter("idStudent");
            
            student.setCarnet(  request.getParameter("carnet") );
            student.setNombre(request.getParameter("nombre") );
            student.setfNacimiento(request.getParameter("fnacimiento") );
            student.setfRegistro( request.getParameter("fregistro"));
            
            switch( this.accion ) {
                case "register":
                    sDao.registerStudent(student);
                    RequestDispatcher rd=request.getRequestDispatcher("registered-students-list.jsp");            
                    rd.include(request, response);
                break;
                case "delete":
                    boolean res = sDao.deleteStudent(Integer.parseInt( this.idStudent));
                    response.sendRedirect("registered-students-list.jsp");
                break;
                case "registerNewStudentInQueue":
                    System.out.println("registrando...");
                    boolean rs =  sDao.getDatesStudentsRegistereds();
                    response.sendRedirect("students-in-assign-queue-list.jsp");
                break;
                case "assigned":
                    System.out.println("inscritoooo");
                    boolean rsIns = sDao.registerInscription();
                    response.sendRedirect("assigned-students-list.jsp");
                break;
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
