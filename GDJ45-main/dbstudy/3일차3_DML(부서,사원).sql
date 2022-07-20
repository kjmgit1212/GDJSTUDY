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

/****************************************************************/

/*
    DML(Data Manipulation Language)
    
    1. 데이터조작어
    2. 행(Row, Record)의 삽입, 수정, 삭제 작업
    3. 작업(트랜잭션) 완료를 위해서 commit이 필요
    4. 작업(트랜잭션) 취소를 위해서 rollback이 필요
    5. 종류
        1) INSERT INTO VALUES : 삽입
        2) UPDATE SET  WHERE  : 수정
        3) DELETE FROM WHERE  : 삭제
*/

-- 1. 삽입
-- 기본키(PK)를 가진 '부모테이블' 먼저 삽입
INSERT INTO DEPARTMENT VALUES(1, '영업부', '대구');
INSERT INTO DEPARTMENT VALUES(2, '인사부', '서울');
INSERT INTO DEPARTMENT VALUES(3, '총무부', '대구');
INSERT INTO DEPARTMENT VALUES(4, '기획부', '서울');

COMMIT;  -- 작업 성공은 COMMIT, 작업 취소는 ROLLBACK;

-- 외래키(FK)를 가진 '자식테이블' 나중에 삽입
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1001, '구창민', 1, '과장', 'M', '95-05-01', 5000000);
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1002, '김민서', 1, '사원', 'M', '17-09-01', 2500000);
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1003, '이은영', 2, '부장', 'F', '90-09-01', 5500000);
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY)
VALUES (1004, '한성일', 2, '과장', 'M', '93-04-01', 5000000);

COMMIT;


-- 2. 삽입(외부 데이터 이용)
--     1) 엑셀 데이터 생성(시트마다 테이블 1개 데이터 배치)
--     2) 테이블 선택 후 우클릭 - [데이터 임포트]
--     * 참고 : 샘플데이터 대량 생성하는 사이트
--              mockaroo.com

-- 3. 수정

-- DEPARTMENT 테이블

-- 1) 부서번호가 1인 부서의 지역을 '인천'으로 수정하시오.
UPDATE DEPARTMENT
   SET LOCATION = '인천'
 WHERE DEPT_NO = 1;

-- 2) 부서번호가 3인 부서의 이름은 '전략부', 지역을 '부산'으로 수정하시오.
UPDATE DEPARTMENT
   SET DEPT_NAME = '전략부', LOCATION = '부산'
 WHERE DEPT_NO = 3;

-- 3) 부서번호가 2인 부서의 부서번호를 5로 수정하시오.
--    DEPARTMENT의 부서번호를 참조 중인 EMPLOYEE의 칼럼이 존재(외래키가 존재)하므로 수정이 안됨.
--    해결책)
--    (1) 외래키 일시중지 (DISABLE)
--    (2) 데이터 수정     (UPDATE)
--    (3) 외래키 재시작   (ENABLE)

ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT EMPLOYEE_DEPARTMENT_FK;  -- 외래키 중지

UPDATE DEPARTMENT
   SET DEPT_NO = 5
 WHERE DEPT_NO = 2;

UPDATE EMPLOYEE
   SET DEPART = 5
 WHERE DEPART = 2;
 
ALTER TABLE EMPLOYEE
    ENABLE CONSTRAINT EMPLOYEE_DEPARTMENT_FK;  -- 외래키 재시작

-- EMPLOYEE 테이블

-- 4) 부서번호가 1인 사원들의 기본급을 100000 인상하시오.
UPDATE EMPLOYEE
   SET SALARY = SALARY + 100000
 WHERE DEPART = 1;

-- 5) 직급이 '부장'인 사원들의 기본급을 10% 인상하시오.
UPDATE EMPLOYEE
   SET SALARY = SALARY * 1.1
 WHERE POSITION = '부장';


-- 4. 삭제

-- 1) 부서번호가 3인 부서를 삭제하시오. (성공)
--    EMPLOYEE 테이블에는 부서번호가 3인 행(ROW, RECORD)이 없기 때문에
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO = 3;

-- 2) 부서번호가 1인 부서를 삭제하시오. (실패)
--    EMPLOYEE 테이블에는 DEPARTMENT 테이블의 부서번호 1을 참조하고 있는 행(ROW, RECORD)이 있기 때문에
/*
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT EMPLOYEE_DEPARTMENT_FK FOREIGN KEY(DEPART) 
        REFERENCES DEPARTMENT(DEPT_NO);
*/
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO = 1;

-- 3) 부서번호가 1인 부서를 삭제하시오. (DEPARTMENT, EMPLOYEE 모두 삭제)
--    DEPARTMENT 테이블의 부서번호 1인 행을 삭제하고, 참조 중인 EMPLOYEE 테이블의 행도 함께 삭제
/*
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT EMPLOYEE_DEPARTMENT_FK FOREIGN KEY(DEPART) 
        REFERENCES DEPARTMENT(DEPT_NO)
            ON DELETE CASCADE;
*/