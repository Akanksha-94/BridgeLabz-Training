public class Exercise2 {
  static class LegacyAPI {
    @Deprecated
    public void oldFeature() {
      System.out.println("Old feature");
    }

    public void newFeature() {
      System.out.println("New feature");
    }
  }

  public static void main(String[] args) {
    LegacyAPI l = new LegacyAPI();
    l.oldFeature();
    l.newFeature();
  }
}
