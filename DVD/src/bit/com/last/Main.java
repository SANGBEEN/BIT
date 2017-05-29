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
			System.out.println("�۾��� �����ϼ���>> 1:������ ��� 2:�˻� 3:�뿩/�ݳ� 7:ȸ������  9:����");
			System.out.println("====================================================================");
			int select = sc.nextInt();
			if(select==9)break;
			if(select==1){
				System.out.println("���̵� �Է��ϼ���");
				String id = sc.next();
				System.out.println("��й�ȣ�� �Է��ϼ���");
				String pass = sc.next();
				admin=AdminDAO.adminLogin(id, pass);
				if(admin!=null){
					System.out.println("�α��� �Ǿ����ϴ�.");
					while(true){
						System.out.println("====================================================================");
						System.out.println("1:�� ���� 2:DVD���� 0:�ڷΰ���");
						System.out.println("====================================================================");
						select = sc.nextInt();
						if(select==0)break;
						if(select==1){
							System.out.println("====================================================================");
							System.out.println("1:��� �� ��ȸ 2:�̸����� �˻� 3:�� ���� 0:�ڷΰ���");
							System.out.println("====================================================================");
							select=sc.nextInt();
							if(select==0)continue;
							if(select==1){
								cuslist = CustomerDAO.cusSelectAll();
								CustomerView.print(cuslist);
							}else if(select==2){
								System.out.println("�̸��� �Է��ϼ���");
								String name=sc.next();
								cus = CustomerDAO.cusSelectByName(name);
								if(cus!=null){
									CustomerView.print(cus);
									borlist=BorrowDAO.borSelectByCnum(cus.getC_num());
									if(borlist!=null){
										BorrowView.print(borlist);
									}
									else
										System.out.println("�뿩���� DVD�� �������� �ʽ��ϴ�.");
								}
								else
									System.out.println("���� �� �Դϴ�.");
								
								
							}else if(select==3){
								//������
								System.out.println("����ȣ�� �Է��ϼ���");
								select=sc.nextInt();
								if(CustomerDAO.cusDelete(select))
									System.out.println(select+"�� ���� �����߽��ϴ�.");
								else
									System.out.println("�ݳ����� ���� dvd�� �����մϴ�.");
							}
						}else if(select==2){
							System.out.println("====================================================================");
							System.out.println("1:DVD�߰� 2:DVD���� 3:DVD���� 4:��ü��ȸ 0:�ڷΰ���");
							System.out.println("====================================================================");
							select=sc.nextInt();
							if(select==0)continue;
							if(select==1){
								//addDvd
								dvd=new DvdDTO();
								sc.nextLine();
								System.out.print("����: ");
								dvd.setTitle(sc.nextLine());
								System.out.print("������� : ");
								dvd.setRelease_date(DateUtil.getSqlDate(sc.next()));
								sc.nextLine();
								System.out.print("���� : ");
								dvd.setDirector(sc.nextLine());
								System.out.print("�帣: ");
								dvd.setGenre(sc.next());
								sc.nextLine();
								System.out.print("�ֿ����: ");
								dvd.setActor(sc.nextLine());
								System.out.print("�������: ");
								dvd.setRating(sc.next());
								System.out.print("���� : ");
								dvd.setPrice(sc.nextInt());
								if(DvdDAO.addDvd(dvd)){
									System.out.println(dvd.getTitle()+"�� �߰��Ͽ����ϴ�");
								}else
									System.out.println("�߰�����");
								
							}else if(select==2){
								//deleteDvd
								System.out.println("������ dvd�� ��ȣ�� �Է��ϼ���");
								select=sc.nextInt();
								//dvd=DvdDAO.dvdSelectByNum(select);
								if(DvdDAO.deleteDvd(select))
									System.out.println("����");
								else
									System.out.println("��������");
							}else if(select==3){
								//updateDvd
								System.out.print("������ ���� �� dvd ��ȣ�� �Է��ϼ���");
								select=sc.nextInt();
								if(DvdDAO.dvdSelectByNum(select)!=null){
									System.out.print("���� ������ �Է��ϼ���");
									int price=sc.nextInt();
									if(DvdDAO.priceUpdate(price, select))
										System.out.println("���漺��");
									else
										System.out.println("�������");
								}else
									System.out.println("�߸��Է��ϼ̽��ϴ�.");
						
								
							}else if(select==4){
								dvdlist = DvdDAO.dvdSelectAll();
								System.out.println("��� ��ȭ ��ȸ");
								DvdView.print(dvdlist);
							}else
								System.out.println("�߸��Է��ϼ̽��ϴ�.");
						}
					}
				
				}else{
					System.out.println("���̵�/��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
				}
				
			}else if(select==2){
				System.out.println("====================================================================");
				System.out.println("1:��ü��ȸ 2:�������� �˻� 3:�������� �˻� 4:��¥�� �˻� 5:���� �˻�");
				System.out.println("====================================================================");
				select=sc.nextInt();
				searchDvd(dvdlist, select, sc);

			}else if(select==3){
				System.out.println("���̵� �Է��ϼ���");
				String id = sc.next();
				System.out.println("��й�ȣ�� �Է��ϼ���");
				String pass = sc.next();
				cus=CustomerDAO.login(id, pass);
				if(cus!=null){
					
					while(true){
						System.out.println("====================================================================");
						System.out.println("1:�뿩 2:�ݳ� 0:�ڷΰ���");
						System.out.println("====================================================================");
						select=sc.nextInt();
						if(select==0)break;
						if(select==1){
							while(true){
								System.out.println("====================================================================");
								System.out.println("1:��ü��ȸ 2:�������� �˻� 3:�������� �˻� 4:��¥�� �˻� 5:���� �˻� 0:�ڷ�");
								System.out.println("====================================================================");
								select = sc.nextInt();
								if(select==0)break;
								dvdlist=searchDvd(dvdlist, select, sc);
								if(dvdlist==null){
									System.out.println("���ǿ� �´� dvd�� �������� �ʽ��ϴ�");
									continue;
								}
								else if(dvdlist.size()==1){
									dvd=dvdlist.get(0);
									if(foo(dvd,cus,bor,sc)==0)continue;
									break;
								}else{
									//2�� �̻��� �� �뿩�ϰ����ϴ� ��ȣ �Է�
									System.out.println("�뿩�� dvd�� ��ȣ�� �Է��ϼ���");
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
								System.out.println("�ݳ��� dvd�� ��ȣ�� �Է��Ͻÿ�. �ڷΰ���:0");
								select=sc.nextInt();
								if(select==0)break;;
								bor=BorrowDAO.borSelectByDnum(select);
								if(bor==null){
									System.out.println("�߸��Է��ϼ̽��ϴ�.");
									continue;
								}
								Date end_date=bor.getEnd_date();
								dvd=DvdDAO.dvdSelectByNum(select);
								if(BorrowDAO.borDelete(dvd)){
									//DvdDAO.changeEnable(dvd);
									System.out.println(dvd.getTitle()+"�� �ݳ��ϼ̽��ϴ�.");
									if(TODAY.compareTo(end_date.toString())>0){
										System.out.println("�ݳ������� �������ϴ�. �������ʹ� �ݳ������� �����ּ���.");
									}
								}else{
									System.out.println("�߸��Է��ϼ̽��ϴ�.");
								}
								
							}
						}else
							System.out.println("�߸��Է��ϼ̽��ϴ�.");
					}
	
				
				}else
					System.out.println("���̵�/��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			}else if(select==7){
				cus=new CustomerDTO();
				System.out.println("====ȸ������====");
				System.out.print("�̸�:");
				cus.setName(sc.next());
				System.out.print("ID:");
				cus.setId(sc.next());
				System.out.print("��й�ȣ:");
				cus.setPassword(sc.next());
				System.out.print("�̸���:");
				cus.setEmail(sc.next());
				System.out.print("�ֹε�Ϲ�ȣ:");
				cus.setRrn(sc.next());
				System.out.print("��ȭ��ȣ:");
				cus.setPhone(sc.next());
				sc.nextLine();
				System.out.print("�ּ�:");
				cus.setAddress(sc.nextLine());
				if(CustomerDAO.signUp(cus))
					System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
				else
					System.out.println("ȸ�����Կ� �����߽��ϴ�.");
			}else
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
			
		}
	}
	
	
	
	//borrowDVDȣ�� �޼���
	public static int foo(DvdDTO dvd, CustomerDTO cus, BorrowDTO bor,  Scanner sc){

		System.out.println(dvd.getTitle()+"�� �뿩�Ͻðڽ��ϱ�? Y/N");
		String ans = sc.next();
		int money=0;
		if(ans.toUpperCase().equals("Y") && dvd.getEnable().equals("Y")){
			System.out.println(dvd.getTitle()+"�� �뿩��� "+dvd.getPrice()+"�� �Դϴ�.");
			System.out.print("���ǵ�>>");
			money=sc.nextInt();
			if(money<dvd.getPrice()){
				if((money+cus.getPoint())>=dvd.getPrice()){
					System.out.println("���� ������ ���ϸ����� "+cus.getPoint()+"�� �Դϴ�.");
					System.out.println("����Ʈ�� ����Ͻðڽ��ϱ�? Y/N");
					ans=sc.next();
					if(ans.toUpperCase().equals("Y")){
						if(BorrowDAO.borrowDVDusingPoint(cus, dvd,(dvd.getPrice()-money))){
							bor=BorrowDAO.borSelectByDnum(dvd.getD_num());
							System.out.println("����Ʈ�� ����ϼ̽��ϴ�. ���� ����Ʈ�� "+(cus.getPoint()-(dvd.getPrice()-money))+"�Դϴ�.");
							System.out.println(dvd.getTitle()+"�� �뿩�ϼ̽��ϴ�. �ݳ���¥�� "+bor.getEnd_date()+"�Դϴ�");
						}else{
							System.out.println("�뿩�� �Ұ����� DVD�Դϴ�");
						}
						System.out.println("�� �뿩�Ͻðڽ��ϱ�? Y/N");
						ans=sc.next();
						if(ans.toUpperCase().equals("Y"))return 0;
					}
				}else{
					System.out.println("���� ���ڶ��ϴ�.");
					return 0;
				}
			}else{
				if(BorrowDAO.borrowDVD(cus, dvd)){//enablecheck
					bor=BorrowDAO.borSelectByDnum(dvd.getD_num());
					System.out.println(dvd.getTitle()+"�� �뿩�ϼ̽��ϴ�. �ݳ���¥�� "+bor.getEnd_date()+"�Դϴ�");
				}else{
					System.out.println("�뿩�� �Ұ����� DVD�Դϴ�");
				}
				System.out.println("�� �뿩�Ͻðڽ��ϱ�? Y/N");
				ans=sc.next();
				if(ans.toUpperCase().equals("Y"))return 0;
			}
			
			
		}else{
			System.out.println("�뿩�� �Ұ����� DVD�Դϴ�");
			return 0;
		}
	
		return 1;
	}
	public static List<DvdDTO> searchDvd(List<DvdDTO> dvdlist, int select, Scanner sc){

		switch(select){
		case 1:
			dvdlist = DvdDAO.dvdSelectAll();
			System.out.println("��� ��ȭ ��ȸ");
			DvdView.print(dvdlist);
			break;
		case 2:
			sc.nextLine();
			System.out.print("������ �Է��ϼ���");
			String director=sc.nextLine();
			dvdlist = DvdDAO.dvdSelectByDirector(director);
			DvdView.print(dvdlist);
			break;
		case 3:
			sc.nextLine();
			System.out.print("������ �Է��ϼ���");
			String title=sc.nextLine();
			dvdlist = DvdDAO.dvdSelectByTitle(title);
			DvdView.print(dvdlist);
			break;
		case 4:
			System.out.println("���� ��¥�� �Է��ϼ���");
			Date start_date = DateUtil.getSqlDate(sc.next());
			System.out.println("���� ��¥�� �Է��ϼ���");
			Date end_date = DateUtil.getSqlDate(sc.next());
			if(start_date==null || end_date==null || (start_date.after(end_date))){
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
				break;
			}
			dvdlist = DvdDAO.dvdSelectByDate(start_date,end_date);
			DvdView.print(dvdlist);
			break;
		case 5:
			sc.nextLine();
			System.out.println("��츦 �Է��ϼ���");
			String actor = sc.nextLine();
			dvdlist = DvdDAO.dvdSelectByActor(actor);
			DvdView.print(dvdlist);
			break;
		default:
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
		}
		return dvdlist;
	}

}
