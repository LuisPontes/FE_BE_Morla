package pt.morla.app.bo.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.morla.app.bo.db.interfaces.CategoriasRepository;
import pt.morla.app.bo.db.interfaces.ICategorias;
import pt.morla.app.bo.db.models.categorias_tb;

@Service
public class CategoriasService implements ICategorias{

	@Autowired
    private CategoriasRepository repository;
	
	@Override
	public List<categorias_tb> findAll() {
		List<categorias_tb> result = (List<categorias_tb>) repository.findAll();
        return result;
	}

	@Override
	public Long save(categorias_tb new_cat_obj) {
		categorias_tb newObj = repository.save(new_cat_obj);
		return newObj.getId();
	}

	@Override
	public void remove(Long id) {
		repository.deleteById(id);
	}

	@Override
	public int updateActiveFlag(Long long1, int active) {
		return repository.updateActiveFlag(long1, active);
	}

	@Override
	public List<categorias_tb> findAllActive() {
		return repository.findAllActive();
	}

}
