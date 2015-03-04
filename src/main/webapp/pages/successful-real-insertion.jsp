<%--
  Created by IntelliJ IDEA.
  User: DOTIN SCHOOL 3
  Date: 3/4/2015
  Time: 9:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="../style/index.css">
</head>

<body>
<div>
    <p>
        اطلاعات در سیستم با موفقیت ثبت شد.
    </p>
    <p>
        شماره مشتری:
        <%=request.getAttribute("customerId")%>
    </p>

    <P>
        <a href="pages/real-customer.jsp">
            بازگشت به صفحه ی قبل
        </a>.
    </P>

</div>

</body>
</html>