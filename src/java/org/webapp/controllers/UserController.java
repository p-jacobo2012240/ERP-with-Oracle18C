/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.webapp.models.User;
import org.webapp.dao.UserDao;
import org.webapp.models.User;

/**
 *
 * @author Pablo Jacobo
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    User userModel = new User();
    UserDao userDao = new UserDao();
    boolean statusLogin =  false;
    String accion = "";
    HttpSession session;
    String idUsuario;
     
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
                this.idUsuario = request.getParameter("idUser");
                this.userModel.setNickname(request.getParameter("userWP"));
                this.userModel.setPassword(request.getParameter("passWP"));
                this.accion = request.getParameter("accion");
                // default user --> PROGRA2
                // default password -->  umg123 
                switch( this.accion ) {
                    case "login":
                        if ( (this.userModel.getNickname().equals("PROGRA2")) && (this.userModel.getPassword().equals("umg123"))) {
                            this.session = request.getSession(true);
                            this.session.setAttribute("user", this.userModel.getNickname());
                            response.sendRedirect("home.jsp");
                        }else {
                            this.statusLogin = this.userDao.auth(this.userModel);
                            if ( this.statusLogin ) {
                                this.session = request.getSession(true);
                                this.session.setAttribute("user", this.userModel.getNickname());
                                response.sendRedirect("home.jsp");
                            }else {
                                request.setAttribute("loginError","error al crear el usuario en oracle...");
                                RequestDispatcher rd=request.getRequestDispatcher("index.jsp");            
                                rd.include(request, response);
                            }
                        }
                    break;
                    case "logout":
                        this.session = request.getSession();
                        session.invalidate();
                        response.sendRedirect("index.jsp");
                    break;
                    case "register":
                        boolean rspRegister = this.userDao.registerUser(userModel);
                        if ( rspRegister ) {
                            request.setAttribute("error","error al crear el usuario en oracle...");
                            RequestDispatcher rd=request.getRequestDispatcher("registered-users-list.jsp");            
                            rd.include(request, response);
                        } else {
                            request.setAttribute("createdUser","usuario creado en oracle...");
                            RequestDispatcher rd=request.getRequestDispatcher("registered-users-list.jsp");            
                            rd.include(request, response);
                        }
                    break;
                    case "delete":
                        boolean rs = this.userDao.deleteUser( Integer.parseInt(  this.idUsuario ));
                        response.sendRedirect("registered-users-list.jsp");
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
