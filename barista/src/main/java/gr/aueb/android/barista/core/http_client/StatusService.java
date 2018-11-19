package gr.aueb.android.barista.core.http_client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StatusService {

    /**
     * Returns plain text formated message
     * @return
     */
    @GET("status")
    Call<String> getStatusMessage();

    /**
     * Returns JSON formated message
     * @return
     */
    @GET("status2")
    Call<String> getStatusMessage2();


    /**
     * kills the server container
     */
    @GET("kill")
    Call<String> killServer();

    @POST("echo")
    Call<String> echoMessage(@Body String originalMsg);

}
