package pers.darren.springboot.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.darren.springboot.example.model.Product;

import java.math.BigDecimal;

@Configuration
public class ConfigurationBeanCase implements ApplicationContextAware, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationBeanCase.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean("Product")
    public Product getProduct() {
        final var product = new Product();
        product.setSupplier("供应商");
        product.setName("产品名称");
        return product;
    }

    @Bean("FullProduct")
    public Product getFullProduct() {
        // 调用getProduct方法时，
        // 如果Spring容器中已经存在Product实例，则会直接从Spring容器中引用，不会产生新的实例；
        // 如果Spring容器中还不存在Product实例，则会执行方法内容进行实例化，并将其注入至Spring容器中，然后返回给调用者；
        // 为何会是上述描述的这样？其原因在于当前类在被Spring容器接管后，会给当前类生成代理类，然后此类所有的行为都由代理类完成；
        // 代理类可以对当前类的所有行为进行环绕控制，比如在类方法执行前、执行中、执行后等各个阶段进行切入，然后控制方法的执行流程。
        final var product = this.getProduct();
        product.setPrice(new BigDecimal(100));
        product.setQuantity(1000);
        product.setDescription("产品描述");
        return product;
    }

    /*** 从测试结果看，使用@PostConstruct注解的方法要早于afterPropertiesSet方法执行 ***/

    @Override
    public void afterPropertiesSet() throws Exception {
        // 以下两行代码从Spring应用上下文中获取到的Product Bean是同一个实例
        logger.info("Product=" + this.applicationContext.getBean("Product", Product.class));
        logger.info("FullProduct=" + this.applicationContext.getBean("FullProduct", Product.class));
    }

    @PostConstruct
    private void printProduct() {
        // 以下两行代码从Spring应用上下文中获取到的Product Bean是同一个实例
        logger.info("Product=" + this.applicationContext.getBean("Product", Product.class));
        logger.info("FullProduct=" + this.applicationContext.getBean("FullProduct", Product.class));
    }
}