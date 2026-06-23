import java.util.Arrays;

/**
 * Sorting algorithms.
 */
public class Sort {
    private static void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int valToInsert = arr[i];
            int j = i - 1;
            for (; j >= 0; j--)
                if (valToInsert < arr[j])
                    arr[j + 1] = arr[j];
                else
                    break;
            arr[j+1] = valToInsert;
        }
        return arr;
    }

    private static int[] getSubArray(int[] arr, int start, int end) {
        int[] newArr = new int[end - start];
        for (int i = start, p = 0; i < end; i++, p++)
            newArr[p] = arr[i];
        return newArr;
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length <= 1)
            return arr;

        int mid = (0 + arr.length) / 2;
        int[] firstArr = mergeSort(getSubArray(arr, 0, mid));
        int[] secondArr = mergeSort(getSubArray(arr, mid, arr.length));

        int newArr[] = new int[arr.length];
        int p1 = 0, p2 = 0, np = 0;
        while (p1 < firstArr.length && p2 < secondArr.length) {
            if (firstArr[p1] < secondArr[p2]) {
                newArr[np++] = firstArr[p1++];
            } else {
                newArr[np++] = secondArr[p2++];
            }
        }
        while (p1 < firstArr.length) {
            newArr[np++] = firstArr[p1++];
        }
        while (p2 < secondArr.length) {
            newArr[np++] = secondArr[p2++];
        }
        return newArr;
    }

    private static int[] mergeSortWithoutExtraSubArrays(int[] arr) {
        int[] sortedArray = new int[arr.length];
        mergeSortWithoutExtraSubArrays(arr, 0, arr.length, sortedArray);
        return sortedArray;
    }

    private static void mergeSortWithoutExtraSubArrays(int[] arr, int start, int end, int[] sortedArray) {
        if (end - start <= 1) {
            sortedArray[start] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        mergeSortWithoutExtraSubArrays(arr, start, mid, sortedArray);
        mergeSortWithoutExtraSubArrays(arr, mid, end, sortedArray);

        int p1 = start, p2 = mid, np = start;
        while (p1 < mid && p2 < end) {
            if (arr[p1] < arr[p2]) {
                sortedArray[np++] = arr[p1++];
            } else {
                sortedArray[np++] = arr[p2++];
            }
        }
        while (p1 < mid) {
            sortedArray[np++] = arr[p1++];
        }
        while (p2 < end) {
            sortedArray[np++] = arr[p2++];
        }

        for (int i = start; i < end; i++) {
            arr[i] = sortedArray[i];
        }
    }

    public static int[] quickSort(int[] arr) {
        return arr;
    }

    public static int[] countingSort(int[] arr) {
        // Max value.
        int maxValueInArr = arr[0];
        for (int e : arr)
            if (e > maxValueInArr)
                maxValueInArr = e;

        // Counts.
        int[] counts = new int[maxValueInArr + 1]; // we can have 0, 1, ...maxValueInArr as candidate numbers.
        for (int e : arr)
            counts[e]++;

        // Cumulative counts.
        int cumulativeCounts = 0;
        for (int i = 0; i < counts.length; i++) {
            cumulativeCounts += counts[i];
            counts[i] = cumulativeCounts - 1; // counts are 1-based but indices are 0-based.
        }

        int[] sortedArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            sortedArr[counts[arr[i]]--] = arr[i];

        return sortedArr;
    }

    public static void main(String[] args) {
        int[] sampleArray = { 64, 34, 25, 12, 22, 11, 90, 88 };

        System.out.println("Original array: " + Arrays.toString(sampleArray));
        System.out.println("\nSorting results:");

        System.out.println("Bubble Sort: " + Arrays.toString(bubbleSort(sampleArray.clone())));
        System.out.println("Selection Sort: " + Arrays.toString(selectionSort(sampleArray.clone())));
        System.out.println("Insertion Sort: " + Arrays.toString(insertionSort(sampleArray.clone())));

        System.out.println("Merge Sort: " + Arrays.toString(mergeSort(sampleArray.clone())));
        System.out.println("Merge Sort: " + Arrays.toString(mergeSortWithoutExtraSubArrays(sampleArray.clone())));

        System.out.println("Quick Sort: " + Arrays.toString(quickSort(sampleArray.clone())));
        System.out.println("Counting Sort: " + Arrays.toString(countingSort(sampleArray.clone())));
    }

}
