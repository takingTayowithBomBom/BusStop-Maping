package makingGUI;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeManage {
	public TimeManage() {
		Calendar time = Calendar.getInstance();;
		int hour = time.get(Calendar.HOUR);
		int minute = time.get(Calendar.MINUTE);
		int second = time.get(Calendar.SECOND);;
	}
}
