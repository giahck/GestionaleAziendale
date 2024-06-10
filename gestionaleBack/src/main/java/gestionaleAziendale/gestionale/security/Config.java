package project.epic_energy_back.security;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class Config {

   /* @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
      //  configuration.setAllowCredentials(true);
       // configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(http -> http.disable());
        httpSecurity.csrf(http -> http.disable());
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.cors(Customizer.withDefaults());

        //permette l'accesso a tutti dei servizi con endpoint /api/users e metodi get (naturalmente dopo l'autenticazione)
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/api/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers( "/auth/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers( "/anagrafe/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/indirizzo/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/clienti/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/utenti/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/fatture/**").permitAll());
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/indirizzi/**").permitAll());

        //nega l'accesso a qualsiasi servizio che non sia get e path /api/users
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").denyAll());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

  /*  @Bean//permette di abilitare l'accesso al servizio anche da parte di server diversi da quello su cui risiede
    //il servizio. In questo caso ho abilitato tutti i server ad accedere a tutti i servizi
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }*/



    @Bean
    public Cloudinary getCloudinary(@Value("${cloudinary.name}") String name,
                                    @Value("${cloudinary.apikey}") String apikey,
                                    @Value("${cloudinary.secret}") String secret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("api_key", apikey);
        config.put("api_secret", secret);
        return new Cloudinary(config);

    }

   /* @Bean
    public JavaMailSenderImpl getJavaMailSender(@Value("${gmail.mail.transport.protocol}" )String protocol,
                                                @Value("${gmail.mail.smtp.auth}" ) String auth,
                                                @Value("${gmail.mail.smtp.starttls.enable}" )String starttls,
                                                @Value("${gmail.mail.debug}" )String debug,
                                                @Value("${gmail.mail.from}" )String from,
                                                @Value("${gmail.mail.from.password}" )String password,
                                                @Value("${gmail.smtp.ssl.enable}" )String ssl,
                                                @Value("${gmail.smtp.host}" )String host,
                                                @Value("${gmail.smtp.port}" )String port){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));

        mailSender.setUsername(from);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);
        props.put("smtp.ssl.enable",ssl);

        return mailSender;

    }*/
}