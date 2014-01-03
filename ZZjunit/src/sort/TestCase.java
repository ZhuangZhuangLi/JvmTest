package sort;

import org.junit.Test;


public class TestCase {
	@Test
	public void QuickSort() {
		// TODO Auto-generated method stub
		QuickSort qs=new QuickSort();
		int  [] arr=new int[]{3,8,1,2,5};
		qs.Print(arr);
		qs.Sort(arr,0,arr.length-1);
		System.out.println("--------------------------------");
		qs.Print(arr);
	}
	
	@Test
	public void TestFinnaly() {
		try {
			System.out.println("Test");
			return ;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			System.out.println("finally");
		}
	}
	
}
