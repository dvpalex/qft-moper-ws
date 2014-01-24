-- Insert RowType : Tipos de Linhas para layouts

USE moper_dsv;

IF EXISTS(SELECT * FROM ROWTYPE)
	DELETE FROM ROWTYPE
GO

SET IDENTITY_INSERT ROWTYPE ON 

INSERT INTO ROWTYPE(ROWTYPE_ID, DESCR)VALUES(1, 'HEADER');
GO
INSERT INTO ROWTYPE(ROWTYPE_ID, DESCR)VALUES(2, 'DETAIL');
GO
INSERT INTO ROWTYPE(ROWTYPE_ID, DESCR)VALUES(3, 'TRAILLER');
GO
SET IDENTITY_INSERT ROWTYPE OFF
GO