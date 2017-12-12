package fr.esgi.devtvdb.tools;

import java.io.IOException;

import fr.esgi.devtvdb.entities.Wrapper;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by shgas on 12/12/2017.
 */
class EnvelopeConverter<T> implements Converter<ResponseBody, T> {
    final Converter<ResponseBody, Wrapper<T>> delegate;

    EnvelopeConverter(Converter<ResponseBody, Wrapper<T>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        Wrapper<T> envelope = delegate.convert(responseBody);
        return envelope.data;
    }
}
