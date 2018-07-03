
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Autocomplete {

    public Term[] quries;
    private int beginIndex;
    private int lastIndex;
    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms){
        if (terms == null)
            throw new java.lang.NullPointerException();
        this.quries = terms;
        Arrays.sort(quries);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix){
        if (prefix == null) 
            throw new java.lang.NullPointerException();
     
        int n = totalMatches(prefix);
        if ((beginIndex == -1) || (lastIndex == -1)) 
            throw new IllegalArgumentException("Returned begining and ending index are not correct.");
    
        Term[] totalMatched = new Term[lastIndex - beginIndex + 1];
        int i = 0;
        int j = beginIndex;
        while (n != 0){
            totalMatched[i] = quries[j];
            j++;
            i++;
            n--;
        }
        Arrays.sort(totalMatched, Term.byReverseWeightOrder());
        return totalMatched;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix){
      if (prefix == null) 
          throw new java.lang.NullPointerException();
     return totalMatches(prefix);
    }
    
    
    public int totalMatches (String prefix){   
     Term temp = new Term(prefix, 0);
     beginIndex = BinarySearchDeluxe.firstIndexOf(quries, temp, Term.byPrefixOrder(prefix.length()));
     lastIndex  = BinarySearchDeluxe.lastIndexOf(quries, temp, Term.byPrefixOrder(prefix.length()));
     return lastIndex - beginIndex + 1;  
    }

    // unit testing (required)
    public static void main(String[] args){ 
    // read in the terms from a file
    String filename = args[0];
    In in = new In(filename);
    int N = in.readInt();
    Term[] terms = new Term[N];
    for (int i = 0; i < N; i++) {
        long weight = in.readLong();       // read the next weight
        in.readChar();                         // scan past the tab
        String query = in.readLine();          // read the next query
        terms[i] = new Term(query, weight);    // construct the term
    }

    // read in queries from standard input and print out the top k matching terms
    int k = Integer.parseInt(args[1]);
    Autocomplete autocomplete = new Autocomplete(terms);
    while (StdIn.hasNextLine()) {
        String prefix = StdIn.readLine();
        Term[] results = autocomplete.allMatches(prefix);
        for (int i = 0; i < Math.min(k, results.length); i++)
            StdOut.println(results[i]);
    }
    }
}
