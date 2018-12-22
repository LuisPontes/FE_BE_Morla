package pt.morla.app.bo.db.interfaces;

import java.util.List;

import pt.morla.app.bo.db.models.images_tb;

public interface IImages {

	public List<images_tb> findAll();

	public Long save(images_tb obj);

	public void remove(Long id);
}
