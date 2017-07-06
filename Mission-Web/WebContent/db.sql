select * from t_board;

insert into t_board(no,title,writer,content) values(seq_t_board_no.nextval,'1','1','1');
select no, title, writer, to_char(reg_date, 'yyyy-mm-dd') as reg_date
 from t_board 
order by no desc
create sequence seq_t_board_no;

create table t_member(
	id varchar2(20) primary key,
	name varchar2(20) not null,
	password varchar2(30) not null,
	email_id varchar2(20),
	email_domain varchar2(20),
	tel1 char(3),
	tel2 char(4),
	tel3 char(4),
	post char(7),
	basic_addr varchar(200),
	detail_addr varchar(200),
	type char(1) default 'U',
	reg_date date default sysdate
)
insert into t_member(id,name,password,email_id,email_domain,tel1,tel2,tel3,basic_addr,detail_addr,type,reg_date) values('aa11a','11bbb','123','aaa','@naver.com','010','0000','0000','강남','비트','U',sysdate)
select * from t_member
insert into t_member values('admin','admin','admin','admin@naver.com','aaa','010','9999','9999','aaa','강남','비트','A',sysdate)
alter sequence seq_t_board_no nocache;
update T_BOARD set view_cnt=view_cnt+1 where no=26
select * from T_BOARD
SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'seq_t_board_no';
ALTER SEQUENCE seq_t_board_no increment by 1
select seq_t_board_no.nextval from dual
INCREMENT BY 1;
update t_member set type='S' where name='admin'
truncate table t_board_file
update t_board set title='44444' , content='fsdffdsf' where no=4
delete from t_board where no = 2
select * from seq;
select * from t_board_file
select * from t_board