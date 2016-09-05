package nl.rabobank.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"nl.rabobank.register"})
public class WebModule extends WebMvcConfigurerAdapter {
//    @Override
//    public void addViewControllers(ViewControllerRegistry aRegistry) {
//        aRegistry.addViewController("/").setViewName("loginform");
//        aRegistry.
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry aRegistry) {
        aRegistry.addResourceHandler("/s/*").addResourceLocations("classpath:/webapp/WEB-INF/view/scripts/*");
        aRegistry.addResourceHandler("/c/*").addResourceLocations("classpath:/webapp/WEB-INF/view/css/*");
        aRegistry.addResourceHandler("/i/*").addResourceLocations("classpath:/webapp/WEB-INF/view/images/*");
        aRegistry.addResourceHandler("/WEB-INF/view/*").addResourceLocations(
            "classpath:/webapp/WEB-INF/view/*");

    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
