-- 테이블 삭제
DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

-- DEPARTMENT 테이블 생성
CREATE TABLE DEPARTMENT(
    DEPT_NO   NUMBER NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION  VARCHAR2(15 BYTE) NOT NULL
);

-- 기본키
ALTER TABLE DEPARTMENT ADD CONSTRAINT DEPARTMENT_PK PRIMARY KEY(DEPT_NO);

-- EMPLOYEE 테이블 생성
CREATE TABLE EMPLOYEE(
    EMP_NO    NUMBER NOT NULL,
    NAME      VARCHAR2(20 BYTE) NOT NULL,
    DEPART    NUMBER,
    POSITION  VARCHAR2(20 BYTE),
    GENDER    CHAR(2),
    HIRE_DATE DATE,
    SALARY    NUMBER
);

-- 기본키
ALTER TABLE EMPLOYEE ADD CONSTRAINT EMPLOYEE_PK PRIMARY KEY(EMP_NO);

-- 외래키
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT EMPLOYEE_DEPARTMENT_FK FOREIGN KEY(DEPART) 
        REFERENCES DEPARTMENT(DEPT_NO);

-- 데이터 삽입
INSERT INTO DEPARTMENT VALUES(1, '영업부', '대구');
INSERT INTO DEPARTMENT VALUES(2, '인사부', '서울');
INSERT INTO DEPARTMENT VALUES(3, '총무부', '대구');
INSERT INTO DEPARTMENT VALUES(4, '기획부', '서울');

INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1001, '구창민', 1, '과장', 'M', '95-05-01', 5000000);
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1002, '김민서', 1, '사원', 'M', '17-09-01', 2500000);
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1003, '이은영', 2, '부장', 'F', '90-09-01', 5500000);
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1004, '한성일', 2, '과장', 'M', '93-04-01', 5000000);

COMMIT;


/*
    서브쿼리(sub query)
    
    1. 메인쿼리에 포함되는 하위쿼리이다.
    2. 서브쿼리는 괄호()를 이용해서 묶어준다.
    3. SELECT절에서 사용되면 '스칼라 서브쿼리', FROM절에서 사용되면 '인라인뷰', WHERE절에서 사용되면 '서브쿼리'라고 부른다.
    4. 종류
        1) 단일 행(SINGLE ROW) 서브쿼리 : 서브쿼리 결과가 1개, 단일 행 연산(=,!=,>,<,>=,<=) 사용
        2) 다중 행(MULTI ROW) 서브쿼리  : 서브쿼리 결과가 여러개, 다중 행 연산(IN, ANY, ALL 등) 사용
*/


/* WHERE절의 서브쿼리 */

-- 1. 사원번호(EMP_NO)가 1001인 사원과 같은 직급(POSITION)을 가진 사원을 조회하시오.
--    서브쿼리 : 사원번호가 1001인 사원의 직급(단일 행 : 사원번호(EMP_NO)칼럼이 PK이므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 직급 = (사원번호가 1001인 사원의 직급)

SELECT EMP_NO, NAME, POSITION
  FROM EMPLOYEE
 WHERE POSITION = (SELECT POSITION
                     FROM EMPLOYEE
                    WHERE EMP_NO = 1001);

/*
서브쿼리가 실행되면 아래와 같은 메인쿼리가 완성된다.
SELECT EMP_NO, NAME, POSITION
  FROM EMPLOYEE
 WHERE POSITION = ('과장');
*/


-- 2. 급여(SALARY)가 가장 높은 사원을 조회하시오.
--    서브쿼리 : 가장 높은 급여(단일 행 : 집계함수의 결과를 사용하므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 급여 = (가장 높은 급여)

SELECT EMP_NO, NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY)
                   FROM EMPLOYEE);


