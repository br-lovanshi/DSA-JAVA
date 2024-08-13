package LInkedListJava.Problms;
class Node2{
    int data;
    Node2 next;

    Node2(int data){
        this.data = data;
        this.next = null;
    }
}
public class MiddleOfList {
     Node2 head;

    MiddleOfList(){
        this.head = null;
    }

    public void insertAtEnd(int data){
        Node2 newNode = new Node2(data);

        if(head == null){
            newNode.next = head;
            head = newNode;
            return;
        }

        Node2 curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        newNode.next = curr.next;
        curr.next = newNode;

    }

    public void print(){
        if(head == null) return;
        Node2 curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void middleOfList(){
        Node2 curr = head;
        int length = 0;

        while(curr != null){
            length++;
            curr = curr.next;
        }

        length = length/2;
        int indx = 0;
        while(curr != null && curr.next != null && length != indx){
            indx++;
            curr = curr.next;
        }
        if(length % 2 == 0){
            System.out.println(curr.next.data);
        }
        System.out.println(curr.data);
    }
    public static void main(String[] args) {
        MiddleOfList ll = new MiddleOfList();
        ll.insertAtEnd(1);
        ll.insertAtEnd(2);
        ll.insertAtEnd(3);
        ll.insertAtEnd(4);
        ll.print();
        ll.middleOfList();

    }
}
