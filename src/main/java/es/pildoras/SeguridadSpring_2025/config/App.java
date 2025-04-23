package es.pildoras.SeguridadSpring_2025.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Hello world!
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="es.pildoras.SeguridadSpring_2025")
@PropertySource("classpath:persistencia-mysql.properties")
public class App {
	
	@Autowired
	private Environment env;
	//en la variable env se guardará todas las propiedades de nuestro archivo propiedades
	
	
	//-------Ssitema de log para revisiones
	private Logger miLogger=Logger.getLogger(getClass().getName());
   
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	//definirun bean para seguridad
	
	@Bean
	public DataSource seguridadDataSource() {
		
		//crear el poll de conexiones
		ComboPooledDataSource seguridadDataSource=new ComboPooledDataSource();
		
		//establecer el driver JDBC
		
		try {
			seguridadDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//hacer log de las propiedades de conexion
		
		miLogger.info("URL de la base de datos: "+env.getProperty("jdbc.url"));
		
		System.out.println("URL de la base de datos: "+ env.getProperty("jdbc.url"));
		
		miLogger.info("Usuario conectado a DDBB: "+env.getProperty("jdbc.user"));
		
		System.out.println("Usuario conectado a DDBB: "+env.getProperty("jdbc.user"));
		
		//establecer las propiedades de conexion con DDBB
		
		seguridadDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		seguridadDataSource.setUser(env.getProperty("jdbc.user"));
		seguridadDataSource.setPassword(env.getProperty("jdbc.password"));
		
		System.out.println("LA CC conectado a DDBB: "+env.getProperty("jdbc.password"));
		
		//establecer las propeidades del poll de conexiones
		
		seguridadDataSource.setInitialPoolSize(getPropPool("connection.pool.initialPoolSize"));
		
		seguridadDataSource.setMinPoolSize(getPropPool("connection.pool.minPoolSize"));
		
		seguridadDataSource.setMaxPoolSize(getPropPool("connection.pool.maxPoolSize"));
		
		System.out.println("max pool size deberia ser 20 y es: "+getPropPool("connection.pool.maxPoolSize"));
		
		seguridadDataSource.setMaxIdleTime(getPropPool("connection.pool.maxIdleTime"));
		
		
		return seguridadDataSource;
		
	}
	
	private int getPropPool(String nombreProp) {
		
		String propVal=env.getProperty(nombreProp);
		
		int propPool=Integer.parseInt(propVal);
		
		return propPool;
		
	}
	
}
