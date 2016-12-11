<%-- 
    Document   : recovery
    Created on : Nov 29, 2016, 2:39:15 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="stderror.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/recovery.css">
        <title>Recovery</title>
        
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
            var username = get('username').value
            get('usernamee').innerHTML = ''
            if (! /^[\a-zA-Z0-9]*$/.test(username)) {
            valid = false
            get('usernamee').innerHTML = "<span style='color: red;'>Username can contain only a-z, A-Z, and 0-9 </span>"
            }

            var sq = get('sq').value
            get('sqe').innerHTML = ''
            if (! /^[\a-zA-Z ]*$/.test(sq)) {
            valid = false
            get('sqe').innerHTML = "<span style='color: red;'>Security Question can contain only a-z, A-Z and spaces </span>"
            }

            var password = get('password').value
            get('passworde').innerHTML = ''
            if (! /^[\a-zA-Z0-9]*$/.test(password)) {
            valid = false
            get('passworde').innerHTML = "<span style='color: red;'> Password can contain only a-z, A-Z, and spaces</span>"
            }
            
            var password = get('password').value
            var passwordC = get('confirmPassword').value
            if (password !== passwordC  ) {
                valid = false
                get('passworde').innerHTML = "<span style='color: red;'>Password must match!</span>"
            }

            return valid;
            }

        function get(id) { return document.getElementById(id) }
        </script>
    </head>
    <%
        String securityMessage = (String)session.getAttribute("securityMessage");
        String changeMessage = (String)session.getAttribute("changeMessage");
    %>
    <body>
        <div align="center" class="container">
            <h1>JMU Book Time Machine</h1>
            <div id="wrapper">
                <form method="post" action="userControl?action=checkUpdatePass" class="form-signin" onsubmit="return validateForm();">
                    <h2 class="form-signin-heading">Password Recovery</h2>
                    <label for="loginUsername" class="sr-only">Username</label>
                    <input type="text" name="loginUsername" id="username" class="form-control" placeholder="Username" required autofocus onchange="this.value = this.value.trim();">
                    <span id="usernamee" class="errmsg"> </span>
                    </br>
                    <label for="recoverySQ" class="sr-only">Security Question</label>
                    <input type="password" name="recoverySQ" id="sq" class="form-control" placeholder="Security Question Answer" required onchange="this.value = this.value.trim();">
                    <span id="sqe" class="errmsg"> </span>
                    </br>
                    </br>
                    <h2 class="form-signin-heading">Change Password</h2>
                    <label for="loginPassword" class="sr-only">New Password</label>
                    <input type="password" name="loginPassword" id="password" class="form-control" placeholder="New Password" required autofocus onchange="this.value = this.value.trim();">
                    <span id="passworde" class="errmsg"> </span>
                    </br>
                    <label for="newPasswordConfirm" class="sr-only">Confirm New Password</label>
                    <input type="password" name="newPasswordConfirm" id="confirmPassword" class="form-control" placeholder="Confirm New Password" required onchange="this.value = this.value.trim();">
                    </br>
                    <button class="btn btn-block" name="recover" type="submit" >Change Password</button>
                    </br>
                    <%
                        if(securityMessage != null){out.println("<div class=\"wrapper\">" + securityMessage + "</div></br>");}
                        if(changeMessage != null){out.println("<div class=\"wrapper\">" + changeMessage + "</div></br>");}
                    %>
                </form>
            </div>
        </div>
    </body>
</html>
