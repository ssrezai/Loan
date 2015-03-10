<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="logic.GrantCondition" %>
<%@ page import="java.math.BigInteger" %>
<%--&lt;%&ndash;--%>
<%--Created by IntelliJ IDEA.--%>
<%--User: DOTIN SCHOOL 3--%>
<%--Date: 3/1/2015--%>
<%--Time: 3:48 PM--%>
<%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>--%>
<%--<link rel="stylesheet" type="text/css" href="../style/customer-management-style.css">--%>
<%--<title>--%>
<%--عملیات تسهیلات بانکی_شروط اعطا--%>
<%--</title>--%>
<%--<script>--%>
<%--function addToTable2()--%>
<%--{--%>
<%--var name=document.form["grantCondition"]["name"].value;--%>
<%--var minCost=document.form["grantCondition"]["min_contract_cost"].value;--%>
<%--var maxCost=document.form["grantCondition"]["max_contract_cost"].value;--%>
<%--var minDuration=document.form["grantCondition"]["min_contract_duration"].value;--%>
<%--var maxDuration=document.form["grantCondition"]["max_contract_duration"].value;--%>
<%--var tr= document.getElementById("table")--%>
<%--document.getElementById("demo")--%>
<%--}--%>
<%--</script>--%>

<%--</head>--%>

<%--<script>--%>
<%--function addToTable() {--%>
<%--var name=document.form["grantCondition"]["name"].value;--%>
<%--var minCost=document.form["grantCondition"]["min_contract_cost"].value;--%>
<%--var table = document.getElementById("myTable");--%>
<%--var header = table.createTHead();--%>
<%--var row = header.insertRow(0);--%>
<%--var cell = row.insertCell(0);--%>
<%--var cell1 = row.insertCell(1);--%>
<%--cell.innerHTML = name;--%>
<%--cell1.innerHTML = minCost;--%>
<%--}--%>
<%--</script>--%>

<%--<body>--%>

<%--<div dir="rtl" style="margin-top: 150px">--%>


<%--<input type="hidden" value="<%=request.getParameter("loan_type_name")%>" name="loan_type_name">--%>
<%--<input type="hidden" value="<%=request.getParameter("interest_rate")%>" name="interest_rate">--%>


<%--name <input type="text" name="name" id="name">--%>

<%--value         <input type="text" name="min_contract_duration" id="min_contract_duration">--%>

<%--<button onclick="addToTable()">--%>
<%--اضافه شدن به شروط اعطا--%>
<%--</button>--%>


<%--</div>--%>
<%--<div dir="rtl">--%>
<%--<table id="myTable" border="1" style="width:100%">--%>


<%--</table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html>
<head>
    <style>
        table, td,th {
            border: 1px solid black;
        }
    </style>
</head>
<body>

<p>Click the button to create a thead element for the table.</p>
<table id="myTable">
    <thead>

    <tr>
        <th>h1</th>
        <th>h2</th>
        <th>h3</th>
        <th>h4</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>

<input type="button" value="click" onclick="myFunction()"></button><br/>
name <input type="text" name="name2" id="n"><br/>

val1 <input type="text" name="min_contract_duration" id="val1"><br/>

val2 <input type="text" name="max_contract_duration" id="val2"><br/>

val3 <input type="text" name="min_contract_cost" id="val3"><br/>
<%--<form action="/LoanTypeServlet" method="get">--%>
    <%--<input type="submit" value="click....">--%>
<%--</form>--%>

<script>
    function myFunction() {
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

//        var table = document.getElementById("myTable");
//        var header = table.createTHead();
//        var row = header.insertRow(0);
//
//        var cell1 = row.insertCell(0);
//        var cell2 = row.insertCell(1);
//        var cell3 = row.insertCell(2);
//        var cel14 = row.insertCell(3);
//
//        cell1.innerHTML = document.getElementById('name2').value;
//        cell2.innerHTML = document.getElementById('val1').value;
//        cell3.innerHTML = document.getElementById('val2').value;
//        cel14.innerHTML = document.getElementById('val3').value;
<%List list= new ArrayList();
    GrantCondition g=new GrantCondition(1,2,"samir",new BigInteger("3"),new BigInteger("3"));
    request.setAttribute("list",g);%>
    }

</script>

</body>
</html>

