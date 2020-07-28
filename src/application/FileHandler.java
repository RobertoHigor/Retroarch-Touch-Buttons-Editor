package application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {
	private List<String> conteudoArquivo;
	
	public FileHandler()
	{
		File arquivo = new File("src\\\\application\\img\\teste.cfg");
		
		try {
			conteudoArquivo = new ArrayList<>(Files.readAllLines(arquivo.toPath(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Retornar uma lista de imagens
	public List<Imagem> retornarImagens()
	{
		List<Imagem> imagens = new ArrayList<>();
		Matcher matcherOverlay;
		Matcher matcherDesc;
		Matcher matcherImage;
		
		Pattern patternOverlay = Pattern.compile("desc(\\d|\\d\\d)\\Soverlay\\s=\\s");
		Pattern patternDescriptor = Pattern.compile("desc(\\d|\\d\\d)\\s=\\s");
		Pattern patternImageValues = Pattern.compile("(?<=,)[^,\\\"rect]+");
		
		// Para cada linha do arquivo
		for (int linha = 0; linha < conteudoArquivo.size(); linha++)
		{
			String conteudoLinha = conteudoArquivo.get(linha);
			matcherOverlay = patternOverlay.matcher(conteudoLinha);
			matcherDesc = patternDescriptor.matcher(conteudoLinha);
			
			
			// Se for uma descrição de overlay
			if (matcherOverlay.find())
			{
				//System.out.println("Achou overlay na linha: " + conteudoLinha);
				int indiceAposMatch = matcherOverlay.end();				
				System.out.println(conteudoLinha.substring(indiceAposMatch, conteudoLinha.length()));
			// Senão, se for uma descrição de coordenada
			} else if (matcherDesc.find())
			{
				matcherImage = patternImageValues.matcher(conteudoLinha);
				List<Float> listaAtributos= new ArrayList<>();
				//Enquanto houver coordenada
				while (matcherImage.find())
				{					
					//System.out.println(matcherImage.group());
					listaAtributos.add(Float.parseFloat(matcherImage.group()));					
				}
				
				imagens.add(atribuirImagem(listaAtributos));				
			}
		}
		
		// ler até achar descx
			// Se for descx_overlay
				// adicionar a string depois do "=" como nome da imgem
			//Senão
				// Pegar os valores entre as vírgulas
				// 2 e 3 são posição
				// 5 e 6 são tamanho
		return null;
	}
	
	// Atribuir os valores da lista para as propriedades do objeto Imagem
	private Imagem atribuirImagem(List<Float> listaAtributos)
	{
		Imagem img = new Imagem();
		img.setPosX(listaAtributos.get(0));
		img.setPosY(listaAtributos.get(1));
		img.setRangeX(listaAtributos.get(2));
		img.setRangeX(listaAtributos.get(3));
		
		return img;
	}
	
	public static void main(String[] args) {
		FileHandler file = new FileHandler();
		file.retornarImagens();
	}

}
