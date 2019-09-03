package gr.aueb.android.barista.core.http_client;


import java.util.List;


import gr.aueb.android.barista.core.model.CommandDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *  An interface tht specifies how the Barista Server REST API is designed
 *  in order to be used by the Retrofit Client.
 *
 */
public interface BaristaPluginService{

    @POST("execute")
    Call<ResponseBody> executeCommand(@Body CommandDTO command);

    @POST("executeAll")
    Call<ResponseBody> executeCommand(@Body List<CommandDTO> commands);

    @GET("kill")
    Call<String> killServer();

    @GET("activate")
    Call<String> activate();
}