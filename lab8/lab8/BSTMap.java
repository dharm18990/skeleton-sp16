package lab8;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

	Node root;
	int size;

	class Node{
		K key;
		V value;
		Node left,right; 

		public Node(){}

		public Node(K k,V v){
			key=k;
			value=v;
		}
	}

	public BSTMap(){}

	
	/** Removes all of the mappings from this map. */
	public void clear(){
		root=null;
		size=0;
	}

	/* Returns true if this map contains a mapping for the specified key. */
	public boolean containsKey(K key){
		return get(key) != null;
	}

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    public V get(K key){

    	if(size()==0)
    		return null;

    	Node temp=root;
    	//System.out.println("temp:"+temp+" "+temp.key);
    	while(temp!=null){
    		int comp=temp.key.compareTo(key);
    		if(comp==0)
    			return temp.value;
    		else if(comp<0)
    			temp=temp.left;
    		else
    			temp=temp.right;
    	}
    	return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
    	return size;
    }


    //private recursive method
    private Node put(Node node,K key,V value){
        if(node==null)
            return new Node(key,value);
        int comp;
        comp=key.compareTo(node.key);
        if(comp==0)
            return node;
        else if(comp<0)
            node.left=put(node.left,key,value);
        else
            node.right=put(node.right,key,value);
        return node;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        root=put(root,key,value);
        size++;
        return;
    }

    

    public void printInOrder(){
        if(size()==0){
            System.out.println("list empty");
            return;
        }
        inOrder(root);
        
    }

    private void printNode(Node node){
        System.out.print("("+node.key+" "+node.value+")");
    }

    private void inOrder(Node node){
        if(node==null)
            return;
        inOrder(node.left);
        printNode(node);
        inOrder(node.right);
    }

    private void inOrder(Node node,Set keySet){
        if(node==null)
            return ;
        inOrder(node.left,keySet);
        //System.out.println("Adding key:"+node.key);
        keySet.add(node.key);
        inOrder(node.right,keySet);
    }

    private void inOrder(Node node,K[] keys,int counter){
        if(node==null)
            return ;
        inOrder(node.left,keys,counter);
        System.out.println("Adding key to array at location:"+counter+" key:"+node.key);
        keys[counter++]=node.key;
        inOrder(node.right,keys,counter);
    }
    
    public Set<K> keySet(){
        if(root==null)
            return null;
        Set<K> keys=new HashSet<K>();
        inOrder(root,keys);
        return keys;
    }    
    
    public V remove(K key){
    	throw new UnsupportedOperationException();
    }

    public V remove(K key, V value){
    	throw new UnsupportedOperationException();	
    }

    public class KeyIterator implements Iterator<K>{
        private K[] keys;
        int counter;

        public KeyIterator(){
            keys=(K[])new Comparable[size];
            counter=0;
            inOrder(root,keys,0);

            System.out.println("Printing inside constrcutor");
            for(int i=0;i<keys.length;i++)
                System.out.print(keys[i]+" ");
            System.out.println();

        }

        public boolean hasNext(){
            return counter<keys.length;
        }

        public K next(){
            return keys[counter++];
        }
        public void remove(){

        }
    }
    
    public Iterator<K> iterator(){
    	Iterator<K> seer=new KeyIterator();

        return seer;
    }

    public static void main(String[] args) {
    	BSTMap<String, String> a = new BSTMap<String, String>();
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
    	
    	b.put("C", 1);
    	System.out.println(b.get("C"));
        b.put("A", 2);
        b.put("B", 3);
        b.put("G", 4);
        b.printInOrder();
        System.out.println();
        System.out.println("keys are:"+b.keySet());

        System.out.println("Printing via enhanced for loop:");
        for(String str:b)
            System.out.println(str);

        //Above code is same as saying
        System.out.println("Printing the legendary iterator:");
        Iterator<String> seer=b.iterator();
        while(seer.hasNext()){
            System.out.println(seer.next());
        }

    }

}