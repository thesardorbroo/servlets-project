package config;

import entity.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Util {

    public static void sendResponse(String contentType, HttpServletResponse resp, Response<?> value) throws IOException {
        resp.setContentType(contentType);
        PrintWriter writer = resp.getWriter();
        writer.println(value);

        writer.close();

    }
}
