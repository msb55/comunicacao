package model;

public class Musica {
	
	private String nome;
	private String album;
	private String artista;	
	
	public Musica(String nome, String album, String artista) {
		this.nome = nome;
		this.album = album;
		this.artista = artista;
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}

}
