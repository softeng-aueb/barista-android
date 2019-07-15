package gr.aueb.android.barista.core.http_client;


import org.mockito.internal.matchers.Null;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gr.aueb.android.barista.BuildConfig;
import gr.aueb.android.barista.core.model.CommandDTO;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;


public class DefaultBaristaRetrofitClient extends BaristaRetrofitClient{

    private final int DEFAULT_PORT = 8040;
    private Retrofit retrofit;

    /**
     * Construct an HTTP client in order to perform REST CALLS to the Barista Server
     *
     */
    public DefaultBaristaRetrofitClient(String BaseURL, int port, Converter.Factory converter){
       super(BaseURL,port,converter);

    }

    @Override
    public void activate(){
        BaristaPluginService service = getRequestClient().create(BaristaPluginService.class);
        Call<String> callSync = service.activate();
        Timber.d("REST CALL URI: %s",callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();

        } catch (IOException e) {
            Timber.e("Exception Occured. "+e.getMessage());
            callSync.cancel();
            e.printStackTrace();
            return;
        }
    }



    /**
     * Do a GET request to give signal to kill the server
     * fixme if server has stoped normally by  the gradle plugin. timeout exception will occur
     */
    public void killServer(){

        BaristaPluginService service = getRequestClient().create(BaristaPluginService.class);
        Call<String> callSync = service.killServer();
        Timber.d("REST CALL URI: %s",callSync.request().url().toString());

        try {
            Response<String> response = callSync.execute();
            Timber.d("Sucessfull Call with response: %s",response.message());

        } catch (IOException e) {
            Timber.e("Exception Occured. "+e.getMessage());
            callSync.cancel();
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void executeCommand(CommandDTO cmd) {
        if(cmd == null ){
            throw new NullCommandRequestException("Request to execute a null command.");
        }
        Timber.d("Calling: "+cmd.toString()+" for "+cmd.getSessionToken());
        BaristaPluginService service = getRequestClient().create(BaristaPluginService.class);
        Call<ResponseBody> callSync = service.executeCommand(cmd);
        Timber.d("Sending request: "+callSync.request().toString());

        callSync.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Timber.d("Sucessfull Call: "+response.code()+" - "+response.message()+" - "+response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Timber.d("Failed Call: "+t.getMessage());
            }

        });
    }

    @Override
    public void executeAllCommands(List<CommandDTO> commands) {

        Timber.d("Calling list of commands");
        BaristaPluginService service = getRequestClient().create(BaristaPluginService.class);
        Call<ResponseBody> callSync = service.executeCommand(commands);
        Timber.d("Sending request: "+callSync.request().toString());
        try {
           Response<ResponseBody> response = callSync.execute();
           Timber.d(response.message());
        } catch (IOException e) {

            Timber.d(e.getMessage());
            e.printStackTrace();
        }
//        callSync.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Timber.d("Sucessfull Call: "+response.code()+" - "+response.message()+" - "+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Timber.d("Failed Call: "+t.getMessage());
//            }
//
//        });

    }

    /**
     * Returns
     * @return
     */
    private Retrofit getRequestClient(){
        if(retrofit == null){
            this.create();
        }

        return retrofit;
    }

    /**
     *  Initializes the retrofit client instance to be used for the REST calls.
     *  By default it uses the Jackson Converter factory.
     */
    private void create(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5,TimeUnit.SECONDS)
                    .writeTimeout(5,TimeUnit.SECONDS);
            httpClient.addInterceptor(logging);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(URI)
                .addConverterFactory( JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

}
