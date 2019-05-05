package chart;

import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

public class ScatterPlot extends JFrame {
	private static final long serialVersionUID = 6294689542092367723L;

  	public ScatterPlot(String title, XYDataset dataset, String xAxis, String yAxis) {
	    super(title);

	    JFreeChart chart = ChartFactory.createScatterPlot(title, xAxis, yAxis, dataset);

	    XYPlot plot = (XYPlot)chart.getPlot();
	    plot.setBackgroundPaint(new Color(255,228,196));
	  
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
  	}
}