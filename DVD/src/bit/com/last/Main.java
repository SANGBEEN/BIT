package bit.com.last;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import util.DateUtil;

public class Main {
	

	public static void main(String[] args){
		List<DvdDTO> dvdlist = null;
		List<CustomerDTO> cuslist = null;
		List<BorrowDTO> borlist = null;
		CustomerDTO cus = null;
		DvdDTO dvd = null;
		BorrowDTO bor = null;
		AdminDTO admin = null;
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String TODAY=sdf.format(date);
	
		Scanner sc = new Scanner(System.in);

		while(true){
			System.out.println("====================================================================");
			System.out.println("작업을 선택하세요>> 1:관리자 모드 2:검색 3:대여/반납 7:회원가입  9:종료");
			System.out.println("====================================================================");
			int select = sc.nextInt();
			if(select==9)break;
			if(select==1){
				System.out.println("아이디를 입력하세요");
				String id = sc.next();
				System.out.println("비밀번호를 입력하세요");
				String pass = sc.next();
				admin=AdminDAO.adminLogin(id, pass);
				if(admin!=null){
					System.out.println("로그인 되었습니다.");
					while(true){
						System.out.println("====================================================================");
						System.out.println("1:고객 관리 2:DVD관리 0:뒤로가기");
						System.out.println("====================================================================");
						select = sc.nextInt();
						if(select==0)break;
						if(select==1){
							System.out.println("====================================================================");
							System.out.println("1:모든 고객 조회 2:이름으로 검색 3:고객 삭제 0:뒤로가기");
							System.out.println("====================================================================");
							select=sc.nextInt();
							if(select==0)continue;
							if(select==1){
								cuslist = CustomerDAO.cusSelectAll();
								CustomerView.print(cuslist);
							}else if(select==2){
								System.out.println("이름을 입력하세요");
								String name=sc.next();
								cus = CustomerDAO.cusSelectByName(name);
								if(cus!=null){
									CustomerView.print(cus);
									borlist=BorrowDAO.borSelectByCnum(cus.getC_num());
									if(borlist!=null){
										BorrowView.print(borlist);
									}
									else
										System.out.println("대여중인 DVD가 존재하지 않습니다.");
								}
								else
									System.out.println("없는 고객 입니다.");
								
								
							}else if(select==3){
								//고객삭제
								System.out.println("고객번호를 입력하세요");
								select=sc.nextInt();
								if(CustomerDAO.cusDelete(select))
									System.out.println(select+"번 고객을 삭제했습니다.");
								else
									System.out.println("반납하지 않은 dvd가 존재합니다.");
							}
						}else if(select==2){
							System.out.println("====================================================================");
							System.out.println("1:DVD추가 2:DVD삭제 3:DVD수정 4:전체조회 0:뒤로가기");
							System.out.println("====================================================================");
							select=sc.nextInt();
							if(select==0)continue;
							if(select==1){
								//addDvd
								dvd=new DvdDTO();
								sc.nextLine();
								System.out.print("제목: ");
								dvd.setTitle(sc.nextLine());
								System.out.print("출시일자 : ");
								dvd.setRelease_date(DateUtil.getSqlDate(sc.next()));
								sc.nextLine();
								System.out.print("감독 : ");
								dvd.setDirector(sc.nextLine());
								System.out.print("장르: ");
								dvd.setGenre(sc.next());
								sc.nextLine();
								System.out.print("주연배우: ");
								dvd.setActor(sc.nextLine());
								System.out.print("관람등급: ");
								dvd.setRating(sc.next());
								System.out.print("가격 : ");
								dvd.setPrice(sc.nextInt());
								if(DvdDAO.addDvd(dvd)){
									System.out.println(dvd.getTitle()+"를 추가하였습니다");
								}else
									System.out.println("추가실패");
								
							}else if(select==2){
								//deleteDvd
								System.out.println("삭제할 dvd의 번호를 입력하세요");
								select=sc.nextInt();
								//dvd=DvdDAO.dvdSelectByNum(select);
								if(DvdDAO.deleteDvd(select))
									System.out.println("삭제");
								else
									System.out.println("삭제실패");
							}else if(select==3){
								//updateDvd
								System.out.print("가격을 변경 할 dvd 번호를 입력하세요");
								select=sc.nextInt();
								if(DvdDAO.dvdSelectByNum(select)!=null){
									System.out.print("변경 가격을 입력하세요");
									int price=sc.nextInt();
									if(DvdDAO.priceUpdate(price, select))
										System.out.println("변경성공");
									else
										System.out.println("변경실패");
								}else
									System.out.println("잘못입력하셨습니다.");
						
								
							}else if(select==4){
								dvdlist = DvdDAO.dvdSelectAll();
								System.out.println("모든 영화 조회");
								DvdView.print(dvdlist);
							}else
								System.out.println("잘못입력하셨습니다.");
						}
					}
				
				}else{
					System.out.println("아이디/비밀번호가 올바르지 않습니다.");
				}
				
			}else if(select==2){
				System.out.println("====================================================================");
				System.out.println("1:전체조회 2:감독으로 검색 3:제목으로 검색 4:날짜로 검색 5:배우로 검색");
				System.out.println("====================================================================");
				select=sc.nextInt();
				searchDvd(dvdlist, select, sc);

			}else if(select==3){
				System.out.println("아이디를 입력하세요");
				String id = sc.next();
				System.out.println("비밀번호를 입력하세요");
				String pass = sc.next();
				cus=CustomerDAO.login(id, pass);
				if(cus!=null){
					
					while(true){
						System.out.println("====================================================================");
						System.out.println("1:대여 2:반납 0:뒤로가기");
						System.out.println("====================================================================");
						select=sc.nextInt();
						if(select==0)break;
						if(select==1){
							while(true){
								System.out.println("====================================================================");
								System.out.println("1:전체조회 2:감독으로 검색 3:제목으로 검색 4:날짜로 검색 5:배우로 검색 0:뒤로");
								System.out.println("====================================================================");
								select = sc.nextInt();
								if(select==0)break;
								dvdlist=searchDvd(dvdlist, select, sc);
								if(dvdlist==null){
									System.out.println("조건에 맞는 dvd가 존재하지 않습니다");
									continue;
								}
								else if(dvdlist.size()==1){
									dvd=dvdlist.get(0);
									if(foo(dvd,cus,bor,sc)==0)continue;
									break;
								}else{
									//2건 이상일 때 대여하고자하는 번호 입력
									System.out.println("대여할 dvd의 번호를 입력하세요");
									select = sc.nextInt();
									int index=0;
		
									while(dvdlist.get(index).getD_num()!=select){
										index++;
									}
									dvd = dvdlist.get(index);
									if(foo(dvd,cus,bor,sc)==0)continue;
									break;
									
								}
							}
						}else if(select==2){
							while(true){
								borlist=BorrowDAO.borSelectByCnum(cus.getC_num());
								BorrowView.print(borlist);
								System.out.println("반납할 dvd의 번호를 입력하시오. 뒤로가기:0");
								select=sc.nextInt();
								if(select==0)break;;
								bor=BorrowDAO.borSelectByDnum(select);
								if(bor==null){
									System.out.println("잘못입력하셨습니다.");
									continue;
								}
								Date end_date=bor.getEnd_date();
								dvd=DvdDAO.dvdSelectByNum(select);
								if(BorrowDAO.borDelete(dvd)){
									//DvdDAO.changeEnable(dvd);
									System.out.println(dvd.getTitle()+"을 반납하셨습니다.");
									if(TODAY.compareTo(end_date.toString())>0){
										System.out.println("반납기한이 지났습니다. 다음부터는 반납기한을 지켜주세요.");
									}
								}else{
									System.out.println("잘못입력하셨습니다.");
								}
								
							}
						}else
							System.out.println("잘못입력하셨습니다.");
					}
	
				
				}else
					System.out.println("아이디/비밀번호가 올바르지 않습니다.");
			}else if(select==7){
				cus=new CustomerDTO();
				System.out.println("====회원가입====");
				System.out.print("이름:");
				cus.setName(sc.next());
				System.out.print("ID:");
				cus.setId(sc.next());
				System.out.print("비밀번호:");
				cus.setPassword(sc.next());
				System.out.print("이메일:");
				cus.setEmail(sc.next());
				System.out.print("주민등록번호:");
				cus.setRrn(sc.next());
				System.out.print("전화번호:");
				cus.setPhone(sc.next());
				sc.nextLine();
				System.out.print("주소:");
				cus.setAddress(sc.nextLine());
				if(CustomerDAO.signUp(cus))
					System.out.println("회원가입이 완료되었습니다.");
				else
					System.out.println("회원가입에 실패했습니다.");
			}else
				System.out.println("잘못입력하셨습니다.");
			
		}
	}
	
	
	
