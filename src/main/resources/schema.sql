CREATE TABLE IF NOT EXISTS scripture_books (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS verses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    reference VARCHAR(100) NOT NULL,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES scripture_books(id)
);

CREATE TABLE IF NOT EXISTS game_sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100),
    score INT DEFAULT 0,
    level_reached INT DEFAULT 1,
    lives_remaining INT DEFAULT 3,
    current_streak INT DEFAULT 0,
    best_streak INT DEFAULT 0,
    correct_answers INT DEFAULT 0,
    incorrect_answers INT DEFAULT 0,
    session_start TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    session_end TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    verse_id BIGINT NOT NULL,
    question_text TEXT NOT NULL,
    correct_answer TEXT NOT NULL,
    option_a TEXT NOT NULL,
    option_b TEXT NOT NULL,
    option_c TEXT NOT NULL,
    option_d TEXT NOT NULL,
    game_mode VARCHAR(20) NOT NULL,
    difficulty_points INT DEFAULT 10,
    FOREIGN KEY (verse_id) REFERENCES verses(id)
);