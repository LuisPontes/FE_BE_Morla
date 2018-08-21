package pt.morla.bo.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.morla.bo.db.interfaces.ISeparadores;
import pt.morla.bo.db.interfaces.SeparadorRepository;
import pt.morla.bo.db.models.tb_separador;

@Service
public class SeparadorService implements ISeparadores{

	@Autowired
    private SeparadorRepository repository;
	
	@Override
	public List<tb_separador> findAll() {
		List<tb_separador> result = (List<tb_separador>) repository.findAll();
        return result;
	}

	@Override
	public Long save(tb_separador new_cat_obj) {
		tb_separador newObj = repository.save(new_cat_obj);
		return newObj.getId();
	}

	@Override
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
