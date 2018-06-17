package com.milaboratory.mitoola.util;

import com.milaboratory.mitoola.util.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;


import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;


/**
 * @author Alexei Zakharov (ayza)
 */
public class ValidateUtils {
    public static class RequestParam {
        public static void isTrue(boolean expression, String message) {
            if (!expression) {
                throw badRequestException(message);
            }
        }

        public static void notNull(Object param, String paramName) {
            if (param == null) {
                throw badRequestException(paramName + " is null");
            }
        }

        public static void notBlank(String param, String paramName) {
            if (StringUtils.isBlank(param)) {
                throw badRequestException(paramName + " is null or empty");
            }
        }
    }

    public static void checkExists(boolean expression, String message) {
        if (!expression) {
            throw notFoundException(message);
        }
    }


    public static BadRequestException badRequestException(String message) {
        ErrorResponseDto errorDto = new ErrorResponseDto(
                false,
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                message,
                Instant.now());
        throw new BadRequestException(message,
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(JsonUtils.write(errorDto))
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .build());
    }

    public static NotFoundException notFoundException(String message) {
        ErrorResponseDto errorDto = new ErrorResponseDto(
                false,
                Response.Status.NOT_FOUND.getStatusCode(),
                Response.Status.NOT_FOUND.getReasonPhrase(),
                message,
                Instant.now());
        throw new NotFoundException(message,
                Response.status(Response.Status.NOT_FOUND)
                        .entity(JsonUtils.write(errorDto))
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .build());
    }

}
