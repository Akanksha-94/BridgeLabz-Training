public class DigitalWatch {
    public static void main(String[] args) {

        for (int h = 0; h < 24; h++) {
            for (int min = 0; min < 60; min++) {

                System.out.println(h + " : " + min);

                if (h == 13 && min== 0) {

                    System.out.println("Power Cut! Watch Stopped.");

                    break;
                }
            }

            if (h == 13) {
                
                break;
            }
        }
    }
}
