/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ntons
 */
public class Heuristic {


    public int findAndPrintMin(int[] arr){
        int n = arr.length;
        if(n==0){
            return 0;
        }
        if (n==1){
            return arr[0];
        }
        int sum1 = (n-2)*arr[0];
        for (int i=1;i<n;i++){
           sum1 =  sum1 + arr[i];
        }
        int cnt;
        int cnt1=2;
        int min = sum1;
        int sum2;
        if (n==4){
            sum2 = 3*arr[1]+arr[3]+arr[0];
            if (min>sum2){
                min = sum2;
            }
        }
        while(n>cnt1+2){
            sum2=0;
                for (cnt = n-1;cnt>cnt1;cnt=cnt-2){
                    sum2 = sum2 + 2*arr[1] + arr[0]+arr[cnt];
                }
                while(cnt>1){
                    sum2 = sum2 + arr[cnt] + arr[0];
                    cnt-=1;
                }
                sum2 = sum2 + arr[1];
            if (sum2<min){
                min = sum2;
            }
            cnt1 = cnt1 + 2;
        }
        return min;
    }
}
