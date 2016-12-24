package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(5);
     	boolean actualBool=arb.isEmpty();
     	boolean expectedBool=true;
     	
     	assertEquals("list empty",expectedBool,actualBool);
     	
     	arb.enqueue(5);
		arb.enqueue(2);
		arb.enqueue(1);
		
		assertEquals("size",3,arb.fillCount());
		
		assertEquals("dequeue",(Integer)5,arb.dequeue());
		assertEquals("dequeue",(Integer)2,arb.dequeue());
		assertEquals("dequeue",(Integer)1,arb.dequeue());
		assertEquals("size",0,arb.fillCount());
		assertEquals("size",true,arb.isEmpty());

		arb.enqueue(15);
		arb.enqueue(21);
		arb.enqueue(11);
		arb.enqueue(13);
		arb.enqueue(14);

		 for(int i:arb)
		 	System.out.print(i+"->");
		 System.out.println();

		assertEquals("dequeue",(Integer)15,arb.dequeue());
		assertEquals("dequeue",(Integer)21,arb.dequeue());

		arb.enqueue(7);
		assertEquals("peek",(Integer)11,arb.peek());

		arb.enqueue(8);
		assertEquals("peek",(Integer)11,arb.peek());		
		assertEquals("dequeue",(Integer)11,arb.dequeue());
		assertEquals("dequeue",(Integer)13,arb.dequeue());
		assertEquals("dequeue",(Integer)14,arb.dequeue());
		assertEquals("dequeue",(Integer)7,arb.dequeue());
		assertEquals("dequeue",(Integer)8,arb.dequeue());

		assertEquals("size",true,arb.isEmpty());
		System.out.println("printing");
		
		for(int i:arb)
			System.out.println(i+" ");

     	   
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
