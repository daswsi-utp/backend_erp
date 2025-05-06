-- Insertar roles
INSERT INTO roles (id, name) VALUES 
(1, 'ADMIN'),
(2, 'CRM_COORDINATOR'), 
(3, 'CRM_COMERCIAL')
ON CONFLICT (id) DO NOTHING;

-- Insertar usuarios
INSERT INTO users (id, email, password, dni) VALUES
(1, 'admin@example.com', '12a516f0c7fyc0iXwue01hp5djVMKuJkad1LGqneNkc3Pab2xZnVp2acdf1km1', '12a51678'),
(2, 'user@example.com', '12a51677QHQmZWB.SUBT2QYbzExeZpyKOVE/JyhzEBlZhnvAYnmMAVVEGC', '87654321'),
(3, 'comercial@example.com', '12a51999ABCDEFGH.SUBT2QYbzExeZpyKOVE/JyhzEBlZhnvAYnmMAVVEGX', '11223344')
ON CONFLICT (id) DO NOTHING;

-- Relacionar usuarios con roles
INSERT INTO user_roles (user_id, role_id) VALUES 
(1, 1),  -- admin con rol ADMIN
(2, 2),  -- user con rol CRM_COORDINATOR
(3, 3)   -- nuevo usuario comercial con rol CRM_COMERCIAL
ON CONFLICT (user_id, role_id) DO NOTHING;