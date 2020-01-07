<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <%-- 引入JQ和Bootstrap --%>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="css/style.css" rel="stylesheet">

    <title>Job-Hunter - editPage</title>
</head>

<body>

<div class="editDIV">

    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">RegisterPersonalInformation</h3>
        </div>
        <div class="panel-body">

            <form method="post" action="/register" role="form">
                <table class="editTable">
                    <tr>
                        <td>name：</td>
                        <td><input type="text" name="name" id="name" value="${user.name}" placeholder="please input name here">
                        </td>
                    </tr>
                    <tr>
                        <td>email：</td>
                        <td><input type="text" name="email" id="email" value="${user.position}" placeholder="please input email"></td>
                    </tr>
                    <tr>
                        <td>password：</td>
                        <td><input type="text" name="password" id="password" value="${user.password}" placeholder="please input password"></td>
                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">Register</button>
                        </td>

                    </tr>

                </table>
            </form>
        </div>
    </div>

</div>

</body>
</html>