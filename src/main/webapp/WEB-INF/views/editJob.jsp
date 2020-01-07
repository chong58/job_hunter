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
            <h3 class="panel-title">editJobInformation</h3>
        </div>
        <div class="panel-body">

            <form method="post" action="/updateJob" role="form">
                <table class="editTable">
                    <tr>
                        <td>companyName：</td>
                        <td><input type="text" name="companyName" id="companyName" value="${job.companyName}" placeholder="please input company name here">
                        </td>
                    </tr>
                    <tr>
                        <td>position：</td>
                        <td><input type="text" name="position" id="position" value="${job.position}" placeholder="please input position"></td>
                    </tr>
                    <tr>
                        <td>applicationDay：</td>
                        <td><input type="date" name="applicationDay" id="applicationDay" value="${job.applicationDay}"
                                   placeholder="please input application day"></td>
                    </tr>

                    <tr>
                        <td>applicationDay：</td>
                        <td><input type="date" name="applicationCloseDay" id="applicationCloseDay" value="${job.applicationCloseDay}"
                                   placeholder="please input application day"></td>
                    </tr>
                    <tr>
                        <td>status：</td>
                        <td><input type="text" name="status" id="status" value="${job.status}" placeholder="please input status"></td>
                    </tr>

                    <tr>
                        <td>jobLink：</td>
                        <td><input type="text" name="jobLink" id="jobLink" value="${job.jobLink}" placeholder="please input jobLink"></td>
                    </tr>


                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${job.id}">
                            <button type="submit" class="btn btn-success">Submit</button>
                        </td>

                    </tr>

                </table>
            </form>
        </div>
    </div>

</div>

</body>
</html>