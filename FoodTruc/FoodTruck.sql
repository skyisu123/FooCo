-- --------------------------SELLER_MEMBER-----------------------------
CREATE TABLE SELLER_MEMBER
(
    SELLER_ID          VARCHAR2(30)     NOT NULL, 
    SELLER_PW          VARCHAR2(30)     NOT NULL, 
    SELLER_NAME        VARCHAR2(20)     NOT NULL, 
    SELLER_EMAIL       VARCHAR2(50)     NOT NULL, 
    SELLER_PHONE       VARCHAR2(15)     NOT NULL, 
    SELLER_CODE        VARCHAR2(30)     NOT NULL, 
    CODE_NAME          VARCHAR2(20)     NOT NULL, 
    SELLER_LOCATION    VARCHAR2(100)    NOT NULL, 
    SELLER_BLOGADDR    VARCHAR2(30)     NOT NULL, 
    SELLER_GRADE       VARCHAR2(20)     default '새싹' NOT NULL, 
    SELLER_DATE        DATE             default sysdate NOT NULL, 
    CONSTRAINT SELLER_MEMBER_PK PRIMARY KEY (SELLER_ID)
);

------------------------------------------------------------------------

-----------------------------REPLY_QUERY-----------------------------
CREATE TABLE REPLY_QUERY
(
    QUERY_CODE       NUMBER            NOT NULL, 
    SELLER_ID        VARCHAR2(30)      NOT NULL, 
    REPLY_TITLE      VARCHAR2(20)      NOT NULL, 
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
        REFERENCES SELLER_MEMBER (SELLER_ID);
        
------------------------------------------------------------------------
        
-- --------------------------SELLER_QUERY-----------------------------
CREATE TABLE SELLER_QUERY
(
    QUERY_NUM        NUMBER            NOT NULL, 
    SELLER_ID        VARCHAR2(30)      NOT NULL, 
    QUERY_TYPE       VARCHAR2(20)      NOT NULL, 
    QUERY_TITLE      VARCHAR2(50)      NOT NULL, 
    QUERY_CONTENT    VARCHAR2(2000)    NOT NULL, 
    QUERY_READ       VARCHAR2(20)      NOT NULL, 
    QUERY_DATE       DATE              default sysdate NOT NULL, 
    CONSTRAINT SELLER_QUERY_PK PRIMARY KEY (QUERY_NUM)
);

CREATE SEQUENCE seller_query_seq
START WITH 1
INCREMENT BY 1;

-- REPLY_QUERY테이블 외래키 설정. 위 SELLER_QUERY가 생성된 후 실행
ALTER TABLE REPLY_QUERY
    ADD CONSTRAINT FK_REPLY_QUERY_QUERY_NUM_SELLE FOREIGN KEY (QUERY_NUM)
        REFERENCES SELLER_QUERY (QUERY_NUM);
--

------------------------------------------------------------------------

-- --------------------------USER_MEMBER-----------------------------
CREATE TABLE USER_MEMBER
(
    MEMBER_ID       VARCHAR2(30)     NOT NULL, 
    MEMBER_PW       VARCHAR2(30)     NOT NULL, 
    MEMBER_NAME     VARCHAR2(20)     NOT NULL, 
    MEMBER_EMAIL    VARCHAR2(50)     NOT NULL, 
    MEMBER_PHONE    VARCHAR2(15)     NOT NULL, 
    MEMBER_ADDR     VARCHAR2(100)    NOT NULL, 
    MEMBER_DATE     DATE             default sysdate NOT NULL, 
    CONSTRAINT USER_MEMBER_PK PRIMARY KEY (MEMBER_ID)
);

------------------------------------------------------------------------


-- --------------------------REPLY_TRUCK-----------------------------
CREATE TABLE REPLY_TRUCK
(
    REPLY_NUM        NUMBER           NOT NULL, 
    MEMBER_ID        VARCHAR2(30)     NOT NULL, 
    TRUCK_NUM        NUMBER           NOT NULL, 
    REPLY_CONTENT    VARCHAR2(500)    NOT NULL, 
    SCORE            varchar2(10)     NOT NULL, 
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
    SELLER_ID            VARCHAR2(30)      NOT NULL, 
    business_name        VARCHAR2(50)      NOT NULL, 
    TRUCK_NAME           VARCHAR2(20)      NOT NULL, 
    TRUCK_TYPE           VARCHAR2(20)      NOT NULL, 
    TRUCK_STIME          VARCHAR2(20)      NOT NULL, 
    TRUCK_ETIME          VARCHAR2(20)      NOT NULL, 
    TRUCK_RESERVATION    VARCHAR2(15)      NOT NULL, 
    TRUCK_INFO           VARCHAR2(2000)    NOT NULL, 
    TRUCK_IMAGE          VARCHAR2(40)      NOT NULL, 
    TRUCK_LIKE           NUMBER            default 0 NOT NULL, 
    ADDREQ               VARCHAR2(5)       default 'Y' NOT NULL, 
    TRUCK_DATE           DATE              default sysdate NOT NULL, 
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
    QUERY_TYPE       VARCHAR2(20)      NOT NULL, 
    QUERY_TITLE      VARCHAR2(50)      NOT NULL, 
    QUERY_CONTENT    VARCHAR2(2000)    NOT NULL, 
    QUERY_READ       VARCHAR2(20)      NOT NULL, 
    QUERY_DATE       DATE              default sysdate NOT NULL, 
    MEMBER_ID        VARCHAR2(30)      NOT NULL, 
    SELLER_ID        VARCHAR2(30)      NOT NULL, 
    CONSTRAINT USER_QUERY_PK PRIMARY KEY (QUERY_NUM)
);

CREATE SEQUENCE user_query_seq
START WITH 1
INCREMENT BY 1;

------------------------------------------------------------------------ 

-- --------------------------MENU-----------------------------
CREATE TABLE MENU
(
    MENU_CODE        NUMBER          NOT NULL, 
    TRUCK_NUM        NUMBER          NOT NULL, 
    MENU_CATEGORY    VARCHAR2(20)    NOT NULL, 
    MENU_NAME        VARCHAR2(20)    NOT NULL, 
    MENU_PRICE       NUMBER          NOT NULL, 
    INVENTORY        NUMBER          NOT NULL, 
    CONSTRAINT MENU_PK PRIMARY KEY (MENU_CODE)
);

CREATE SEQUENCE MENU_CODE_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE MENU
    ADD CONSTRAINT FK_MENU_TRUCK_NUM_FOODTRUCK_TR FOREIGN KEY (TRUCK_NUM)
        REFERENCES FOODTRUCK (TRUCK_NUM) on delete cascade;
------------------------------------------------------------------------    

-- --------------------------LOCATIONS-----------------------------
CREATE TABLE LOCATIONS
(
    FOOD_LOCATION    VARCHAR2(20)    NOT NULL, 
    TRUCK_NUM        NUMBER          NOT NULL, 
    REGION           VARCHAR2(20)    NOT NULL, 
    CONSTRAINT LOCATIONS_PK PRIMARY KEY (FOOD_LOCATION)
);

ALTER TABLE LOCATIONS
    ADD CONSTRAINT FK_LOCATIONS_TRUCK_NUM_FOODTRU FOREIGN KEY (TRUCK_NUM)
        REFERENCES FOODTRUCK (TRUCK_NUM);

------------------------------------------------------------------------  

-- --------------------------MENU_IMAGE-----------------------------
CREATE TABLE MENU_IMAGE
(
    MENU_CODE     NUMBER          NOT NULL, 
    IMAGE_NAME    VARCHAR2(40)    NOT NULL
);

ALTER TABLE MENU_IMAGE
    ADD CONSTRAINT FK_MENU_IMAGE_MENU_CODE_MENU_M FOREIGN KEY (MENU_CODE)
        REFERENCES MENU (MENU_CODE) on delete cascade;

drop table menu_image;
------------------------------------------------------------------------  


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        
-- SELLER_MEMBER 테스트데이터
INSERT INTO SELLER_MEMBER (SELLER_ID, SELLER_PW, SELLER_NAME, SELLER_EMAIL, SELLER_PHONE, SELLER_CODE, CODE_NAME, SELLER_LOCATION, SELLER_BLOGADDR, SELLER_GRADE, SELLER_DATE) 
VALUES ('SELLER_ID 1', 'SELLER_PW 1', 'SELLER_NAME 1', 'SELLER_EMAIL 1', 'SELLER_PHONE 1', 'SELLER_CODE 1', 'CODE_NAME 1', 'SELLER_LOCATION 1', 'SELLER_BLOGADDR 1', 'SELLER_GRADE 1', sysdate);

-- USER_MEMBER 테스트 데이터
INSERT INTO USER_MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_EMAIL, MEMBER_PHONE, MEMBER_ADDR, MEMBER_DATE) 
VALUES ('MEMBER_ID 1', 'MEMBER_PW 1', 'MEMBER_NAME 1', 'MEMBER_EMAIL 1', 'MEMBER_PHONE 1', 'MEMBER_ADDR 1', sysdate);
        
        
-- 중복조회 테스트
select count(*)
from SELLER_MEMBER sm, USER_MEMBER um
where sm.seller_id = 'MEMBER_ID 21' or um.member_id = 'MEMBER_ID 1';
        
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

