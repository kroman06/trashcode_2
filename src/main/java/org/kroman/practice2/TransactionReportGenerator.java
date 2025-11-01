package org.kroman.practice2;

import java.util.List;

public abstract class TransactionReportGenerator {
    private static final double VISUALIZATION_UNIT = -1000.0; // 1k = *

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Total balance: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Txn count from " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("Top 10 expenses:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printMinMaxExpenseReport(Transaction min, Transaction max, java.time.LocalDate start, java.time.LocalDate end) {
        System.out.println("\n=== Report during " + start + " till " + end + " ===");
        if (max != null) System.out.println("Biggest expense: " + max.getDescription() + " (" + max.getAmount() + ")");
        else System.out.println("Biggest expense wasn't found.");

        if (min != null) System.out.println("Smallest expense: " + min.getDescription() + " (" + min.getAmount() + ")");
        else  System.out.println("Smallest expense wasn't found.");
    }

    public static void printCategoryExpenseSummary(java.util.Map<String, Double> categorySummary) {
        System.out.println("\n=== Expense category summary ===");
        categorySummary.entrySet().stream()
                .sorted(java.util.Map.Entry.comparingByValue())
                .forEach(entry -> System.out.printf("Category: %-20s | Amount: %.2f%n", entry.getKey(), entry.getValue()));
    }

    public static void printMonthlyExpenseSummary(java.util.Map<String, Double> monthlySummary) {
        System.out.println("\n=== Monthly expenses report ===");

        monthlySummary.entrySet().stream()
                .sorted(java.util.Map.Entry.comparingByKey())
                .forEach(entry -> {
                    double totalExpenses = entry.getValue();
                    long starsCount = Math.round(totalExpenses / VISUALIZATION_UNIT);
                    StringBuilder stars = new StringBuilder();
                    for (int i = 0; i < starsCount; i++) {
                        stars.append('*');
                    }
                    System.out.printf("Month: %-10s | Amount: %-10.2f | %s%n", entry.getKey(), totalExpenses, stars);
                });
    }
}