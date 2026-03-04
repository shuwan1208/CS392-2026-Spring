public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft qudratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?
	for(int i=0;i<A.length;i++){
		int left=0, right=A.length-1;
		while(left<right)
		{
			if(A[right]-A[left]==A[i] && i!=right && i!=left){
				return true;
			}
			else if (A[right]-A[left]>A[i]){
				right--;
			}
			else{
				left++;
			}
		}
	}
	return false;
    }
	
/*
         * TIME COMPLEXITY EXPLANATION:
         * 1. The outer 'for' loop iterates n times (where n is A.length), 
         * treating each element as the target sum A[k].
         * 2. Inside the loop, the two-pointer approach ('while' loop) 
         * takes O(n) time to find A[i] + A[j] == A[k], as the pointers 
         * 'left' and 'right' traverse the array at most once per outer iteration.
         * 3. Since the input array is already sorted, no initial sorting step is needed.
         * 4. Therefore, the total time complexity is exactly O(n) * O(n) = O(n^2).
         * This perfectly satisfies the soft O(n^2) requirement.
         */


    public static void main(String[] argv) {
	// Please write some code here for testing solve_3sum
	Integer[] A1 = {1, 2, 3, 5, 10};
        System.out.println(solve_3sum(A1));

        Integer[] A2 = {1, 4, 9};
        System.out.println(solve_3sum(A2));

        Integer[] A3 = {2, 2, 4};
        System.out.println(solve_3sum(A3));

        Integer[] A4 = {1, 2};
        System.out.println(solve_3sum(A4));
	// t f t f
    }
}

