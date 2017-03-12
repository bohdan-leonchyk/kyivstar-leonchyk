package net.kyivstar.leonchyk.repo;

import net.kyivstar.leonchyk.entity.Webcam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
public interface WebcamRepository extends CrudRepository<Webcam, String> {

	List<Webcam> findAll();

}
