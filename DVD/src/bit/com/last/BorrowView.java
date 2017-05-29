package bit.com.last;

import java.util.List;

public class BorrowView {
	public static void print(List<BorrowDTO> borlist){
		System.out.println("恥 "+borlist.size()+"鯵研 企食掻戚淑艦陥.");
		System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		System.out.println("|壱梓腰硲|DVD腰硲|  亜維|企窒析切\t|鋼崖析切\t  |");
		System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		for(BorrowDTO bor : borlist)
			System.out.println(bor);
		System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");

	}
	
}
