package pers.darren.springboot;

import static org.springframework.boot.Banner.Mode.CONSOLE;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ConfigurableApplicationContext;

import com.mysql.cj.jdbc.MysqlDataSource;

import lombok.extern.slf4j.Slf4j;
import pers.darren.springboot.config.WebReportConfig;
import pers.darren.springboot.example.util.ComponentProvider.AnotherComponent;
import pers.darren.springboot.props.AcmePropertiesCB;
import pers.darren.springboot.props.AcmePropertiesMCB;
import pers.darren.springboot.props.AcmePropertiesPB;
import pers.darren.springboot.props.JDBCInfo;

@Slf4j
@SpringBootApplication
public class SpringbootApplication {

    public static void main(final String[] args) {
        // SpringApplication.run(SpringbootApplication.class, args);

        // final SpringApplication application = new
        // SpringApplication(SpringbootApplication.class);
        // application.setBannerMode(OFF);
        // application.run(args);

        final ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
                SpringbootApplication.class).listeners(event -> {
                    if (event instanceof ApplicationStartingEvent) {
                        log.info("Spring application is starting!");
                    }
                }, event -> {
                    if (event instanceof ApplicationEnvironmentPreparedEvent) {
                        log.info("Spring application environment is prepared!");
                    }
                }, event -> {
                    if (event instanceof ApplicationPreparedEvent) {
                        log.info("Spring application is prepared!");
                    }
                }, event -> {
                    if (event instanceof ApplicationStartedEvent) {
                        log.info("Spring application is started!");
                    }
                }, event -> {
                    if (event instanceof ApplicationReadyEvent) {
                        log.info("Spring application is ready!");
                    }
                }).bannerMode(CONSOLE).lazyInitialization(true).logStartupInfo(true).run(args);

        log.info(applicationContext.getBean(JDBCInfo.class).toString());
        log.info(applicationContext.getBean(AcmePropertiesPB.class).toString());
        log.info(applicationContext.getBean(AcmePropertiesCB.class).toString());
        log.info(applicationContext.getBean(AcmePropertiesMCB.class).toString());
        log.info(applicationContext.getBean("acme-pers.darren.springboot.props.AcmePropertiesPB").toString());
        log.info(applicationContext.getBean(AnotherComponent.class).toString());
        final MysqlDataSource source = applicationContext.getBean(MysqlDataSource.class);
        log.info(source.getServerName() + source.getPort() + source.getDatabaseName() + source.getUrl());
        log.info(applicationContext.getBean(WebReportConfig.class).toString());

        try {
            Thread.sleep(5000);
        } catch (final InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        // log.info("Exit code>>>" + SpringApplication.exit(applicationContext));
    }
}