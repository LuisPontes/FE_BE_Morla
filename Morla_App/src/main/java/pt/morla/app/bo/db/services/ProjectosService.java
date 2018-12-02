package pt.morla.app.bo.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.morla.app.bo.db.interfaces.IProjectos;
import pt.morla.app.bo.db.interfaces.ProjectosRepository;
import pt.morla.app.bo.db.models.projectos_tb;

@Service
public class ProjectosService implements IProjectos{

	@Autowired
    private ProjectosRepository repository;
	
	@Override
	public List<projectos_tb> findAll() {
		List<projectos_tb> result = (List<projectos_tb>) repository.findAll();
        return result;
	}

	@Override
	public Long save(projectos_tb new_cat_obj) {
		projectos_tb newObj = repository.save(new_cat_obj);
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
	public List<projectos_tb> findAllActive() {
		return repository.findAllActive();
	}

	@Override
	public List<projectos_tb> findbyId(Long id) {
		return repository.findbyId(id);
	}
	


}
