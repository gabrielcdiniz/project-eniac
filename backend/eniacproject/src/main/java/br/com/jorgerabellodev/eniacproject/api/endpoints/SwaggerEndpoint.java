package br.com.jorgerabellodev.eniacproject.api.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/")
public class SwaggerEndpoint {

    @GetMapping
    public RedirectView getSwaggerLink() {
        return new RedirectView("/swagger-ui.html");
    }

}
