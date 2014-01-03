package sort;

public class BinarySerch {
	public static int find(int [] a,int result,int low,int high) {
		// TODO Auto-generated method stub
		if(result>a[high]||result<a[low]||low>high){
			return -1;
		}else{
		int mid=(low+high)/2;
		if(a[mid]==result){
			return mid;
		}
		 if(a[mid]>result){
			return find(a,result,low,mid-1);
		}
		 if(a[mid]<result){
			return find(a,result,mid+1,high);
		}
		 return -1;
		}
	}
	
	public static void main(String[] args) {
		int a[]={1,3,5,7,10};
		System.out.println(find(a,11,0,4));
	}

}
