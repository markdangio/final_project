<%-- 
    Document   : home
    Created on : Nov 30, 2016, 9:02:15 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JMU Book Time Machine Home Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="javascript/home.js"></script>
        <link rel="stylesheet" href="css/home.css">
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
            var sellClassName = get('sellClassName').value
            get('sellClassNamee').innerHTML = ''
            if (! /^[\a-zA-Z0-9 ]*$/.test(sellClassName)) {
            valid = false
            get('sellClassNamee').innerHTML = "<span style='color: red;'>Class name can contain only a-z, A-Z, 0-9 and a space. </span>"
            }

            var sellClassSubject = get('sellClassSubject').value
            get('sellClassSubjecte').innerHTML = ''
            if (! /^[\a-zA-Z]*$/.test(sellClassSubject)) {
            valid = false
            get('sellClassSubjecte').innerHTML = "<span style='color: red;'>Class subject can contain only a-z, A-Z </span>"
            }

            var email = get('email').value
            get('emaile').innerHTML = ''
            if (! /^[\a-zA-Z0-9.@]*$/.test(email)) {
            valid = false
            get('emaile').innerHTML = "<span style='color: red;'>Email can contain only a-z, A-Z, ., and @ </span>"
            }

            var birthday = get('birthday').value
            get('birthdaye').innerHTML = ''
            if (! /^[\-0-9]*$/.test(birthday)) {
            valid = false
            get('birthdaye').innerHTML = "<span style='color: red;'>Birthday can contain only 0-9 and hyphen</span>"
            }

            var username = get('signUpUsername').value
            get('signUpUsernamee').innerHTML = ''
            if (! /^[\a-zA-Z0-9]*$/.test(username)) {
            valid = false
            get('signUpUsernamee').innerHTML = "<span style='color: red;'>Username can contain only a-z, A-Z, and 0-9 </span>"
            }

            var password = get('signUpPassword').value
            get('signUpPassworde').innerHTML = ''
            if (! /^[\a-zA-Z0-9]*$/.test(password)) {
            valid = false
            get('signUpPassworde').innerHTML = "<span style='color: red;'>Password can contain only a-z, A-Z, and 0-9</span>"
            }

            var password = get('signUpPassword').value
            var passwordC = get('signUpPasswordConfirm').value
            if (password !== passwordC  ) {
            valid = false
            get('signUpPassworde').innerHTML = "<span style='color: red;'>Password must match!</span>"
            }

            var security = get('signUpSecurityAnswer').value
            get('signUpSecurityAnswere').innerHTML = ''
            if (! /^[\a-zA-Z ]*$/.test(security)) {
            valid = false
            get('signUpSecurityAnswere').innerHTML ="<span style='color: red;'>Security Answer can contain only a-z, A-Z, and a space</span>";
            }                

            return valid;
            }

            function get(id) { return document.getElementById(id) }
        </script>

    </head>
    <%

        boolean loggedIn;

        if (session.getAttribute("loggedIn") == null) {
            loggedIn = false;
        } else {
            loggedIn = (boolean) session.getAttribute("loggedIn");
        }
        if (!loggedIn) {
    %>
    <jsp:forward page="index.jsp" />
    <%
        }
    %>

    <body>
        <div class="container">
            <div class="row">
                <nav class="navbar navbar-default navbar-static-top">
                    <div class="container-fluid">
                        <ul class="nav navbar-nav">
                            <li><a id="bar" href="home.jsp"><i class="fa fa-book" style="font-size:24px;"></i></a></li>
                            <li><a id="bar" href="messages.jsp">Messages</a></li>
                            <li><a id="bar" href="profile.jsp">Profile</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a id="bar" href="logout">Logout</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="row">
                <div align="center" class="col-md-6">
                    <h2 class="form-signin-heading">Sell a Book</h2>
                    <form method="post" action="bbc?action=check" class="form-signin">
                        <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <h2 class="form-signin-heading">First, add class info</h2>
                                    <label for="sellClassName" class="sr-only">Class Name</label>
                                    <input type="text" name="sellClassName" id="sellClassName" class="form-control" placeholder="Class Name" required autofocus>
                                    <span id="sellClassNamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellClassSubject" class="sr-only">Class Subject</label>
                                    <input type="text" name="sellClassSubject" id="sellClassSubject" class="form-control" placeholder="Class Subject" required autofocus>
                                    <span id="sellClassSubjecte" class="errmsg"> </span>
                                    </br>
                                    <label for="sellClassNumber" class="sr-only">Class Number</label>
                                    <input type="number" name="sellClassNumber" id="sellClassNumber" class="form-control" placeholder="Class Number" required autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellClassSection" class="sr-only">Class Section</label>
                                    <input type="number" name="sellClassSection" id="sellClassSection" class="form-control" placeholder="Class Section" required autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellClassProfessor" class="sr-only">Class Professor</label>
                                    <input type="text" name="sellClassProfessor" id="sellClassProfessor" class="form-control" placeholder="Class Professor" required autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellClassDescription" class="sr-only">Class Description</label>
                                    <input type="text" name="sellClassDescription" id="sellClassDescription" class="form-control" placeholder="Class Description" autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <button class="btn btn-block" name="next" onclick="moveForwardClass();">Next</button>
                                    </form>
                                </div>
                                <div class="item">
                                    <h2 class="form-signin-heading">Add book info</h2>
                                    <label for="sellTitle" class="sr-only">Title</label>
                                    <input type="text" name="sellTitle" id="sellTitle" class="form-control" placeholder="Title" required autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellAuthor" class="sr-only">Author</label>
                                    <input type="text" name="sellAuthor" id="sellAuthor" class="form-control" placeholder="Author" required autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellEdition" class="sr-only">Edition</label>
                                    <input type="number" name="sellEdition" id="sellEdition" class="form-control" placeholder="Edition" required autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellPublisher" class="sr-only">Publisher</label>
                                    <input type="text" name="sellPublisher" id="sellPublisher" class="form-control" placeholder="Publisher" required autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <label for="sellCoverPhoto" class="sr-only">Book Cover Photo</label>
                                    <input type="file" name="sellCoverPhoto" id="sellCoverPhoto" autofocus>
                                    <span id="lastnamee" class="errmsg"> </span>
                                    </br>
                                    <button class="btn btn-block" name="back"onclick="moveBackward();">Back</button>
                                    </br>
                                    <button class="btn btn-block" name="next"onclick="moveForwardInfo();">Next</button>
                                </div>
                                <div class="item">
                                    <h2 class="form-signin-heading">Add price and submit!</h2>
                                    <label for="sellPrice" class="sr-only">Price</label>
                                    <input type="number" name="sellPrice" id="sellPrice" class="form-control" placeholder="Price" required autofocus>
                                    </br>
                                    <button class="btn btn-block" name="back" onclick="moveBackward();">Back</button>
                                    </br>
                                    <button class="btn btn-block" name="postbook" type="submit">Post Book</button>
                                    </br>
                                </div>
                            </div>
                    </form>
                </div>
            </div>
            <div align="center" class="col-md-6">
                <form method="post" action="bbc?action=search" class="form-signin">
                    <div class="item">
                        <h2 class="form-signin-heading">Find Book</h2>
                        <label for="title" class="sr-only">Title</label>
                        <input type="text" name="title" id="title" class="form-control" placeholder="Title" autofocus>
                        <span id="lastnamee" class="errmsg"> </span>
                        </br>
                        <label for="author" class="sr-only">Author</label>
                        <input type="text" name="author" id="author" class="form-control" placeholder="Author" autofocus>
                        <span id="lastnamee" class="errmsg"> </span>
                        </br>
                        <label for="edition" class="sr-only">Edition</label>
                        <input type="number" name="edition" id="edition" class="form-control" placeholder="Edition" autofocus>
                        <span id="lastnamee" class="errmsg"> </span>
                        </br>
                        <label for="publisher" class="sr-only">Publisher</label>
                        <input type="text" name="publisher" id="publisher" class="form-control" placeholder="Publisher" autofocus>
                        <span id="lastnamee" class="errmsg"> </span>
                        </br>
                        <button class="btn btn-block" name="bookSearch" type="submit">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
