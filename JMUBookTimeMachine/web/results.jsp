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
            ArrayList<Books> books = (ArrayList<Books>)session.getAttribute("bookResults");
            Iterator<Books> it = books.iterator();
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
                <table border="1">
                    <tbody>
                        <tr><th>Book Id</th><th>Title</th><th>Author</th>
                            <th>Edition</th><th>Publisher</th><th>Cover Photo</th><th>Class Id</th></tr>

                        <%
                            while (it.hasNext()) {
                                Books book = it.next();
                                out.println("<tr>");
                                out.println("<td>" + book.getBookId() + "</td>");
                                out.println("<td>" + book.getTitle() + "</td>");
                                out.println("<td>" + book.getAuthor() + "</td>");
                                out.println("<td>" + book.getEdition() + "</td>");
                                out.println("<td>" + book.getPublisher() + "</td>");
                                out.println("<td>" + book.getCoverPhoto() + "</td>");
                                out.println("<td>" + book.getClassId() + "</td>");
                                out.println("</tr>");
                            }
                        %>

                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
