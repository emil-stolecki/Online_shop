CREATE TABLE roles(
	id SERIAL PRIMARY KEY,
	name VARCHAR(10)
);

INSERT INTO roles (name) VALUES
  ('USER'),
  ('MOD'),
  ('ADMIN'),
  ('BANNED');


CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  seller VARCHAR(50),
  price NUMERIC,
  description VARCHAR(800),
  amountInStock INTEGER,
  preview VARCHAR(50)
  
);


CREATE TABLE categories(
	id SERIAL PRIMARY KEY,
	name VARCHAR(25)
);


CREATE TABLE products_categories (
	product_id BIGINT REFERENCES products(id), 
	cat_id BIGINT REFERENCES categories(id),
	CONSTRAINT products_categories_id PRIMARY KEY(product_id,cat_id)

);

INSERT INTO categories(name) VALUES
('Elektronika'),
('Moda'),
('Dom'),
('Dziecko'),
('Rozrywka'),
('Sport'),
('Turystyka'),
('Sztuka'),
('Edukacja');


INSERT INTO products(name,seller,price,amountInStock,description) VALUES

('Smartwatch elegancki', 'NejtStyle', 199.90,500, '{Stan:Nowy, Faktura: Tak, Model: AKTIVE 40, Kolor: Złoty, Materiał: Stal}'),
('Ładowarka do telefonu USB-C','Baseus',39.94,1000, '{Stan:Nowy, Faktura: Tak, Kolor:czarny}'),
('Smartfon Motorola edge 30 neo','X-kom',949.00,300,'{Stan:Nowy, Faktura: Tak, Kolor:czarny} Motorola edge 30 neo 8/128 GB Black Onyx, ekran 6,28" procesor Qualcomm Snapdragon 695, szybkie ładowanie'),
('Smartfon Xiaomi POCO X5','X-kom', 959.04,300,'{Stan:Nowy, Faktura: Tak,Model:POCO X5 5g Kolor:czarny}'),
('Powerbank EPB-114','Extralink',151.05,800,'{Stan:Nowy, Faktura: Tak, Pojemność: 50000mAh, Napięcie nominalne:5V} Power bank Extralink EPB-112 to kompaktowy power bank w przystępnej cenie. Pojemność 50000 mAh wystarcza do wielokrotnego naładowania urządzenia a cztery porty wyjściowe pozwalają ładować kilka urządzeń jednocześnie.'),
('Drukarka laserowa BROTHER HL-1222WE','Electro',459.00,200,'{Stan:Nowy, Faktura: Tak, Marka:Brother, Kolor:Czarny}'),
('Myszka Bezprzewodowa BT','Mobes',15.83,1000,'{Stan:Nowy, Faktura: Tak,Kolor:Czarny, Sensor: Laserowy} Myszka idealnie sprawdzi się do pracy z komputerem, laptopem, a nawet tabletem!'),
('Tablet Lenovo M10HD','Media Expert',599.00,300,'{Stan:Nowy, Faktura: Tak,Kolor:Srebrny,Model:M10 HD TB-X306XA}'),
('Kingston Pendrive 128 GB','RTVEUROAGD',30.70,2000,'{Stan:Nowy, Faktura: Tak,Model:Data Traveler Exodia M, Interfejs:USB 3.2, Pojemność:128}'),
('Słuchawki Gamingowe SENNHEISER','ITX24',229.00,600,'{Stan:Nowy, Faktura: Tak, Model:GSP500} Fabrycznie zapakowane, profesjonalne słuchawki gamingowe, renomowanej firmy na świecie'),

('Bluza Malfini r. L Jacket 501','kstore',53.42,2000,'{Stan:Nowy, Faktura: Tak, Płeć: mężczyzna, Rozmiar:L, Kolor:Czarny}'),
('Buty śniegowce damskie','Hurtownia Moda', 84.59,1500,'{Stan:Nowy, Faktura: Tak,Płeć:Kobieta,Rozmiar:38,Kolor:Szary} Modny ponadczasowy fason, idealnie dopasuje się do wielu stylizacji! Świetnie prezentuje się na nodze.'),
('Bluza z kapturem adidas Entrada','Adidas',74.99,2500,'{Stan:Nowy, Faktura: Tak,Płeć: mężczyzna, Rozmiar:XXL, Kolor:Biały, Materiał: Bawełna}'),
('Plecak Vans','Vans',142.00,1000,'{Stan:Nowy, Faktura: Tak,Płeć: unisex,Kolor:Czarny}'),
('Kurtka Ziomowa Damska','Femi',199.00,400,'{Stan:Nowy, Faktura: Tak,Płeć:Kobieta,Rozmiar:S,Kolor:Czarny} Pikowana kurtka z kapturem to absolutna perła wśród zimowych kurtek! Ta pikowana kurtka to absolutny must-have tej zimy. Jej wyjątkowy design i funkcjonalność z pewnością przyciągną uwagę każdej miłośniczki mody.'),
('River Island Plus Sukienka na co dzień','thelineup',89.95,500,'{Stan:Nowy, Faktura: Tak,Płeć:Kobieta,Rozmiar:50, Kolor:Biały}'),
('Rękawiczki POLAROWE ZIMOWE 4F','',34.08,600,'{Stan:Nowy, Faktura: Tak,Płeć: unisex,Kolor:Czarny}'),

