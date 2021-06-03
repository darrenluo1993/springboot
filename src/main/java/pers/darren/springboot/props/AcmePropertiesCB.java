package pers.darren.springboot.props;

import java.net.InetAddress;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.Getter;
import lombok.ToString;

/**
 * 通过构造函数给成员变量绑定属性值
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jun 3, 2021 2:57:35 PM
 */
@Getter
@ToString
@ConfigurationProperties("acme")
public class AcmePropertiesCB {

    private final boolean enabled;

    private final InetAddress remoteAddress;

    private final Security security;

    @ConstructorBinding
    public AcmePropertiesCB(final boolean enabled, final InetAddress remoteAddress, final Security security) {
        this.enabled = enabled;
        this.remoteAddress = remoteAddress;
        this.security = security;
    }

    @Getter
    @ToString
    public static class Security {

        private final String username;

        private final String password;

        private final List<String> roles;

        public Security(final String username, final String password, @DefaultValue("USER") final List<String> roles) {
            this.username = username;
            this.password = password;
            this.roles = roles;
        }
    }
}