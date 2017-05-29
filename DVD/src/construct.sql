create table customer(
	C_NUM number(6) constraint customer_C_NUM_pk primary key,
	ID varchar(20) constraint customer_ID_uk unique,
	PASSWORD varchar(20) not null,
	RRN varchar(20) constraint customer_RRN_uk unique,
	NAME varchar(20) not null,
	EMAIL varchar(50) not null,
	address varchar(50) not null,
	phone varchar(20) constraint customer_phone_uk unique,
	point number(5) default 0
);

select * from customer
update customer set point=110.1 where name='상빈'
alter table customer
modify(point number(6,1) default 0.0)
insert into customer values (AAA.NEXTVAL,'custom1','1234','920118','상빈','cu@naver.com', '강남','010-1234-5768',0);
insert into customer values (AAA.NEXTVAL,'custom2','1234','910118','동주','cu2@naver.com', '강남','010-1234-5760',0);
insert into customer values (AAA.NEXTVAL,'custom3','1234','900118','방현','cu3@naver.com', '강남','010-1234-2760',0);
alter table customer
add(name varchar(20) not null);


create table admin(A_NUM number(6) constraint admin_A_NUM_pk primary key);

alter table admin
add(A_phone varchar(20) constraint admin_A_phone_uk unique)

create table admin(
	A_NUM number(6) constraint admin_A_NUM_pk primary key,
	A_ID varchar(20) constraint admin_A_ID_uk unique,
	A_PASSWORD varchar(20) not null,
	A_RRN varchar(20) constraint admin_A_RRN_uk unique,
	A_NAME varchar(20) not null,
	A_EMAIL varchar(50) not null,
	A_address varchar(50) not null,
	A_phone varchar(20) constraint admin_A_phone_uk unique,
);
select * from admin;
insert into admin values(1,'admin','1234','920118', '관리자','admin@naver.com','강남','123-123-1234')
delete from admin


create table family(
	C_F_Num number(6) constraint family_C_F_Num_fk references customer(C_NUM),
	F_Name varchar(20) not null,
	F_ID varchar(20) constraint family_ID_uk unique
);

insert into family()
select * from dvd where release_date between '2017-05-01' and '2017-06-01' order by title


create table DVD(
	D_NUM number(6) constraint DVD_D_NUM_pk primary key,
	director varchar(20) not null,
	release_date date,
	title varchar(20) not null,
	genre varchar(20),
	price number(10) not null,
	rating varchar(20)
);
select * from dvd
alter table dvd
modify(title varchar(50) )

alter table dvd
modify(enable char(1) constraint dvd_enable_ck check(enable in ('Y','N')) default 'Y' );
update dvd set actor='다니엘 칼루야' where d_num=100
insert into dvd values(100,'조던 필레', '2017/05/17', '겟아웃', '공포/스릴러', 10000, '15세');
insert into dvd values(101,'김형주', '2017/05/03', '보안관', '범죄/코미디',5000, '15세');
insert into dvd values(BBB.NEXTVAL,'리틀리 스콧', '2017/05/09', '에이리언:커머넌트', 'SF/스릴러',5000, '15세','마이클 패스벤더','Y');
insert into dvd values(BBB.NEXTVAL,'제임스 건', '2017/05/03', '가오갤2', '액션/SF',5000, '12세','크리스 프랫','Y');
insert into dvd values(BBB.NEXTVAL,'요아킴 뢰닝', '2017/05/24', '캐리비안의 해적', '액션/판타지',15000, '12세','조니 뎁','Y');
insert into dvd values(BBB.NEXTVAL,'김형주', '2017/05/03', '보안관', '범죄/코미디',5000, '15세');
insert into dvd values(BBB.NEXTVAL,'김형주', '2017/05/03', '보안관', '범죄/코미디',5000, '15세');
insert into dvd values(BBB.NEXTVAL,'김형주', '2017/05/03', '보안관', '범죄/코미디',5000, '15세');

CREATE SEQUENCE AAA START WITH 1 INCREMENT BY 1 MAXVALUE 100 CYCLE NOCACHE;
CREATE SEQUENCE BBB START WITH 1 INCREMENT BY 1 MAXVALUE 100 CYCLE NOCACHE;


create table borrow(
	C_B_Num number(6) constraint borrow_C_B_Num_fk references customer(C_NUM),
	D_B_Num number(6) constraint borrow_D_B_Num_fk references dvd(D_NUM),
	price number(10),
	start_date date,
	end_date date,
	primary key(C_B_Num,D_B_Num,price)
);
select * from borrow
select * from customer
alter table dvd
modify(actor varchar(50) not null)

---------------------------


select * from borrow
update borrow set end_date='2017-05-23' where C_B_NUM=1


select * from customer







