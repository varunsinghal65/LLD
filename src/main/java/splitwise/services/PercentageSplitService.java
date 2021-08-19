package splitwise.services;

import splitwise.models.Expense;
import splitwise.models.UserShare;

public class PercentageSplitService implements ISplitService {
    @Override
    public void populateShare(Expense expense) {
        expense.getInputUserShares().forEach((userShare) -> {
            expense.getComputedUserShares().add(new UserShare(
                    (expense.getAmount() / 100) * userShare.getAmount(),
                    userShare.getUserId())
            );
        });
    }
}
