package business.control;


import business.model.Usuario;

import java.util.ArrayList;

import infra.Persistencia;

public class GerenciaUsuario {

	private final static int MAX_LOGIN = 12,
			 MAX_SENHA = 16,
			 MIN_SENHA = 8,
			 VAZIO = 0;
	
	private ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	
	Persistencia p = new Persistencia();
	
	
	public boolean validaLogin(Usuario usuario) throws EspacoVazio, CaracteresMaximos, TemNumero {
		
		if(usuario.getLogin().length() == VAZIO) {
			
			throw new EspacoVazio("Login não pode ser deixado em branco!");
			
		
		}else if(usuario.getLogin().length() > MAX_LOGIN) {
			throw new CaracteresMaximos("Login excedeu o número maximo (15) de caracteres!");
		
		}else if(usuario.getLogin().matches(".*\\d.*")) {
			
			throw new TemNumero("Login não pode conter números!");
			
		}else {
			return true;
		}
		
	}
	
	public boolean validaSenha(Usuario usuario) throws CaracteresMaximos, CaracteresMinimos, PossuiNumero, PossuiLetras{
		
		if(usuario.getSenha().length() > MAX_SENHA) {
			throw new CaracteresMaximos("A senha excedeu o número máximo (18) de caractes!");
		
		}else if(usuario.getSenha().length() < MIN_SENHA) {
			throw new CaracteresMinimos("A senha não atingiu a quantidade mínima (6) de caracteres!");
		
		}else if(!usuario.getSenha().matches(".*\\d.*\\d.*")){
			throw new PossuiNumero("A senha deve possuir pelo menos 2 números!");
		
		}else if(!usuario.getSenha().matches("(.*)[a-z|A-Z](.*)")) {
			throw new PossuiLetras("A senha deve possuir letras!");
		
		}
		
		return true;
	}
	
	public void adicionaUsuario(Usuario usuario) {
		
		listaDeUsuarios.add(new Usuario(usuario.getLogin(), usuario.getSenha()));
		
		System.out.printf("\nO usuario '%s' foi adicionado com sucesso \n", usuario.getLogin());
		
		
	}
	
	public void listaDeUsuarios() {
		
		System.out.printf("\nTamanho da lista de usuarios: %d\n" , listaDeUsuarios.size());
		
		for (int i = 0; i < listaDeUsuarios.size(); i++) {
			Usuario u = (Usuario) listaDeUsuarios.get(i);
			
			System.out.printf("\nLogin do Usuario %d: %s", i+1, u.getLogin());
			System.out.printf("\nSenha do Usuario %d: %s", i+1, u.getSenha());
		}
	}
	
	
	public boolean removeUsuario(String login) {
			
		for (int i = 0; i < listaDeUsuarios.size(); i++) {
				
			Usuario u = (Usuario) listaDeUsuarios.get(i);
				
				
			if (login.equals(u.getLogin())) {
				return listaDeUsuarios.remove(u);
			}
		}
			
		return false;
	}
	
	public void salvaListaEmArquivo() throws ArquivoNaoEncontrado{
		
		try {
			p.salvaLista(listaDeUsuarios);
		}catch(ArquivoNaoEncontrado e) {
			throw e;
		}
		
	}
	
	public void leLista() throws ArquivoNaoEncontrado{
		
		try {
			listaDeUsuarios = p.carregaLista();
		}catch(ArquivoNaoEncontrado e) {
			throw e;
		}	
	
	}
}
