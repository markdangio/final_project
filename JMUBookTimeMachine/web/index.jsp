<%-- 
    Document   : index
    Created on : Nov 30, 2016, 10:01:47 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/index.css">
        <title>JMU Book Time Machine</title>
    </head>

    <body>
        <h1>JMU Book Time Machine</h1>
        <div class="container">
            <div class="row">
                <div align="center" class="col-md-6">
                    <form method="post" action="userControl?action=signup" class="form-signin">
                        <h2 class="form-signin-heading">Sign Up!</h2>
                        <label for="firstname">First Name
                        <input type="text" name="firstname" id="firstname" class="form-control" placeholder="First Name" required autofocus></label>
                        </br>
                        <label for="lastname">Last Name
                        <input type="text" name="lastname" id="lastname" class="form-control" placeholder="Last Name" required autofocus></label>
                        </br>
                        <label for="email">Email Address
                        <input type="email" name="email" id="email" class="form-control" placeholder="Email Address" required autofocus></label>
                        </br>
                        <label for="birthday">Birthday
                        <input type="text" name="birthday" id="birthday" class="form-control" placeholder="1995-10-15" required autofocus></label>
                        </br>
                        <label for="signUpUsername">User Name
                        <input type="text" name="signUpUsername" id="signUpUsername" class="form-control" placeholder="User Name" required autofocus></label>
                        </br>
                        <label for="signUpPassword">Password
                        <input type="password" name="signUpPassword" id="signUpPassword" class="form-control" placeholder="Password" required></label>
                        </br>
                        <label for="signUpPasswordConfirm">Confirm Password
                        <input type="password" name="signUpPasswordConfirm" id="signUpPasswordConfirm" class="form-control" placeholder="Repeat Password" required></label>
                        </br>
                        <label for="signUpSecurityAnswer">Security Question
                        <input type="text" name="signUpSecurityAnswer" id="signUpSecurityAnswer" class="form-control" placeholder="Mother's Maiden Name" required autofocus></label>
                        </br>
                        <label for="avatar">Profile Picture
                        <input type="file" name="avatar" id="avatar" style="color:white;"></label>
                        </br>
                        <button class="btn btn-block" name="adduser" type="submit">Sign up</button>
                    </form>
                </div>
                <div align="center" class="col-md-6">
                    <form method="post" action="control?action=login" class="form-signin">
                        <h2 class="form-signin-heading">Login</h2>
                        <label for="loginUsername">Username
                        <input type="text" name="loginUsername" id="loginUsername" class="form-control" placeholder="Username" required autofocus></label>
                        </br>
                        <label for="loginPassword">Password
                        <input type="password" name="loginPassword" id="loginPassword" class="form-control" placeholder="Password" required></label>
                        </br>
                        <button class="btn btn-block" name="login" type="submit">Sign in</button>
                    </form>
                    <a href="recovery.jsp" style="color:purple;"><p>Forgot Password?</p></a>
                </div>
            </div>
        </div>
    </body>
</html>
