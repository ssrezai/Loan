<%@ page import="logic.LoanLogic" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
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
        تشکیل پرونده تسهیلاتی
    </title>
</head>
<body>
<form dir="rtl">
    <table>
        <tr id="searchLabel">
            <td>
                شماره مشتری
            </td>
            <td>
                نام
            </td>
            <td>
                نام خانوادگی
            </td>
            <td>

            </td>
        </tr>
        <tr id="search">
            <td>
                <input type="text" name="customer_id">
            </td>
            <td>
                <input type="text" name="first_name" disabled>
            </td>
            <td>
                <input type="text" name="last_name" disabled>
            </td>
            <td>
                <input type="submit" name="submit" value="بازیابی">
            </td>
        </tr>

        <tr id="submitLabel">
            <td>
                نوع تسهیلات
            </td>
            <td>
                مدت قرارداد
            </td>
            <td>
                مبلغ قرارداد
            </td>
            <td>

            </td>
        </tr>

        <tr id="submit">
            <td>
                <%
                    List loanTypeNames = LoanLogic.returnLoanTypeName();
                    Iterator iterator = loanTypeNames.iterator();
                %>
                <select>
                    <% while (iterator.hasNext()) {%>
                    <option>
                        <%=iterator.next()%>
                        <% } %>
                    </option>
                </select>
                <%--<input type="" name="loan_type">--%>
            </td>
            <td>
                <input type="text" name="contract_cost">
            </td>
            <td>
                <input type="text" name="contract_duration">
            </td>
            <td>
                <input type="submit" name="submit" value="ثبت">
            </td>
        </tr>

    </table>
</form>

</body>
</html>
