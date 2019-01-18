package pt.morla.app.bo.db.models;

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
	
//	@Column(name = "image",columnDefinition="LONGBLOB")
//	private byte[] image;
	
	@Column(name = "path_up")
	private String path_up;
	
	@Column(name = "path_show")
	private String path_show;
	
	@Column(name = "idFather")
	private Long idFather;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "descricao")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath_up() {
		return path_up;
	}

	public void setPath_up(String path_up) {
		this.path_up = path_up;
	}

	public String getPath_show() {
		return path_show;
	}

	public void setPath_show(String path_show) {
		this.path_show = path_show;
	}

	public Long getIdFather() {
		return idFather;
	}

	public void setIdFather(Long idFather) {
		this.idFather = idFather;
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
		return "images_tb [id=" + id + ", path_up=" + path_up + ", path_show=" + path_show + ", idFather=" + idFather
				+ ", titulo=" + titulo + ", autor=" + autor + ", descricao=" + descricao + "]";
	}

//	private String encode_to_str_Img;
	
	
	
	
	
}
