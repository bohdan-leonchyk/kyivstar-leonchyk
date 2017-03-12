package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.service.WebcamService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/webcam")
public class WebcamController {

	private final WebcamService webcamService;

	@Autowired
	public WebcamController(WebcamService webcamService) {
		this.webcamService = webcamService;
	}

	@RequestMapping(value = "/{identifier}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneWebcam(@PathVariable String identifier) {
		Webcam webcam = webcamService.findByIdentifier(identifier);

		if (webcam != null)
			return ResponseEntity.ok().body(webcam);
		else
			return ResponseEntity.badRequest().body("No webcams with this identifier.");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllWebcams() {
		List<Webcam> webcams = webcamService.findAllWebcams();

		if (webcams != null)
			return ResponseEntity.ok().body(webcams);
		else
			return ResponseEntity.badRequest().body(webcams);
	}
}
