package leetcode.Number;

public class MedianOfTwoSortedArrays {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums1.length>nums2.length){
                return findMedianSortedArrays(nums2,nums1);
            }

            int x=nums1.length;
            int y=nums2.length;

            int low=0;
            int high=x;
            while(low <= high){
                int partitionX=(low+high)/2;
                int partitionY=(x+y+1)/2-partitionX;

                //if partitionX is 0 it means nothing is there on left side. use -inf for mlx(maxleftX);
                //if partitionX is length of nums1.length then there is nothing on right side. use inf for mrx(minrightX)
                int mlx=(partitionX==0)?    Integer.MIN_VALUE:nums1[partitionX-1];
                int mrx=(partitionX==x)?    Integer.MAX_VALUE:nums1[partitionX];

                int mly=(partitionY==0)?    Integer.MIN_VALUE:nums2[partitionY-1];
                int mry=(partitionY==y)?    Integer.MAX_VALUE:nums2[partitionY];

                if(mlx<=mry && mly<=mrx){
                    //we have partitioned array at correct place
                    //now get max of left elements and min of right elements to get the median in case of even length combined array size
                    //or get max of left for odd length combined array size
                    if((x+y)%2==0){
                        return ((double)Math.max(mlx,mly)+Math.min(mrx,mry))/2;
                    }else{
                        return (double)Math.max(mlx,mly);
                    }
                }else if(mlx>mry){//we are too far on right side for partitionX.Go on left side.
                    high=partitionX-1;
                }else{//we are too far on left side for partitionX, go on right side.
                    low = partitionX+1;
                }
            }

            //only we can come here id if input arrays were not sorted. throw in that scenario.
            throw new IllegalArgumentException();
        }

        public static void main(String[] args){
            int[] x={1,2};
            int[] y={3,4};

            MedianOfTwoSortedArrays mt=new MedianOfTwoSortedArrays();
            System.out.println(mt.findMedianSortedArrays(x,y));
        }


}



