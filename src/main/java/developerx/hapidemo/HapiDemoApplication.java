package developerx.hapidemo;

import ca.uhn.fhir.context.FhirContext;
import developerx.springframework.HapiMessageConverterConfigurer;
import developerx.swagger.HapiSwaggerSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HapiDemoApplication {

	@Bean
	public FhirContext fhirContext() {
		return FhirContext.forR5();
	}

	@Bean
	public HapiMessageConverterConfigurer hapiMessageConverterConfigurer(FhirContext fhirContext) {
		return new HapiMessageConverterConfigurer(fhirContext);
	}

	@Bean
	public HapiSwaggerSupport hapiModelConverter() {
		return new HapiSwaggerSupport();
	}

	public static void main(String[] args) {
		SpringApplication.run(HapiDemoApplication.class, args);
	}

}
