ROWS = 6
COLS = 7
EMPTY = '.'
PLAYER_ONE = 'X'
PLAYER_TWO = 'O'

board = [[EMPTY for _ in range(COLS)] for _ in range(ROWS)]

def print_board():
    for row in board:
        print(' '.join(row))
    print('0 1 2 3 4 5 6')

def make_move(col, player):
    for i in range(ROWS - 1, -1, -1):
        if board[i][col] == EMPTY:
            board[i][col] = player
            return True
    return False

def check_win(player):
    for row in range(ROWS):
        for col in range(COLS):
            if check_direction(row, col, 1, 0, player) or \
               check_direction(row, col, 0, 1, player) or \
               check_direction(row, col, 1, 1, player) or \
               check_direction(row, col, 1, -1, player):
                return True
    return False

def check_direction(row, col, row_dir, col_dir, player):
    count = 0
    for i in range(4):
        r = row + i * row_dir
        c = col + i * col_dir
        if 0 <= r < ROWS and 0 <= c < COLS and board[r][c] == player:
            count += 1
        else:
            break
    return count == 4

def play_game():
    current_player = PLAYER_ONE
    while True:
        print_board()
        try:
            col = int(input(f"Player {current_player}, enter a column (0-6): "))
            if col < 0 or col >= COLS or not make_move(col, current_player):
                print("Invalid move, try again.")
                continue
        except ValueError:
            print("Please enter a valid number.")
            continue

        if check_win(current_player):
            print_board()
            print(f"Player {current_player} wins!")
            break

        current_player = PLAYER_ONE if current_player == PLAYER_TWO else PLAYER_TWO

play_game()
