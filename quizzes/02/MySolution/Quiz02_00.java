//
// HX: 20 points
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.FnStrn.*;
import MyLibrary.MyQueue.*;
import MyLibrary.MyStack.*;
public class Quiz02_00 {
    /*
     Please give a description of your MyLibrary
     What classes have you implemented? For each class
     you have implemented in MyLibrary, please create an
     object of that class as follows:
     */
    public static void main (String[] args) {
	FnList<Integer> fnListObj = new FnList<Integer>();
	FnListSUtil fnListSUtilObj = new FnListSUtil();
	FnA1sz<Integer> fnA1szObj = new FnA1sz<Integer>(new Integer[]{1, 2, 3});
	FnA1szSUtil fnA1szSUtilObj = new FnA1szSUtil();
	FnA1szUtil<Integer> fnA1szUtilObj = new FnA1szUtil<Integer>();
	FnStrn fnStrnObj = new FnStrn("quiz02");
	FnStrnSUtil fnStrnSUtilObj = new FnStrnSUtil();
	MyQueueArray<Integer> myQueueArrayObj = new MyQueueArray<Integer>(8);
	MyQueueEmptyExn myQueueEmptyExnObj = new MyQueueEmptyExn();
	MyQueueFullExn myQueueFullExnObj = new MyQueueFullExn();
	MyStackArray<Integer> myStackArrayObj = new MyStackArray<Integer>(8);
	MyStackList<Integer> myStackListObj = new MyStackList<Integer>();
	MyStackEmptyExn myStackEmptyExnObj = new MyStackEmptyExn();
	MyStackFullExn myStackFullExnObj = new MyStackFullExn();
	System.out.println(fnListObj != null);
	System.out.println(fnListSUtilObj != null);
	System.out.println(fnA1szObj != null);
	System.out.println(fnA1szSUtilObj != null);
	System.out.println(fnA1szUtilObj != null);
	System.out.println(fnStrnObj != null);
	System.out.println(fnStrnSUtilObj != null);
	System.out.println(myQueueArrayObj != null);
	System.out.println(myQueueEmptyExnObj != null);
	System.out.println(myQueueFullExnObj != null);
	System.out.println(myStackArrayObj != null);
	System.out.println(myStackListObj != null);
	System.out.println(myStackEmptyExnObj != null);
	System.out.println(myStackFullExnObj != null);
	return /*void*/;
    }
} // end of [class Quiz01_00{...}]
