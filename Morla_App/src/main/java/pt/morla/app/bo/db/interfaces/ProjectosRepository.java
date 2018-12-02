package pt.morla.app.bo.db.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.morla.app.bo.db.models.projectos_tb;

@Repository
public interface ProjectosRepository extends CrudRepository<projectos_tb, Long>{

	@Modifying
	@Query("update projectos_tb s set s.active_flag = :active_flag where s.id = :id")
	@Transactional
	public int updateActiveFlag(@Param("id") Long id,@Param("active_flag") Integer active_flag);
	
	@Query(value = "SELECT u FROM projectos_tb u where u.active_flag = 1")
	List<projectos_tb> findAllActive();

	
	@Query(value = "SELECT u FROM projectos_tb u where u.id = :id")
	List<projectos_tb> findbyId(@Param("id") Long id);
	
	
}
