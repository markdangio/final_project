<%-- 
    Document   : results
    Created on : Dec 6, 2016, 5:47:28 PM
    Author     : dangiomr
--%>

<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="stderror.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/results.css">
        <title>Results</title>
    </head>
    <body>
        <%
            // get a list of all Books searched for
            String userId = (String)session.getAttribute("userId");
            User u = UserActions.getUser(userId);
            ArrayList<BookInfo> books = (ArrayList<BookInfo>) session.getAttribute("bookResults");
            Iterator<BookInfo> it = books.iterator();
        %>

        <div class="container">
            <div class="row">
                <nav class="navbar navbar-default navbar-static-top">
                    <div class="container-fluid">
                        <ul class="nav navbar-nav">
                            <li><a id="bar" href="home.jsp"><i class="fa fa-book" style="font-size:24px;"> Home</i></a></li>
                            <li><a id="bar" href="messageControl?action=showMessages">Messages</a></li>
                            <li><a id="bar" href="profile.jsp">Profile</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a id="bar" href="logout">Logout</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="panel panel-default">
                        <div class=panel-heading>Results of Books For Sale</div>
                        <table class=table> 
                            <thead> 
                                <tr> 
                                    <th>Owner</th>
                                    <th></th>
                                    <th>Title</th> 
                                    <th>Author</th> 
                                    <th>Edition</th> 
                                    <th>Publisher</th>
                                    <th>Posted Date</th> 
                                    <th>Price</th> 
                                    <th>Reserved</th> 
                                </tr> 
                            </thead> 
                            <tbody>
                                <%
                                    while (it.hasNext()) {
                                        BookInfo book = it.next();
                                        User seller = (User) UserActions.getUser(book.getSellerId());

                                        out.println("<tr>");
                                        out.println("<td>" + seller.getFirstName() + " " + seller.getLastName() + "</td>");
                                        out.println("<td><form method=\"post\" action=\"messageControl?action=show&toUserId=" + seller.getUserId() + 
                                                "\"><button class=\"btn btn-block\" name=\"message\" type=\"submit\">Message</button></form></td>");
                                        out.println("<td><a href=\"/images/" + book.getCoverPhoto() + "\">" + book.getTitle() + "</a></td>");
                                        out.println("<td>" + book.getAuthor() + "</td>");
                                        out.println("<td>" + book.getEdition() + "</td>");
                                        out.println("<td>" + book.getPublisher() + "</td>");
                                        out.println("<td>" + book.getPostedDate() + "</td>");
                                        out.println("<td>$" + book.getPrice() + "</td>");
                                        if(book.getReserverId().equals("null")){
                                            out.println("<td><form method=\"post\" action=\"bbc?action=reserve&saleId=" + book.getSaleId() + "&reserverId=" + u.getUserId() + "\"><button class=\"btn btn-block\" name=\"reserve\" type=\"submit\">Reserve</button></form></td>");
                                        }
                                        else {
                                           out.println("<td></td>"); 
                                        }
                                        out.println("</tr>");
                                    }
                                %>
                            </tbody> 
                        </table> 
                    </div> 
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </body>
</html>
