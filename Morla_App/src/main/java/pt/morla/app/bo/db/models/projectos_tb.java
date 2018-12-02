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

	@Column(name = "descricao",length =50000)
	private String descricao;

	@Column(name = "img_capa")
	private String img_capa;

	@Column(name = "ficha_tecnica",length =50000)
	private String ficha_tecnica;

	@Column(name = "foto_galeria")
	private String foto_galeria;

	@Column(name = "video_galeria")
	private String video_link;

	@Column(name = "active")
	private Integer active_flag;

	@Column(name = "lastUpdate")
	private Date lastUpdate;

	@Column(name = "categoria_id")
	private String categoria_id;

	@Column(name = "orderView")
	private Integer orderView;

	@Column(name = "facebook")
	private String facebook;

	@Column(name = "linkeDin")
	private String linkeDin;

	@Column(name = "twitter")
	private String twitter;

	@Column(name = "instagram")
	private String instagram;

	@Column(name = "email")
	private String email;

	@Column(name = "telemovel")
	private String telemovel;

	@Transient
	private String active;

	@Transient
	private MultipartFile[] fileDatas;

	@Transient
	private String[] listPathsFotoGaleria;

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

	public String getImg_capa() {
		return img_capa;
	}

	public void setImg_capa(String img_capa) {
		this.img_capa = img_capa;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLinkeDin() {
		return linkeDin;
	}

	public void setLinkeDin(String linkeDin) {
		this.linkeDin = linkeDin;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(String telemovel) {
		this.telemovel = telemovel;
	}
	public String[] getListPathsFotoGaleria() {
		return listPathsFotoGaleria;
	}

	public void setListPathsFotoGaleria(String[] split) {
		this.listPathsFotoGaleria = split;
	}

	public String getVideo_link() {
		return video_link;
	}

	public void setVideo_link(String video_link) {
		this.video_link = video_link;
	}

	public void mappingActive() {
		if (this.active == null) {
			this.active_flag = 1;
		} else if (this.active.equals("on")) {
			this.active_flag = 1;
		} else {
			this.active_flag = 0;
		}
	}

	@Override
	public String toString() {
		return "projectos_tb [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", img_capa=" + img_capa
				+ ", ficha_tecnica=" + ficha_tecnica + ", foto_galeria=" + foto_galeria + ", video_link=" + video_link
				+ ", active_flag=" + active_flag + ", lastUpdate=" + lastUpdate + ", categoria_id=" + categoria_id
				+ ", orderView=" + orderView + ", facebook=" + facebook + ", linkeDin=" + linkeDin + ", twitter="
				+ twitter + ", instagram=" + instagram + ", email=" + email + ", telemovel=" + telemovel + ", active="
				+ active + ", fileDatas=" + Arrays.toString(fileDatas) + ", listPathsFotoGaleria="
				+ Arrays.toString(listPathsFotoGaleria) + "]";
	}


}
