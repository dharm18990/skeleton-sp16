/***
The double ended queue is very similar to the SList and AList classes that we've discussed in class. Specifically, any Deque implementation must have exactly the following operations:

 * `public void addFirst(Item)`: Adds an item to the front of the Deque.
 * `public void addLast(Item)`: Adds an item to the back of the Deque.
 * `public boolean isEmpty()`: Returns true if deque is empty, false otherwise.
 * `public int size()`: Returns the number of items in the Deque.
 * `public void printDeque()`: Prints the items in the Deque from first to last, separated by a space.  
 * `public Item removeFirst()`: Removes and returns the item at the front of the Deque. If no such item exists, returns null.
 * `public Item removeLast()`: Removes and returns the item at the back of the Deque. If no such item exists, returns null.
 * `public Item get(int index)`: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! 

In addition to the methods listed above, you should also include:

 * `public LinkedListDeque()`: Creates an empty linked list deque.
 * `public Item getRecursive(int index)`: Same as get, but uses recursion.
***/
public class LinkedListDeque<Item> {

	private class LinkedListNode{
		Item item;
		LinkedListNode next;
		LinkedListNode prev;
		public LinkedListNode(Item i,LinkedListNode p,LinkedListNode n){
			item=i;
			prev=p;
			next=n;
		}
		public LinkedListNode(){
			this(null,null,null);
		}


	 public Item getRecursive(int index){
	 	//System.out.println("running getRecursive()");
	 	//System.out.println("index:"+index);
	 	//System.out.println("item:"+item);
	 	if(index==0){
	 		return item;
	 	}
	 	else if(next!=sentinel)
	 		return next.getRecursive(index-1);
	 	else
	 		return null;
	 }
	}

	private LinkedListNode sentinel;
	private int size;
	
	public LinkedListDeque(){
		sentinel=new LinkedListNode();
		sentinel.next=sentinel;
		sentinel.prev=sentinel;
		size=0;
	}

	public void addFirst(Item i){
		//list empty
		if(isEmpty()){
			System.out.println("inside if");
			LinkedListNode newset=new LinkedListNode(i,sentinel,sentinel);
			sentinel.prev=newset;
			sentinel.next=newset;
			size++;
			return;
		}
		LinkedListNode newset=new LinkedListNode(i,sentinel,sentinel.next);
		sentinel.next.prev=newset;
		sentinel.next=newset;
		size++;
		return;
	}

	public void addLast(Item i){
		if(isEmpty()){
			addFirst(i);
			return;
		}
		LinkedListNode newset=new LinkedListNode(i,sentinel.prev,sentinel);
		sentinel.prev.next=newset;
		sentinel.prev=newset;
		size++;
		return;
	}

	public boolean isEmpty(){
		return size==0;
	}

	public int size(){
		return size;
	}

	public void printDeque(){
		if(isEmpty()){
			System.out.println("List Empty");
			return;
		}
		LinkedListNode temp=sentinel.next;
		while(temp!=sentinel){
			System.out.print(temp.item+"->");
			temp=temp.next;
		}
		System.out.println();
	}

	 public Item removeFirst(){
	 	if(isEmpty()){
	 		return null;
	 	}
	 	else{
	 		Item item=sentinel.next.item;
	 		sentinel.next.next.prev=sentinel;
	 		sentinel.next=sentinel.next.next;
	 		size--;
	 		return item;
	 	}
	 }

	 public Item removeLast(){
	 	if(isEmpty()){
	 		return null;
	 	}
	 	else{
	 		Item item=sentinel.prev.item;
	 		sentinel.prev.prev.next=sentinel;
	 		sentinel.prev=sentinel.prev.prev;	
	 		size--;
	 		return item;
	 	}
	 }

	 public Item get(int index){
	 	int count=0;
	 	if(isEmpty()){
	 		return null;
	 	}
	 	else{
	 		LinkedListNode temp=sentinel.next;
	 		while(temp!=sentinel){
	 			if(count==index){
	 				Item item=temp.item;
	 				return item;
	 			}
	 			count++;
	 			temp=temp.next;
	 		}
	 		return null;

	 	}
	 }




	public static void main(String[] args) {
		LinkedListDeque<String> list=new LinkedListDeque<>();
		list.printDeque();
		list.addFirst("faisal");
		list.addFirst("raza");
		list.addFirst("Rabbani");
		list.printDeque();
		System.out.println(list.get(0));
		System.out.println("getRecursive():"+list.sentinel.next.getRecursive(2));

	}

	
}