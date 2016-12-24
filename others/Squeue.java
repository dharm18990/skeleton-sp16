import java.util.*;
public class Squeue {
	Stack<Integer> inStack;

	public Squeue(){
		inStack=new Stack<Integer>();
	}
	public void enqueue(int item){
		inStack.push(item);
	}
	public int dequeue(){
		return dequeueHelper(inStack.pop());
	}

	private int dequeueHelper(int prev){
		if(inStack.size()==0)
			return prev;
		int cur=inStack.pop();
		int temp=dequeueHelper(cur);
		enqueue(prev);
		return temp;
	}

	public static void main(String[] args) {
		Squeue queue=new Squeue();
		
		for(int i=0;i<10;i++)
			queue.enqueue(i);

		System.out.println(queue.dequeue());


	}
}