-- --------------------------SELLER_MEMBER-----------------------------
CREATE TABLE SELLER_MEMBER
(
    SELLER_ID          VARCHAR2(100)    NOT NULL, 
    SELLER_PW          VARCHAR2(500)    NOT NULL, 
    SELLER_NAME        VARCHAR2(100)    NOT NULL, 
    SELLER_EMAIL       VARCHAR2(500)    NOT NULL, 
    SELLER_PHONE       VARCHAR2(200)    NOT NULL, 
    SELLER_CODE        VARCHAR2(100)    NOT NULL, 
    CODE_NAME          VARCHAR2(100)    NOT NULL, 
    SELLER_LOCATION    VARCHAR2(100)    NOT NULL, 
    SELLER_BLOGADDR    VARCHAR2(200)    NOT NULL, 
    SELLER_GRADE       VARCHAR2(20)     default '새싹' NOT NULL, 
    SELLER_DATE        DATE             default sysdate NOT NULL, 
    CONSTRAINT SELLER_MEMBER_PK PRIMARY KEY (SELLER_ID)
);

------------------------------------------------------------------------

-----------------------------REPLY_QUERY-----------------------------
CREATE TABLE REPLY_QUERY
(
    QUERY_CODE       NUMBER            NOT NULL, 
    SELLER_ID        VARCHAR2(100)     NOT NULL, 
    REPLY_TITLE      VARCHAR2(500)     NOT NULL, 
    REPLY_CONTENT    VARCHAR2(2000)    NOT NULL, 
    REPLY_DATE       DATE              default sysdate NOT NULL, 
    QUERY_NUM        NUMBER            NOT NULL, 
    CONSTRAINT REPLY_QUERY_PK PRIMARY KEY (QUERY_CODE)
);

CREATE SEQUENCE REPLY_QUERY_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE REPLY_QUERY
    ADD CONSTRAINT FK_REPLY_QUERY_SELLER_ID_SELLE FOREIGN KEY (SELLER_ID)
        REFERENCES SELLER_MEMBER (SELLER_ID) on delete cascade;

        
------------------------------------------------------------------------
        
-- --------------------------SELLER_QUERY-----------------------------
CREATE TABLE SELLER_QUERY
(
    QUERY_NUM        NUMBER            NOT NULL, 
    SELLER_ID        VARCHAR2(100)     NOT NULL, 
    QUERY_TYPE       VARCHAR2(200)     NOT NULL, 
    QUERY_TITLE      VARCHAR2(200)     NOT NULL, 
    QUERY_CONTENT    VARCHAR2(2000)    NOT NULL, 
    QUERY_READ       VARCHAR2(20)      NOT NULL, 
    QUERY_DATE       DATE              default sysdate NOT NULL, 
    CONSTRAINT SELLER_QUERY_PK PRIMARY KEY (QUERY_NUM)
);

CREATE SEQUENCE SELLER_QUERY_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE REPLY_QUERY
    ADD CONSTRAINT FK_REPLY_QUERY_QUERY_NUM_SELLE FOREIGN KEY (QUERY_NUM)
        REFERENCES SELLER_QUERY (QUERY_NUM) on delete cascade;

------------------------------------------------------------------------        
        
 -- --------------------------USER_MEMBER-----------------------------
CREATE TABLE USER_MEMBER
(
    MEMBER_ID       VARCHAR2(100)    NOT NULL, 
    MEMBER_PW       VARCHAR2(500)    NOT NULL, 
    MEMBER_NAME     VARCHAR2(100)    NOT NULL, 
    MEMBER_EMAIL    VARCHAR2(500)    NOT NULL, 
    MEMBER_PHONE    VARCHAR2(200)    NOT NULL, 
    MEMBER_ADDR     VARCHAR2(500)    NOT NULL, 
    MEMBER_DATE     DATE             default sysdate NOT NULL, 
    CONSTRAINT USER_MEMBER_PK PRIMARY KEY (MEMBER_ID)
);

------------------------------------------------------------------------       
        
