--- SELECT TABLE -----
SELECT * FROM Customer;
SELECT * FROM Invoice where CustomerID = 1009;
SELECT * FROM DetailsInvoice;
------------------------------------------------------------------------------------------------------------
delete from  DetailsInvoice
-- TABLE KHACHHANG ---------
CREATE TABLE Customer (
	CustomerID INT IDENTITY(1001,1) PRIMARY KEY,
	Name NVARCHAR(60) NOT NULL,
	Address NVARCHAR(100),
	Phone VARCHAR(15) UNIQUE NOT NULL,
	Gender BIT DEFAULT 0,	-- 0 Nu, 1 Nam
	Email VARCHAR(100),
	Birthdate DATE CHECK (Birthdate < GETDATE()),
)
ALTER TABLE Customer
ADD PurchasePoints MONEY DEFAULT 0;
UPDATE Customer
SET PurchasePoints = 0;
update Invoice set totalMoney = 0;
------------------------------------------------------------------------------------------------------------

--- CREATE TABLE Employee ----
CREATE TABLE Employee(
	EmployeeID int identity(1001,1) primary key,
	Account varchar(60),
	Password varchar(30),
	Name nvarchar(60),
	BirthDate date,
	HireDate date,
	Phone varchar(20),
	Email varchar(50),
	Gender bit,
	Salary float,
	Address varchar(60)
)
------------------------------------------------------------------------------------------------------------

-- TABLE KHUYENMAI ---------
CREATE TABLE KhuyenMai (
    MaKhuyenMai INT IDENTITY(1,1) PRIMARY KEY,
    TenKhuyenMai NVARCHAR(50) NOT NULL,
	DanhSachMaSP NVARCHAR(MAX), -- cách nhau bởi dấu phẩy
    MoTa NVARCHAR(255),
    NgayBatDau DATE,
    NgayKetThuc DATE,
    GiamGia FLOAT NOT NULL -- phần trăm giảm giá
);
ALTER TABLE KHUYENMAI ADD CONSTRAINT CHK_Time_Sales CHECK (NgayKetThuc >= NgayBatDau);
------------------------------------------------------------------------------------------------------------

--- CREATE TABLE BILL -------------
CREATE TABLE Invoice (
	InvoiceID INT IDENTITY(1001,1) PRIMARY KEY, -- Số Hóa Đơn ở Tab Hóa Đơn
	InvoiceCode VARCHAR(15) UNIQUE, -- Mã Hóa Đơn ở Tab CTHD
	CustomerID INT FOREIGN KEY REFERENCES Customer(CustomerID),
	EmployeeID INT FOREIGN KEY REFERENCES Employee(EmployeeID),
	DateBill SMALLDATETIME DEFAULT GETDATE() CHECK(DateBill < GetDate()),
	ToTalMoney MONEY DEFAULT 0
)	
------------------------------------------------------------------------------------------------------------

--- CREATE TABLE DETAILS INVOICE -------
CREATE TABLE DetailsInvoice (
	InvoiceID INT FOREIGN KEY REFERENCES Invoice(InvoiceID),
	ProductID INT FOREIGN KEY REFERENCES Product(ProductID),
	Quantity INT CHECK (Quantity > 0),
	Value MONEY CHECK (VALUE > 0),
	SubTotal AS Quantity * Value,
	PRIMARY KEY (InvoiceID, ProductID)
);
------------------------------------------------------------------------------------------------------------

