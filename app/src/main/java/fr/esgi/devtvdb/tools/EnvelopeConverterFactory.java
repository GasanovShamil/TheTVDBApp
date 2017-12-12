package fr.esgi.devtvdb.tools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import fr.esgi.devtvdb.entities.Wrapper;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by shgas on 12/12/2017.
 */


class EnvelopeConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Type envelopeType = Types.newParameterizedType(Wrapper.class, type);
        Converter<ResponseBody, Wrapper> delegate =
                retrofit.nextResponseBodyConverter(this, envelopeType, annotations);
        return new EnvelopeConverter(delegate);
    }
}
