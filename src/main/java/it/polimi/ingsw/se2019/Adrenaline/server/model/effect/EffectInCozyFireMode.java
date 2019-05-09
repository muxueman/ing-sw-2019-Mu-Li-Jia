package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

public class EffectInCozyFireMode {

    public EffectInCozyFireMode() {
    }

    public void abcd () {
        int k = 2;
        int num = 0;
        int temp = 1;
        int j = 0;
        for (num = 1; num <= 1000; num++) {
            k = 2;
            temp = 1;
            j = num;
            while (j >= k) {
                if (j % k == 0) {
                    temp += k;
                    j = j / k;
                } else {
                    k++;
                }
            }
            if (temp == num) {
                System.out.println(temp);
            }

        }
    }
    public void bb() {
        double a = 100;
        double sum = 100;
        for(int i =2 ;i<=10;i++){
            a = a*0.5;
            sum += a*2;
        }
        System.out.println("a="+a);
        System.out.println("sum="+sum);
    }


}

