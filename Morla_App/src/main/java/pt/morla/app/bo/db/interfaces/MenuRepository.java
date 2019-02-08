package pt.morla.app.bo.db.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.morla.app.bo.db.models.menu_obj;

@Repository
public interface MenuRepository extends CrudRepository<menu_obj, Long>{

}
