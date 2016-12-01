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
        <title>JMU Book Time Machine</title>
    </head>

    <body>
        <h1>JMU Book Time Machine</h1>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
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
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                        </br>
                        <label for="inputPasswordConfirm" class="sr-only">Confirm Password</label>
                        <input type="password" id="inputPasswordConfirm" class="form-control" placeholder="Repeat Password" required>
                        </br>
                        <label for="inputAvatar" class="sr-only">Avatar</label>
                        Profile Picture
                        <input type="file" id="inputAvatar">
                        </br>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
                    </form>
                </div>
                <div class="col-md-6">
                    <form action="login"  method="post" class="form-signin">
                        <h2 class="form-signin-heading">Please sign in</h2>
                        <label for="username" class="sr-only">Username</label>
                        <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>
                        <label for="password" class="sr-only">Password</label>
                        <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
