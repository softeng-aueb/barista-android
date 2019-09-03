package gr.aueb.android.barista.core.http_client;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *  A Barista Http Client implementation that uses the Retrofit2 framework.
 *  All the commands are transfomed into JSON using the jackson converter factory.
 *
 *
 *
 */
public abstract class BaristaRetrofitClient implements  BaristaClient {

    private static BaristaRetrofitClient INSTANCE = null;


    private int port;
    private static Retrofit retrofit;
    protected static String URI ;
    private Converter.Factory converter;

    /**
     *
     * @param BaseURL The URL of the server
     * @param port The listening port of the server
     * @param converter The DTO -> Json Converter to be used
     */
    public  BaristaRetrofitClient(String BaseURL, int port, Converter.Factory converter){

        this.URI = BaseURL+":"+port+"/barista/";
        this.converter = converter;
        this.retrofit = getRequestClient();

    }

    private void create(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl(URI)
                .addConverterFactory( JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    private Retrofit getRequestClient(){
        if(retrofit == null){
            this.create();
        }
        return retrofit;
    }

}
