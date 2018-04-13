package cse.underdog.org.underdog_client.login;

public class LoginResult {
    UserInfo data = new UserInfo();
    String stat;

    public LoginResult(UserInfo data, String stat){
        this.data = data;
        this.stat = stat;
    }

    public String getNickname(){
        return data.nickname;
    }
}
