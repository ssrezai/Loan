package logic;

import crud.LoanCRUD;
import crud.LoanFileCRUD;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by DOTIN SCHOOL 3 on 3/7/2015.
 *
 * @author Samira Rezaei
 */
public class LoanFileLogic {

    public static boolean checkContract(LoanType loanType, int contractDuration, BigInteger contractCost) {
        boolean validate = false;
        Set totalConditions = LoanCRUD.getTotalSetOfConditions(loanType);
        Iterator iterator = totalConditions.iterator();
        while (iterator.hasNext() && !validate) {
            GrantCondition grantCondition = new GrantCondition();
            grantCondition = (GrantCondition) iterator.next();
            int minContractDuration = grantCondition.getMinContractDuration();
            int maxContractDuration = grantCondition.getMaxContractDuration();
            BigInteger minContractCost = grantCondition.getMinContractCost();
            BigInteger maxContractCost = grantCondition.getMaxContractCost();
            if (contractDuration < maxContractDuration && contractDuration > minContractDuration
                    && contractCost.compareTo(maxContractCost) <0  && contractCost.compareTo(minContractCost) >0 ) {
                validate = true;
            }
        }
        return validate;
    }

    public static void makeNewLoanFile(RealCustomer realCustomer, LoanType loanType, int contractDuration, BigInteger contractCost) {
        if (checkContract(loanType, contractDuration, contractCost)) {
            LoanFileCRUD.addNewLoanFile(realCustomer, loanType, contractDuration, contractCost);
        }

    }

}
