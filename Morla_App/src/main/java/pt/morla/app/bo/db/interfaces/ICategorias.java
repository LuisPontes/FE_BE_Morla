package pt.morla.app.bo.db.interfaces;

import java.util.List;

import pt.morla.app.bo.db.models.categorias_tb;

public interface ICategorias {

	public List<categorias_tb> findAll();

	public Long save(categorias_tb new_cat_obj);

	public void remove(Long id);
	
	public int updateActiveFlag(Long long1,int active);
	
	public List<categorias_tb> findAllActive();

	public int update(Long id, 
			String nome,
			String img_backGround, 
			String cor_backGround, 
			int orderView, 
			String url, 
			int active,
			String typeContent);
}
