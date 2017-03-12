package net.kyivstar.leonchyk.repo;

import net.kyivstar.leonchyk.entity.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
public interface PictureRepository extends CrudRepository<Picture, Integer> {

	List<Picture> findAll();

}
