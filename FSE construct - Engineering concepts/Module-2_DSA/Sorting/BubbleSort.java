public class BubbleSort {
    public static void main(String [] args)
    {
        int [] nums = {1,4,3,5,7,2,6};
        bubbleSort(nums);
        for(int num:nums)
        {
            System.out.print(num+" ");
        }
    }
    public static void bubbleSort(int [] arr)
    {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++)
        {
            for(int j = 0; j < n - i - 1; j++)
            {
                if(arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }    
}
