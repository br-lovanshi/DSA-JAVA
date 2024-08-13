package LInkedListJava;

public class DublyLL {

    private Node head;
    private int size;

    private class Node {
        private int value;
        private Node prev;
        private Node next;

        Node(int value) {
            this.value = value;
        }

        Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public void insertFirst(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            size++;
        }
    }

    public void insert(int value, int index) {
        Node newNode = new Node(value);
        if(head == null){
            newNode.prev = null;
            newNode.next = null;
            head = newNode;
            size++;
            return;
        }
        if(size-1 < index) return;
        Node curr = head;
        for(int i = 0; i<index-1; i++){
            curr = curr.next;
        }
        newNode.next = curr.next;
        curr.next = newNode;
        newNode.prev = curr;
        if(newNode.next != null){
            newNode.next.prev = newNode;
        }
    }

    public void insertLast(int value) {
        Node newNode = new Node(value);
        newNode.next = null;
        if(head == null){
            newNode.prev = null;
            head = newNode;
            return;
        }
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = newNode;
        newNode.prev = curr;
    }

    public void display() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.value + "=>");
            curr = curr.next;
        }
        System.out.println("END");
    }

    public void displayReverse(){
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        while(curr != null){
            System.out.print(curr.value + "->");
            curr = curr.prev;
        }
        System.out.println("Start");
    }

    public static void main(String[] args) {
        DublyLL dublyLL = new DublyLL();
        dublyLL.insertLast(20);
        dublyLL.insertFirst(10);
        dublyLL.insertFirst(30);
        dublyLL.display();
        dublyLL.displayReverse();
        dublyLL.insert(5,4);
        dublyLL.display();

    }
}
