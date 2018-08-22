package pt.morla.bo.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_content")
public class tb_content {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "categoria_id")
	private String categoria_id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "sinopse")
	private String sinopse;
	@Column(name = "ano")
	private String ano;
	@Column(name = "coreografia")
	private String coreografia;
	@Column(name = "interpretacao")
	private String interpretacao;
	@Column(name = "musica")
	private String musica;
	@Column(name = "figurinos")
	private String figurinos;
	@Column(name = "edicaoMusical")
	private String edicaoMusical;
	@Column(name = "fotografia")
	private String fotografia;
	@Column(name = "video")
	private String video;
	@Column(name = "duracao")
	private String duracao;
	@Column(name = "classE")
	private String classE;
	@Column(name = "ap_publicas")
	private String ap_publicas;
	@Column(name = "agradecimentos")
	private String agradecimentos;
	@Column(name = "option1")
	private String option1;
	@Column(name = "option2")
	private String option2;
	@Column(name = "active")
	private int active_flag;

	@Transient
	private String active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(String categoria_id) {
		this.categoria_id = categoria_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getCoreografia() {
		return coreografia;
	}

	public void setCoreografia(String coreografia) {
		this.coreografia = coreografia;
	}

	public String getInterpretacao() {
		return interpretacao;
	}

	public void setInterpretacao(String interpretacao) {
		this.interpretacao = interpretacao;
	}

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public String getFigurinos() {
		return figurinos;
	}

	public void setFigurinos(String figurinos) {
		this.figurinos = figurinos;
	}

	public String getEdicaoMusical() {
		return edicaoMusical;
	}

	public void setEdicaoMusical(String edicaoMusical) {
		this.edicaoMusical = edicaoMusical;
	}

	public String getFotografia() {
		return fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getClassE() {
		return classE;
	}

	public void setClassE(String classE) {
		this.classE = classE;
	}

	public String getAp_publicas() {
		return ap_publicas;
	}

	public void setAp_publicas(String ap_publicas) {
		this.ap_publicas = ap_publicas;
	}

	public String getAgradecimentos() {
		return agradecimentos;
	}

	public void setAgradecimentos(String agradecimentos) {
		this.agradecimentos = agradecimentos;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void mappingActive() {
		if (this.active.equals("on")) {
			this.active_flag = 1;
		} else {
			this.active_flag = 0;
		}
	}

	@Override
	public String toString() {
		return "tb_content [id=" + id + ", categoria_id=" + categoria_id + ", titulo=" + titulo + ", sinopse=" + sinopse
				+ ", ano=" + ano + ", coreografia=" + coreografia + ", interpretacao=" + interpretacao + ", musica="
				+ musica + ", figurinos=" + figurinos + ", edicaoMusical=" + edicaoMusical + ", fotografia="
				+ fotografia + ", video=" + video + ", duracao=" + duracao + ", classE=" + classE + ", ap_publicas="
				+ ap_publicas + ", agradecimentos=" + agradecimentos + ", option1=" + option1 + ", option2=" + option2
				+ "]";
	}

}
