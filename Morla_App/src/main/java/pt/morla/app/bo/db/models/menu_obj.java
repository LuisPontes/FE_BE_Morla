package pt.morla.app.bo.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_obj")
public class menu_obj {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "type",columnDefinition="VARCHAR(255)")
	private String type;
	
	@Column(name = "textColor",columnDefinition="VARCHAR(255)")
	private String textColor;
	
	@Column(name = "fontFamily",columnDefinition="VARCHAR(255)")
	private String fontFamily;
	
	@Column(name = "textSize",columnDefinition="VARCHAR(255)")
	private String textSize;

	@Column(name = "fontWeight",columnDefinition="VARCHAR(255)")
	private String fontWeight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public String getTextSize() {
		return textSize;
	}

	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}

	public String getFontWeight() {
		return fontWeight;
	}

	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}

	@Override
	public String toString() {
		return "menu_obj [id=" + id + ", type=" + type + ", textColor=" + textColor + ", fontFamily=" + fontFamily
				+ ", textSize=" + textSize + ", fontWeight=" + fontWeight + "]";
	}
	

	

	

	
}
