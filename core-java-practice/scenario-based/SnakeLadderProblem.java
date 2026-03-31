
import java.util.Random;
public class SnakeLadderProblem{
  public static final int NO_play=0;
  public static final int LADDER=1;
  public static final int SNAKE=2;

  public static void main(String [] args){
    int player1Pos=0;
    int player2Pos=0;

    int diceCountPlayer1=0;
    int diceCountPlayer2=0;

    int currentPlayer=1; //start with player 1


    while(player1Pos<100 && player2Pos<100){
      
      int dice = (int) (Math.random() * 6) + 1;

     
      int option = (int) (Math.random() * 3) ;  // 0-no play, 1-ladder,2-snake
      System.out.println(" Player "+ currentPlayer + " rolled "+ dice );

      if(currentPlayer == 1)diceCountPlayer1++;
      else diceCountPlayer2++;
      
      if(option==NO_play){
        System.out.println("No play position unchanged");


    }else if(option == LADDER){
      System.out.println("Ladder-move forward by "+ dice );
      if(currentPlayer==1){
      if(player1Pos + dice <= 100)
        player1Pos+= dice;
      System.out.println("Player 1 position: "+ player1Pos);
      }else{
        if(player2Pos + dice <=100)
        player2Pos+= dice;      
      System.out.println("Player 2 position: "+ player2Pos);
    }

    System.out.println("ladder- extra turn-");
    continue;//extra chance
  }else{
    System.out.println("Snake-move backward by "+ dice );
    if(currentPlayer==1){
      player1Pos-= dice;
      if(player1Pos<0) player1Pos=0;
      System.out.println("Player 1 position: "+ player1Pos);
    }else{
      player2Pos-= dice;
      if(player2Pos<0) player2Pos=0;
      System.out.println("Player 2 position: "+ player2Pos);
    }
  }

  currentPlayer=(currentPlayer==1)?2:1;//switch player

    }
    System.out.println("game over gyz");
    
    if(player1Pos==100){
      System.out.println("Player 1 wins the game");
    }else{
      System.out.println("Player 2 wins the game");
    }

    System.out.println("player 1 dice rolls=" + diceCountPlayer1);
    System.out.println("player 2 dice rolls=" + diceCountPlayer2);


  }

}


//uc1 to uc-5  --it is for my understandig 
/*public class SnakeLadderProblem {
  public static void main(String[] args) {
    final int winningPosition = 100;
    
    int playerPosition = 0;// uc1
    int diceCount = 0;

    while (playerPosition != winningPosition) { //uc3

      int diceRolls = (int) (Math.random() * 6) + 1;

     
      int option = (int) (Math.random() * 3) + 1;  // ug2-// 0-no play, 1-ladder,2-snake
      diceCount++;

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
    System.out.println("total dice rolled"+ diceCount)

  }

}
*/