package pt.morla.app.bo.db.interfaces;

import java.util.List;

import pt.morla.app.bo.db.models.user_obj;

public interface IUsers {

	public List<user_obj> findAll();

	public Long save(user_obj new_cat_obj);

	public void remove(Long id);
	
	public List<user_obj> findByName(String name);

	public int update(Long id, 
			String nome,
			String img_backGround, 
			String cor_backGround, 
			int orderView, 
			String url, 
			int active,
			String typeContent);
}
