--- IMPORT DATA FOR TABLE CUSTOMER --------------------
INSERT INTO Customer (Name, Address, Phone, Gender, Email, Birthdate) VALUES
('Example Name', 'Example Address', '0909090909', 1, 'Example@gmail.com', NULL)
INSERT INTO Customer (Name, Address, Phone, Gender, Email, Birthdate) VALUES
('Phan Tuan Kiet', 'Binh Dinh', '0969777210', 1, 'phantuankiet@nv.dkstore.com', '2004-06-19'),
('Tran Nguyen Phi Duong', 'Phu Yen', '0348102328', 1, 'tranduong@gmail.com', '2004-07-05')
INSERT INTO Customer (Name, Address, Phone, Gender, Email, Birthdate) VALUES
('Phan Thi Thanh Thuy', 'Thanh Hoa', '0822739053', 0, NULL, NULL),
('Le Dinh Tri', 'Thanh pho Soc Trang, Soc Trang', '0304196439', 1, NULL, NULL),
('Huynh Thi Thanh Tu', NULL, '0314558354', 0, NULL, NULL),
('Bui Thi Thu Thao', 'Tuy Hoa, Phu Yen', '0713602143', 0, 'buittthao@st.chuvanan.edu.vn', NULL),
('Bui Dinh Hai', 'Tuy Hoa, Phu Yen', '0932851151', 1, NULL, '2009-06-17'),
('Ly Trong Kiet', NULL, '0785321711', 1, NULL, NULL),
('Vu Dinh Loc', NULL, '0358694201', 1, NULL, NULL),
('Cu Trong Nghia',  NULL, '0787766413', 1,  NULL,  NULL),
('Ly Thi Kim Chi', NULL, '0280066967', 0, NULL,  NULL),
('Hoang Quang Vinh', NULL, '0932742564', 1, NULL, '2002-11-24'),
('Nguyen Thi Thu Hanh',  NULL, '0627581427', 0, NULL, '2000-11-09'),
('Le Khanh Lan',  NULL, '0214917307', 1, NULL, '2004-07-04'),
('Tran Dinh Nghia', 'Lam Dong', '0615082501', 1, NULL, '2009-01-18'),
('Dang Thi Thanh Tam', 'Ho Chi Minh', '0706274748', 0, NULL, NULL),
('Bui Thi Thu Thao', 'Tuy Hoa, Phu Yen', '0190372688', 0, NULL, '2010-08-14'),
('Hugo Chevalier', NULL, '0660229415', 1, NULL, '2003-01-16'),
('Truong Quoc Huy', NULL, '0884799274', 1, NULL, '2010-07-05'),
('Hoang Nhat Linh', NULL, '0832048861', 1, NULL, '2010-04-06'),
('Le Dinh Phong', 'Bac Lieu', '0964057172', 1, NULL, '2007-11-23'),
('Lam Thi My Dung', NULL, '0554450808', 0, NULL, NULL),
('Dang Thanh Long',  NULL, '0580561345', 1, NULL, '2004-04-16'),
('Hoang Van Kien', NULL, '0527963486', 1, 'hoangvankien@st.uni.edu.vn', '2002-12-05'),
('Nguyen Mai Lan', 'Thanh pho Da Nang, Da Nang', '0584747224', 0, NULL, '1999-11-27'),
('Phan Quang Hieu', NULL, '0308378504', 1, NULL, '2007-11-17'),
('Le Ngoc Hoang',  NULL, '0835816065', 1, NULL, '2007-10-16'),
('Le Thi Thuy Hang', NULL, '0988818269', 0, NULL, '2007-06-04'),
('Nguyen Van Phong',  NULL, '0199333657', 1, NULL, '2009-08-24'),
('Nguyen Thi Kim Ngan',  NULL, '0990870430', 0, NULL, '2000-05-03');

