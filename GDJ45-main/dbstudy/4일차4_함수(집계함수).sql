-- 함수 확인용 데이터
DROP TABLE STUDENT;
CREATE TABLE STUDENT (
    NAME VARCHAR2(20 BYTE),
    KOR  NUMBER,
    ENG  NUMBER,
    MAT  NUMBER
);
INSERT INTO STUDENT(NAME, KOR, ENG, MAT) VALUES('정숙', 100, 100, 100);
INSERT INTO STUDENT(NAME, KOR, ENG, MAT) VALUES('영수', 100, NULL, 100);
INSERT INTO STUDENT(NAME, KOR, ENG, MAT) VALUES('진영', NULL, 100, 100);
COMMIT;


-- 집계함수 (그룹함수)
-- 1. 통계에서 사용(합계, 평균 등)한다.
-- 2. NULL값을 연산에서 제외한다.
-- 3. 종류
--    1) 합계 : SUM(칼럼)
--    2) 평균 : AVG(칼럼)
--    3) 최대 : MAX(칼럼)
--    4) 최소 : MIN(칼럼)
--    5) 개수 : COUNT(칼럼)

-- 각 칼럼의 합계
SELECT
       SUM(KOR)
     , SUM(ENG)
     , SUM(MAT)
--   , SUM(KOR, ENG, MAT)  :  집계함수의 인수는 1개만 가능하다. 인수가 3개이므로 불가능.
--   , SUM(KOR + ENG + MAT)  :  어떤 레코드의 KOR + ENG + MAT
     , SUM(KOR) + SUM(ENG) + SUM(MAT)  -- 국어, 영어, 수학의 전체 합
  FROM
       STUDENT;


-- NULL은 제외하고 평균을 구한다.
SELECT
       AVG(KOR)
     , AVG(ENG)
     , AVG(MAT)
  FROM
       STUDENT;

-- NULL인 경우 결시로 가정하고 0점 처리한 뒤 평균을 구한다.
SELECT
       AVG(NVL(KOR, 0))
     , AVG(NVL(ENG, 0))
     , AVG(NVL(MAT, 0))
  FROM
       STUDENT;

-- 국어 시험을 응시한 사람이 몇 명인지 구한다.
SELECT
       COUNT(KOR)
  FROM
       STUDENT;

-- 행(ROW)의 갯수를 구한다. (인원수를 구한다.)
SELECT
       COUNT(NAME)  -- NAME에 NULL이 없으므로 가능
     , COUNT(KOR)   -- KOR에 NULL이 있으므로 불가능
     , COUNT(ENG)   -- ENG에 NULL이 있으므로 불가능
     , COUNT(MAT)   -- MAT에 NULL이 없으므로 가능
  FROM
       STUDENT;

-- 특정 칼럼에 따라 차이가 발생하므로 행(ROW)의 개수를 구할 때는 모든 칼럼(*)을 참조한다.
SELECT
       COUNT(*)
  FROM
       STUDENT;
