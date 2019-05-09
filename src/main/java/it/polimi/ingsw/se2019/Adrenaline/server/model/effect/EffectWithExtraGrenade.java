package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

public class EffectWithExtraGrenade {

    public EffectWithExtraGrenade() {
    }

    public void insertSort()
    {
        int[] numbers = {12,3,4,6,8,9,0};
        int size = numbers.length;
        int temp = 0 ;
        int j =  0;

        for(int i = 0 ; i < size ; i++)
        {
            temp = numbers[i];
            //假如temp比前面的值小，则将前面的值后移
            for(j = i ; j > 0 && temp < numbers[j-1] ; j --)
            {
                numbers[j] = numbers[j-1];
            }
            numbers[j] = temp;
        }
    }
}
