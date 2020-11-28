<%-- 
    Document   : index
    Created on : 14-oct-2020, 22:58:14
    Author     : Pablo Jacobo
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src='https://cdn.jsdelivr.net/npm/sweetalert2@9'></script>
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
         
        <%  
            // setting cases
            String login_msg=(String)request.getAttribute("loginError");  
            String createdUser = (String)request.getAttribute("createdUser");  
            
            if(login_msg!= null){
                out.println("<script src='https://cdn.jsdelivr.net/npm/promise-polyfill'></script>");
                out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@9'></script>");
                out.println("<script>");
                out.println(" setTimeout( function(){ Swal.fire('usuario/clave incorrect@', '', 'error' ) },200 )  ");
                out.println("</script>");
            }
            
            if( createdUser != null){
                out.println("<script src='https://cdn.jsdelivr.net/npm/promise-polyfill'></script>");
                out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@9'></script>");
                out.println("<script>");
                out.println(" setTimeout( function(){ Swal.fire('Usuario creado correctamente !!!', '', 'success' ) },200 )  ");
                out.println("</script>");
            }
            
        %>

        
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
                    <input  name="accion" type="hidden" value="login">
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
                    <!--a href="register.jsp"> Registrar un nuevo usuario </a-->
                </form>
            </div>
        </div>
    </body>
</html>