-- --------------------------REPLY_TRUCK-----------------------------
CREATE TABLE REPLY_TRUCK
(
    REPLY_NUM        NUMBER           NOT NULL, 
    MEMBER_ID        VARCHAR2(100)    NOT NULL, 
    TRUCK_NUM        NUMBER           NOT NULL, 
    REPLY_CONTENT    VARCHAR2(500)    NOT NULL, 
    SCORE            VARCHAR2(50)     NOT NULL, 
    REPLY_DATE       DATE             default sysdate NOT NULL, 
    REPLY_READ       VARCHAR2(20)     NOT NULL, 
    CONSTRAINT REPLY_TRUCK_PK PRIMARY KEY (REPLY_NUM)
);

CREATE SEQUENCE REPLY_TRUCK_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE REPLY_TRUCK
    ADD CONSTRAINT FK_REPLY_TRUCK_MEMBER_ID_USER_ FOREIGN KEY (MEMBER_ID)
        REFERENCES USER_MEMBER (MEMBER_ID) on delete cascade;

------------------------------------------------------------------------        
        
-- --------------------------FOODTRUCK-----------------------------
CREATE TABLE FOODTRUCK
(
    TRUCK_NUM            NUMBER            NOT NULL, 
    SELLER_ID            VARCHAR2(100)     NOT NULL, 
    business_name        VARCHAR2(200)     NOT NULL, 
    TRUCK_NAME           VARCHAR2(200)     NOT NULL, 
    TRUCK_TYPE           VARCHAR2(200)     NOT NULL, 
    TRUCK_STIME          VARCHAR2(200)     NOT NULL, 
    TRUCK_ETIME          VARCHAR2(200)     NOT NULL, 
    TRUCK_RESERVATION    VARCHAR2(150)     NOT NULL, 
    TRUCK_INFO           VARCHAR2(2000)    NOT NULL, 
    TRUCK_IMAGE          VARCHAR2(400)     NOT NULL, 
    TRUCK_LIKE           NUMBER            default 0 NOT NULL, 
    ADDREQ               VARCHAR2(10)      default 'Y' NOT NULL, 
    TRUCK_VISIT          NUMBER            default 0 NOT NULL, 
    TRUCK_DATE           DATE              default sysdate NOT NULL, 
    TRUCK_LOCATION		 VARCHAR2(1000)	   not null,
    CONSTRAINT FOODTRUCK_PK PRIMARY KEY (TRUCK_NUM)
);
-- 푸드트럭 테이블 생성 후에 해야함.
ALTER TABLE REPLY_TRUCK
    ADD CONSTRAINT FK_REPLY_TRUCK_TRUCK_NUM_FOODT FOREIGN KEY (TRUCK_NUM)
        REFERENCES FOODTRUCK (TRUCK_NUM);

CREATE SEQUENCE FOODTRUCK_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE FOODTRUCK
    ADD CONSTRAINT FK_FOODTRUCK_SELLER_ID_SELLER_ FOREIGN KEY (SELLER_ID)
        REFERENCES SELLER_MEMBER (SELLER_ID);

------------------------------------------------------------------------        
        
-- --------------------------USER_QUERY-----------------------------
CREATE TABLE USER_QUERY
(
    QUERY_NUM        NUMBER            NOT NULL, 
    QUERY_TYPE       VARCHAR2(100)     NOT NULL, 
    QUERY_TITLE      VARCHAR2(200)     NOT NULL, 
    QUERY_CONTENT    VARCHAR2(2000)    NOT NULL, 
    QUERY_READ       VARCHAR2(20)      NOT NULL, 
    QUERY_DATE       DATE              default sysdate NOT NULL, 
    MEMBER_ID        VARCHAR2(100)     NOT NULL, 
    SELLER_ID        VARCHAR2(100)     NOT NULL, 
    CONSTRAINT USER_QUERY_PK PRIMARY KEY (QUERY_NUM)
);

CREATE SEQUENCE USER_QUERY_SEQ
START WITH 1
INCREMENT BY 1;

------------------------------------------------------------------------
        
