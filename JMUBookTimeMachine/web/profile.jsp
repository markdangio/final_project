<%-- 
    Document   : profile
    Created on : Nov 29, 2016, 2:39:15 PM
    Author     : Mitch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <%/*
        boolean loggedIn;
        
        if(session.getAttribute("loggedIn") == null){
            loggedIn = false;
        }
        else{
            loggedIn = (boolean) session.getAttribute("loggedIn");
        }
        if(!loggedIn)
        {*/
            %>
            <!--jsp:forward page="index.html" />-->
            <%
        //}
    %>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-6" id="profile_image">
                    <img class="profile_pic" id="propic" src="" alt="profile_image">
                    <p id="name"></p>
                    <p id="email"></p>
                    <p id="age"></p>
                    <p id="sex"></p>
                    <p id="grade"></p>
                </div>
                <div class="col-xs-6" id="books_selling">
                    
                </div>
            </div>
        </div>
    </body>
</html>
