package leetcode.backtracking_TrieNode;

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
 */


import java.util.*;
public class SudokuSolver37 {
    public void solveSudoku(char[][] b) {
        if (b.length == 0 || b[0].length == 0) return ;
        List<int[]> ret = new ArrayList<>();

        Set<Integer>[] row = new HashSet[9];
        //initialize
        for (int i = 0 ; i < 9 ; ++i) {
            row[i] = new HashSet<>();
        }
        Set<Integer>[] col = new HashSet[9];
        //initialize
        for (int i = 0 ; i < 9 ; ++i) {
            col[i] = new HashSet<>();
        }
        Set<Integer>[] block = new HashSet[9];
        //initialize
        for (int i = 0 ; i < 9 ; ++i) {
            block[i] = new HashSet<>();
        }


        for (int i = 0 ; i < 9 ; ++i) {
            for (int j = 0 ; j  < 9 ; ++j) {
                if (b[i][j] == '.') {
                    ret.add(new int[] {i , j});
                } else {
                    row[i].add(Character.getNumericValue(b[i][j]));
                    col[j].add(Character.getNumericValue(b[i][j]));
                    block[( i / 3 ) * 3 + j / 3].add(Character.getNumericValue(b[i][j]));
                }
            }
        }
        dfs (0 , ret , row , col , block ,  b);
    }

    private boolean dfs (int index  , List<int[]> ret , Set<Integer>[] row , Set<Integer>[] col , Set<Integer>[] block , char[][] b) {
        if (index == ret.size()) {
            return true ;
        }

        for (int num = 1 ; num <= 9 ; ++num) {
            if (!row[ret.get(index)[0]].contains(num) && !col[ret.get(index)[1]].contains(num) && !block[ (ret.get(index)[0] / 3) * 3 + ret.get(index)[1] / 3].contains(num)) {
                b[ret.get(index)[0]][ret.get(index)[1]] = (char)(num + '0');
                row[ret.get(index)[0]].add(num);
                col[ret.get(index)[1]].add(num);
                block[ (ret.get(index)[0] / 3) * 3 + ret.get(index)[1] / 3].add(num);
                if ( dfs (index + 1 , ret , row , col , block , b) ) {
                    return true;
                }
                b[ret.get(index)[0]][ret.get(index)[1]] = '.';
                row[ret.get(index)[0]].remove(num);
                col[ret.get(index)[1]].remove(num);
                block[ (ret.get(index)[0] / 3) * 3 + ret.get(index)[1] / 3].remove(num);
            }
        }
        return false;
    }
}
