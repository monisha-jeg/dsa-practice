package sample_problems;

/** Search in a sorted rotated array in O(log n) */
class SearchInSortedArray {
    static int binarySearch(int a[], int start, int end, int x) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (a[mid] == x)
                return mid;
            else if (a[mid] < x)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    static int findMinIndex(int a[]) {
        if (a[0] <= a[a.length - 1])
            return 0;

        int start = 0, end = a.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (a[mid] < a[end])
                end = mid;
            else if (a[mid] > a[end])
                start = mid + 1;
        }
        return start;
    }

    static int search(int[] a, int x) {
        int minIndex = findMinIndex(a);

        if (x > a[a.length - 1])
            return binarySearch(a, 0, minIndex - 1, x);
        else
            return binarySearch(a, minIndex, a.length - 1, x);
    }

    public static void main(String[] args) {
        int a[] = { 3, 4, 5, 6, 1, 2 };

        System.out.println(findMinIndex(new int[] { 3, 4, 5, 6, 1, 2 }));

        System.out.println(search(a, 0));
        System.out.println(search(a, 1));
        System.out.println(search(a, 2));
        System.out.println(search(a, 3));
        System.out.println(search(a, 4));
        System.out.println(search(a, 5));
        System.out.println(search(a, 6));
        System.out.println(search(a, 7));
    }
}
