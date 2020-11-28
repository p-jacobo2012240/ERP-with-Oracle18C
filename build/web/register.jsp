<%-- 
    Document   : register
    Created on : 02-nov-2020, 14:46:26
    Author     : Pablo Jacobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>JSP Page</title>
         <style>
            #max-space-card{
                width: 40%;
                margin: auto;
                margin-top: 10%;
            }
        </style>
    </head>
    <body>
        <div class="card" id="max-space-card"  >
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
