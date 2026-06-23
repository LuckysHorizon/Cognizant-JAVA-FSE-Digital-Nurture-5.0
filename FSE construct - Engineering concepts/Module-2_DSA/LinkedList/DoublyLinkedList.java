import java.util.Scanner;

public class DoublyLinkedList {
    static class Node
    {
        int data;
        Node prev;
        Node next;

        public Node(int data)
        {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
        public Node(int data, Node prev, Node next)
        {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
    public static Node arrayToDoublyLL(int [] arr)
    {
        int n = arr.length;
        if(n == 0) return null;
        Node head = new Node(arr[0]);
        Node prev = head;

        for(int i = 1; i < n; i++)
        {
            Node newNode = new Node(arr[i],prev,null);
            prev.next = newNode;
            prev = newNode;
        }
        return head;
    }
    public static void printDoublyLL(Node head)
    {
        while(head != null)
        {
            System.out.print(head.data + " <=> ");
            head = head.next;
        }
        System.out.print("null");
    }
    public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter DoublyLinkedList Input Length: ");
		int n = sc.nextInt();
		int [] arr = new int[n];
		System.out.print("Enter DoublyLinkedList Input: ");
		for(int i=0;i<n;i++)
		{
			arr[i] = sc.nextInt();
		}
        sc.close();
		Node head = arrayToDoublyLL(arr);
		//Printing DoublyLinkedList
		printDoublyLL(head);
		head = InsertAtHead(head,0);
		System.out.println();
		printDoublyLL(head);
	}
    private static Node InsertAtHead(Node head, int x) {
        Node newNode = new Node(x,null,head);
        head.prev = newNode;
        return newNode;
    }
}
