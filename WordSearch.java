import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
    private char[][]data;

    //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;



    private void clear(){
      for(int i = 0; i < data.length; i++){
        for(int j = 0; j<data[i].length; j++){
          data[i][j] = '_';
        }
      }
    }


    public WordSearch( int rows, int cols, String fileName){
      data.clear()
      this.addAllWords()

    }

    public WordSearch(int rows, int cols, String fileName, int randSeed){
  
    }

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */

     private boolean addAllWords(){

     }


     public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){

        }










































    public WordSearch(int rows, int cols){
      if(cols < 0 || rows < 0)
        throw new IllegalArgumentException("No");

      data = new char[rows][cols];
      this.clear();


    }

    /**Set all values in the WordSearch to underscores'_'*/



    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
     public String toString(){
          String output = "";
          for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
              output += data[i][j] + " ";
            }
            output += "\n";
          }
          return output;
        }



    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
     public boolean addWordHorizontal(String word,int row, int col){
     if (word.length() > data[row].length - col) return false;
     int col2 = col;
     for (int i = 0; i < word.length(); i++){
       if (data[row][col2] != '_' && data[row][col2] != word.charAt(i)){
         return false;
       }
       col2++;
     }
     for (int i = 0; i < word.length(); i++){
       data[row][col] = word.charAt(i);
       col++;
     }
     return true;
     }

    /**Attempts to add a given word to the specified position of the WordGrid.
      *The word is added from top to bottom, must fit on the WordGrid, and must
      *have a corresponding letter to match any letters that it overlaps.
      *
      *@param word is any text to be added to the word grid.
      *@param row is the vertical locaiton of where you want the word to start.
      *@param col is the horizontal location of where you want the word to start.
      *@return true when the word is added successfully. When the word doesn't fit,
      *or there are overlapping letters that do not match, then false is returned.
      *and the board is NOT modified.
      */
     public boolean addWordVertical(String word,int row, int col){
     if (word.length() > data.length - row) return false;
     int row2 = row;
     for (int i = 0; i < word.length(); i++){
       if (data[row2][col] != '_' && data[row2][col] != word.charAt(i)){
         return false;
       }
       row2++;
     }
     for (int i = 0; i < word.length(); i++){
       data[row][col] = word.charAt(i);
       row++;
     }
     return true;
     }


     public boolean addWordDiagonal(String word, int row, int col){
       if (word.length() > data.length - row || word.length() > data[row].length - col) return false;
       int row2 = row;
       int col2 = col;
       for (int i = 0; i < word.length(); i++){
         if (data[row2][col2] != '_' && data[row2][col2] != word.charAt(i)){
           return false;
         }
         row2++;
         col2++;
       }
       for (int i = 0; i < word.length(); i++){
        data[row][col] = word.charAt(i);
        row++;
        col++;
      }
      return true;
    }
 }
