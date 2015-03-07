<%@ page import="logic.LoanLogic" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="logic.LoanType" %>
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
<form dir="rtl" action="/LoanFileServlet" method="post">
    <table>
        <tr>
            <td>گام 1: انتخاب مشتری</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
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
                <input type="text" name="customer_id" value="<%=(String)request.getAttribute("id")%>">
            </td>
            <td>
                <%String name = (String) request.getAttribute("first_name");%>
                <input type="text" name="first_name" value="<%= (String) request.getAttribute("first_name")%>" disabled>
            </td>
            <td>

                <input type="text" name="last_name" value="<%= (String) request.getAttribute("last_name")%>" disabled>
            </td>
            <td>
                <input type="submit" name="search" value="بازیابی">
            </td>
            <% if (request.getAttribute("Invalid_error_msg") != null) {%>
            <script> alert("شماره مشتری وارد شده معتبر نیست " + "\nشماره مشتری فقط شامل رقم است.")
            </script>

            <%}%>
            <% if (request.getAttribute("noSuch_error_msg") != null) {%>
            <script> alert("شماره مشتری وارد شده در سیستم موجود نمیباشد.");
            </script>
            <% }%>
        </tr>
        <tr style="margin-top: 20px">
           <td >
               گام 2: انتخاب نوع تسهیلات
           </td>
            <td></td>
            <td></td>
            <td></td>
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
                    //                    List loanTypeNames = LoanLogic.returnLoanTypeName();
//                    Iterator iterator = loanTypeNames.iterator();
                    List loanTypes = LoanLogic.returnLoanType();
                    Iterator iterator = loanTypes.iterator();
                %>
                <select>
                    <% while (iterator.hasNext()) {%>
                    <option>
                        <%
                            LoanType loanType = (LoanType) iterator.next();
                            String str = loanType.getLoanTypeName() + "; " + loanType.getInterestRate() + "%";
                        %>
                        <%--<%=iterator.next()%>--%>
                        <%=str%>

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
                <% if (request.getAttribute("error") != null) {%>
                <script> alert("نمیشه...");
                </script>
                <%}%>
                <% if (request.getAttribute("yes") != null) {%>
                <script> alert(":دی  میشه");
                </script>
                <%}%>
            </td>
        </tr>

    </table>
</form>

</body>
</html>
