package br.com.jorgerabellodev.eniacproject.api.config.profiles;

import br.com.jorgerabellodev.eniacproject.api.services.DBService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    @Autowired
    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public Boolean instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;
    }
}
