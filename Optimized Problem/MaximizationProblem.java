public class MaximizationProblem {
    public static void main(String[] args) {

        int max=0, Z=0, x1=0, x2=0;

        for(int i=0; i<=4; i++){
            for(int j=0; j<=4; j++){
                Z = 3*i+2*j;

                if(Z>max && i+j<=4){
                    max=Z;
                    x1=i;
                    x2=j;
                }
            }
        }

        System.out.println(x1+" "+x2+" "+" "+max);
    }
}
