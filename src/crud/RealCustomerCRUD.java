package crud;

import logic.LoanType;
import logic.RealCustomer;
import logic.SessionFactoryUtil;
import org.apache.log4j.Logger;
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
    static final Logger logger = Logger.getLogger(RealCustomerCRUD.class);

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
            logger.info("ADD NEW REAL CUSTOMER TO REAL_CUSTOMER TABLE AND NEW CUSTOMER TO CUSTOMER TABLE..");
        }
    }


    public static RealCustomer getRealCustomerByNationalCode(String nationalCode) {
        RealCustomer realCustomer = null;
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            //    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM  RealCustomer R  where R.nationalCode=" + nationalCode);
            List<RealCustomer> realCustomerList = query.list();
            for (RealCustomer realCustomerInList : realCustomerList) {
                if (realCustomerInList.getNationalCode().equals(nationalCode)) {
                    realCustomer = realCustomerInList;
                }
            }
            //   tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            logger.info("READ ONE REAL CUSTOMER BY NATIONAL CODE..");
            return realCustomer;
        }
    }

    public static RealCustomer getRealCustomerById(int realCustomerId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List results = null;
        RealCustomer realCustomer = null;
        try {
            // Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM  RealCustomer R  where R.customerID='" + realCustomerId + "'");
            results = query.list();
            if (results.size() != 0) {
                Iterator iterator = results.iterator();
                realCustomer = new RealCustomer();
                realCustomer = (RealCustomer) iterator.next();
            }
            //  tx.commit();
        } finally {
            session.close();
            logger.info("READ ONE REAL CUSTOMER BY CUSTOMER ID..");
            return realCustomer;

        }
    }

    public static List searchRealCustomer(String hql) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List results = null;
        try {
            //Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            results = query.list();
            // tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            logger.info("READ REAL CUSTOMER  ..");
        }
        return results;
    }

    public static void deleteRealCustomer(String id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            RealCustomer realCustomer = (RealCustomer) session.get(RealCustomer.class, id);
            session.delete(realCustomer);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            session.close();
            logger.info("DELETE ONE REAL CUSTOMER..");
        }

    }

    public static void updateRealCustomerTable(RealCustomer realCustomer) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        try {
            Transaction tx = session.beginTransaction();
            String hql = "update RealCustomer set firstName = :newFirstName, lastName=:newLastName,fatherName=:newFatherName,nationalCode=:newNationalCode,birthDate=:newBirthDate where customerID = :id";
            Query query = session.createQuery(hql);
            query.setString("id", realCustomer.getCustomerID());
            query.setString("newFirstName", realCustomer.getFirstName());
            query.setString("newLastName", realCustomer.getLastName());
            query.setString("newFatherName", realCustomer.getFatherName());
            query.setString("newNationalCode", realCustomer.getNationalCode());
            query.setString("newBirthDate", realCustomer.getBirthDate());
            query.executeUpdate();
            tx.commit();
        } finally {
            session.close();
        }

    }
}
