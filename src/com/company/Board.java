package com.company;


public class Board {

    private final static int size = 3;
    private final int[][] dp;
    private final BoardCell[][] cells;
    private CellArea[] columns;
    private int zeros = 0;
    private boolean game_over;
    private CellArea[] rows;
    private CellArea[] blocks;

    private Board() {
        this.cells = new BoardCell[size * size][size * size];
        this.dp = new int[size * size][size * size];
        this.rows = new CellArea[size * size];
        this.columns = new CellArea[size * size];
        this.blocks = new CellArea[size * size];

        for (int i = 0; i < size * size; i++) {
            columns[i] = new CellArea(0, i, (size * size) - 1, i, this);
            rows[i] = new CellArea(i, 0, i, (size * size) - 1, this);

        }
        int r, c;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                r = i * size;
                c = j * size;
                this.blocks[i * size + j] = new CellArea(r, c, r + size - 1, c + size - 1, this);

                for (int x = r; x < r + size; x++)
                    for (int y = c; y < c + size; y++) {
                        this.dp[x][y] = i * size + j;
                    }
            }
        int[][] temp = {
                {7, 4, 3, 9, 5, 1, 6, 8, 2},
                {1, 6, 2, 4, 8, 7, 3, 9, 5},
                {9, 5, 8, 6, 3, 2, 7, 1, 4},
                {2, 1, 9, 8, 7, 3, 5, 4, 6},
                {3, 7, 4, 5, 6, 9, 1, 2, 8},
                {5, 8, 6, 1, 2, 4, 9, 7, 3},
                {4, 9, 5, 2, 1, 6, 8, 3, 7},
                {8, 2, 7, 3, 9, 5, 4, 6, 1},
                {6, 3, 1, 7, 4, 8, 2, 5, 0}
        };
        for (int i = 0; i < size * size; i++)
            for (int j = 0; j < size * size; j++) {
                cells[i][j] = new BoardCell(temp[i][j]);
                if (temp[i][j] == 0) this.zeros++;
            }

    }

    public static Board get_instance() {
        return BoardBuilder.board;
    }

    public boolean isGame_over() {
        return game_over;
    }

    public void setGame_over(boolean game_over) {
        this.game_over = game_over;
    }

    public BoardCell[][] getCells() {
        return cells;
    }

    public boolean is_filled() {
        return false;
    }

    public void display() {
        for (int i = 0; i < size * size; i++) {
            if (i % 3 == 0) System.out.println("+-------+-------+-------+");
            for (int j = 0; j < size * size; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(cells[i][j].value + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-----------------------+");
    }

    public boolean update(int row, int col, int val) throws InvalidRowEntryException, InvalidColumnEntryException, InvalidBlockEntryException, ImmuatableValueEdit {
        if (this.columns[col].verify(val)) {
            throw new InvalidColumnEntryException();
        }
        if (this.rows[row].verify(val)) {
            throw new InvalidRowEntryException();
        }
        if (this.blocks[find(row, col)].verify(val)) {
            throw new InvalidBlockEntryException();
        }
        if (cells[row][col].fixed)
            throw new ImmuatableValueEdit();
        cells[row][col].value = val;
        this.zeros--;
        if (this.zeros == 0)
            this.game_over = true;
        return true;
    }

    private int find(int row, int col) {
        return dp[row][col];
    }

    private static class BoardBuilder {
        public static final Board board = new Board();
    }


}
