package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

public class EffectInLongBarrelMode {
    public EffectInLongBarrelMode() {
    }
        public void ee() {
            int a = 0;
            int b = 0;
            int c = 0;
            int num = 1;
            for (a = 1; a < 5; a++) {
                for (b = 1; b < 5; b++) {
                    for (c = 1; c < 5; c++) {
                        if (a != b && a != c && b != c) {
                            System.out.println("NO." + num + ":" + a + "" + b + ""
                                    + c);
                            num++;
                        }
                    }
                }
            }
        }


}
