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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="javascript/home.js"></script>
        <link rel="stylesheet" href="css/home.css">
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
                <div align="center" class="col-md-6">
                    <h2 class="form-signin-heading">Sell a Book</h2>
                    <form method="post" action="bbc?action=check" class="form-signin">
                        <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <h2 class="form-signin-heading">First, add class info</h2>
                                    <label for="sellClassName" class="sr-only">Class Name</label>
                                    <input type="text" name="sellClassName" id="sellClassName" class="form-control" placeholder="Class Name" required autofocus>
                                    </br>
                                    <label for="sellClassSubject" class="sr-only">Class Subject</label>
                                    <input type="text" name="sellClassSubject" id="sellClassSubject" class="form-control" placeholder="Class Subject" required autofocus>
                                    </br>
                                    <label for="sellClassNumber" class="sr-only">Class Number</label>
                                    <input type="number" name="sellClassNumber" id="sellClassNumber" class="form-control" placeholder="Class Number" required autofocus>
                                    </br>
                                    <label for="sellClassSection" class="sr-only">Class Section</label>
                                    <input type="number" name="sellClassSection" id="sellClassSection" class="form-control" placeholder="Class Section" required autofocus>
                                    </br>
                                    <label for="sellClassProfessor" class="sr-only">Class Professor</label>
                                    <input type="text" name="sellClassProfessor" id="sellClassProfessor" class="form-control" placeholder="Class Professor" required autofocus>
                                    </br>
                                    <label for="sellClassDescription" class="sr-only">Class Description</label>
                                    <input type="text" name="sellClassDescription" id="sellClassDescription" class="form-control" placeholder="Class Description" autofocus>
                                    </br>
                                    <button class="btn btn-block" name="next" onclick="moveForwardClass();">Next</button>
                                    </form>
                                </div>
                                <div class="item">
                                    <h2 class="form-signin-heading">Add book info</h2>
                                    <label for="sellTitle" class="sr-only">Title</label>
                                    <input type="text" name="sellTitle" id="sellTitle" class="form-control" placeholder="Title" required autofocus>
                                    </br>
                                    <label for="sellAuthor" class="sr-only">Author</label>
                                    <input type="text" name="sellAuthor" id="sellAuthor" class="form-control" placeholder="Author" required autofocus>
                                    </br>
                                    <label for="sellEdition" class="sr-only">Edition</label>
                                    <input type="number" name="sellEdition" id="sellEdition" class="form-control" placeholder="Edition" required autofocus>
                                    </br>
                                    <label for="sellPublisher" class="sr-only">Publisher</label>
                                    <input type="text" name="sellPublisher" id="sellPublisher" class="form-control" placeholder="Publisher" required autofocus>
                                    </br>
                                    <label for="sellCoverPhoto" class="sr-only">Book Cover Photo</label>
                                    <input type="file" name="sellCoverPhoto" id="sellCoverPhoto" autofocus>
                                    </br>
                                    <button class="btn btn-block" name="back"onclick="moveBackward();">Back</button>
                                    </br>
                                    <button class="btn btn-block" name="next"onclick="moveForwardInfo();">Next</button>
                                </div>
                                <div class="item">
                                    <h2 class="form-signin-heading">Add price and submit!</h2>
                                    <label for="sellPrice" class="sr-only">Price</label>
                                    <input type="number" name="sellPrice" id="sellPrice" class="form-control" placeholder="Price" required autofocus>
                                    </br>
                                    <button class="btn btn-block" name="back" onclick="moveBackward();">Back</button>
                                    </br>
                                    <button class="btn btn-block" name="postbook" type="submit">Post Book</button>
                                    </br>
                                </div>
                            </div>
                    </form>
                </div>
            </div>
            <div align="center" class="col-md-6">
                <form method="post" action="bbc?action=search" class="form-signin">
                    <div class="item">
                        <h2 class="form-signin-heading">Find Book</h2>
                        <label for="title" class="sr-only">Title</label>
                        <input type="text" name="title" id="title" class="form-control" placeholder="Title" autofocus>
                        </br>
                        <label for="author" class="sr-only">Author</label>
                        <input type="text" name="author" id="author" class="form-control" placeholder="Author" autofocus>
                        </br>
                        <label for="edition" class="sr-only">Edition</label>
                        <input type="number" name="edition" id="edition" class="form-control" placeholder="Edition" autofocus>
                        </br>
                        <label for="publisher" class="sr-only">Publisher</label>
                        <input type="text" name="publisher" id="publisher" class="form-control" placeholder="Publisher" autofocus>
                        </br>
                        <button class="btn btn-block" name="bookSearch" type="submit">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
