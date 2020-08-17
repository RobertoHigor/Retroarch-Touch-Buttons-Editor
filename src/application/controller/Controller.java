package application.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Imagem;
import application.util.ConfigFileReader;
import application.util.FileReader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Controller implements Initializable { // Para carregar a imagem assim que abrir

	public Pane imagePane;
	private float width;
	private float height;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//width = (float) imagePane.getPrefWidth();
		width = 1280;
		height = 720;
		//height = (float) imagePane.getPrefHeight();
		AdicionarImagens(imagePane);
	}  
	
	private void AdicionarImagens(Pane pane)
	{
		FileReader config = new ConfigFileReader();
		config.setConfig("teste");
		List<Imagem> imagens = config.retornarImagens();		
		
		addBackground(pane);
		
		for (int i = 0; i < imagens.size(); i++)
		{
			// img pnel
			ImageView imageView = new ImageView();
			Imagem tmpImg = imagens.get(i);
			
			String imgName = tmpImg.getNome();
			
			System.out.println("Nome: "+imgName
					+" RangeX: "+tmpImg.getRangeX()
					+" RangeY: "+tmpImg.getRangeY());
			
			Image image = new Image(
					new File("src/application/img/"+imgName).toURI().toString());
			
			setImageViewAttributes(imageView, tmpImg, image);
					
			pane.getChildren().add(imageView);
		}	

	}

	private void addBackground(Pane pane) {
		// Criar ImageView da imagem
		ImageView background = new ImageView();
		Image backgroundImage = new Image(new File("src/application/img/background.png").toURI().toString());
		background.setImage(backgroundImage);
		background.setFitWidth(width);
		background.setFitHeight(height);

		pane.getChildren().add(background);
	}

	// Get the Imagem class attributes and put it on the ImageView
	private void setImageViewAttributes(ImageView imageView, Imagem tmpImg, Image image) {
		imageView.setImage(image);
		
		// Set Image Attributes
		float imgHeight = (height * 2) * tmpImg.getRangeY();
		float imgWidth = (width * 2) * tmpImg.getRangeX();
		imageView.setFitHeight(imgHeight);	
		imageView.setFitWidth(imgWidth);
		
		System.out.println(image.getHeight() + (image.getHeight() * tmpImg.getRangeY()));
		
		// Getting center point to use as a pivot
		float centerX = (float) (imgWidth / 2);
		float centerY = (float) (imgHeight / 2);
		
		imageView.setX(converterCoordenada(tmpImg.getPosX(), 'x', centerX));
		imageView.setY(converterCoordenada(tmpImg.getPosY(), 'y', centerY));
	}
	
	private float converterCoordenada(float coordenada, char tipo, double center)
	{	
		if (tipo=='x')
		{
			return (float) ((coordenada * width) - center);
		}
		if (tipo=='y')
		{
			return (float) ((coordenada * height) - center);
		}
		
		return 100f;
	}
 }