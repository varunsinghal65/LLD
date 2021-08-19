package splitwise.manager;

import splitwise.models.*;
import splitwise.services.ISplitService;
import splitwise.services.SplitServiceFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseManager {

    //balance sheet
    private final HashMap<String, HashMap<String, Double>> owedTo;
    //expense sheet
    private final List<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        this.owedTo = new HashMap<>();
    }

    public void addExpense(String paidByUserId, SplitType splitType,
                           double amount, List<String> userIds,
                           List<UserShare> inputUserShares) {
        //create expense
        Expense expense = ExpenseFactory.getInstance(paidByUserId, splitType, amount, userIds, inputUserShares);
        // check for expense validity
        if (!expense.isValid())
            throw new IllegalArgumentException("ERROR: Invalid splitting criteria received");
        //split the user shares in the expense
        ISplitService splitService = SplitServiceFactory.getInstance(expense.getSplitType());
        splitService.populateShare(expense);
        // read the computed shares and update the balance sheet
        List<UserShare> computedShares = expense.getComputedUserShares();
        //update the balance sheet with the computedShares
        updateOwedToMap(paidByUserId, computedShares);
        expenses.add(expense);
    }

    private void updateOwedToMap(String paidByUserId, List<UserShare> computedShares) {
        for (UserShare share : computedShares) {
            if (share.getUserId().equals(paidByUserId)) {
                continue;
            }
            String owedByUserId = share.getUserId();
            double amountOwed = share.getAmount();
            owedTo.computeIfAbsent(owedByUserId, key -> new HashMap<>());
            double existingAmountOwed = owedTo.get(owedByUserId).getOrDefault(paidByUserId, 0.0);
            owedTo.get(owedByUserId).put(paidByUserId, existingAmountOwed + amountOwed);

            //basically, if we have a scenario, where u1 owes u2 and u2 also owes u1
            //then as per requirement for our system, we can examine, who owes the higher amount
            //between the 2, and then remove one of the mappings.
            String u1Id = paidByUserId;
            String u2Id = owedByUserId;
            if (owedTo.get(u1Id) != null &&
                    owedTo.get(u1Id).getOrDefault(u2Id, 0.0) != 0.0 &&
                    owedTo.get(u2Id) != null &&
                    owedTo.get(u2Id).getOrDefault(u1Id, 0.0) != 0.0) {
                settleDebts(u1Id, u2Id);
            }
        }
    }

    private void settleDebts(String u1Id, String u2Id) {
        double oneOwesTwoAmount = owedTo.get(u1Id).get(u2Id); // 500
        double twoOwesOneAmount = owedTo.get(u2Id).get(u1Id); // 250
        if (oneOwesTwoAmount > twoOwesOneAmount) {
            owedTo.get(u1Id).put(u2Id, oneOwesTwoAmount - twoOwesOneAmount);
            owedTo.get(u2Id).put(u1Id, 0.0);
        } else if (oneOwesTwoAmount < twoOwesOneAmount) {
            owedTo.get(u2Id).put(u1Id, twoOwesOneAmount - oneOwesTwoAmount);
            owedTo.get(u1Id).put(u2Id, 0.0);
        } else {
            owedTo.get(u2Id).put(u1Id, 0.0);
            owedTo.get(u1Id).put(u2Id, 0.0);
        }
    }

    public HashMap<String, HashMap<String, Double>> queryBalanceSheetForAll() {
        return owedTo;
    }

    public HashMap<String, Double> queryBalanceSheetByUserId(String userId) {
        return owedTo.getOrDefault(userId, null);
    }

    public List<Expense> getPassBook() {
        return expenses;
    }

}
