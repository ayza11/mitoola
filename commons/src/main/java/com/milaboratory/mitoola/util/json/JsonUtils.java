package com.milaboratory.mitoola.util.json;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;



/**
 * @author Alexei Zakharov (ayza)
 */
public class JsonUtils {

    private static final DefaultJsonMapper MAPPER = new DefaultJsonMapper();
    private static final StreamingJsonUtils STREAMING_UTILS = new StreamingJsonUtils(MAPPER);

    public static String write(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void write(Object object, @NotNull File file) {
        //noinspection ConstantConditions
        Validate.notNull(file, "file is null");
        try {
            MAPPER.writeValue(file, object);
        } catch (IOException e) {
            throw new IllegalStateException("Error write to file - " + file.getAbsolutePath() + ":" + e.getMessage(), e);
        }
    }

    public static void write(Object object, @NotNull OutputStream stream) {
        //noinspection ConstantConditions
        Validate.isTrue(stream != null, "stream is invalid");
        try {
            MAPPER.writeValue(stream, object);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void write(Object object, @NotNull Writer stream) {
        //noinspection ConstantConditions
        Validate.isTrue(stream != null, "writer is invalid");
        try {
            MAPPER.writeValue(stream, object);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static <T> void write(@NotNull Iterator<T> iterator, @NotNull Writer writer) {
        //noinspection ConstantConditions
        Validate.isTrue(iterator != null, "iterator is null");
        //noinspection ConstantConditions
        Validate.isTrue(writer != null, "writer is null");
        STREAMING_UTILS.writeArray(iterator, writer);
    }

    public static <T> void write(@NotNull Iterator<T> iterator, @NotNull OutputStream stream) {
        //noinspection ConstantConditions
        Validate.isTrue(iterator != null, "iterator is null");
        //noinspection ConstantConditions
        Validate.isTrue(stream != null, "stream is null");
        STREAMING_UTILS.writeArray(iterator, stream);
    }

    public static <T> void write(@NotNull Iterator<T> iterator, @NotNull File file) {
        //noinspection ConstantConditions
        Validate.isTrue(iterator != null, "iterator is null");
        //noinspection ConstantConditions
        Validate.isTrue(file != null, "file is null");
        STREAMING_UTILS.writeArray(iterator, file);
    }

    public static <VALUE> VALUE read(String json, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, valueClass);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static <VALUE> VALUE read(@NotNull File file, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(file != null, "file is null");
        try {
            return MAPPER.readValue(file, valueClass);
        } catch (IOException e) {
            throw new IllegalStateException("Error read from file - " + file.getAbsolutePath() + ":" + e.getMessage(), e);
        }
    }

    public static <VALUE> VALUE read(@NotNull URL url, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(url != null, "url is invalid");
        try {
            return MAPPER.readValue(url, valueClass);
        } catch (IOException e) {
            throw new IllegalStateException("Error read from url - " + url.toString() + ":" + e.getMessage(), e);
        }
    }

    public static <VALUE> VALUE read(@NotNull InputStream stream, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(stream != null, "stream is invalid");
        try {
            return MAPPER.readValue(stream, valueClass);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static <VALUE> VALUE read(@NotNull Reader reader, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(reader != null, "reader is invalid");
        try {
            return MAPPER.readValue(reader, valueClass);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @NotNull
    public static <VALUE> List<VALUE> readList(String json, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        if (json == null) {
            return Collections.emptyList();
        }
        try {
            List<VALUE> result = MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, valueClass));
            return result != null ? result : Collections.<VALUE>emptyList();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @NotNull
    public static <VALUE> List<VALUE> readList(@NotNull File file, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(file != null, "file is null");
        try {
            List<VALUE> result = MAPPER.readValue(file, MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, valueClass));
            return result != null ? result : Collections.<VALUE>emptyList();
        } catch (IOException e) {
            throw new IllegalStateException("Error read from file - " + file.getAbsolutePath() + ":" + e.getMessage(), e);
        }
    }

    @NotNull
    public static <VALUE> List<VALUE> readList(@NotNull URL url, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(url != null, "url is invalid");
        try {
            List<VALUE> result = MAPPER.readValue(url, MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, valueClass));
            return result != null ? result : Collections.<VALUE>emptyList();
        } catch (IOException e) {
            throw new IllegalStateException("Error read from url - " + url.toString() + ":" + e.getMessage(), e);
        }
    }

    @NotNull
    public static <VALUE> List<VALUE> readList(@NotNull InputStream stream, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(stream != null, "stream is invalid");
        try {
            List<VALUE> result = MAPPER.readValue(stream, MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, valueClass));
            return result != null ? result : Collections.<VALUE>emptyList();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @NotNull
    public static <VALUE> List<VALUE> readList(@NotNull Reader reader, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(reader != null, "reader is invalid");
        try {
            List<VALUE> result = MAPPER.readValue(reader, MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, valueClass));
            return result != null ? result : Collections.<VALUE>emptyList();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @NotNull
    public static <VALUE> Iterator<VALUE> getArrayIterator(@NotNull Reader reader, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(reader != null, "reader is null");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        return STREAMING_UTILS.readArray(reader, valueClass);
    }

    @NotNull
    public static <VALUE> Iterator<VALUE> getArrayIterator(@NotNull InputStream input, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(input != null, "input is null");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        return STREAMING_UTILS.readArray(input, valueClass);
    }

    public static <VALUE> void forEachArrayElement(@NotNull InputStream jsonArrayStream,
                                                   @NotNull Class<VALUE> valueClass,
                                                   @NotNull Consumer<VALUE> consumer)
    {
        //noinspection ConstantConditions
        Validate.isTrue(jsonArrayStream != null, "stream is invalid");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(consumer != null, "consumer is null");
        forEachArrayElementInternal(STREAMING_UTILS.readArray(jsonArrayStream, valueClass), consumer);
    }

    public static <VALUE> void forEachArrayElement(@NotNull Reader jsonArrayReader,
                                                   @NotNull Class<VALUE> valueClass,
                                                   @NotNull Consumer<VALUE> consumer)
    {
        //noinspection ConstantConditions
        Validate.isTrue(jsonArrayReader != null, "reader is invalid");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(consumer != null, "consumer is null");
        forEachArrayElementInternal(STREAMING_UTILS.readArray(jsonArrayReader, valueClass), consumer);
    }

    public static <VALUE> void forEachArrayElement(@NotNull File jsonArrayFile,
                                                   @NotNull Class<VALUE> valueClass,
                                                   @NotNull Consumer<VALUE> consumer)
    {
        //noinspection ConstantConditions
        Validate.isTrue(jsonArrayFile != null, "reader is invalid");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        //noinspection ConstantConditions
        Validate.isTrue(consumer != null, "consumer is null");
        forEachArrayElementInternal(STREAMING_UTILS.readArray(jsonArrayFile, valueClass), consumer);
    }

    private static <VALUE> void forEachArrayElementInternal(@NotNull Iterator<VALUE> elements, @NotNull Consumer<VALUE> consumer) {
        while (elements.hasNext()) {
            consumer.accept(elements.next());
        }
    }

    @NotNull
    public static <KEY, VALUE> Map<KEY, VALUE> readMap(String json, @NotNull Class<KEY> keyClass, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(keyClass != null, "key class is null");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        if (json == null) {
            return Collections.emptyMap();
        }
        try {
            Map<KEY, VALUE> result = MAPPER.readValue(json, MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, keyClass, valueClass));
            return result != null ? result : Collections.<KEY, VALUE>emptyMap();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @NotNull
    public static <KEY, VALUE> Map<KEY, VALUE> readMap(@NotNull File file, @NotNull Class<KEY> keyClass, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(file != null, "file is null");
        //noinspection ConstantConditions
        Validate.isTrue(keyClass != null, "key class is null");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        try {
            Map<KEY, VALUE> result = MAPPER.readValue(file, MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, keyClass, valueClass));
            return result != null ? result : Collections.<KEY, VALUE>emptyMap();
        } catch (IOException e) {
            throw new IllegalStateException("Error read from file - " + file.getAbsolutePath() + ":" + e.getMessage(), e);
        }
    }

    @NotNull
    public static <KEY, VALUE> Map<KEY, VALUE> readMap(@NotNull URL url, @NotNull Class<KEY> keyClass, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(url != null, "url is invalid");
        //noinspection ConstantConditions
        Validate.isTrue(keyClass != null, "key class is null");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        try {
            Map<KEY, VALUE> result = MAPPER.readValue(url, MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, keyClass, valueClass));
            return result != null ? result : Collections.<KEY, VALUE>emptyMap();
        } catch (IOException e) {
            throw new IllegalStateException("Error read from url - " + url.toString() + ":" + e.getMessage(), e);
        }
    }

    @NotNull
    public static <KEY, VALUE> Map<KEY, VALUE> readMap(@NotNull InputStream stream, @NotNull Class<KEY> keyClass, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(stream != null, "stream is invalid");
        //noinspection ConstantConditions
        Validate.isTrue(keyClass != null, "key class is null");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        try {
            Map<KEY, VALUE> result = MAPPER.readValue(stream, MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, keyClass, valueClass));
            return result != null ? result : Collections.<KEY, VALUE>emptyMap();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @NotNull
    public static <KEY, VALUE> Map<KEY, VALUE> readMap(@NotNull Reader reader, @NotNull Class<KEY> keyClass, @NotNull Class<VALUE> valueClass) {
        //noinspection ConstantConditions
        Validate.isTrue(reader != null, "reader is invalid");
        //noinspection ConstantConditions
        Validate.isTrue(keyClass != null, "key class is null");
        //noinspection ConstantConditions
        Validate.isTrue(valueClass != null, "value class is null");
        try {
            Map<KEY, VALUE> result = MAPPER.readValue(reader, MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, keyClass, valueClass));
            return result != null ? result : Collections.<KEY, VALUE>emptyMap();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static boolean validate(String json) {
        if (json == null) {
            return false;
        }
        try {
            final JsonParser parser = MAPPER.getFactory().createParser(json);
            //noinspection StatementWithEmptyBody
            while (parser.nextToken() != null) {
                // do nothing
            }
            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    static ObjectMapper getActiveMapper() {
        return MAPPER;
    }
}
