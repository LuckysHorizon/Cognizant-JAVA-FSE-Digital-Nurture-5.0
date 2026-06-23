public class LinearSearch 
{
    public static void main(String [] args)
    {
        int [] arr = {1,4,3,7,5,2,9};
        int target = 2;
        linearSearch(arr,target);
    }
    public static int linearSearch(int [] arr, int target)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == target) System.out.println("Target Element Found at Index " + i);
        }
        return -1;
    }
}
