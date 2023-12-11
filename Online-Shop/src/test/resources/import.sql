INSERT INTO users(id,login, email, first_name, last_name, encrypted_password) VALUES
(1,'john', 'john@gmail.com', 'John', 'Smith', 'dsbfjdsfg'),
(2,'ann', 'annn@gmail.com', 'Ann', 'Willson', 'hfhgfhhgf'),
(3,'steve', 'steve@gmail.com', 'Steve', 'Fox', 'ertertas'),
(4,'kate', 'kate@gmail.com', 'Kate', 'London', 'dgfdfdfgdf')
ON CONFLICT (id) DO NOTHING;




INSERT INTO reviews(id,user_id,product_id,rating,content) VALUES
(1,1,1,4,'słaba jakość, długo się ładuje i się zawiesza'),
(2,1,2,7,'bardzo fajne'),
(3,1,3,5,'może być'),
(4,1,4,3,'nie podoba mi się'),
(5,2,1,6,'dziecko się ucieszyło'),
(6,3,1,5,'za tą cene mogło by być lepiej'),
(7,4,1,7,'bardzo przydatny gadżet'),
(8,2,2,6,'spodziewałam się lepszej jakości ale ogólnie jest ok'),
(9,3,2,3,'nie podoba mi się'),
(10,3,3,6,'super produkt')
ON CONFLICT (id) DO NOTHING;

INSERT INTO items_in_carts (id,user_id,product_id,amount) VALUES
(1,1,1,2),
(2,1,2,3),
(3,1,3,1),
(4,2,1,1),
(5,2,5,2)
ON CONFLICT (id) DO NOTHING;