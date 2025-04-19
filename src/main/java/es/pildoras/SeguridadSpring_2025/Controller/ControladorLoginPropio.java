package es.pildoras.SeguridadSpring_2025.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorLoginPropio {
	
	@GetMapping("/miFormularioLogin")
	public String muestraLoginFormulario() {
		
		//return "login-propio";
		return "login_nuevo";
	}

}
