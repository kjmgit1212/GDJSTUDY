/*
    모델링

    1. [보기] - [Data Modeler] - [브라우저]
    2. [브라우저] 창 - [제목 없음_1] - [관계형 모델 []] - 우클릭 - [새 관계형 모델]
    3. 상단 메뉴 [새 테이블] 클릭 후 [Relational_1] 창에 그리기(클릭 or 드래그)
        1) 일반 : 테이블 이름, DDL에 생성 체크
        2) 열   : 열 이름, 논리적, 타입, 옵션(PK, 필수(NOT NULL))
        3) 고유 제약 조건 : UNIQUE
    4. 상단 메뉴 [새 외래키] 클릭 후 PK 클릭 후 FK 클릭(클릭 순서 지킬 것)
        1) 필수 선택하면 실선으로 관계 생성, 필수 해제하면 점선으로 관계 생성
        2) 연관된 열
            참조된 열  |    열
             PK 등록   |  FK 등록
    5. 상단 메뉴 [DDL 생성] 클릭 후 [생성] 버튼 클릭하면 자동으로 생성되는 DDL 확인 가능
*/


/*
    DDL(Data Definition Language)
    1. 데이터정의어
    2. 데이터베이스 객체를 추가/수정/삭제하는 쿼리문
    3. 작업 수행 후 취소가 불가능
    4. 종류
        1) 생성 : CREATE
        2) 변경 : ALTER
        3) 삭제 : DROP
*/


-- 테이블 삭제
DROP TABLE enroll;
DROP TABLE student;
DROP TABLE subject;

-- 외래키 제약조건을 무시하고 테이블 삭제
DROP TABLE student CASCADE CONSTRAINTS;
DROP TABLE subject CASCADE CONSTRAINTS;
DROP TABLE enroll CASCADE CONSTRAINTS;


-- 테이블 생성
CREATE TABLE enroll (
    enroll_no     NUMBER NOT NULL,
    std_no        NUMBER NOT NULL,
    subject_code  VARCHAR2(1 BYTE) NOT NULL
);

CREATE TABLE student (
    std_no    NUMBER NOT NULL,
    std_name  VARCHAR2(15 BYTE),
    std_age   NUMBER
);

CREATE TABLE subject (
    subject_code  VARCHAR2(1 BYTE) NOT NULL,
    subject_name  VARCHAR2(10 BYTE),
    professor     VARCHAR2(15 BYTE)
);


-- 테이블 수정
-- PK 등록
ALTER TABLE enroll ADD CONSTRAINT enroll_pk PRIMARY KEY ( enroll_no );
ALTER TABLE student ADD CONSTRAINT student_pk PRIMARY KEY ( std_no );
ALTER TABLE subject ADD CONSTRAINT subject_pk PRIMARY KEY ( subject_code );
-- FK 등록
ALTER TABLE enroll
    ADD CONSTRAINT enroll_student_fk FOREIGN KEY ( std_no )
        REFERENCES student ( std_no );
ALTER TABLE enroll
    ADD CONSTRAINT enroll_subject_fk FOREIGN KEY ( subject_code )
        REFERENCES subject ( subject_code );