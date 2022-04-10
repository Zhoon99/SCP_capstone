package kr.mmgg.scp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class dateTime {
	private Calendar time = Calendar.getInstance();
	
	public String dateTime() {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String dateTime = datetimeFormat.format(time.getTime());
		return dateTime;
	}
}
