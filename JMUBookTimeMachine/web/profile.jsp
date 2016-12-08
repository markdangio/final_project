<%-- 
    Document   : profile
    Created on : Nov 29, 2016, 2:39:15 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/profile.css">
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
        User u = (User)session.getAttribute("userObj");
        String username = (String)session.getAttribute("username"); //eventually not needed
        
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
                <div class="col-xs-4" id="profile_image">
                    <img class="profile_pic" id="propic" src="" alt="profile_image">
                    <p id="username">
                        <%
                            out.println(username);
                            //out.println(u.getUsername());
                        %>
                    </p>
                    <p id="email">
                    <%
                            //out.println(u.getEmail());
                    %>
                    </p>
                    <p id="name">
                        <%
                            //out.print(u.getFirstName()+ " ");
                            //out.println(u.getLastName());
                        %>
                    </p>
                </div>
                <div align="center" class="col-xs-4" id="books_selling">
                    <h2>Books Selling</h2>
                </div>
                <div align="center" class="col-xs-4" id="books_reserved">
                    <h2>Books Reserved</h2>
                </div>
            </div>
        </div>
    </body>
</html>
