package leetcode.Number;

public class RemoveElements {

    public int removeElement(int[] a, int val) {
        if(a.length==0){
            return 0;
        }

        int end=-1;

        for(int i=0;i<a.length;i++){
            if(a[i]!=val){
                end++;
                a[end]=a[i];
            }
        }
        return end+1;
    }

    public static void main(String[] args){
        RemoveElements re=new RemoveElements();
        System.out.println(re.removeElement(new int[]{2,3,4,5,5,5,5,6},5));

    }
}
