-- CREATE TABLE과 서브쿼리
-- 1. 서브쿼리 결과 집합을 새로운 테이블로 생성할 수 있다.
-- 2. 서브쿼리 결과 집합이 없는 경우(행(ROW)이 없는 경우)에도 새로운 테이블을 생성할 수 있다.
--    이 경우 새로운 테이블에는 칼럼만 존재한다.
-- 3. NOT NULL 제약조건을 제외한 제약조건은 복사되지 않는다.




-- 1. MEMBER_NO가 1인 데이터만 MEMBER2 테이블로 복사
CREATE TABLE MEMBERS2
    AS (SELECT MEMBER_NO, MEMBER_ID, NAME, REGISTE_DATE
          FROM MEMBERS
         WHERE MEMBER_NO = 1);

-- 기본키는 별도로 지정
ALTER TABLE MEMBERS2
    ADD CONSTRAINT MEMBERS2_PK PRIMARY KEY(MEMBER_NO);



-- 2. MEMBERS 테이블의 구조만 복사(행은 복사하지 않기)
CREATE TABLE MEMBERS3
    AS (SELECT MEMBER_NO, MEMBER_ID, NAME, REGISTE_DATE
          FROM MEMBERS
         WHERE 1 = 2);

-- 기본키는 별도로 지정
ALTER TABLE MEMBERS3
    ADD CONSTRAINT MEMBERS3_PK PRIMARY KEY(MEMBER_NO);




-- 3. BOARDS 테이블의 전체 데이터를 BOARDS2 테이블로 복사
CREATE TABLE BOARDS2
    AS (SELECT BOARD_NO, TITLE, CONTENT, MEMBER_ID, CREATE_DATE
          FROM BOARDS);

-- 기본키는 별도로 지정
ALTER TABLE BOARDS2
    ADD CONSTRAINT BOARDS2_PK PRIMARY KEY(BOARD_NO);




-- INSERT와 서브쿼리
-- VALUES절 대신 서브쿼리를 사용할 수 있다.
INSERT INTO MEMBERS2(MEMBER_NO, MEMBER_ID, NAME, REGISTE_DATE)
(SELECT MEMBER_NO, MEMBER_ID, NAME, REGISTE_DATE
         FROM MEMBERS
        WHERE MEMBER_NO = 2);




-- UPDATE와 서브쿼리
-- SET절에서 서브쿼리를 사용할 수 있다.

UPDATE BOARDS2
   SET (TITLE, CONTENT) = (SELECT TITLE, CONTENT
                             FROM BOARDS2
                            WHERE BOARD_NO = 5)
 WHERE BOARD_NO = 3;




-- DELETE와 서브쿼리
-- WHERE절에서 서브쿼리를 사용할 수 있다.

DELETE
  FROM BOARDS2
 WHERE (TITLE, CONTENT) = (SELECT TITLE, CONTENT
                             FROM BOARDS2
                            WHERE BOARD_NO = 5);


COMMIT;