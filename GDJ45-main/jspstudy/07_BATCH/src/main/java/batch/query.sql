-- 1. ROWNUM 칼럼 & ROW_NUMBER() 함수
-- 1) 높은 평균 순서로 정렬한다.
-- 2) 정렬된 결과에 1,2,3... 행번호를 붙인다.
-- 3) 행번호가 1~3인 레코드를 가져온다.

-- 1) ROWNUM 칼럼
SELECT B.RN, B.STU_NO, B.NAME, B.AVG
  FROM (SELECT ROWNUM AS RN, A.STU_NO, A.NAME, A.AVG
          FROM (SELECT STU_NO, NAME, AVG
                  FROM STUDENT
                 ORDER BY AVG DESC) A) B
 WHERE B.RN BETWEEN 1 AND 3;

-- 2) ROW_NUMBER() OVER(ORDER BY 칼럼)
SELECT S.RN, S.STU_NO, S.NAME, S.AVG
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY AVG DESC) AS RN, STU_NO, NAME, AVG
          FROM STUDENT) S
 WHERE S.RN BETWEEN 1 AND 3;



-- 2. RANK() 함수
-- 1) 높은 평균 순서로 순위를 매긴다.
-- 2) 순위가 1~3인 레코드를 가져온다.

-- 3) RANK() OVER(ORDER BY 칼럼)
SELECT S.순위, S.STU_NO, S.NAME, S.AVG
  FROM (SELECT RANK() OVER(ORDER BY AVG DESC) AS 순위, STU_NO, NAME, AVG
          FROM STUDENT) S
 WHERE S.순위 BETWEEN 1 AND 3;