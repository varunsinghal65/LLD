package splitwise.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Expense {

    private String paidByUserId;
    private SplitType splitType;
    private List<String> userIds;
    //User share means that userId of user share owes the share amount to the paidByUserId
    private List<UserShare> inputUserShares;
    private List<UserShare> computedUserShares;
    private double amount;

    public boolean isValid() {
        return true;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<UserShare> getComputedUserShares() {
        return computedUserShares;
    }

    public void setComputedUserShares(List<UserShare> computedUserShares) {
        this.computedUserShares = computedUserShares;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(String paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public List<UserShare> getInputUserShares() {
        return inputUserShares;
    }

    public void setInputUserShares(List<UserShare> inputUserShares) {
        this.inputUserShares = inputUserShares;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "paidByUserId='" + paidByUserId + '\'' +
                ", splitType=" + splitType +
                ", userIds=" + userIds +
                ", inputUserShares=" + inputUserShares +
                ", computedUserShares=" + computedUserShares +
                ", amount=" + amount +
                '}';
    }

    public Expense(String paidByUserId, SplitType splitType,
                   double amount, List<String> userIds,
                   List<UserShare> inputUserShares) {
        this.paidByUserId = paidByUserId;
        this.splitType = splitType;
        this.amount = amount;
        this.userIds = userIds;
        this.inputUserShares = inputUserShares;
        computedUserShares = new ArrayList<>();
    }

}
