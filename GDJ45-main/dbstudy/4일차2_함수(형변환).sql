/*
    DUAL 테이블
    
    1. DUMMY 칼럼에 'X'값을 하나 가지고 있는 테이블이다.
    2. 테이블 자체는 아무 의미가 없다.
    3. 오라클의 SELECT문은 FROM절이 필수이기 때문에, 테이블이 필요없는 단순 조회도 FROM절을 작성해야 한다.
       이 때 DUAL테이블을 FROM절에 작성한다.
*/


-- 1. 형 변환 함수

-- 1) 숫자로 변환하기
--    TO_NUMBER('문자열')

SELECT '100', TO_NUMBER('100')
  FROM DUAL;

SELECT '1.5', TO_NUMBER('1.5')
  FROM DUAL;

-- '문자열' 연산 특징
-- 숫자형 문자열은 자동으로 숫자로 변환된다.
SELECT 1 + 1
  FROM DUAL;

SELECT 1 + '1'  -- SELECT 1 + TO_NUMBER('1')
  FROM DUAL;

SELECT '1' + '1'  -- SELECT TO_NUMBER('1') + TO_NUMBER('1')
  FROM DUAL;



-- 2) 날짜로 변환하기
--    TO_DATE('문자열', [형식])
--    지정된 형식으로 문자열을 해석해서 날짜로 변환
--    형식을 생략하면 기본 날짜 형식으로 해석

-- 현재 날짜와 시간
-- 타입이 DATE인 경우 : SYSDATE
-- 타입이 TIMESTAMP인 경우 : SYSTIMESTAMP

SELECT SYSDATE
  FROM DUAL;

SELECT SYSTIMESTAMP
  FROM DUAL;

SELECT TO_DATE('22/02/24', 'yy/mm/dd')
  FROM DUAL;

SELECT TO_DATE('02/24/22', 'mm/dd/yy')
  FROM DUAL;

-- * 주의
-- 날짜 형식을 변경하는 함수가 아니라,
-- 전달된 문자열을 지정된 날짜 형식으로 해석
-- 날짜의 형식 변경은 TO_CHAR 함수로 처리
SELECT TO_DATE(SYSDATE, 'yyyy-mm-dd')  -- yyyy-mm-dd 형식으로 출력되지 않는다.
  FROM DUAL;


-- 3) 문자로 변환하기
--    TO_CHAR(값, [형식])
--    전달된 값(숫자, 날짜)을 지정한 형식으로 변경함
SELECT 
       TO_CHAR(123)              -- '123'
     , TO_CHAR(123, '999999')    -- '   123'
     , TO_CHAR(123, '000000')    -- '000123'
     , TO_CHAR(1234, '9,999')    -- '1,234'
     , TO_CHAR(12345, '9,999')   -- '######' (값보다 형식의 폭이 적다는 의미)
     , TO_CHAR(12345, '99,999')  -- '12,345'
     , TO_CHAR(3.4, '9')         -- '3' (정수로 반올림)
     , TO_CHAR(3.5, '9')         -- '4' (정수로 반올림)
     , TO_CHAR(3.55, '9.9')      -- '3.6' (소수 1자리로 반올림)
  FROM 
       DUAL;
     
SELECT
       TO_CHAR(SYSDATE)                 -- 22/02/24
     , TO_CHAR(SYSTIMESTAMP)            -- 22/02/24 13:33:08.004000 +09:00
     , TO_CHAR(SYSDATE, 'yyyy-mm-dd')   -- 2022-02-24
     , TO_CHAR(SYSDATE, 'hh:mi:ss')     -- 01:33:08
  FROM
       DUAL;