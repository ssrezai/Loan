package logic;

import java.math.BigInteger;

/**
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 */
public class LoanFile {
    private Customer customer;
    private LoanType loanType;
    private int id;
    private int contractDuration;
    private BigInteger contractCost;

    public LoanFile(Customer customer, LoanType loanType, int contractDuration, BigInteger contractCost) {
        this.customer = customer;
        this.loanType = loanType;
        this.contractDuration = contractDuration;
        this.contractCost = contractCost;
    }

    public LoanFile() {
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public BigInteger getContractCost() {
        return contractCost;
    }

    public void setContractCost(BigInteger contractCost) {
        this.contractCost = contractCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
