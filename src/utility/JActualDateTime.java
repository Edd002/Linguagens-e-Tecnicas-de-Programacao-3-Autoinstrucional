package utility;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

public class JActualDateTime {
	public static JLabel getCurrentDateTime(JLabel lblDataTime) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while(true) {
					Date data = Calendar.getInstance().getTime();
					DateFormat dateFormatDate = DateFormat.getDateInstance();
					DateFormat dateFormatTime = DateFormat.getTimeInstance();
					lblDataTime.setText(dateFormatTime.format(data) + " " + dateFormatDate.format(data));   
					try {
						Thread.sleep(1000);
					} catch(InterruptedException interruptedException) {
						
					}
				}
			}
		});

		thread.start();
		return lblDataTime;
	}
}
