import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
    private char[][]data;

    //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String> wordsToAdd = new ArrayList<String>();

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String> wordsAdded = new ArrayList<String>();


//clear function just to set everything to "_"
    private void clear(){
      for(int i = 0; i < data.length; i++){
        for(int j = 0; j<data[i].length; j++){
          // loop through everything and set it to _
          data[i][j] = '_';
        }
      }
    }


    public WordSearch( int rows, int cols, String fileName) throws FileNotFoundException{
      //Make sure there are rows and columns
      if(rows < 1 || cols < 1){
        throw new IllegalArgumentException();
      }

      //Now see if the file exists and if not make a readable error message for the user
      try{
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      } catch(FileNotFoundException e) {
      System.out.println("File not found: " + fileName);
      System.exit(1);
    }
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while(in.hasNext()){
        wordsToAdd.add(in.next());
      }

      randgen = new Random();
      seed = randgen.nextInt();
      data = new char[rows][cols];
      this.clear();
      this.addAllWords();

    }

    public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
      //Make sure there are rows and columns
      if(rows < 1 || cols < 1){
        throw new IllegalArgumentException();
      }

      //Now see if the file exists and if not make a readable error message for the user
      try{
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      } catch(FileNotFoundException e) {
      System.out.println("File not found: " + fileName);
      System.exit(1);
    }
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      while(in.hasNext()){
        wordsToAdd.add(in.next());
      }

      //use randSeed
      data = new char[rows][cols];
      seed = randSeed;
      randgen = new Random(randSeed);
      this.clear();
      this.addAllWords();
    }


    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
     public String letters = "qwertyuiopasdfghjklzxcvbnm";
     private void fillInWords(){
       for(int j = 0; j < data.length; j++){
         for(int k = 0; k < data[j].length; k++){
           if(data[j][k] == '_'){
             data[j][k] = letters.charAt(randgen.nextInt(26));
           }
         }
       }
     }

     private void addAllWords(){
       int counter = 0;
       //use 70 tries per word
       //Loop through every term in words to a
       while(wordsToAdd.size() > 1 && counter < 100){
         int s = Math.abs(randgen.nextInt()%wordsToAdd.size());

      //test random words and arrangements
        if(addWord(wordsToAdd.get(s),
                  Math.abs(randgen.nextInt(data.length)), Math.abs(randgen.nextInt(data[0].length)),
                  randgen.nextInt(3) - 1, randgen.nextInt(3) - 1 )) {
                    counter = 0;
                    wordsAdded.add(wordsToAdd.get(s));
                    wordsToAdd.remove(s);

                  } else {
                    counter ++;
                  }

      //If it's false try again and add 1 to the counter
      //If it's true add the word and move on to the next one
       }
     }

     //Make a helper function to try and isolate the problem and fix it
     private boolean checkWord(String word,int row, int col, int rowIncrement, int colIncrement){
       //make sure you're actuall adding a row
        if (rowIncrement == 0 && colIncrement == 0) {
                return false;
        }
      //put all the other problems in a try catch block to prevent errors
        try {
                int r = row;
                int c = col;
                //loop through and make sure at each point in the word
                //that the slot is either empty or has a shared letter with the word
                for (int i = 0; i < word.length(); i++, r += rowIncrement, c += colIncrement) {
                        char letter = word.charAt(i);
                        if (data[r][c] != letter && data[r][c] != '_') {
                                return false;
                        }
                }
                return true;
        } catch (ArrayIndexOutOfBoundsException e) {
                return false;
        }
}


private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
        if (!checkWord(word, row, col, rowIncrement, colIncrement)) {
                return false;
        }
        int x = col;
        int y = row;
        for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                data[x][y] = letter;
                x += colIncrement;
                y += rowIncrement;
        }
        return true;
}




        public String toString(){
             String output = "|";
             for (int i = 0; i < data.length; i++){
               for (int j = 0; j < data[i].length; j++){
                 output += (data[i][j] + " ").toUpperCase();
               }
               output += "|\n|";
             }
             return output;
           }
        //Parse integers command = Integer.parseInt(args[j])
        public static void main(String[] args)throws FileNotFoundException{
          try{
            //try for all 3 scenarios where input is correct, catch incorrect input error
            if(args.length == 3){
              int rows = Integer.parseInt(args[0]);
              int columns = Integer.parseInt(args[1]);
              WordSearch ans = new WordSearch(rows, columns, args[2]);
              ans.fillInWords();
              System.out.println(ans);


            }
            if(args.length == 4 || (args.length == 5 && !args[4].equals("key"))){
              int rows = Integer.parseInt(args[0]);
              int columns = Integer.parseInt(args[1]);
              WordSearch ans = new WordSearch(rows, columns, args[2], Integer.parseInt(args[3]));
              ans.fillInWords();
              System.out.println(ans);
            }

            if(args.length == 5 && args[4].equals("key")){
              int rows = Integer.parseInt(args[0]);
              int columns = Integer.parseInt(args[1]);
              WordSearch ans = new WordSearch(rows, columns, args[2], Integer.parseInt(args[3]));
              System.out.println(ans);
              ans.fillInWords();
              System.out.println(ans);
              System.out.println("The seed was " + args[3]);
            }
            //Number Format exception = improper input; file not found caught already in the constructor
          } catch(NumberFormatException e){
              System.out.println("Wrong input \n usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
              System.exit(1);
            }
          }


//OLDER FUNCTIONS


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
