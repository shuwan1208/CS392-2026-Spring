//
// HX: 20 points
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.FnGtree.*;
import MyLibrary.FnStrn.*;
import MyLibrary.LnStrm.*;
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
	/*
	  MyLibrary contains functional containers (FnList, FnA1sz, FnStrn,
	  LnStrm), tree/stream helpers (FnGtreeSUtil, LnStrmSUtil, LnStcn),
	  and queue/stack implementations plus their exception classes.
	  Interface/abstract types such as FnGtree, MyQueue/MyQueueBase, and
	  MyStack/MyStackBase are not instantiated directly.
	*/
	FnList<Integer> fnListObj = new FnList<Integer>();
	FnListSUtil fnListSUtilObj = new FnListSUtil();
	FnA1sz<Integer> fnA1szObj = new FnA1sz<Integer>(new Integer[]{1, 2, 3});
	FnA1szSUtil fnA1szSUtilObj = new FnA1szSUtil();
	FnA1szUtil<Integer> fnA1szUtilObj = new FnA1szUtil<Integer>();
	FnGtreeSUtil fnGtreeSUtilObj = new FnGtreeSUtil();
	FnStrn fnStrnObj = new FnStrn("quiz02");
	FnStrnSUtil fnStrnSUtilObj = new FnStrnSUtil();
	LnStrm<Integer> lnStrmObj = new LnStrm<Integer>(24);
	LnStrmSUtil lnStrmSUtilObj = new LnStrmSUtil();
	LnStcn<Integer> lnStcnObj = new LnStcn<Integer>(24);
	MyQueueArray<Integer> myQueueArrayObj = new MyQueueArray<Integer>(8);
	MyQueueList<Integer> myQueueListObj = new MyQueueList<Integer>();
	MyQueueEmptyExn myQueueEmptyExnObj = new MyQueueEmptyExn();
	MyQueueFullExn myQueueFullExnObj = new MyQueueFullExn();
	MyQueueArrayTest myQueueArrayTestObj = new MyQueueArrayTest();
	MyStackArray<Integer> myStackArrayObj = new MyStackArray<Integer>(8);
	MyStackList<Integer> myStackListObj = new MyStackList<Integer>();
	MyStackEmptyExn myStackEmptyExnObj = new MyStackEmptyExn();
	MyStackFullExn myStackFullExnObj = new MyStackFullExn();
	MyStackArrayTest myStackArrayTestObj = new MyStackArrayTest();
	MyStackListTest myStackListTestObj = new MyStackListTest();
	if (args.length < 0) {
	    System.out.println(fnListObj);
	    System.out.println(fnListSUtilObj);
	    System.out.println(fnA1szObj);
	    System.out.println(fnA1szSUtilObj);
	    System.out.println(fnA1szUtilObj);
	    System.out.println(fnGtreeSUtilObj);
	    System.out.println(fnStrnObj);
	    System.out.println(fnStrnSUtilObj);
	    System.out.println(lnStrmObj);
	    System.out.println(lnStrmSUtilObj);
	    System.out.println(lnStcnObj);
	    System.out.println(myQueueArrayObj);
	    System.out.println(myQueueListObj);
	    System.out.println(myQueueEmptyExnObj);
	    System.out.println(myQueueFullExnObj);
	    System.out.println(myQueueArrayTestObj);
	    System.out.println(myStackArrayObj);
	    System.out.println(myStackListObj);
	    System.out.println(myStackEmptyExnObj);
	    System.out.println(myStackFullExnObj);
	    System.out.println(myStackArrayTestObj);
	    System.out.println(myStackListTestObj);
	}
	return /*void*/;
    }
} // end of [class Quiz01_00{...}]
