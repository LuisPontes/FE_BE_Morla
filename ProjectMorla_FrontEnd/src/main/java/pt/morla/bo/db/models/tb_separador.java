package pt.morla.bo.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_separadores")
public class tb_separador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
	private long id;
    @Column(name="nome")
	private String nome;
    @Column(name="img")
	private String img;
    @Column(name="id_content")
	private String id_content;
    
    public tb_separador() {	}
    public tb_separador(String nome) {	this.nome=nome; }
    public tb_separador(long id) { this.id=id;	}
    public tb_separador(long id, String nome,String img,String content) {	this.id=id;this.nome=nome;this.img=img;this.id_content=content;}
    

	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getId_content() {
		return id_content;
	}
	public void setId_content(String id_content) {
		this.id_content = id_content;
	}
	@Override
	public String toString() {
		return "tb_separadores [id=" + id + ", nome=" + nome + ", img=" + img + ", id_content=" + id_content + "]";
	}
	
	
	
}
