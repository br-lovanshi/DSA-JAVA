package LInkedLIstJava.Basic;


class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }

}

class LinkedList1 {
    Node head;

    public LinkedList1(){
        this.head = null;
    }

    public void insertion(int data){

        Node currentNode = new Node(data);
        System.out.println("curr data " + currentNode.data);

        currentNode.next = head;
        head = currentNode;
        System.out.println("Head data " + head.data);

    }
}
public class CreateLL {
    
    public static void main(String[] args) {
        LinkedList1 list = new LinkedList1();
        // list.insertion(1);
        list.insertion(2);
        
    }
}
