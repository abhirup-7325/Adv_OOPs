package Assignment_2;

/*
 * Write a class that implements the CharSequence interface found in the java.lang package. So, it
would contain the following methods.
(i) char charAt(int index)
(ii) int length()
(iii)CharSequence subSequence(int start, int end)
(iv)Override toString()
Your implementation should return the string backwards. Select one of the sentences from the
lecture slide to use as the data. Write a small main method to test your class; make sure to call all
four methods.  
 */

 class ReverseCharSequence implements CharSequence {
     private String original;
     
     public ReverseCharSequence(String original) {
         this.original = original;
     }
     
     @Override
     public int length() {
         return original.length();
     }
     
     @Override
     public char charAt(int index) {
         return original.charAt(original.length() - 1 - index);
     }
     
     @Override
     public CharSequence subSequence(int start, int end) {
         int originalStart = original.length() - end;
         int originalEnd = original.length() - start;
         
         StringBuilder sub = new StringBuilder();
         for (int i = originalStart; i < originalEnd; i++) {
             sub.append(original.charAt(i));
         }
         
         return new ReverseCharSequence(sub.toString());
     }
     
     @Override
     public String toString() {
         StringBuilder reversed = new StringBuilder();
         for (int i = original.length() - 1; i >= 0; i--) {
             reversed.append(original.charAt(i));
         }
         return reversed.toString();
     }
 }
 
 public class p8 {
     public static void main(String[] args) {
         String sentence = "Java does not allow friends...";
         ReverseCharSequence reversedSequence = new ReverseCharSequence(sentence);
         
         System.out.println("Original length: " + sentence.length());
         System.out.println("Reversed length: " + reversedSequence.length());
         
         System.out.println("\nOriginal first char: " + sentence.charAt(0));
         System.out.println("Reversed first char: " + reversedSequence.charAt(0));
         
         System.out.println("\nOriginal string: " + sentence);
         System.out.println("Reversed string: " + reversedSequence.toString());
         
         int start = 5;
         int end = 15;
         
         CharSequence originalSub = new StringBuilder();
         for (int i = start; i < end; i++) {
             ((StringBuilder) originalSub).append(sentence.charAt(i));
         }
         
         System.out.println("\nOriginal subsequence (5, 15): " + originalSub);
         System.out.println("Reversed subsequence (5, 15): " + reversedSequence.subSequence(start, end));
         System.out.println("ToString of reversed subsequence: " + reversedSequence.subSequence(start, end).toString());
     }
 }