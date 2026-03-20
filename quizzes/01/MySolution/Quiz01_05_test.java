//
// HX: For testing Quiz01_05
//
import java.util.function.ToIntBiFunction;
import MyLibrary.FnList.*;

public class Quiz01_05_test extends Quiz01_05 {

    @Override
    public <T> FnList<T> someSort(FnList<T> xs, ToIntBiFunction<T, T> cmp) {
        return Assign05_01.insertSort(xs, cmp); 
    }
    public static void main(String[] args) {
        Quiz01_05_test tester = new Quiz01_05_test();
        FnList<Integer> list = new FnList<>();
        for (int i = 999; i >= 0; i--) {
            list = new FnList<>(i, list);
        }
        ToIntBiFunction<Integer, Integer> parityCmp = (a, b) -> Integer.compare(a % 2, b % 2);
        FnList<Integer> result = tester.someRevStableSort(list, parityCmp);
        System.out.println("Expected: Evens first (in reverse order 998...0), then Odds (999...1)");
        System.out.print("First 10 elements: \n-> ");
        
        FnList<Integer> curr = result;
        for (int i = 0; i < 10 && curr.consq(); i++) {
            System.out.print(curr.hd() + " ");
            curr = curr.tl();
        }
        System.out.println();
    }
}