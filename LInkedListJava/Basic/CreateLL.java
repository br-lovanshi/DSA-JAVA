package LInkedListJava.Basic;


 class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }

}

class LinkedList11 {
    Node head;

    public LinkedList11() {
        this.head = null;
    }

    // add ele at the first index of list
    public void insertAtFirst(int data) {

        Node currentNode = new Node(data);

        currentNode.next = head;
        head = currentNode;

    }

    public void insertAtSpecificIndex(int position, int data) {
        Node newNode = new Node(data);

        // if position is 0
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node currNode = head.next, prevNode = head;
        int currPosition = 1;

        while (currNode != null && currPosition != position) {
            prevNode = currNode;
            currNode = currNode.next;
            currPosition++;
        }

        if (currNode == null) return;

        newNode.next = currNode;
        prevNode.next = newNode;

    }

    // insert at the last position
    public void insertAtLast(int data) {
        Node newNode = new Node(data);

        Node currentNode = head;
        if (currentNode == null) {
            currentNode = newNode;
            currentNode.next = head;
            head = currentNode;
            return;
        }

        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }

    // print all the element of the list
    public void printList() {

        Node currNode = head;

        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    // delete ele of same value
    public void delete(int key) {
        Node currNode = head;

        // if key found at first position
        if (currNode != null && currNode.data == key) {
            head = currNode.next;
            return;
        }

        Node prevNode = null;

        while (currNode != null && currNode.data != key) {
            prevNode = currNode;
            currNode = currNode.next;
        }

        if (currNode == null) return;

        prevNode.next = currNode.next;
    }

    // delete element from specific position
    public void deleteByPosition(int position) {

        if (position == 0) {
            head = head.next;
            return;
        }

        Node currNode = head.next;
        Node prevNode = head;

        int currPosition = 1;

        while (currNode != null && currPosition != position) {
            prevNode = currNode;
            currNode = currNode.next;
            currPosition++;
        }
        if (currNode == null) return;

        if (currPosition == position) {
            prevNode.next = currNode.next;
        }
    }

    public void update(int oldData, int newData) {

        Node currNode = head;

        if (currNode != null && currNode.data == oldData) {
            currNode.data = newData;
            return;
        }

        while (currNode != null) {
            if (currNode.data == oldData) {
                currNode.data = newData;
                return;
            }
            currNode = currNode.next;
        }

        // while(currNode != null && currNode.data != oldData){
        //     currNode = currNode.next;
        // }

        // if(currNode == null) return;

        // currNode.data = newData;
    }

    public void reverse() {
        Node curr = head;
        Node prev = null;
        Node next = null;

        while (curr != null) {

            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }

        head = prev;
    }
}

public class CreateLL {

    public static void main(String[] args) {
        LinkedList11 list = new LinkedList11();
        // list.insertion(1);
        // list.insertion(2);

        // LinkedList1 list = new LinkedList1();

        // list.insertAtLast(1);
        // list.insertAtLast(2);
        // list.insertAtLast(3);
        // list.insertAtLast(4);
        // list.insertAtLast(5);

        // list.printList();
        // list.insertAtSpecificIndex(2, 10);

        // list.printList();
        // list.delete(3);
        // list.deleteByPosition(0);

        // list.printList();
        // list.deleteByPosition(2);

        // update
        // list.update(3, 5);

        list.reverse();
        list.printList();
    }
}
