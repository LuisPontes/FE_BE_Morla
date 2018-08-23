package pt.morla.bo.db.interfaces;

import java.util.List;

import pt.morla.bo.db.models.tb_content;

public interface IContents {

	public List<tb_content> findAll();

	public Long save(tb_content new_cat_obj);

	public void remove(Long id);
	
	public int updateActiveFlag(Long long1,int active);
}
