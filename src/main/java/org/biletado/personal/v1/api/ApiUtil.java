package org.biletado.personal.v1.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiUtil {

    static ObjectMapper mapper = new ObjectMapper();



    public static void setStringResponse(NativeWebRequest req, String contentType, String string) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setEntityJsonResponse(NativeWebRequest req, Object entity) {
        try {
            setStringResponse(req, MediaType.APPLICATION_JSON_VALUE, mapper.writeValueAsString(entity));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
