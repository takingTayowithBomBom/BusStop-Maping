package makingGUI;

import java.util.Calendar;
import java.util.TimeZone;
import java.lang.Object;

public class TimeManage {
	public TimeManage() {
		Calendar time = Calendar.getInstance();;
		int hour = time.get(Calendar.HOUR);
		int minute = time.get(Calendar.MINUTE);
		int second = time.get(Calendar.SECOND);
		System.out.println(hour+"Ω√"+minute+"∫–"+second+"√ ");
	}
	public static void main(String[]args) {
		new TimeManage();
	}
}
