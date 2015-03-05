package logic;

import java.util.Set;

/**
 * @author  Samira Rezaei
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 */
public class LoanType {
    private String loanTypeName;
    private int interestRate;
    private int loanTypeId;
    private Set grantConditions;

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public Set getGrantConditions() {
        return grantConditions;
    }

    public void setGrantConditions(Set grantConditions) {
        this.grantConditions = grantConditions;
    }
}
