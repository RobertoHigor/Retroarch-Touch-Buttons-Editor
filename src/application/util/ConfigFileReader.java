package application.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.Main;
import application.model.Imagem;

public class ConfigFileReader implements FileReader {
	Properties configFile;
	List<Imagem> imagens;

	@Override
	public void setConfig(String cfg) {
		configFile = new Properties();
		try {
			FileInputStream file = new FileInputStream("D:/Arquivos Importantes/Desenvolvimento/Java/JavaFX/"
					+ "Retroarch-Touch-Buttons-Editor/src/application/img/" + cfg + ".cfg");
			configFile.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Imagem> retornarImagens() {
		// The list of <Imagem>
		imagens = new ArrayList<>();
		
		// Get the number of descs using the overlayx_descs value
		int descs = Integer.parseInt(this.configFile.getProperty("overlay0_descs"));
		
		for (int i = 0; i < descs; i++) {
			Imagem img = new Imagem();
			
			String descAttribute = this.configFile.getProperty("overlay0_desc"+i);
			String descImage = this.configFile.getProperty("overlay0_desc"+i+"_overlay");
			
			img = setImageAttributes(img, descAttribute);			
			img = setImageNames(img, descImage);
			imagens.add(img);
		}
		
		return imagens;
	}

	private Imagem setImageNames(Imagem img, String descImage) {
		img.setNome(descImage);
		return img;
		
	}

	private Imagem setImageAttributes(Imagem img, String descImage) {
		// Pattern to return only numeric values
		Pattern patternImageValues = Pattern.compile("(?<=,)[^,\\\"rect]+");
		Matcher matcherImage = patternImageValues.matcher(descImage);	
		
		// Add attributes values to the list
		List<Float> attributes = new ArrayList<>();
		while (matcherImage.find()) {
			attributes.add(Float.parseFloat(matcherImage.group()));
		}
		
		// Set Attributes
		img.setPosX(attributes.get(0));
		img.setPosY(attributes.get(1));
		img.setRangeX(attributes.get(2));
		img.setRangeY(attributes.get(3));
		//System.out.println(attributes);
		
		return img;
	}

	public static void main(String[] args) {
		ConfigFileReader cfg = new ConfigFileReader();
		cfg.setConfig("teste");
		List<Imagem> testeImagens = new ArrayList<>();
		testeImagens = cfg.retornarImagens();
		
		Imagem img = new Imagem();
		img = testeImagens.get(0);
		System.out.println(img.getNome());
	}

}
