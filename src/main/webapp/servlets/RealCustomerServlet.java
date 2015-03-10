package main.webapp.servlets;

import logic.RealCustomer;
import logic.RealCustomerLogic;
import logic.exception.DuplicateCustomerException;
import logic.exception.InvalidNationalCodeException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 *
 * @author Samira Rezaei
 */
public class RealCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final Logger logger = Logger.getLogger(RealCustomerServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("delete") != null) {
            if (request.getParameter("type").equals("real")) {
                String id = request.getParameter("customer_id");
                RealCustomerLogic.deleteRealCustomer(id);
//                logger.info("Redirect: successful-remove.html");
                response.sendRedirect("pages/index.html");
            }

        } else if (request.getParameter("modify") != null) {
            if (request.getParameter("type").equals("real")) {
                RealCustomer realCustomer = new RealCustomer();
                realCustomer.setFirstName(request.getParameter("first_name"));
                realCustomer.setLastName(request.getParameter("last_name"));
                realCustomer.setFatherName(request.getParameter("father_name"));
                realCustomer.setBirthDate(request.getParameter("birth_date"));
                realCustomer.setNationalCode(request.getParameter("national_code"));
                realCustomer.setCustomerID(request.getParameter("customer_id"));
                try {
                    if (RealCustomerLogic.checkNationalCodeValidation(realCustomer.getNationalCode())) {
                        if (RealCustomerLogic.checkNationalCodeModification(realCustomer)) {
                            RealCustomerLogic.updateRealCustomerInfo(realCustomer);
                            request.setAttribute("successfulUpdate", "successfulUpdate");
                            request.getRequestDispatcher("pages/real-customer.jsp").forward(request, response);
                        }
                    }
                } catch (InvalidNationalCodeException e) {
                    request.setAttribute("InvalidNationalCodeException", "...InvalidNationalCodeException ...");
                    request.getRequestDispatcher("pages/real-customer.jsp").forward(request, response);
                    logger.info("Catch InvalidNationalCodeException");

                } catch (DuplicateCustomerException e) {
                    request.setAttribute("DuplicateCustomerException", "...DuplicateCustomerException ...");
                    request.getRequestDispatcher("pages/real-customer.jsp").forward(request, response);
                    logger.info("Catch DuplicateCustomerException");

                }
            }

        }


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
                            request.setAttribute("InvalidNationalCodeException", "...InvalidNationalCodeException ...");
                            request.getRequestDispatcher("pages/real-customer.jsp").forward(request, response);
                            System.out.println("...InvalidNationalCodeException ...");
                        } catch (DuplicateCustomerException e) {
                            ////////////////////
                            request.setAttribute("DuplicateCustomerException", "...DuplicateCustomerException ...");
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
                    response.setContentType("text/html; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter printWriter = response.getWriter();
                    printWriter.println(makeRealCustomerTable(searchResult));
                    System.out.println(searchResult.size());

                }
            }


        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public String makeRealCustomerTable(List<RealCustomer> realCustomers) {
        String tableRows = "";
        int count = realCustomers.size();
        String deleteButton = "<input type=\"submit\" name=\"delete\"  value=\"delete\">\n";
        String modifyButton = "<input type=\"submit\" value=\"modify\" name=\"modify\">\n";

        String tableHeader = "<tr>\n" +
                "    <th>شماره مشتری</th>\n" +
                "    <th>نام</th>\t\t\n" +
                "    <th>نام خانوادگی</th>\n" +
                "    <th>نام پدر</th>\n" +
                "    <th>کد ملی</th>\t\t\n" +
                "    <th>تاریخ تولد</th>\n" +
                "     <th>حذف</th>\n" +
                "     <th>بروزرسانی</th>\n" +
                "     </tr>\n";
        for (RealCustomer realCustomer : realCustomers) {
            String firstName = fillTableRow(realCustomer.getFirstName());
            String lastName = fillTableRow(realCustomer.getLastName());
            String fatherName = fillTableRow(realCustomer.getFatherName());
            String birthDate = fillTableRow(realCustomer.getBirthDate());
            String nationalCode = fillTableRow(realCustomer.getNationalCode());
            String accountNumber = fillTableRow(realCustomer.getCustomerID());
            tableRows = tableRows +
                    "<tr> " +
                    "<form action= \"/RealCustomerServlet\" method=\"get\">\n" +
                    "<input type=\"hidden\" name=\"type\" value=\"real\">" +
                    "<td>" + accountNumber + "</td>\n" + "<input type=\"hidden\" name=\"customer_id\" value=" + accountNumber + ">" +
                    "<td> <input type=\"text\" name=\"first_name\" value=\"" + firstName + "\">" + "</td>\n" +
                    "<td> <input type=\"text\" name=\"last_name\" value=\"" + lastName + "\">" + "</td>\n" +
                    "<td><input type=\"text\" name=\"father_name\" value=\"" + fatherName + "\">" + "</td>\n" +
                    "<td><input type=\"text\" name=\"national_code\" value=\"" + nationalCode + "\">" + "</td>\n" +
                    "<td><input type=\"text\" name=\"birth_date\" value=\"" + birthDate + "\">" + "</td>\n" +
                    "<td>" + deleteButton + "</td>\n" +
                    "<td>" + modifyButton + "</td>\n" +
                    " </form>"
                    + "</tr>\n";
        }
        String htmlPart1 = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "<title> نتایج جستجو</title>" +
                "<style>\n" +
                "body,table,form {\n" +
                "background-color: #eeffee;\n" +
                "align-content: center;\n" +
                "margin-left: auto;\n" +
                " margin-right: auto;\n" +
                "        }" +
                " div {\n" +
                " margin-top: 10px;\n" +
                "margin-right: 70px;\n" +
                " margin-left: 70px;\n" +
                "text-align: center;\n" +
                "}" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "    padding: 5px;\n" +
                "}\n" +
                "</style>" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" +
                "</head>\n" +
                "\n" +
                "<body>\n";

        String tableTag = "<table>\n" + tableHeader + tableRows + "</table>";
        String htmlPart2 = "<div>\n" +
                "  <p>\n" +
                "  #of search result:" + count + "\n" +
                "  </p>\n" +
                "  <p>\n" +
                "<a href=\"pages/real-customer.jsp\">" +
                "            بازگشت به صفحه ی قبل\n" +
                "  </a>\n" +
                "  </p>\n" +
                "</div>" +
                "</body>\n" +
                "</html>";

        return htmlPart1 + tableTag + htmlPart2;

    }

    public String fillTableRow(String rowValue) {
        AtomicReference<String> finalRowValue = new AtomicReference<String>(" ");
        if (rowValue == null) {
            finalRowValue.set("-Not Set-");
            return finalRowValue.get();
        } else
            return rowValue;
    }
}
