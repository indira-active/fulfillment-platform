package com.indiraactive.fulfillmentplatform;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;

// TODO: Address security password appearing in log when initially booting server Prod-

@SpringBootApplication
@EnableOAuth2Sso
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}