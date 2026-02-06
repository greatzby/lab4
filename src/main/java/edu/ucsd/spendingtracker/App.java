package edu.ucsd.spendingtracker;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.spendingtracker.datasource.SqlDataSource;
import edu.ucsd.spendingtracker.model.Model;
import edu.ucsd.spendingtracker.presenter.PresenterManager;
import edu.ucsd.spendingtracker.presenter.SpendingPresenter;
import edu.ucsd.spendingtracker.presenter.SummaryPresenter;
import edu.ucsd.spendingtracker.repository.ExpenseRepository;
import edu.ucsd.spendingtracker.view.SpendingView;
import edu.ucsd.spendingtracker.view.SummaryView;
import edu.ucsd.spendingtracker.view.charts.BarChartProvider;
import edu.ucsd.spendingtracker.view.charts.PieChartProvider;
import edu.ucsd.spendingtracker.view.charts.IChartProvider;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        SqlDataSource dataSource = new SqlDataSource();
        ExpenseRepository repository = new ExpenseRepository(dataSource);

        Model sharedModel = new Model(repository);

        SpendingView spendingView = new SpendingView();
        SummaryView summaryView = new SummaryView();
        

        List<IChartProvider> chartProviders = new ArrayList<>();
        chartProviders.add(new BarChartProvider());
        chartProviders.add(new PieChartProvider());

        SpendingPresenter listPresenter = new SpendingPresenter(sharedModel, spendingView);
        SummaryPresenter summaryPresenter = new SummaryPresenter(sharedModel, summaryView, chartProviders);

        PresenterManager manager = new PresenterManager();
        manager.defineInteractions(primaryStage, "Spending Tracker", listPresenter, summaryPresenter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}