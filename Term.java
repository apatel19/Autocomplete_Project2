
import java.util.Comparator;
import java.util.Arrays;

public class Term implements Comparable<Term> {

    final String query;
    final long weight;
    
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight){
       if ((query == null) || (weight < 0))
           throw new IllegalArgumentException("Either query is null or weight is < 0.");
       this.query = query;
       this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
        return new Comparator<Term>() {
            public int compare (Term a, Term b){
             if (a.weight > b.weight)
                 return -1;
             else if (a.weight == b.weight)
                 return 0;
             else 
                 return 1;
            }  
        };
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
        return new Comparator<Term>(){
            public int compare(Term a, Term b) {
                String s1 = a.query;
                String s2 = b.query;
                int minlength = 0; 
                
                if (s1.length() < s2.length())
                    minlength = s1.length();
                else 
                    minlength = s2.length();
                
                
                if (minlength >= r) {
                    return s1.substring(0, r).compareTo(s2.substring(0, r));
                } 
                else if (s1.substring(0, minlength).compareTo(s2.substring(0, minlength)) == 0) {
                    if (s1.length() == minlength) 
                        return -1;
                    else 
                        return 1;
                }
                else 
                    return s1.substring(0, minlength).compareTo(s2.substring(0, minlength));
            } 
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that){
        String a = this.query;
        String b = that.query;
        return a.compareTo(b);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString(){
        return this.weight + "\t" + this.query;
    }

    // unit testing (required)
    public static void main(String[] args)  {
//        Term[] terms = {new Term("Debbie", 3), new Term("Abcd", 8), new Term("Cathy", 1), new Term("Abbcd", 1)};
//        for (Term term : terms) System.out.println(term);
//        System.out.println();
//  
//        Arrays.sort(terms, Term.byReverseWeightOrder());
//        for (Term term : terms) System.out.println(term);
//        System.out.println();
//  
//        Arrays.sort(terms, Term.byPrefixOrder(2));
//        for (Term term : terms) System.out.println(term);
//        System.out.println();
//  
//        Arrays.sort(terms);
//        for (Term term : terms) System.out.println(term);
    }
}
