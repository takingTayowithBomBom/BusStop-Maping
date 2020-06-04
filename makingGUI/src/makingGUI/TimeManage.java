package makingGUI;

import java.util.Calendar;
import java.lang.Object;

public class TimeManage {
	int hour;
	int minute;
	int second;
	
	public TimeManage() {
		Calendar time = Calendar.getInstance();
		hour = time.get(Calendar.HOUR);
		minute = time.get(Calendar.MINUTE);
		second = time.get(Calendar.SECOND);
	}
	
	public String getTime() {
		String tempStr = "";
		tempStr = Integer.toString(hour) + "Ω√" + Integer.toString(minute) + "∫–"
				+ Integer.toString(second) + "√ ";
		return tempStr;
	}

}
