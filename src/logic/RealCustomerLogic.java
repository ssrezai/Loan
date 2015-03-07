package logic;

import crud.RealCustomerCRUD;
import logic.exception.DuplicateCustomerException;
import logic.exception.InvalidCustomerId;
import logic.exception.InvalidNationalCodeException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by DOTIN SCHOOL 3 on 3/3/2015.
 * we do the basic logical checking foe Real Customer..
 *
 * @author Samira Rezaei
 */
public class RealCustomerLogic {

    public static boolean checkRealCustomerAdding(RealCustomer realCustomer) throws DuplicateCustomerException, InvalidNationalCodeException {
        boolean validate;
        if (checkNationalCodeValidation(realCustomer.getNationalCode())) {
            if (RealCustomerCRUD.getRealCustomerByNationalCode(realCustomer.getNationalCode()) == null) {
                validate = true;
            } else {
                validate = false;
                throw new DuplicateCustomerException("Duplicate NC");
            }

        } else {
            validate = false;
            throw new InvalidNationalCodeException("Wrong NC");

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

    public static String getRealCustomerId(RealCustomer realCustomer) {
        String customerId = "";
        String nationalCode = realCustomer.getNationalCode();
        RealCustomer returnedRealCustomer = RealCustomerCRUD.getRealCustomerByNationalCode(nationalCode);
        if (returnedRealCustomer != null) {
            customerId = String.valueOf(returnedRealCustomer.getCustomerID());
        }
        return customerId;
    }

    public static RealCustomer getRealCustomerByCustomerID(String id) throws InvalidCustomerId {
        RealCustomer realCustomer = new RealCustomer();
        if (id.matches("\\d+")) {
            int customerId = Integer.parseInt(id);
            realCustomer=  RealCustomerCRUD.getRealCustomerById(customerId);
        } else {
            throw new InvalidCustomerId("invalid id...");
        }
        return realCustomer;
    }

    public static String buildQuery(HttpServletRequest request) {
        int count = 0;
        String query = "";
        if (request.getParameter("first_name").length() > 0) {
            query = "FROM RealCustomer WHERE first_name='" + request.getParameter("first_name") + "'";
            count++;
        }
        if (request.getParameter("last_name").length() > 0) {
            if (count == 0) {
                query = "FROM RealCustomer WHERE last_name='" + request.getParameter("last_name") + "'";
            } else {
                query = query + " AND last_name='" + request.getParameter("last_name") + "'";
            }
            count++;
        }
        if (request.getParameter("national_code").length() > 0) {
            if (count == 0) {
                query = "FROM RealCustomer WHERE national_code='" + request.getParameter("national_code") + "'";
            } else {
                query = query + " AND national_code='" + request.getParameter("national_code") + "'";
            }
            count++;
        }
        if ((request.getParameter("customer_id").length() > 0)) {
            if (count == 0) {
                query = "FROM RealCustomer WHERE fk_customerId=" + request.getParameter("customer_id");
            } else {
                query = query + " AND fk_customerId=" + request.getParameter("customer_id");
            }
            count++;
        }
        return query;

    }

    public static List searchRealCustomer(HttpServletRequest request) {
        String query = buildQuery(request);
        List result = RealCustomerCRUD.searchRealCustomer(query);
        return result;

    }

}
