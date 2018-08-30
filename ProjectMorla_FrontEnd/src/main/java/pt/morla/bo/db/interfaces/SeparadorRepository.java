package pt.morla.bo.db.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.morla.bo.db.models.tb_separador;


@Repository
public interface SeparadorRepository extends CrudRepository<tb_separador, Long>{

	@Modifying
	@Query("update tb_separador s set s.active_flag = :active_flag where s.id = :id")
	@Transactional
	public int updateActiveFlag(@Param("id") Long id,@Param("active_flag") Integer active_flag);
	
	@Query(value = "SELECT u FROM tb_separador u where u.active_flag = 1")
	List<tb_separador> findAllActive();
}
