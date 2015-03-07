package logic;

import java.math.BigInteger;

/**
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 * @author Samira Rezaei
 */
public class GrantCondition {
    private int minContractDuration;
    private int maxContractDuration;
    private String grantConditionName;
    private BigInteger minContractCost;
    private BigInteger maxContractCost;
    private int id;
    private int loanType;

    public GrantCondition(int minContractDuration, int maxContractDuration, String grantConditionName, BigInteger minContractCost, BigInteger maxContractCost) {
        this.minContractDuration = minContractDuration;
        this.maxContractDuration = maxContractDuration;
        this.grantConditionName = grantConditionName;
        this.minContractCost = minContractCost;
        this.maxContractCost = maxContractCost;
    }

    public GrantCondition() {
    }

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }

    public int getMinContractDuration() {
        return minContractDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMinContractDuration(int minContractDuration) {
        this.minContractDuration = minContractDuration;
    }

    public int getMaxContractDuration() {
        return maxContractDuration;
    }

    public void setMaxContractDuration(int maxContractDuration) {
        this.maxContractDuration = maxContractDuration;
    }

    public String getGrantConditionName() {
        return grantConditionName;
    }

    public void setGrantConditionName(String grantConditionName) {
        this.grantConditionName = grantConditionName;
    }

    public BigInteger getMinContractCost() {
        return minContractCost;
    }

    public void setMinContractCost(BigInteger minContractCost) {
        this.minContractCost = minContractCost;
    }

    public BigInteger getMaxContractCost() {
        return maxContractCost;
    }

    public void setMaxContractCost(BigInteger maxContractCost) {
        this.maxContractCost = maxContractCost;
    }
}
