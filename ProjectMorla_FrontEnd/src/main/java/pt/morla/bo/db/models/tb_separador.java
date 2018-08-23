package pt.morla.bo.db.models;

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
@Table(name = "tb_separadores")
public class tb_separador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "img")
	private String img;
	@Column(name = "lastUpdate")
	private Date lastUpdate;
	@Column(name = "url")
	private String url;
	@Column(name = "active")
	private Integer active_flag;

	@Transient
	private String active;
	@Transient
	private MultipartFile[] fileDatas;

	public tb_separador() {
	}

	public tb_separador(String nome) {
		this.nome = nome;
	}

	public tb_separador(Long id) {
		this.id = id;
	}

	public tb_separador(Long id, String nome, String img, Date lastUpdate) {
		this.id = id;
		this.nome = nome;
		this.img = img;
		this.lastUpdate = lastUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date date) {
		this.lastUpdate = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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
		return "tb_separador [id=" + id + ", nome=" + nome + ", img=" + img + ", fileDatas="
				+ Arrays.toString(fileDatas) + ", lastUpdate=" + lastUpdate + ", url=" + url + "]";
	}

}
