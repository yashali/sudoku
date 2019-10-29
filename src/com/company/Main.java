package com.company;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {

        Board board = Board.get_instance();
        while (!board.is_filled()) {
            board.display();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(input);
            int row = Integer.parseInt(stringTokenizer.nextToken());
            int col = Integer.parseInt(stringTokenizer.nextToken());
            int val = Integer.parseInt(stringTokenizer.nextToken());

            //read inputs
            try {
                board.update(row, col, val);
            } catch (InvalidBlockEntryException ex) {
                //do something
            } catch (InvalidColumnEntryException ex) {
                //do something
            } catch (InvalidRowEntryException | ImmuatableValueEdit ex) {
                //do something
            }
            if (board.isGame_over()) {
                System.out.println("Game Over");
                board.display();
                return;
            }
        }
    }
}
