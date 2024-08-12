package LInkedLIstJava.Basic;

public class CustomLL {
    private Node head;
    private Node tail;
    int size;

    CustomLL() {
        this.size = 0;
    }

    private class Node {
        private int data;
        private Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //    insert at first position
    public void insertFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        if (this.tail == null) {
            tail = newNode;
        }
        size++;
    }

    public void insert(int data, int index) {
        if (index == 0) insertFirst(data);
        if (index == size) insertEnd(data);

        Node curr = head;
        for (int i = 1; i < index; i++) {
            curr = curr.next;
        }

        Node newNode = new Node(data);
        newNode.next = curr.next;
        curr.next = newNode;
    }

    public void insertEnd(int data) {
        if (this.tail == null) insertFirst(data);
        Node newNode = new Node(data);
        this.tail.next = newNode;
        tail = newNode;
        size++;
    }

    public int deleteFirst() {
        int data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }
    public int delete(int index){
        if(index == 0) return deleteFirst();
        if(index == size-1) return deleteLast();
        Node prev = get(index-1);
        int value = prev.next.data;
        prev.next = prev.next.next;
        return value;
    }
    public int deleteLast() {
        if (size <= 1) {
            deleteFirst();
        }
        Node curr = head;
        Node secondLast = get(size - 2);
        int data = tail.data;
        tail = secondLast;
        tail.next = null;
        return data;
    }

    public Node get(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public int findMiddleEle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + "->");
            curr = curr.next;
        }
        System.out.println("End");
    }
}

class Main {
    public static void main(String[] args) {
        CustomLL ll = new CustomLL();
        ll.insertFirst(1);
        ll.insertFirst(2);
        ll.insertFirst(3);
        ll.print();
        ll.insertEnd(4);
        ll.insertEnd(5);
        ll.print();
//        System.out.println(ll.size);
//        ll.insert(100, 2);
//        ll.print();
//        ll.deleteFirst();
//        ll.print();
//        System.out.println(ll.findMiddleEle());
        ll.deleteLast();
        ll.print();
        System.out.println(ll.delete(2));
        ll.print();
    }
}
