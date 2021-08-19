package splitwise.services;

import splitwise.models.Expense;
import splitwise.models.UserShare;

public class EqualSplitService implements ISplitService{
    @Override
    public void populateShare(Expense expense) {
        double eachUserShare = expense.getAmount()/ expense.getUserIds().size();
        expense.getUserIds().forEach((userId)->{
            expense.getComputedUserShares().add(new UserShare(eachUserShare, userId));
        });
    }
}
