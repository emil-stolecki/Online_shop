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
  amount_in_stock INTEGER
  
);


CREATE TABLE categories(
	id SERIAL PRIMARY KEY,
	name VARCHAR(25)
);


CREATE TABLE products_categories (
	
	product_id BIGINT REFERENCES products(id), 
	category_id BIGINT REFERENCES categories(id),
	CONSTRAINT products_categories_id PRIMARY KEY(product_id,category_id)

);

INSERT INTO categories(name) VALUES
('Elektronika'),--1--
('Moda'),--2--
('Dom i Ogród'),--3--
('Dziecko'),--4--
('Rozrywka'),--5---
('Sport'),--6--
('Turystyka'),--7--
('Sztuka'),--8--
('Edukacja'),--9--
('Zwierzęta');--10--


INSERT INTO products(name,seller,price,amount_in_stock,description) VALUES

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
--10--
('Bluza Malfini r. L Jacket 501','kstore',53.42,2000,'{Stan:Nowy, Faktura: Tak, Płeć: mężczyzna, Rozmiar:L, Kolor:Czarny}'),
('Buty śniegowce damskie','Hurtownia Moda', 84.59,1500,'{Stan:Nowy, Faktura: Tak,Płeć:Kobieta,Rozmiar:38,Kolor:Szary} Modny ponadczasowy fason, idealnie dopasuje się do wielu stylizacji! Świetnie prezentuje się na nodze.'),
('Bluza z kapturem adidas Entrada','Adidas',74.99,2500,'{Stan:Nowy, Faktura: Tak,Płeć: mężczyzna, Rozmiar:XXL, Kolor:Biały, Materiał: Bawełna}'),
('Plecak Vans','Vans',142.00,1000,'{Stan:Nowy, Faktura: Tak,Płeć: unisex,Kolor:Czarny}'),
('Kurtka Ziomowa Damska','Femi',199.00,400,'{Stan:Nowy, Faktura: Tak,Płeć:Kobieta,Rozmiar:S,Kolor:Czarny} Pikowana kurtka z kapturem to absolutna perła wśród zimowych kurtek! Ta pikowana kurtka to absolutny must-have tej zimy. Jej wyjątkowy design i funkcjonalność z pewnością przyciągną uwagę każdej miłośniczki mody.'),
('River Island Plus Sukienka na co dzień','thelineup',89.95,500,'{Stan:Nowy, Faktura: Tak,Płeć:Kobieta,Rozmiar:50, Kolor:Biały}'),
('Rękawiczki POLAROWE ZIMOWE 4F','',34.08,600,'{Stan:Nowy, Faktura: Tak,Płeć: unisex,Kolor:Czarny}'),
--17--
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
--27--
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
--37--
('Super Mario Bros. Wonder Switch','Electro',234.99,300,'{Stan:Nowy, Faktura: Tak, Wersja Językowa: Angielska}'),
('SZACHY DREWNIANE KLASYCZNE','',121.89,400,'{Stan:Nowy, Faktura: Tak}'),
--39--
('Uchwyt rowerowy na telefon','Lynxer',45.00,800,'{Stan:Nowy, Faktura: Tak} Dzięki sakwie rowerowej Lynxer przewieziesz drobne przedmioty takie jak klucze, portfel, słuchawki, a nawet dętkę rowerową bez konieczności zabierania ze sobą plecaka. Przezroczysty front (o wymiarach: 18x9.5cm)- przewodzący dotyk, umożliwia pisanie wiadomości, zmienianie muzyki, odbieranie połączeń czy ustawianie mapy bez wyciągania telefonu z etui.'),	
('ROWER GÓRSKI MTB','Amigo Rock',597.64,300,'{Stan:Nowy, Faktura: Tak, Rozmiar Koła: 24"}'),
('Hula hop z wypustkami GFY','Misz-Masz',45.00,700,'{Stan:Nowy, Faktura: Tak,Kolor:Fiolet, Średnica: 50 cm}'),
('ROWEREK STACJONARNY','Zipro',599.00,200,'{Stan:Nowy, Faktura: Tak}'),
('SKAKANKA BOKSERSKA SKÓRZANA' , '4fizjo', 23.90,400,'{Stan:Nowy, Faktura: Tak, Materiał: Skóra}'),
('Zestaw obciążeń kompozytowych bitumicznych 20kg','Hop-Sport',99.98,200,'{Stan:Nowy, Faktura: Tak, Łączna Waga:20 kg}'),
--45--
('KUCHENKA ELEKTRYCZNA TURYSTYCZNA JEDNOPALIKOWA','',67.10,400,'{Stan:Nowy, Faktura: Tak}'),
('LODÓWKA TURYSTYCZNA MILLA HOME MC500G','Electro',229.99,600,'{Stan:Nowy, Faktura: Tak, Kolor:Czarny}'),
('MULTITOOL NARZĘDZIE WIELOFUNKCYJNE','ZielonySklep',77.00,1000,'{Stan:Nowy, Faktura: Tak}'),
--48--
('MARKERY ALKOHOLOWE 168 SZT','Lweight',58.50,500,'{Stan:Nowy, Faktura: Tak,Grubość:1mm}'),
('Duży obraz malowany Peder Monsted Wiejski ogród','Silver',1425.00,1,'{Stan:Nowy, Faktura: Tak,Wysokość: 66.5cm,Szerokość:96.5cm}'),
('NEONOWE DRUCIKI kolorowe','Profi',9.99,1500,'{Stan:Nowy, Faktura: Tak}'),
--51--
('JavaScript i jQuery','Matfel',77.40,600,'{Stan:Nowy, Faktura: Tak,Okładka:Miękka}'),
('FISZKI – angielski – Konwersacje A2-B2','',33.99,1600,'{Stan:Nowy, Faktura: Tak,Język:Angielski}'),
('Atlas grzybów','Korob',24.99,300,'{Stan:Nowy, Faktura: Tak,Okładka:Twarda}'),
('Naturalne kamienie szlachetne i ozdobne','Tantis', 89.90,500,'{Stan:Nowy, Faktura: Tak, Rok Wydania:2023}');
--55--
INSERT INTO products(name,seller,price,amount_in_stock,description) VALUES
('Malarstwo Polskie','TaniaKsiążka',42.90, 100,'{Stan:Nowy, Faktura:Tak, Wydawnictwo:Fenix, Rok Wydania: 2018, Ilość Stron:255} Publikacja przedstawia niezbędną wiedzę o dziejach polskiego malarstwa przez pryzmat sylwetek artystów oraz ich najważniejszych dzieł.Jednocześnie ukazuje przemiany w naszej kulturze i historii odzwierciedlone na obrazach. Wielu dziełom poświęcono eseistyczne opisy, którym towarzyszą liczne, efektowne reprodukcje, w tym wyjątkowe autoportrety.'),
('H. Daume XIX w piękne klasyczne malrstwo','', 300,1,'{Stan:Dobry, Technika Wykonania: Olej, Rodzaj Podłoża: Płótno}'),
('FRANCIS BACON - MALRSTWO','', 65,1,'{Stan:Bardzo Dobry}'),
('Zestaw plastyczny Maaleo 128 szt.','Grand_Trade', 99.90,70,'{Stan:Nowy, Faktura: Tak}'),
('Zestaw malarski Artist 34','lc_wroc', 78.98,5,'{Stan:Nowy, Faktura: Tak, Waga:6kg}'),
('Płótno malarskie 24x30 cm podobrazie ZESTAW 6 SZT','Sztucznik', 55,100,'{Stan:Nowy, Faktura: Tak, Materiał:Płótno}'),
('Zestaw do malowania w walizce 46szt.','Maaleo', 105.49,100,'{Stan:Nowy, Faktura: Tak}'),
('Rzeźba, dekoracja ze starego drewna','', 239.00,1,'{Stan:Nowy, Faktura: Tak, Materiał:Drewno}'),
--63--
('SASZETKA NERKA TAKTYCZNA MILITARNA WOJSKOWA SURVIVAL KIESZEŃ + PAS PASEK','mobes_pl', 22.49,200,'{Stan:Nowy, Faktura: Tak, Kolor:zielony}'),
('Plecak wojskowy K&M I180 20-40 l khaki','KIMCollection', 49,150,'{Stan:Nowy, Faktura: Tak, Kolor:khaki}'),
('Walizka średnia Puccini Zadar zielona 46 l','PUCCINI', 199.99,500,'{Stan:Nowy, Faktura: Tak, Kolor:Niebieski, Rodzaj:Twarda} Marka PUCCINI to polska firma z ponad dwudziestoletnią tradycją. Kupując na tej aukcji, otrzymasz wyłącznie oryginalny towar, prosto od producenta.'),
('Walizka twarda średnia WITTCHEN ABS 56-3A-652 62 l','WITTCHEN', 179.90,450,'{Stan:Nowy, Faktura: Tak, Kolor:Bordowy, Rodzaj:Twarda}'),
('Spodnie Hi-Tec r. M','plecakowo_pl', 177.77,10,'{Stan:Nowy, Faktura: Tak, Kolor:Czarny, Płeć: Mężczyzna}Męskie spodnie typu softshell cenionej marki Hi-tec. Zapewniają ochronę przed deszczem dzięki zastosowaniu wodoodpornego materiału.'),
('Mata samopompująca Bestway Pavillo 50 cm x 180 cm x 2,5 cm','eAGD', 69.99,800,'{Stan:Nowy, Faktura: Tak, Kolor:Zielony, Długość:180cm, Szerokość: 50cm}'),
('Termos Tefal K3064414 1 l czarny','Tefal', 119,300,'{Stan:Nowy, Faktura: Tak, Kolor:Czarny, Pojemność: 1l}Wytrzymały termos marki Tefal o pojemności 1000 ml został wykonany z wysokiej jakości stali nierdzewnej. Silikonowa tuleja gwarantuje 100% nieprzepuszczalność i pozwala na bezpieczne korzystanie z termosu w transporcie, bez obawy wylania napoju. Dodatkowo, termos Senator został wyposażony w kubek w formie zakrętki oraz system szybkiego zamykania. Producent udziela na produkt 5-letniej gwarancji'),
('Bluzka termoaktywna damska Molti BL100 czarna r. S','MOLTI', 75.00,15,'{Stan:Nowy, Faktura: Tak, Kolor:czarny, Płeć; Kobieta}'),
('Bluzka termoaktywna damska Molti BL100 czarna r. S','MOLTI', 75.00,15,'{Stan:Nowy, Faktura: Tak, Kolor:czarny, Płeć; Kobieta}'),
('Skarpety do połowy łydki Comodo wielokolorowy r. 43-46','Comodo', 59,200,'{Stan:Nowy, Faktura: Tak, Kolor:khaki, Płeć;Unisex, Kolor:Czerwony}'),
--73--
('Termos Zojirushi SL-GH18-BA 1,27 l 3 cz. czarny','Ghik',259.99,8,'{Stan:Nowy, Faktura: Tak, Kolor:Czarny, Pojemność:1.27l}'),
('Koc Relaxdays 000 prostokątny 200 x 300 cm','techfixx', 99.99,5,'{Stan:Nowy, Faktura: Tak, Kolor:Niebiesko-Biały}');
--75--
INSERT INTO products(name,seller,price,amount_in_stock,description) VALUES
('Hagne chusta kratka - wiskoza','BORN86', 45,7,'{Stan:Nowy, Faktura: Tak, Kolor:Wielokolorowy, Wzór:Kratka, Płeć: Kobieta}'),
('Eldar halka poliamid r.','aktywni24', 45,7,'{Stan:Nowy, Faktura: Tak, Kolor:Beżowy, Płeć: Kobieta}'),
('KOLCZYKI SREBRNE 925 WISZĄCE .Cyrkonie SWAROVSKI','SilverWorld-PL', 59,4,'{Stan:Nowy, Faktura: Tak, Materiał:Srebro}'),
('Born2be kozaki damskie za kolano płaski obcas','born2be', 55.11,10,'{Stan:Nowy, Faktura: Tak, Materiał:Skóra ekologiczna, Kolor:Czarny,Płeć: Kobieta}'),
('Torebka damska shopper na ramię miejska Torebka shopper czarny','Bouri', 45.99,4,'{Stan:Nowy, Faktura: Tak, Kolor:Czarny,Płeć: Kobieta}'),
('Lulu Castagnette Torebka listonoszka skóra naturalna','Hila_eu', 139.99,6,'{Stan:Nowy, Faktura: Tak, Kolor:Czarny,Płeć: Kobieta, Materiał:Skóra}'),
('Kurtka parka z kożuszkiem kaptur kieszenie ściągacz futerko ciepła','Zalzo', 149.00,10,'{Stan:Nowy, Faktura: Tak, Kolor:Czerwony,Płeć: Kobieta}'),
('SPÓDNICA PLISOWANA MINI SPÓDNICZKA Z PODSZEWKĄ','dlook', 68.99,10,'{Stan:Nowy, Faktura: Tak, Kolor:Szary, Płeć: Kobieta}'),
('100% Sukienka koktajlowa glamour mini','dlook', 76.99,20,'{Stan:Nowy, Faktura: Tak, Kolor:Czerwony, Płeć: Kobieta}'),
('Magmac Sukienka na co dzień glamour mini','blogomania', 109.99,20,'{Stan:Nowy, Faktura: Tak, Kolor:Czerwony, Płeć: Kobieta}'),
--85--
('SEPHORA WILD WISHES PALETA CIENI DUŻY ZESTAW','limago_store', 159.99,60,'{Stan:Nowy, Faktura: Nie}Paleta o wyjątkowym designie zawierająca 86 kolorów do makijażu oczu, ust i policzków:86 modnych, promiennych odcieni o matowym, perłowym i brokatowym wykończeniu, którymi stworzysz całą gamę makijażowych efektów, od nude po dzikie i nieokiełznane!'),
('LANEIGE Lip Sleeping Mask BERRY 20g Korea','xiaodongdianpu', 47.98,300,'{Stan:Nowy, Faktura: Nie, Pojemość:20ml}'),
('AA LAAB Skoncentrowane serum-ampułka 30 ml','OCEANIC', 31.99,100,'{Stan:Nowy, Faktura: Tak}Już nie musisz wybierać pomiędzy skutecznością a bezpieczeństwem. Nowatorska receptura bazuje na aktywności multifunkcyjnych składników, które poprzez synergiczne działanie zapewniają skórze skuteczną profilaktykę starzenia, bez ryzyka podrażnień. Kompleks CENTELLA B12 to zgłoszone do patentu połączenie dwóch wyjątkowych składników aktywnych'),
('Zestaw do pielęgnacji brody Logit kartacz i grzebień','iWant', 24.99,100,'{Stan:Nowy, Faktura: Tak, Materiał:Drwno i Włosie Dzika}'),
('Pasta do włosów Reuzel 113 ml','xiaodongdianpu', 51.72,1000,'{Stan:Nowy, Faktura: Nie, Pojemość:113ml}'),
--90--
('Karma sucha dla psa, różne rodzaje, 2 x 1 kg','Purizon',44.40 ,200,'{Stan:Nowy, Faktura: Tak, Rodzaj:Kaczka} Sucha karma dla psów marki Purizon to zrównoważone i naturalne pożywienie dla wszystkich mięsożernych psów. Zawiera aż 80% starannie wyselekcjonowanych składników pochodzenia zwierzęcego, dzięki czemu jest karmą o jakości premium. W jej skład wchodzi świeże mięso drobiowe, świeżo złowiona ryba oraz pożywne składniki, np. olej z łososia czy warzywa, owoce lub zioła. Karma Purizon produkowana jest w zupełności bez dodatku zbóż, a do jej produkcji wykorzystano surowce o najwyższej jakości.'),
('Rocco Classic, 6 x 400 g','Rocco',34 ,300,'{Stan:Nowy, Faktura: Tak, Rodzaj:Wołowina,Zwierzę:Pies }'),
('Piłka TPR z piszczałką','zooplus',37 ,28,'{Stan:Nowy, Faktura: Tak, Kolor:Zielony,Zwierzę:Pies}'),
('Stripey Lobster, zabawka dla psa','Kakadu',27.80 ,160,'{Stan:Nowy, Faktura: Tak,Kolor: czerwony / biały,Zwierzę:Pies}'),
('Trixie Transporter Capri','Trixie',83.90 ,300,'{Stan:Nowy, Faktura: Tak, kolor:Niebieski, Materiał:Tworzywo sztuczne,Zwierzę:Kot/Mały Pies/Gryzonie }'),
('Łopatka do kocich kuwet','kakadu',2.40 ,1500,'{Stan:Nowy, Faktura: Tak, Kolor:Fioletowy,Zwierzę:Kot }'),
('Wędka dla kota z kolorowymi piórkami','zoozoo',20.60 ,400,'{Stan:Nowy, Faktura: Tak,Zwierzę:Kot}'),
('Wild Freedom Kitten, 6 x 200 g','zooplus', 42.90,200,'{Stan:Nowy, Faktura: Tak,Rodzaj:Cielęcina,Zwierzę:Kot}'),
('Płatki grochu przysmak dla gryzoni','kakadu',14.80 ,150,'{Stan:Nowy, Faktura: Tak,Zwierzę:Gryzoń}'),
('JR Farm 3 piłeczki do gryzienia','kakadu', 22.80,600,'{Stan:Nowy, Faktura: Tak, Zwierzę:Gryzoń}');
--100--
INSERT INTO products(name,seller,price,amount_in_stock,description) VALUES
('Gra planszowa Ryzerze i Zamki Egmont Gra Zręcznościowa dla Dzieci','Pandu',50 ,150,'{Stan:Nowy, Faktura: Nie,Wydawca:Egmont}'),
('Gra planszowa Bitwa Lądowa','Pandu',50 ,150,'{Stan:Nowy, Faktura: Nie, Wiek: 5-7 lat}'),
('PLANSZÓWKA GRA PLANSZOWA OD 12 LAT SWORD SORCERY','Happy_Kids',800 ,1,'{Stan:Nowy, Faktura: Tak, Wiek:12+}'),
('Gra Viticulture DODATEK Goście z Wrzosowisk','Happy_Kids',86 ,3,'{Stan:Nowy, Faktura: Tak, Wiek:12+, Rodzaj:dodatek}'),
('Drewniane Pudełko na Kostki RPG Dungeons Dragons','panchatka',89 ,10,'{Stan:Nowy, Faktura: Tak, Materiał:Drewno, Rodzaj:Kości}'),
('Księga RPG w formacie A4 z siatką heksagonalną Playmaty','RGToou',164.90 ,10,'{Stan:Nowy, Faktura: Tak}'),
('Fighting Fantasy: Bestia chaosu Steve Jackson','KSIAZKI-24h',26.69 ,400,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski, Wydawnictwo:Foxmages}'),
('Fourth Wing Rebecca Yarros','prolibri',49.56 ,114,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski, Wydawnictwo:Filia}'),
('HARLEQUIN zestaw książek seria 10 szt. Romans HARLEQUIN','onlineksiazka',25 ,100,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski, Wydawnictwo:Harlequin}'),
('My name is Lena. Romans mafijny Alicja Skirgajłło','the_book',32.84 ,5,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski'),
--110--
('Ludowa historia Polski','taniaksiazka.pl',59.11 ,60,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski, Wydawnictwo:WAB'),
('SPQR. Historia starożytnego Rzymu','taniaksiazka.pl',58 ,40,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski, Wydawnictwo:Rebis'),
('Psychologia dla początkujących','taniaksiazka.pl',28 ,50,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski, Wydawnictwo:Publicat'),
('Zielony sekret zdrowia. Uzdrawianie przez roślinne gotowanie','taniaksiazka.pl',45.97 ,40,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski'),
('Święty Jan Paweł II. Papież wielu pokoleń','taniaksiazka.pl',22.94 ,400,'{Stan:Nowy, Faktura: Tak, Język Publikacji:Polski'),
--115--
('OGÓREK samopylny PARTNER odmorny na mączniaka','KukuGarden',4.49 ,2000,'{Faktura: Tak, Waga:2g}'),
('Agrowłóknina ściółkująca (przeciw chwastom) Agrovover czarny 160 x 5000 cm 90 g/m²','Agro-cover',134 ,400,'{Faktura: Tak, Kolor:Czarny}'),
('Tulipan różowy bulwy/cebule/kłącza luzem','Ogród_i_Dom',2.00,4000,'{Faktura: Tak, Kolor Kwiatów:Różowy}'),
('Piwonia bulwy/cebule/kłącza w opakowaniu zbiorczym','Cebulove_pl',44.45,2000,'{Faktura: Tak, Kolor Kwiatów:Różne}'),
('Zawilec biały, czerwony, fioletowy, różnobarwny, różowy','Cebulove_pl',2.40,4000,'{Faktura: Tak, Kolor Kwiatów:Różne}')
;
  
INSERT INTO products_categories(product_id,category_id) VALUES
(1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),
(35,1),

(1,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(64,2),(65,2),
(68,2),(71,2),(72,2),(73,2),(76,2),(77,2),(78,2),(79,2),(80,2),(81,2),
(82,2),(83,2),(84,2),(85,2),(86,2),(87,2),(88,2),(89,2),(90,2),

(18,3),(19,3),(20,3),(21,3),(22,3),(23,3),(24,3),(25,3),(26,3),
(27,3),(116,3),(117,3),(118,3),(119,3),(120,3),

(25,4),(28,4),(29,4),(30,4),(31,4),(32,4),(33,4),(34,4),(35,4),(36,4),
(37,4),

(35,5),(38,5),(39,5),(101,5),(102,5),(103,5),(104,5),(105,5),(106,5),(107,5),
(108,5),(109,5),(110,5),

(12,6),(40,6),(41,6),(42,6),(43,6),(44,6),(45,6),

(5,7),(14,7),(40,7),(41,7),(46,7),(47,7),(48,7),(63,7),(64,7),(65,7),
(66,7),(67,7),(68,7),(69,7),(70,7),(71,7),(72,7),(73,7),(74,7),(75,7),

(49,8),(50,8),(51,8),(56,8),(57,8),(58,8),(59,8),(60,8),(61,8),(62,8),

(31,9),(51,9),(52,9),(53,9),(54,9),(55,9),(111,9),(112,9),(113,9),(114,9),
(115,9),

(91,10),(92,10),(93,10),(94,10),(95,10),(96,10),(97,10),(98,10),(99,10),(100,10)

;


