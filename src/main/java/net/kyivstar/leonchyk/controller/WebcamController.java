package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.service.WebcamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@RestController
@RequestMapping(value = "/webcams")
public class WebcamController {

	private final WebcamService webcamService;

	@Autowired
	public WebcamController(WebcamService webcamService) {
		this.webcamService = webcamService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllWebcams() {

		List<Webcam> webcams = webcamService.findAllWebcams();

		if (webcams != null)
			return ResponseEntity.ok().body(webcams);

		return ResponseEntity.badRequest().body(webcams);
	}

	@RequestMapping(value = "/{identifier}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneWebcam(@PathVariable String identifier) {

		Webcam webcam = webcamService.findByIdentifier(identifier);

		if (webcam != null)
			return ResponseEntity.ok().body(webcam);

		return ResponseEntity.badRequest().body("No webcams with this identifier.");
	}

	@RequestMapping(value = "/{identifier}", method = RequestMethod.POST)
	public ResponseEntity<?> saveWebcam(@PathVariable String identifier) {

		Webcam webcam = new Webcam();
		webcam.setIdentifier(identifier);

		if (webcamService.findByIdentifier(identifier) == null) {
			webcamService.saveWebcam(webcam);
			return ResponseEntity.ok().body("Webcam with identifier  " + identifier + " succesfully saved.");
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Webcam with identifier " + identifier
				+ " already exists!");
	}

	@RequestMapping(value = "/{identifier}/{location}", method = RequestMethod.POST)
	public ResponseEntity<?> saveWebcam(@RequestBody Webcam webcam) {

		if (webcamService.findByIdentifier(webcam.getIdentifier()) == null) {
			webcamService.saveWebcam(webcam);
			return ResponseEntity.ok().body("Webcam with identifier  " + webcam.getIdentifier() + " succesfully saved.");
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Webcam with identifier " + webcam.getIdentifier()
			+ " already exists!");
	}

	@RequestMapping(value = "/{identifier}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteWebcam(@PathVariable String identifier) {

		Webcam webcam = webcamService.findByIdentifier(identifier);

		if (webcam != null) {
			webcamService.deleteWebcam(identifier);
			return ResponseEntity.ok().body("Webcam with identifier " + identifier + " was deleted.");
		}

		return ResponseEntity.badRequest().body("There is no webcam with identifier " + identifier + " to delete");
	}

	@RequestMapping(value = "/{identifier}/{location}", method = RequestMethod.PUT)
	public ResponseEntity<?> editWebcamLocation(
			@PathVariable String identifier,
	        @PathVariable String location) {

		Webcam webcam = webcamService.findByIdentifier(identifier);

		if (webcam != null) {
			webcam.setLocation(location);
			webcamService.saveWebcam(webcam);
			return ResponseEntity.ok().body("Webcam with identifier " + identifier + " was edited to location: " + location);
		}

		return ResponseEntity.badRequest().body("There is no webcam with identifier " + identifier + " to edit.");
	}
}
