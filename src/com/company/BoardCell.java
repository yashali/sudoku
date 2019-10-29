package com.company;

public class BoardCell {
    int value;
    boolean fixed;

    public BoardCell(int value) {
        this.value = value;
        this.fixed = this.value != 0 ? true : false;
    }
}
