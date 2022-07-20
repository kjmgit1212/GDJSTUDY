-- 기타 함수


-- 1. 순위
--    1) RANK() OVER (ORDER BY 순위구할칼럼 ASC)  : 오름차순 순위(낮은 값이 1등), ASC는 생략 가능
--    2) RANK() OVER (ORDER BY 순위구할칼럼 DESC) : 내림차순 순위(높은 값이 1등)
--    3) 같은 값이면 동점으로 처리(등수가 같음)

-- 1) EMPLOYEES테이블의 사원 정보를 연봉(SALARY) 순으로 조회하시오. (높은 연봉이 1등)
--    각 사원의 연봉순위를 함께 조회하시오.
SELECT
       RANK() OVER (ORDER BY SALARY DESC) AS 연봉순위
     , EMAIL
     , SALARY
  FROM
       EMPLOYEES
 ORDER BY
       SALARY DESC;

-- 2) EMPLOYEES테이블의 사원 정보를 입사(HIRE_DATE) 순으로 조회하시오. (먼저 입사한 사원이 1등)
--    각 사원의 입사순위를 함께 조회하시오.
SELECT
       RANK() OVER (ORDER BY HIRE_DATE ASC) AS 입사순위
     , EMAIL
     , HIRE_DATE
  FROM
       EMPLOYEES
 ORDER BY
       HIRE_DATE ASC;


-- 2. 그룹 내의 순위
--    RANK() OVER (PARTITION BY 그룹칼럼 ORDER BY 순위구할칼럼 ASC)  : 그룹 내 오름차순 순위
--    RANK() OVER (PARTITION BY 그룹칼럼 ORDER BY 순위구할칼럼 DESC) : 그룹 내 내림차순 순위
SELECT
       RANK() OVER (PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS 부서내연봉순위
     , DEPARTMENT_ID
     , SALARY
  FROM
       EMPLOYEES
 ORDER BY
       DEPARTMENT_ID, SALARY DESC;


-- PARTITION BY와 OVER 활용하기
-- PARTITION BY는 그룹화 작업을 수행하므로 그룹함수(집계함수)와 함께 사용 가능
SELECT
       DISTINCT DEPARTMENT_ID
     , SUM(SALARY) OVER (PARTITION BY DEPARTMENT_ID) AS 부서별연봉합
     , AVG(SALARY) OVER (PARTITION BY DEPARTMENT_ID) AS 부서별연봉평균
     , MAX(SALARY) OVER (PARTITION BY DEPARTMENT_ID) AS 부서별최대연봉
     , MIN(SALARY) OVER (PARTITION BY DEPARTMENT_ID) AS 부서별최소연봉
     , COUNT(*)    OVER (PARTITION BY DEPARTMENT_ID) AS 부서별사원수
  FROM
       EMPLOYEES;


-- 3. 분기
--    DECODE(표현식, 값1, 출력1, 값2, 출력2, 값3, 출력3, ...)
--    표현식과 값의 비교는 동등 비교(=)만 가능

-- EMPLOYEES, DEPARTMENTS 테이블의 JOIN 대신 DECODE 함수 사용
-- DEPARTMENT_ID   DEPARTMENT_NAME
-- 10              Administration
-- 20              Marketing
-- 30              Purchasing
-- 40              Human Resources
-- 50              Shipping
SELECT
       DEPARTMENT_ID AS 부서번호
     , DECODE(DEPARTMENT_ID  -- 표현식(DEPARTMENT_ID)
            , 10, 'Administration'
            , 20, 'Marketing'
            , 30, 'Purchasing'
            , 40, 'Human Resources'
            , 50, 'Shipping') AS 부서명
  FROM
       EMPLOYEES;

-- PHONE_NUMBER  RESULT
-- 011           'HP'
-- 515           'EAST'
-- 590           'WEST'
-- 603           'SOUTH'
-- 650           'NORTH'
SELECT
       PHONE_NUMBER
     , DECODE(SUBSTR(PHONE_NUMBER, 1, 3)
            , '011', 'HP'
            , '515', 'EAST'
            , '590', 'WEST'
            , '603', 'SOUTH'
            , '650', 'NORTH') AS RESULT
  FROM
       EMPLOYEES;

-- SALARY      RESULT
-- <10000      C
-- 10000~19999 B
-- >=20000     A
SELECT
       SALARY
     , NVL(DECODE(TRUNC(SALARY / 10000)
            , 0, 'C'
            , 1, 'B'), 'A')
  FROM
       EMPLOYEES;


-- 4. 분기 표현식
/*
    CASE
        WHEN 비교조건 THEN 결과값
        WHEN 비교조건 THEN 결과값
        ELSE 결과값
    END
*/

-- SALARY      RESULT
-- <10000      C
-- 10000~19999 B
-- >=20000     A
SELECT
       SALARY
     , CASE
           WHEN SALARY < 10000 THEN 'C'
           WHEN SALARY < 20000 THEN 'B'
           ELSE 'A'
       END AS 구분
  FROM
       EMPLOYEES;

-- HIRE_DATE를 이용해서 고용개월수를 구하고 다음 기준에 따라 'A', 'B', 'C' 나타내기
-- 고용개월수   RESULT
-- <180         C
-- 180~239      B
-- >=240        A
SELECT
       TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') AS 고용일
     , TRUNC(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) || '개월' AS 근무개월
     , CASE
           WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) < 180 THEN 'C'
           WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) < 240 THEN 'B'
           ELSE 'A'
       END AS 구분
  FROM
       EMPLOYEES;
