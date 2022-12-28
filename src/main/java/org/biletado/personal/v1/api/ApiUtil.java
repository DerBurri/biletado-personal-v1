package org.biletado.personal.v1.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.request.NativeWebRequest;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiUtil {
    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(example);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setResponseFromModel(NativeWebRequest req, String contentType, Object entity) throws JsonProcessingException
    {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            ObjectMapper mapper = new ObjectMapper();
            String response = mapper.writeValueAsString(entity);
            res.getWriter().print(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
