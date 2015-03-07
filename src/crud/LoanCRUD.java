package crud;

import logic.GrantCondition;
import logic.LoanType;
import logic.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by DOTIN SCHOOL 3 on 3/5/2015.
 *
 * @author Samira Rezaei
 */
public class LoanCRUD {

    public static List getLoanTypeName() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List results = null;
        try {
            Transaction tx = session.beginTransaction();
            String hql = "SELECT L.loanTypeName FROM LoanType L";
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


    public static List getLoanTypes() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List results = null;
        try {
            Transaction tx = session.beginTransaction();
            String hql = "SELECT L FROM LoanType L";
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

//
//    public static List getAllGrantConditions(LoanType loanType) {
//        List allGrantConditions = null;
//
//        return allGrantConditions;
//    }

    public static void addNewGrantCondition(GrantCondition grantCondition) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(grantCondition);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //    public static void addLoanType(LoanType loanType, GrantCondition grantCondition) {
//       Set conditionsSet= getTotalSetOfConditions(loanType);
//        conditionsSet.add(grantCondition);
////        loanType.setGrantConditions(grantCondition);
//
//    }
    public static int getLoanTypeId(String loanTypeName, int interestRate) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List results = null;
        int id = -1;
        try {
            Transaction tx = session.beginTransaction();
            String hql = " FROM LoanType L WHERE L.loanTypeName=" + loanTypeName + "AND L.interestRate=" + interestRate;
            Query query = session.createQuery(hql);
            results = query.list();
            if (results != null) {
                Iterator iterator = results.iterator();
                LoanType loanType = (LoanType) iterator.next();
                id = loanType.getLoanTypeId();
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return id;
        }

    }

    public static LoanType getLoanType(String loanTypeName, int interestRate) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        List loanTypeList = null;
        LoanType loanType = null;
        try {
            Transaction tx = session.beginTransaction();
            String hql = " FROM LoanType L WHERE L.loanTypeName='" + loanTypeName + "' AND L.interestRate=" + interestRate;
            Query query = session.createQuery(hql);
            loanTypeList = query.list();
            Iterator iterator = loanTypeList.iterator();
            loanType = (LoanType) iterator.next();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return loanType;
        }

    }

    public static void addLoanType(String loanTypeName, int interestRate, GrantCondition grantCondition) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        LoanType loanType = getLoanType(loanTypeName, interestRate);
        Set grantConditions = null;
        try {

            if (loanType != null) {
                Transaction tx = session.beginTransaction();
                int loanTypeId = loanType.getLoanTypeId();
                grantConditions = getTotalSetOfConditions(loanType);
                grantConditions.add(grantCondition);
                loanType.setGrantConditions(grantConditions);
                grantCondition.setLoanType(loanTypeId);
                session.save(grantCondition);
                tx.commit();

            } else {
                LoanType newLoanType = new LoanType();


                try {
                    Transaction tx = session.beginTransaction();
                    newLoanType.setInterestRate(interestRate);
                    newLoanType.setLoanTypeName(loanTypeName);
                    grantConditions = new HashSet<GrantCondition>();
                    grantConditions.add(grantCondition);
                     newLoanType.setGrantConditions(grantConditions);
                    session.save(newLoanType);
                    //session.getTransaction().commit();
                    tx.commit();
                  //  session.beginTransaction();
                    LoanType committedLoanType = getLoanType(loanTypeName, interestRate);
                    grantCondition.setLoanType(committedLoanType.getLoanTypeId());
                    session.save(grantCondition);
                   // session.getTransaction().commit();
                } finally {
                    Set checkedList = getTotalSetOfConditions(newLoanType);
                    if (checkedList.size() == 0) {
                        Transaction tx = session.beginTransaction();
                        tx.begin();
                        LoanType removableLoanType = (LoanType) session.get(LoanType.class, newLoanType.getLoanTypeId());
                        session.delete(removableLoanType);
                        tx.commit();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
    }

    public static Set getTotalSetOfConditions(LoanType loanType) {
        Set<GrantCondition> totalSetOfConditions = null;
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            String hql = "FROM GrantCondition G WHERE G.loanType=" + loanType.getLoanTypeId();
            Query query = session.createQuery(hql);
            List results = query.list();
            // Set<Foo> foo = new HashSet<>(myList);
            totalSetOfConditions = new HashSet<GrantCondition>(results);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return totalSetOfConditions;
        }


    }
}
