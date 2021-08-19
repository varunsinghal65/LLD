package splitwise.services;

import splitwise.models.Expense;

public class ExactSplitService implements ISplitService{
    @Override
    public void populateShare(Expense expense) {
        expense.setComputedUserShares(expense.getInputUserShares());
    }
}
