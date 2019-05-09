package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

public class EffectInPunisherMode {
    public EffectInPunisherMode() {
    }

    public void kkk() {
        int[] arr = { 1, 3, 6, 8, 6, 3, 1 };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < arr[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 4; i < arr.length; i++) {
            for (int j = arr[i]; j < 8; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < arr[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void in() {
        int[] a = new int[20];
        int[] b = new int[20];
        double sum = 0.0;
        a[0] = 2;
        a[1] = 3;
        b[0] = 1;
        b[1] = 2;
        for(int i = 2;i<20;i++){
            a[i] = a[i-1]+a[i-2];
        }
        for(int j =2;j<20;j++){
            b[j] = b[j-1]+b[j-2];
        }
        for(int i =0;i<20;i++){
            sum+=a[i]/b[i];
        }
        System.out.println(sum);
    }
    public void ma() {
        int sum = 0;
        int data = 1;
        for(int i = 1;i<=20;i++){
            data = data*i;
            sum += data;
        }
        System.out.println(sum);
    }
    public void ain() {
        int sum = jieCheng(5);
        System.out.println(sum);
    }
    public static int jieCheng(int n){
        if(n>0){
            return jieCheng(n-1)*n;
        }else{
            return  1;
        }
    }

}
