package pt.morla.app.bo.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.morla.app.bo.db.interfaces.IUsers;
import pt.morla.app.bo.db.interfaces.UsersRepository;
import pt.morla.app.bo.db.models.user_obj;

@Service
public class UsersService implements IUsers{

	@Autowired
    private UsersRepository repository;
	
	@Override
	public List<user_obj> findAll() {
		List<user_obj> result = (List<user_obj>) repository.findAll();
        return result;
	}

	@Override
	public Long save(user_obj new_cat_obj) {
		user_obj newObj = repository.save(new_cat_obj);
		return newObj.getId();
	}

	@Override
	public void remove(Long id) {
		repository.deleteById(id);
	}

	

	@Override
	public int update(
			Long id, 
			String nome,
			String img_backGround, 
			String cor_backGround, 
			int orderView, 
			String url, 
			int active,
			String typeContent
			) {
		return 0;

		/*return repository.update(
				id, 
				nome,
				img_backGround, 
				cor_backGround, 
				orderView, 
				url, 
				active,
				typeContent
				);
*/
	}

	@Override
	public List<user_obj> findByName(String name) {
		List<user_obj> list = repository.findByName(name);
		return list;
	}

}
