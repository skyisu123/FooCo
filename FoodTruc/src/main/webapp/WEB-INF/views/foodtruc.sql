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
    SELLER_GRADE       VARCHAR2(20)     default '����' NOT NULL, 
    SELLER_DATE        DATE             default sysdate NOT NULL, 
    CONSTRAINT SELLER_MEMBER_PK PRIMARY KEY (SELLER_ID)
);

------------------------------------------------------------------------

-- --------------------------REPLY_QUERY-----------------------------
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
-- REPLY_QUERY���̺� �ܷ�Ű ����. �� SELLER_QUERY�� ������ �� ����
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
    SCORE            NUMBER           NOT NULL, 
    REPLY_DATE       DATE             default sysdate NOT NULL, 
    REPLY_READ       VARCHAR2(20)     NOT NULL, 
    CONSTRAINT REPLY_TRUCK_PK PRIMARY KEY (REPLY_NUM)
);

CREATE SEQUENCE REPLY_TRUCK_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE REPLY_TRUCK
    ADD CONSTRAINT FK_REPLY_TRUCK_MEMBER_ID_USER_ FOREIGN KEY (MEMBER_ID)
        REFERENCES USER_MEMBER (MEMBER_ID);

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
-- Ǫ��Ʈ�� ���̺� ���� �Ŀ� �ؾ���.
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
        REFERENCES FOODTRUCK (TRUCK_NUM);
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
        REFERENCES MENU (MENU_CODE);

drop table menu_image;
------------------------------------------------------------------------  


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        
-- SELLER_MEMBER �׽�Ʈ������
INSERT INTO SELLER_MEMBER (SELLER_ID, SELLER_PW, SELLER_NAME, SELLER_EMAIL, SELLER_PHONE, SELLER_CODE, CODE_NAME, SELLER_LOCATION, SELLER_BLOGADDR, SELLER_GRADE, SELLER_DATE) 
VALUES ('SELLER_ID 1', 'SELLER_PW 1', 'SELLER_NAME 1', 'SELLER_EMAIL 1', 'SELLER_PHONE 1', 'SELLER_CODE 1', 'CODE_NAME 1', 'SELLER_LOCATION 1', 'SELLER_BLOGADDR 1', 'SELLER_GRADE 1', sysdate);

-- USER_MEMBER �׽�Ʈ ������
INSERT INTO USER_MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_EMAIL, MEMBER_PHONE, MEMBER_ADDR, MEMBER_DATE) 
VALUES ('MEMBER_ID 1', 'MEMBER_PW 1', 'MEMBER_NAME 1', 'MEMBER_EMAIL 1', 'MEMBER_PHONE 1', 'MEMBER_ADDR 1', sysdate);
        
        
-- �ߺ���ȸ �׽�Ʈ
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
        
---------------------------------- ��ġ�� ��ȸ �׽�Ʈ ---------------------------
select * from seller_member;  
delete from seller_member where 1=1;
        
select * from foodtruck 
where seller_id = ( 
	select seller_id from seller_member 
	where seller_location = '����,������'
) AND addreq = 'N' and rownum between 1 and 16;

select * from (
	select Tb.*, rownum rNum
		from (
			select * from foodtruck 
			where seller_id = (select seller_id from seller_member where seller_location = '����,������')
		) Tb  where Tb.addreq = 'N'
	)
where rNum between 33 and 48;



update FOODTRUCK set truck_like=100 where seller_id='skyisu123';
update seller_member set seller_location='����,������' where seller_id='skyisu123';					
update foodtruck set addreq='N' where 1=1;
					
					
		SELECT * FROM (SELECT rownum Rn, Tlike.*FROM (SELECT ft.truck_num , ft.business_name, ft.truck_name, ft.truck_type, ft.truck_info, ft.truck_image, ft.truck_like, AVG(rt.score) Av 
			FROM foodtruck ft LEFT JOIN reply_truck rt ON ft.truck_num = rt.truck_num 
			GROUP BY ft.truck_num , ft.business_name, ft.truck_name, ft.truck_type, ft.truck_info, ft.truck_image, ft.truck_like
			ORDER BY Av DESC)Tlike)
			WHERE Rn >0;		
			
select to_date('2017-08-05', 'yyyy-mm-dd') from dual;

select * from foodtruck where to_date('2017-08-31', 'yyyy-mm-dd') < to_date(substr(truck_etime, 1, 10), 'yyyy-mm-dd') AND addreq = 'N';
					
select to_date(substr('2017-12-31 12:59', 1, 10), 'yyyy-mm-dd') from dual;
					
select * from menu where inventory=1;
update menu set inventory=1 where inventory=10;
					
select * from menu where truck_num = 193 order by menu_code asc;
select * from menu_image;
select * from menu_image where menu_code = 63;

----------------------------------------------------------------------------------------------------------
-- reply_truck �׽�Ʈ ������
select * from reply_truck;
select * from user_member;	-- MEMBER_ID 1 > ���̵�
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��1', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��2', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��3', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��4', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��5', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��6', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��7', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��8', 5, default, 'Y');
insert into reply_truck (reply_num, member_id, truck_num, reply_content, score, reply_date, reply_read)
	values (REPLY_TRUCK_SEQ.nextval, 'MEMBER_ID 1', 230, '���־��9', 5, default, 'Y');	


select count(*) from REPLY_TRUCK where truck_num=230;

SELECT * FROM (
			SELECT Tb.*, rownum rNum
				FROM (
					SELECT * FROM reply_truck
					WHERE truck_num = 230
				) Tb
			)
		WHERE rNum BETWEEN 1 AND 5;
















