public class Exercise1 {
  static class Animal {
    public void makeSound() {
      System.out.println("Animal sound");
    }
  }

  static class Dog extends Animal {
    @Override
    public void makeSound() {
      System.out.println("Woof!");
    }
  }

  public static void main(String[] args) {
    Dog d = new Dog();
    d.makeSound();
  }
}
