package business.control;

import java.lang.Exception;

public class UsuarioInexistente extends Exception{
	
	public UsuarioInexistente(String message) {
		super(message);
	}
}
