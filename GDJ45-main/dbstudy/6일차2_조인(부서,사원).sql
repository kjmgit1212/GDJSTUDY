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
    조인(JOIN)
    
    1. 다중 테이블을 조회하는 방법이다.
    2. 조회하거나 조건으로 사용할 칼럼이 서로 다른 테이블에 존재하는 경우에 조인을 사용한다.
    3. 종류
        1) 크로스 조인 : 카테젼 곱(각 테이블의 모든 행(row) 정보를 조합)
        2) 내부 조인 : INNER JOIN, 각 테이블에 모두 존재하는 데이터를 대상으로 조인
        3) 외부 조인 : OUTER JOIN, 한 테이블은 모든 데이터를 대상으로 하고 다른 테이블은 존재하는 데이터를 대상으로 조인
    4. 형식
       1) 콤마(,) 표기법
           SELECT 조회할칼럼
             FROM 테이블1, 테이블2
            WHERE 테이블1.칼럼 = 테이블2.칼럼;  -- 조인조건
       2) JOIN 문법
           SELECT 조회할칼럼
             FROM 테이블1 JOIN 테이블2
               ON 테이블1.칼럼 = 테이블2.칼럼;  -- 조인조건
*/


-- 1. 크로스 조인 확인
SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NO, D.DEPT_NAME, D.LOCATION
  FROM DEPARTMENT D CROSS JOIN EMPLOYEE E;

SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NO, D.DEPT_NAME, D.LOCATION
  FROM DEPARTMENT D, EMPLOYEE E;


-- 2. 사원번호(EMP_NO), 사원명(NAME), 부서명(DEPT_NAME)을 조회하시오.
--    테이블이 2개 사용되어야 하므로 '조인'이다.
--       EMPLOYEE   : EMP_NO, NAME
--       DEPARTMENT : DEPT_NAME
--       조인조건   : DEPARTMENT의 DEPT_NO와 EMPLOYEE의 DEPART 칼럼값이 일치하는 데이터

-- 1) 조인 문법
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART;


-- 2) 콤마 표기법
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D, EMPLOYEE E
 WHERE D.DEPT_NO = E.DEPART;


-- 3. '대구'지역에서 근무하는 사원들의 사원번호(EMP_NO)와 사원명(NAME)을 조회하시오.
--    조건인 '대구'는 DEPARTMENT테이블에서 확인, 조회할 칼럼인 사원번호와 사원명은 EMPLOYEE테이블에서 확인

-- 1) 조인 문법
SELECT E.EMP_NO, E.NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART  -- 조인조건
 WHERE D.LOCATION = '대구';  -- 일반조건

-- 2) 콤마 표기법
SELECT E.EMP_NO, E.NAME
  FROM DEPARTMENT D, EMPLOYEE E
 WHERE D.DEPT_NO = E.DEPART  -- 조인조건
   AND D.LOCATION = '대구';  -- 일반조건



-- 아래 데이터를 수정 / 추가한다.
-- 참조무결성이 위배된 데이터이기 때문에 외래키 제약조건을 잠시 중지하고 추가한다.

ALTER TABLE EMPLOYEE DISABLE CONSTRAINT EMPLOYEE_DEPARTMENT_FK;

UPDATE EMPLOYEE SET DEPART = NULL WHERE EMP_NO = 1002;

INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1005, '김미나', 5, '사원', 'F', '18-05-01', 1800000);

COMMIT;


-- 4. 사원번호(EMP_NO), 사원명(NAME), 부서명(DEPT_NAME)을 조회하시오.
--    배정 받은 부서가 없는 경우 NULL로 조회되도록 하시오.

-- 풀이)
-- 배정 받은 부서가 없으면 조회되지 않는 INNER JOIN
-- 1001  구창민  영업부
-- 1003  이은영  인사부
-- 1004  한성일  인사부
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART;
    
-- 따라서 이 문제는 내부 조인으로 풀 수 없다.


-- 배정 받은 부서가 없어도 사원테이블에 존재하는 데이터는 모두 조회할 수 있는 OUTER JOIN
-- 1001  구창민  영업부
-- 1002  김민서  NULL
-- 1003  이은영  인사부
-- 1004  한성일  인사부
-- 1005  김미나  NULL

-- * 사원테이블에 존재하는 데이터는 모두 조회 : 사원테이블이 왼쪽인지 오른쪽인지 판단
--   사원테이블이 왼쪽에 있으면   '왼쪽 외부 조인'   : LEFT OUTER JOIN
--   사원테이블이 오른쪽에 있으면 '오른쪽 외부 조인' : RIGHT OUTER JOIN

-- 1) 조인 문법
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D RIGHT OUTER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART;


-- 2) 콤마 표기법
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D, EMPLOYEE E
 WHERE D.DEPT_NO(+) = E.DEPART;  -- 왼쪽 조인은 오른쪽에 (+)표시, 오른쪽 조인은 왼쪽에 (+)표시



-- 5. 부서번호별로 소속된 사원수를 조회하시오.
--    소속된 사원이 없는 경우 사원수를 0으로 조회하시오.
--    부서번호    사원수
--    1            1
--    2            2
--    3            0
--    4            0

-- 풀이)
-- 부서번호 3,4는 일치하는 사원 정보가 없지만 조회되도록 왼쪽 외부 조인으로 풀이

SELECT D.DEPT_NO AS 부서번호, COUNT(E.DEPART) AS 사원수
  FROM DEPARTMENT D LEFT OUTER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 GROUP BY D.DEPT_NO
 ORDER BY D.DEPT_NO;
