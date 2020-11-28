<%-- 
    Document   : home
    Created on : 14-oct-2020, 22:58:29
    Author     : Pablo Jacobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.webapp.models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session.getAttribute("user") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Home Page</title>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Bienvenido : <%= session.getAttribute("user")%>  </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
            <form class="form-inline my-2 my-lg-0" method="post" action="UserController">
                <input  name="accion" type="hidden" value="logout">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Salir</button>
            </form>
        </div>
    </nav>
</head>
<body>


    <div class="card text-center">

        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Usuarios registrados</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="registered-users-list.jsp"  class="btn btn-primary">Ir</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Estudiantes registrados</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="registered-students-list.jsp" class="btn btn-primary">Ir</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Estudiantes en cola de inscripcion</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="students-in-assign-queue-list.jsp" class="btn btn-primary">Ir</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Estudiantes inscritos</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="assigned-students-list.jsp" class="btn btn-primary">Ir</a>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </div>
</body>
</html>
