import java.util.Scanner;

class Node
{
    int data;
    Node next;

    public Node(int data)
    {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    public static Node ArraytoList(int [] arr)
    {
        int n = arr.length;
        if(n == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for(int i = 1; i < n; i++)
        {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }
    public static void printLinkedList(Node head)
    {
        Node current = head;
        while(current != null)
        {
            System.out.print(current.data+" -> ");
            current = current.next;
        }
        System.out.print("null");
    }
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Linked List Length: ");
        int n = sc.nextInt();
        int [] arr = new int[n];
        System.out.print("Enter Elements: ");
        for(int i = 0; i < n; i++)
        {
            arr[i] = sc.nextInt();
        }
        sc.close();
        Node head = ArraytoList(arr);
        printLinkedList(head);
    }    
}
