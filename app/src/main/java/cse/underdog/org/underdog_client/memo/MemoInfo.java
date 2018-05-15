package cse.underdog.org.underdog_client.memo;

public class MemoInfo {
    int memo_id;
    String context;
    int user_id;

    public String getContext(){
        return context;
    }

    public MemoInfo(){

    }

    public MemoInfo(String context){
        this.context=context;
    }

    public MemoInfo(int memo_id, String context, int user_id){
        this.context = context;
        this.memo_id = memo_id;
        this.user_id = user_id;
    }

}
