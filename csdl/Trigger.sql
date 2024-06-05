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


-- TRIGGER FOR Customer.PurchasePoints BY Invoice.ToTalMoney
CREATE TRIGGER Trigger_Customer_PurchasePoints
ON Invoice
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @TotalMoney MONEY;
    DECLARE @CustomerID INT;

    -- Sử dụng CTE để tính toán và cập nhật điểm tích lũy
    WITH UpdatedInvoice AS (
        SELECT InvoiceID, CustomerID, ToTalMoney
        FROM inserted
    )
    UPDATE Customer
    SET PurchasePoints = Customer.PurchasePoints + CONVERT(INT, UI.ToTalMoney * 0.05) -- 10%
    FROM Customer
    INNER JOIN UpdatedInvoice UI
    ON Customer.CustomerID = UI.CustomerID;
END;

CREATE TRIGGER Trigger_Customer_PurchasePoints
ON Invoice
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @TotalMoney MONEY;
    DECLARE @CustomerID INT;

    -- Sử dụng CTE để tính toán và cập nhật điểm tích lũy
    WITH UpdatedInvoice AS (
        SELECT CustomerID, ToTalMoney
        FROM inserted
    )
    UPDATE C
    SET PurchasePoints = ROUND(C.PurchasePoints + ISNULL(UI.ToTalMoney, 0) * 0.05, 2) -- 5% tính trên tổng tiền, làm tròn đến 2 số sau dấu phẩy
    FROM Customer AS C
    LEFT JOIN UpdatedInvoice AS UI ON C.CustomerID = UI.CustomerID;
END;


-- TRIGGER UPDATE COUNTDOWN DONGIA FOR TABLE CTHOADON FROM TABLE SANPHAM WHERE KHUYENMAI ---
CREATE TRIGGER Update_DI_Value
ON DetailsInvoice
AFTER INSERT
AS
BEGIN
    UPDATE CT
    SET Value = CASE 
                    WHEN KM.MaKhuyenMai IS NOT NULL AND HD.DateBill BETWEEN KM.NgayBatDau 
						 AND KM.NgayKetThuc THEN SP.SellPrice - (SP.SellPrice * KM.GiamGia)
                    ELSE SP.SellPrice
                END
    FROM DetailsInvoice CT
    INNER JOIN inserted I ON CT.InvoiceID = I.InvoiceID AND CT.ProductID = I.ProductID
    INNER JOIN Product SP ON I.ProductID = SP.ProductID
    INNER JOIN Invoice HD ON CT.InvoiceID = HD.InvoiceID
    LEFT JOIN KhuyenMai KM ON CHARINDEX(',' + CAST(CT.ProductID AS NVARCHAR(10)) 
			  + ',', ',' + KM.DanhSachMaSP + ',') > 0
END

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