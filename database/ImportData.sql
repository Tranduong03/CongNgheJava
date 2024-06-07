--- IMPORT DATA FOR TABLE CUSTOMER --------------------
INSERT INTO Customer (Name, Address, Phone, Gender, Email, Birthdate) VALUES
('Example Name', 'Example Address', '0909090909', 1, 'Example@gmail.com', NULL),
('Phan Tuan Kiet', 'Binh Dinh', '0969777210', 1, 'phantuankiet@nv.dkstore.com', '2004-06-19'),
('Tran Nguyen Phi Duong', 'Phu Yen', '0348102328', 1, 'tranduong@gmail.com', '2004-07-05')
INSERT INTO Customer (Name, Address, Phone, Gender, Email, Birthdate) VALUES
('Phan Thi Thanh Thuy', 'Thanh Hoa', '0822739053', 0, NULL, NULL),
('Le Dinh Tri', 'Thanh pho Soc Trang, Soc Trang', '0304196439', 1, NULL, NULL),
('Huynh Thi Thanh Tu', NULL, '0314558354', 0, NULL, NULL),
('Bui Thi Thu Thao', 'Tuy Hoa, Phu Yen', '0713602143', 0, 'buittthao@st.chuvanan.edu.vn', NULL),
('Bui Dinh Hai', 'Tuy Hoa, Phu Yen', '0932851151', 1, NULL, '2009-06-17'),
('Ly Trong Kiet', NULL, '0785321711', 1, NULL, NULL),
('Vu Dinh Loc', NULL, '0358694201', 1, NULL, '2008-12-12'),
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
('Le Thi Thuy Hang', 'Binh Thuan', '0988818269', 0, NULL, '2007-06-04'),
('Nguyen Van Phong',  NULL, '0199333657', 1, NULL, '2009-08-24'),
('Nguyen Thi Kim Ngan',  'Dong Thap', '0990870430', 0, NULL, '2000-05-03');

INSERT INTO Invoice (CustomerID, EmployeeID) VALUES (1004, 1011)
INSERT INTO DetailsInvoice (InvoiceID, ProductID, Quantity) VALUES (1128, 54, 10);