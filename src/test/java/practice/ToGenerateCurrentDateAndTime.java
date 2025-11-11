package practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToGenerateCurrentDateAndTime {

	public static void main(String[] args) {

		Date d=new Date();
//		System.out.println(d);Thu Oct 16 16:56:26 IST 2025
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(sim.format(d));//16-10-2025
		Calendar cal = sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH, 30);
		System.out.println(cal.getTime());//Sat Nov 15 16:56:26 IST 2025
		System.out.println(sim.format(cal.getTime()));//15-11-2025
		
		
	}

}
