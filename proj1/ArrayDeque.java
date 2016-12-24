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
class ArrayDeque<Item> {
	Item[] items;
	int size;
	int nextFirst;
	int nextLast;

	public ArrayDeque(){
		items=(Item[]) new Object[8];
		size=0;
		nextFirst=-1;
		nextLast=-1;
	}

	public void resize(int capacity){
		//declare new array of size capacity
		Item[] newItems=(Item[]) new Object[capacity];
		//copy items from old to new array

		int i=nextFirst;
		int counter=-1;

		int noOfItems=0;
		while(noOfItems<=size){
			newItems[++counter]=items[i];
			i=plusOne(i);
			noOfItems++;
		}
		//All new items have been copied at this point;

		nextFirst=0;
		nextLast=counter;
		items=newItems;
		return;
	}

	public int plusOne(int index){
		//check for boundary case which is items.length-1

		if(index==items.length-1){
			return 0;
		}
		else
			return index+1;

	}

	public int minusOne(int index){
		//check for bounday case and circular shift
		if(index==0){
			index=items.length-1;
			return index;
		}
		else{
			return index-1;
		}
	}

	public void addFirst(Item item){
		if(isEmpty()){
			addLast(item);
			return;
		}
		
		if(size<items.length){
			nextFirst=minusOne(nextFirst);
			items[nextFirst]=item;
			size++;
			return;	
		}else{
			//resize the array
			resize(items.length*2);
			addFirst(item);
		}

	}

	public void addLast(Item item){
		if(isEmpty()){//list empty
			items[0]=item;
			nextFirst=nextLast=0;
			size++;
			return;
		}
		if(size<items.length){
			nextLast=plusOne(nextLast);
			items[nextLast]=item;
			size++;
			return;
		}else{
			//resize the array
			resize(items.length*2);
			addLast(item);
		}
	}

	public Item removeFirst(){
		if(isEmpty()){//list empty
			System.out.println("List empty");
			return null;
		}
		else{//list not empty
			double rFactor=(double)size/(double)items.length;
			if(rFactor>.25){//check if size/items.length is less than .25 or not
				Item item=items[nextFirst];
				items[nextFirst]=null;
				nextFirst=plusOne(nextFirst);
				size--;
				return item;
			}
			else{//resize
				resize(items.length/2);
				return removeFirst();
			}	
		} 
	}

	public Item removeLast(){
		if(isEmpty()){//list empty
			System.out.println("List empty");
			return null;
		}
		else{//list not empty
			double rFactor=(double)size/(double)items.length;
			if(rFactor>.25){//check if size/items.length is less than .25 or not
				Item item=items[nextLast];
				items[nextLast]=null;
				nextLast=minusOne(nextLast);
				size--;
				return item;
			}
			else{//resize
				resize(items.length/2);
				return removeLast();
			}	
		}
	}


	public boolean isEmpty(){
		return size==0;
	}

	public int size(){
		return size;
	}

	public Item get(int index){
		return items[(index+nextFirst)%items.length];
	}

	public void printDeque(){
		System.out.println("printDeque():"+items.length);
		if(isEmpty()){
			System.out.println("List empty");
			return;
		}
		
		int counter=nextFirst;
		int noOfItems=0;
		while(true){
			System.out.print(items[counter]+"->");
			counter=plusOne(counter);
			noOfItems++;
			if(noOfItems==size)
				break;
		}
		System.out.println();
	}



	public static void main(String[] args) {
		ArrayDeque<Integer> arr=new ArrayDeque<>();

		arr.addLast(5);
		arr.addLast(10);
		arr.addLast(7);
		arr.addFirst(8);
		arr.addFirst(18);
		arr.addLast(32);
		arr.addLast(12);
		arr.addLast(42);
		arr.printDeque();
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		arr.addLast(58);
		arr.addLast(77);
		arr.addFirst(13);
		arr.addFirst(18);
		arr.addLast(32);
		arr.addLast(12);
		arr.addLast(42);
		
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		System.out.println("removeFirst "+arr.removeFirst());
		arr.printDeque();
		arr.addFirst(13);
		arr.addFirst(18);
		arr.addLast(32);
		arr.addLast(12);
		arr.addLast(42);
		arr.addLast(5);
		arr.addLast(10);
		arr.printDeque();
		for(int i=0;i<arr.size();i++)
			System.out.print(arr.get(i)+"->");

		
	}
}