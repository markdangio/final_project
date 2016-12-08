<%-- 
    Document   : recovery
    Created on : Nov 29, 2016, 2:39:15 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/recovery.css">
        <title>Recovery</title>
    </head>
    <body>
        <div align="center" class="container">
            <h1>JMU Book Time Machine</h1>
            <form method="post" action="userControl?action=checkUpdatePass" class="form-signin">
                <h2 class="form-signin-heading">Password Recovery</h2>
                <label for="recoveryUserName" class="sr-only">Username</label>
                <input type="text" name="recoveryUserName" id="username" class="form-control" placeholder="Username" required autofocus>
                </br>
                <label for="recoverySQ" class="sr-only">Security Question</label>
                <input type="text" name="recoverySQ" id="sq" class="form-control" placeholder="Security Question Answer" required>
                </br>
                <button class="btn btn-block" name="recover" type="submit">Change Password</button>
            </form>
        </div>
    </body>
</html>
