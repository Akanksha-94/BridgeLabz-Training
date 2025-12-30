public class SnakeLadderProblem {
  public static void main(String[] args) {
    final int winningPosition = 100;
    
    int playerPosition = 0;// uc1

    while (playerPosition != winningPosition) { //uc3

      int diceRolls = (int) (Math.random() * 6) + 1;

     
      int option = (int) (Math.random() * 3) + 1;  // ug2-// 0-no play, 1-ladder,2-snake

      System.out.println("Dice Rolled= " + diceRolls);

      switch (option) {
        case 0:
          System.out.println("no play");
          break;
        case 1:
          System.out.println("ladder");
          if(playerPosition + diceRolls <=winningPosition;){
          playerPosition = playerPosition + diceRolls;
          }else {
            System.out.println("move exceed 100, stay in same position");
          }
          break;
        case 2:
          System.out.println("snake");
          playerPosition = playerPosition - diceRolls;
           if (playerPosition < 0) {
              playerPosition = 0;// reser to 0 if negative
              }
              break;   

      }

     
      System.out.println("player position is " + playerPosition);
    }
    System.out.println("congratulations you reached 100");

  }

}
