package splitwise.models;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(String paidByUserId, SplitType splitType, double amount, List<String> userIds,
                        List<UserShare> inputUserShares) {
        super(paidByUserId, splitType, amount, userIds, inputUserShares);
    }

}
