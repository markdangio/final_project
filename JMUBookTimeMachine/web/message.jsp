<%--
    Document   : message
    Created on : Dec 1, 2016, 3:39:40 PM
    Author     : massarmh
--%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messages</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="javascript/home.js"></script>
        <link rel="stylesheet" href="css/messages.css">
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
        <%
            // get a list of all Books searched for
            String userId = (String)session.getAttribute("userId");
            User u = UserActions.getUser(userId);
            ArrayList<Message> messages = (ArrayList<Message>) session.getAttribute("messages");
            Iterator<Message> it = messages.iterator();
        %>
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
                <div class="col-md-2"></div>
                
                <div align="center" class="col-md-8">
                    <h2 class="form-signin-heading">Messages</h2>
                    <div class="row messages" id="wrapper">
                        <%
                            out.println("<p>HI</p>");
                            while (it.hasNext()) {
                                Message message = it.next();
                                if(message.getFromUserId().equals(userId)){
                                    out.println("<p class=\"right\">" + message.getContent() + "</p>");
                                }
                                else{
                                    out.println("<p class=\"left\">" + message.getContent() + "</p>");
                                }
                                out.println("</br>");
                            }
                        %>
                    </div>
                    <form method="post" action="messageControl?action=create" class="form-signin">
                        <input type = "text" name="content" id="message" class="form-control" placeholder="Message" required autofocus/> 
                        <button class="btn btn-block" name="addMsg" type="submit">Send Message</button>
                    </form>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
</html>
