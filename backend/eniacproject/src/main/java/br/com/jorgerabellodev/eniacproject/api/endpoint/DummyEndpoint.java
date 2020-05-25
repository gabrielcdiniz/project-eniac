package br.com.jorgerabellodev.eniacproject.api.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class DummyEndpoint {

  @GetMapping
  public ResponseEntity<?> hello() {
    return new ResponseEntity<>("It Works !", HttpStatus.OK);
  }

}
