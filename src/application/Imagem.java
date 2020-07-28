package application;

public class Imagem {

	private String nome;
	private float posX;
	private float posY;
	private float rangeX;
	private float rangeY;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPosX() {
		return posX * 1280;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY * 720;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public float getRangeX() {
		return rangeX;
	}
	public void setRangeX(float rangeX) {
		this.rangeX = rangeX;
	}
	public float getRangeY() {
		return rangeY;
	}
	public void setRangeY(float rangeY) {
		this.rangeY = rangeY;
	}
	
	
}
