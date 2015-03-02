/**
 * Created by DOTIN SCHOOL 3 on 3/2/2015.
 */
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
        bool=false;

    }
    else
    {
        var nationalCode= document.forms["insert_form"]["national_code"].value;
        bool=checkNationalCodeDigitCount(nationalCode);
    }
    return bool;
}
function checkNationalCodeDigitCount(nationalCode)
{
    var bool;
    if(isNaN(nationalCode)|| nationalCode.length!=10)
    {
        alert("کد ملی 10 رقم دارد. مجددا تلاش نمایید.");
        bool= false;
    }
    else
    {
        bool=checkNationalCodeValidation(nationalCode);
    }
    return bool;
}
function checkNationalCodeValidation(nationalCode)
{
    var sum=0;
    for(var index=0;index<10;index++)
    {
        var position=index+1;
        sum=sum+(Number(nationalCode.substr(index,1))*(position));
    }
    var divisor=11;
    var remaining=sum % divisor;
    var rightDigit=Number(nationalCode.substr(10,1));
    var rightDigitComplement= 11- rightDigit;
    if(remaining==rightDigit||remaining==rightDigitComplement)
    {
        return true;
    }
    else
    {
        alert("کد ملی وارد شده معتبر نیست");
        return false;
    }
}