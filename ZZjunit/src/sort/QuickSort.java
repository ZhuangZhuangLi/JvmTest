package sort;

public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void Sort(int[] arr,int low ,int high) {
		// TODO Auto-generated method stub
		if(low<high){
			int middle=Parttion(arr, low ,high);
			Sort(arr, low ,middle-1);
			Sort(arr, middle+1 ,high);
		}
		
	}
	public int Parttion(int[] arr,int low ,int high) {
		// TODO Auto-generated method stub
		int key=arr[low];
		while(low<high){
			while(key<=arr[high]&&low<high){
				high--;
			}
				arr[low]=arr[high];
	
			while(key>=arr[low]&&low<high){
				low++;
			}
				arr[high]=arr[low];
		}
		arr[low] = key;
	    return low;

	}
	public void Print(int[] arr) {
		// TODO Auto-generated method stub
		System.out.println("打印该数组");
		for(int a:arr){
			System.out.print(a);
			System.out.print(",");
		}
	}


}
