import java.util.Scanner;

public class CircularDoublyLinkedList {

    static class Node {
        int data;
        Node prev, next;
        Node(int data) { this.data = data; }
    }

    // Build from array
    public static Node arrayToCircularDLL(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }
        current.next = head;
        head.prev = current;
        return head;
    }

    // Print list
    public static void printCircularDLL(Node head) {
        if (head == null) { System.out.println("Empty"); return; }
        Node current = head;
        while (true) {
            System.out.print(current.data + " <=> ");
            current = current.next;
            if (current == head) break;
        }
        System.out.println("(back to head)");
    }

    // Insert at head
    public static Node insertAtHead(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            return newNode;
        }
        Node tail = head.prev;
        newNode.next = head;
        newNode.prev = tail;
        head.prev = newNode;
        tail.next = newNode;
        return newNode;
    }

    // Insert at end
    public static Node insertAtEnd(Node head, int data) {
        if (head == null) return insertAtHead(null, data);
        Node tail = head.prev;
        Node newNode = new Node(data);
        newNode.next = head;
        newNode.prev = tail;
        tail.next = newNode;
        head.prev = newNode;
        return head;
    }

    // Delete head
    public static Node deleteHead(Node head) {
        if (head == null || head.next == head) return null;
        Node tail = head.prev;
        Node newHead = head.next;
        tail.next = newHead;
        newHead.prev = tail;
        return newHead;
    }

    // Delete by value
    public static Node deleteByValue(Node head, int key) {
        if (head == null) return null;
        Node current = head;
        do {
            if (current.data == key) {
                if (current.next == current) return null;
                current.prev.next = current.next;
                current.next.prev = current.prev;
                if (current == head) head = current.next;
                break;
            }
            current = current.next;
        } while (current != head);
        return head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Length: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        sc.close();

        Node head = arrayToCircularDLL(arr);
        printCircularDLL(head);

        head = insertAtHead(head, 0);
        System.out.println("After insert at head:");
        printCircularDLL(head);

        head = insertAtEnd(head, 99);
        System.out.println("After insert at end:");
        printCircularDLL(head);

        head = deleteHead(head);
        System.out.println("After delete head:");
        printCircularDLL(head);

        head = deleteByValue(head, 50);
        System.out.println("After delete value 50:");
        printCircularDLL(head);
    }
}