select * from seller_member;
select * from USER_MEMBER;

select *
		from USER_MEMBER
		where MEMBER_ID = 'MEMBER_ID 1' AND MEMBER_PW = 'MEMBER_PW 1';
        
        
---------------------------------------------------------------------------------
select * from foodtruck;
        
select max(truck_num) from foodtruck;
delete from foodtruck where 1=1;
delete from menu where 1=1;
delete from menu_image where 1=1;
delete from reply_truck where 1=1;

select MENU_CODE_SEQ.nextval from dual;
        
        
---------------------------------- 위치별 조회 테스트 ---------------------------
select * from seller_member;  
delete from seller_member where 1=1;
        
select * from foodtruck 
where seller_id = ( 
	select seller_id from seller_member 
	where seller_location = '서울,강남구'
) AND addreq = 'N' and rownum between 0 and 20;


select seller_id from seller_member where seller_location = '서울,강남구';

select * from (
	select Tb.*, rownum rNum from
		( select f.* from
			foodtruck f inner join seller_member s
				on f.seller_id = s.seller_id 
			where s.seller_location = #{location} and f.addreq = 'N'
		)Tb )
where rNum between #{offset} and #{limit};

	
update FOODTRUCK set truck_like=100 where seller_id='skyisu123';
update seller_member set seller_location='서울,강남구' where seller_id='skyisu123';					
update foodtruck set addreq='N' where 1=1;

