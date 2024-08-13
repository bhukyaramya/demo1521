package practice.contactTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaBasics {

	public static void main(String[] args) {

		Date dateObj = new Date();

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String actDate = sim.format(dateObj);
		System.out.println("Actual Date : " + actDate);

		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String dateRequires = sim.format(cal.getTime());
		System.out.println("30 Days After Date : " + dateRequires);

	}

}