--- IMPORT DATA FOR TABLE EMPLOYEE --------------------
INSERT INTO Employee(Name, BirthDate, Phone, Email, Gender, Address, Salary, HireDate, Account, Password) VALUES
('Tran Nguyen Phi Duong', '2004-07-03', '0348102328', 'phiduong.it.hcm@gmail.com', 1, 'Phu Yen', 100.2, '2022-12-12', 'tnphiduong', 'quannlyDK'),
('Phan Tuan Kiet', '2004-05-17', '0346811966', 'ptuankled@gmail.com', 1, 'Binh Dinh', 100, '2022-12-12', 'ptuankled', 'quanlyDK')
INSERT INTO Employee(Name, BirthDate, Phone, Email, Gender, Address, Salary, HireDate, Account, Password) VALUES
('Bui Quoc Anh', '2000-06-14', '0990123945', 'quocanh@gmail.com', 1, 'Bac Lieu', 87.2, '2022-12-12', 'quocanh1', 'nhanvienDK'),
('Nguyen Kim', '2001-08-14', '0999923933', 'kimnguyen@yahoo.com', 0, 'Long An', 87.2, '2022-12-12', 'nguyenkim2', 'nhanvienDK'),
('Tran Van Kha', '2002-06-20', '0990199345', 'vankha2006@gmail.com', 1, 'Vinh Long', 87.2, '2022-12-12', 'vankha3', 'nhanvienDK'),
('Vo Trung Giang', '2000-07-1', '0990191435', 'vtgiang0701@gmail.com', 1, 'Khanh Hoa', 69.2, '2023-12-12', 'trunggianng4', 'nhanvienDK'),
('Nguyen Gia Khanh', '2002-09-14', '0990126605', 'giakhanhh@gmail.com', 1, 'Vung Tau', 69.2, '2023-12-12', 'nguyengkhanh', 'nhanvienDK'),
('Vo Huy Hoang', '2001-03-30', '0996302945', 'vhhoang@yahoo.com', 1, 'Phu Yen', 87.2, '2022-12-12', 'huyhoang', 'nhanvienDK'),
('Tran Ham Thuyen', '2004-07-07', '0997820945', 'hamthuyen@gmail.com', 0, 'BinhDinh', 81.2, '2023-06-12', 'hamthuyen', 'nhanvienDK'),
('Nguyen Duy Khang', '2003-06-15', '0870123945', 'duykhang@gmail.com', 1, 'Khanh Hoa', 81.2, '2022-06-12', 'duykhang', 'nhanvienDK'),
('Bui Dat Thanh', '2002-09-15', '0990100036', 'thanhhh@gmail.com', 1, 'Long  An', 87.2, '2022-12-12', 'datthhanhh', 'nhanvienDK'),
('Vo Kieu Trinh', '2004-06-4', '099000115', 'kieutrinhh@gmail.com', 0, 'An Giang', 81.2, '2023-12-12', 'kieutrinhh', 'nhanvienDK'),
('Phan Tieu Chi', '2001-04-11', '0987123945', 'tieuchi@gmail.com', 0, 'Bac Lieu', 81.2, '2023-12-12', 'ptchiii', 'nhanvienDK'),
('Vo Ngoc Bich', '2002-02-19', '0996123945', 'vngocbich@gmail.com', 0, 'Binh Duong', 81.2, '2023-12-12', 'vongocbich', 'nhanvienDK'),
('Pham Tuan Kiet', '2003-01-10', '0990129310', 'ptkiett@gmail.com', 1, 'Binh Dinh', 83.2, '2022-11-12', 'tuankiet', 'quanlyDK'),
('Bui Huy Hoang', '2003-11-10', '0998397245', 'buihihoang@gmail.com', 1, 'Bac Lieu', 87.2, '2022-12-12', 'bhihon', 'nhanvienDK'),
('Bui Quoc Anh', '2000-06-14', '0990123999', 'quocanhh0614@gmail.com', 1, 'Ca Mau', 87.2, '2022-12-12', 'quocanh2', 'nhanvienDK'),
('Tran Van Vy', '1999-10-10', '0987223945', 'vanvy@gmail.com', 1, 'Kien Giang', 81.2, '2023-12-12', 'vanvy', 'nhanvienDK'),
('Pham Dinh Vy', '2004-09-10', '0990123585', 'dinhvy@yahoo.com', 1, 'Binh Dinh', 69.2, '2023-12-12', 'dinhvyy', 'nhanvienDK'),
('Bui Gia Cat Luong', '2005-01-1', '0990178935', 'catlhuong@gmail.com', 1, 'Binh Thuan', 81.2, '2023-1-12', 'giacatluong', 'nhanvienDK')

