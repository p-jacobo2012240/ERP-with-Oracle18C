<%-- 
    Document   : registered-students-add
    Created on : 02-nov-2020, 18:16:59
    Author     : Pablo Jacobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            #max-space-add{
                width: 40%;
                margin: auto;
                margin-top: 2%;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Home Page</title>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Bienvenido : <%= session.getAttribute("user") %>  </a>
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
        <div class="card" id="max-space-add">
            <div class="card-body">
                <form method="post" action="UserController" >
                    <div class="form-group">
                        <label for="exampleInputEmail1">Usuario</label>
                        <input 
                            type="text" 
                            class="form-control" 
                            id="exampleInputEmail1"
                            name="userWP"
                            aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Clave</label>
                        <input 
                            type="password" 
                            class="form-control" 
                            name="passWP"
                            id="exampleInputPassword1">
                    </div>
                    <input  name="accion" type="hidden" value="register">
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Registrar</button>
                </form>
            </div>
        </div>
    </body>
</html>
