<%-- 
    Document   : changePass
    Created on : Nov 29, 2016, 2:39:15 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/changePass.css">
        <title>Recovery</title>
    </head>
    <body>
        <div align="center" class="container">
            <h1>JMU Book Time Machine</h1>
            <form method="post" action="userControl?action=updatePass" class="form-signin">
                <h2 class="form-signin-heading">Change Password</h2>
                <label for="newPassword" class="sr-only">New Password</label>
                <input type="password" name="newPassword" id="username" class="form-control" placeholder="New Password" required autofocus>
                </br>
                <label for="newPasswordConfirm" class="sr-only">Confirm New Password</label>
                <input type="password" name="newPasswordConfirm" id="sq" class="form-control" placeholder="Confirm New Password" required>
                </br>
                <button class="btn btn-block" name="saveNewPass" type="submit">Save Password</button>
            </form>
        </div>
    </body>
</html>
