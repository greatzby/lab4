package edu.ucsd.spendingtracker.view.charts;

import edu.ucsd.spendingtracker.model.Category;
import javafx.scene.Node;

import java.util.Map;

public interface IChartProvider {
    Node createChart(Map<Category, Double> data);

    String getDisplayName();
}