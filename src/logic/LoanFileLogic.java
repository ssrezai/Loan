package logic;

import crud.LoanCRUD;
import crud.LoanFileCRUD;
import logic.exception.MismatchConditionException;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by DOTIN SCHOOL 3 on 3/7/2015.
 *
 * @author Samira Rezaei
 */
public class LoanFileLogic {
    static final Logger logger = Logger.getLogger(LoanFileLogic.class);


    public static boolean checkContract(LoanType loanType, int contractDuration, BigInteger contractCost) throws MismatchConditionException {
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
                    && contractCost.compareTo(maxContractCost) < 0 && contractCost.compareTo(minContractCost) > 0) {
                validate = true;
            }
        }
        if (!validate) {
            logger.warn("Mismatch Condition Exception..There is not any condition that match with user defined condition");
            throw new MismatchConditionException("MismatchConditionException");
        }
        return validate;
    }

    public static void makeNewLoanFile(RealCustomer realCustomer, int id, int contractDuration, BigInteger contractCost) throws MismatchConditionException {
        LoanType loanType = LoanCRUD.getLoanTypeId(id);
        if (checkContract(loanType, contractDuration, contractCost)) {
            LoanFileCRUD.addNewLoanFile(realCustomer, loanType, contractDuration, contractCost);
        }
    }

}
