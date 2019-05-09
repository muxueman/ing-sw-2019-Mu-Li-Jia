package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

import java.util.Scanner;

public class EffectInScannerMode {
    public EffectInScannerMode() {
    }
    public void mai() {
        System.out.println("请输入n：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int i = 0;
        int t = 0;
        while (n > 1) {
            if (a[i] == 0) {
                t++;
                if (t == 3) {
                    t = 0;
                    a[i] = 1;
                    n--;
                }
            }
            i++;
            if(i == a.length){
                i = 0;
            }
        }
        for(int j=0;j<a.length;j++){
            if(a[j]!=1){
                System.out.println(j+1);
            }
        }
    }
    public void mssn() {
        int monkey = 0;
        int a =getPeach(monkey);
        System.out.println(a);
    }
    public static int getPeach(int monkey) {
        if (monkey <5) {
            return (getPeach(monkey+1)*5+1);
        }else{
            return 1;
        }
    }
    public void msssssin() {
        System.out.println("请输入一个偶数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int j = 0;
        int num2 = 0;
        int flag = 0;
        int tag = 0;
        int temp = 0;
        if (num % 2 != 0 || num == 0) {
            System.out.println("输入数据错误！");
        } else {
            for (int i = 3; i < num; i++) {
                j = (int) Math.sqrt(i);
                for (int k = 2; k <= j; k++) {
                    if (i % k == 0) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    num2 = num - i;
                    temp = (int) Math.sqrt(num2);
                    for (int k = 2; k <= temp; k++) {
                        if (num2 % k == 0) {
                            tag = 1;
                        }
                    }
                    if (tag == 0&&num2>=3) {
                        System.out.println(num + "=" + i + "+" + num2);
                    }
                    tag = 0;
                }
                flag = 0;
            }
        }
    }
}
