interface Payment{
    void pay();
}
class CreditCard implements Payment{
    public void pay(){
        System.out.println("Payment made using Credit Card");
    }
}
class DebitCard implements Payment{
    public void pay(){
        System.out.println("Payment made using Debit Card");
    }
}
class Wallet implements Payment{
    public void pay(){
        System.out.println("Payment made using Wallet");
    }
}
public class DigitalPayment {
    public static void main(String[] args) {
        Payment cc=new CreditCard();
        cc.pay();
        Payment dc=new DebitCard();
        dc.pay();
        Payment w=new Wallet();
        w.pay();
    }
}
