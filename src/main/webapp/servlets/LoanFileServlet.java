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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            String id = request.getParameter("customer_id");
            System.out.println(id);
            try {
                RealCustomer realCustomer = RealCustomerLogic.getRealCustomerByCustomerID(id);
                request.setAttribute("first_name", realCustomer.getFirstName());
                request.setAttribute("last_name", realCustomer.getLastName());
                request.setAttribute("id", realCustomer.getCustomerID());
                request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);

                System.out.println(realCustomer.getFirstName() + " " + realCustomer.getLastName());
            } catch (InvalidCustomerId e) {
                System.out.println("WRONG ID...try again...");
                request.setAttribute("error_msg", "WRONG ID...try again...");
                request.getRequestDispatcher("pages/make-loan-file.jsp").forward(request, response);


            }
        }


    }
}
