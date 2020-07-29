package application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
			e.printStackTrace();
		}
	}
	
	// TODO Procurar classe Java para leitura de .cfg
	// Retornar uma lista de imagens
	public List<Imagem> retornarImagens()
	{
		List<Imagem> imagens = new ArrayList<>();
		List<String> nomeImagens = new ArrayList<>();
		Queue<Float> listaAtributos= new LinkedList<>();
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
				adicionarNomeImagem(nomeImagens, matcherOverlay, conteudoLinha);
			// Senão, se for uma descrição de coordenada
			} else if (matcherDesc.find())
			{
				adicionarAtributos(listaAtributos, patternImageValues, conteudoLinha);				
			}
		}
		
		criarImagens(imagens, nomeImagens, listaAtributos);
		
		return imagens;
	}


	private void criarImagens(List<Imagem> imagens, List<String> nomeImagens, Queue<Float> listaAtributos) {
		for (int i = 0; i < nomeImagens.size(); i++) {
			imagens.add(atribuirImagem(nomeImagens.get(i), listaAtributos));
		}
	}


	private void adicionarAtributos(Queue<Float> listaAtributos, Pattern patternImageValues, String conteudoLinha) {
		Matcher matcherImage;
		matcherImage = patternImageValues.matcher(conteudoLinha);				
		//Enquanto houver coordenada
		while (matcherImage.find())
		{				
			//System.out.println(matcherImage.group());
			listaAtributos.add(Float.parseFloat(matcherImage.group()));					
		}
	}


	private void adicionarNomeImagem(List<String> nomeImagens, Matcher matcherOverlay, String conteudoLinha) {
		//System.out.println("Achou overlay na linha: " + conteudoLinha);
		int indiceAposMatch = matcherOverlay.end();	
		nomeImagens.add("src\\application\\img\\"+conteudoLinha.substring(indiceAposMatch, conteudoLinha.length()));
		System.out.println(conteudoLinha.substring(indiceAposMatch, conteudoLinha.length()));
	}
	
	// TODO: Conseguir ler descrições fora de ordem
	// Atribuir os valores da lista para as propriedades do objeto Imagem
	private Imagem atribuirImagem(String nomeImagem, Queue<Float> listaAtributos)
	{
		Imagem img = new Imagem();
		img.setNome(nomeImagem);
		img.setPosX(listaAtributos.poll());
		img.setPosY(listaAtributos.poll());
		img.setRangeX(listaAtributos.poll());
		img.setRangeY(listaAtributos.poll());
		
		return img;
	}
	
	public static void main(String[] args) {
		FileHandler file = new FileHandler();
		file.retornarImagens();
	}

}
