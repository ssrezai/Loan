package logic;

import crud.LoanCRUD;

import java.util.List;

/**
 * Created by DOTIN SCHOOL 3 on 3/5/2015.
 *
 */
public class LoanLogic {

    public static List returnLoanTypeName()
    {
        List loanTypeNames=null;
        loanTypeNames= LoanCRUD.getLoanTypeName();
        return loanTypeNames;
    }

}
