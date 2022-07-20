/*
    DQL(Data Query Language)
    
    1. 데이터질의어
    2. 특정 테이블의 칼럼을 조회하거나 값을 조회한다.
    3. DB의 변경이 없다.
    4. 형식
        SELECT 칼럼 
          FROM 테이블
        [WHERE 조건]
        [GROUP BY 그룹화]
        [ORDER BY 정렬]
*/


-- 1. 사원(EMPLOYEE) 테이블에서 사원명(NAME)을 조회하시오.
SELECT NAME
  FROM EMPLOYEE;

-- 칼럼 앞에 오너(OWNER)를 명시할 수 있다. - 오너 : 칼럼이 속한 테이블
SELECT EMPLOYEE.NAME
  FROM EMPLOYEE;
  
-- 테이블에 별명(ALIAS)을 주고, 이를 오너로 명시할 수 있다.
SELECT E.NAME
  FROM EMPLOYEE E;  -- EMPLOYEE 테이블의 별명은 E이다.

-- 조회할 칼럼에 별명(ALIAS)을 줄 수 있다.
SELECT NAME AS 사원명  -- NAME칼럼의 별명은 사원명이다.
  FROM EMPLOYEE;


-- 2. 사원테이블의 모든 칼럼을 조회하시오.
-- * : 모든 칼럼을 의미한다.
-- 중요 : 실무에서는 *를 사용하지 않는다. (성능 이슈)
SELECT *
  FROM EMPLOYEE;

-- 모든 칼럼이 필요하면 모두 적어라.
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE;


-- 3. 부서(DEPARTMENT)테이블의 지역명(LOCATION) 칼럼을 조회하시오.
SELECT LOCATION
  FROM DEPARTMENT;

-- DISTINCT : 중복을 제거한다.
SELECT DISTINCT LOCATION
  FROM DEPARTMENT;
  

-- 4. 사원테이블에서 직급(POSITION)이 '과장'인 사원의 이름(NAME)을 조회하시오.
SELECT NAME
  FROM EMPLOYEE
 WHERE POSITION = '과장';


-- 5. 사원테이블에서 급여(SALARY)가 5000000 이상인 사원의 이름(NAME)과 직급(POSITION)을 조회하시오.
SELECT NAME AS 사원명, POSITION AS 직급
  FROM EMPLOYEE
 WHERE SALARY >= 5000000;


-- 6. 사원테이블에서 급여(SALARY)가 2000000~4000000 사이인 사원의 이름(NAME)과 급여(SALARY)를 조회하시오.
SELECT NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY BETWEEN 2000000 AND 4000000;  -- 추천

SELECT NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 2000000 AND SALARY <= 4000000;
 
 
-- 7. 사원테이블에서 고용일(HIRE_DATE)이 '90/01/01'~'99/12/31' 사이인 사원의 이름(NAME)과 고용일(HIRE_DATE)을 조회하시오.
SELECT NAME, HIRE_DATE
  FROM EMPLOYEE
 WHERE HIRE_DATE BETWEEN '90/01/01' AND '99/12/31';

SELECT NAME, HIRE_DATE
  FROM EMPLOYEE
 WHERE TO_DATE(HIRE_DATE, 'yy/mm/dd') BETWEEN TO_DATE('90/01/01', 'yy/mm/dd') AND TO_DATE('99/12/31', 'yy/mm/dd');


-- 8. 사원테이블에서 직급(POSITION)이 '과장', '부장'인 사원의 이름(NAME)과 부서번호(DEPART)를 조회하시오.
SELECT NAME, DEPART
  FROM EMPLOYEE
 WHERE POSITION IN('과장', '부장');  -- 추천

SELECT NAME, DEPART
  FROM EMPLOYEE
 WHERE POSITION = '과장' OR POSITION = '부장';


-- 9. 부서테이블에서 지역(LOCATION)이 '대구'인 지역의 부서명(DEPT_NAME)을 조회하시오.
SELECT DEPT_NAME
  FROM DEPARTMENT
 WHERE LOCATION IN('대구');
 
SELECT DEPT_NAME
  FROM DEPARTMENT
 WHERE LOCATION = '대구';


-- 10. 사원테이블에서 성별(GENDER)이 NULL이 아닌 사원의 이름(NAME)을 조회하시오.
SELECT NAME
  FROM EMPLOYEE
 WHERE GENDER IS NOT NULL;


-- 11. 부서테이블에서 부서명(DEPT_NAME)에 '무'가 포함된 부서의 모든 칼럼을 조회하시오.
-- 만능문자 : %, _
-- 문자 데이터를 대상으로 만능문자를 사용한다.
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE DEPT_NAME LIKE '%무%';


-- 12. 사원테이블에서 사원명(NAME)이 '김'으로 시작하지 않는 사원의 사원명(NAME)을 조회하시오.
SELECT NAME
  FROM EMPLOYEE
 WHERE NAME NOT LIKE '김%';


-- 13. 사원테이블에서 급여(SALARY)가 높은 사원부터 낮은 사원순(내림차순)으로 사원명(NAME)과 급여(SALARY)를 조회하시오.
SELECT NAME, SALARY
  FROM EMPLOYEE
 ORDER BY SALARY DESC;


-- 14. 사원테이블의 모든 칼럼을 사원명(NAME)의 가나다순(오름차순)으로 조회하시오.
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY NAME;  -- ASC은 생략 가능하다.


-- 15. 사원테이블의 모든 칼럼을 부서(DEPART)별로 오름차순 정렬하되, 같은 부서내에서는 고용일(HIRE_DATE)의 오름차순으로 정렬하여 조회하시오.
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY DEPART, HIRE_DATE;

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY DEPART ASC, HIRE_DATE ASC;