package bit.com.last;

import java.util.List;

public class BorrowView {
	public static void print(List<BorrowDTO> borlist){
		System.out.println("�� "+borlist.size()+"���� �뿩���̽ʴϴ�.");
		System.out.println("��������������������������������������������������������������������������������������");
		System.out.println("|����ȣ|DVD��ȣ|  ����|��������\t|�ݳ�����\t  |");
		System.out.println("��������������������������������������������������������������������������������������");
		for(BorrowDTO bor : borlist)
			System.out.println(bor);
		System.out.println("��������������������������������������������������������������������������������������");

	}
	
}
