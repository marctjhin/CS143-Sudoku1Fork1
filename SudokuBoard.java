import java.util.*;
import java.io.*;


//Sudoku Board class
public class SudokuBoard {
   //fields
   protected String[][] board;
   

   //constructor
   public SudokuBoard(String FileName) {
      board = new String[9][9];
      
      try {
         Scanner input = new Scanner(new File(FileName));
         int row = 0;    
         while (input.hasNextLine() && row < board.length) {
            String line = input.nextLine();
            
            if (line.length() != board.length) {
               System.out.println(FileName + " format doesnt match requirements X_x \n");
               return;
            }
            //adding file values to board
            for (int col = 0; col < board[row].length; col++) {
               board[row][col] = "" + line.charAt(col);
            }
            row++;
         }
      } catch (FileNotFoundException e) {
         System.out.println("Can't load " + FileName + " :(");
      }
   }

   // check for datas in the board
   private boolean hasValidData() {
      Set<String> valid = new HashSet<>();
      
      for (int i = 1; i <= 9; i++) {
         valid.add("" + i);
      }
      
      valid.add(".");
      
      for (int r = 0; r < 9; r++) {
         for (int c = 0; c < 9; c++) {
            if (!valid.contains(board[r][c])) {
               return false;
            }
         }
      }
      
      return true;
   }

   // check for rows
   private boolean hasValidRows() {
      for (int r = 0; r < 9; r++) {
         Set<String> valid = new HashSet<>();
         
         for (int c = 0; c < 9; c++) {
            String val = board[r][c];
            
               if (!val.equals(".")) {
                  if (valid.contains(val)) {
                  return false;
                  }
                  
               valid.add(val);
               }
         } 
      }
      return true;
   }

   private boolean hasValidMiniSquares() {
      for (int spot = 1; spot <= 9; spot++) {
         String[][] mini = miniSquare(spot);
         Set<String> seen = new HashSet<>();
         
         for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
               String val = mini[r][c];
               if (!val.equals(".")) {
                  if (seen.contains(val)) {
                     return false;
                  }
                  seen.add(val);
               }
            }
         }
         
      }
      return true;
   }


   //helper for toString
   public String printBoard() {
      String sBoard = "+-----------------------+\n";
      for (int r = 0; r < board.length; r++) {
         for (int c = 0; c < 9; c++) {
            if (c == 0 || c == 3 || c == 6) {
               sBoard += "| ";
            }
            sBoard += board[r][c] + " ";
         }
         sBoard += "|\n";    
         if (r == 2 || r == 5) {
               sBoard += "|-------|-------|-------|\n";
         }
      }
      sBoard += "+-----------------------+\n";
      return sBoard;
   }
   
   
   //toString
   public String toString() {
      return printBoard();  

   }
}
