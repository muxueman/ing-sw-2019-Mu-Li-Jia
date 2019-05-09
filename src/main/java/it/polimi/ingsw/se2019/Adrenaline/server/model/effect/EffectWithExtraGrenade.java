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
    /**
     * 打印九九乘法口诀表
     */
    public void nineNineMulitTable(){
        for (int i = 1,j = 1; j <= 9; i++) {
            System.out.print(i+"*"+j+"="+i*j+" ");
            if(i==j){
                i=0;
                j++;
                System.out.println();
            }
        }
    }
    /**
     * 判断任意一个整数是否素数
     * @param num
     * @return boolean
     */
    public boolean isPrimeNumber(int num)
    {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num%i==0)
            {
                return false;
            }
        }
        return true;
    }
    /**
     *获得任意一个整数的阶乘
     *@param n
     *@returnn!
     */
    public int factorial(int num)
    {
        //递归
        if(num == 1)
        {
            return 1;
        }
        return num*factorial(num-1);
    }

    /**
     *二分查找特定整数在整型数组中的位置(递归)
     *@param dataset
     *@param data
     *@param beginIndex
     *@param endIndex
     *@return index
     */
    public int binarySearch(int[] dataset,int data,int beginIndex,int endIndex){
        int midIndex = (beginIndex+endIndex)/2;
        //如果查找的数要比开始索引的数据要小或者是比结束索引的书要大，或者开始查找的索引值大于结束的索引值返回-1没有查到
        if(data <dataset[beginIndex]||data>dataset[endIndex]||beginIndex>endIndex){
            return -1;
        }
        if(data <dataset[midIndex]){
            return binarySearch(dataset,data,beginIndex,midIndex-1);
        }else if(data>dataset[midIndex])
        {
            return binarySearch(dataset,data,midIndex+1,endIndex);
        }else {
            return midIndex;
        }
    }

    /**
     *二分查找特定整数在整型数组中的位置(非递归)
     *@param dataset
     *@param data
     *@return index
     */
    public int binarySearch(int[] dataset ,int data)
    {
        int beginIndex = 0;
        int endIndex = dataset.length - 1;
        int midIndex = -1;
        if(data <dataset[beginIndex]||data>dataset[endIndex]||beginIndex>endIndex){
            return -1;
        }
        while(beginIndex <= endIndex) {
            midIndex = (beginIndex+endIndex)/2;
            if(data <dataset[midIndex]) {
                endIndex = midIndex-1;
            } else if(data>dataset[midIndex]) {
                beginIndex = midIndex+1;
            }else {
                return midIndex;
            }
        }
        return -1;
    }

}
