package pt.morla.bo.db.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.morla.bo.db.models.tb_content;


@Repository
public interface ContentsRepository extends CrudRepository<tb_content, Long>{

	//public tb_separadores findSeparador(String nome);
}
