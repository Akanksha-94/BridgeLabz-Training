import java.util.Random;
public class SnakeLadderProblem{

  public static void main(String[] args) {
    int player1Pos=0;
    int player2Pos=0;
    Random random=new Random();

    int[][] snakes={ //snake :head--tail
      {99,54},
      {70,55},
      {52,43},
      {23,2}

    };

    int [][]ladders={ //ladder: start--end
      {4,25},
      {13,46},
      {74,93},
      {50,69},

    };
    int currentPlayer=1;
    while(player1Pos<100 && player2Pos<100){
      int dice=random.nextInt(6)+1;
      System.out.println("Player "+ currentPlayer + "rolled: "+ dice);

      if(currentPlayer==1){
        player1Pos=movePlayer(player1Pos,dice,snakes,ladders);
        System.out.println("player 1 position = "+ player1Pos);
        if(player1Pos ==100)break;
        currentPlayer=2;
      }else{
        player2Pos=movePlayer(player2Pos,dice,snakes,ladders);
        System.out.println("player 2 position = " + player2Pos);
        if(player2Pos==100)break;
        currentPlayer =1;
      }
      System.out.println(" ");
    }

    if(player1Pos ==100){
      System.out.println("player 1 wins!");
    }else{
      System.out.println("player 2 wins!");
    }


   
   

    }

     public static int movePlayer(int position, int dice, int[][]snakes , int[][] ladders){

      //move only if within 100

      if(position + dice <=100){
        position=position+ dice;

      }

      //check ladder-
      for(int i=0;i< ladders.length;i++){

        int start=ladders[i][0]; //ladder start
        int end =ladders[i][1]; //ladder end

        if(position==start){
          System.out.println(" ladder found -now going up words frpm "+ start + "to "+ end);
          position=end;
        }
      }

      //check snacks-
      for(int i=0;i<snakes.length;i++){

        int head =snakes[i][0]; //snake head
        int tail=snakes[i][1]; //snack tail

        if(position == head){
          System.out.println("snake bite- going down words from "+ head + "to "+ tail);
          position=tail;
        }
      }
        return position;
       
  }
}