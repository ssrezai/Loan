package main.webapp.servlets;

import logic.RealCustomer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 *
 * @author Samira Rezaei
 */
public class RealCustomerServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("submit") != null) {
                String type = request.getParameter("type");
                if (type.equalsIgnoreCase("real")) {
                    RealCustomer realCustomer = new RealCustomer();
                    realCustomer.setFirstName(request.getParameter("first_name"));
                    realCustomer.setLastName(request.getParameter("last_name"));
                    realCustomer.setFatherName(request.getParameter("father_name"));
                    String birthDate = request.getParameter("year") + "/" + request.getParameter("month") + "/" + request.getParameter("day");
                    realCustomer.setBirthDate(birthDate);
                    realCustomer.setNationalCode(request.getParameter("national_code"));
                    /////.........////


                } else if (request.getParameter("search") != null) {

                }
            }


        }
    }
}
