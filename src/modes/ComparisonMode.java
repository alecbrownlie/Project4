package modes;

import algorithms.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.lang.IllegalArgumentException;
import java.util.*; 
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import chart.ScatterPlot;

public class ComparisonMode {
	private KnapsackDP knapsackDP = new KnapsackDP();
	private KnapsackGreedy knapsackGreedy = new KnapsackGreedy();

	public void run() {
		File folder = new File("../test/");
		List<File> files = Arrays.asList(folder.listFiles());
		if (files.size() % 3 != 0) {
			System.out.println("[ERROR] number of files in test/ must be a multiple of 3");
			System.exit(0);
		}
		Collections.sort(files);
		compareDP(files);
		compareGreedy(files);
	}

	private void compareDP(List<File> files) {
		XYSeriesCollection datasetDP = new XYSeriesCollection();
		System.out.println("Generating Graphs for Dynamic Programming Comparisons...");

		XYSeries dpSeries = new XYSeries("DP");
		XYSeries dpHashSeries = new XYSeries("DP w/ Hashing");

		for (int i = 0; i < files.size() / 3; i++) {
			final Integer index = i;
			List<File> currentFiles = files.stream().filter(file -> index == Integer.parseInt(file.getName().substring(2,3))).collect(Collectors.toList());
			Integer capacity = 0;
			List<Integer> values = new ArrayList<Integer>();
			List<Integer> weights = new ArrayList<Integer>();
			Set<Integer> optimalSubset = new HashSet<Integer>();

			for (File file : currentFiles) {
				String fileName = file.getName();
				char c = fileName.charAt(fileName.length() - 5);
				switch(c) {
					case 'c':
						capacity = readFile(file).get(0);
						break;
					case 'v':
						values = readFile(file);
						break;
					case 'w':
						weights = readFile(file);
						break;
					default:
						break;
				}
			}

			long startTime = System.nanoTime();
			Integer optimalValue = knapsackDP.compute(capacity, values, weights, optimalSubset);
			long endTime = System.nanoTime();
			dpSeries.add(values.size(), (endTime - startTime));

			// startTime = System.nanoTime();
			// optimalValue = knapsackDP.computeWithHeap(capacity, values, weights, optimalSubset);
			// endTime = System.nanoTime();
			// dpHashSeries.add(values.size(), (endTime - startTime));
		}

		datasetDP.addSeries(dpSeries);
		// datasetDP.addSeries(dpHashSeries);
		String title = "Dynamic Programming Comparison";
		generateScatterPlot(datasetDP, title, "N", "Time");
	}


	private void compareGreedy(List<File> files) {
		XYSeriesCollection datasetGreedy = new XYSeriesCollection();
		System.out.println("Generating Graphs for Greedy Comparisons...\n");

		XYSeries greedySeries = new XYSeries("Greedy");
		XYSeries greedyHeapSeries = new XYSeries("Greedy w/ Max-Heap");

		for (int i = 0; i < files.size() / 3; i++) {
			final Integer index = i;
			List<File> currentFiles = files.stream().filter(file -> index == Integer.parseInt(file.getName().substring(2,3))).collect(Collectors.toList());
			Integer capacity = 0;
			List<Integer> values = new ArrayList<Integer>();
			List<Integer> weights = new ArrayList<Integer>();
			Set<Integer> optimalSubset = new HashSet<Integer>();

			for (File file : currentFiles) {
				String fileName = file.getName();
				char c = fileName.charAt(fileName.length() - 5);
				switch(c) {
					case 'c':
						capacity = readFile(file).get(0);
						break;
					case 'v':
						values = readFile(file);
						break;
					case 'w':
						weights = readFile(file);
						break;
					default:
						break;
				}
			}

			long startTime = System.nanoTime();
			Integer optimalValue = knapsackGreedy.compute(capacity, values, weights, optimalSubset);
			long endTime = System.nanoTime();
			greedySeries.add(values.size(), (endTime - startTime));

			startTime = System.nanoTime();
			optimalValue = knapsackGreedy.computeWithHeap(capacity, values, weights, optimalSubset);
			endTime = System.nanoTime();
			greedyHeapSeries.add(values.size(), (endTime - startTime));
		}

		datasetGreedy.addSeries(greedySeries);
		datasetGreedy.addSeries(greedyHeapSeries);
		String title = "Greedy Comparison";
		generateScatterPlot(datasetGreedy, title, "N", "Time");
	}

	private List<Integer> readFile(File file) {
		BufferedReader reader = null;
		List<Integer> result = new ArrayList<Integer>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String text;
			while ((text = reader.readLine()) != null) {
				text = text.replaceAll("\t", "");
				text = text.replaceAll("\n", "");
				text = text.replaceAll(" ", "");
		        result.add(Integer.parseInt(text));
		    }
		} catch (FileNotFoundException e) {
		    System.out.println("[ERROR]: file not found.");
		} catch (IOException e) {
		    System.out.println("Error: IOException.");
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {}
		}

		return result;
	}

	private void generateScatterPlot(XYDataset dataset, String title, String xAxis, String yAxis) {
		SwingUtilities.invokeLater(() -> {
	      ScatterPlot scatterPlot = new ScatterPlot(title, dataset, xAxis, yAxis);
	      scatterPlot.setSize(900, 450);
	      scatterPlot.setLocationRelativeTo(null);
	      scatterPlot.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      scatterPlot.setVisible(true);
	    });
	}
}