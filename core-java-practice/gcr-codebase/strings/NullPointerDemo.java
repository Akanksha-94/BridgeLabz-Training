public class NullPointerDemo {

  public void generateException(){
    String str=null;
    System.out.println(str.length());
  }

  public void handleException(){
    String str=null;
    try{
      System.out.println(str.length());
    } catch(NullPointerException e){
      System.out.println("NullPointerException handle successfully");
    }

  }
  public static void main(String[] args) {
    System.out.println("generate exception");
  NullPointerDemo obj = new NullPointerDemo();
    try{
     obj. generateException();

    }catch(Exception e){
      System.out.println("Exception occurred: " + e);
    }

    System.out.print("handle exception");
    obj.handleException();
    
  }
  
}
