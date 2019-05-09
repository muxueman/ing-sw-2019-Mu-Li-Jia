package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;
import java.util.Scanner;


public class EffectInRocketFistMode {
    public EffectInRocketFistMode() {
    }
    public void n() {
        System.out.println(age(5));
    }
    public static int age(int n){
        int c ;
        if(n == 1){
            c = 10;
        }else{
            c = age(n-1)+2;
        }
        return c;
    }
        public void sin() {
            System.out.println("请输入一个正整数：");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            int[] a = new int[5];
            int b = 0;
            int n = 0;
            for (int i = 4; i >= 0; i--) {
                b = (int) Math.pow(10, i);
                if(num/b != 0&&i>=n){
                    n = i+1;             //位数=最高位+1
                }
                a[i] = num / b;
                num = num - a[i]*b;
            }
            System.out.println("位数："+n);
            for(int j =0;j< n;j++){
                if(a[j]!=-1){
                    System.out.print(a[j]+"\t");
                }
            }
            System.out.println();
            //test();
        }
        //另一种简单方法
    /*
    public static void test(){
        System.out.println("请输入一个正整数：");
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int numLength = num.length();
        System.out.println("位数："+numLength);
        for(int i = numLength-1; i>=0;i--){
            System.out.print(num.charAt(i)+"\t");
        }
    }
    */
        public void ssin() {
            int j = 0 ;
            int flag = 0;
            for(int i=2;i<100;i++){
                j = (int) (Math.sqrt(i));
                for(int k = 2; k<=j;k++){
                    if(i%k == 0){
                        flag = 1;
                    }
                }
                if(flag == 0){
                    System.out.println(i);
                }
                flag = 0;
            }
        }
    public void sssss() {
        System.out.println("请输入行数：");
        Scanner sc = new Scanner(System.in);
        int line = sc.nextInt();
        int[] a = new int[line];
        for (int i = 0; i < line; i++) {
            a[i] = 1;
        }
        if (line == 1) {
            System.out.println(1);
        } else if (line == 2) {
            System.out.println(1);
            System.out.println(1 + "\t" + 1);
        } else {
            System.out.println(1);
            System.out.println(1 + "\t" + 1);
            for (int i = 1; i < line-1; i++) {
                for (int j = i; j >= 1; j--) {
                    a[j] = a[j] + a[j - 1];
                }
                for(int k =0;k<i+2;k++){
                    System.out.print(a[k]+"\t");
                }
                System.out.println();
            }
        }
    }
}
