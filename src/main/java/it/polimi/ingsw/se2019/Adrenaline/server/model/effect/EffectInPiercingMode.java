package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;
import java.util.Scanner;

public class EffectInPiercingMode {
    public EffectInPiercingMode() {
    }
    public void ww() {
        for(int i=0;i<100000;i++){
            if((Math.sqrt(i+100)%1==0)&&(Math.sqrt(i+168)%1 == 0)){
                System.out.println(i);
            }
        }
    }
    public void pp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年份：");
        int year = sc.nextInt();
        System.out.println("请输入月份：");
        int month = sc.nextInt();
        System.out.println("请输入天数：");
        int day = sc.nextInt();
        int date = 0;
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            arr[1] = 29;
        }
        for (int i = 0; i < month - 1; i++) {
            date += arr[i];
        }
        date += day;
        System.out.println("天数为：" + date);
    }

}
