package pt.morla.bo.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.morla.bo.db.interfaces.ContentsRepository;
import pt.morla.bo.db.interfaces.IContents;
import pt.morla.bo.db.models.tb_content;

@Service
public class ContentsService implements IContents{

	@Autowired
    private ContentsRepository repository;
	
	@Override
	public List<tb_content> findAll() {
		List<tb_content> result = (List<tb_content>) repository.findAll();
        return result;
	}

	@Override
	public Long save(tb_content new_cat_obj) {
		tb_content newObj = repository.save(new_cat_obj);
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
	public List<tb_content> findAllActive() {
		return repository.findAllActive();
	}

}
