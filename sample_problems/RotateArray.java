package sample_problems;

/** Rotate array of size n by d elements to the left */
class RotateArray {

    static int[] rotateByCopy(int[] a, int d) {
        int b[] = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[(i + d) % a.length];
        }
        return b;
    }

    static void rotateInPlace(int[] a, int d) {
        for (int i = 1; i <= d; i++) {

            int temp = a[0];
            int j = 0;
            for (; j < a.length - 1; j++) {
                a[j] = a[j + 1];
            }
            a[j] = temp;
        }
    }

    static String print(int a[]) {
        String str = "";
        for (int i = 0; i < a.length; i++) {
            str += a[i] + " ";
        }
        return str;
    }

    public static void main(String[] args) {
        int a[] = { 1, 2, 3, 4, 5, 6 };

        System.out.println(print(a));
        rotateInPlace(a, 2);
        System.out.println(print(a));

        System.out.println(print(rotateByCopy(a, 2)));
    }
}