('Garnek tradycyjny Tefal DUETTO+','Tefal',309.00,700,'{Stan:Nowy, Faktura: Tak,Kolor:Srebrny, Materiał: Stal Nierdzewna}'),
('Lampa wisząca Smartled Modern','SmartLED',126.11,300,'{Stan:Nowy, Faktura: Tak,Kolor:Czarny}'),
('FOTEL BIUROWY OBROTOWY CHROM','Telstar',332.40,200,'{Stan:Nowy, Faktura: Tak,Kolor:Czarny, Materiał: Skóra/Metal}'),
('Kosz wolnostojący na pranie 55l','Rotho',53.96,1000,'{Stan:Nowy, Faktura: Tak,Kolor:Beżowy, Długość:432 mm, Szerokość: 355 mm, Wysokość: 533mm}'),
('Lustro stojące','BDart',189.00,500,'{Stan:Nowy, Faktura: Tak, Kolor:biały} Lustro stojące do garderoby skandynawskie Shabby Chick155x36 Borgsjo'),
('Wieszak na ubrania i klucze','Mazur-Tom',39.99,900,'{Stan:Nowy, Faktura: Tak,Kolor:Czarny}'),
('Regał Matkam Julia 58 cm x 176 cm x 30 cm','Matkam Meble',222.99,300,'{Stan:Nowy, Faktura: Tak,Materiał:Dąb} Regał R3 wykonany jest z bardzo dobrej jakości płyty laminowanej o grubości 16mm, którą cechuje zwiększona odporność na zarysowania oraz odporność na ścieranie i wilgoć (możliwość ścierania wilgotną gąbką).'),
('Fotel dziecięcy','Odol-Plusz',159.90,400,'{Stan:Nowy, Faktura: Tak,Kolor:szary} Śliczny pluszowy fotelik dla dziecka.Może służyć do leżenia jak i siedzeniaKOLOR - RÓŻOWE SERCA Zdejmowany pokrowiec do prania.'),
('Stół kuchenny do jadalni loft','Gamet',579.00,250,'{Stan:Nowy, Faktura: Tak,Głębokość:65cm, Szerokość:135}'),
('PUFA FOTEL SAKO XXXL', 'Pufini', 94.99,2800,'{Stan:Nowy, Faktura: Tak, Kolor:Zielony}'),

('Lalka Barbie Malibu Syrenka', 'Korob',105.00,1000,'{Stan:Nowy, Faktura: Tak} Dziecięca wyobraźnia może zanurzyć się w opowiadanie historii dzięki lalce Barbie™ Dreamtopia Syrenka z magicznym podświetleniem!Zanurz tę piękną lalkę Barbie® w wodzie, aby zobaczyć, jak jej perłowy ogon migocze w jednym z czterech kolorowych pokazów świetlnych.'),
('Gra zręcznościowa głodna kaczka','MTA',78.90,500,'{Stan:Nowy, Faktura: Tak}'),
('Tor wyścigowy Mattel Hot Wheels City','Korob',119.00,600,'{Stan:Nowy, Faktura: Tak} Świetny prezent dla dzieci w wieku 4-8 lat!'),
('EDUKACYJNE KLOCKI MAGNETYCZNE','Click4Toys',79.90,400,'{Stan:Nowy, Faktura: Tak, Rodzaj:zestaw} Klocki są super lekkie i bardzo trwałe. Można z nich stworzyć niesamowite budowle. Przez zabawę z klockami dziecko ćwiczy wyobraźnię przestrzenną. Klocki stymulują umiejętności manualne oraz uczą zależności przyczynowo-skutkowych. Doskonale działają na sensorykę oraz zmysły: żywe kolory pobudzają percepcję wzrokową, uwagę i zdolność obserwacji oraz umiejętność kategoryzacji.'),
('Samochód zdalnie sterowany','OverMax',119.00,500,'{Stan:Nowy, Faktura: Tak}'),
('LEGO Classic 10696 Kreatywne klocki','Avans',100.58,400,'{Stan:Nowy, Faktura: Tak}'),
('Ciastolina Hasbro Play-Doh Stylista Szalonych Fryzur','Fitom',59.90,900,'{Stan:Nowy, Faktura: Tak}'),
('MIKROFON KARAOKE GŁOŚNIK ZABAWKA BLUETOOTH LED','manta',49.00,400,'{Stan:Nowy, Faktura: Tak, Kolor: Różowy} To co najważniejsze to czysty dźwięk, funkcja modulacji głosu i echa oraz rozmiar idealny dla każdej ręki.'),
('FIGURKI POKEMON GO ZESTAW FIGUREK 24szt POKEMONY','Wysyłkowo24',29.99,200,'{Stan:Nowy, Faktura: Tak}'),
('KOT PLUSZAK MASKOTKA DLUGI KOT','BestHurt',69.00,1500,'{Stan:Nowy, Faktura: Tak,Materiał:Tkanina}'),

