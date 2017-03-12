package net.kyivstar.leonchyk.service;

import net.kyivstar.leonchyk.entity.Webcam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Service
public interface WebcamService {

	Webcam findByIdentifier(String identifier);
	List<Webcam> findAllWebcams();
}
