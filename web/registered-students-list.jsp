<%-- 
    Document   : students-list
    Created on : 02-nov-2020, 15:58:23
    Author     : Pablo Jacobo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.webapp.models.Student" %>
<%@page import="org.webapp.dao.StudentDao" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session.getAttribute("user") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <style>
            #margin-sts{
                margin-top: 2%;
            }
        </style>
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

    <div  id="max-space-dsh" class="container" >
        <div class="btn-group btn-lg btn-block" class="col-sm-3" >
            <a href="registered-students-add.jsp"  class="btn btn-success btn-lg btn-block">Agregar</a>
            <a href="#"  class="btn "></a>
            <a href="home.jsp" class="btn btn-warning btn-lg btn-block">Regresar</a>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Carnet</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Fecha Nacimiento</th>
                    <th scope="col">Fecha Registro</th>
                    <th scope="col">Opciones</th>
                </tr>
            </thead>
            <tbody>

                <%
                    StudentDao sDao = new StudentDao(); 
                    List<Student> sList = sDao.getAllStudentsRegistered();
                    Iterator<Student> iteradorStudent = sList.iterator();
                    Student s = new Student();

                    while (iteradorStudent.hasNext()) {
                        s = iteradorStudent.next();
                %>

                <tr>
                    <td><%= s.getCarnet() %> </td>
                    <td><%= s.getNombre() %> </td>
                    <td><%= s.getfNacimiento() %> </td>
                    <td><%= s.getfRegistro() %> </td>
                    <td> 
                        <a 
                            type="button" 
                            class="btn btn-danger"
                            href="StudentController?accion=delete&idStudent=<%= s.getIdStudent() %> "
                            > Eliminar </a>
                    </td>

                </tr>
            <br>    


            <%
                }
            %>

            </tbody>
        </table>
    </div>  


</body>
</html>
