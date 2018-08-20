package pt.morla.bo.db.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.morla.bo.db.models.tb_separador;


@Repository
public interface SeparadorRepository extends CrudRepository<tb_separador, Long>{

	//public tb_separadores findSeparador(String nome);
}
