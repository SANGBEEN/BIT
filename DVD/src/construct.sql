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
update customer set point=110.1 where name='���'
alter table customer
modify(point number(6,1) default 0.0)
insert into customer values (AAA.NEXTVAL,'custom1','1234','920118','���','cu@naver.com', '����','010-1234-5768',0);
insert into customer values (AAA.NEXTVAL,'custom2','1234','910118','����','cu2@naver.com', '����','010-1234-5760',0);
insert into customer values (AAA.NEXTVAL,'custom3','1234','900118','����','cu3@naver.com', '����','010-1234-2760',0);
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
insert into admin values(1,'admin','1234','920118', '������','admin@naver.com','����','123-123-1234')
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
update dvd set actor='�ٴϿ� Į���' where d_num=100
insert into dvd values(100,'���� �ʷ�', '2017/05/17', '�پƿ�', '����/������', 10000, '15��');
insert into dvd values(101,'������', '2017/05/03', '���Ȱ�', '����/�ڹ̵�',5000, '15��');
insert into dvd values(BBB.NEXTVAL,'��Ʋ�� ����', '2017/05/09', '���̸���:Ŀ�ӳ�Ʈ', 'SF/������',5000, '15��','����Ŭ �н�����','Y');
insert into dvd values(BBB.NEXTVAL,'���ӽ� ��', '2017/05/03', '������2', '�׼�/SF',5000, '12��','ũ���� ����','Y');
insert into dvd values(BBB.NEXTVAL,'���Ŵ �ڴ�', '2017/05/24', 'ĳ������� ����', '�׼�/��Ÿ��',15000, '12��','���� ��','Y');
insert into dvd values(BBB.NEXTVAL,'������', '2017/05/03', '���Ȱ�', '����/�ڹ̵�',5000, '15��');
insert into dvd values(BBB.NEXTVAL,'������', '2017/05/03', '���Ȱ�', '����/�ڹ̵�',5000, '15��');
insert into dvd values(BBB.NEXTVAL,'������', '2017/05/03', '���Ȱ�', '����/�ڹ̵�',5000, '15��');

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