-- 3. 부서번호(DEPT_NO)가 1인 부서와 같은 지역에 있는 부서를 조회하시오.
--    서브쿼리 : 부서번호(DEPT_NO)가 1인 부서의 지역(단일 행 : 부서번호(DEPT_NO)칼럼이 PK이므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM DEPARTMENT WHERE 지역 = (부서번호가 1인 부서의 지역)

SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE LOCATION = (SELECT LOCATION
                     FROM DEPARTMENT
                    WHERE DEPT_NO = 1);



-- 4. 전체 사원의 평균급여 이상을 받는 사원을 조회하시오.
--    서브쿼리 : 평균급여(단일 행 : 집계함수의 결과를 사용하므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 급여 >= (평균급여)

SELECT EMP_NO, NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT AVG(SALARY)
                    FROM EMPLOYEE);


-- 5. 전체 사원의 평균근속개월 이상을 근무한 사원을 조회하시오.
--    서브쿼리 : 평균근속개월(단일 행 : 집계함수의 결과를 사용하므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 근속개월 >= (평균근속개월)

-- MONTHS_BETWEEN(최근날짜, 이전날짜) : 두 날짜 사이에 경과한 개월수 반환

SELECT EMP_NO, NAME, HIRE_DATE
  FROM EMPLOYEE
 WHERE MONTHS_BETWEEN(SYSDATE, HIRE_DATE) >= (SELECT AVG(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
                                                FROM EMPLOYEE);



-- 6. 부서번호(DEPART)가 2인 부서에 근무하는 사원들의 직급과 일치하는 직급을 가진 사원을 조회하시오.
--    서브쿼리 : 부서번호(DEPART)가 2인 부서에 근무하는 사원들의 직급(다중 행 : 부서번호(DEPART)칼럼은 PK도 아니고 UNIQUE도 아니므로 서브쿼리 결과가 여러개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 직급 IN(부서번호가 2인 부서에 근무하는 사원들의 직급)

SELECT EMP_NO, NAME, POSITION
  FROM EMPLOYEE
 WHERE POSITION IN(SELECT POSITION
                     FROM EMPLOYEE
                    WHERE DEPART = 2);


-- 7. 부서명(DEPT_NAME)이 '영업부'인 부서에 근무하는 사원을 조회하시오.
--    서브쿼리 : 부서명(DEPT_NAME)이 '영업부'인 부서번호(다중 행 : 부서명(DEPT_NAME)칼럼은 PK도 아니고 UNIQUE도 아니므로 서브쿼리 결과가 여러개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 부서번호 IN(부서명이 '영업부'인 부서번호)

SELECT EMP_NO, NAME, DEPART
  FROM EMPLOYEE
 WHERE DEPART IN(SELECT DEPT_NO
                   FROM DEPARTMENT
                  WHERE DEPT_NAME = '영업부');


-- 8. 직급(POSITION)이 '과장'인 사원들이 근무하는 부서를 조회하시오.
--    서브쿼리 : 직급(POSITION)이 '과장'인 사원들의 부서번호
--    메인쿼리 : SELECT 칼럼 FROM 부서 WHERE 부서번호 IN(직급이 '과장'인 사원들의 부서번호)

SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE DEPT_NO IN(SELECT DEPART
                    FROM EMPLOYEE
                   WHERE POSITION = '과장');


-- 9. 부서번호(DEPART)가 1인 부서에 근무하는 어떤 사원의 급여(SALARY)보다 더 많은 급여를 받는 사원을 조회하시오.
--    부서번호(DEPART)가 1인 부서에 근무하는 사원들의 급여 중 어떤 급여이든 많이 받으면 조회하시오.

-- ANY : 제시된 조건 중 하나만 만족하면 가능한 경우

-- 서브쿼리 : 부서번호(DEPART)가 1인 부서에 근무하는 사원들의 급여(SALARY)
-- 메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 급여 > ANY(부서번호가 1인 부서에 근무하는 사원들의 급여)

SELECT EMP_NO, NAME, DEPART, SALARY
  FROM EMPLOYEE
 WHERE SALARY > ANY(SELECT SALARY
                      FROM EMPLOYEE
                     WHERE DEPART = 1);

-- ANY 대신 집계함수 사용
-- 메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 급여 > (부서번호가 1인 부서에 근무하는 사원들의 최소급여)

SELECT EMP_NO, NAME, DEPART, SALARY
  FROM EMPLOYEE
 WHERE SALARY > (SELECT MIN(SALARY)
                   FROM EMPLOYEE
                  WHERE DEPART = 1);



-- 10. 부서번호(DEPART)가 1인 부서에 근무하는 모든 사원의 급여(SALARY)보다 더 많은 급여를 받는 사원을 조회하시오.
--    부서번호(DEPART)가 1인 부서에 근무하는 사원들의 모든 급여와 비교해서 더 많이 받으면 조회하시오.

-- ALL : 제시된 모든 조건을 만족하는 경우

-- 서브쿼리 : 부서번호(DEPART)가 1인 부서에 근무하는 사원들의 급여(SALARY)
-- 메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 급여 > ALL(부서번호가 1인 부서에 근무하는 사원들의 급여)

SELECT EMP_NO, NAME, DEPART, SALARY
  FROM EMPLOYEE
 WHERE SALARY > ALL(SELECT SALARY
                      FROM EMPLOYEE
                     WHERE DEPART = 1);


-- ALL 대신 집계함수 사용
-- 메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 급여 > (부서번호가 1인 부서에 근무하는 사원들의 최대급여)

SELECT EMP_NO, NAME, DEPART, SALARY
  FROM EMPLOYEE
 WHERE SALARY > (SELECT MAX(SALARY)
                   FROM EMPLOYEE
                  WHERE DEPART = 1);
