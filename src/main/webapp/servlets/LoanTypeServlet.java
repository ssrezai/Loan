package main.webapp.servlets;

import logic.LoanLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DOTIN SCHOOL 3 on 3/7/2015.
 */
public class LoanTypeServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request != null) {
            if (request.getParameter("submit") != null) {
                String loanTypeName=request.getParameter("loan_type_name");
                String interestRate=request.getParameter("interest_rate");
                 if(LoanLogic.validateLoanType(loanTypeName,interestRate))
                 {
                     String minContractCost=request.getParameter("min_contract_cost");
                     String maxContractCost=request.getParameter("max_contract_cost");
                     String minContractDuration=request.getParameter("min_contract_duration");
                     String maxContractDuration=request.getParameter("max_contract_duration");
                     String name=request.getParameter("name");
                     if(LoanLogic.validateGrantCondition(minContractCost,maxContractCost,minContractDuration,maxContractDuration,name))
                     {
                         LoanLogic.addLoanTypeWithGrantCondition(loanTypeName,interestRate,minContractCost,maxContractCost,minContractDuration,maxContractDuration,name);
                         response.sendRedirect("pages/index.html");
                     }


                 }
                else
                 {
                     request.setAttribute("error","error");
                     response.sendRedirect("pages/bank-loan-function.jsp");

                 }
            }
        }


    }
}