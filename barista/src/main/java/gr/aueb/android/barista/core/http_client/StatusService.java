package gr.aueb.android.barista.core.http_client;



import gr.aueb.android.barista.core.http_client.dto.SizeDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("setDimension")
    Call<String> resizeScreen(@Query("token") String token,@Query("width") String width, @Query("height") String height);

    @GET("reset")
    Call<String> resetSize(@Query("token") String token);

    @GET("geofix")
    Call<String> setGeoFix(@Query("token") String token,@Query("lat") double lat, @Query("longt") double longt);

    @GET("actualSize")
    Call<SizeDto> getActualSize(@Query("token") String token);

}
