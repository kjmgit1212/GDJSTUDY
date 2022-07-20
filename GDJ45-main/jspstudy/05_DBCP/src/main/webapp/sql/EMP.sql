DROP TABLE EMP;
CREATE TABLE EMP
(
    EMPNO NUMBER PRIMARY KEY,
    NAME VARCHAR2(20 BYTE),
    DEPT VARCHAR2(20 BYTE),
    HIRED DATE
);

DROP SEQUENCE EMP_SEQ;
CREATE SEQUENCE EMP_SEQ 
INCREMENT BY 1 
START WITH 1 
NOMAXVALUE 
NOMINVALUE 
NOCYCLE
NOCACHE;

INSERT INTO EMP VALUES (EMP_SEQ.NEXTVAL, '이정재', '총무', SYSDATE);
COMMIT;
