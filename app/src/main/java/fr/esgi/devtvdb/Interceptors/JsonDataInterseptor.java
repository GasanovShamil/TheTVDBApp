package fr.esgi.devtvdb.Interceptors;

import android.util.Log;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by shgas on 14/12/2017.
 */

public class JsonDataInterseptor  implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            Response response = chain.proceed(request);
            JsonParser jParser = new JsonParser();
            JsonObject jObject = jParser.parse(response.body().string()).getAsJsonObject();

            String rawJson =  jObject.get("data").toString();

            // Re-create the response before returning it because body can be read only once
            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), rawJson)).build();
        }

}
