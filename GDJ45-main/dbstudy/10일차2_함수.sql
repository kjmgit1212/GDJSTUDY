/*
    함수(FUNCTION)
    
    1. 특정 결과 값을 반환한다.
    2. 값을 반환할 때 RETURN을 사용한다.
    3. 입력 파라미터를 사용할 수 있다.
    4. 함수 호출 결과를 처리할 수 있도록 호출해야 한다.
    5. 형식
        CREATE [OR REPLACE] FUNCTION 함수명(매개변수)
        RETURN 반환타입
        IS  -- AS도 가능
            변수선언
        BEGIN
            작업수행
        [EXCEPTION]
            예외처리
        END 함수명;
*/




-- 함수 FUNC1 정의
CREATE OR REPLACE FUNCTION FUNC1
RETURN VARCHAR2  -- 반환타입
IS
BEGIN
    RETURN 'Hello Function';  -- 반환값
END FUNC1;

-- 함수 FUNC1 호출
SELECT FUNC1()
  FROM DUAL;




-- 함수 FUNC2 정의
CREATE OR REPLACE FUNCTION FUNC2
(
    var_salary NUMBER
)
RETURN VARCHAR2
IS
    var_result VARCHAR2(10 BYTE);
BEGIN
    IF var_salary >= 18000 THEN
        var_result := '고연봉';
    ELSIF var_salary >= 10000 THEN
        var_result := '중연봉';
    ELSE
        var_result := '저연봉';
    END IF;
    RETURN var_result;
END FUNC2;

-- 함수 FUNC2 호출
SELECT EMPLOYEE_ID, FIRST_NAME, SALARY, FUNC2(SALARY)
  FROM EMPLOYEES;




-- 함수 FUNC3 정의
CREATE OR REPLACE FUNCTION FUNC3
(
    var_start NUMBER,  -- 시작
    var_end   NUMBER,  -- 종료
    var_mod   NUMBER   -- 나머지
)
RETURN NUMBER
IS
    n NUMBER;
    total NUMBER;
BEGIN
    total := 0;
    FOR n IN var_start..var_end LOOP
        IF MOD(n, 2) = var_mod THEN
            total := total + n;
        END IF;
    END LOOP;
    RETURN total;
END FUNC3;

-- 함수 FUNC3 호출
SELECT FUNC3(1, 100, 0)  -- 1부터 100까지 모든 짝수(0)의 합계를 구한다.(2550)
  FROM DUAL;

SELECT FUNC3(1, 100, 1)  -- 1부터 100까지 모든 홀수(1)의 합계를 구한다.(2500)
  FROM DUAL;