package ua.nure.starodubets.Practice5.part4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The class search max value of two-dimensional array
 *
 */
public class Part4 {

	/**
	 * The method fills array random numbers
	 *
	 * @param matrix two-dimensional array
	 * @return filled array
	 */
	public int[][] fillMatrix(int matrix[][]) {
		for (int i = 0; i < matrix.length; i++){
		    for (int j = 0; j < matrix[i].length; j++){
		      matrix[i][j] = (int)(Math.random() * 1000);
		    }
		}
		return matrix;
	}

	/**
	 * The method search max value of two-dimensional array
	 *
	 * @param matrix two-dimensional array
	 * @param row number of rows in two-dimensional array
	 * @param column number of columns in two-dimensional array
	 * @return max value of two-dimensional array
	 */
	public int findMaxValue(int[][] matrix) {
		int row = matrix.length;
		int column = matrix[0].length;
        int max = matrix[0][0];
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < column; b++) {
            	try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                if (matrix[a][b] > max) {
                    max = matrix[a][b];
                }
            }
        }
        return max;
	}

	/**
	 * The method search max value of an array
	 *
	 * @param array an input array
	 * @return max value of an array
	 */
	public int findMax(int[] array) {
		int max = array[0];
		for(int i = 0; i < array.length; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	/**
	 * The method search max value of two-dimensional array using multithreading
	 *
	 * @param matrix two-dimensional array
	 * @return max value of two-dimensional array
	 */
	public int findMaxValueWithThreads(int[][] matrix) {
		int matrixLength = matrix.length;
		int[] result = new int[matrixLength];
		ExecutorService executor = Executors.newFixedThreadPool(matrixLength);
		for(int i = 0; i < matrix.length; i++) {
			int threadIndex = i;
			Runnable thread = new Runnable() {
				public void run() {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					result[threadIndex] = findMax(matrix[threadIndex]);
				}
			};
			executor.execute(thread);
		}
		executor.shutdown();
		while(!executor.isTerminated()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return findMax(result);
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 */
	public static void main(String[] args) {
		Part4 part4 = new Part4();
		int[][] matrix = new int[4][100];
		part4.fillMatrix(matrix);
		long start = System.currentTimeMillis();
		int max = part4.findMaxValue(matrix);
		long end = System.currentTimeMillis();
		long time = end - start;
		long start2 = System.currentTimeMillis();
		int max2 = part4.findMaxValueWithThreads(matrix);
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;
		System.out.println("Max value without multithreading is " + max);
		System.out.println("Time for execution without multithreading is: " + time + " milliseconds");
		System.out.println("Max value with multithreading is " + max2);
		System.out.println("Time for execution with multithreading is: " + time2 + " milliseconds");
	}
}
