package com.milaboratory.mitoola.util.json;


import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Streaming json I/O operations, memory friendly
 *
 * @author Alexei Zakharov (ayza)
 */
class StreamingJsonUtils {

    private ObjectMapper mapper;

    StreamingJsonUtils(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    <T> void writeArray(Iterable<T> values, File file) {
        writeArray(values.iterator(), file);
    }

    <T> void writeArray(Iterator<T> values, File file) {
        try (OutputStream output = new FileOutputStream(file)) {
            writeArray(values, output);
        } catch (IOException e) {
            throw new UncheckedIOException("Exception while opening file for writing: " + file.getAbsolutePath(), e);
        }
    }

    <T> void writeArray(Iterator<T> values, OutputStream output) {
        MappingJsonFactory factory = createJsonFactory();
        try {
            writeArrayInternal(values, factory.createGenerator(output, JsonEncoding.UTF8));
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to write JSON stream", e);
        }
    }

    <T> void writeArray(Iterator<T> values, Writer writer) {
        MappingJsonFactory factory = createJsonFactory();
        try {
            writeArrayInternal(values, factory.createGenerator(writer));
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to write JSON stream", e);
        }
    }

    <T> void writeArrayInternal(Iterator<T> values, JsonGenerator generator) throws IOException {
        generator.writeStartArray();
        toStream(values).forEach(obj -> {
            try {
                generator.writeObject(obj);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
        generator.writeEndArray();
        generator.flush();
    }

    <T> Iterator<T> readArray(File file, Class<T> valueClass) {
        try {
            InputStream input = new FileInputStream(file);
            return readArray(input, valueClass);
        } catch (IOException e) {
            throw new UncheckedIOException("Exception while opening json file " + file.getAbsolutePath(), e);
        }
    }

    <T> Iterator<T> readArray(InputStream input, Class<T> valueClass) {
        MappingJsonFactory factory = createJsonFactory();
        try {
            JsonParser parser = factory.createParser(input);
            return readArrayInternal(parser, valueClass);
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to create json parser from stream", e);
        }
    }

    <T> Iterator<T> readArray(Reader reader, Class<T> valueClass) {
        MappingJsonFactory factory = createJsonFactory();
        try {
            JsonParser parser = factory.createParser(reader);
            return readArrayInternal(parser, valueClass);
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to create json parser from stream", e);
        }
    }

    /**
     * Returns iterator of lazily parsed values
     */
    <T> Iterator<T> readArrayInternal(JsonParser parser, Class<T> valueClass) {
        T firstValue;
        try {
            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Json array expected, found: " + parser.getCurrentToken().asString());
            }
            parser.nextToken();
            if (parser.getCurrentToken() == JsonToken.END_ARRAY) {
                // empty array
                parser.close();
                return new ArrayList<T>().iterator();
            }
            firstValue = parser.readValueAs(valueClass);
        } catch (IllegalStateException e) {
            try {
                parser.close();
            } catch (IOException ex) {
            }
            throw e;
        } catch (IOException e) {
            try {
                parser.close();
            } catch (IOException ex) {
            }
            throw new UncheckedIOException("Unable to read from json stream", e);
        }

        return new JsonArrayReaderIterator<>(parser, valueClass, firstValue);
    }

    class JsonArrayReaderIterator<T> implements Iterator<T> {
        private final JsonParser parser;
        private Class<T> valueClass;
        private T currentValue;

        public JsonArrayReaderIterator(JsonParser parser, Class<T> valueClass, T firstValue) {
            this.parser = parser;
            this.valueClass = valueClass;
            this.currentValue = firstValue;
        }

        @Override
        public boolean hasNext() {
            return currentValue != null;
        }

        @Override
        public T next() {
            if (parser.isClosed()) {
                throw new NoSuchElementException("Parser is closed");
            }

            T oldCurrentValue = currentValue;
            try {
                if (parser.getCurrentToken() == JsonToken.END_ARRAY) {
                    parser.close();
                    currentValue = null;
                } else {
                    currentValue = parser.readValueAs(valueClass);
                }
            } catch (JsonProcessingException e) {
                try {
                    parser.close();
                } catch (IOException e2) {
                }
                throw new IllegalStateException("Error while parsing json stream", e);
            } catch (IOException e) {
                try {
                    parser.close();
                } catch (IOException e2) {
                }
                throw new UncheckedIOException("Unable to read from json stream", e);
            }
            return oldCurrentValue;
        }
    }

    private MappingJsonFactory createJsonFactory() {
        return new MappingJsonFactory(mapper);
    }

    static <T> Stream<T> toStream(Iterator<T> iterator) {
        return StreamSupport.stream(toIterable(iterator).spliterator(), false);
    }

    static <T> Iterable<T> toIterable(Iterator<T> iterator) {
        return () -> iterator;
    }
}

