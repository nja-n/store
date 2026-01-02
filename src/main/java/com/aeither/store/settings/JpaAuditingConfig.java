package com.aeither.store.settings;

import com.aeither.store.common.domain.AuthenticationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider(AuthenticationContext authenticationContext) {
        return () -> Optional.ofNullable(authenticationContext.getCurrentUsername());
    }
}
