/*
    뷰(VIEW)
    1. 테이블이나 다른 뷰를 이용해 만드는 가상 테이블이다.
    2. 데이터가 디스크에 저장되지 않고, 쿼리문만 저장해 둔다.
    3. 복잡한 쿼리를 뷰로 만들어 두고 사용하면 간단하게 쿼리문을 작성할 수 있다.
    4. 뷰를 사용하므로써 얻어지는 성능상 이점은 없다.
    5. 보여 주고 싶은 데이터만 뷰로 만들어 보안상 이점을 얻을 수 있다.
*/


-- 1. 'admin'이 작성한 게시글을 이용해서 'ADMIN_BOARDS'이름을 가진 VIEW를 생성하시오.

CREATE VIEW ADMIN_BOARDS
    AS (SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE
          FROM BOARDS
         WHERE MEMBER_ID = 'admin');


-- 2. 생성된 ADMIN_BOARDS를 조회하시오.

SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE
  FROM ADMIN_BOARDS;


-- 3. BOARD_NO, TITLE, CONTENT, NAME, CREATE_DATE를 이용해서 'VIEW_BOARDS'이름을 가진 VIEW를 생성하시오.

CREATE VIEW VIEW_BOARDS
    AS (SELECT B.BOARD_NO, B.TITLE, B.CONTENT, M.NAME, B.CREATE_DATE
          FROM MEMBERS M INNER JOIN BOARDS B
            ON M.MEMBER_ID = B.MEMBER_ID);


-- 4. 생성된 VIEW_BOARDS를 조회하시오.

SELECT BOARD_NO, TITLE, CONTENT, NAME, CREATE_DATE
  FROM VIEW_BOARDS;


-- 5. 생성된 VIEW의 목록을 조회하시오.
DESC USER_VIEWS;
SELECT
       VIEW_NAME  -- 뷰이름
     , TEXT       -- 뷰로저장해둔쿼리문
  FROM
       USER_VIEWS;


-- 6. 생성된 모든 VIEW를 삭제하시오.
DROP VIEW ADMIN_BOARDS;
DROP VIEW VIEW_BOARDS;
