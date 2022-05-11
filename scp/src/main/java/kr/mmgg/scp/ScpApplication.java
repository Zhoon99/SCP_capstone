package kr.mmgg.scp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import kr.mmgg.scp.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ScpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScpApplication.class, args);
	}
}
