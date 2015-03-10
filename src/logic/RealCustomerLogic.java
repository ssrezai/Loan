package logic;

import crud.RealCustomerCRUD;
import logic.exception.DuplicateCustomerException;
import logic.exception.InvalidCustomerIdException;
import logic.exception.InvalidNationalCodeException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by DOTIN SCHOOL 3 on 3/3/2015.
 * we do the basic logical checking foe Real Customer..
 *
 * @author Samira Rezaei
 */
public class RealCustomerLogic {
    static final Logger logger = Logger.getLogger(RealCustomerLogic.class);


    public static boolean checkRealCustomerAdding(RealCustomer realCustomer) throws DuplicateCustomerException, InvalidNationalCodeException {
        boolean validate;
        if (checkNationalCodeValidation(realCustomer.getNationalCode())) {
            RealCustomer realCustomerFromDB = RealCustomerCRUD.getRealCustomerByNationalCode(realCustomer.getNationalCode());
            if (realCustomerFromDB == null) {
                validate = true;
            } else {
                if (realCustomerFromDB.getCustomerID().equals(realCustomer.getCustomerID())) {
                    validate = true;
                } else {
                    validate = false;
                    logger.warn("Duplicate National Code Exception..");
                    throw new DuplicateCustomerException("Duplicate NC");
                }
            }
        } else {
            validate = false;
            logger.warn("Invalid National Code Exception..");
            throw new InvalidNationalCodeException("Wrong NC");
        }
        return validate;

    }

    public static boolean checkNationalCodeValidation(String nationalCode) throws InvalidNationalCodeException {
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
        if (!validate) {
            logger.warn("Invalid National Code Exception..");
            throw new InvalidNationalCodeException("invalid nc");
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

    public static RealCustomer getRealCustomerByCustomerID(String id) throws InvalidCustomerIdException {
        RealCustomer realCustomer = new RealCustomer();
        if (id.matches("\\d+")) {
            int customerId = Integer.parseInt(id);
            realCustomer = RealCustomerCRUD.getRealCustomerById(customerId);
        } else {
            logger.warn("Invalid Customer ID Exception..");
            throw new InvalidCustomerIdException("invalid id...");
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
        return RealCustomerCRUD.searchRealCustomer(query);
    }

    public static void deleteRealCustomer(String id) {
        RealCustomerCRUD.deleteRealCustomer(id);
    }

    public static boolean checkNationalCodeModification(RealCustomer realCustomer) throws DuplicateCustomerException {
        boolean validate = false;
        RealCustomer realCustomerFromDB = RealCustomerCRUD.getRealCustomerByNationalCode(realCustomer.getNationalCode());
        if (realCustomerFromDB == null) {
            validate = true;
        } else {
            if (realCustomerFromDB.getCustomerID().equals(realCustomer.getCustomerID())) {
                validate = true;
            } else {
                validate = false;
                logger.warn("Duplicate National Code Exception..");
                throw new DuplicateCustomerException("");
            }
        }
        return validate;
    }

    public static void updateRealCustomerInfo(RealCustomer realCustomer) {
        RealCustomerCRUD.updateRealCustomerTable(realCustomer);
    }
}
