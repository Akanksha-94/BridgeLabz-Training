import java.lang.reflect.*;

public class ReflectionLoggingProxy {
  public interface Greeting {
    String sayHello(String name);
  }

  static class GreetingImpl implements Greeting {
    public String sayHello(String name) {
      return "Hello, " + name;
    }
  }

  public static void main(String[] args) {
    GreetingImpl impl = new GreetingImpl();
    Greeting proxy = (Greeting) Proxy.newProxyInstance(
        Greeting.class.getClassLoader(),
        new Class[] { Greeting.class },
        (proxyObj, method, methodArgs) -> {
          System.out.println("calling " + method.getName());
          return method.invoke(impl, methodArgs);
        });
    System.out.println(proxy.sayHello("Sam"));
  }
}
