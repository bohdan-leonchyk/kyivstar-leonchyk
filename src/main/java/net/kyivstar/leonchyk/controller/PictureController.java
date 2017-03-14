package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.service.PictureService;
import net.kyivstar.leonchyk.service.WebcamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@RestController
@RequestMapping(value = "/pictures")
public class PictureController {

	private static final String FILENAME = "{filename:.+}";

	private final PictureService pictureService;
	private final WebcamService webcamService;

	@Autowired
	public PictureController(PictureService pictureService, WebcamService webcamService) {

		this.pictureService = pictureService;
		this.webcamService = webcamService;
	}

	/**
	 * Show file picture
	 *
	 * @param fk_identifier webcam indetifier (foreign key for picture)
	 * @param picName picture filename
	 * @return
	 */
	@RequestMapping(value = "/{fk_identifier}/{picture_name}", method = RequestMethod.GET)
	public ResponseEntity<?> getLastAddedPicture(
			@PathVariable String fk_identifier,
			@PathVariable String picName) {

		Webcam webcam = webcamService.findByIdentifier(fk_identifier);

		if (webcam != null) {

			try {
				Resource file = pictureService.findOneFilePicture(picName);

				return ResponseEntity.ok()
						.contentLength(file.contentLength())
						.contentType(MediaType.IMAGE_JPEG)
						.body(new InputStreamResource(file.getInputStream()));

			} catch (IOException ex) {
				return ResponseEntity.badRequest().body("Could not find " + picName + " => " + ex.getMessage());
			}
		}

		return ResponseEntity.badRequest().body("There is no webcam identifier: " + fk_identifier);
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

	/**
	 * Delete picture file
	 * !!! in development (no database integration yet)
	 *
	 * @param filename
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/" + FILENAME)
	public ResponseEntity<?> deleteFile(@PathVariable String filename) {

		try {
			pictureService.deleteFilePicture(filename);

			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("Successfully deleted " + filename);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete " + filename + " => " + e.getMessage());
		}
	}
}
