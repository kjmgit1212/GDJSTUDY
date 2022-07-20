-- 메타 데이터
-- 데이터베이스를 구성하는 객체(테이블, 사용자, 인덱스, 뷰 등)들의 정보를 가진 데이터
-- 테이블 형식으로 존재


-- DESCRIBE
-- 테이블의 구조를 확인하는 명령
-- DESCRIBE 테이블명;
-- DESC 테이블명;


-- SELECT
-- 테이블 조회 쿼리문
-- SELECT 칼럼 
--   FROM 테이블 
-- [WHERE 조건];


-- [테이블]의 메타 데이터 확인
-- USER_TABLES : 현재 접속한 사용자가 작성한 테이블 목록
-- DBA_TABLES  : 관리자가 작성한 테이블 목록
-- ALL_TABLES  : 모든 테이블 목록

DESC ALL_TABLES;

SELECT OWNER, TABLE_NAME, TABLESPACE_NAME
  FROM ALL_TABLES
 WHERE OWNER != 'SYS';


-- [오브젝트]의 메타 데이터 확인

DESC USER_OBJECTS;

SELECT *  -- * : 모든 칼럼 조회
  FROM USER_OBJECTS;


-- [제약조건]의 메타 데이터 확인

DESC USER_CONSTRAINTS;

SELECT *
  FROM USER_CONSTRAINTS
 WHERE TABLE_NAME = 'ENROLL';