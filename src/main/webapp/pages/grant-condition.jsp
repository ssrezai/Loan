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
        عملیات تسهیلات بانکی_شروط اعطا
    </title>
</head>
<body>

<div dir="rtl" style="margin-top: 150px">

    <form action="/LoanTypeServlet" method="post">

        <input type="hidden" value="<%=request.getParameter("loan_type_name")%>" name="loan_type_name">
        <input type="hidden" value="<%=request.getParameter("interest_rate")%>" name="interest_rate">

        <table>
            <label for="name">
            </label>
            <tr>
                <td>
                    نام
                </td>
                <td style="margin-left: 10px">

                </td>
                <td>
                    <input type="text" name="name" id="name">
                </td>
            </tr>
            <tr>
                <td>
                    حداقل مدت قرارداد
                </td>
                <td>

                </td>
                <td>
                    <input type="text" name="min_contract_duration" id="min_contract_duration">
                </td>
                <td>
                    ماه
                </td>
            </tr>
            <tr>
                <td>
                    حداکثر مدت قرارداد
                </td>
                <td>

                </td>
                <td>
                    <input type="text" name="max_contract_duration" id="max_contract_duration">
                </td>
                <td>
                    ماه
                </td>
            </tr>
            <tr>
                <td>
                    حداقل مبلغ قرارداد
                </td>
                <td>

                </td>
                <td>
                    <input type="text" name="min_contract_cost" id="min_contract_cost">
                </td>
                <td>
                    ریال
                </td>
            </tr>
            <tr>
                <td>
                    حداکثر مبلغ قرارداد
                </td>
                <td>

                </td>
                <td>
                    <input type="text" name="max_contract_cost" id="max_contract_cost">
                </td>
                <td>
                    ریال
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="submit" value=" ثبت">
                </td>
            </tr>

        </table>

    </form>
</div>
</body>
</html>
