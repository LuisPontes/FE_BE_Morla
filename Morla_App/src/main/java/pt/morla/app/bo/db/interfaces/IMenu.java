package pt.morla.app.bo.db.interfaces;

import java.util.List;

import pt.morla.app.bo.db.models.menu_obj;



public interface IMenu {

	public List<menu_obj> findAll();

	public Long save(menu_obj obj);

	public void remove(Long id);
}
