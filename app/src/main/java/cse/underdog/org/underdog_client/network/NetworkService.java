package cse.underdog.org.underdog_client.network;


import cse.underdog.org.underdog_client.login.LoginInfo;
import cse.underdog.org.underdog_client.login.LoginResult;
import cse.underdog.org.underdog_client.schedule.ScheduleResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {
    //ToDo: api 문서보고 url 맞추기
    @POST("/login/signin")
    Call<LoginResult> checkLogin(@Body LoginInfo loginInfo);

<<<<<<< HEAD
    /*@GET("/schedule/showschedule")
    Call<> getSchedule();*/
=======
    @GET("/schedule/showschedule")
    Call<ScheduleResult> getSchedule();
>>>>>>> d11e152c8de84eceb37bc1387ab50a7c98894ce8

   /* @POST("/logout/logout")
    Call<LogoutResult> logout();

    @POST("/signup/up")
    Call<JoinResult> getJoinResult(@Body JoinInfo joinInfo);*/


}
