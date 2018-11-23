package leetcode.Number;

import java.util.HashSet;

class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {

        HashSet<Character> set = new HashSet<Character>();

//check for each row
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                if(set.contains(board[i][j])){
                    return false;
                }
                set.add(board[i][j]);
            }
            set.clear();
        }

//check for each column
        for(int j=0;j<9;j++){
            for(int i=0;i<9;i++){
                if(board[i][j]=='.'){
                    continue;
                }
                if(set.contains(board[i][j])){
                    return false;
                }
                set.add(board[i][j]);
            }
            set.clear();
        }

//check for each sub-grid
        for(int k=0;k<9;k++){
            for(int i=(k/3)*3;i<(k/3)*3+3;i++){
                for(int j=(k%3)*3;j<(k%3)*3+3;j++){
                    if(board[i][j]=='.'){
                        continue;
                    }
                    if(set.contains(board[i][j])){
                        return false;
                    }
                    set.add(board[i][j]);
                }
            }
            set.clear();
        }

        return true;
    }


    public static void main(String[] args){
        ValidSudoku vs=new ValidSudoku();
        char[][] c= {
            {'2', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(vs.isValidSudoku(c));


    }
}
