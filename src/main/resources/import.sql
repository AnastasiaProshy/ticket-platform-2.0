-- Insert roles
INSERT INTO role (id, name) VALUES (1, 'ADMIN'), (2, 'USER');

-- Insert users
INSERT INTO user (id, username, password, available, email) VALUES (1, 'Nastia', '{noop}nastia', true, 'nastia@example.com'), (2, 'Vitia', '{noop}vitia', true, 'vitia@example.com'), (3, 'Sasha', '{noop}sasha', true, 'sasha@example.com'), (4, 'Pasha', '{noop}pasha', false, 'pasha@example.com'), (5, 'Shura', '{noop}shura', true, 'shura@example.com');

-- Map users to roles
INSERT INTO user_roles (user_id, roles_id) VALUES (1, 1), (2, 2), (3, 2), (4, 2), (5, 1);

-- Insert tickets 
INSERT INTO tickets (id, title, description, status, user_id) VALUES (1, 'Primo', 'Descrizione no.1', 'To Do', 3), (2, 'Secondo', 'Descrizione no.2', 'To Do', 2), (3, 'Terzo', 'Descrizione no.3', 'Completed', 2), (4, 'Quarto', 'Descrizione no.4', 'To Do', 2), (5, 'Quinto', 'Descrizione no.5', 'In Progress', 3), (6, 'Sesto', 'Descrizione no.6', 'To Do', 3),  (7, 'Settimo', 'Descrizione no.7', 'Completed', 4); 

-- Insert notes
INSERT INTO notes (id, description, data, ticket_id) VALUES (1, 'Note about first ticket', '2024-08-24', 1), (2, 'Note about second ticket', '2022-08-24', 2);

-- Insert categories 
INSERT INTO categories (id, name) VALUES (1, 'Urgent'), (2, 'Standard');

-- Insert tickets to categories	
INSERT INTO categories_ticket (ticket_id, category_id) VALUES (1, 1), (2, 2), (3, 1), (4, 1), (5, 2), (6, 1), (7, 2);
