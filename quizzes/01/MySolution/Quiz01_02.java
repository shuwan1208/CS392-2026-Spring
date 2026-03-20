import MyLibrary.FnA1sz.FnA1sz;

public class Quiz01_02 {
    
    public static boolean solve_3prod(Integer[] A) {
		// Please give a soft quadratic time implementation
	// that solves the 3-prod problem. The function call
	// solve_3prod(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k].
	// Why is your implementation soft O(n^2)? Please give a
	// BRIEF explanation
        if (A == null || A.length < 3) return false;

        FnA1sz<Integer> fnA = new FnA1sz<>(A);
        FnA1sz<Integer> sorted = fnA.mergeSort((a, b) -> Integer.compare(a, b));
        
        int n = sorted.length();
        Integer[] S = new Integer[n];
        for (int i = 0; i < n; i++) {
            S[i] = sorted.getAt(i);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                long target = (long) S[i] * S[j];
                
                int first = firstOccurrence(S, target);
                if (first != -1) {
                    int last = lastOccurrence(S, target);
                    int count = last - first + 1;
                    
                    int required = 1;
                    if (S[i] == target) required++;
                    if (S[j] == target) required++;
                    
                    if (count >= required) {
                        return true;
                    }
                }
            }
        }
        
        /* * BRIEF explanation:
         * 1. Sorting the array takes O(n log n) time.
         * 2. Iterating through all distinct (i, j) pairs takes O(n^2) time.
         * 3. For each pair, using binary search to find A[i]*A[j] takes O(log n) time.
         * The total time complexity is O(n^2 log n), satisfying the soft O(n^2) constraint.
         */
        return false;
    }

    private static int firstOccurrence(Integer[] arr, long target) {
        int low = 0, high = arr.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                res = mid;
                high = mid - 1; // Keep searching left
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    private static int lastOccurrence(Integer[] arr, long target) {
        int low = 0, high = arr.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                res = mid;
                low = mid + 1; // Keep searching right
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] argv) {
		// Please write some code here for testing solve_3prod
        Integer[] test1 = {2, 3, 6, 10}; 
        Integer[] test2 = {2, 2, 4};     
        Integer[] test3 = {1, 5, 5};     
        Integer[] test4 = {0, 0, 0};     
        Integer[] test5 = {2, 4, 10};    
        Integer[] test6 = {-2, -3, 6};   

        System.out.println("test1 (Expected: true)  -> Result: " + solve_3prod(test1));
        System.out.println("test2 (Expected: true)  -> Result: " + solve_3prod(test2));
        System.out.println("test3 (Expected: true)  -> Result: " + solve_3prod(test3));
        System.out.println("test4 (Expected: true)  -> Result: " + solve_3prod(test4));
        System.out.println("test5 (Expected: false) -> Result: " + solve_3prod(test5));
        System.out.println("test6 (Expected: true)  -> Result: " + solve_3prod(test6));
    }
}