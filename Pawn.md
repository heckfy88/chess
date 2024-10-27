# Chess Console App - Pawn Movement Test Cases

This document provides test cases to validate the pawn movement logic in the Chess Console App. Each command follows the format:
```plaintext
move <from_row> <from_column> <to_row> <to_column>
```

## Table of Contents
1. [Basic Pawn Movement](#basic-pawn-movement)
2. [Pawn Capture](#pawn-capture)
3. [En Passant](#en-passant)
4. [Pawn Promotion](#pawn-promotion)
5. [Illegal Moves (to Test Validation)](#illegal-moves-to-test-validation)

---

### 1. Basic Pawn Movement
These moves test that pawns can move forward correctly by one or two squares.

- **White pawn moves two squares forward from its starting position:**
  ```plaintext
  move 1 0 3 0
  ```

- **Black pawn moves two squares forward from its starting position:**
  ```plaintext
  move 6 0 4 0
  ```

- **White pawn moves one square forward:**
  ```plaintext
  move 1 1 2 1
  ```

- **Black pawn moves one square forward:**
  ```plaintext
  move 6 2 5 2
  ```

---

### 2. Pawn Capture
These moves test that pawns can capture diagonally.

- **White pawn captures black pawn diagonally:**
    1. First, move a black pawn to a position where it can be captured:
       ```plaintext
       move 6 1 4 1
       ```
    2. Capture the black pawn with the white pawn:
       ```plaintext
       move 3 0 4 1
       ```

- **Black pawn captures white pawn diagonally:**
    1. Move a white pawn to a position where it can be captured:
       ```plaintext
       move 1 2 3 2
       ```
    2. Capture the white pawn with the black pawn:
       ```plaintext
       move 4 3 3 2
       ```

---

### 3. En Passant
The en passant rule allows a pawn to capture a pawn that has just moved two squares forward from its starting position, landing beside the capturing pawn.

- **White pawn performs en passant on black pawn:**
    1. Move a black pawn two squares forward:
       ```plaintext
       move 6 4 4 4
       ```
    2. Move a white pawn two squares forward beside the black pawn:
       ```plaintext
       move 1 3 3 3
       ```
    3. White pawn captures the black pawn en passant:
       ```plaintext
       move 3 3 4 4
       ```

---

### 4. Pawn Promotion
These moves test that when a pawn reaches the opposite end of the board, it can be promoted to a queen, rook, bishop, or knight.

- **White pawn reaches the promotion rank (last row):**
    - Move a white pawn incrementally to the last row. For example, assuming there’s no obstacle:
      ```plaintext
      move 6 7 7 7
      ```
    - The console should prompt for a promotion choice (e.g., promote to queen, rook, bishop, or knight).

---

### 5. Illegal Moves (to Test Validation)
These moves check if the program correctly prevents pawns from making invalid moves.

- **White pawn tries to move backward:**
  ```plaintext
  move 3 1 2 1
  ```

- **White pawn tries to move forward two squares after its first move:**
    1. First, move the pawn one square forward:
       ```plaintext
       move 1 1 2 1
       ```
    2. Attempt an invalid two-square move:
       ```plaintext
       move 2 1 4 1
       ```

- **White pawn tries to move sideways:**
  ```plaintext
  move 1 1 1 2
  ```

---

Use these test cases to validate and debug the pawn movement logic in the Chess Console App. Make sure to observe whether each move behaves as expected, particularly for special moves such as en passant and promotion. If any moves behave unexpectedly, review the pawn movement logic to ensure compliance with chess rules.