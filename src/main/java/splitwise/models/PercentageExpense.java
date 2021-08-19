package splitwise.models;

import java.util.List;

public class PercentageExpense extends Expense {

    public PercentageExpense(String paidByUserId, SplitType splitType, double amount, List<String> userIds,
                             List<UserShare> inputUserShares) {
        super(paidByUserId, splitType, amount, userIds, inputUserShares);
    }

    @Override
    public boolean isValid() {
        return getInputUserShares().stream().mapToDouble(UserShare::getAmount).sum() == 100;
    }
}
