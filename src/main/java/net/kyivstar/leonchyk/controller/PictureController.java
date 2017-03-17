package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.service.PictureService;
import net.kyivstar.leonchyk.service.WebcamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@RestController
@RequestMapping(value = "/pictures")
public class PictureController {

	private final PictureService pictureService;
	private final WebcamService webcamService;

	@Autowired
	public PictureController(PictureService pictureService, WebcamService webcamService) {
		this.pictureService = pictureService;
		this.webcamService = webcamService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllPictures() {
		return ResponseEntity.ok().body(pictureService.findAll());
	}

	/**
	 * Get list of all pictures for current webcam
	 *
	 * @param fk_identifier webcam indetifier (foreign key for picture)
	 * @return list of pictures for current webcam
	 */
	@RequestMapping(value = "/{fk_identifier}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPicturesForCurrentWebcam(@PathVariable String fk_identifier) {

		Webcam webcam = webcamService.findByIdentifier(fk_identifier);

		if (webcam != null) {
			List<Picture> pictures = pictureService.findByWebcam(webcam);

			if (pictures != null)
				return ResponseEntity.ok().body(pictures);

			return ResponseEntity.badRequest().body("There is no any picture.");
		}

		return ResponseEntity.badRequest().body("There is no webcam: " + fk_identifier);
	}

	/**
	 * Send to client file Base64 encoded.
	 *
	 * @param fk_identifier webcam indetifier (foreign key for picture)
	 * @param filename picture filename
	 * @return picture Base64 encoded
	 */
	@RequestMapping(value = "/{fk_identifier}/{filename}/base64", method = RequestMethod.GET)
	public ResponseEntity<?> getLastAddedPicture(
			@PathVariable String fk_identifier,
			@PathVariable String filename) {

		Webcam webcam = webcamService.findByIdentifier(fk_identifier);

		System.out.println(System.getProperty("user.dir"));

		if (webcam != null) {
			if (pictureService.findOnePicture(filename, webcam) != null) {

				try {


					return ResponseEntity.ok().body(pictureService.fileBase64Encode(filename));

				} catch (IOException ex) {
					return ResponseEntity.badRequest().body("Could not find " + filename + " => " + ex.getMessage());
				}
			}
			return ResponseEntity.badRequest().body("There is no picture");
		}

		return ResponseEntity.badRequest().body("There is no webcam");
	}

	/**
	 * Upload picture from client to server into "AmazonServer" folder
	 * and add information into database.
	 *
	 * @param fk_identifier webcam for picture
	 * @param file picture file
	 */
	@RequestMapping(value = "/{fk_identifier}", method = RequestMethod.POST)
	public ResponseEntity<?> createFile(
			@PathVariable String fk_identifier,
			@RequestParam("file") MultipartFile file) throws URISyntaxException {

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Choose file.");
		}

		Webcam webcam = webcamService.findByIdentifier(fk_identifier);

		if (webcam == null) {
			return ResponseEntity.badRequest().body("There is no webcam: " + fk_identifier);
		}

		try {
			pictureService.savePicture(file, webcam);

			return ResponseEntity.ok().body("File " + file.getOriginalFilename() + " succesfully uploaded");
		} catch (IOException ex) {
			return ResponseEntity.badRequest().body("Could not save file: " + ex.getMessage());
		}
	}
}
