package logic;

/**
 * Created by DOTIN SCHOOL 3 on 3/1/2015.
 */
public class GrantCondition {
    private int minContractDuration;
    private int maxContractDuration;
    private String grantConditionName;
    private int minContractCost;
    private int maxContractCost;

    public int getMinContractDuration() {
        return minContractDuration;
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

    public int getMinContractCost() {
        return minContractCost;
    }

    public void setMinContractCost(int minContractCost) {
        this.minContractCost = minContractCost;
    }

    public int getMaxContractCost() {
        return maxContractCost;
    }

    public void setMaxContractCost(int maxContractCost) {
        this.maxContractCost = maxContractCost;
    }
}
