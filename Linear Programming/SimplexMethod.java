import java.lang.reflect.Array;
import java.util.Arrays;

public class SimplexMethod {

    static double[] calculateZj(int[] cb, double[][] tableau) {
        double[] zj = new double[tableau[0].length];
        for (int j = 0; j < tableau[0].length; j++) {
            double sum = 0;
            for (int i = 0; i < tableau.length; i++) {
                sum += cb[i] * tableau[i][j];
            }
            zj[j] = sum;
        }
        return zj;
    }

    static double[] calculateCjmZj(double[] zj, int[] cj) {
        double[] cjmzj = new double[cj.length];
        for (int i = 0; i < cj.length; i++) {
            cjmzj[i] = cj[i] - zj[i];
        }
        return cjmzj;
    }

    static int getEnteringColumn(double[] cjmzj) {
        int index = 0;
        double max = cjmzj[0];
        for (int i = 1; i < cjmzj.length - 1; i++) { 
            if (cjmzj[i] > max) {
                max = cjmzj[i];
                index = i;
            }
        }
        return index;
    }

    static int getLeavingRow(double[][] tableau, int keyCol) {
        double minRatio = Double.MAX_VALUE;
        int row = -1;
        for (int i = 0; i < tableau.length; i++) {
            double colVal = tableau[i][keyCol];
            System.out.println("Column Value: "+colVal+" for row "+i  );
            if (colVal > 0) {
                double ratio = tableau[i][tableau[0].length - 1] / colVal;
                if (ratio < minRatio) {
                    minRatio = ratio;
                    row = i;
                }
            }
        }
        System.out.println();
        return row;
    }

    static void tableauUpdate(double[][] tableau, int pivotRow, int pivotCol) {
        double pivotElement = tableau[pivotRow][pivotCol];

        for (int j = 0; j < tableau[0].length; j++) {
            tableau[pivotRow][j] /= pivotElement;
        }

        for (int i = 0; i < tableau.length; i++) {
            if (i != pivotRow) {
                double factor = tableau[i][pivotCol];
                for (int j = 0; j < tableau[0].length; j++) {
                    tableau[i][j] -= factor * tableau[pivotRow][j];
                }
            }
        }

        for(int i=0; i<tableau.length; i++){
            System.out.println(Arrays.toString(tableau[i]));
        }  
    }

    public static void main(String[] args) {
        double[][] tableau = {
            {1, 4, 1, 0, 0, 24},
            {3, 1, 0, 1, 0, 21},
            {1, 1, 0, 0, 1, 9}
        };

        int[] cj = {2, 5, 0, 0, 0}; 
        int[] cb = {0, 0, 0};       
        
        while (true) {
            double[] zj = calculateZj(cb, tableau);
            double[] cjmzj = calculateCjmZj(zj, cj);

            boolean optimal = true;
            for (double val : cjmzj) {
                if (val > 0) {
                    optimal = false;
                    break;
                }
            }
            if (optimal) break;
 
            int keyCol = getEnteringColumn(cjmzj);
            int keyRow = getLeavingRow(tableau, keyCol);

            if (keyRow == -1) {
                System.out.println("Unbounded solution!");
                return;
            }

            cb[keyRow] = cj[keyCol];
            tableauUpdate(tableau, keyRow, keyCol);
            System.out.println(Arrays.toString(cjmzj));
        }

        System.out.println("Optimal Tableau:");
        for (double[] row : tableau) {
            for (double val : row) {
                System.out.printf("%8.2f", val);
            }
            System.out.println();
        }
    }
}
