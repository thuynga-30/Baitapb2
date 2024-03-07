import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ClockFrame extends JFrame {
    private JLabel timeLabel;
    private int timezoneOffset;
    private Thread updateThread;
    public ClockFrame(int timezoneOffset) {
    	this.timezoneOffset= timezoneOffset;
        setTitle("Clock");
        setSize(350, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        timeLabel = new JLabel();
        updateTime();
        add(timeLabel);
    }

    public void Start() {
        new Thread(()->{
            while (true) {
                updateTime();
            }
        }).start();
    }

    public void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        long currentTime = System.currentTimeMillis() + timezoneOffset * 3600 * 1000;
        timeLabel.setText(dateFormat.format(new Date(currentTime)));
    }
}