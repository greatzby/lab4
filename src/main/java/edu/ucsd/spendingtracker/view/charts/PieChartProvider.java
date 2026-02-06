package edu.ucsd.spendingtracker.view.charts;

import edu.ucsd.spendingtracker.model.Category;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

import java.util.Map;

public class PieChartProvider implements IChartProvider {

    @Override
    public Node createChart(Map<Category, Double> data) {
        PieChart chart = new PieChart();
        chart.setLegendVisible(false); // 你现在 UI 里已经有标签/下拉框了，legend 可关掉
        chart.setLabelsVisible(true);

        // 把每个 category 的 total 转成一个 slice
        for (Map.Entry<Category, Double> entry : data.entrySet()) {
            Category cat = entry.getKey();
            double sum = entry.getValue();

            PieChart.Data slice = new PieChart.Data(cat.name(), sum);
            chart.getData().add(slice);

            // JavaFX 的 node 是延迟生成的：监听 nodeProperty，生成后再上色
            slice.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-pie-color: " + cat.color + ";");
                }
            });
        }

        return chart;
    }

    @Override
    public String getDisplayName() {
        return "Pie Chart";
    }
}