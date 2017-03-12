package net.kyivstar.leonchyk.service.impl;

import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.repo.WebcamRepository;
import net.kyivstar.leonchyk.service.WebcamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Component
public class WebcamServiceImpl implements WebcamService {

	private final WebcamRepository webcamRepository;

	@Autowired
	public WebcamServiceImpl(WebcamRepository webcamRepository) {
		this.webcamRepository = webcamRepository;
	}

	@Override
	public Webcam findByIdentifier(String identifier) {
		return webcamRepository.findOne(identifier);
	}

	@Override
	public List<Webcam> findAllWebcams() {
		return webcamRepository.findAll();
	}

	@Override
	public void saveWebcam(Webcam webcam) {
		webcamRepository.save(webcam);
	}

	@Override
	public void deleteWebcam(String identifier) {
		webcamRepository.delete(identifier);
	}
}
