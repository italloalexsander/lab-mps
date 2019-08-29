package infra;

import business.model.Usuario;
import business.control.*;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;



public class Persistencia{
	
	public void salvaLista(ArrayList<Usuario> lista) throws ArquivoNaoEncontrado{
		
		try {

			File file = new File("Usuarios.dat");
			
            FileOutputStream saveFile = new FileOutputStream(file);

            ObjectOutputStream stream = new ObjectOutputStream(saveFile);

            
             // salva o objeto
            stream.writeObject(lista);

            stream.close();

          } catch (Exception e) {

        	  throw new ArquivoNaoEncontrado("Arquivo não encontrado. Um novo arquivo será inicializado");

          }	
	}
	
	public ArrayList<Usuario> carregaLista() throws ArquivoNaoEncontrado{
		
		try {
            File file = new File("Usuarios.dat");
            
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            
            ArrayList<Usuario> listaDeUsuarios = (ArrayList<Usuario>) input.readObject();
            
            input.close();
            
            return listaDeUsuarios;
		}
		catch(Exception e){
            throw new ArquivoNaoEncontrado("Arquivo não encontrado. Um novo arquivo será inicializado");
		}
		
	}
}
