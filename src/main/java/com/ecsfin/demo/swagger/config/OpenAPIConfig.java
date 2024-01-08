package com.ecsfin.demo.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
		info = @Info(
				title = "Demo-Swagger-App",
				description = "Swagger Learning",
				version = "1.0.0",
				contact = @Contact(
							email = "vibin@ecsfin.com",
							name = "Vibin Krishnan",
							url = "https://www.ecsfin.com"
						),
				license = @License(name = "Copyright © 2023 ECS Fin. All Rights Reserved."),
				termsOfService = "https://www.ecsfin.com/contact-us/"
				)
		)
@SecurityScheme(
		name = "BearerAuth",
		description = "JWT Auth Description",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
		)
public class OpenAPIConfig {

}
