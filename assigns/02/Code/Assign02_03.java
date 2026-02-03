
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
