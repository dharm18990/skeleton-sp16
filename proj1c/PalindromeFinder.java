/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("words.txt");
        System.out.println("running");
        
        // String word="flake";
        // System.out.println(Palindrome.isPalindrome(word,new OffByOne()));
        // System.out.println(word);

        CharacterComparator offBy5=new OffByN(Integer.valueOf(args[0]));



        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word,offBy5)) {
                System.out.println(word);
            }
        }
    }
} 
