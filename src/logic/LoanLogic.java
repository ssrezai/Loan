package logic;

import crud.LoanCRUD;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * Created by DOTIN SCHOOL 3 on 3/5/2015.
 *
 * @author Samira Rezaei
 *         Basic logic for Loan function is checked here....
 */
public class LoanLogic {

    public static List returnLoanTypeName() {
        List loanTypeNames = null;
        loanTypeNames = LoanCRUD.getLoanTypeName();
        return loanTypeNames;
    }

    public static List<LoanType> returnLoanType() {
        List loanTypes = null;
        loanTypes = LoanCRUD.getLoanTypes();
        return loanTypes;
    }

    public static boolean validateLoanType(String loanTypeName, String interestRate) {
        boolean valid = true;
        if (loanTypeName == null || loanTypeName.equals("") || interestRate == null || interestRate.equals("") || !interestRate.matches("\\d{1,2}(\\.\\d{1,2})?")) {
            valid = false;
        }
        return valid;
    }

    public static boolean validateGrantCondition(String minContractCost, String maxContractCost, String minContractDuration, String maxContractDuration, String name) {
        boolean valid = true;
        if (minContractCost == null || minContractCost.equals("") || maxContractCost == null || maxContractCost.equals("") || name == null || name.equals("")
                || !minContractDuration.matches("\\d+") || !maxContractDuration.matches("\\d+")
                || !maxContractCost.matches("\\d+") || !minContractCost.matches("\\d+")) {
            valid = false;
        }
        return valid;
    }

    public static void addLoanTypeWithGrantCondition(String loanTypeName, String interestRate,
                                                     String minContractCost, String maxContractCost, String minContractDuration, String maxContractDuration, String name) {
        GrantCondition grantCondition = new GrantCondition(Integer.parseInt(minContractDuration), Integer.parseInt(maxContractDuration), name, new BigInteger(minContractCost), new BigInteger(maxContractCost));
        LoanType loanType = new LoanType();
        loanType.setLoanTypeName(loanTypeName);
        loanType.setInterestRate(Integer.parseInt(interestRate));
        LoanCRUD.updateLoanTypeTable(loanTypeName, Integer.parseInt(interestRate), grantCondition);
    }

    public static void addLoanTypeWithGrantCondition2(String loanTypeName, String interestRate, Set<GrantCondition> conditionList) {
       // GrantCondition grantCondition = new GrantCondition(Integer.parseInt(minContractDuration), Integer.parseInt(maxContractDuration), name, new BigInteger(minContractCost), new BigInteger(maxContractCost));
        LoanType loanType = new LoanType();
        loanType.setLoanTypeName(loanTypeName);
        loanType.setInterestRate(Integer.parseInt(interestRate));
        LoanCRUD.updateLoanTypeTable2(loanTypeName, Integer.parseInt(interestRate), conditionList);
    }

    public static Set<GrantCondition> getTotalCondition(LoanType loanType) {
        return LoanCRUD.getTotalSetOfConditions(loanType);
    }

    public static boolean validateGrantConditionList(String conditionList) {
        boolean valid = true;
        String[] listOfConditions = splitConditionList(conditionList);

        for (int index = 0; index < listOfConditions.length; index++) {
            if (!valid) {
                break;
            } else {
                String[] condition = listOfConditions[index].split("/");
                for (int counter = 0; counter < condition.length; counter++) {
                    if (condition[counter].length() == 0) {
                        valid = false;
                    }
                }
                if (!condition[1].matches("\\d+") || !condition[2].matches("\\d+") || !condition[3].matches("\\d+") || !condition[4].matches("\\d+")) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    public static String[] splitConditionList(String conditionList) {
        return conditionList.split(",");

    }

    public static Set<GrantCondition> makeGrantCondition(String conditionList) {
        String[] listOfConditions = splitConditionList(conditionList);
        Set<GrantCondition> conditionSet = null;
        for (int index = 0; index < listOfConditions.length; index++) {
            String[] condition = listOfConditions[index].split("/");
            String name = condition[0];
            String minContractDuration = condition[1];
            String maxContractDuration = condition[2];
            String minContractCost = condition[3];
            String maxContractCost = condition[4];
            GrantCondition grantCondition = new GrantCondition(Integer.parseInt(minContractDuration), Integer.parseInt(maxContractDuration), name, new BigInteger(minContractCost), new BigInteger(maxContractCost));
            conditionSet.add(grantCondition);
        }
        return conditionSet;
    }

}
