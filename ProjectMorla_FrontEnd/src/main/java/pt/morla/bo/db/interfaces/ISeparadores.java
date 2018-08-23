package pt.morla.bo.db.interfaces;

import java.util.List;

import pt.morla.bo.db.models.tb_separador;

public interface ISeparadores {

	public List<tb_separador> findAll();

	public Long save(tb_separador new_cat_obj);

	public void remove(Long id);
	
	public int updateActiveFlag(Long long1,int active);
}
