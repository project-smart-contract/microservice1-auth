package ma.fstt.microservice1auth.restController;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/service1")
public class CalculatorRestController {


    @GetMapping("/add/{a}/{b}")
    public Double add(@PathVariable Double a , @PathVariable Double b){

        return  a + b ;
    }

    @GetMapping("/{msg}")
    public String Service1(@PathVariable String msg){

        return "Microservice 1 AFTER DOCKERFILE : "+ msg;
    }
}
