package pers.darren.springboot.example.util;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Validated
@ConfigurationProperties("jdbc")
public class JDBCInfo {

	@NotEmpty
	private String url;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private List<String> serverList;
}