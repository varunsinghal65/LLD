package splitwise.models;

import java.util.List;

public class ExpenseFactory {

    public static Expense getInstance(String paidByUserId, SplitType splitType,
                                      double amount, List<String> userIds,
                                      List<UserShare> inputUserShares) {
        switch (splitType) {
            case EQUAL:
                return new EqualExpense(paidByUserId, splitType, amount, userIds, inputUserShares);
            case PERCENT:
                return new PercentageExpense(paidByUserId, splitType, amount, userIds, inputUserShares);
            case EXACT:
                return new ExactExpense(paidByUserId, splitType, amount, userIds, inputUserShares);
            default:
                throw new IllegalArgumentException("Error: invalid split type received.");
        }
    }

}
