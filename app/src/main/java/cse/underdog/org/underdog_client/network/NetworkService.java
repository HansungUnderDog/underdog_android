package cse.underdog.org.underdog_client.network;


import cse.underdog.org.underdog_client.login.LoginInfo;
import cse.underdog.org.underdog_client.login.LoginResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkService {
    //ToDo: api 문서보고 url 맞추기
    @POST("/login/signin")
    Call<LoginResult> checkLogin(@Body LoginInfo loginInfo);

   /* @POST("/logout/logout")
    Call<LogoutResult> logout();

    @POST("/signup/up")
    Call<JoinResult> getJoinResult(@Body JoinInfo joinInfo);*/


}
