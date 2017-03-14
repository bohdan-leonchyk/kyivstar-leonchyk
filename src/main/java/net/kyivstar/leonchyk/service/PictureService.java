package net.kyivstar.leonchyk.service;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.entity.Webcam;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Service
public interface PictureService {

	String fileBase64Encode(String filename) throws IOException;
	Picture findOnePicture(String name, Webcam webcam);
	List<Picture> findByWebcam(Webcam webcam);
	void savePicture(MultipartFile file, Webcam webcam) throws IOException;

}
