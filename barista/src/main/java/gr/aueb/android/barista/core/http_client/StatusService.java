package gr.aueb.android.barista.core.http_client;



import gr.aueb.android.barista.core.dto.SizeDto;
import gr.aueb.android.barista.core.model.Command;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Deprecated
public interface StatusService {

    /**
     * Returns plain text formated message
     * @return
     */
    @Deprecated
    @GET("status")
    Call<String> getStatusMessage();

    /**
     * Returns JSON formated message
     * @return
     */
    @Deprecated
    @GET("status2")
    Call<String> getStatusMessage2();


    /**
     * kills the server container
     */
    @Deprecated
    @GET("kill")
    Call<String> killServer();

    @Deprecated
    @POST("echo")
    Call<String> echoMessage(@Body String originalMsg);

    @Deprecated
    @GET("setDimension")
    Call<String> resizeScreen(@Query("token") String token,@Query("width") String width, @Query("height") String height);

    @Deprecated
    @GET("reset")
    Call<String> resetSize(@Query("token") String token);

    @Deprecated
    @GET("geofix")
    Call<String> setGeoFix(@Query("token") String token,@Query("lat") double lat, @Query("longt") double longt);

    @Deprecated
    @GET("actualSize")
    Call<SizeDto> getActualSize(@Query("token") String token);

    @POST("execute")
    Call<String> executeCommand(@Body Command command);

}
