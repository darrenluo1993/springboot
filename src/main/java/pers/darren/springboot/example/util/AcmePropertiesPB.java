package pers.darren.springboot.example.util;

import static java.util.Collections.singleton;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@ConfigurationProperties("acme")
public class AcmePropertiesPB {
	@Getter
	@Setter
	private boolean enabled;
	@Getter
	@Setter
	private InetAddress remoteAddress;
	@Getter
	private final Security security = new Security();
	@Getter
	private final List<MyPojo> list = new ArrayList<>();
	@Getter
	private final Map<String, MyPojo> map = new HashMap<>();

	@Getter
	@Setter
	@ToString
	public static class Security {

		private String username;

		private String password;

		private List<String> roles = new ArrayList<>(singleton("USER"));
	}

	@Getter
	@Setter
	@ToString
	public static class MyPojo {

		private String name;

		private String description;
	}
}