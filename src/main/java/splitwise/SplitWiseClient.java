package splitwise;

import splitwise.manager.ExpenseManager;
import splitwise.models.SplitType;
import splitwise.models.UserShare;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class SplitWiseClient {

    private static final ExpenseManager expenseManager = new ExpenseManager();

    /**
     *
     * Question picked from
     * https://workat.tech/machine-coding/editorial/how-to-design-splitwise-machine-coding-ayvnfo1tfst6
     * https://github.com/prasadgujar/low-level-design-primer/blob/master/solutions.md
     * Sample input to the program :
     *
     * EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
     * EXPENSE u1 1250 2 u2 u3 EXACT 370 880
     * EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
     *
     * Show
     * u1 owes u4: 230.0
     * u2 owes u1: 620.0
     * u2 owes u4: 240.0
     * u3 owes u1: 1130.0
     * u3 owes u4: 240.0
     *
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String input = scanner.nextLine();
            String[] commands = input.split(" ");

            //read cmd line input
            String action = commands[0];

            //launch expense manager based on action
            if (action.equalsIgnoreCase("EXPENSE")) {
                readExpenseInputAndAddExpense(commands);
            } else if (action.equalsIgnoreCase("SHOW") && commands.length == 1) {
                printAllBalances();
            } else if (action.equalsIgnoreCase("SHOW")) {
                String userId = commands[1];
                HashMap<String, Double> owedTo = expenseManager.queryBalanceSheetByUserId(userId);
                if (owedTo == null) {
                    System.out.println("No balances");
                } else {
                    printUserBalance(userId, owedTo);
                }
            } else if(action.equalsIgnoreCase("STATEMENT") && commands.length != 1) {
                String userId = commands[1];
                expenseManager.getPassBook().forEach((expense)->{
                    if (expense.getUserIds().contains(userId) || userId.equalsIgnoreCase(expense.getPaidByUserId())) {
                        System.out.println(expense);
                    }
                });
            }
        }
    }

    private static void printAllBalances() {
        HashMap<String, HashMap<String, Double>> owedTo = expenseManager.queryBalanceSheetForAll();
        if (owedTo.size() == 0) {
            System.out.println("No balances");
        }
        owedTo.keySet().forEach((owedBy) -> {
            if (owedTo.get(owedBy) != null) {
                printUserBalance(owedBy, owedTo.get(owedBy));
            }
        });
    }

    private static void printUserBalance(String owedByUserId, HashMap<String, Double> owesToMap) {
        AtomicReference<Boolean> noBalance = new AtomicReference<>(true);
        owesToMap
                .keySet()
                .stream()
                .filter(key -> owesToMap.get(key) != 0.0)
                .forEach((key) -> {
                    System.out.println(owedByUserId + " " + "owes" + " " + key + ":" + " " + owesToMap.get(key));
                    noBalance.set(false);
                });
        if (noBalance.get()) {
            System.out.println("No balances for user id: " + owedByUserId);
        }
    }

    private static void readExpenseInputAndAddExpense(String[] commands) {
        // read
        String paidByUserId = commands[1];
        double amount = Double.parseDouble(commands[2]);
        int numberOfUsers = Integer.parseInt(commands[3]);

        List<String> userIds = new ArrayList<>();
        int i = 0;
        for (i = 1; i <= numberOfUsers; i++) {
            userIds.add(commands[3 + i]);
        }

        SplitType splitType = SplitType.valueOf(commands[4 + numberOfUsers]);

        List<UserShare> inputUserShares = new ArrayList<>();
        if (splitType != SplitType.EQUAL) {
            for (int j = 1; j <= numberOfUsers; j++) {
                double share = Double.parseDouble(commands[4 + numberOfUsers + j]);
                inputUserShares.add(new UserShare(share, userIds.get(j - 1)));
            }
        }

        //launch action
        expenseManager.addExpense(paidByUserId, splitType, amount, userIds, inputUserShares);
        System.out.println("Expense added successfully");
    }
}
