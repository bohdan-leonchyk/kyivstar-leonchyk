package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.service.PictureService;
import net.kyivstar.leonchyk.service.WebcamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@RestController
@RequestMapping(value = "/picture")
public class PictureController {

	private final PictureService pictureService;
	private final WebcamService webcamService;

	@Autowired
	public PictureController(PictureService pictureService, WebcamService webcamService) {

		this.pictureService = pictureService;
		this.webcamService = webcamService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOnePicture(@PathVariable @NumberFormat Integer id) {

		Picture picture = pictureService.findOnePicture(id);

		if (picture != null)
			return ResponseEntity.ok().body(picture);

		return ResponseEntity.badRequest().body("There is no picture with this id.");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllPictures() {

		List<Picture> pictures = pictureService.findAllPictures();

		if (pictures != null)
			return ResponseEntity.ok().body(pictures);

		return ResponseEntity.badRequest().body(pictures);
	}

	@RequestMapping(value = "/{identifier}", method = RequestMethod.POST)
	public ResponseEntity<?> savePicture(@PathVariable String identifier) {

		Webcam webcam = webcamService.findByIdentifier(identifier);

		if (webcam == null)
			return ResponseEntity.badRequest().body("There is no webcam with identifier " + identifier);

		Picture picture = new Picture();
		picture.setCreatedTime(LocalDateTime.now());
		picture.setWebcam(webcam);
		pictureService.savePicture(picture);

		return ResponseEntity.ok().body(picture);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePicture(@PathVariable @NumberFormat Integer id) {

		Picture picture = pictureService.findOnePicture(id);

		if (picture != null) {
			pictureService.deletePicture(id);
			return ResponseEntity.ok().body(picture);
		}

		return ResponseEntity.badRequest().body("There is no picture with id: " + id);
	}

	@RequestMapping(value = "/{id}/{fk_identifier}", method = RequestMethod.PUT)
	public ResponseEntity<?> editPictureWebcam(
			@PathVariable @NumberFormat Integer id,
	        @PathVariable String fk_identifier) {

		Picture picture = pictureService.findOnePicture(id);

		if (picture != null) {
			Webcam webcam = webcamService.findByIdentifier(fk_identifier);

			if (webcam != null) {
				picture.setWebcam(webcam);
				pictureService.savePicture(picture);

				return ResponseEntity.ok().body(picture);
			}

			return ResponseEntity.badRequest().body("There is no camera with identifier: " + fk_identifier);
		}

		return ResponseEntity.badRequest().body("There is no picture with id: " + id);
	}
}
