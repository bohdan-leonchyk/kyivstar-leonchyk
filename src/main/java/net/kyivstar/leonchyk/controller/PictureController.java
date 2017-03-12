package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@RestController
public class PictureController {

	private final PictureService pictureService;

	@Autowired
	public PictureController(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	@RequestMapping(value = "/picture", method = RequestMethod.GET)
	public ResponseEntity<?> getOnePicture() {
		Picture picture = pictureService.findOnePicture(1);
		if (picture != null)
			return ResponseEntity.ok().body(picture);
		else
			return ResponseEntity.badRequest().body(picture);
	}

	@RequestMapping(value = "/picture/all", method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllPictures() {
		List<Picture> pictures = pictureService.findAllPictures();
		if (pictures != null)
			return ResponseEntity.ok().body(pictures);
		else
			return ResponseEntity.badRequest().body(pictures);
	}
}
