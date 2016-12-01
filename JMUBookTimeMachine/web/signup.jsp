<%-- 
    Document   : signup
    Created on : Nov 29, 2016, 2:39:15 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
   <%
        
        boolean loggedIn;
        
        if(session.getAttribute("loggedIn") == null){
            loggedIn = false;
        }
        else{
            loggedIn = (boolean) session.getAttribute("loggedIn");
        }
        if(!loggedIn)
        {  
           
            %>
            <jsp:forward page="index.html" />
            <%
        }
    %>
    <body>
        <div class="container">

            <form class="form-signin">
                <h2 class="form-signin-heading">Sign Up!</h2>
                <label for="inputUserName" class="sr-only">User Name</label>
                <input type="text" id="inputUserName" class="form-control" placeholder="User Name" required autofocus>
                </br>
                <label for="inputFirstName" class="sr-only">First Name</label>
                <input type="text" id="inputFirstName" class="form-control" placeholder="First Name" required autofocus>
                </br>
                <label for="inputLastName" class="sr-only">Last Name</label>
                <input type="text" id="inputLastName" class="form-control" placeholder="Last Name" required autofocus>
                </br>
                <label for="inputEmail" class="sr-only">Email Address</label>
                <input type="email" id="inputEmail" class="form-control" placeholder="Email Address" required autofocus>
                </br>
                <label for="inputBirthday" class="sr-only">Birthday</label>
                <input type="text" id="inputBirthday" class="form-control" placeholder="10/10/1994" required autofocus>
                </br>
                <label for="inputAvatar" class="sr-only">Avatar</label>
                Profile Picture
                <input type="file" id="inputAvatar">
                </br>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                </br>
                <label for="inputPasswordConfirm" class="sr-only">Confirm Password</label>
                <input type="password" id="inputPasswordConfirm" class="form-control" placeholder="Repeat Password" required>
                </br>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
            </form>

        </div> <!-- /container --
    </body>
</html>
