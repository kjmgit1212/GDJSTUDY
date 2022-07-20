-- 함수 확인용 데이터
DROP TABLE STUDENT;
CREATE TABLE STUDENT (
    NAME VARCHAR2(20 BYTE),
    KOR  NUMBER,
    ENG  NUMBER,
    MAT  NUMBER
);
INSERT INTO STUDENT(NAME, KOR, ENG, MAT) VALUES(NULL, 100, 100, 100);
INSERT INTO STUDENT(NAME, KOR, ENG, MAT) VALUES('영수', 100, NULL, 100);
INSERT INTO STUDENT(NAME, KOR, ENG, MAT) VALUES('진영', NULL, 100, 100);
COMMIT;


-- NULL 처리
-- 1. 연산에 NULL이 포함되면 결과는 NULL이다.
-- 2. NULL 값을 다른 값으로 변경하는 것이 일반적이다.

-- 1. NVL 함수
--    NVL(칼럼, 칼럼값이 NULL일 때 대신 사용할 값)
SELECT 
       NVL(NAME, '아무개')
     , NVL(KOR, 0)
     , NVL(ENG, 0)
     , NVL(MAT, 0)
  FROM
       STUDENT;


-- 2. NVL2 함수
--    NVL2(칼럼, 칼럼값이 NULL이 아닐 때 사용할 값, 칼럼값이 NULL일 때 사용할 값)
SELECT
       NVL(NAME, '아무개')
     , NVL2(KOR, '국어응시', '국어결시')
     , NVL2(ENG, '영어응시', '영어결시')
     , NVL2(MAT, '수학응시', '수학결시')
  FROM
       STUDENT;