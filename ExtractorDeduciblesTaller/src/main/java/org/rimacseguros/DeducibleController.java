package org.rimacseguros;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/taller", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
public class DeducibleController {

    private final JsonService jsonService = new JsonService();

    public DeducibleController(JsonService jsonService) {
    }

    @PostMapping("/procesarDeducible")
    public Response procesarDeducibleRequest(@RequestBody Request request) {
            Response responseJson = new Response();
            responseJson= jsonService.generateResponse(request);
            return responseJson;
    }



}

