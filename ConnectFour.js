const ROWS = 6;
const COLS = 7;
const EMPTY = '.';
const PLAYER_ONE = 'X';
const PLAYER_TWO = 'O';

let board = Array.from({ length: ROWS }, () => Array(COLS).fill(EMPTY));

function printBoard() {
    console.clear();
    board.forEach(row => console.log(row.join(' ')));
    console.log('0 1 2 3 4 5 6');
}

function makeMove(col, player) {
    for (let i = ROWS - 1; i >= 0; i--) {
        if (board[i][col] === EMPTY) {
            board[i][col] = player;
            return true;
        }
    }
    return false;
}

function checkWin(player) {
    const directions = [
        { row: 0, col: 1 },  // Horizontal
        { row: 1, col: 0 },  // Vertical
        { row: 1, col: 1 },  // Diagonal \
        { row: 1, col: -1 }  // Diagonal /
    ];

    for (let row = 0; row < ROWS; row++) {
        for (let col = 0; col < COLS; col++) {
            if (board[row][col] === player) {
                for (let dir of directions) {
                    if (checkDirection(row, col, dir, player)) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}

function checkDirection(row, col, dir, player) {
    for (let i = 0; i < 4; i++) {
        const r = row + i * dir.row;
        const c = col + i * dir.col;
        if (r < 0 || r >= ROWS || c < 0 || c >= COLS || board[r][c] !== player) {
            return false;
        }
    }
    return true;
}

function playGame() {
    let currentPlayer = PLAYER_ONE;
    let gameWon = false;

    while (true) {
        printBoard();
        const col = prompt(`Player ${currentPlayer}, enter a column (0-6): `);
        if (col < 0 || col >= COLS || !makeMove(col, currentPlayer)) {
            console.log("Invalid move, try again.");
            continue;
        }

        if (checkWin(currentPlayer)) {
            printBoard();
            console.log(`Player ${currentPlayer} wins!`);
            gameWon = true;
            break;
        }

        currentPlayer = currentPlayer === PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE;
    }

    if (!gameWon) {
        console.log("It's a draw!");
    }
}

playGame();
