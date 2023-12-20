import java.util.Arrays;

public class Recursion {


    public static void main(String[] args) {
    }


    //-----------------------------------------------------------------------------------------------------------------//
    //tribonacci, fibonacci. use Number.doubleValue() for generic types [T extends Number], otherwise analog implementation

    public static long fibonacci(long n){
        return n >= 1 ? longFibo(0, 1, n) : 0;
    }

    private static long longFibo(long n0, long n1, long limit) {
        if(n0 < 0 || n1 < 0) {
            System.out.println("Overflow");
            return -1;
        }
        return limit <= 2 ? n1 : longFibo(n1, n0 + n1, limit);
    }

    //long tribonacci
    public static long tribonacci(long n){
        return n >= 2 ? longTribo(0, 1, 1, n) : 0;
    }

    private static long longTribo(long n0, long n1, long res, long limit){
        if(n0 < 0 || n1 < 0 || res < 0) {
            System.out.println("Overflow");
            return -1;
        }
        return limit <= 3 ? res : longTribo(n1, res, n0 + n1 + res, limit - 1);
    }

    //-----------------------------------------------------------------------------------------------------------------//
    //fully recursive merge sort / mergesort. expand to 2d arrays (2d-merge sort / 2d merge sort) by iterating
    //through each array and merging them 1 by 1. Arrays.copyOfRange can be replaced by for loop (extra method advised)


    public static int[] mergeSort(int[] array){
        if(array == null || array.length < 2) return array;
        else{
            int[] h1 = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2));
            int[] h2 = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length));
            return merge(h1, h2);
        }
    }

    private static int[] merge(int[] a, int[] b){
        int[] res = new int[a.length + b.length];
        int ax = 0, bx = 0, rx = 0;
        while(ax < a.length && bx < b.length){
            if(a[ax] <= b[bx]) res[rx++] = a[ax++];
            else res[rx++] = b[bx++];
        }
        while(ax < a.length) res[rx++] = a[ax++];
        while(bx < b.length) res[rx++] = b[bx++];
        return res;
    }

    //-----------------------------------------------------------------------------------------------------------------//
    //recursive quick sort / quicksort

    public static int[] quickSort(int[] array) {
        return array == null || array.length < 2 ? array : qSort(array, 0, array.length - 1);
    }

    public static int[] qSort(int[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(array, start, end);
            qSort(array, start, pivotIndex - 1);
            qSort(array, pivotIndex + 1, end);
        }
        return array;
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //-----------------------------------------------------------------------------------------------------------------//
    //recursive array permutations / variants / variatons /array variation / array variant.
    //for Strings, adjust by converting the String into a char[], then adding permuted char[] as new String(char[])

    public static int[][] permutations(int[] array) {
        int totalPermutations = factorial(array.length);
        int[][] permutations = new int[totalPermutations][array.length];
        permute(array, 0, permutations, new int[]{0});
        return permutations;
    }

    private static void permute(int[] array, int start, int[][] permutations, int[] count) {
        if (start >= array.length) {
            permutations[count[0]++] = array.clone();
            return;
        }
        for (int i = start; i < array.length; i++) {
            swap(array, start, i);                                  //swap method declaration: see "quickSort"
            permute(array, start + 1, permutations, count);
            swap(array, start, i);
        }
    }

    private static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}
