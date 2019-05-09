package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;

public class EffectWithChainReaction {

    public EffectWithChainReaction() {
    }
    public void maSFin() {
        System.out.println(computeAge(8));
    }

    public int computeAge(int n) {
        if(n==1) return 10;
        return computeAge(n-1) + 2;
    }


    public void toBinary(int n,StringBuffer result) {
        if(n/2 != 0)
            toBinary(n/2,result);
        result.append(n%2);
    }
    public void quickSort(String[] strDate,int left,int right){
        String middle,tempDate;
        int i,j;
        i=left;
        j=right;
        middle=strDate[(i+j)/2];

        do{
            while(strDate[i].compareTo(middle)<0&& i<right)
                i++; //找出左边比中间值大的数
            while(strDate[j].compareTo(middle)>0&& j>left)
                j--; //找出右边比中间值小的数
            if(i<=j){ //将左边大的数和右边小的数进行替换
                tempDate=strDate[i];
                strDate[i]=strDate[j];
                strDate[j]=tempDate;
                i++;
                j--;
            }

        }while(i<=j); //当两者交错时停止
        if(i<right){
            quickSort(strDate,i,right);//从
        }

        if(j>left){
            quickSort(strDate,left,j);
        }
    }

    /**
     * @param args
     */


}
