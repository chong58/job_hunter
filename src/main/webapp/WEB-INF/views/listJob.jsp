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

<table>
<form action="/searchJob">
<input type="text" value="Google" name="companyName">
<input type="submit" name="Search" value="Search">
</form>
</table>

<%String id = request.getSession().getAttribute("userId").toString();%>
Hello ，<%=id %>

<table>
<form action="/searchJobNumber">
<button type="submit" class="btn btn-success">showGraph</button>
</form>
</table>

<table>
<form action="/startEmailService">
<button type="submit" class="btn btn-success">startEmailService</button>
</form>
</table>

<table>
<form action="/stopEmailService">
<button type="submit" class="btn btn-success">stopEmailService</button>
</form>
</table>

<input id="userId" name="userId" type="hidden" value="8">
<div class="listDIV">

    <table class="table table-striped table-bordered table-hover table-condensed">

        <caption>JobList - totalNumber-${page.total}</caption>
        <thead>
        <tr class="success">
            <th>companyName</th>
            <th>position</th>
            <th>applicationDay</th>
            <th>applicationCloseDay</th>
            <th>status</th>
            <th>jobLink</th>

            <th>edit</th>
            <th>delete</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${jobs}" var="s" varStatus="status">
            <tr>
                <td>${s.companyName}</td>
                <td>${s.position}</td>
                <td>${s.applicationDay}</td>
                <td>${s.applicationCloseDay}</td>
                <td>${s.status}</td>
                <td>${s.jobLink}</td>

                <td><a href="/editJob?id=${s.id}"><span class="glyphicon glyphicon-edit"></span> </a></td>
                <td><a href="/deleteJob?id=${s.id}"><span class="glyphicon glyphicon-trash"></span> </a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>


</div>

<nav class="pageDIV">
    <ul class="pagination">
        <li <c:if test="${!page.hasPrevious}">class="disabled"</c:if>>
            <a href="?page.start=0">
                <span>«</span>
            </a>
        </li>

        <li <c:if test="${!page.hasPrevious}">class="disabled"</c:if>>
            <a href="?page.start=${page.start-page.count}">
                <span>‹</span>
            </a>
        </li>

        <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">

            <c:if test="${status.count*page.count-page.start<=30 && status.count*page.count-page.start>=-10}">
                <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                    <a
                            href="?page.start=${status.index*page.count}"
                            <c:if test="${status.index*page.count==page.start}">class="current"</c:if>
                    >${status.count}</a>
                </li>
            </c:if>
        </c:forEach>

        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="?page.start=${page.start+page.count}">
                <span>›</span>
            </a>
        </li>
        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="?page.start=${page.last}">
                <span>»</span>
            </a>
        </li>
    </ul>
</nav>

<div class="addDIV">

    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">addJob</h3>
        </div>
        <div class="panel-body">

            <form method="post" action="/addJob" role="form">
                <table class="addTable">
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
                        <td>applicationCloseDay：</td>
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
                    <tr>
                        <td>userId：</td>
                        <td><input type="text" name="userId" id="userId" value="${job.userId}" placeholder="please input userId"></td>
                    </tr>


                    <tr class="submitTR">
                        <td colspan="2" align="center">
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