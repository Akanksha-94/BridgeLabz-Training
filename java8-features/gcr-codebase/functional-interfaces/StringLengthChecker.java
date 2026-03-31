
import java.util.function.Function;

public class StringLengthChecker {
    public static void main(String[] args) {
        int charLimit=5;
        Function<String,Integer> stringLen=
            message->message.length();
        String message="Hello World to java 8 features";
        int length=stringLen.apply(message);
        if(length>charLimit){
            System.out.println("Message exceeds chharacter limit");
        }
        else{
            System.out.println("Message is within character limit");
        }
    }
}
