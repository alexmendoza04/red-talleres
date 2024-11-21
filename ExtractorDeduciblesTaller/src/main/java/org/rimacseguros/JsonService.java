package org.rimacseguros;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JsonService {

    private ObjectMapper objectMapper;

    public JsonService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    public Response generateResponse(Request request) {
        List<Deducible> deducibles = DeducibleProcessor.extractDeducibles(request.getPayload().getText(),request.getPayload().getCode());
        Response response = new Response();
        response.setPayload(deducibles);
        return response;
    }
}