	//borrowDVD호출 메서드
	public static int foo(DvdDTO dvd, CustomerDTO cus, BorrowDTO bor,  Scanner sc){

		System.out.println(dvd.getTitle()+"을 대여하시겠습니까? Y/N");
		String ans = sc.next();
		int money=0;
		if(ans.toUpperCase().equals("Y") && dvd.getEnable().equals("Y")){
			System.out.println(dvd.getTitle()+"의 대여료는 "+dvd.getPrice()+"원 입니다.");
			System.out.print("내실돈>>");
			money=sc.nextInt();
			if(money<dvd.getPrice()){
				if((money+cus.getPoint())>=dvd.getPrice()){
					System.out.println("현재 고객님의 마일리지는 "+cus.getPoint()+"점 입니다.");
					System.out.println("포인트를 사용하시겠습니까? Y/N");
					ans=sc.next();
					if(ans.toUpperCase().equals("Y")){
						if(BorrowDAO.borrowDVDusingPoint(cus, dvd,(dvd.getPrice()-money))){
							bor=BorrowDAO.borSelectByDnum(dvd.getD_num());
							System.out.println("포인트를 사용하셨습니다. 남은 포인트는 "+(cus.getPoint()-(dvd.getPrice()-money))+"입니다.");
							System.out.println(dvd.getTitle()+"을 대여하셨습니다. 반납날짜는 "+bor.getEnd_date()+"입니다");
						}else{
							System.out.println("대여가 불가능한 DVD입니다");
						}
						System.out.println("더 대여하시겠습니까? Y/N");
						ans=sc.next();
						if(ans.toUpperCase().equals("Y"))return 0;
					}
				}else{
					System.out.println("돈이 모자랍니다.");
					return 0;
				}
			}else{
				if(BorrowDAO.borrowDVD(cus, dvd)){//enablecheck
					bor=BorrowDAO.borSelectByDnum(dvd.getD_num());
					System.out.println(dvd.getTitle()+"을 대여하셨습니다. 반납날짜는 "+bor.getEnd_date()+"입니다");
				}else{
					System.out.println("대여가 불가능한 DVD입니다");
				}
				System.out.println("더 대여하시겠습니까? Y/N");
				ans=sc.next();
				if(ans.toUpperCase().equals("Y"))return 0;
			}
			
			
		}else{
			System.out.println("대여가 불가능한 DVD입니다");
			return 0;
		}
	
		return 1;
	}
	public static List<DvdDTO> searchDvd(List<DvdDTO> dvdlist, int select, Scanner sc){

		switch(select){
		case 1:
			dvdlist = DvdDAO.dvdSelectAll();
			System.out.println("모든 영화 조회");
			DvdView.print(dvdlist);
			break;
		case 2:
			sc.nextLine();
			System.out.print("감독을 입력하세요");
			String director=sc.nextLine();
			dvdlist = DvdDAO.dvdSelectByDirector(director);
			DvdView.print(dvdlist);
			break;
		case 3:
			sc.nextLine();
			System.out.print("제목을 입력하세요");
			String title=sc.nextLine();
			dvdlist = DvdDAO.dvdSelectByTitle(title);
			DvdView.print(dvdlist);
			break;
		case 4:
			System.out.println("시작 날짜를 입력하세요");
			Date start_date = DateUtil.getSqlDate(sc.next());
			System.out.println("종료 날짜를 입력하세요");
			Date end_date = DateUtil.getSqlDate(sc.next());
			if(start_date==null || end_date==null || (start_date.after(end_date))){
				System.out.println("잘못입력하셨습니다.");
				break;
			}
			dvdlist = DvdDAO.dvdSelectByDate(start_date,end_date);
			DvdView.print(dvdlist);
			break;
		case 5:
			sc.nextLine();
			System.out.println("배우를 입력하세요");
			String actor = sc.nextLine();
			dvdlist = DvdDAO.dvdSelectByActor(actor);
			DvdView.print(dvdlist);
			break;
		default:
			System.out.println("잘못입력하셨습니다.");
		}
		return dvdlist;
	}

}
