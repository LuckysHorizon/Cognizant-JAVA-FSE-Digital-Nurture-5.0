import java.util.Scanner;

public class CircularLinkedList 
{
    static class Node
    {
        int data;
        Node next;
        public Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }
    public static Node arrayToCircularList(int [] arr)
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
        //Making Circular
        current.next = head;
        return head;
    }
    public static void printCircularLinkedList(Node head)
    {
        if(head == null)
        {
            System.out.println("List is Empty");
            return;
        } 
        Node current = head;
        //to avoid infinite loop we use do-while loop
        do
        {
            System.out.print(current.data+ " -> ");
            current = current.next;
        }while(current != head);
        System.out.println("(Back to head)");
    }
    public static Node insertAtEnd(Node head, int data)
    {
        Node newNode = new Node(data);
        if(head == null)
        {
            newNode.next = newNode;
            return newNode;
        }
        Node curr = head;
        while(curr.next != head)
        {
            curr = curr.next;
        }
        curr.next = newNode;
        newNode.next = head;
        return head;
    }
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ente Linked List Length: ");
        int n = sc.nextInt();
        int [] arr = new int[n];
        System.out.print("Enter Elements: ");
        for(int i = 0; i < n; i++)
        {
            arr[i] =  sc.nextInt();
        }
        sc.close();

        Node head = arrayToCircularList(arr);
        System.out.println("Circular Linked List: ");
        printCircularLinkedList(head);

        //Inserting at End

        head = insertAtEnd(head, 56);
        System.out.println("After Inserting 56 at End: ");
        printCircularLinkedList(head);
    }    
}
