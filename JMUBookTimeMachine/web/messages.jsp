<%--
    Document   : messages
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
            ArrayList<User> messagesUsers = (ArrayList<User>) session.getAttribute("messagesUsers");
            Iterator<User> it = messagesUsers.iterator();
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
                        <div class="list-group">
                            <%
                                    while (it.hasNext()) {
                                        BookInfo book = it.next();
                                        User seller = (User) UserActions.getUser(book.getSellerId());

                                        out.println("<tr>");
                                        out.println("<td>" + seller.getFirstName() + " " + seller.getLastName() + "</td>");
                                        out.println("<td><form method=\"post\" action=\"messagesControl?action=create&toUserId=" + seller.getUserId() + 
                                                "\"><button class=\"btn btn-block\" name=\"message\" onClick=\"/messages.jsp?" + seller.getUserId() + "\">Message</button></form></td>");
                                        out.println("<td>" + book.getTitle() + "</td>");
                                        out.println("<td>" + book.getAuthor() + "</td>");
                                        out.println("<td>" + book.getEdition() + "</td>");
                                        out.println("<td>" + book.getPublisher() + "</td>");
                                        out.println("<td>" + book.getPostedDate() + "</td>");
                                        out.println("<td>$" + book.getPrice() + "</td>");
                                        if(book.getReserverId() == null){
                                            out.println("<td><form method=\"post\" action=\"bbc?action=reserve&saleId=" + book.getSaleId() + "&reserverId=" + u.getUserId() + "\"><button class=\"btn btn-block\" name=\"reserve\" type=\"submit\">Reserve</button></form></td>");
                                        }
                                        else {
                                           out.println("<td></td>"); 
                                        }
                                        out.println("</tr>");
                                    }
                                %>
                            <%
                                while (it.hasNext()) {
                                    User messagesUser = it.next();
                                    out.println("<a href=\"/messageControl?toUserId=" + messagesUser.getUserId() + "\" class=\"list-group-item\">");
                                    
                                    out.println("</a>");
                                }
                            %>
                            <a href="#" class="list-group-item"></a>
                        </div>
                        <!-- <form method="post" action="control?action=signup" class="form-signin"> -->
                        <div class="messages">
                            <h2 class="form-signin-heading">Messages</h2>
                            <label for="sellAuthor" class="sr-only">Message</label>
                            </br>
                            <input type = "text" name="message" id="message" class="form-control" placeholder="Message" required autofocus/> 
                            <button class="btn btn-block" name="addMsg" type="submit">Send Message</button>
                        </div>
                    </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
</html>
