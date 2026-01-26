package edu.ucsd.spendingtracker.presenter;

import edu.ucsd.spendingtracker.model.Expense;
import edu.ucsd.spendingtracker.model.Model;
import edu.ucsd.spendingtracker.view.SpendingView;

public class SpendingPresenter extends AbstractPresenter<SpendingView> {
    private Runnable onShowSummary;

    public SpendingPresenter(Model model, SpendingView view) {
        super(model, view);

        this.view.getSummaryButton().setOnAction(e -> {
            if (onShowSummary != null) {
                onShowSummary.run();
            }
        });

        this.view.setOnDelete(id -> {
            this.model.deleteExpense(id); 
            updateView();               
        });

        this.view.getOpenModalButton().setOnAction(e -> {
            this.view.showAddExpenseModal(newExpense -> {
                this.model.addExpense(newExpense); 
                updateView();                      
            });
        });

        updateView();
    }

    public void setOnShowSummary(Runnable action) {
        this.onShowSummary = action;
    }

    @Override
    public String getViewTitle() {
        return "Expenses";
    }

    @Override
    public void updateView() {
        this.view.clearList();

        for (Expense e : this.model.getExpenses()) {
            this.view.addExpenseRow(
                    e.getId(),
                    e.getName(),
                    e.getCategory().name(),
                    e.getCategory().color,
                    e.getAmount()
            );
        }
    }
}