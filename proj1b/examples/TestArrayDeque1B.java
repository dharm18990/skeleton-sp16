import org.junit.Test;
import static org.junit.Assert.*;

/** Demos the version of assertEquals with a String message. */
public class TestArrayDeque1B {
    @Test
    public void test1() {
        StudentArrayDeque<Integer> list=new StudentArrayDeque<>();
        
        FailureSequence fs = new FailureSequence();
        DequeOperation addFirst = new DequeOperation("addFirst", 13);
        DequeOperation removeFirst1 = new DequeOperation("removeFirst");
        DequeOperation removeFirst2 = new DequeOperation("removeFirst");
        DequeOperation size = new DequeOperation("size");

        fs.addOperation(addFirst);
        list.addFirst(13);

        fs.addOperation(removeFirst1);
        list.removeFirst();

        fs.addOperation(removeFirst2);
        list.removeFirst();

        fs.addOperation(size);
        int actual = list.size();
        
        int expected = 0;
        
        assertEquals(fs.toString(),expected, actual);
    }


    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestArrayDeque1B.class);
    }
} 
