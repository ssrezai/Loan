<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: DOTIN SCHOOL 3
  Date: 3/1/2015
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../style/customer-management-style.css">
    <title>
        عملیات مشتری حقیقی
    </title>
    <script>
        function formValidation() {
            var bool;
            var national_code = document.forms["insert_form"]["national_code"].value;
            var first_name = document.forms["insert_form"]["first_name"].value;
            var last_name = document.forms["insert_form"]["last_name"].value;
            var father_name = document.forms["insert_form"]["father_name"].value;
            if (national_code == null || national_code == "" ||
                    first_name == null || first_name == "" ||
                    last_name == null || last_name == "" ||
                    father_name == null || father_name == "") {
                alert("پر کردن تمام فیلدها اجباری است. مجددا تلاش نمایید.");
                bool = false;

            }
            else {
                var nationalCode = document.forms["insert_form"]["national_code"].value;
                bool = checkNationalCodeDigitCount(nationalCode);
            }
            return bool;
        }
        function checkNationalCodeDigitCount(nationalCode) {
            var bool;
            if (isNaN(nationalCode) || nationalCode.length != 10) {
                alert("کد ملی 10 رقم دارد. مجددا تلاش نمایید.");
                bool = false;
            }
            else {
                bool = checkNationalCodeValidation(nationalCode);
            }
            return bool;
        }
        function checkNationalCodeValidation(nationalCode) {
            var sum = 0;
            for (var index = 0; index < 10; index++) {
                var position = index + 1;
                sum = sum + (Number(nationalCode.substr(index, 1)) * (position));
            }
            var divisor = 11;
            var remaining = sum % divisor;
            var rightDigit = Number(nationalCode.substr(10, 1));
            var rightDigitComplement = 11 - rightDigit;
            if (remaining == rightDigit || remaining == rightDigitComplement) {
                return true;
            }
            else {
                alert("کد ملی وارد شده معتبر نیست");
                return false;
            }
        }


    </script>
    <script src="../javaScripts/form-validation.js" type="text/javascript"></script>
</head>
<body>


<div>

<form name="insert_form" onsubmit="return formValidation()" action="/RealCustomerServlet" method="post">
    <fieldset dir="rtl">
        <input type="hidden" name="type" value="real">

        <legend>
            تعریف مشتری جدید
        </legend>

        <table>
            <tr>
                <td><label for="first_name">
                    نام
                </label></td>
                <td></td>
                <td>
                    <input type="text" name="first_name" id="first_name">
                </td>
            </tr>

            <tr>
                <td><label for="last_name">
                    نام خانوادگی
                </label></td>
                <td></td>
                <td>
                    <input type="text" name="last_name" id="last_name">
                </td>
            </tr>
            <tr>
                <td><label for="father_name">
                    نام پدر
                </label></td>
                <td></td>
                <td>
                    <input type="text" name="father_name" id="father_name">
                </td>
            </tr>
            <tr>
                <td><label for="day"></label><label for="month"></label><label for="year"></label>
                    تاریخ تولد
                </td>
                <td></td>
                <td>
                    <select name="year" id="year" onchange="" size="1">
                        <option value="13557777">1355</option>
                        <option value="1356">1356</option>
                        <option value="1357">1357</option>
                        <option value="1358">1358</option>
                        <option value="1359">1359</option>
                        <option value="1360">1360</option>
                        <option value="1361">1361</option>
                        <option value="1362">1362</option>
                        <option value="1363">1363</option>
                        <option value="1364">1364</option>
                        <option value="1365">1365</option>
                        <option value="1366">1366</option>
                        <option value="1367">1367</option>
                        <option value="1368">1368</option>
                        <option value="1369">1369</option>
                        <option value="1370">1370</option>
                        <option value="1371">1371</option>
                        <option value="1372">1372</option>
                        <option value="1373">1373</option>
                        <option value="1374">1374</option>
                        <option value="1375">1375</option>
                        <option value="1376">1376</option>
                        <option value="1377">1377</option>
                        <option value="1378">1378</option>
                        <option value="1379">1379</option>
                        <option value="1380">1380</option>
                        <option value="1381">1381</option>
                        <option value="1382">1382</option>
                        <option value="1383">1383</option>
                        <option value="1384">1384</option>
                        <option value="1385">1385</option>
                        <option value="1386">1386</option>
                        <option value="1387">1387</option>
                        <option value="1388">1388</option>
                        <option value="1389">1389</option>
                        <option value="1390">1390</option>
                        <option value="1391">1391</option>
                        <option value="1392">1392</option>
                        <option value="1393">1393</option>
                    </select>
                    <select name="month" id="month" onchange="" size="1">
                        <option value="01">فروردین</option>
                        <option value="02">اردیبهشت</option>
                        <option value="03">خرداد</option>
                        <option value="04">تیر</option>
                        <option value="05">مرداد</option>
                        <option value="06">شهریور</option>
                        <option value="07">مهر</option>
                        <option value="08">آبان</option>
                        <option value="09">آذر</option>
                        <option value="10">دی</option>
                        <option value="11">بهمن</option>
                        <option value="12">اسفند</option>
                    </select>
                    <select name="day" id="day" onchange="" size="1">
                        <option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="national_code">
                    کدملی
                </label></td>
                <td></td>
                <td>
                    <!--onkeyup="this.value=this.value.replace(/[^\d]/,'')"-->
                    <input type="text" name="national_code" id="national_code">
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="submit" value="ثبت"></td>
                <td></td>
            </tr>
        </table>
    </fieldset>

</form>

<form name="search_form" action="/RealCustomerServlet" method="post">
    <fieldset dir="rtl">
        <legend> جستجوی مشتری حقیقی</legend>
        <input type="hidden" name="type" value="real">
        <table>
            <tr>
                <td><label for="search_first_name">
                    نام
                </label></td>
                <td></td>
                <td>
                    <input type="text" name="first_name" id="search_first_name">
                </td>
            </tr>
            <tr>
                <td><label for="search_last_name">
                    نام خانوادگی
                </label></td>
                <td></td>
                <td>
                    <input type="text" name="last_name" id="search_last_name">
                </td>
            </tr>
            <tr>
                <td><label for="search_national_code">
                    کدملی
                </label></td>
                <td></td>
                <td><input type="text" name="national_code" id="search_national_code"></td>
            </tr>
            <tr>
                <td><label for="search_customer_id">
شماره مشتری
                </label></td>
                <td></td>
                <td><input type="text" name="customer_id" id="search_customer_id"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="search" value="جستجو"></td>
                <td></td>
            </tr>
        </table>

    </fieldset>
</form>
<div>
    <p>
        <% if (request.getAttribute("InvalidNationalCodeException") != null) {%>
        <script> alert("خطا! کدملی وارد شده نامعتبر است");
        </script>
        <%}%>
        <% if (request.getAttribute("DuplicateCustomerException") != null) {%>
        <script> alert("خطا! کدملی وارد شده تکراری است");
        </script>
        <%}%>
        <% if (request.getAttribute("successfulUpdate") != null) {%>
        <script> alert("اطلاعات با موفقیت بروزرسانی شد.");
        </script>
        <%}%>
    </p>
    <c:remove var="message" scope="session"/>
</div>
</div>

</body>
</html>
