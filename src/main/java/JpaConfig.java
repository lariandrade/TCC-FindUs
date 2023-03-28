import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.findus.repository")
public class JpaConfig {
    // Configurações do DataSource, EntityManagerFactory e outros beans
}