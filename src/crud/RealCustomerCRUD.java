package crud;

import logic.DuplicateCustomerException;
import logic.RealCustomer;
import logic.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by DOTIN SCHOOL 3 on 3/3/2015.
 */
public class RealCustomerCRUD {

    public static void AddRealCustomer(RealCustomer realCustomer) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx =  session.beginTransaction();
            System.out.println(realCustomer.getNationalCode());
            System.out.println(realCustomer.getFirstName());
            System.out.println(realCustomer.getLastName());
            System.out.println(realCustomer.getCustomerID());
            session.save(realCustomer);
            tx.commit();
            System.out.println("Done..Add new Real customer to DB");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    public static void checkNationalCode(RealCustomer realCustomer) {
//        try {
//            Session session = SessionFactoryUtil.getSessionFactory().openSession();
//            Transaction tx = session.beginTransaction();
//            System.out.println("start search...");
//
//            session.save(realCustomer);
//            tx.commit();
//            session.close();
//            System.out.println("Done..Add new Real customer to DB");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static RealCustomer getRealCustomerByNationalCode(String nationalCode) {
        RealCustomer realCustomer = null;
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        boolean find = true;
        try {

            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM RealCustomer");
            List<RealCustomer> realCustomerList = query.list();
            for (RealCustomer realCustomerInList : realCustomerList) {
                if (realCustomerInList.getNationalCode().equals(nationalCode)) {
                    realCustomer = realCustomerInList;
                }
                System.out.println("NC:" + realCustomerInList.getNationalCode());
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return realCustomer;
        }
    }
}