('Super Mario Bros. Wonder Switch','Electro',234.99,300,'{Stan:Nowy, Faktura: Tak, Wersja Językowa: Angielska}'),
('SZACHY DREWNIANE KLASYCZNE','',121.89,400,'{Stan:Nowy, Faktura: Tak}'),


('Uchwyt rowerowy na telefon','Lynxer',45.00,800,'{Stan:Nowy, Faktura: Tak} Dzięki sakwie rowerowej Lynxer przewieziesz drobne przedmioty takie jak klucze, portfel, słuchawki, a nawet dętkę rowerową bez konieczności zabierania ze sobą plecaka. Przezroczysty front (o wymiarach: 18x9.5cm)- przewodzący dotyk, umożliwia pisanie wiadomości, zmienianie muzyki, odbieranie połączeń czy ustawianie mapy bez wyciągania telefonu z etui.'),	
('ROWER GÓRSKI MTB','Amigo Rock',597.64,300,'{Stan:Nowy, Faktura: Tak, Rozmiar Koła: 24"}'),
('Hula hop z wypustkami GFY','Misz-Masz',45.00,700,'{Stan:Nowy, Faktura: Tak,Kolor:Fiolet, Średnica: 50 cm}'),
('ROWEREK STACJONARNY','Zipro',599.00,200,'{Stan:Nowy, Faktura: Tak}'),
('SKAKANKA BOKSERSKA SKÓRZANA' , '4fizjo', 23.90,400,'{Stan:Nowy, Faktura: Tak, Materiał: Skóra}'),
('Zestaw obciążeń kompozytowych bitumicznych 20kg','Hop-Sport',99.98,200,'{Stan:Nowy, Faktura: Tak, Łączna Waga:20 kg}'),

('KUCHENKA ELEKTRYCZNA TURYSTYCZNA JEDNOPALIKOWA','',67.10,400,'{Stan:Nowy, Faktura: Tak}'),
('LODÓWKA TURYSTYCZNA MILLA HOME MC500G','Electro',229.99,600,'{Stan:Nowy, Faktura: Tak, Kolor:Czarny}'),
('MULTITOOL NARZĘDZIE WIELOFUNKCYJNE','ZielonySklep',77.00,1000,'{Stan:Nowy, Faktura: Tak}'),

('MARKERY ALKOHOLOWE 168 SZT','Lweight',58.50,500,'{Stan:Nowy, Faktura: Tak,Grubość:1mm}'),
('Duży obraz malowany Peder Monsted Wiejski ogród','Silver',1425.00,1,'{Stan:Nowy, Faktura: Tak,Wysokość: 66.5cm,Szerokość:96.5cm}'),
('NEONOWE DRUCIKI kolorowe','Profi',9.99,1500,'{Stan:Nowy, Faktura: Tak}'),

('JavaScript i jQuery','Matfel',77.40,600,'{Stan:Nowy, Faktura: Tak,Okładka:Miękka}'),
('FISZKI – angielski – Konwersacje A2-B2','',33.99,1600,'{Stan:Nowy, Faktura: Tak,Język:Angielski}'),
('Atlas grzybów','Korob',24.99,300,'{Stan:Nowy, Faktura: Tak,Okładka:Twarda}'),
('Naturalne kamienie szlachetne i ozdobne','Tantis', 89.90,500,'{Stan:Nowy, Faktura: Tak, Rok Wydania:2023}')

;
  
INSERT INTO products_categories(product_id,cat_id) VALUES
(1,1),
(2,1),
(3,1),
(4,1),
(1,2),
(5,1),
(5,7),
(6,1),
(7,1),
(8,1),
(9,1),
(10,1),
(11,2),
(12,2),
(13,2),
(12,6),
(14,2),
(14,7),
(15,2),
(16,2),
(17,2),
(18,3),
(19,3),
(20,3),
(21,3),
(22,3),
(23,3),
(24,3),
(25,3),
(25,4),
(26,3),
(27,3),
(28,4),
(29,4),
(30,4),
(31,4),
(31,9),
(32,4),
(33,4),
(34,4),
(35,4),
(35,1),
(35,5),
(36,4),
(37,4),
(38,5),
(39,5),
(40,6),
(41,6),
(40,7),
(41,7),
(42,6),
(43,6),
(44,6),
(45,6),
(46,7),
(47,7),
(48,7),
(49,8),
(50,8),
(51,8),
(51,9),
(52,9),
(53,9),
(54,9);
