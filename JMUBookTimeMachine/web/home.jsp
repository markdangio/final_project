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
        <h1>JMU Book Time Machine Home Page</h1>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <p>Sell Book</p>
                </div>
                <div class="col-md-6">
                    <p>Buy Book</p>
                </div>
            </div>
        </div>
    </body>
</html>
