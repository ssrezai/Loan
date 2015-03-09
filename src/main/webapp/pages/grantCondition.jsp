<%@ page import="java.util.ArrayList" %>
<%@ page import="logic.GrantCondition" %>
<%--
  Created by IntelliJ IDEA.
  User: DOTIN SCHOOL 3
  Date: 3/8/2015
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        شروط اعطا تسهیلات
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../style/customer-management-style.css">
    <style>
        #myTable, td, th {
            border: 1px solid black;
        }

    </style>
</head>
<%ArrayList<GrantCondition> grantConditionArrayList = new ArrayList<GrantCondition>();%>
<script>
    var grantConditionList = new Array();
    var count = 0;

    function createTable() {
        var grantCondition;
        if (checkParameters()) {
            var tableRef = document.getElementById('myTable').getElementsByTagName('tbody')[0];
            var newRow = tableRef.insertRow(tableRef.rows.length);
            var newCell0 = newRow.insertCell(0);
            var newCell1 = newRow.insertCell(1);
            var newCell2 = newRow.insertCell(2);
            var newCell3 = newRow.insertCell(3);
            var newCell4 = newRow.insertCell(4);

            var str0 = document.getElementById("name").value;
            var str1 = document.getElementById("min_contract_duration").value;
            var str2 = document.getElementById("max_contract_duration").value;
            var str3 = document.getElementById("min_contract_cost").value;
            var str4 = document.getElementById("max_contract_cost").value;

            var newText = document.createTextNode(str0);
            var newText1 = document.createTextNode(str1);
            var newText2 = document.createTextNode(str2);
            var newText3 = document.createTextNode(str3);
            var newText4 = document.createTextNode(str4);

            newCell0.appendChild(newText);
            newCell1.appendChild(newText1);
            newCell2.appendChild(newText2);
            newCell3.appendChild(newText3);
            newCell4.appendChild(newText4);
            grantCondition = str0 + "/" + str1 + "/" + str2 + "/" + str3 + "/" + str4;


            grantConditionList[count] = grantCondition;
            count = count + 1;
            document.submitForm.list.value = grantConditionList;

            return true;

        }
        else {
            alert("حداقل یک شرط اعطا باید تعریف شود");
            return false;
        }

    }
    function checkParameters() {
        var name = document.getElementById("name").value;
        var minDuration = document.getElementById("min_contract_duration").value;
        var maxDuration = document.getElementById("max_contract_duration").value;
        var minCost = document.getElementById("min_contract_cost").value;
        var maxCost = document.getElementById("max_contract_cost").value;
        var bool = true;
        if (name.length == 0 || minCost.length == 0 || maxCost.length == 0 || minDuration.length == 0 || maxDuration.length == 0) {
            bool = false;
            alert("پر کردن تمام فیلدها اجباری است.")
        }
        if (isNaN(minCost) || isNaN(maxCost) || isNaN(minDuration) || isNaN(maxDuration)) {
            bool = false;
            alert("اطلاعات ورودی نامعتبر است");
        }
        return bool;
    }
    function checkBeforeSubmit() {
        if (grantConditionList.length == 0) {
            alert("حداقل یک شرط اعطا باید تعریف شود");
            return false;
        }
        else {
            return true;
        }
    }


</script>
<body>
<table dir="rtl" id="inputTable" style="border: none" class="inputTable">

    <tr style="border: none">
        <td style="border: none">
            نام
        </td>
        <td style="border: none">
            <input type="text" name="name" id="name"><br>
        </td>
    </tr>

    <tr style="border: none">
        <td style="border: none">
            حداقل مدت قرارداد
        </td>
        <td style="border: none">
            <input type="text" name="min_contract_duration" id="min_contract_duration"><br>
        </td>
        <td style="border: none">
            ماه
        </td>
    </tr>

    <tr style="border: none">
        <td style="border: none">
            حداکثر مدت قرارداد
        </td>
        <td style="border: none">
            <input type="text" name="max_contract_duration" id="max_contract_duration"><br>
        </td>
        <td style="border: none">
            ماه
        </td>
    </tr>

    <tr style="border: none">
        <td style="border: none">
            حداقل مبلغ قرارداد
        </td>
        <td style="border: none">
            <input type="text" name="min_contract_cost" id="min_contract_cost"><br>
        </td>
        <td style="border: none">
            ریال
        </td>
    </tr>
    <tr style="border: none">
        <td style="border: none">
            حداکثر مبلغ قرارداد
        </td>
        <td style="border: none">
            <input type="text" name="max_contract_cost" id="max_contract_cost"><br>
        </td>
        <td style="border: none">
            ریال
        </td>
    </tr>
    <tr style="border: none">
        <td style="border: none">

        </td>
        <td style="border: none">
            <input type="button" value="اضافه کردن شرط جدید" onclick="createTable()">
        </td>
    </tr>
    <tr style="border: none">
        <td></td>
        <td style="border: none">
            <form name="submitForm" id="submitForm" onsubmit="return checkBeforeSubmit()" action="/LoanTypeServlet"
                  method="get">
                <input type="submit" value="ثبت نهایی">
                <input name="list" id="list" type="hidden">
                <%request.setCharacterEncoding("UTF-8");%>
                <input type="hidden" value="<%=request.getParameter("loan_type_name")%>" name="loan_type_name">
                <input type="hidden" value="<%=request.getParameter("interest_rate")%>" name="interest_rate">
            </form>
        </td>
    </tr>

</table>

</div>
<div dir="rtl">
    <table id="myTable">
        <thead>

        <tr>
            <th>نام</th>
            <th>حداقل مدت قرارداد</th>
            <th>حداکثرمدت قرارداد</th>
            <th>حداقل مبلغ قرارداد</th>
            <th> حداکثر مبلغ قرارداد</th>

        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>

</body>
</html>
