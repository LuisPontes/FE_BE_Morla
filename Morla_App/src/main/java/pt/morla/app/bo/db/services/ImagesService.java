package pt.morla.app.bo.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.morla.app.bo.db.interfaces.IImages;
import pt.morla.app.bo.db.interfaces.ImagesRepository;
import pt.morla.app.bo.db.models.images_tb;

@Service
public class ImagesService implements IImages{

	@Autowired
    private ImagesRepository repository;

	@Override
	public List<images_tb> findAll() {
		List<images_tb> result = (List<images_tb>) repository.findAll();
        return result;
	}

	@Override
	public Long save(images_tb obj) {
		images_tb newObj = repository.save(obj);
		return newObj.getId();
	}

	@Override
	public void remove(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
