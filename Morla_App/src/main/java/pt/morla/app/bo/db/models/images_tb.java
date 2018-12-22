package pt.morla.app.bo.db.models;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images_tb")
public class images_tb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "image",columnDefinition="BLOB")
	private byte[] image;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "descricao")
	private String descricao;

	private String encode_to_str_Img;
	
	
	public String getEncode_to_str_Img() {
		return encode_to_str_Img;
	}

	public void setEncode_to_str_Img(String encode_to_str_Img) {
		this.encode_to_str_Img = encode_to_str_Img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "images_tb [id=" + id + ", image=" + Arrays.toString(image) + ", titulo=" + titulo + ", autor=" + autor
				+ ", descricao=" + descricao + ", encode_to_str_Img=" + encode_to_str_Img + "]";
	}
	
	
	
}
