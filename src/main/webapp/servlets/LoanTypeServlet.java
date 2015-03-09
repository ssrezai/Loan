package main.webapp.servlets;

import logic.GrantCondition;
import logic.LoanLogic;
import logic.LoanType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by DOTIN SCHOOL 3 on 3/7/2015.
 *
 * @author Samira Rezaei
 */
public class LoanTypeServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            String totalConditionList = request.getParameter("list");
            System.out.println(totalConditionList);
            if (LoanLogic.validateGrantConditionList(totalConditionList)) {
                String loanTypeName = request.getParameter("loan_type_name");
                String interestRate = request.getParameter("interest_rate");
                LoanType loanType = new LoanType();
                loanType.setLoanTypeName(loanTypeName);
                loanType.setInterestRate(Integer.parseInt(interestRate));
                Set<GrantCondition> conditionSet=LoanLogic.makeGrantCondition(totalConditionList);
                System.out.println(loanTypeName);
            }
//            String[] conditionList = totalConditionList.split(",");
//            for (int index = 0; index < conditionList.length; index++)
//            {
//                System.out.println(conditionList[index]);
//                String [] list2= conditionList[index].split("/");
//                for(int i=0;i<list2.length;i++)
//                {
//                    System.out.println(list2[i]);
//                }
//            }
        }

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            if (request.getParameter("submit") != null) {
                String loanTypeName = request.getParameter("loan_type_name");
                String interestRate = request.getParameter("interest_rate");
                LoanType loanType = new LoanType();
                loanType.setLoanTypeName(loanTypeName);
                loanType.setInterestRate(Integer.parseInt(interestRate));
                Set<GrantCondition> conditionSet = LoanLogic.getTotalCondition(loanType);
                Iterator iterator = conditionSet.iterator();
                if (LoanLogic.validateLoanType(loanTypeName, interestRate)) {
                    String minContractCost = request.getParameter("min_contract_cost");
                    String maxContractCost = request.getParameter("max_contract_cost");
                    String minContractDuration = request.getParameter("min_contract_duration");
                    String maxContractDuration = request.getParameter("max_contract_duration");
                    String name = request.getParameter("name");
                    if (LoanLogic.validateGrantCondition(minContractCost, maxContractCost, minContractDuration, maxContractDuration, name)) {
                        LoanLogic.addLoanTypeWithGrantCondition(loanTypeName, interestRate, minContractCost, maxContractCost, minContractDuration, maxContractDuration, name);
                        request.setAttribute("conditions", iterator);
                        request.getRequestDispatcher("pages/grant-condition.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "error");
                    response.sendRedirect("pages/bank-loan-function.jsp");

                }
            }
        }


    }
}