INSERT INTO users(id,login, email, first_name, last_name, encrypted_password) VALUES
(1,'john', 'john@gmail.com', 'John', 'Smith', 'dsbfjdsfg'),
(2,'ann', 'annn@gmail.com', 'Ann', 'Willson', 'hfhgfhhgf'),
(3,'steve', 'steve@gmail.com', 'Steve', 'Fox', 'ertertas')
ON CONFLICT (id) DO NOTHING;




INSERT INTO reviews(id,user_id,product_id,rating,content) VALUES
(1,1,1,4,''),
(2,1,2,7,''),
(3,1,3,5,''),
(4,1,4,3,''),
(5,2,1,6,'')
ON CONFLICT (id) DO NOTHING;

INSERT INTO items_in_carts (id,user_id,product_id,amount) VALUES
(1,1,1,2),
(2,1,2,3),
(3,1,3,1),
(4,2,1,1),
(5,2,5,2)
ON CONFLICT (id) DO NOTHING;