<%--
  Created by IntelliJ IDEA.
  User: myeong
  Date: 2018. 2. 15.
  Time: PM 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String context = request.getContextPath();
    %>
    <form action="<%=context%>/student/studentview" method="post">
        이름 : <input type="text" name="name"><br />
        나이 : <input type="age" name="age"><br />
        학년 : <input type="classNum" name="classNum"><br />
        반 : <input type="gradeNum" name="gradeNum"><br />
        <input type="submit" value="전송">
    </form>

</body>
</html>
