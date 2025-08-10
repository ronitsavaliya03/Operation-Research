
import java.util.Arrays;

public class HungerianMethod {

    static boolean isSafe(int[][] allo, int row, int col){
        int n=allo.length;
        for(int i=0; i<row; i++){
            if(allo[i][col]!=0){
                return false;
            }
        }
        return true;
    }

    static boolean placeZero(int row, int[][] allo) {
        int n = allo.length;

        if (row == n)
            return true;

        for (int i = 0; i < n; i++) {

            if (isSafe(allo, row, i)) {
                allo[row][i] = 1;
                if (placeZero(row + 1, allo))
                    return true;
                allo[row][i] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] assignment = { { 15, 13, 14, 17 }, { 11, 12, 15, 13 }, { 13, 12, 10, 11 }, { 15, 17, 14, 16 } };
        boolean[][] allocation = new boolean[4][4];
        int[] minRow = new int[4];
        int[] minCol = new int[4];

        for (int i = 0; i < assignment.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < assignment.length; j++) {
                if (min > assignment[i][j]) {
                    min = assignment[i][j];
                }
                minRow[i] = min;
            }

            for (int j = 0; j < assignment.length; j++) {
                assignment[i][j] -= minRow[i];
            }
        }

        for (int i = 0; i < assignment.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < assignment.length; j++) {
                if (min > assignment[j][i]) {
                    min = assignment[j][i];
                }
                minCol[i] = min;
            }

            for (int j = 0; j < assignment.length; j++) {
                assignment[j][i] -= minCol[i];
            }
        }

        for (int i = 0; i < assignment.length; i++) {
            for (int j = 0; j < assignment.length; j++) {
                System.out.print(assignment[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < assignment.length; i++) {
            int count = 0;
            for (int j = 0; j < assignment.length; j++) {
                if (assignment[i][j] == 0) {
                    count++;
                }
            }

            for (int k = 0; k < assignment.length; k++) {
                if (count == 1 && assignment[i][k] == 0) {
                    allocation[i][k] = true;
                    assignment[i][k] = -1;
                }
            }

            System.out.println(Arrays.toString(allocation[i]));

        }

        
    }
}