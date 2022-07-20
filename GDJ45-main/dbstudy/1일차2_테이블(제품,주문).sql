/*
    1. 테이블 작성 방법
        CREATE TABLE 테이블명(칼럼명 타입 [제약조건], 칼럼명 타입 [제약조건], [제약조건] ...);
    
    2. 타입
        1) 고정 길이 문자열 : CHAR
        2) 가변 길이 문자열 : VARCHAR2
        3) 숫자 : NUMBER
        4) 날짜 : DATE, TIMESTAMP
    
    3. 제약조건
        1) NOT NULL : 널 값 불가능
        2) UNIQUE : 중복 불가능
        3) CHECK : 값의 제한(조건으로 지정)
        4) PRIMARY KEY : 기본키 (개체무결성 제약조건)
        5) FOREIGN KEY : 외래키 (참조무결성 제약조건)
*/

-- 제품테이블
-- 1-2. PRIMARY KEY 제약조건의 이름이 없는 방식
CREATE TABLE PRODUCT(
    PRODUCT_NO   NUMBER PRIMARY KEY,
    PRODUCT_NAME VARCHAR2(30 BYTE),
    PRICE        NUMBER,
    STOCK        NUMBER
);
CREATE TABLE PRODUCT(
    PRODUCT_NO   NUMBER,
    PRODUCT_NAME VARCHAR2(30 BYTE),
    PRICE        NUMBER,
    STOCK        NUMBER,
    PRIMARY KEY(PRODUCT_NO)
);
-- 3. PRIMARY KEY 제약조건의 이름이 PRODUCT_PK로 지정되는 방식
CREATE TABLE PRODUCT(
    PRODUCT_NO   NUMBER,
    PRODUCT_NAME VARCHAR2(30 BYTE),
    PRICE        NUMBER,
    STOCK        NUMBER,
    CONSTRAINTS  PRODUCT_PK PRIMARY KEY(PRODUCT_NO)
);


-- 주문테이블
CREATE TABLE ORDERS(
    ORDER_NO   NUMBER PRIMARY KEY,
    ORDER_NAME VARCHAR2(15 BYTE),
    PRODUCT_NO NUMBER REFERENCES PRODUCT(PRODUCT_NO),
    ORDER_DATE DATE
);
CREATE TABLE ORDERS(
    ORDER_NO   NUMBER,
    ORDER_NAME VARCHAR2(15 BYTE),
    PRODUCT_NO NUMBER,
    ORDER_DATE DATE,
    PRIMARY KEY(ORDER_NO),
    FOREIGN KEY(PRODUCT_NO) REFERENCES PRODUCT(PRODUCT_NO)
);
CREATE TABLE ORDERS(
    ORDER_NO   NUMBER,
    ORDER_NAME VARCHAR2(15 BYTE),
    PRODUCT_NO NUMBER,
    ORDER_DATE DATE,
    CONSTRAINTS ORDERS_PK PRIMARY KEY(ORDER_NO),
    CONSTRAINTS ORDERS_PRODUCT_FK FOREIGN KEY(PRODUCT_NO) REFERENCES PRODUCT(PRODUCT_NO)
);

/*
    1. 참조 관계의 테이블
        1) 기본키를 가진 테이블 : 부모테이블
        2) 외래키를 가진 테이블 : 자식테이블
    
    2. 참조 관계에서 삭제시 주의할 점
        1) 자식테이블을 먼저 지운다. (외래키를 가진 테이블을 먼저 지운다.)
        2) 부모테이블을 나중에 지운다.
    
    3. 테이블 삭제
        DROP TABLE 테이블명;
*/

-- 외래키를 가진 '주문(ORDERS)테이블' 먼저 삭제
DROP TABLE ORDERS;

-- 기본키를 가진 '제품(PRODUCT)테이블' 나중에 삭제
DROP TABLE PRODUCT;