delete from foodtruck where truck_name not like '%작응영민트럭%';
					
select * from reply_truck;
select reply_truck_seq.nextval from dual;
delete from reply_truck where 1=1;					

select * from foodtruck;

SELECT * FROM (
          SELECT t.*, ROW_NUMBER() OVER(ORDER BY truck_date DESC) AS RNUM
             FROM (
               SELECT * FROM foodtruck WHERE 
                  to_date('17-09-02', 'yyyy-mm-dd') < to_date(substr(truck_etime, 1, 10), 'yyyy-mm-dd')
                     AND addreq = 'N'                    
                ) t
        )
      WHERE RNUM BETWEEN 1 AND 4;

select to_date('17-09-02', 'yyyy-mm-dd') from dual;
select to_date(substr('2017-12-31 12:00', 1, 10), 'yyyy-mm-dd') from dual;


update seller_member set seller_location = '서울,강남구' where 1=1;


SELECT * FROM ( SELECT t.*, ROW_NUMBER() OVER(ORDER BY truck_date DESC) AS RNUM FROM foodtruck t where addreq = 'N') 
		WHERE RNUM BETWEEN 1 AND 4; 
		
		
		
select member_id from user_member where member_name = '송영민' and member_phone = '01049498135'; 		
select member_id from user_member where member_name = '송영민' and member_email = 'skyisu123@naver.com';
		
		
select seller_id from seller_member where seller_name = '송영민' and seller_phone = '01049498135'; 		
select seller_id from seller_member where seller_name = '송영민' and seller_email = 'skyisu1234@naver.com';		
		
select * from user_member;
select * from seller_member;

		
SELECT count(*) FROM foodtruck 
			WHERE seller_id = ( 
				SELECT seller_id FROM seller_member 
				WHERE seller_location = '서울,강남구'
			) AND addreq = 'N';		

select count(*) 
	from foodtruck f inner join seller_member s
		on f.seller_id = s.seller_id
	where s.seller_location = '서울,강남구' and f.addreq = 'N';
			
			
select count(*) from foodtruck where seller_id = 'skyisu123';		
select * from foodtruck where seller_id = 'skyisu123';
			
select * from foodtruck;	
delete from FOODTRUCK where truck_num = 30;
			

select * from menu;
select * from menu_image;
			
select * from user_member;
			
			
			
			
			
			
			
			
			
		


