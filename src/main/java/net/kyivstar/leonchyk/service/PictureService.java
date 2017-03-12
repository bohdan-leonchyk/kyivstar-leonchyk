package net.kyivstar.leonchyk.service;

import net.kyivstar.leonchyk.entity.Picture;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Service
public interface PictureService {

	Picture findOnePicture(Integer id);
	List<Picture> findAllPictures();
}
