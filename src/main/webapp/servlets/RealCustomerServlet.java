package main.webapp.servlets;

import logic.RealCustomer;
import logic.RealCustomerLogic;
import logic.exception.DuplicateCustomerException;
import logic.exception.InvalidNationalCodeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 *
 * @author Samira Rezaei
 */
public class RealCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

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
                        System.out.println(realCustomer.getNationalCode());
                        /////.........////
                        try {
                            if (RealCustomerLogic.checkRealCustomerAdding(realCustomer)) {
                                RealCustomerLogic.addNewRealCustomer(realCustomer);
                                String customerId = RealCustomerLogic.getRealCustomerId(realCustomer);

                                request.setAttribute("customerId", customerId);
                                request.getRequestDispatcher("pages/successful-real-insertion.jsp").forward(request, response);
                                System.out.println(customerId);
                                System.out.println("Done..");
                            }
                        } catch (InvalidNationalCodeException e) {
                            request.setAttribute("error", "...InvalidNationalCodeException ...");
                            request.getRequestDispatcher("pages/real-customer.jsp").forward(request, response);
                            System.out.println("...InvalidNationalCodeException ...");
                        } catch (DuplicateCustomerException e) {
                            ////////////////////
                            request.setAttribute("error", "...DuplicateCustomerException ...");
                            request.getRequestDispatcher("pages/real-customer.jsp").forward(request, response);
                            ///////////////////////////
                            System.out.println("...DuplicateCustomerException ...");
                        }


                    }
                } else if (request.getParameter("search") != null) {
                    System.out.println("searching..");
                    RealCustomer realCustomer = new RealCustomer();
                    realCustomer.setFirstName(request.getParameter("first_name"));
                    realCustomer.setLastName(request.getParameter("last_name"));
                    realCustomer.setNationalCode(request.getParameter("national_code"));
                    realCustomer.setCustomerID(request.getParameter("customer_id"));
                    System.out.println(realCustomer.getFirstName());
                    ///pass this obj to logic layer///
                    List searchResult = RealCustomerLogic.searchRealCustomer(request);
                    System.out.println(searchResult.size());

                }
            }


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
