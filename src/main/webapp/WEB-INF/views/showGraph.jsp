<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

    <%-- 引入JQ和Bootstrap --%>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="css/style.css" rel="stylesheet">

    <title>JobHunter - FrontPage</title>

    <script>
        $(function () {
            $("ul.pagination li.disabled a").click(function () {
                return false;
            });
        });
    </script>
</head>

<body>

<div class="listDIV">

    <form method="post" action="/calculateEverydayJobNumber">

        <p><label class="label_input">startDay</label><input type="date" name = "startDay" id="startDay" /></p>
        <p><label class="label_input">endDay</label><input type="date" name = "endDay" id="endDay" /></p>

        <div id="login_control">
            <button type="submit" class="btn btn-default btn-info">Submit</button>
        </div>


    </form>

    <table class="table table-striped table-bordered table-hover table-condensed">

        <tbody>
            <tr>
                <td>${image}</td>
            </tr>

        </tbody>
    </table>


</div>

</body>
</html>