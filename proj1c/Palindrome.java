class Palindrome {

	/*
	**build a Deque where the characters in the deque appear 
	**in the same order as in the word
	***/
	public static Deque<Character> wordToDeque(String word){
		Deque<Character> deque=new ArrayDeque<Character>();
		for(int i=0;i<word.length();i++)
			deque.addLast(word.charAt(i));

		return deque;
	}


	/*
	**The isPalindrome method should return true if the given word is a palindrome, 
	**and false otherwise
	**Any word of length 1 or 0 is a palindrome
	***/

	public static boolean isPalindrome(String word){
		Deque<Character> deque=wordToDeque(word);
		//deque.printDeque();
		boolean flag=palindromeHelper(deque);
		return flag;
	}

	public static boolean isPalindrome(String word, CharacterComparator cc){
		Deque<Character> deque=wordToDeque(word);
		//deque.printDeque();
		boolean flag=palindromeHelper(deque,cc);
		return flag;	
	}

	public static boolean palindromeHelper(Deque<Character> deque,CharacterComparator cc){
		if(deque.size()==0 || deque.size()==1){
			//System.out.println("size is 1 or 0");
			return true;
		}

		char ch1=deque.removeFirst();
		char ch2=deque.removeLast();

		//System.out.println(ch1+" "+ch2+" "+cc.equalChars(ch1,ch2));
		if(cc.equalChars(ch1,ch2)){
			//System.out.println(ch1+" "+ch2+" "+cc.equalChars(ch1,ch2));
			return palindromeHelper(deque,cc);
		}
		return false;
	}


	public static boolean palindromeHelper(Deque<Character> deque){
		if(deque.size()==0 || deque.size()==1){
			//System.out.println("size is 1 or 0");
			return true;
		}
		char ch1=deque.removeFirst();
		char ch2=deque.removeLast();
		//System.out.println(ch1+" "+ch2+" "+deque.size());
		
		
		if(ch1==ch2)
			return palindromeHelper(deque);
		return false;
			
	}
}