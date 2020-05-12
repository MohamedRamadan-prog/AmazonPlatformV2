package amazon.layer;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

	@Autowired
	ServletContext ServletContext;

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/products").addResourceLocations(ServletContext.getRealPath("/products"));
		registry.addResourceHandler("/images/**").
				addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/users").addResourceLocations(ServletContext.getRealPath("/users"));
	}
}