--- IMPORT DATA FOR TABLE INVOICE ---------------------
INSERT INTO Invoice(EmployeeID, CustomerID) VALUES 
(1001, 1030),
(1001, 1027),
(1001, 1027),
(1002, 1023),
(1002, 1013),
(1002, 1019),
(1002, 1013),
(1002, 1009),
(1002, 1022),
(1002, 1007),
(1002, NULL),
(1003, 1016),
(1003, 1011),
(1003, 1021),
(1003, 1015),
(1003, 1030),
(1004, 1007),
(1004, 1011),
(1004, NULL),
(1005, 1014),
(1005, 1031),
(1005, 1020),
(1005, NULL),
(1006, 1005),
(1006, 1016),
(1006, 1007),
(1006, 1026),
(1006, 1010),
(1006, NULL),
(1006, 1031),
(1006, 1028),
(1007, 1031),
(1007, 1024),
(1007, 1002),
(1007, NULL),
(1007, 1023),
(1007, 1015),
(1007, NULL),
(1007, 1016),
(1007, 1015),
(1007, NULL),
(1007, NULL),
(1008, 1006),
(1008, 1004),
(1008, 1021),
(1008, NULL),
(1008, 1003),
(1008, 1031),
(1008, NULL),
(1008, NULL),
(1009, 1015),
(1009, 1006),
(1009, 1021),
(1009, 1004),
(1009, 1007),
(1009, NULL),
(1009, NULL),
(1010, 1028),
(1010, 1028),
(1010, 1016),
(1010, NULL),
(1011, 1025),
(1011, 1003),
(1011, 1020),
(1011, 1014),
(1011, NULL),
(1012, 1010),
(1012, 1003),
(1012, 1025),
(1012, NULL),
(1012, 1022),
(1012, NULL),
(1012, 1012),
(1012, NULL),
(1012, NULL),
(1013, 1027),
(1013, 1030),
(1013, 1011),
(1013, 1005),
(1013, 1020),
(1013, NULL),
(1013, NULL),
(1014, 1025),
(1014, 1023),
(1014, 1013),
(1014, NULL),
(1015, 1024),
(1015, 1031),
(1015, 1019),
(1015, 1006),
(1015, 1020),
(1015, NULL),
(1016, 1030),
(1016, 1024),
(1016, 1002),
(1016, NULL),
(1016, NULL),
(1017, 1004),
(1017, 1017),
(1017, 1005),
(1017, 1003),
(1017, 1004),
(1017, 1024),
(1017, NULL),
(1017, NULL),
(1018, 1030),
(1018, 1002),
(1018, 1010),
(1018, 1009),
(1018, 1007),
(1018, 1013),
(1018, NULL),
(1019, 1006),
(1019, 1001),
(1019, 1010),
(1019, NULL),
(1019, NULL),
(1020, 1027),
(1020, 1009),
(1020, 1018),
(1020, 1020),
(1020, 1016),
(1020, 1006);

