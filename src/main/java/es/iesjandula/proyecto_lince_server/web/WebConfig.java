package es.iesjandula.proyecto_lince_server.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author David Martinez Flores
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer
{
	/**
	 * Method addCorsMappings
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**");
	}
}