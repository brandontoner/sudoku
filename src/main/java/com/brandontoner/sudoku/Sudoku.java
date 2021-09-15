package com.brandontoner.sudoku;

class Sudoku {
    private static final int SECTION_SIZE = 3;
    private static final int SECTION_COUNT = 3;
    private static final int PUZZLE_SIZE = SECTION_SIZE * SECTION_COUNT;

    private static boolean validAtPos(int[] puzzle, int val, int xin, int yin) {
        // check row and column
        for (int i = 0; i < PUZZLE_SIZE; ++i) {
            if (puzzle[i * PUZZLE_SIZE + xin] == val || puzzle[yin * PUZZLE_SIZE + i] == val) {
                return false;
            }
        }
        // check section
        int ymin = (yin / SECTION_SIZE) * SECTION_SIZE;
        int xmin = (xin / SECTION_SIZE) * SECTION_SIZE;
        int ymax = ymin + SECTION_SIZE;
        int xmax = xmin + SECTION_SIZE;
        for (int y = ymin; y < ymax; ++y) {
            for (int x = xmin; x < xmax; ++x) {
                if (puzzle[y * PUZZLE_SIZE + x] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean solve(int[] puzzle, int index) {
        if (index == puzzle.length) {
            return true;
        }

        if (puzzle[index] != 0) {
            return solve(puzzle, index + 1);
        }

        int y = index / PUZZLE_SIZE;
        int x = index % PUZZLE_SIZE;

        for (int n = 1; n <= PUZZLE_SIZE; ++n) {
            if (validAtPos(puzzle, n, x, y)) {
                puzzle[index] = n;
                if (solve(puzzle, index + 1)) {
                    return true;
                }
            }
        }
        puzzle[index] = 0; // Return the puzzle to its previous state
        return false;
    }

    private static void print(int[] puzzle) {
        for (int y = 0; y < PUZZLE_SIZE; ++y) {
            if (y != 0 && y % SECTION_SIZE == 0) {
                System.out.println("---------------");
            }
            for (int x = 0; x < PUZZLE_SIZE; ++x) {
                if (x != 0 && x % SECTION_SIZE == 0) {
                    System.out.print(" | ");
                }
                System.out.print(puzzle[y * PUZZLE_SIZE + x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] puzzle1 = {0, 2, 0, 0, 0, 0, 0, 0, 7,
                         0, 7, 0, 0, 0, 4, 0, 1, 0,
                         9, 0, 5, 0, 0, 0, 0, 0, 0,
                         0, 8, 0, 6, 3, 0, 0, 0, 2,
                         7, 0, 0, 0, 0, 0, 0, 0, 0,
                         2, 0, 0, 0, 1, 8, 0, 6, 0,
                         0, 0, 0, 0, 0, 0, 4, 0, 9,
                         0, 3, 0, 1, 0, 0, 0, 2, 0,
                         4, 0, 0, 0, 0, 0, 0, 8, 0};
        solve(puzzle1, 0);
        print(puzzle1);
    }
}
