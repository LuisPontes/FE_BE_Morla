package pt.morla.app.bo.db.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.morla.app.bo.db.models.images_tb;

@Repository
public interface ImagesRepository extends CrudRepository<images_tb, Long>{

}
