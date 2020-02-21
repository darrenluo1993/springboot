package pers.darren.springboot.example.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.cj.jdbc.MysqlDataSource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
public class ComponentProvider {

	@Bean
	@ConfigurationProperties("another")
	public AnotherComponent anotherComponent() {
		return new AnotherComponent();
	}

	@Bean
	@ConfigurationProperties("mysql.ds")
	public MysqlDataSource mysqlDataSource() {
		return new MysqlDataSource();
	}

	@Getter
	@Setter
	@ToString
	public static final class AnotherComponent {

		private String name;

		private String type;
	}
}