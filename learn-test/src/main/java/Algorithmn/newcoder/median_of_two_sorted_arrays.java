package Algorithmn.newcoder;

/**
 * created at 2019/7/13
 *
 * @author sheyang
 */
public class median_of_two_sorted_arrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        if (0 >= size1) {
            return size2 % 2 == 0 ? (nums2[size2 / 2] + nums2[size2 / 2 - 1]) / 2 : nums2[size2 / 2];
        }
        if (0 >= size2) {
            return size1 % 2 == 0 ? (nums1[size1 / 2] + nums1[size1 / 2 - 1]) / 2 : nums1[size1 / 2];
        }
        int index1 = 0;
        int index2 = 0;
        int midLeft = 0;
        int midRight = 0;
        while ((index1 + index2) <= (size1 + size2) / 2) {
            midLeft = midRight;
            int temp1 = index1 >= size1 ? Integer.MAX_VALUE : nums1[index1];
            int temp2 = index2 >= size2 ? Integer.MAX_VALUE : nums2[index2];
            if (temp1 > temp2) {
                midRight = temp2;
                index2++;
            } else {
                midRight = temp1;
                index1++;
            }
        }
        if ((size1 + size2) % 2 == 0) {
            return ((double) midLeft + midRight) / 2;
        } else {
            return midRight;
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

}
