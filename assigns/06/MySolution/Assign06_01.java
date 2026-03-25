/*
 *  Array-based Quicksort
 */
import java.util.Random;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {
    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T, T> cmp) {
	// Please implement standard array-based quickSort and make sure
	// that equal elements are properly handled. In particular, your
	// testing code should test your implementation on an array of 1M zeros!
        if (A == null || A.length <= 1) {
            return;
        }
        Random rand = new Random();
        quickSort(A, 0, A.length - 1, cmp, rand);
    }

    private static <T> void quickSort(T[] A, int low, int high, ToIntBiFunction<T, T> cmp, Random rand) {
        if (low >= high) {
            return;
        }
        int pivotIdx = low + rand.nextInt(high - low + 1);
        swap(A, low, pivotIdx);
        T pivot = A[low];

        int lt = low;       
        int gt = high;      
        int i = low + 1;    

        while (i <= gt) {
            int cmpResult = cmp.applyAsInt(A[i], pivot);
            
            if (cmpResult < 0) {
                swap(A, lt++, i++);
            } else if (cmpResult > 0) {
                swap(A, i, gt--);
            } else {
                i++; 
            }
        }
        quickSort(A, low, lt - 1, cmp, rand);
        quickSort(A, gt + 1, high, cmp, rand);
    }

    private static <T> void swap(T[] A, int i, int j) {
        T temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


    public static void main(String[] args) {
        int n = 1_000_000;
        System.out.println("Generating an array of 1,000,000 random numbers...");
        Integer[] randomArray = new Integer[n];
        Random testRand = new Random();
        for (int i = 0; i < n; i++) {
            randomArray[i] = testRand.nextInt(n); 
        }
    
        System.out.println("Sorting 1M random numbers...");
        long start1 = System.currentTimeMillis();
        arrayQuickSort(randomArray, Integer::compare);
        long end1 = System.currentTimeMillis();
        System.out.println("Finished in " + (end1 - start1) + " ms!");
        System.out.println("Is random array properly sorted? " + verifySorted(randomArray));
    
        System.out.println("\nGenerating an array of 1,000,000 zeros...");
        Integer[] millionZeros = new Integer[n];
        for (int i = 0; i < n; i++) {
            millionZeros[i] = 0; 
        }
    
        System.out.println("Sorting 1M zeros...");
        long start2 = System.currentTimeMillis();
        arrayQuickSort(millionZeros, Integer::compare);
        long end2 = System.currentTimeMillis();
        System.out.println("Finished in " + (end2 - start2) + " ms!");
        System.out.println("Is zero array properly sorted? " + verifySorted(millionZeros));
    }
    
    private static boolean verifySorted(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}