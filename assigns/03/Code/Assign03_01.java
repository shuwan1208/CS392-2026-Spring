/*
HX-2026-02-05: 10 points
*/
public class Assign03_01 {
    //
    // HX-2025-09-15:
    // This implementation of f91
    // is not tail-recursive. Please
    // translate it into a version that
    // is tail-recursive
    //
    /*
    static int f91(int n) {
	if (n > 100)
	    return n-10;
	else
	    return f91(f91(n+11);
    }
    */
    static int f91(int n) {
        return f91_help(n, 1);
    }
    static int f91_help(int n, int a) {
        if(a==0){
            return n;
        }
        if (n>100)
            return f91_help(n-10,a-1);
        else
            return f91_help(n+11, a+1);
    }

    public static void main(String[] argv) {
	// Please write some testing code here
    for (int i = 90; i <= 110; i++) {
        System.out.println("f91(" + i + ") = " + f91(i));
    }
}
}
