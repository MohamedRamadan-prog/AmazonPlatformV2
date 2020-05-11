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
		System.out.println("heeeeeeeey " + ServletContext.getRealPath("/products"));
		registry.addResourceHandler("/products").addResourceLocations(ServletContext.getRealPath("/products"));
		registry.addResourceHandler("/users").addResourceLocations(ServletContext.getRealPath("/users"));
	}
}