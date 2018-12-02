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
@Table(name = "categorias_tb")
public class categorias_tb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "img_backGround")
	private String img_backGround;
	
	@Column(name = "cor_backGround")
	private String cor_backGround;
	
	@Column(name = "orderView")
	private Integer orderView;	
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "active")
	private Integer active_flag;

	@Column(name = "lastUpdate")
	private Date lastUpdate;
	
	@Column(name = "typeContent")
	private String type;

	@Transient
	private String slug;
	@Transient
	private String active;
	@Transient
	private MultipartFile[] fileDatas;
	
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

	public String getImg_backGround() {
		return img_backGround;
	}

	public void setImg_backGround(String img_backGround) {
		this.img_backGround = img_backGround;
	}

	public String getCor_backGround() {
		return cor_backGround;
	}

	public void setCor_backGround(String cor_backGround) {
		this.cor_backGround = cor_backGround;
	}

	public Integer getOrderView() {
		return orderView;
	}

	public void setOrderView(Integer orderView) {
		this.orderView = orderView;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
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
		return "categorias_tb [id=" + id + ", nome=" + nome + ", img_backGround=" + img_backGround + ", cor_backGround="
				+ cor_backGround + ", orderView=" + orderView + ", url=" + url + ", active_flag=" + active_flag
				+ ", lastUpdate=" + lastUpdate + ", type=" + type + ", slug=" + slug + ", active=" + active
				+ ", fileDatas=" + Arrays.toString(fileDatas) + "]";
	}

	
}
