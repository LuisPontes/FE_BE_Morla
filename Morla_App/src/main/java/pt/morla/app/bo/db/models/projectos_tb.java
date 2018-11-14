package pt.morla.app.bo.db.models;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "projectos_tb")
public class projectos_tb {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;	
	
	@Column(name = "img_capa")
	private String img_capa;
	
	@Column(name = "ficha_tecnica")
	private String ficha_tecnica;
	
	@Column(name = "foto_galeria")
	private String foto_galeria;
	
	@Column(name = "video_galeria")
	private String video_galeria;
	
	@Column(name = "active")
	private Integer active_flag;
	
	@Column(name = "lastUpdate")
	private Date lastUpdate;
	
	@Column(name = "categoria_id")
	private String categoria_id;

	@Column(name = "orderView")
	private Integer orderView;	
	
	@Transient
	private String active;
	
	@Transient
	private MultipartFile[] fileDatas;
	
	@Transient
	private MultipartFile[] fileMovieDatas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFicha_tecnica() {
		return ficha_tecnica;
	}

	public void setFicha_tecnica(String ficha_tecnica) {
		this.ficha_tecnica = ficha_tecnica;
	}

	public String getFoto_galeria() {
		return foto_galeria;
	}

	public void setFoto_galeria(String foto_galeria) {
		this.foto_galeria = foto_galeria;
	}

	public String getVideo_galeria() {
		return video_galeria;
	}

	public void setVideo_galeria(String video_galeria) {
		this.video_galeria = video_galeria;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(String categoria_id) {
		this.categoria_id = categoria_id;
	}
	
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public MultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}

	public Integer getOrderView() {
		return orderView;
	}

	public void setOrderView(Integer orderView) {
		this.orderView = orderView;
	}

	public MultipartFile[] getFileMovieDatas() {
		return fileMovieDatas;
	}

	public void setFileMovieDatas(MultipartFile[] fileMovieDatas) {
		this.fileMovieDatas = fileMovieDatas;
	}
	
	

	public String getImg_capa() {
		return img_capa;
	}

	public void setImg_capa(String img_capa) {
		this.img_capa = img_capa;
	}

	public void mappingActive() {
		if ( this.active == null ) 
		{
			this.active_flag = 0;
		}
		else if (this.active.equals("on") ) 
		{
			this.active_flag = 1;
		} 
		else 
		{
			this.active_flag = 0;
		}
	}

	@Override
	public String toString() {
		return "Projectos_tb [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", ficha_tecnica="
				+ ficha_tecnica + ", foto_galeria=" + foto_galeria + ", video_galeria=" + video_galeria
				+ ", active_flag=" + active_flag + ", lastUpdate=" + lastUpdate + ", categoria_id=" + categoria_id
				+ ", active=" + active + ", fileDatas=" + Arrays.toString(fileDatas) + "]";
	}
	
	
	
}
