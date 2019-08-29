package view;

import java.util.Scanner;

import business.control.*;

import business.model.Usuario;


public class Interface {

	
	public static void main(String args[]) {
		
		boolean loginValido = false;
		boolean senhaValida = false;
		boolean continua = true;
		
		int decisao = 1; //Variavel que auxiliara quando o programa tiver que ser terminado
		
		Scanner s = new Scanner(System.in);
		Usuario usuario = new Usuario();
		GerenciaUsuario gerencia = new GerenciaUsuario();
		
		String loginRemovido = "";
		
		try {
			gerencia.leLista();
		}catch(ArquivoNaoEncontrado e) {
			System.out.println("\n" + e.getMessage());
		}
		
		
		System.out.println("\nLista de usuários em arquivo: ");
		gerencia.listaDeUsuarios();
		
		while(continua) {
			
			loginValido = false;
			senhaValida = false;
			
			System.out.println("\n\nO que voce deseja fazer? 1-Adicionar usuario 2-Excluir usuario 3-Fechar o programa");
			
			decisao = s.nextInt();
			s.nextLine();
			
			if (decisao == 1) {
				
				//Enquanto não for introduzido um login valido, o programa continua
				// solicitando a inserção de um login
				while (!loginValido) {
					
					System.out.println("\nDigite seu Login: ");
					
					usuario.setLogin(s.nextLine());
					
					try {
						loginValido = gerencia.validaLogin(usuario);
					}
					catch(EspacoVazio e) {
						System.out.println("\nLogin invalido! " + e.getMessage() + " Digite novamente!");
					}
					catch(CaracteresMaximos e) {
						System.out.println("\nLogin invalida! " + e.getMessage() + " Digite novamente!");
					}
					catch(TemNumero e) {
						System.out.println("\nLogin invalida! " + e.getMessage() + " Digite novamente!");
					}
					catch(Exception e) {
						System.out.println("\nOcorreu um comportamento inesperado! Digite o login novamente!");
					}
					
				}
				
				//Enquanto não for introduzido uma senha valida, o programa continua
				// solicitando a inserção de uma senha
				while(!senhaValida) {
					
					System.out.println("\nDigite sua senha: ");
					
					usuario.setSenha(s.nextLine());
					
					try {
						senhaValida = gerencia.validaSenha(usuario);
					}
					catch(CaracteresMaximos e) {
						System.out.println("\nSenha invalida! " + e.getMessage() + " Digite novamente!");
					}
					catch(CaracteresMinimos e) {
						System.out.println("\nSenha invalida! " + e.getMessage() + " Digite novamente!");
					}
					catch(PossuiNumero e) {
						System.out.println("\nSenha invalida! " + e.getMessage() + " Digite novamente!");
					}
					catch(PossuiLetras e) {
						System.out.println("\nSenha invalida! " + e.getMessage() + " Digite novamente!");
					}
					catch(Exception e) {
						System.out.println("\nOcorreu um comportamento inesperado! Digite a senha novamente!");
					}
				}
				
				//Quando forem introduzidos login e senha válidos, o usuário é cadastrado
				gerencia.adicionaUsuario(usuario);
				
			}
			
			else if (decisao == 2) {
				System.out.println("\nDigite o login do usuario que voce deseja excluir: ");
				loginRemovido = s.nextLine();
				
				if (gerencia.removeUsuario(loginRemovido)) {
					System.out.println("\nUsuario removido: " + loginRemovido);
				}
				
				else{
					System.out.println("\nUsuario nao foi removido");
				}
			}
			
			else if (decisao == 3){
				
				System.out.println("\nLista de usuários em arquivo: ");
				gerencia.listaDeUsuarios();
				
				System.out.println("\nAté mais!");
				try {
					gerencia.salvaListaEmArquivo();
				}catch(ArquivoNaoEncontrado e) {
					System.out.println("\n" + e.getMessage());
				}
				
				continua = false;
			}
			
			else {
				System.out.println("\nOpcao invalida");
			}
		}
		
	}
	
}
