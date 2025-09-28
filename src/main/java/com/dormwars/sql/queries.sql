-- docker exec -it dorm_wars_container psql -U dmendelow -d dorm_wars_db

-- Drop tables if they already exist (for clean re-creation)
DROP TABLE IF EXISTS register_users CASCADE;
DROP TABLE IF EXISTS event_teams CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS tournaments CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS teams CASCADE;
DROP TABLE IF EXISTS schools CASCADE;
DROP TABLE IF EXISTS sports CASCADE;

-- ================================
-- Schools
-- ================================
CREATE TABLE schools (
    school_id SERIAL PRIMARY KEY,
    school_name VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    primary_color VARCHAR(50),
    secondary_color VARCHAR(50),
    school_logo TEXT,
    active BOOLEAN DEFAULT TRUE
);

-- ================================
-- Users
-- ================================
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    school_id INT REFERENCES schools(school_id) ON DELETE SET NULL,
    user_type VARCHAR(50) NOT NULL
);

-- ================================
-- Sports
-- ================================
CREATE TABLE sports (
    sport_id SERIAL PRIMARY KEY,
    sport_name VARCHAR(100) NOT NULL,
    description TEXT,
    abbreviation VARCHAR(20),
    players_per_team INT NOT NULL
);

-- ================================
-- Teams
-- ================================
CREATE TABLE teams (
    team_id SERIAL PRIMARY KEY,
    team_name VARCHAR(100) NOT NULL,
    school_id INT REFERENCES schools(school_id) ON DELETE CASCADE,
    active BOOLEAN DEFAULT TRUE
);

-- ================================
-- Tournaments
-- ================================
CREATE TABLE tournaments (
    tournament_id SERIAL PRIMARY KEY,
    sport_id INT NOT NULL REFERENCES sports(sport_id) ON DELETE CASCADE,
    winner_id INT REFERENCES teams(team_id) ON DELETE SET NULL,
    max_teams INT NOT NULL,
    number_of_rounds INT NOT NULL
);

-- ================================
-- Events
-- ================================
CREATE TABLE events (
    event_id SERIAL PRIMARY KEY,
    location VARCHAR(255),
    datetime TIMESTAMP NOT NULL,
    sport_id INT NOT NULL REFERENCES sports(sport_id) ON DELETE CASCADE,
    is_tournament_game BOOLEAN DEFAULT FALSE,
    status VARCHAR(20) CHECK (status IN ('upcoming','completed','cancel','postponed')),
    short_description VARCHAR(255) NOT NULL,
    long_description TEXT NOT NULL,
    winner_id INT REFERENCES teams(team_id) ON DELETE SET NULL,
    loser_id INT REFERENCES teams(team_id) ON DELETE SET NULL,
    tournament_id INT REFERENCES tournaments(tournament_id) ON DELETE SET NULL,
    round_number INT,
    next_event_id INT REFERENCES events(event_id) ON DELETE SET NULL
);

-- ================================
-- Event Teams
-- ================================
CREATE TABLE event_teams (
    event_team_id SERIAL PRIMARY KEY,
    event_id INT NOT NULL REFERENCES events(event_id) ON DELETE CASCADE,
    team_id INT NOT NULL REFERENCES teams(team_id) ON DELETE CASCADE,
    is_tournament_team BOOLEAN DEFAULT FALSE,
    tournament_id INT REFERENCES tournaments(tournament_id) ON DELETE SET NULL,
    active BOOLEAN DEFAULT TRUE
);

-- ================================
-- Register Users
-- ================================
CREATE TABLE register_users (
    register_user_id SERIAL PRIMARY KEY,
    event_team_id INT NOT NULL REFERENCES event_teams(event_team_id) ON DELETE CASCADE,
    user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE
);

-- sql
SELECT table_schema, table_name
FROM information_schema.tables
WHERE table_type = 'BASE TABLE'
  AND table_schema NOT IN ('pg_catalog','information_schema')
ORDER BY table_schema, table_name;

-- sql
SELECT table_name,
       ordinal_position,
       column_name,
       data_type,
       is_nullable,
       column_default
FROM information_schema.columns
WHERE table_schema = 'public'
ORDER BY table_name, ordinal_position;