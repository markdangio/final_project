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
                <div class="col-md-6">
                    <form method="post" action="userControl?action=signup" class="form-signin">
                        <h2 class="form-signin-heading">Sign Up!</h2>
                        <label for="firstname" class="sr-only">First Name</label>
                        <input type="text" name="firstname" id="firstname" class="form-control" placeholder="First Name" required autofocus>
                        </br>
                        <label for="lastname" class="sr-only">Last Name</label>
                        <input type="text" name="lastname" id="lastname" class="form-control" placeholder="Last Name" required autofocus>
                        </br>
                        <label for="email" class="sr-only">Email Address</label>
                        <input type="email" name="email" id="email" class="form-control" placeholder="Email Address" required autofocus>
                        </br>
                        <label for="birthday" class="sr-only">Birthday</label>
                        <input type="text" name="birthday" id="birthday" class="form-control" placeholder="1995-10-15" required autofocus>
                        </br>
                        <label for="signUpUsername" class="sr-only">User Name</label>
                        <input type="text" name="signUpUsername" id="signUpUsername" class="form-control" placeholder="User Name" required autofocus>
                        </br>
                        <label for="signUpPassword" class="sr-only">Password</label>
                        <input type="password" name="signUpPassword" id="signUpPassword" class="form-control" placeholder="Password" required>
                        </br>
                        <label for="signUpPasswordConfirm" class="sr-only">Confirm Password</label>
                        <input type="password" name="signUpPasswordConfirm" id="signUpPasswordConfirm" class="form-control" placeholder="Repeat Password" required>
                        </br>
                        <label for="avatar" class="sr-only">Profile Picture</label>
                        <input type="file" name="avatar" id="avatar">
                        </br>
                        <button class="btn btn-block" name="adduser" type="submit">Sign up</button>
                    </form>
                </div>
                <div align="center" class="col-md-6">
                    <form method="post" action="control?action=login" class="form-signin">
                        <h2 class="form-signin-heading">Please sign in</h2>
                        <label for="loginUsername" class="sr-only">Username</label>
                        <input type="text" name="loginUsername" id="loginUsername" class="form-control" placeholder="Username" required autofocus>
                        </br>
                        <label for="loginPassword" class="sr-only">Password</label>
                        <input type="password" name="loginPassword" id="loginPassword" class="form-control" placeholder="Password" required>
                        </br>
                        <button class="btn btn-block" name="login" type="submit">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
