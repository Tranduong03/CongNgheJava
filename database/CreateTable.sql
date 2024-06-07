SELECT * FROM Customer;
SELECT * FROM Employee;
SELECT * FROM Category;
SELECT * FROM Product;
SELECT * FROM Invoice where InvoiceID = 1128;
SELECT * FROM DetailsInvoice where InvoiceID = 1128;
SELECT * FROM KhuyenMai;

-- TABLE KHACHHANG ---------
CREATE TABLE Customer (
	CustomerID INT IDENTITY(1001,1) PRIMARY KEY,
	Name NVARCHAR(65) NOT NULL,
	Address NVARCHAR(100),
	Phone VARCHAR(15) UNIQUE NOT NULL,
	Gender BIT DEFAULT 0,	-- 0 Nam, 1 Nu
	Email VARCHAR(100),
	Birthdate DATE CHECK (Birthdate < GETDATE()),
	PurchasePoints MONEY DEFAULT 0
)

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
	Gender BIT DEFAULT 0,   -- 0 Nam, 1 Nu
	Salary float,
	Address varchar(60)
)

--- CREATE TABLE CATEGORY ---------
CREATE TABLE Category (
	CategoryID INT IDENTITY(1,1) PRIMARY KEY,
	Name VARCHAR(50) NOT NULL,
	Description VARCHAR(128)
)

--- CREATE TABLE PRODUCT ----------
CREATE TABLE Product (
	ProductID INT IDENTITY(1,1) PRIMARY KEY,
	Name VARCHAR(80) NOT NULL,
	CategoryID INT FOREIGN KEY REFERENCES Category(CategoryID),
	SellPrice FLOAT,
	Description NVARCHAR(250),
	Quantity INT DEFAULT 0, 
	Status BIT DEFAULT 1
)

--- CREATE TABLE BILL -------------
CREATE TABLE Invoice (
	InvoiceID INT IDENTITY(1001,1) PRIMARY KEY, -- Số Hóa Đơn in ra chi tiết Hóa đơn
	InvoiceCode VARCHAR(15) UNIQUE, -- Mã Hóa Đơn hiển thị ở Tab Bảng Hóa Đơn( để click vào)
	CustomerID INT FOREIGN KEY REFERENCES Customer(CustomerID),
	EmployeeID INT FOREIGN KEY REFERENCES Employee(EmployeeID),
	DateBill SMALLDATETIME DEFAULT GETDATE() CHECK(DateBill <= GetDate()),
	ToTalMoney MONEY DEFAULT 0
)	

--- CREATE TABLE DETAILS INVOICE -------
CREATE TABLE DetailsInvoice (
	InvoiceID INT FOREIGN KEY REFERENCES Invoice(InvoiceID),
	ProductID INT FOREIGN KEY REFERENCES Product(ProductID),
	Quantity INT CHECK (Quantity > 0),
	Value MONEY CHECK (VALUE > 0),
	SubTotal AS Quantity * Value,
	PRIMARY KEY (InvoiceID, ProductID)
);

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