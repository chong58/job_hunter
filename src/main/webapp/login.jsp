<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>loginPage</title>

    <link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>

<body>
<div id="login_frame">

    <p id="image_logo"><img src="images/winter.jpg"></p>

    <form method="post" action="/login">

        <p><label class="label_input">UserEmail</label><input type="text" name = "email" id="email" class="text_field"/></p>
        <p><label class="label_input">Password</label><input type="text" name = "password" id="password" class="text_field"/></p>
        <a id="register" href="register.jsp">register</a>

        <div id="login_control">
            <button type="submit" class="btn btn-default btn-info">login</button>
        </div>


    </form>
</div>

</body>
</html>
