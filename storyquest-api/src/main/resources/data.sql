-- Clear existing data (optional)
DELETE FROM choices WHERE id > 0;
DELETE FROM story_nodes WHERE id > 0;

-- Insert story nodes
INSERT INTO story_nodes (id, title, description, background_image) 
VALUES (1, 'Forest Entrance', 'You find yourself at the entrance of a mysterious forest. The path ahead splits in two directions.', 'forest_entrance')
ON CONFLICT (id) DO NOTHING;

INSERT INTO story_nodes (id, title, description, background_image) 
VALUES (2, 'Dark Path', 'The path is shrouded in darkness. You hear strange sounds all around you.', 'dark_path')
ON CONFLICT (id) DO NOTHING;

INSERT INTO story_nodes (id, title, description, background_image) 
VALUES (3, 'Bright Path', 'Sunlight filters through the trees on this path. It seems peaceful.', 'bright_path')
ON CONFLICT (id) DO NOTHING;

-- Insert choices
INSERT INTO choices (story_node_id, text, target_node_id) 
VALUES (1, 'Take the dark path to the left', 2)
ON CONFLICT DO NOTHING;

INSERT INTO choices (story_node_id, text, target_node_id) 
VALUES (1, 'Take the bright path to the right', 3)
ON CONFLICT DO NOTHING;