UPDATE Invoice
SET DateBill = d.DateBill
FROM Invoice i
JOIN (
    VALUES 
    (1017, '2024-05-03 05:10:07'),
    (1018, '2024-05-03 20:32:41'),
    (1019, '2024-05-04 16:49:04'),
    (1020, '2024-05-04 05:02:07'),
    (1021, '2024-05-05 02:33:02'),
    (1022, '2024-05-06 00:27:39'),
    (1023, '2024-05-07 23:21:24'),
    (1024, '2024-05-08 11:06:57'),
    (1025, '2024-05-09 23:41:36'),
    (1026, '2024-05-10 07:26:40'),
    (1027, '2024-05-11 22:43:56'),
    (1028, '2024-05-12 16:40:21'),
    (1029, '2024-05-13 17:28:51'),
    (1030, '2024-05-14 14:11:38'),
    (1031, '2024-05-15 09:07:32'),
    (1032, '2024-05-16 04:57:14'),
    (1033, '2024-05-17 04:01:35'),
    (1034, '2024-05-18 01:22:14'),
    (1035, '2024-05-19 08:24:01'),
    (1036, '2024-05-20 07:50:35'),
    (1037, '2024-05-21 11:21:47'),
    (1038, '2024-05-22 11:30:04'),
    (1039, '2024-05-23 01:41:53'),
    (1040, '2024-05-24 13:36:47'),
    (1041, '2024-05-25 03:28:19'),
    (1042, '2024-05-26 20:32:12'),
    (1043, '2024-05-27 13:44:34'),
    (1044, '2024-05-28 16:57:28'),
    (1045, '2024-05-29 22:30:45'),
    (1046, '2024-05-30 17:09:50'),
    (1047, '2024-05-31 21:43:02'),
    (1048, '2024-06-01 16:47:50'),
    (1049, '2024-06-02 10:28:37'),
(1050, '2024-05-03 18:40:34'),
(1051, '2024-05-04 04:49:51'),
(1052, '2024-05-05 22:16:07'),
(1053, '2024-05-06 13:07:23'),
(1054, '2024-05-07 17:11:43'),
(1055, '2024-05-08 12:16:13'),
(1056, '2024-05-09 21:06:14'),
(1057, '2024-05-10 16:14:12'),
(1058, '2024-05-11 16:31:26'),
(1059, '2024-05-12 02:31:04'),
(1060, '2024-05-13 12:04:07'),
(1061, '2024-05-14 17:31:36'),
(1062, '2024-05-15 04:47:46'),
(1063, '2024-05-16 20:47:31'),
(1064, '2024-05-17 16:40:22'),
(1065, '2024-05-18 18:24:50'),
(1066, '2024-05-19 18:58:49'),
(1067, '2024-05-20 18:25:12'),
(1068, '2024-05-21 10:39:15'),
(1069, '2024-05-22 16:31:38'),
(1070, '2024-05-23 05:16:32'),
(1071, '2024-05-24 03:30:56'),
(1072, '2024-05-25 18:25:17'),
(1073, '2024-05-04 10:41:31'),
(1074, '2024-05-05 01:33:45'),
(1075, '2024-05-06 23:45:17'),
(1076, '2024-05-07 06:15:36'),
(1077, '2024-05-08 08:42:57'),
(1078, '2024-05-09 15:15:45'),
(1079, '2024-05-10 13:22:43'),
(1080, '2024-05-11 13:46:51'),
(1081, '2024-05-12 07:56:20'),
(1082, '2024-05-13 14:25:45'),
(1083, '2024-05-14 10:46:37'),
(1084, '2024-05-15 23:09:48'),
(1085, '2024-05-16 04:40:06'),
(1086, '2024-05-17 11:33:00'),
(1087, '2024-05-18 19:30:41'),
(1088, '2024-05-19 14:38:16'),
(1089, '2024-05-20 05:18:06'),
(1090, '2024-05-21 03:23:40'),
(1091, '2024-05-22 16:17:14'),
(1092, '2024-05-23 02:49:59'),
(1093, '2024-05-24 09:25:03'),
(1094, '2024-05-25 05:25:08'),
(1095, '2024-05-26 07:40:54'),
(1096, '2024-05-27 19:07:46'),
(1097, '2024-05-28 16:17:23'),
(1098, '2024-05-29 06:47:33'),
(1099, '2024-05-30 20:55:19'),
(1100, '2024-05-31 11:28:19'),
(1101, '2024-06-01 05:36:49'),
(1102, '2024-06-02 22:41:50'),
(1103, '2024-06-03 22:54:21'),
(1104, '2024-05-04 07:17:49'),
(1105, '2024-05-05 01:29:42'),
(1106, '2024-05-06 11:56:24'),
(1107, '2024-05-07 21:17:47'),
(1108, '2024-05-08 21:15:22'),
(1109, '2024-05-09 14:35:31'),
(1110, '2024-05-10 19:54:56'),
(1111, '2024-05-11 17:47:50'),
(1112, '2024-05-12 10:02:54'),
(1113, '2024-05-13 06:04:24'),
(1114, '2024-05-14 22:25:30'),
(1115, '2024-05-15 04:22:13'),
(1116, '2024-05-16 19:11:11'),
(1117, '2024-05-17 17:26:42'),
(1118, '2024-05-18 12:27:07'),
(1119, '2024-05-19 22:49:50'),
(1120, '2024-05-20 05:38:08'),
(1121, '2024-05-21 23:36:43'),
(1122, '2024-05-29 00:37:21'),
(1123, '2024-05-29 01:19:43'),
(1124, '2024-05-29 20:01:50'),
(1125, '2024-05-30 04:51:36'),
(1126, '2024-05-30 23:27:30'),
(1127, '2024-05-30 11:43:09')
) AS d(InvoiceID, DateBill) ON i.InvoiceID = d.InvoiceID;


