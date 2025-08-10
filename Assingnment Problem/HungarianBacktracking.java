public class HungarianBacktracking {

    static int n = 4;
    static int[][] cost = {
        {22, 23, 24, 25},
        {24, 25, 26, 27},
        {27, 28, 29, 28},
        {23, 25, 28, 24}
    };

    static boolean[] used = new boolean[n]; 
    static int[] assignment = new int[n];   
    static int[] bestAssignment = new int[n];
    static int minCost = Integer.MAX_VALUE;

    static void assignTasks(int row, int currentCost) {
        if (row == n) {
            if (currentCost < minCost) {
                minCost = currentCost;
                System.arraycopy(assignment, 0, bestAssignment, 0, n);
            }
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!used[col]) {
                used[col] = true;
                assignment[row] = col;
                assignTasks(row + 1, currentCost + cost[row][col]);
                used[col] = false; 
            }
        }
    }

    public static void main(String[] args) {
        assignTasks(0, 0);

        System.out.println("Minimum Assignment Cost: " + minCost);

        System.out.print("Task assigned: ");
        for (int task : bestAssignment) {
            System.out.print((task + 1) + " "); 
        }
        System.out.println();
    }
}