-- --------------------------MENU-----------------------------
CREATE TABLE MENU
(
    MENU_CODE        NUMBER           NOT NULL, 
    TRUCK_NUM        NUMBER           NOT NULL, 
    MENU_CATEGORY    VARCHAR2(100)    NOT NULL, 
    MENU_NAME        VARCHAR2(100)    NOT NULL, 
    MENU_PRICE       NUMBER           NOT NULL, 
    INVENTORY        NUMBER           NOT NULL, 
    CONSTRAINT MENU_PK PRIMARY KEY (MENU_CODE)
);

CREATE SEQUENCE MENU_CODE_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE MENU
    ADD CONSTRAINT FK_MENU_TRUCK_NUM_FOODTRUCK_TR FOREIGN KEY (TRUCK_NUM)
        REFERENCES FOODTRUCK (TRUCK_NUM) on delete cascade;
------------------------------------------------------------------------        
        
-- --------------------------MENU_IMAGE-----------------------------
CREATE TABLE MENU_IMAGE
(
    MENU_CODE     NUMBER           NOT NULL, 
    IMAGE_NAME    VARCHAR2(100)    NOT NULL
);

ALTER TABLE MENU_IMAGE
    ADD CONSTRAINT FK_MENU_IMAGE_MENU_CODE_MENU_M FOREIGN KEY (MENU_CODE)
        REFERENCES MENU (MENU_CODE) on delete cascade;

        
------------------------------------------------------------------------    





----- 판매자 테스트 데이터 생성 SQL ------        
        
INSERT INTO SELLER_MEMBER (SELLER_ID, SELLER_PW, SELLER_NAME, SELLER_EMAIL, SELLER_PHONE, 
	SELLER_CODE, CODE_NAME, SELLER_LOCATION, SELLER_BLOGADDR, SELLER_GRADE, SELLER_DATE) 
VALUES ('test41', '1234', '이현지', 'leehyun@daum.net', '01012345678', 
	'1318212345', '이현지', '서울,마포구', 'http://facebook.com', '새싹', sysdate);        
        

INSERT INTO SELLER_MEMBER (SELLER_ID, SELLER_PW, SELLER_NAME, SELLER_EMAIL, SELLER_PHONE, 
	SELLER_CODE, CODE_NAME, SELLER_LOCATION, SELLER_BLOGADDR, SELLER_GRADE, SELLER_DATE) 
VALUES ('test42', '1234', '이지현', 'babybosx@daum.net', '01012345678', 
	'1318212345', '이지현', '서울,강남구', 'http://facebook.com', '새싹', sysdate);

INSERT INTO SELLER_MEMBER (SELLER_ID, SELLER_PW, SELLER_NAME, SELLER_EMAIL, SELLER_PHONE, 
	SELLER_CODE, CODE_NAME, SELLER_LOCATION, SELLER_BLOGADDR, SELLER_GRADE, SELLER_DATE) 
VALUES ('test43', '1234', '임윤아', 'lalalala@daum.net', '01012345678', 
	'1318212345', '임윤아', '서울,강남구', 'http://facebook.com', '새싹', sysdate);
	
INSERT INTO SELLER_MEMBER (SELLER_ID, SELLER_PW, SELLER_NAME, SELLER_EMAIL, SELLER_PHONE, 
	SELLER_CODE, CODE_NAME, SELLER_LOCATION, SELLER_BLOGADDR, SELLER_GRADE, SELLER_DATE) 
VALUES ('test44', '1234', '윤하', 'password486@daum.net', '01012345678', 
	'1318212345', '윤하', '서울,강남구', 'http://facebook.com', '새싹', sysdate);
	
INSERT INTO SELLER_MEMBER (SELLER_ID, SELLER_PW, SELLER_NAME, SELLER_EMAIL, SELLER_PHONE, 
	SELLER_CODE, CODE_NAME, SELLER_LOCATION, SELLER_BLOGADDR, SELLER_GRADE, SELLER_DATE) 
VALUES ('test45', '1234', '오윤아', 'ohyoon@daum.net', '01012345678', 
	'1318212345', '오윤아', '서울,강남구', 'http://facebook.com', '새싹', sysdate);

------------------------------------------------------------------------    

	


        
        
        
        
        
        
        