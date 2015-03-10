package logic;

import crud.LoanCRUD;
import logic.exception.EmptyGrantConditionListException;
import logic.exception.InvalidGrantConditionException;
import logic.exception.LoanTypeInfoException;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DOTIN SCHOOL 3 on 3/5/2015.
 *
 * @author Samira Rezaei
 *         Basic logic for Loan function is checked here....
 */
public class LoanLogic {
    static final Logger logger = Logger.getLogger(LoanLogic.class);


//    public static List returnLoanTypeName() {
//        List loanTypeNames = null;
//        loanTypeNames = LoanCRUD.getLoanTypeName();
//        return loanTypeNames;
//    }

    public static List<LoanType> returnLoanType() {
        List loanTypes = null;
        loanTypes = LoanCRUD.getLoanTypes();
        return loanTypes;
    }

    public static boolean validateLoanType(String loanTypeName, String interestRate) throws LoanTypeInfoException {
        boolean valid = true;
        if (loanTypeName == null || loanTypeName.equals("") || interestRate == null || interestRate.equals("") || !interestRate.matches("\\d{1,2}(\\.\\d{1,2})?")) {
            valid = false;
            logger.warn("LoanTypeInfoException..");
            throw new LoanTypeInfoException("LoanTypeInfoException");
        }
        return valid;
    }

    public static boolean validateGrantCondition(String minContractCost, String maxContractCost, String minContractDuration, String maxContractDuration, String name) throws InvalidGrantConditionException{
        boolean valid = true;
        if (minContractCost == null || minContractCost.equals("") || maxContractCost == null || maxContractCost.equals("") ||
                name == null || name.equals("") || minContractDuration.equals("") || minContractDuration == null || maxContractDuration == null
                || !minContractDuration.matches("\\d+") || !maxContractDuration.matches("\\d+")
                || !maxContractCost.matches("\\d+") || !minContractCost.matches("\\d+")) {
            valid = false;
            throw new InvalidGrantConditionException("InvalidGrantConditionException");
        }
        return valid;
    }


    public static void addLoanTypeWithGrantCondition(String loanTypeName, String interestRate, Set conditionList) {
        LoanType loanType = new LoanType();
        loanType.setLoanTypeName(loanTypeName);
        loanType.setInterestRate(Integer.parseInt(interestRate));
        LoanCRUD.updateLoanTypeTable(loanTypeName, Integer.parseInt(interestRate), conditionList);
    }

//    public static Set<GrantCondition> getTotalCondition(LoanType loanType) {
//        return LoanCRUD.getTotalSetOfConditions(loanType);
//    }

    public static boolean validateGrantConditionList(String conditionList) throws InvalidGrantConditionException, EmptyGrantConditionListException {
        boolean valid = true;
        String[] listOfConditions = splitConditionList(conditionList);
        if (listOfConditions.length == 0) {
            logger.warn("EmptyGrantConditionListException..");
            throw new EmptyGrantConditionListException("Empty list...");
        } else {
            for (String listOfCondition : listOfConditions) {
                if (!valid) {
                    break;
                } else {
                    String[] condition = listOfCondition.split("/");
                    if (validateGrantCondition(condition[3], condition[4], condition[1], condition[2], condition[0])) {
                    }


//                    for (int counter = 0; counter < condition.length; counter++) {
//                        if (condition[counter].length() == 0) {
//                            valid = false;
//                            logger.warn("InvalidGrantConditionException..");
//                            throw new InvalidGrantConditionException("");
//                        }
//                    }
//                    if (!condition[1].matches("\\d+") || !condition[2].matches("\\d+") || !condition[3].matches("\\d+") || !condition[4].matches("\\d+")) {
//                        valid = false;
//                        logger.warn("InvalidGrantConditionException..");
//                        throw new InvalidGrantConditionException("");
//                    }
                }
            }
        }
        return valid;
    }

    public static String[] splitConditionList(String conditionList) {
        return conditionList.split(",");
    }

    public static Set makeGrantCondition(String conditionList) {
        String[] listOfConditions = splitConditionList(conditionList);
        Set conditionSet = new HashSet();
        for (String listOfCondition : listOfConditions) {
            String[] condition = listOfCondition.split("/");
            String name = condition[0];
            String minContractDuration = condition[1];
            String maxContractDuration = condition[2];
            String minContractCost = condition[3];
            String maxContractCost = condition[4];
            GrantCondition grantCondition = new GrantCondition(Integer.parseInt(minContractDuration), Integer.parseInt(maxContractDuration), name, new BigInteger(minContractCost), new BigInteger(maxContractCost));
            conditionSet.add(grantCondition);
        }
        logger.info("Make Grant Condition List..");
        return conditionSet;
    }

}
