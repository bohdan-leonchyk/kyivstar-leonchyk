package net.kyivstar.leonchyk.repo;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.entity.Webcam;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Bohdan Leonchyk
 * @date 13.03.2017
 */
public interface PicturePagingRepository extends PagingAndSortingRepository<Picture, String> {

	Picture findByName(String name);
	Picture findFirstByOrderByIdDesc(Webcam webcam);

}
