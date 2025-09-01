public class SJF {
    public static void main(String[] args) {
        System.out.println("Shortest Job First Scheduling");
        int[] burstTime = {6, 8, 7, 3};
        int[] waitingTime = new int[burstTime.length];
        int[] turnaroundTime = new int[burstTime.length];
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (int i = 1; i < burstTime.length; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
        }

        for (int i = 0; i < burstTime.length; i++) {
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        double avgWaitingTime = (double) totalWaitingTime / burstTime.length;
        double avgTurnaroundTime = (double) totalTurnaroundTime / burstTime.length;

        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < burstTime.length; i++) {
            System.out.println((i + 1) + "\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
        }
        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
