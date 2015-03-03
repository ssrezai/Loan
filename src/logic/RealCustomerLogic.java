package logic;

import crud.RealCustomerCRUD;

/**
 * Created by DOTIN SCHOOL 3 on 3/3/2015.
 * we do the basic logical checking foe Real Customer..
 *
 * @author Samira Rezaei
 */
public class RealCustomerLogic {

    public static boolean checkRealCustomerAdding(RealCustomer realCustomer) throws DuplicateCustomerException {
        boolean validate = false;
        if (checkNationalCodeValidation(realCustomer.getNationalCode())) {
            if (RealCustomerCRUD.getRealCustomerByNationalCode(realCustomer.getNationalCode()) == null) {
                validate = true;
            } else {
                validate = false;
                throw new DuplicateCustomerException("Duplicate NC");
            }

        } else {
            validate = false;
        }
        return validate;

    }

    public static boolean checkNationalCodeValidation(String nationalCode) {
        boolean validate = true;
        if (nationalCode.equals("") || !nationalCode.matches("\\d{10}")) {
            validate = false;
        } else {
            int sum = 0;
            for (int index = 0; index < 9; index++) {
                int numberInPosition = Integer.parseInt(nationalCode.substring(index, index + 1));
                sum = sum + numberInPosition * (index + 1);
            }
            int divisor = 11;
            int remaining = sum % divisor;
            int rightDigit = Integer.parseInt((nationalCode.substring(9, 10)));
            int rightDigitComplement = 11 - rightDigit;
            if (remaining == rightDigit || remaining == rightDigitComplement) {
                return true;
            }
        }
        return validate;
    }

    public static void addNewRealCustomer(RealCustomer realCustomer) {
        RealCustomerCRUD.AddRealCustomer(realCustomer);

    }

}
