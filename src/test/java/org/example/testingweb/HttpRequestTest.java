package org.example.testingweb;

import org.example.AddressBook;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @Autowired
    private ServerProperties serverProperties;

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void defaultGetHomePage() throws Exception {
        HttpEntity<AddressBook> request = new HttpEntity<>(new AddressBook(1));
        String url = "http://localhost:" + port + "/addressRestBook/";
        ResponseEntity<AddressBook[]> response = restTemplate
                .exchange(url, HttpMethod.GET, null, AddressBook[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
