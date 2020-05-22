package br.com.jorgerabellodev.eniacproject.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DummyEndpointTest {

  @Test
  public void GivenARequestToHelloEndpointShouldAnswerWithItWorksAndOKHttpStatus() {
    DummyEndpoint dummyEndpoint = new DummyEndpoint();
    ResponseEntity response = dummyEndpoint.hello();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("It Works !", response.getBody());
  }

}
