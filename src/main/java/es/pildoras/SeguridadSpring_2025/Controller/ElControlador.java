package es.pildoras.SeguridadSpring_2025.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ElControlador {

	
	@GetMapping("/")
	public String muestraInicio() {
		
		return "inicio";
	}
	
	
	//agregamos mapping a admninistradores
	@GetMapping("/administradores")
	public String muestraAdministradores() {
		
		return "administradores";
	}
	
	@GetMapping("/ayudantes")
	public String muestraAyudantes() {
		
		return "ayudantes";
	}
	
	@GetMapping("/acceso-denegado")
	public String muestraAccesoDenegado() {
		
		return "acceso-denegado";
	}
}
