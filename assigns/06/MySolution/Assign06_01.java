/*
 *  Array-based Quicksort
 */
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
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
        System.out.println("Generating an array of 1,000,000 zeros...");
        int n = 1_000_000;
        Integer[] millionZeros = new Integer[n];
        for (int i = 0; i < n; i++) {
            millionZeros[i] = 0; 
        }

        System.out.println("Sorting 1M zeros...");
        long start = System.currentTimeMillis();
        arrayQuickSort(millionZeros, Integer::compare);
        
        long end = System.currentTimeMillis();
        System.out.println("Finished in " + (end - start) + " ms!");
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (millionZeros[i] < millionZeros[i - 1]) {
                isSorted = false;
                break;
            }
        }
        System.out.println("Is array properly sorted? " + isSorted);
    }
}