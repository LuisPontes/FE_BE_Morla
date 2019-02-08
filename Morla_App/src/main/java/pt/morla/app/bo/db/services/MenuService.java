package pt.morla.app.bo.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.morla.app.bo.db.interfaces.IMenu;
import pt.morla.app.bo.db.interfaces.MenuRepository;
import pt.morla.app.bo.db.models.menu_obj;

@Service
public class MenuService implements IMenu{

	@Autowired
    private MenuRepository repository;

	@Override
	public List<menu_obj> findAll() {
		List<menu_obj> result = (List<menu_obj>) repository.findAll();
        return result;
	}

	@Override
	public Long save(menu_obj obj) {
		menu_obj newObj = repository.save(obj);
		return newObj.getId();
	}

	@Override
	public void remove(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
