package tyufkdrycgdytuytuofcic;

public class Main {

	public static void main(String[] args) {
		var list = new MyLinkedList();
		list.addLast(10);
		list.addLast(20);
		list.addLast(30);
		System.out.println(list.indexOf(20));
		System.out.println(list.contains(10));
		list.removeFirst();
		list.removeFirst();
		list.removeFirst();
		
		list.printLinkedList();
	}

}
