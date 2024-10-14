-- Insert roles
INSERT INTO role (id, name) VALUES (1, 'ADMIN'), (2, 'USER');

-- Insert users
INSERT INTO user (id, username, password, available) VALUES (1, 'A', '{noop}a', true), (2, 'B', '{noop}b', true), (3, 'C', '{noop}c', false);

-- Map users to roles
INSERT INTO user_roles (user_id, roles_id) VALUES (1, 1), (2, 2), (3, 2);

-- Insert tickets 
INSERT INTO tickets (id, title, description, status, user_id) VALUES (1, 'Primo', 'Descrizione no.1', 'To Do', 3), (2, 'Secondo', 'Descrizione no.2', 'To Do', 2), (3, 'Terzo', 'Descrizione no.3', 'To Do', 1);

-- Insert notes
INSERT INTO notes (id, description, data, ticket_id) VALUES (1, 'Note about first ticket', '2024-08-24', 1), (2, 'Note about second ticket', '2022-08-24', 2);

-- Insert categories 
INSERT INTO categories (id, name) VALUES (1, 'Urgent'), (2, 'Standard');

-- Insert tickets to categories	
INSERT INTO categories_ticket (ticket_id, category_id) VALUES (1, 1), (2, 2), (3, 1);
