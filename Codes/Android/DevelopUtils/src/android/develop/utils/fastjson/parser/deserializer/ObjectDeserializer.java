package android.develop.utils.fastjson.parser.deserializer;

import java.lang.reflect.Type;

import android.develop.utils.fastjson.parser.DefaultJSONParser;

public interface ObjectDeserializer {
    <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName);
    
    int getFastMatchToken();
}
