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
    <script>
        function addToTable()
        {
            var name=document.form["grantCondition"]["name"].value;
            var minCost=document.form["grantCondition"]["min_contract_cost"].value;
            var maxCost=document.form["grantCondition"]["max_contract_cost"].value;
            var minDuration=document.form["grantCondition"]["min_contract_duration"].value;
            var maxDuration=document.form["grantCondition"]["max_contract_duration"].value;
            var table= document.getElementById('grantConditionTable');
            var header = table.createTHead();
            var row = header.insertRow(0);

            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);
            var cell2 = row.insertCell(2);
            var cell3 = row.insertCell(3);
            var cell4 = row.insertCell(4);


            cell.innerHTML = document.getElementById('name').value;
            cell.innerHTML = document.getElementById('min_contract_cost').value;
            cell.innerHTML = document.getElementById('max_contract_cost').value;
            cell.innerHTML = document.getElementById('min_contract_duration').value;
            cell.innerHTML = document.getElementById('max_contract_duration').value;
        }
    </script>

</head>
<body>

<div dir="rtl" style="margin-top: 150px">

    <form name="grantCondition" id="grantCondition" action="/LoanTypeServlet" method="post">

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
                   <input type="button"  onclick="addToTable()" value="اضافه شده به شروط اعطا">
                    <%--<input type="submit" name="submit" value=" ثبت">--%>
                </td>
            </tr>

        </table>

        <input type="submit" name="final_submit" value=" ثبت نهایی">

    </form>
</div>
<div dir="rtl">
    <table id="grantConditionTable" border="1" style="width:100%">
        <th>
            نام
        </th>
        <th>
            حداقل مدت قرارداد
        </th>
        <th>
            حداکثر مدت قرارداد
        </th>
        <th>
            حداقل مبلغ قرارداد
        </th>
        <th>
            حداکثر مبلغ قرارداد
        </th>
        <tr>
            <td id="t_name"></td>
            <td id="t_minDuration"></td>
            <td id="t_maxDuration"></td>
            <td id="t_minCost"></td>
            <td id="t_maxCost"></td>
        </tr>
        <%--<%@ page import="java.util.Iterator" %>--%>
        <%--<%@ page import="logic.GrantCondition" %>--%>
        <%--<% Iterator iterator = (Iterator) session.getAttribute("conditions");--%>
            <%--while (iterator.hasNext()) {--%>
                <%--GrantCondition grantCondition = (GrantCondition) iterator.next();%>--%>
        <%--<tr>--%>
            <%--<td><%=grantCondition.getGrantConditionName()%>--%>
            <%--</td>--%>
            <%--<td><%=grantCondition.getMinContractDuration()%>--%>
            <%--</td>--%>
            <%--<td><%=grantCondition.getMaxContractDuration()%>--%>
            <%--</td>--%>
            <%--<td><%=grantCondition.getMinContractCost()%>--%>
            <%--</td>--%>
            <%--<td><%=grantCondition.getMaxContractCost()%>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<% }--%>
        <%--%>--%>


    </table>
</div>
</body>
</html>
