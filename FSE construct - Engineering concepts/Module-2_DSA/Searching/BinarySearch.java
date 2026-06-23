public class BinarySearch {
    public static void main(String [] args)
    {
        //Array must be sorted
        int [] arr = {10,20,30,50,70,90,110,118,121};
        int target = 118;
        int idx = binarySearch(arr,target);
        System.out.println((idx!=-1) ? "Target Found at index "+idx : "Target Not Found ");
    }
    public static int binarySearch(int [] arr, int target)
    {
        int low = 0;
        int high = arr.length - 1;
        while(low <= high)
        {
            int mid = low + (high-low) / 2;
            if(arr[mid] == target) return mid;
            else if(arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }    
}
