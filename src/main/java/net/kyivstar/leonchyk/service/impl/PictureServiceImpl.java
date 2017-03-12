package net.kyivstar.leonchyk.service.impl;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.repo.PictureRepository;
import net.kyivstar.leonchyk.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Component
public class PictureServiceImpl implements PictureService {

	private final PictureRepository pictureRepository;

	@Autowired
	public PictureServiceImpl(PictureRepository pictureRepository) {
		this.pictureRepository = pictureRepository;
	}

	@Override
	public Picture findOnePicture(Integer id) {
		return pictureRepository.findOne(id);
	}

	@Override
	public List<Picture> findAllPictures() {
		return pictureRepository.findAll();
	}
}
