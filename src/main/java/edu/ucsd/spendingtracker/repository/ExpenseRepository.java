package edu.ucsd.spendingtracker.repository;

import java.util.List;

import edu.ucsd.spendingtracker.datasource.IDataSource;
import edu.ucsd.spendingtracker.model.Expense;

public class ExpenseRepository {
    private IDataSource dataSource;

    public ExpenseRepository(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addExpense(Expense expense) {
        dataSource.addExpense(expense);
    }

    public void deleteExpense(int id) {
        dataSource.deleteExpense(id);
    }

    public List<Expense> getExpenses() {
        return dataSource.getExpenses();
    }

    public double getTotal() {
        double total = 0;
        for (Expense expense : dataSource.getExpenses()) {
            total += expense.getAmount();
        }
        return total;
    }
}