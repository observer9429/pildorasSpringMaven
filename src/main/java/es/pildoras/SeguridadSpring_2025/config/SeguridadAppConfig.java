package es.pildoras.SeguridadSpring_2025.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Configuration
@EnableWebSecurity
public class SeguridadAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource seguridadDataSource;//mirará el metodo inyectado
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		UserBuilder usuarios=User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser(usuarios.username("Juan").password("123").roles("usuario","administrador"))
		.withUser(usuarios.username("Peter").password("456").roles("usuario"))
		.withUser(usuarios.username("Gabriela").password("789").roles("usuario","ayudante"))
		.withUser(usuarios.username("Juaneco").password("666").roles("usuario","administrador"));
		*/
		
		//inyectar el metodo public DataSource seguridadDataSource
		//que es dodne obtenemos inf del pool de conexiones,user clave
		
		auth.jdbcAuthentication().dataSource(seguridadDataSource);
		//mirará el metodo inyectado atraves del AUTOWIRED
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Tseguridad en tipo login - logout
		
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin()
		http.authorizeRequests()
			.antMatchers("/").hasRole("USUARIO")
			.antMatchers("/administradores/**").hasRole("ADMINISTRADOR")
			.antMatchers("/ayudantes/**").hasRole("AYUDANTE")
		.and().formLogin()
			.loginPage("/miFormularioLogin")
			.loginProcessingUrl("/autenticacionUsuario")
			.permitAll()
		.and()
			.logout()
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/acceso-denegado");
		
	}

	
	
}
