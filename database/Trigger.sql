
-- TRIGGER CREATE INVOICRE CODE BY INVOICEID--------
CREATE TRIGGER CreateInvoiceCode
ON Invoice
AFTER INSERT
AS
BEGIN
    UPDATE Invoice
    SET InvoiceCode = 'HDDK' + CAST(Inserted.InvoiceID AS VARCHAR(15))
    FROM Invoice
    INNER JOIN Inserted
    ON Invoice.InvoiceID = Inserted.InvoiceID;;
END;


-- TRIGGER FOR Customer.PurchasePoints BY Details Invoice.SubToTal 
CREATE TRIGGER UpdatePurchasePoints
ON DetailsInvoice
AFTER INSERT
AS
BEGIN
    DECLARE @InvoiceID INT;
    DECLARE @CustomerID INT;

    -- Lấy InvoiceID từ bảng Inserted
    SELECT @InvoiceID = i.InvoiceID
    FROM Inserted i;

    -- Lấy CustomerID từ bảng Invoice dựa trên InvoiceID
    SELECT @CustomerID = CustomerID
    FROM Invoice
    WHERE InvoiceID = @InvoiceID;

    -- Cập nhật PurchasePoints của khách hàng
    UPDATE Customer
    SET PurchasePoints = (SELECT SUM(Quantity * Value * 0.05) -- 5% của SubTotal
                         FROM DetailsInvoice WHERE InvoiceID = @InvoiceID)
    WHERE CustomerID = @CustomerID;
END;


--- TRIGGER AUTO UPDATE TOTAL MONEY FOR TABLE INVOICE FROM TABLE DETAIL INVOICE ------------------------
CREATE TRIGGER UpdateTongTien
ON DetailsInvoice
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
	DECLARE @InvoiceID INT
	SELECT @InvoiceID = InvoiceID FROM inserted
	IF @InvoiceID IS NULL SELECT @InvoiceID = InvoiceID FROM deleted

	UPDATE Invoice
	SET TotalMoney = (SELECT SUM(SubTotal) FROM DetailsInvoice WHERE InvoiceID = @InvoiceID)
	WHERE InvoiceID = @InvoiceID
END

-- TRIGGER UPDATE COUNTDOWN DONGIA FOR TABLE CTHOADON FROM TABLE SANPHAM WHERE KHUYENMAI ---
CREATE TRIGGER Update_DI_Value
ON DetailsInvoice
AFTER INSERT
AS
BEGIN
    -- Declare variables to hold the promotion discount and product price
    DECLARE @ProductID INT;
    DECLARE @InvoiceID INT;
    DECLARE @SellPrice FLOAT;
    DECLARE @Discount FLOAT;
    DECLARE @DateBill SMALLDATETIME;

    -- Cursor to loop through the inserted rows
    DECLARE cur CURSOR FOR 
    SELECT I.InvoiceID, I.ProductID, SP.SellPrice, HD.DateBill
    FROM inserted I
    INNER JOIN Product SP ON I.ProductID = SP.ProductID
    INNER JOIN Invoice HD ON I.InvoiceID = HD.InvoiceID;

    OPEN cur;
    FETCH NEXT FROM cur INTO @InvoiceID, @ProductID, @SellPrice, @DateBill;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Check if there is a promotion for the product and date
        SELECT @Discount = KM.GiamGia
        FROM KhuyenMai KM
        WHERE CHARINDEX(',' + CAST(@ProductID AS NVARCHAR(10)) + ',', ',' + KM.DanhSachMaSP + ',') > 0
          AND @DateBill BETWEEN KM.NgayBatDau AND KM.NgayKetThuc;

        -- Update the value in DetailsInvoice based on the promotion
        UPDATE DetailsInvoice
        SET Value = CASE 
                        WHEN @Discount IS NOT NULL THEN @SellPrice - (@SellPrice * @Discount)
                        ELSE @SellPrice
                    END
        WHERE InvoiceID = @InvoiceID AND ProductID = @ProductID;

        FETCH NEXT FROM cur INTO @InvoiceID, @ProductID, @SellPrice, @DateBill;
    END

    CLOSE cur;
    DEALLOCATE cur;
END;


--- 
CREATE TRIGGER trg_InsertDetailsInvoice
ON DetailsInvoice
AFTER INSERT
AS
BEGIN
    DECLARE @ProductID INT;
    DECLARE @InvoiceID INT;
    DECLARE @Quantity INT;

    -- Get the values from the inserted row
    SELECT @ProductID = ProductID, @InvoiceID = InvoiceID, @Quantity = Quantity
    FROM inserted;

    -- Check the current stock quantity of the product
    IF EXISTS (
        SELECT 1 
        FROM Product 
        WHERE ProductID = @ProductID AND Quantity >= @Quantity
    )
    BEGIN
        -- If there is enough stock, update the product's quantity
        UPDATE Product
        SET Quantity = Quantity - @Quantity
        WHERE ProductID = @ProductID;

        -- Insert the details invoice
        INSERT INTO DetailsInvoice (InvoiceID, ProductID, Quantity, Value)
        SELECT InvoiceID, ProductID, Quantity, Value
        FROM inserted;
    END
    ELSE
    BEGIN
        -- Handle the case where there is not enough stock
        RAISERROR ('Not enough stock for product', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
