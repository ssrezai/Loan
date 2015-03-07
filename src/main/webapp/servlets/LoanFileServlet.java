package main.webapp.servlets;

import logic.RealCustomer;
import logic.RealCustomerLogic;
import logic.exception.InvalidCustomerId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DOTIN SCHOOL 3 on 3/7/2015.
 */
public class LoanFileServlet extends HttpServlet {
    private int customerId = 0;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            if (request.getParameter("search") != null) {
                String id = request.getParameter("customer_id");
                System.out.println(id);
                try {
                    RealCustomer realCustomer = RealCustomerLogic.getRealCustomerByCustomerID(id);
                    if (realCustomer != null) {
                        request.setAttribute("first_name", realCustomer.getFirstName());
                        request.setAttribute("last_name", realCustomer.getLastName());
                        request.setAttribute("id", realCustomer.getCustomerID());
                        customerId = Integer.parseInt(realCustomer.getCustomerID());
                        request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                    } else {
                        System.out.println("No such user...try again...");
                        request.setAttribute("noSuch_error_msg", "No such realCustomerID..");
                        request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                    }

                    System.out.println(realCustomer.getFirstName() + " " + realCustomer.getLastName());
                } catch (InvalidCustomerId e) {
                    System.out.println("WRONG ID...try again...");
                    request.setAttribute("Invalid_error_msg", "WRONG ID...try again...");
                    request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                }
            } else if (request.getParameter("submit") != null) {
                if (customerId == 0) {
                    request.setAttribute("error", "WRONG ID...try again...");
                    request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);
                } else {
                    request.setAttribute("yes", "yes");
                    request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);

                }
            }

        }


    }
}
