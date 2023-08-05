public class SudokuSolver {

    private static final int Grid_size = 9;

    public static void main(String[] args) {
        int[][] board={
                {6, 7, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 0, 0, 6, 0, 0, 0, 3},
                {0, 0, 8, 7, 0, 3, 5, 0, 0},
                {0, 8, 0, 4, 0, 1, 0, 0, 2},
                {0, 0, 5, 0, 0, 0, 0, 9, 0},
                {0, 0, 0, 0, 7, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 4, 0, 3, 0, 8, 0, 0, 1}
        };
        printBoard(board);
        System.out.println();

        if(solveBoard(board)){
            System.out.println("Solved succesfully!");
        }else {
            System.out.println("Unsolvable board :(");
        }
        System.out.println();

        printBoard(board);

    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < Grid_size; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for (int column = 0 ; column < Grid_size; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberinRow(int[][] board, int number, int row){
        for (int i = 0; i<Grid_size;i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberinColumn(int[][] board, int number, int column) {
        for (int i = 0; i < Grid_size; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberinBox(int[][] board, int number,int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i<localBoxRow + 3; i++){
            for(int j = localBoxColumn; j<localBoxColumn + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }
    private  static  boolean isValidPlacement(int[][] board, int number, int row, int column){
        return !isNumberinRow(board,number,row) &&
                !isNumberinColumn(board,number,column) &&
                !isNumberinBox(board,number,row,column);
    }
    private static boolean solveBoard(int[][] board){
        for (int row = 0; row < Grid_size; row++){
            for (int column = 0; column < Grid_size; column++){
                if(board[row][column] == 0){
                    for (int numberToTry = 1; numberToTry <= Grid_size; numberToTry++){
                        if(isValidPlacement(board,numberToTry,row,column)){
                            board[row][column] = numberToTry;
                            if(solveBoard(board)){
                                return true;
                            }else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