--- IMPORT DATA FOR TABLE PRODUCT
INSERT INTO product (Name, CategoryID, SellPrice, Description, Quantity) VALUES 
('Vinamilk Yogurt Mango', 3, 15.00, '330ml bag', 100),
('Vinamilk Mozzarella Cheese', 3, 25.00, '', 150),
('Vinamilk Cheddar Cheese', 3, 26.00, '', 120),
('Vinamilk Parmesan Cheese', 3, 28.00, '', 90),
('Vinamilk Cream Cheese', 3, 27.00, '', 80),
('Vinamilk Slices Cheese', 3, 32.00, '', 110),
('Vinamilk String Cheese', 3, 25.00, '', 130),
('Vinamilk Stick Cheese', 3, 29.00, '', 140),
('Vinamilk Burger Cheese', 3, 28.00, '', 160),
('Ong Tho Condensed Milk', 3, 35.00, '500ml can', 70),
('Dutch Lady Condensed Milk', 3, 37.00, '500ml can', 50),
('TH True Milk Condensed Milk', 3, 29.00, '500ml can', 200),
('Moc Chau Condensed Milk', 3, 31.00, '500ml can', 60);
INSERT INTO product (Name, CategoryID, SellPrice, Description, Quantity) VALUES 
('Quick Oats', 4, 2.24, '', 120),
('Flavored Oats', 4, 2.41, '', 90),
('Instant Oats', 4, 1.92, '', 140),
('Cereal Oats', 4, 2.00, '', 110),
('Chestnut Oats', 4, 2.12, '', 130),
('Almond Oats', 4, 2.29, '', 80),
('Walnut Oats', 4, 2.20, '', 100),
('Almond Nuts', 4, 3.18, '', 150),
('Walnut Oats', 4, 2.20, 'Walnut Oats', 100),
('Almond Nuts', 4, 3.18, 'Almond Nuts', 150),
('Walnut Nuts', 4, 2.65, 'Walnut Nuts', 70),
('Pumpkin Seeds', 4, 3.14, 'Pumpkin Seeds', 60),
('Chestnut Nuts', 4, 3.47, 'Chestnut Nuts', 200),
('Macadamia Nuts', 4, 3.10, 'Macadamia Nuts', 50),
('Cashew Nuts', 4, 3.06, 'Cashew Nuts', 110),
('Chia Seeds', 4, 3.18, 'Chia Seeds', 90),
('Red Beans', 4, 3.63, 'Red Beans', 80);
INSERT INTO product (Name, CategoryID, SellPrice, Description, Quantity) VALUES 
('Pork Belly', 5, 4.53, 'Pork Belly', 150),
('Ground Pork', 5, 5.76, 'Ground Pork', 160),
('Pork Chop', 5, 4.94, 'Pork Chop', 140),
('Smoked Pork', 5, 4.12, 'Smoked Pork', 130),
('Gio Lua', 5, 6.58, 'Gio Lua', 120),
('Cha Lua', 5, 6.17, 'Cha Lua', 110),
('Cha Que', 5, 4.12, 'Cha Que', 100),
('Nem Chua', 5, 7.00, 'Nem Chua', 90),
('Sausage', 5, 7.41, 'Sausage', 80),
('Chicken Breast', 5, 7.82, 'Chicken Breast', 70);
INSERT INTO product (Name, CategoryID, SellPrice, Description, Quantity) VALUES 
('Apple', 6, 0.78, 'Apple', 100),
('Pear', 6, 0.74, 'Pear', 90),
('Grape', 6, 3.09, 'Grape', 110),
('Kiwi', 6, 2.63, 'Kiwi', 120),
('Guava', 6, 2.51, 'Guava', 80),
('Plum', 6, 0.58, 'Plum', 150),
('Strawberry', 6, 0.74, 'Strawberry', 140),
('Blueberry', 6, 4.07, 'Blueberry', 70),
('Raspberry', 6, 0.82, 'Raspberry', 130),
('Pomegranate', 6, 2.92, 'Pomegranate', 160),
('Mango', 6, 1.07, 'Mango', 110),
('Rambutan', 6, 1.73, 'Rambutan', 60),
('Durian', 6, 0.91, 'Durian', 140),
('Mangosteen', 6, 1.11, 'Mangosteen', 70),
('Pomelo', 6, 4.03, 'Pomelo', 150),
('Orange', 6, 3.09, 'Orange', 90),
('Dried Banana', 6, 3.87, 'Dried Banana', 80),
('Tangerine', 6, 3.09, 'Tangerine', 100),
('Avocado', 6, 1.40, 'Avocado', 50),
('Pineapple', 6, 0.99, 'Pineapple', 120),
('Dragon Fruit', 6, 1.69, 'Dragon Fruit', 130),
('Watermelon', 6, 2.51, 'Watermelon', 60),
('Longan', 6, 0.82, 'Longan', 90);
INSERT INTO product (Name, CategoryID, SellPrice, Description, Quantity) VALUES 
('Hao Hao Chicken', 7, 6.17, 'Hao Hao Chicken', 100),
('Hao Hao Shrimp', 7, 6.58, 'Hao Hao Shrimp', 90),
('Hao Hao Beef', 7, 7.41, 'Hao Hao Beef', 110),
('Hao Hao Sour Spicy', 7, 7.00, 'Hao Hao Sour Spicy', 120),
('Hao Hao Seafood', 7, 7.82, 'Hao Hao Seafood', 80),
('Hao Hao Sausage', 7, 4.12, 'Hao Hao Sausage', 150),
('Omachi Thai Hotpot', 7, 5.76, 'Omachi Thai Hotpot', 140),
('Omachi Sausage', 7, 7.41, 'Omachi Sausage', 70),
('Omachi Sour Spicy Shrimp', 7, 4.12, 'Omachi Sour Spicy Shrimp', 130),
('Omachi Rib', 7, 8.23, 'Omachi Rib', 160),
('Omachi Seafood', 7, 7.41, 'Omachi Seafood', 110),
('Omachi Grilled Chicken', 7, 4.12, 'Omachi Grilled Chicken', 60),
('Daddy Thai Hotpot', 7, 5.35, 'Daddy Thai Hotpot', 140),
('Daddy Sour Spicy Shrimp', 7, 5.35, 'Daddy Sour Spicy Shrimp', 70),
('Daddy Chicken', 7, 4.12, 'Daddy Chicken', 150),
('Daddy Beef', 7, 7.00, 'Daddy Beef', 120),
('Daddy Kimchi', 7, 6.58, 'Daddy Kimchi', 80),
('Daddy Seafood', 7, 7.82, 'Daddy Seafood', 100),
('Acecook Super Big Noodle Chicken', 7, 4.12, 'Acecook Super Big Noodle Chicken', 90),
('Acecook Super Big Noodle Shrimp', 7, 7.00, 'Acecook Super Big Noodle Shrimp', 130),
('Acecook Super Big Noodle Beef', 7, 7.00, 'Acecook Super Big Noodle Beef', 50),
('Acecook Super Big Noodle Thai Hotpot', 7, 8.23, 'Acecook Super Big Noodle Thai Hotpot', 120),
('Acecook Super Big Noodle Seafood', 7, 5.76, 'Acecook Super Big Noodle Seafood', 130),
('Acecook Super Big Noodle Spicy Chicken', 7, 7.82, 'Acecook Super Big Noodle Spicy Chicken', 90);
INSERT INTO product (Name, CategoryID, SellPrice, Description, Quantity) VALUES 
('Niva Tissue', 8, 2.26, 'Niva Tissue', 100),
('Koslim Tissue', 8, 2.47, 'Koslim Tissue', 150),
('Toilet Paper', 8, 0.21, 'Toilet Paper', 90),
('Cotton Wool', 8, 0.41, 'Cotton Wool', 130),
('Cotton Swab Pack', 8, 1.73, 'Cotton Swab Pack', 110),
('PS Toothbrush', 8, 0.41, 'PS Toothbrush', 60),
('Toothpaste', 8, 1.44, 'Toothpaste', 120),
('Dental Floss', 8, 0.53, 'Dental Floss', 140),
('Mouthwash', 8, 3.87, 'Mouthwash', 70),
('Bath Soap', 8, 3.95, 'Bath Soap', 160),
('Shower Gel', 8, 2.35, 'Shower Gel', 100),
('Shampoo', 8, 1.98, 'Shampoo', 90),
('Conditioner', 8, 2.14, 'Conditioner', 110),
('Hair Mask', 8, 1.28, 'Hair Mask', 80),
('Hair Gel', 8, 1.03, 'Hair Gel', 70),
('Perfume', 8, 0.49, 'Perfume', 130),
('Deodorant', 8, 0.78, 'Deodorant', 60),
('Razor', 8, 1.60, 'Razor', 150),
('Shaving Cream', 8, 2.72, 'Shaving Cream', 100),
('Sanitary Napkin', 8, 1.93, 'Sanitary Napkin', 90),
('Tampon', 8, 0.49, 'Tampon', 120),
('Nursing Pad', 8, 3.21, 'Nursing Pad', 110),
('Kitchen Paper', 8, 3.38, 'Kitchen Paper', 80),
('Dishwashing Liquid', 8, 2.55, 'Dishwashing Liquid', 140),
('Floor Cleaner', 8, 3.50, 'Floor Cleaner', 70),
('Glass Cleaner', 8, 2.39, 'Glass Cleaner', 160),
('Trash Bag', 8, 1.60, 'Trash Bag', 90),
('Multipurpose Cloth', 8, 1.36, 'Multipurpose Cloth', 50),
('Sponge', 8, 2.76, 'Sponge', 130),
('Dishcloth', 8, 3.04, 'Dishcloth', 120),
('Aluminum Foil', 8, 1.81, 'Aluminum Foil', 90),
('Parchment Paper', 8, 1.85, 'Parchment Paper', 80),
('Food Storage Box', 8, 3.38, 'Food Storage Box', 100),
('Food Storage Bag', 8, 2.55, 'Food Storage Bag', 70),
('Kitchen Knife', 8, 3.00, 'Kitchen Knife', 110);

