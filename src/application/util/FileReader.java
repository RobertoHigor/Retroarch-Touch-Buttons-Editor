package application.util;

import java.util.List;

import application.model.Imagem;

public interface FileReader {

	public void setConfig(String cfg);
	public List<Imagem> retornarImagens();
}
