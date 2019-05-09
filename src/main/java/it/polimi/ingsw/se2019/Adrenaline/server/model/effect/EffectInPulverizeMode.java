package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

import java.util.Scanner;

public class EffectInPulverizeMode {
    public EffectInPulverizeMode() {
    }
    public void www() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入x：");
        int x = sc.nextInt();
        System.out.println("请输入y：");
        int y = sc.nextInt();
        System.out.println("请输入z：");
        int z = sc.nextInt();
        int temp;
        if(x>y){
            temp = x;
            x = y;
            y= temp;
        }
        if(x>z){
            temp = x;
            x = z;
            z = temp;
        }
        if(y>z){
            temp = y;
            y = z;
            z = temp;
        }
        System.out.println("从小到大的顺序为："+x+"<"+y+"<"+z);
    }
    public void mm() {
        for(int i = 1;i<=9;i++){
            for(int j = 1;j<=i;j++){
                System.out.print(j+"*"+i+"="+j*i+"\t");
            }
            System.out.println();
        }

    }
}



