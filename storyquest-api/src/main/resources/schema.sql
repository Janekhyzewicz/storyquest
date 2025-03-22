-- Story nodes table
CREATE TABLE IF NOT EXISTS story_nodes (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    background_image VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Choices table
CREATE TABLE IF NOT EXISTS choices (
    id SERIAL PRIMARY KEY,
    story_node_id INTEGER REFERENCES story_nodes(id),
    text VARCHAR(255) NOT NULL,
    target_node_id INTEGER REFERENCES story_nodes(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- User progress table
CREATE TABLE IF NOT EXISTS user_progress (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    current_node_id INTEGER REFERENCES story_nodes(id),
    visited_nodes JSONB,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
