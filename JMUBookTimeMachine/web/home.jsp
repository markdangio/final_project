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
            <jsp:forward page="index.jsp" />
            <%
        }
    %>

    <body>
        <div class="container">
            <div class="row">
                <nav class="navbar navbar-default">
                  <div class="container-fluid">
                    <ul class="nav navbar-nav">
                      <li><a href="home.jsp" alt="Home Page"><i class="fa fa-book" style="font-size:24px;"></i></a></li>
                      <li><a href="messages.jsp">Messages</a></li>
                      <li><a href="profile.jsp">Profile</a></li> 
                      <li><a href="notifications.jsp">Notifications</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                      <li><a href="logout">Logout</a></li>
                    </ul>
                  </div>
                </nav>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <form method="post" action="control?action=signup" class="form-signin">
                        <h2 class="form-signin-heading">Sell Book</h2>
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
                        <button class="btn btn-lg btn-primary btn-block" name="adduser" type="submit">Sign up</button>
                    </form>
                </div>
                <div class="col-md-6">
                    <form method="post" action="control?action=login" class="form-signin">
                        <h2 class="form-signin-heading">Find Book</h2>
                        <label for="bookTitle" class="sr-only">Title</label>
                        <input type="text" name="bookTitle" id="bookTitle" class="form-control" placeholder="Title" required autofocus>
                        </br>
                        <button class="btn btn-lg btn-primary btn-block" name="bookSearch" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
