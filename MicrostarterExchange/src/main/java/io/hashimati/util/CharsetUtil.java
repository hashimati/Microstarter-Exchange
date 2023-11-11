package io.hashimati.util;

import java.nio.ByteBuffer;
import java.util.Base64;

import com.oracle.svm.core.util.Utf8;
import jakarta.inject.Singleton;

@Singleton
public class CharsetUtil {

    private String convertToBase64(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    private String convertFromBase64(String text){
        return new String(Base64.getDecoder().decode(text));
    }

    public String converToUTF8(String text, boolean zeroTerminate)
    {
        return new String(Utf8.stringToUtf8(text, zeroTerminate));
    }

    public   String convertFromUTF8(String text, boolean zeroTerminate) {
        return Utf8.utf8ToString(zeroTerminate, ByteBuffer.wrap(text.getBytes()));
    }
}
