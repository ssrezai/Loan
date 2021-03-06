package main.webapp.servlets;

import logic.LoanLogic;
import logic.LoanType;
import logic.exception.EmptyGrantConditionListException;
import logic.exception.InvalidGrantConditionException;
import logic.exception.LoanTypeInfoException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by DOTIN SCHOOL 3 on 3/7/2015.
 *
 * @author Samira Rezaei
 */
public class LoanTypeServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(LoanTypeServlet.class);


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            request.setCharacterEncoding("UTF-8");
            String totalConditionList = request.getParameter("list");
            System.out.println(totalConditionList);
            try {
                if (LoanLogic.validateGrantConditionList(totalConditionList)) {
                    String loanTypeName = request.getParameter("loan_type_name");
                    String interestRate = request.getParameter("interest_rate");
                    LoanLogic.validateLoanType(loanTypeName, interestRate);
                    LoanType loanType = new LoanType();
                    loanType.setLoanTypeName(loanTypeName);
                    loanType.setInterestRate(Integer.parseInt(interestRate));
                    Set conditionSet = LoanLogic.makeGrantCondition(totalConditionList);
                    LoanLogic.addLoanTypeWithGrantCondition(loanTypeName, interestRate, conditionSet);
                    request.setAttribute("successful", "successful");
                    request.getRequestDispatcher("pages/bank-loan-function.jsp").forward(request, response);
                    System.out.println(loanTypeName);
                } else {
                    request.setAttribute("error", "error");
                    request.getRequestDispatcher("pages/bank-loan-function.jsp").forward(request, response);
                }
            } catch (InvalidGrantConditionException e) {
                logger.info("catch InvalidGrantConditionException...");
                e.printStackTrace();
            } catch (LoanTypeInfoException e) {
                logger.info("catch LoanTypeInfoException...");
                e.printStackTrace();
            } catch (EmptyGrantConditionListException e) {
                logger.info(" catch EmptyGrantConditionListException...");
                e.printStackTrace();
            }
        }

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request != null) {
//            if (request.getParameter("submit") != null) {
//                String loanTypeName = request.getParameter("loan_type_name");
//                String interestRate = request.getParameter("interest_rate");
//                LoanType loanType = new LoanType();
//                loanType.setLoanTypeName(loanTypeName);
//                loanType.setInterestRate(Integer.parseInt(interestRate));
//                Set<GrantCondition> conditionSet = LoanLogic.getTotalCondition(loanType);
//                Iterator iterator = conditionSet.iterator();
//                if (LoanLogic.validateLoanType(loanTypeName, interestRate)) {
//                    String minContractCost = request.getParameter("min_contract_cost");
//                    String maxContractCost = request.getParameter("max_contract_cost");
//                    String minContractDuration = request.getParameter("min_contract_duration");
//                    String maxContractDuration = request.getParameter("max_contract_duration");
//                    String name = request.getParameter("name");
//                    if (LoanLogic.validateGrantCondition(minContractCost, maxContractCost, minContractDuration, maxContractDuration, name)) {
//                        LoanLogic.addLoanTypeWithGrantCondition(loanTypeName, interestRate, minContractCost, maxContractCost, minContractDuration, maxContractDuration, name);
//                        request.setAttribute("conditions", iterator);
//                        request.getRequestDispatcher("pages/grant-condition.jsp").forward(request, response);
//                    }
//                } else {
//                    request.setAttribute("error", "error");
//                    response.sendRedirect("pages/bank-loan-function.jsp");
//
//                }
//            }
//        }
//
//
    }
}