package cse.underdog.org.underdog_client.schedule;

public class ScheduleInfo {
    public int schedule_id;
    public int types;
    public String app_person;
    public String place;
    public String content;
    public String dates;
    public int cycle;
    public String user_id;

    public ScheduleInfo() {

    }

    public ScheduleInfo(int schedule_id, int types, String app_person, String place, String content,
                            String dates, int cycle, String user_id) {
       this.schedule_id = schedule_id;
       this.types = types;
       this.app_person = app_person;
       this.place = place;
       this.content = content;
       this.dates = dates;
       this.cycle = cycle;
       this.user_id = user_id;
    }

}