--- IMPORT DATA FOR TABLE KHUYENMAI ---------- DONE
INSERT INTO KHUYENMAI (TenKhuyenMai, DanhSachMaSP, MoTa, NgayBatDau, NgayKetThuc, GiamGia) VALUES
('Sale T5 - 1', '1,2,3,4,5,6,7,8,9,10', 'Sales off Soft Drink', '2024-05-01', '2024-05-31', 0.34),
('Sale T5 - 2', '21,22,23,24,25,26,27,28,29,30', 'Sales off Beer and wine', '2024-05-31', '2024-05-01', 0.15),
('Sale T5 - 3', '60,61,62,63,64,65,66,67,68', 'Sales off Milk', '2024-05-01', '2024-05-31', 0.2),
('Sale T6 - 1', '123,124,125,127,129,131,132,135,140,141,146', 'Sales off Noodle', '2024-06-01', '2024-06-30', 0.4),
('Sale T6 - 2', '1,2,3,4,5,6,7,8,9,10,21,22,23,24,25,60,61,62,63,64,65,66', 'Sales', '2024-06-01', '2024-06-30', 0.12);

--- IMPORT DATA FOR TABLE DETAILS INVOICE -----------
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1005, 74, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1005, 1, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1006, 79, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1007, 127, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1008, 138, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1008, 119, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1008, 139, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1008, 4, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1011, 153, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1011, 124, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1011, 145, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1011, 32, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1011, 13, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1012, 33, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1013, 65, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1013, 73, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1013, 48, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1014, 30, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1014, 87, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1016, 158, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1017, 55, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1017, 6, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1017, 13, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1018, 24, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1019, 123, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1020, 123, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1021, 65, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1022, 42, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1023, 142, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1023, 16, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1023, 134, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1025, 152, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1025, 83, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1026, 151, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1026, 54, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1027, 106, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1027, 168, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1028, 17, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1028, 22, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1030, 51, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1030, 119, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1030, 146, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1031, 67, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1031, 77, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1031, 66, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1032, 130, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1033, 37, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1033, 126, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1034, 147, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1034, 39, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1034, 41, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1034, 122, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1035, 181, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1035, 1, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1036, 112, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1036, 78, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1036, 86, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1036, 48, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1038, 67, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1040, 5, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1041, 73, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1042, 129, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1042, 41, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1043, 137, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1043, 85, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1043, 120, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1043, 62, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1044, 18, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1044, 57, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1045, 21, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1045, 118, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1046, 7, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1046, 106, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1048, 46, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1048, 137, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1049, 91, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1051, 77, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1052, 141, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1052, 170, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1053, 47, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1053, 87, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1054, 136, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1055, 59, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1055, 98, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1055, 145, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1056, 133, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1056, 10, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1056, 122, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1056, 45, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1057, 30, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1057, 177, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1057, 11, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1058, 154, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1058, 165, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1058, 163, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1058, 82, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1058, 7, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1059, 93, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1060, 178, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1060, 54, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1060, 99, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1061, 8, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1061, 160, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1061, 166, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1062, 98, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1062, 12, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1063, 65, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1064, 177, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1064, 181, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1065, 124, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1066, 9, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1066, 36, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1066, 133, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1068, 144, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1068, 47, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1068, 176, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1069, 72, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1069, 167, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1069, 96, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1070, 92, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1070, 98, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1071, 150, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1072, 135, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1072, 138, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1072, 51, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1072, 47, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1073, 25, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1073, 176, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1074, 122, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1074, 175, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1075, 20, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1076, 22, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1077, 135, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1077, 71, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1077, 124, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1078, 158, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1078, 141, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1078, 6, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1078, 91, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1079, 70, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1079, 38, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1080, 33, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1081, 146, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1081, 95, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1081, 98, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1082, 94, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1083, 44, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1083, 43, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1083, 157, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1084, 137, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1084, 84, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1084, 111, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1085, 167, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1086, 173, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1087, 146, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1087, 2, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1087, 52, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1087, 147, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1087, 89, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1089, 170, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1089, 57, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1089, 26, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1090, 34, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1090, 165, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1090, 42, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1091, 87, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1091, 131, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1091, 83, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1091, 169, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1092, 90, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1093, 56, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1093, 48, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1093, 80, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1093, 66, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1094, 90, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1094, 47, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1094, 50, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1095, 13, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1095, 118, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1095, 7, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1095, 79, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1096, 6, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1096, 37, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1097, 14, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1097, 23, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1098, 68, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1098, 43, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1100, 98, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1100, 68, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1101, 93, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1101, 55, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1102, 108, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1102, 25, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1102, 53, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1102, 135, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1102, 152, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1102, 18, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1103, 78, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1103, 140, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1103, 60, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1105, 61, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1105, 169, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1105, 179, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1105, 179, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1105, 44, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1106, 167, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1106, 126, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1106, 68, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1106, 176, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1106, 67, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1106, 128, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1106, 24, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1107, 175, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1108, 16, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1108, 157, 10);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1108, 142, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1109, 49, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1109, 63, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1110, 142, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1111, 109, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1112, 69, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1113, 161, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1113, 77, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1115, 21, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1115, 17, 5);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1115, 163, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1116, 176, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1117, 78, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1117, 18, 6);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1118, 97, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1118, 128, 11);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1118, 47, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1120, 179, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1121, 122, 1);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1121, 99, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1121, 103, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1121, 133, 7);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1122, 64, 2);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1123, 107, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1123, 74, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1123, 100, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1123, 25, 3);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1123, 71, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1124, 63, 15);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1124, 47, 14);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1124, 27, 8);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1124, 181, 4);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1125, 72, 12);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1125, 174, 13);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1126, 24, 9);
INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity) VALUES (1126, 68, 14);

