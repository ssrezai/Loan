package logic;

/**
 * @author  Samira Rezaei
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 */
public class LoanType {
    private String loanTypeName;
    private int interestRate;

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
}
