package com.nbs.iais.ms.meta.referential.api.config;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;


@Configuration
@Import(com.nbs.iais.ms.meta.referential.db.config.ApplicationConfig.class)
@IntegrationComponentScan(basePackageClasses = IAISGateway.class)
public class ApplicationConfig {
}
