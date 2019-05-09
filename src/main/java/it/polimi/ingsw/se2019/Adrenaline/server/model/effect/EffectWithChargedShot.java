package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

import java.lang.reflect.Array;

public class EffectWithChargedShot {
    public EffectWithChargedShot() {
    }
    public void maollin() {
        //产生若干0到1000的随机数，作为数组的初始值
        int[] data = new int[]{
                (int) (Math.random() * 1000),
                (int) (Math.random() * 1000),
                (int) (Math.random() * 1000),
                (int) (Math.random() * 1000),
                (int) (Math.random() * 1000),
                (int) (Math.random() * 1000),
                (int) (Math.random() * 100),
        };

        System.out.println(Math.random());
        System.out.print("交换前的数据：");
        System.out.println();
        reverse(data);
        System.out.print("交换后的数据：");
    }

    //方法执行完后，参数data中的数据顺序即被颠倒
    //实现思路是第1个和第n个交换，第2个和第n-1个交换，依次类推…
    public static void reverse(int[] data) {
        int len = data.length;

        for(int i=0;i<len/2;i++) {
            int temp = data[i];
            data[i] = data[len-1-i];
            data[len-1-i] = temp;
        }
    }
}

