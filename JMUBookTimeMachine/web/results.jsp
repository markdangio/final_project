<%-- 
    Document   : results
    Created on : Dec 6, 2016, 5:47:28 PM
    Author     : dangiomr
--%>

<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            ArrayList<BookInfo> books = (ArrayList<BookInfo>)session.getAttribute("bookResults");
            Iterator<BookInfo> it = books.iterator();
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
                <%
                    while (it.hasNext()) {
                        BookInfo book = it.next();
                %>
                <div class="col-md-1"></div>
                <div class="col-md-2 book result">
                    <%
                        out.println("<div class=\"row\">Owner: username</div>");
                        out.println("<div class=\"row\">Send Message</div>");
                    %>
                </div>
                <div class="col-md-6 book result">
                    <%
                            out.println("<div class=\"row\">");
                            out.println("<div class=\"col-md-3\">" + book.getTitle() + "</div>");
                            out.println("<div class=\"col-md-3\">" + book.getAuthor() + "</div>");
                            out.println("<div class=\"col-md-3\">" + book.getEdition() + "</div>");
                            out.println("<div class=\"col-md-3\">" + book.getPublisher() + "</div>");
                            out.println("</div>");
                            out.println("<div class=\"row\">");
                            out.println("<div class=\"col-md-3\">" + "</div>");
                            out.println("<div class=\"col-md-6\">" + book.getPostedDate() + "</div>");
                            out.println("<div class=\"col-md-3\">$" + book.getPrice() + "</div>");
                            out.println("</div>");
                    %>
                </div>
                <div class="col-md-2 result">
                    <%
                        out.println("<div class=\"row\"><button class=\"btn btn-block\" name=\"reserve\" onClick=\"alert('I have been clicked!')\">Reserve</button></div>");
                    %>
                </div>
                <div class="col-md-1"></div>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>
