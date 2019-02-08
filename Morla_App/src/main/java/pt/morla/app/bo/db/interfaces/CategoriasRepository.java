package pt.morla.app.bo.db.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.morla.app.bo.db.models.categorias_tb;

@Repository
public interface CategoriasRepository extends CrudRepository<categorias_tb, Long>{

	@Modifying
	@Query("update categorias_tb s set s.active_flag = :active_flag where s.id = :id")
	@Transactional
	public int updateActiveFlag(@Param("id") Long id,@Param("active_flag") Integer active_flag);
	
	@Query(value = "SELECT u FROM categorias_tb u where u.active_flag = 1")
	List<categorias_tb> findAllActive();
	/*
	@Modifying
	@Query("update categorias_tb s "
			+ "set s.nome = :nome , "
			+ "s.img_backGround = :img_backGround, "
			+ "s.cor_backGround = :cor_backGround, "
			+ "s.orderView = :orderView, "
			+ "s.url = :url, "
			+ "s.active = :active, "
			+ "s.typeContent = :typeContent "
			+ "where s.id = :id")
	@Transactional
	public int update(
			@Param("id") Long id, 
			@Param("nome") String nome,
			@Param("img_backGround") String img_backGround, 
			@Param("cor_backGround") String cor_backGround, 
			@Param("orderView") Integer orderView, 
			@Param("url") String url, 
			@Param("active") Integer active,
			@Param("typeContent") String typeContent
			);
			*/
}
