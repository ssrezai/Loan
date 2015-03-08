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

    public static void updateLoanTypeTable(String loanTypeName, int interestRate, GrantCondition grantCondition) {

        LoanType loanType = getLoanType(loanTypeName, interestRate);
        try {
            if (loanType != null) {
                int loanTypeId = loanType.getLoanTypeId();
                grantCondition.setLoanType(loanTypeId);
                addNewGrantCondition(grantCondition);

            } else {
                LoanType newLoanType = new LoanType();
                try {
                    newLoanType.setInterestRate(interestRate);
                    newLoanType.setLoanTypeName(loanTypeName);
                    addNewLoanType(newLoanType);
                    LoanType committedLoanType = getLoanType(loanTypeName, interestRate);
                    grantCondition.setLoanType(committedLoanType.getLoanTypeId());
                    addNewGrantCondition(grantCondition);
                } finally {
                    Set checkedList = getTotalSetOfConditions(newLoanType);
                    if (checkedList.size() == 0) {
                        deleteLoanTypeWithoutAnyCondition(newLoanType);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void addNewLoanType(LoanType loanType) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(loanType);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteLoanTypeWithoutAnyCondition(LoanType loanType) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            LoanType removableLoanType = (LoanType) session.get(LoanType.class, loanType.getLoanTypeId());
            session.delete(removableLoanType);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static Set<GrantCondition> getTotalSetOfConditions(LoanType loanType) {
        Set<GrantCondition> totalSetOfConditions = null;
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            LoanType loanTypeInDB=getLoanType(loanType.getLoanTypeName(),loanType.getInterestRate());
            String hql = "FROM GrantCondition G WHERE G.loanType=" + loanTypeInDB.getLoanTypeId();
            Query query = session.createQuery(hql);
            List results = query.list();
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
