package pt.morla.bo.db.interfaces;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.morla.bo.db.models.tb_content;


@Repository
public interface ContentsRepository extends CrudRepository<tb_content, Long>{

	@Modifying
	@Query("update tb_content s set s.active_flag = :active_flag where s.id = :id")
	@Transactional
	public int updateActiveFlag(@Param("id") Long id,@Param("active_flag") Integer active_flag);
}
