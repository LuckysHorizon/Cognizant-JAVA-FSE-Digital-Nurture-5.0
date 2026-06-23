public class InsertionSort {
    public static void main(String [] args)
    {
        int [] arr = {1,0,3,7,5,9,4,8,2,6};
        insertionSort(arr);
        for(int i = 0; i < arr.length; i++)
        {
            if(i != arr.length-1) System.out.print(arr[i] + ", ");
            else System.out.print(arr[i]);
        } 
    }
    public static void insertionSort(int [] arr)
    {
        int n = arr.length;
        for(int i = 1; i < n; i++)
        {
            int key = arr[i];
            int j = i-1;
            while(j >= 0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }    
}
