package my.simple.chatbot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {

    @Bean
    public MessageSource messageSource() {

        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath://messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
