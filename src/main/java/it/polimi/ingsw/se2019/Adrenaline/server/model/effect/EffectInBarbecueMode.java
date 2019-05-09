package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

public class EffectInBarbecueMode {

    private final String type = "in barbecue mode";

    public void selectSort()
    {
        int[] numbers = {1,2,3,4,5,6,7,8};
        int size = numbers.length; //数组长度
        int temp = 0 ; //中间变量

        for(int i = 0 ; i < size ; i++)
        {
            int k = i;   //待确定的位置
            //选择出应该在第i个位置的数
            for(int j = size -1 ; j > i ; j--)
            {
                if(numbers[j] < numbers[k])
                {
                    k = j;
                }
            }
            //交换两个数
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }
}
