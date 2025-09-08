public class TwoPersonZeroSumGame {
    
    public static void main(String[] args) {
        int[][] matrix = {
            {5, 2, 0},
            {5, 6, 4},
            {4, 0, 2}
        };

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] rowMins = new int[rows];
        int[] colMaxs = new int[cols];

        for (int i = 0; i < rows; i++) {
            rowMins[i] = Integer.MAX_VALUE;
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] < rowMins[i]) {
                    rowMins[i] = matrix[i][j];
                }
            }
        }
        
        for (int j = 0; j < cols; j++) {
            colMaxs[j] = Integer.MIN_VALUE;
            for (int i = 0; i < rows; i++) {
                if (matrix[i][j] > colMaxs[j]) {
                    colMaxs[j] = matrix[i][j];
                }
            }
        }

        int maxOfRowMins = Integer.MIN_VALUE;
        for (int min : rowMins) {
            if (min > maxOfRowMins) {
                maxOfRowMins = min;
            }
        }   

        int minOfColMaxs = Integer.MAX_VALUE;
        for (int max : colMaxs) {
            if (max < minOfColMaxs) {
                minOfColMaxs = max;
            }
        }

        if (maxOfRowMins == minOfColMaxs) {
            System.out.println("Saddle point exists with value: " + maxOfRowMins);
        } else {
            System.out.println("No saddle point exists.");
        }
    }
}
