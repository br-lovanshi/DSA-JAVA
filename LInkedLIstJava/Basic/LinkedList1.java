package LInkedLIstJava.Basic;

class Node1 {
    int data;
    Node1 next;

    Node1(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListOperation {

    Node1 head;

    LinkedListOperation() {
        this.head = null;
    }

    public void insertion(int data) {
        Node1 currNode = new Node1(data);
        currNode.next = head;
        head = currNode;
        System.out.println(head.data);
    }

    public void delete(int data) {
        Node1 prev = null;
        Node1 curr = head;

        if (curr.data == data) {
            head = curr.next;
        }

        while (curr.data != data) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;


    }
}

public class LinkedList1 {

    public static void main(String[] args) {
        LinkedListOperation linkedList = new LinkedListOperation();

        linkedList.insertion(10);
        linkedList.insertion(20);
        linkedList.insertion(30);
        linkedList.insertion(40);
        linkedList.delete(30);


    }
}
