package main.webapp.servlets;

import logic.LoanFileLogic;
import logic.RealCustomer;
import logic.RealCustomerLogic;
import logic.exception.InvalidCustomerIdException;
import logic.exception.MismatchConditionException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author Samira Rezaei
 *         Created by DOTIN SCHOOL 3 on 3/7/2015.
 */
public class LoanFileServlet extends HttpServlet {
    private int customerId = 0;
    RealCustomer realCustomer = new RealCustomer();
    static final Logger logger = Logger.getLogger(LoanFileServlet.class);


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            if (request.getParameter("search") != null) {
                String id = request.getParameter("customer_id");

                try {
                    realCustomer = RealCustomerLogic.getRealCustomerByCustomerID(id);
                    if (realCustomer != null) {
                        request.setAttribute("first_name", realCustomer.getFirstName());
                        request.setAttribute("last_name", realCustomer.getLastName());
                        request.setAttribute("id", realCustomer.getCustomerID());
                        customerId = Integer.parseInt(realCustomer.getCustomerID());
                        request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                    } else {
                        logger.info("Non Existence Customer ID");
                        request.setAttribute("noSuch_error_msg", "No such realCustomerID..");
                        request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                    }

                } catch (InvalidCustomerIdException e) {
                    System.out.println("WRONG ID...try again...");
                    logger.info("WRONG ID");
                    request.setAttribute("Invalid_error_msg", "WRONG ID...try again...");
                    request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                }
            } else if (request.getParameter("submit") != null) {
                if (customerId == 0) {
                    logger.warn("Try to submit a loan file without any customer!!..");
                    request.setAttribute("CustomerIdError", "WRONG ID...try again...");
                    request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                } else {
                    String loanType = request.getParameter("loan_type_name");
                    BigInteger cost = new BigInteger(request.getParameter("contract_cost"));
                    int duration = Integer.parseInt(request.getParameter("contract_duration"));
                    try {
                        LoanFileLogic.makeNewLoanFile(realCustomer, Integer.parseInt(loanType), duration, cost);
                        request.setAttribute("successful", "successful");
                        request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                    } catch (MismatchConditionException e) {
                        System.out.println("Mismatch Condition Exception");
                        request.setAttribute("GrantConditionError", "WRONG ID...try again...");
                        request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                    }


                }
            }

        }


    }
}
