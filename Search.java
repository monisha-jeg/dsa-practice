import java.util.Arrays;

public class Search {
    
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target)
                    return i;
        return -1;
    }
    
    public static int binarySearch(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target)
                return mid;
            else if (target < arr[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] sampleArray = {11, 12, 22, 25, 34, 64, 88, 90};
        int target = 25;
        
        System.out.println("Sample array: " + Arrays.toString(sampleArray));
        System.out.println("Target: " + target);
        System.out.println("\nSearch results:");
        
        System.out.println("Linear Search: " + linearSearch(sampleArray, target));
        System.out.println("Binary Search: " + binarySearch(sampleArray, target));
    }
    
}
