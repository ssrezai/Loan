package crud;

import logic.LoanFile;
import logic.LoanType;
import logic.RealCustomer;
import logic.SessionFactoryUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigInteger;

/**
 * Created by DOTIN SCHOOL 3 on 3/7/2015.
 * Create Read Update Delete loan_file Table....
 * @author Samira Rezaei
 */
public class LoanFileCRUD {
    static final Logger logger = Logger.getLogger(LoanFileCRUD.class);

    public static void addNewLoanFile(RealCustomer realCustomer, LoanType loanType, int contractDuration, BigInteger contractCost) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            LoanFile loanFile = new LoanFile(realCustomer, loanType, contractDuration, contractCost);
            session.save(loanFile);
            tx.commit();
        } finally {
            session.close();
            logger.info("ADD NEW LOAN FILE TO LOAN_FILE TABLE");
        }
    }

}

