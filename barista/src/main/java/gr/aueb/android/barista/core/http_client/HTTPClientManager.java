package gr.aueb.android.barista.core.http_client;

import gr.aueb.android.barista.core.utilities.BaristaConfiguration;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HTTPClientManager {

    private static BaristaClient httpClient = null;

    public static void initialize(String endpoint){
        if(httpClient == null){
            BaristaConfiguration baristaConfiguration = BaristaConfiguration.getInstance();
            httpClient = new DefaultBaristaRetrofitClient(endpoint,
                    baristaConfiguration.getPort(),
                    JacksonConverterFactory.create());
        }
    }

    public static BaristaClient getInstance(){
        return httpClient;
    }
}
