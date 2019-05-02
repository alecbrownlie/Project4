// package com.modes;

// import java.util.List;
// import java.util.ArrayList;
// import java.math.BigInteger;
// import java.math.BigDecimal;
// import java.math.MathContext;

// import javax.swing.JFrame;
// import javax.swing.SwingUtilities;
// import javax.swing.WindowConstants;

// import org.jfree.data.xy.XYDataset;
// import org.jfree.data.xy.XYSeries;
// import org.jfree.data.xy.XYSeriesCollection;

// import com.modes.AbstractMode;
// import com.chart.ScatterPlot;

// public class ComparisonMode extends AbstractMode {
// 	private Integer comparisonCount = 0;

// 	protected void runTask1() {
// 		XYSeriesCollection datasetMD = new XYSeriesCollection();
// 		XYSeriesCollection datasetD = new XYSeriesCollection();
// 		XYSeriesCollection dataset = new XYSeriesCollection();

// 		System.out.println("----- Running " + TASK_1 + " -----");

// 		Integer n = randomNumGen.nextInt(100 - 60 + 1) + 60;
		
// 		System.out.println("n = " + n);
// 		datasetMD.addSeries(getAvgModDivisions(n));
// 		datasetD.addSeries(getAvgDivisions(n));
// 		dataset.addSeries(getAvgModDivisions(n));
// 		dataset.addSeries(getAvgDivisions(n));

// 		String titleMD = TASK_1 + ": " + EUCLIDS_ALGO;
// 		String titleD = TASK_1 + ": " + CIC_ALGO;
// 		generateScatterPlot(datasetMD, titleMD, N_EQUALS_STR + n, MD_AVG_N);
// 		generateScatterPlot(datasetD, titleD, N_EQUALS_STR + n, D_AVG_N);
// 		generateScatterPlot(dataset, TASK_1, N_EQUALS_STR + n, "");
// 	}

// 	protected void runTask2() { 
// 		XYSeriesCollection dataset = new XYSeriesCollection();

// 		System.out.println("----- Running " + TASK_2 + " -----");

// 		Integer k = 85;
// 		System.out.println("k = " + k);
// 		dataset.addSeries(getWorstModDivisions(k));
		
// 		String title = TASK_2 + ": " + EUCLIDS_ALGO;
// 		generateScatterPlot(dataset, title, K_EQUALS_STR + k, NUM_MOD_DIVISIONS);
// 	}

// 	protected void runTask3() {
// 		XYSeriesCollection dataset = new XYSeriesCollection();

// 		List<Integer> aRandomNumbers = getRandomNumbers();
// 		List<Integer> bRandomNumbers = getRandomNumbers();

// 		System.out.println("----- Running " + TASK_3 + " -----");
// 		dataset.addSeries(getCommonFactorsComplexity(aRandomNumbers, bRandomNumbers));

// 		String title = TASK_3 + ": " + COMMON_FACTORS;
// 		generateScatterPlot(dataset, title, G_EQUALS_STR + MAX_SIZE_A_B_STR + aRandomNumbers.size() + ", " + bRandomNumbers.size() + "))", NUM_COMP_COMMON);
// 	}

// 	private XYSeries getCommonFactorsComplexity(List<Integer> listA, List<Integer> listB) {	
// 		System.out.println("List A = " + listA);
// 		System.out.println("List B = " + listB);
// 		XYSeries series = plotCommonElementsXY(listA, listB);
// 		return series;
// 	}

// 	private XYSeries plotCommonElementsXY(List<Integer> m, List<Integer> n) {
// 		XYSeries series = new XYSeries(COMMON_FACTORS_FOUND);
// 		int i = 0, j = 0; 
// 	    while (i < m.size() && j < n.size()) 
// 	    { 
// 	    	comparisonCount++;
// 	    	if (m.get(i) < n.get(j)) 
// 	        	i++; 
// 	      	else if (n.get(j) < m.get(i)) 
// 	        	j++; 
// 	      	else {
// 	        	series.add(i, comparisonCount); 
// 	      		j++; 
// 	        	i++; 
// 	      	} 
// 	    }
// 	    return series;
// 	}

// 	private List<Integer> getRandomNumbers() {
// 		List<Integer> randomNumbers = new ArrayList<Integer>();
// 		for (int i = 0; i < 150; i++) {
// 			randomNumbers.add(randomNumGen.nextInt(3 - 2 + 1) + 2);
// 		}
// 		return randomNumbers;
// 	}

// 	private XYSeries getWorstModDivisions(Integer k) {
// 		XYSeries series = new XYSeries(MD);
// 		for (int i = 1; i < k; i++) {
// 			BigInteger currentFib = fibonacci.getNthElement(i);
// 			BigInteger nextFib = fibonacci.getNthElement(i+1);
// 			series.add(i, euclids.getDivisionCountGCD(nextFib, currentFib));
// 		}
// 		return series;
// 	}

// 	private XYSeries getAvgModDivisions(int n) {
// 		XYSeries series = new XYSeries(MD_AVG_N);
// 		for (BigInteger i = BigInteger.valueOf(1); i.compareTo(BigInteger.valueOf(n + 1)) < 0; i = i.add(BigInteger.ONE)){
// 			BigDecimal total = new BigDecimal(0.0);
// 			for (BigInteger j = BigInteger.valueOf(1); j.compareTo(BigInteger.valueOf(i.intValue() + 1)) < 0; j = j.add(BigInteger.ONE)) {
// 				BigInteger divisions = euclids.getDivisionCountGCD(BigInteger.valueOf(n), j);
// 				total = total.add(new BigDecimal(divisions));
// 			}
// 			series.add(i, total.divide(new BigDecimal(i), MathContext.DECIMAL128));
// 		}
// 		return series;
// 	}

// 	private XYSeries getAvgDivisions(int n) {
// 		XYSeries series = new XYSeries(D_AVG_N);
// 		for (BigInteger i = BigInteger.valueOf(1); i.compareTo(BigInteger.valueOf(n + 1)) < 0; i = i.add(BigInteger.ONE)){
// 			BigDecimal total = new BigDecimal(0.0);
// 			for (BigInteger j = BigInteger.valueOf(1); j.compareTo(BigInteger.valueOf(i.intValue() + 1)) < 0; j = j.add(BigInteger.ONE)) {
// 				BigInteger divisions = cic.getDivisionCountGCD(BigInteger.valueOf(n), j);
// 				total = total.add(new BigDecimal(divisions));
// 			}
// 			series.add(i, total.divide(new BigDecimal(i), MathContext.DECIMAL128));
// 		}
// 		return series;
// 	}

// 	private void generateScatterPlot(XYDataset dataset, String title, String xAxis, String yAxis) {
// 		SwingUtilities.invokeLater(() -> {
// 	      ScatterPlot scatterPlot = new ScatterPlot(title, dataset, xAxis, yAxis);
// 	      scatterPlot.setSize(900, 450);
// 	      scatterPlot.setLocationRelativeTo(null);
// 	      scatterPlot.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
// 	      scatterPlot.setVisible(true);
// 	    });
// 	}
// }