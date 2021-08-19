package splitwise.models;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(String paidByUserId, SplitType splitType, double amount, List<String> userIds,
                        List<UserShare> inputUserShares) {
        super(paidByUserId, splitType, amount, userIds, inputUserShares);
    }

    @Override
    public boolean isValid() {
        double totalShareAmount =
                getInputUserShares().stream().mapToDouble(UserShare::getAmount).sum();
        return totalShareAmount == getAmount();
    }
}
