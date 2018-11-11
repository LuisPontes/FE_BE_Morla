package pt.morla.app.bo.db.interfaces;

import java.util.List;

import pt.morla.app.bo.db.models.projectos_tb;

public interface IProjectos {

	public List<projectos_tb> findAll();

	public Long save(projectos_tb new_cat_obj);

	public void remove(Long id);
	
	public int updateActiveFlag(Long long1,int active);
	
	public List<projectos_tb> findAllActive();
}
