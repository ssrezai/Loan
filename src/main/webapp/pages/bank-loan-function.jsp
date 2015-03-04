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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../style/customer-management-style.css">
    <title>
        عملیات تسهیلات بانکی
    </title>
</head>
<body>
<div dir="rtl" style="margin-top: 150px">
    <form>
        <table>
            <label for="loan_type_name">
            </label>
            <tr>
                <td>
نام نوع تسهیلات
                </td>
                <td style="margin-left: 10px">

                </td>
                <td>
                    <input type="text" name="loan_type_name" id="loan_type_name">
                </td>
            </tr>
            <tr>
                <td>
نرخ سود
                </td>
                <td>

                </td>
                <td>
                    <input type="text" name="interest_rate" id="interest_rate">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="مرحله بعد">
                </td>
            </tr>

        </table>

    </form>
</div>
</body>
</html>
