package edu.ucsd.spendingtracker.datasource;

import java.util.List;

import edu.ucsd.spendingtracker.model.Expense;

public interface IDataSource {
    List<Expense> getExpenses();

    void addExpense(Expense expense);

    void deleteExpense(int id);
}