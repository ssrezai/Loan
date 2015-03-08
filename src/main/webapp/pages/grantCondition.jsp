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
</head>
<script>
    function createTable() {
        var tableRef = document.getElementById('myTable').getElementsByTagName('tbody')[0];
        var newRow=tableRef.insertRow(tableRef.rows.length);
        var newCell0=newRow.insertCell(0);
        var newCell1=newRow.insertCell(1);
        var newCell2=newRow.insertCell(2);
        var newCell3=newRow.insertCell(3);
        var str=new String(document.getElementById("n").value)

        var newText=document.createTextNode(str);
        var newText1=document.createTextNode("h");
        var newText2=document.createTextNode("h");
        var newText3=document.createTextNode("h");

        newCell0.appendChild(newText);
        newCell1.appendChild(newText1);
        newCell2.appendChild(newText2);
        newCell3.appendChild(newText3);

</script>
<body>
<div>
    <label>
        نام
    </label>
    <input type="text" name="name" id="name">

    <label>
        حداقل مدت قرارداد
    </label>
    <input type="text" name="min_contract_duration" id="min_contract_duration">

    <label>
        حداکثر مدت قرارداد
    </label>
    <input type="text" name="max_contract_duration" id="max_contract_duration">

    <label>
        حداقل مبلغ قرارداد
    </label>
    <input type="text" name="min_contract_cost" id="min_contract_cost">

    <label>
        حداکثر مبلغ قرارداد
    </label>
    <input type="text" name="max_contract_cost" id="max_contract_cost">

    <input type="button" onclick="createTable()">
</div>
<div dir="rtl">
    <table id="myTable">
        <thead>

        <tr>
            <th>نام</th>
            <th>حداقل مدت قرارداد</th>
            <th>حداکثرمدت قرارداد</th>
            <th>حداقل مبلغ قرارداد</th>
            <th> حداکثر مبلغ قرارداد </th>

        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>

</body>
</html>
