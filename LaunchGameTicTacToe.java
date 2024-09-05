import java.util.Scanner;
import java.util.Random;
class TicTacToe
{
     static char[][] board;//instantiation is not done, just declaration is done
    //instantiation is done when object for tictactoe class is created
    public TicTacToe()
    {
        board=new char[3][3];
        initBoard();
    }

    void initBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                board[i][j]=' ';
            }
            
        }
    }

    static void dispBoard()
    {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++)
        {
            System.out.print("| ");
            for (int j = 0; j < board.length; j++)
            {
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
     static void placeMark(int r,int c,char mark)
    {
        //check for validity of row and column
        if(r>=0 && r<=2 && c>=0 && c<=2)
        {
            board[r][c]=mark;
        }
        else{
            System.out.println("Invalid position");
        }
    }
    static boolean checkColWin()
    {
        for(int j=0;j<=2;j++)
        {
            if( board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j])
            {
                //player won column-wise
                return true;
            }
        }
        //not won column-wise
        return false;
    }
     static boolean checkRowWin()
    {
        for(int i=0;i<=2;i++)
        {
            if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2])
            {
                //row-wise win
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagWin()
    {
        if(( board[0][0]!=' '&& board[0][0]==board[1][1] && board[1][1]==board[2][2])||(board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    static boolean checkDraw()
    {
        for(int i=0;i<=2;i++)
        {
            for(int j=0;j<=2;j++)
            {
                if(board[i][j]==' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
}
 abstract class Player{
    String name;
    char mark;
    abstract void makeMove();
    boolean isValidMove(int r,int c)
    {
        if(r>=0 && r<=2 && c>=0 && c<=2)
        {
            //inorder to access the board which is in the TicTacToe class with the class name, we should make it as static
            if(TicTacToe.board[r][c]==' ')
            {
                //it is a valid move
                return true;
            }
        }
        //either it is out-of-bounds or that specific place is occupied with mark
        return false;
    }

}
class HumanPlayer extends Player
{
    HumanPlayer(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner s=new Scanner(System.in);
        int r;
        int c;
        do{
            System.out.println("Enter row and col:");
            r=s.nextInt();
            c=s.nextInt();
        }while(!isValidMove(r, c));
        TicTacToe.placeMark(r,c,mark);
    }
}

class AIPlayer extends Player
{
    AIPlayer(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner s=new Scanner(System.in);
        int r;
        int c;
        do{
            Random random=new Random();
            r=random.nextInt(3);//generates random numbers exclusive the limit mentioned from 0.
            c=random.nextInt(3);
        }while(!isValidMove(r, c));
        TicTacToe.placeMark(r,c,mark);
    }
}

public class LaunchGameTicTacToe
{
    public static void main(String[] args) {
        TicTacToe t=new TicTacToe();
        TicTacToe.dispBoard();
        
       HumanPlayer p1= new HumanPlayer("Bob",'X');
       AIPlayer p2=new AIPlayer("AI",'O');
       //assigning the player as current player
       Player cp; 
       cp=p1;
       while(true)
       {
        System.out.println(cp.name+"'s turn");
       cp.makeMove();
       TicTacToe.dispBoard();
       if(TicTacToe.checkColWin()||TicTacToe.checkRowWin()||TicTacToe.checkDiagWin())
       {
        System.out.println(cp.name+" has won");
        break;
       }
       else if(TicTacToe.checkDraw())
       {
        System.out.println("Game is a draw");
        break;
       }
       else{
        if(cp==p1)
        {
            cp=p2;
        }
        else{
            cp=p1;
        }
       }
       }


    }
}