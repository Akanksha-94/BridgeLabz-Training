import java.util.*;


public class LexicalTwist {
  //check reversed
  public static boolean isReversed(String first, String second){
    String reversed = new StringBuilder(first).reverse().toString();
    return reversed.equalsIgnoreCase(second);
  }

  //transform word

  public static String transformWord(String word){
    String reversed=new StringBuilder(word).reverse().toString();

    reversed=reversed.toLowerCase();
    reversed=reversed.replaceAll("[aeiou]","@");

    return reversed;
  }
  
  //check if there is a single word or not
  public static boolean isValidate(String word){
    return !word.trim().contains(" ");
  }

  //combine both first and second woird
  public static void combinedWords(String first, String second){
    String combined=(first+second).toUpperCase();

    int vowels=0;
    int constants=0;

    for(char ch: combined.toCharArray()){
      if(ch>='A' && ch<='Z'){
        if("AEIOU".indexOf(ch)!=-1){
          vowels++;
        }else{
          constants++;
        }
      }
    }
    if(vowels>constants){
      printFirstTwoUnique(combined,true);
    }else if(constants>vowels){
      printFirstTwoUnique(combined,false);
    }else{
      System.out.println("Vowels and constants both are equal");
    }

  }

  //print first two unique vowels or consonants 
  public static void printFirstTwoUnique(String word, boolean wantVowels){
    String found="";
    for(char ch:word.toCharArray()){
      boolean isVowel="AEIOU".indexOf(ch)!=-1;
      if(wantVowels && isVowel && found.indexOf(ch)!=-1){
        found+=ch;
      }else if(!wantVowels && !isVowel && found.indexOf(ch)==-1 && Character.isLetter(ch)){
        found+=ch;
      }

      if(found.length()==2){
        break;
      }
    }
    System.out.println("found");
  }
  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter first word: ");
    String first=sc.nextLine();
    System.out.println("Enter second word: ");
    
    String second=sc.nextLine();

    if(!isValidate(first)){
      System.out.println(first+ " word is invalid.");
      return;
    }
    if(!isValidate(second)){
      System.out.println(second+" word is invalid.");
      return;
    }
    if(isReversed(first, second)){
      System.out.println(transformWord(first));
    }else{
      combinedWords(first, second);
    }
  }

}