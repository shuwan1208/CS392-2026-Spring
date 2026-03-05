//
// HX: 10 points
//
import MyLibrary.FnA1sz.FnA1sz;
import MyLibrary.FnA1sz.FnA1szSUtil;
import MyLibrary.FnA1sz.FnA1szUtil;
import MyLibrary.FnList.FnList;
import MyLibrary.FnList.FnListSUtil;
import MyLibrary.FnStrn.FnStrn;
import MyLibrary.FnStrn.FnStrnSUtil;
import MyLibrary.MyQueue.MyQueueArray;
import MyLibrary.MyQueue.MyQueueEmptyExn;
import MyLibrary.MyQueue.MyQueueFullExn;
import MyLibrary.MyStack.MyStackArray;
import MyLibrary.MyStack.MyStackEmptyExn;
import MyLibrary.MyStack.MyStackFullExn;
import MyLibrary.MyStack.MyStackList;

public class Quiz01_00 {
    /*
     Please give a description of your MyLibrary
     What classes have you implemented? For each class
     you have implemented in MyLibrary, please create an
     object of that class as follows:
     */
    public static void main (String[] args) {
	/*
	  MyLibrary (in this repo) contains:
	  - FnList: a simple functional linked-list (+ utilities in FnListSUtil)
	  - FnA1sz: a simple functional array wrapper (+ utilities in FnA1szSUtil/FnA1szUtil)
	  - FnStrn: a simple functional string wrapper (+ utilities in FnStrnSUtil)
	  - MyStack: stack implementations (array-based and list-based) + exceptions
	  - MyQueue: queue implementations (array-based) + exceptions
	*/

	// FnList
	FnList<Integer> fnListObj = new FnList<Integer>();
	FnListSUtil fnListSUtilObj = new FnListSUtil();

	// FnA1sz
	FnA1sz<Integer> fnA1szObj = new FnA1sz<Integer>(new Integer[] { 1, 2, 3 });
	FnA1szSUtil fnA1szSUtilObj = new FnA1szSUtil();
	FnA1szUtil<Integer> fnA1szUtilObj = new FnA1szUtil<Integer>();

	// FnStrn
	FnStrn fnStrnObj = new FnStrn("CS392");
	FnStrnSUtil fnStrnSUtilObj = new FnStrnSUtil();

	// MyStack
	MyStackArray<Integer> myStackArrayObj = new MyStackArray<Integer>(8);
	MyStackList<Integer> myStackListObj = new MyStackList<Integer>();
	MyStackEmptyExn myStackEmptyExnObj = new MyStackEmptyExn();
	MyStackFullExn myStackFullExnObj = new MyStackFullExn();

	// MyQueue
	MyQueueArray<Integer> myQueueArrayObj = new MyQueueArray<Integer>(8);
	MyQueueEmptyExn myQueueEmptyExnObj = new MyQueueEmptyExn();
	MyQueueFullExn myQueueFullExnObj = new MyQueueFullExn();

	// Silence unused-variable warnings by doing something tiny:
	if (args.length < 0) {
	    System.out.println(fnListObj);
	    System.out.println(fnListSUtilObj);
	    System.out.println(fnA1szObj);
	    System.out.println(fnA1szSUtilObj);
	    System.out.println(fnA1szUtilObj);
	    System.out.println(fnStrnObj);
	    System.out.println(fnStrnSUtilObj);
	    System.out.println(myStackArrayObj);
	    System.out.println(myStackListObj);
	    System.out.println(myStackEmptyExnObj);
	    System.out.println(myStackFullExnObj);
	    System.out.println(myQueueArrayObj);
	    System.out.println(myQueueEmptyExnObj);
	    System.out.println(myQueueFullExnObj);
	}

	return /*void*/ ;
    }
}
