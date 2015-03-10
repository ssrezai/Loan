package logic;


import crud.LoanCRUD;
import crud.LoanFileCRUD;
import crud.RealCustomerCRUD;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;

/**
 * Created by DOTIN SCHOOL 3 on 3/2/2015.
 *
 * @author Samira Rezaei
 */
public class MAIN {
    public static void main(String[] args) {

//        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//
//        //creating session object
//        Session session=factory.openSession();
//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//
//        //creating transaction object
//        Transaction transaction = session.beginTransaction();
//
//        RealCustomer realCustomer = new RealCustomer();
//        realCustomer.setFirstName("سارا");
//        realCustomer.setLastName("AhmaD");
//        realCustomer.setBirthDate("1360");
//        realCustomer.setFatherName("Alireza");
//        realCustomer.setNationalCode("2");
//
//        //session.save(customer);
//        session.save(realCustomer);
//        session.getTransaction().commit();
//
//        session.close();
//        SessionFactoryUtil.shutdown();
//        System.out.println("Done..");

//        RealCustomer realCustomer = new RealCustomer();
//
//        realCustomer.setCustomerID("19");
//        String hql = RealCustomerLogic.buildQuery(realCustomer);
//        RealCustomerCRUD.searchRealCustomer(hql);

        //   LoanCRUD.getLoanTypeName();

//        Session session = SessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        RealCustomer realCustomer = new RealCustomer();
//        realCustomer.setFirstName("ALI");
//        realCustomer.setLastName("AHMAD");
//        realCustomer.setBirthDate("1369");
//        realCustomer.setFatherName("Alireza");
//        realCustomer.setNationalCode("89785");
//        session.save(realCustomer);
//        tx.commit();
//       // tx.begin();
//        tx = session.beginTransaction();
//        LoanType loanType =
//                (LoanType) session.get(LoanType.class, 1);
//        tx.commit();
//
//        LoanFileLogic.makeNewLoanFile(realCustomer, loanType, 14, new BigInteger("12"));
//
//        session.close();
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setFirstName("ali");
        realCustomer.setLastName("ali");
        realCustomer.setFatherName("ali");
        realCustomer.setNationalCode("12345698");
        realCustomer.setBirthDate("1256");

        RealCustomerCRUD.AddRealCustomer(realCustomer);
        realCustomer = RealCustomerCRUD.getRealCustomerByNationalCode("12345698");
        realCustomer.setFatherName("456");
        RealCustomerCRUD.updateRealCustomerTable(realCustomer);


    }


    public static boolean validateLoanType(String interestRate) {
        boolean valid = false;
//        loanTypeName == null || loanTypeName == "" || interestRate == null || interestRate == "" ||
        if (interestRate.matches("\\d+(\\.\\d+)?")) {
            valid = true;
        }
        return valid;
    }
}

