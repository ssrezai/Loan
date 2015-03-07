package crud;

import logic.LoanType;
import logic.RealCustomer;
import logic.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Created by DOTIN SCHOOL 3 on 3/3/2015.
 *
 * @author Samira Rezaei
 *         Creat Read Update Delete real_customer Table....
 */
public class RealCustomerCRUD {

    public static void AddRealCustomer(RealCustomer realCustomer) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            System.out.println(realCustomer.getNationalCode());
            System.out.println(realCustomer.getFirstName());
            System.out.println(realCustomer.getLastName());
            System.out.println(realCustomer.getCustomerID());
            session.save(realCustomer);
            tx.commit();
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
        // boolean find = true;
        try {

            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM RealCustomer");
            List<RealCustomer> realCustomerList = query.list();
            for (RealCustomer realCustomerInList : realCustomerList) {
                if (realCustomerInList.getNationalCode().equals(nationalCode)) {
                    realCustomer = realCustomerInList;
                }
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return realCustomer;
        }
    }

    public static RealCustomer getRealCustomerById(int realCustomerId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List results = null;
        RealCustomer realCustomer = null;
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM  RealCustomer R  where R.customerID='" + realCustomerId + "'");
            results = query.list();
            if (results.size() != 0) {
                Iterator iterator = results.iterator();
                realCustomer = new RealCustomer();
                realCustomer = (RealCustomer) iterator.next();
            }

            //  realCustomer = (RealCustomer) session.get(RealCustomer.class, realCustomerId);
            tx.commit();
        } finally {
            session.close();
            return realCustomer;

        }
    }

    public static List searchRealCustomer(String hql) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List results = null;
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            results = query.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return results;
        }
    }
}
