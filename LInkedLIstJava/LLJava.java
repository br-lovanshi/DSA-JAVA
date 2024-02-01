//https://oj.masaischool.com/contest/4896/problems

package LInkedLIstJava;

public class LLJava {

	Node head;

	class Node {
		String data;
		Node next;

		Node(String data) {
			this.data = data;
			this.next = null;
		}

	}

	void addFirst(String data) {
		Node newNode = new Node(data);

		newNode.next = head;
		head = newNode;
	}

	void addLast(String data) {

		Node newNode = new Node(data);

		if (head == null) {
			head = newNode;
			return;
		}

		Node curr = head;

		while (curr.next != null) {

			curr = curr.next;
		}
		curr.next = newNode;

	}

	void printList() {
		Node curr = head;
		while (curr != null) {
			System.out.print(curr.data + "-> ");

			curr = curr.next;

		}
		System.out.println();
		if (head == null)
			System.out.println("null");
	}

	void removeFirst() {

		if (head == null) {
			System.out.println("Empty list");
			return;
		}
		head = head.next;

	}

	void removeLast() {

		if (head == null) {
			System.out.println("Empty list");
			return;
		}

		if (head.next == null) {
			head = null;
			return;
		}

		Node slow = head;
		Node fast = head.next;

		while (fast.next != null) {

			slow = slow.next;
			fast = fast.next;
		}
		slow.next = null;

	}

	void reverseList() {

		Node next = null, prev = null, curr = head;
		while (curr != null) {

			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev;

	}

	void middleNode() {
		Node f = head, s = head;
		while (f != null && f.next != null) {

			f = f.next.next;
			s = s.next;
		}
		System.out.println(s.data);
	}

	void removeDuplicate() {

		Node curr = head;

		while (curr != null) {

			Node temp = curr;

			while (temp != null && temp.data == curr.data) {

				temp = temp.next;
			}
			curr.next = temp;
			curr = curr.next;
		}
		// System.out.println(curr);
	}

	public static void main(String[] args) {
		LLJava list = new LLJava();
		list.addFirst("Brajesh");
		list.addLast("Lovanshi");
		list.addFirst("Hello");
		list.addLast("chill bro");
		list.printList();
		list.reverseList();
		list.printList();
		list.removeFirst();
		list.printList();
		list.removeLast();
		list.printList();
		list.middleNode();
		list.addFirst("Lovanshi");
		list.removeDuplicate();
		list.printList();

	}
}
