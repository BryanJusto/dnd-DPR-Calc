package org.example.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;

public class charting {

    public void charter(double[] totals, double v, int ac, int modifier, double v1) throws IOException {

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Avg damage: ["+v+"] AC/ST: ["+ ac + "] Modifier : ["+modifier+"] kill chance : ["+v1+"%]", totals, 20);

        JFreeChart histogram = ChartFactory.createHistogram("DPR", "Damage", "Numbers of rolls", dataset);

        ChartUtils.saveChartAsPNG(new File("Downloads.png"), histogram, 600, 400);

    }
}
