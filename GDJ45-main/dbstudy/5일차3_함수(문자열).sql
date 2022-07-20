-- 문자 처리 함수


-- 1. 대소문자 처리
--    1) UPPER   : 대문자로 변환 (CAP)
--    2) LOWER   : 소문자로 변환 (cap)
--    3) INITCAP : 첫글자만 대문자 (Cap)
SELECT
       UPPER(EMAIL)
     , LOWER(EMAIL)
     , INITCAP(EMAIL)
  FROM
       EMPLOYEES;

-- 2. 길이
--    1) LENGTH  : 글자수
--    2) LENGTHB : 바이트수
SELECT
       EMAIL
     , LENGTH(EMAIL)
     , LENGTHB(EMAIL)
  FROM
       EMPLOYEES;

-- 3. 연결
--    1) || 연산자
--    2) CONCAT 함수
--       인수가 2개로 고정됨 : CONCAT(A, B) => AB    CONCAT(A, B, C) => 오류
--                                                   CONCAT(A, CONCAT(B, C)) => ABC
SELECT
       '대한' || '독립' || '만세'
     , CONCAT('대한', CONCAT('독립', '만세'))
  FROM
       DUAL;

-- 4. 일부 반환
--    SUBSTR(문자열, BEGIN, LENGTH) : BEGIN부터 LENGTH개 반환, BEGIN의 시작은 1
SELECT
       EMAIL
     , SUBSTR(EMAIL, 1, 3) -- 1번 글자부터 3개 반환
  FROM
       EMPLOYEES;

-- 5. 특정 문자열의 위치 반환
--    INSTR(문자열, 찾을문자열) : 위치 시작은 1부터, 없으면 0
SELECT
       EMAIL
     , INSTR(EMAIL, 'A')
  FROM
       EMPLOYEES;

-- 6. 좌우 문자열 채우기
--    1) LPAD(문자열, 전체폭, 채울문자)
--    2) RPAD(문자열, 전체폭, 채울문자)
SELECT
       LPAD(NVL(DEPARTMENT_ID, 0), 3, '0')
  FROM
       EMPLOYEES;

-- 7. 공백 제거
--    1) LTRIM : 왼쪽 공백 제거
--    2) RTRIM : 오른쪽 공백 제거
--    3) TRIM  : 양쪽 공백 제거
SELECT
       LTRIM('   ORACLE')
     , RTRIM('ORACLE   ')
     , TRIM('   ORACLE   ')
  FROM
       DUAL;