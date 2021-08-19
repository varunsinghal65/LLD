package splitwise.models;

public class UserShare {
    private double amount;
    private String userId;

    @Override
    public String toString() {
        return "UserShare{" +
                "amount=" + amount +
                ", owedByUserId='" + userId + '\'' +
                '}';
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserShare(double amount, String user) {
        this.amount = amount;
        this.userId = user;
    }
}
