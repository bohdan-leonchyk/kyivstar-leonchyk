package net.kyivstar.leonchyk.controller;

import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.service.WebcamService;
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
public class WebcamController {

	private final WebcamService webcamService;

	@Autowired
	public WebcamController(WebcamService webcamService) {
		this.webcamService = webcamService;
	}

	@RequestMapping(value = "/webcam", method = RequestMethod.GET)
	public ResponseEntity<?> getOneWebcam() {
		Webcam webcam = webcamService.findByIdentifier("webcam#1");

		if (webcam != null)
			return ResponseEntity.ok().body(webcamService.findByIdentifier("webcam#1"));
		else
			return ResponseEntity.badRequest().body(webcam);
	}

	@RequestMapping(value = "/webcam/all", method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllWebcams() {
		List<Webcam> webcams = webcamService.findAllWebcams();

		if (webcams != null)
			return ResponseEntity.ok().body(webcams);
		else
			return ResponseEntity.badRequest().body(webcams);
	}
}
