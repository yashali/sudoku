package com.company;

import java.util.HashSet;
import java.util.Set;

public class CellArea {
    Board board;
    int x1, x2, y1, y2;

    public CellArea(int x1, int y1, int x2, int y2, Board board) {
        this.board = board;
        this.x1 = x1;
        this.x2 = x2;
        this.y2 = y2;
        this.y1 = y1;
    }

    public boolean verify(int val) {
        Set<Integer> set = new HashSet<Integer>(); //set
        for (int x = this.x1; x <= this.x2; x++)
            for (int y = this.y1; y <= this.y2; y++) {
                if (this.board.getCells()[x][y].fixed)
                    return false;
                set.add(this.board.getCells()[x][y].value); // put in set
            }
        if(set.contains(val)) return false; //repetition
        return true;
    }
}
