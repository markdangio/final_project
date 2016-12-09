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

        String userId = (String) session.getAttribute("userId");
        User u = UserActions.getUser(userId);

        ArrayList<BookInfo> books = Books_For_SaleActions.searchBook_For_SaleSelling(userId);
        Iterator<BookInfo> it = books.iterator();

        ArrayList<BookInfo> books2 = Books_For_SaleActions.searchBook_For_SaleReserved(userId);
        Iterator<BookInfo> it2 = books2.iterator();
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
                <div class="wrapper">
                    <div class="col-md-4">
                        <ul class="list-group">
                            <li class="list-group-item" id="profile_image"><img class="profile_pic" id="propic" src="" alt="profile_image"></li>
                            <li class="list-group-item">
                                Name: 
                                <%
                                    out.print(u.getFirstName() + " " + u.getLastName());
                                %>
                            </li>
                            <li class="list-group-item">
                                Username: 
                                <%
                                    out.println(u.getUsername());
                                %>
                            </li>
                            <li class="list-group-item">
                                Email: 
                                <%
                                    out.println(u.getEmail());
                                %>
                            </li>
                            <li class="list-group-item">
                                Birthday 
                                <%
                                    out.print(u.getBirthday());
                                %>
                            </li>
                        </ul>
                    </div>
                </div>
                <div align="center" class="col-md-4" id="books_selling">
                    <div class="panel panel-default">
                        <div class=panel-heading>Books Selling</div>
                        <table class=table> 
                            <thead> 
                                <tr> 
                                    <th>Title</th> 
                                    <th>Author</th> 
                                    <th>Edition</th> 
                                    <th>Sold</th>
                                    <th>Reserved By</th>
                                </tr> 
                            </thead> 
                            <tbody>
                                <%
                                    while (it.hasNext()) {
                                        BookInfo book = it.next();
                                        User reserver = (User) UserActions.getUser(book.getSellerId());

                                        out.println("<tr>");
                                        out.println("<td>" + book.getTitle() + "</td>");
                                        out.println("<td>" + book.getAuthor() + "</td>");
                                        out.println("<td>" + book.getEdition() + "</td>");
                                        out.println("<td>$" + book.getSold() + "</td>");
                                        if (book.getReserverId() != null) {
                                            out.println("<td><a href=\"messagesControl?action=create&toUserId=" + reserver.getUserId() + "\">" + reserver.getFirstName() + " " + reserver.getLastName() + "</a></td>");
                                        } else {
                                            out.println("<td></td>");
                                        }
                                        out.println("</tr>");
                                    }
                                %>
                            </tbody> 
                        </table> 
                    </div>
                </div>
                <div align="center" class="col-md-4" id="books_reserved">
                    <div class="panel panel-default">
                        <div class=panel-heading>Books Reserved</div>
                        <table class=table> 
                            <thead> 
                                <tr>
                                    <th>Owner</th>
                                    <th>Title</th> 
                                    <th>Author</th> 
                                    <th>Edition</th> 
                                    <th>Price</th> 
                                </tr> 
                            </thead> 
                            <tbody>
                                <%
                                    while (it2.hasNext()) {
                                        BookInfo book2 = it2.next();
                                        User seller = (User) UserActions.getUser(book2.getSellerId());

                                        out.println("<tr>");
                                        out.println("<td><a href=\"messagesControl?action=create&toUserId=" + seller.getUserId() + "\">" + seller.getFirstName() + " " + seller.getLastName() + "</a></td>");
                                        out.println("<td>" + book2.getTitle() + "</td>");
                                        out.println("<td>" + book2.getAuthor() + "</td>");
                                        out.println("<td>" + book2.getEdition() + "</td>");
                                        out.println("<td>$" + book2.getPrice() + "</td>");
                                        out.println("</tr>");
                                    }
                                %>
                            </tbody> 
                        </table> 
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
