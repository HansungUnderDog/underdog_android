package cse.underdog.org.underdog_client.schedule;

public class ScheduleResult {
    ScheduleInfo data = new ScheduleInfo();
    String stat;

    public ScheduleResult(String stat) {
        this.stat = stat;
    }

    public ScheduleResult(ScheduleInfo data, String stat) {
        this.data = data;
        this.stat = stat;
    }
}
