package edu.ucsd.spendingtracker.view.charts;

import edu.ucsd.spendingtracker.model.Category;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class BarChartProvider implements IChartProvider {

    @Override
    public Node createChart(Map<Category, Double> data) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        data.forEach((cat, sum) -> {
            series.getData().add(new XYChart.Data<>(cat.name(), sum));
        });

        chart.getData().add(series);
        for (XYChart.Data<String, Number> entry : series.getData()) {
            String color = Category.valueOf(entry.getXValue()).color;
            Node bar = entry.getNode();
            if (bar != null)
                bar.setStyle("-fx-bar-fill: " + color + ";");
        }
        chart.setLegendVisible(false);

        return chart;
    }

    @Override
    public String getDisplayName() {
        return "Bar Chart";
    }
}