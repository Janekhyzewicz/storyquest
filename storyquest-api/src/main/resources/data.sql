-- Only insert if the table is empty
INSERT INTO story_nodes (id, title, description, background_image) 
SELECT 1, 'Forest Entrance', 'You find yourself at the entrance of a mysterious forest. The path ahead splits in two directions.', 'forest_entrance'
WHERE NOT EXISTS (SELECT 1 FROM story_nodes WHERE id = 1);

-- Insert choices for the first node
INSERT INTO choices (story_node_id, text, target_node_id) 
SELECT 1, 'Take the dark path to the left', 2
WHERE NOT EXISTS (SELECT 1 FROM choices WHERE story_node_id = 1 AND target_node_id = 2);

INSERT INTO choices (story_node_id, text, target_node_id) 
SELECT 1, 'Take the bright path to the right', 3
WHERE NOT EXISTS (SELECT 1 FROM choices WHERE story_node_id = 1 AND target_node_id = 3);

-- Insert more story nodes
INSERT INTO story_nodes (id, title, description, background_image) 
SELECT 2, 'Dark Path', 'The path is shrouded in darkness. You hear strange sounds all around you.', 'dark_path'
WHERE NOT EXISTS (SELECT 1 FROM story_nodes WHERE id = 2);

INSERT INTO story_nodes (id, title, description, background_image) 
SELECT 3, 'Bright Path', 'Sunlight filters through the trees on this path. It seems peaceful.', 'bright_path'
WHERE NOT EXISTS (SELECT 1 FROM story_nodes WHERE id = 3);

-- Add more choices
INSERT INTO choices (story_node_id, text, target_node_id) 
SELECT 2, 'Continue deeper into the darkness', 4
WHERE NOT EXISTS (SELECT 1 FROM choices WHERE story_node_id = 2 AND target_node_id = 4);

INSERT INTO choices (story_node_id, text, target_node_id) 
SELECT 2, 'Turn back to the forest entrance', 1
WHERE NOT EXISTS (SELECT 1 FROM choices WHERE story_node_id = 2 AND target_node_id = 1);

INSERT INTO choices (story_node_id, text, target_node_id) 
SELECT 3, 'Explore a small cabin visible through the trees', 5
WHERE NOT EXISTS (SELECT 1 FROM choices WHERE story_node_id = 3 AND target_node_id = 5);

INSERT INTO choices (story_node_id, text, target_node_id) 
SELECT 3, 'Continue along the bright path', 6
WHERE NOT EXISTS (SELECT 1 FROM choices WHERE story_node_id = 3 AND target_node_id = 6);

-- Add more nodes
INSERT INTO story_nodes (id, title, description, background_image) 
SELECT 4, 'Stone Circle', 'You discover an ancient stone circle. The air feels charged with energy.', 'stone_circle'
WHERE NOT EXISTS (SELECT 1 FROM story_nodes WHERE id = 4);

INSERT INTO story_nodes (id, title, description, background_image) 
SELECT 5, 'Cabin', 'An old wooden cabin stands in a small clearing. Smoke rises from the chimney.', 'cabin'
WHERE NOT EXISTS (SELECT 1 FROM story_nodes WHERE id = 5);

INSERT INTO story_nodes (id, title, description, background_image) 
SELECT 6, 'Forest Clearing', 'You emerge into a peaceful clearing. Sunlight streams down and wildflowers sway in the breeze.', 'forest_clearing'
WHERE NOT EXISTS (SELECT 1 FROM story_nodes WHERE id = 6);
