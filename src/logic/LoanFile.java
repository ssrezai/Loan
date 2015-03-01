package logic;

/**
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 */
public class LoanFile {
    private Customer customer;
    private LoanType loanType;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
