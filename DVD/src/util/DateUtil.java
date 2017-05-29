package util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static Date getSqlDate(String dt){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d; 
		Date hire = null;
		try {
			d = sd.parse(dt);
			hire = new Date(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("잘못된 포맷입니다.");
			return null;
		}
		return hire;
	}





}
