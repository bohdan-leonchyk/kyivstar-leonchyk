package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@RestController
@RequestMapping(value = "/picture")
public class PictureController {

	private final PictureService pictureService;

	@Autowired
	public PictureController(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOnePicture(@PathVariable @NumberFormat Integer id) {
		Picture picture = pictureService.findOnePicture(id);
		if (picture != null)
			return ResponseEntity.ok().body(picture);
		else
			return ResponseEntity.badRequest().body("No pictures with this id.");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllPictures() {
		List<Picture> pictures = pictureService.findAllPictures();
		if (pictures != null)
			return ResponseEntity.ok().body(pictures);
		else
			return ResponseEntity.badRequest().body(pictures);
	}
}
