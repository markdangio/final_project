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

        <script type="text/JavaScript">
            window.onload = function() {
                try { // Firefox, Opera, IE7
                  request = new XMLHttpRequest()
                }
                catch (e) {
                  try { // IE6
                    request = new ActiveXObject('MSXML2.XMLHTTP.5.0')
                  }
                  catch (e) {
                    request = false
                  }
                }
                if (! request) alert('Ajax setup failed')
            }
            
            function validateForm() {
                var valid = true
                // validate name
                var firstname = get('firstname').value
                get('firstnamee').innerHTML = ''
                if (! /^[\a-zA-Z]*$/.test(firstname)) {
                    valid = false
                    get('firstnamee').innerHTML = 'First name can contain only a-z, A-Z'
                }

                var lastname = get('lastname').value
                get('lastnamee').innerHTML = ''
                if (! /^[\a-zA-Z]*$/.test(lastname)) {
                    valid = false
                    get('lastnamee').innerHTML = 'Last name can contain only a-z, A-Z'
                }
                
                var email = get('email').value
                get('emaile').innerHTML = ''
                if (! /^[\a-zA-Z0-9.@]*$/.test(email)) {
                    valid = false
                    get('emaile').innerHTML = 'Email can contain only a-z, A-Z, ., and @'
                }
                
                var birthday = get('birthday').value
                get('birthdaye').innerHTML = ''
                if (! /^[\-0-9]*$/.test(birthday)) {
                    valid = false
                    get('birthdaye').innerHTML = 'Birthday can contain only 0-9 and hyphen'
                }

                var username = get('signUpUsername').value
                get('signUpUsernamee').innerHTML = ''
                if (! /^[\a-zA-Z0-9]*$/.test(username)) {
                    valid = false
                    get('signUpUsernamee').innerHTML = 'Username can contain only a-z, A-Z, and 0-9'
                }
                
                var password = get('signUpPassword').value
                get('signUpPassworde').innerHTML = ''
                if (! /^[\a-zA-Z0-9]*$/.test(password)) {
                    valid = false
                    get('signUpPassworde').innerHTML = 'Password can contain only a-z, A-Z, and 0-9'
                }
                
                var password = get('signUpPassword').value
                var passwordC = get('signUpPasswordConfirm').value
                if (password !== passwordC  ) {
                    valid = false
                    get('signUpPassworde').innerHTML = 'Password must match'
                }
                
                
                
                
                
                return valid;
            }
             
        function get(id) { return document.getElementById(id) }
        </script>

    </head>

    <body>
        <h1>JMU Book Time Machine</h1>
        <div class="container">
            <div class="row">
                <div align="center" class="col-md-6">
                    <form method="post" action="userControl?action=signup" class="form-signin" onsubmit="return validateForm()">
                        <h2 class="form-signin-heading">Sign Up!</h2>

                        <p>
                            <label for="firstname" class="sr-only">First Name</label>
                            <input type="text" name="firstname" id="firstname" class="form-control" placeholder="First Name" required autofocus onchange="this.value = this.value.trim();"/>
                            <span id="firstnamee" class="errmsg"> </span>
                        </p>
                        <p>
                            <label for="lastname" class="sr-only">Last Name</label>
                            <input type="text" name="lastname" id="lastname" class="form-control" placeholder="Last Name" required autofocus onchange="this.value = this.value.trim();">
                            <span id="lastnamee" class="errmsg"> </span>
                        </p>
                        <p>
                            <label for="email" class="sr-only">Email Address</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="Email Address" required autofocus onchange="this.value = this.value.trim();">
                            <span id="emaile" class="errmsg"> </span>
                        </p>
                        <p>
                            <label for="birthday" class="sr-only">Birthday</label>
                            <input type="text" name="birthday" id="birthday" class="form-control" placeholder="10-31-1994" required autofocus onchange="this.value = this.value.trim();">
                            <span id="birthdaye" class="errmsg"> </span>
                        </p>
                        <p>
                            <label for="signUpUsername" class="sr-only">User Name</label>
                            <input type="text" name="signUpUsername" id="signUpUsername" class="form-control" placeholder="User Name" required autofocus onchange="this.value = this.value.trim();">
                            <span id="signUpUsernamee" class="errmsg"> </span>                        
                        </p>
                        <p>
                            <label for="signUpPassword" class="sr-only">Password</label>
                            <input type="password" name="signUpPassword" id="signUpPassword" class="form-control" placeholder="Password" required onchange="this.value = this.value.trim();">
                            <span id="signUpPassworde" class="errmsg"> </span>                       
                        </p>
                        <p>
                            <label for="signUpPasswordConfirm" class="sr-only">Confirm Password</label>
                            <input type="password" name="signUpPasswordConfirm" id="signUpPasswordConfirm" class="form-control" placeholder="Repeat Password" required onchange="this.value = this.value.trim();">                      
                        </p>
                        </br>

                        <h3>Security Question</h3>
                        </br>
                        <p>
                            <label for="signUpSecurityAnswer" class="sr-only">Security Question</label>
                            <input type="text" name="signUpSecurityAnswer" id="signUpSecurityAnswer" class="form-control" placeholder="Mother's Maiden Name" required autofocus onchange="this.value = this.value.trim();">
                            <span id="signUpSecurityAnswere" class="errmsg"> </span>                       
                        </p>
                        <h3>Profile Picture</h3>
                        <label for="avatar" class="sr-only">Profile Picture</label>
                        </br>
                        <input type="file" name="avatar" id="avatar" style="color:white;">

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
