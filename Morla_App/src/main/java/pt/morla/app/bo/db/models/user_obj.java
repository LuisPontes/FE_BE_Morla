package pt.morla.app.bo.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_tb")
public class user_obj {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "pass")
	String pass;
	
	@Transient
	String passOld;
	
	@Transient
	boolean isauth;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean isIsauth() {
		return isauth;
	}
	public void setIsauth(boolean isauth) {
		this.isauth = isauth;
	}
	public String getPassOld() {
		return passOld;
	}
	public void setPassOld(String passOld) {
		this.passOld = passOld;
	}
	
	
	

}
