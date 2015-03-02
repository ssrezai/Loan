<%@ page import="java.util.Date" %>
<%! int day = 3; %>
<%--
  Created by IntelliJ IDEA.
  User: DOTIN SCHOOL 3
  Date: 3/1/2015
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
        عملیات تسهیلات بانکی
    </title>
</head>
<body>
<%
    switch(day) {
        case 0:
            out.println("It\'s Sunday.");
            break;
        case 1:
            out.println("It\'s Monday.");
            break;
        case 2:
            out.println("It\'s Tuesday.");
            break;
        case 3:
            out.println("It\'s Wednesday.");
            break;
        case 4:
            out.println("It\'s Thursday.");
            break;
        case 5:
            out.println("It\'s Friday.");
            break;
        default:
            out.println("It's Saturday.");
    }
%>
</body>
</html>
