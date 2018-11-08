public class WordSearch{
  private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    private int row;
    private int col;





    public WordSearch(int rows,int cols){
      col = cols;
      row = rows;
      for(int i = 0; i < rows; i++){
        for(int j = 0; j<cols; j++){
          data[i][j] = '_';
        }
      }


    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for(int i = 0; i < data.length; i++){
        for(int j = 0; j<data.length; j++){
          data[i][j] = '_';
        }
      }
    }


    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String ans = "";
      for(int i = 0; i < data.length; i++){
        if(i > 0)
          ans+="/n";
        for(int j = 0; j<data.length; j++){
          ans += data[i][j];
        }
      } return ans;
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
     public boolean addWordHorizontal(String word,int rows, int cols){
      if(data[rows].length - cols < word.length()){
        return false;
      }
      for(int i = cols; i < cols + word.length(); i++){
        data[rows][i] = word.charAt(i);
      } return true;
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
    public boolean addWordVertical(String word,int rows, int cols){
      if(data[cols].length - rows < word.length()){
        return false;
      }
      for(int i = rows; i < rows + word.length(); i++){
        data[i][rows] = word.charAt(i);
      } return true;
    }

}
