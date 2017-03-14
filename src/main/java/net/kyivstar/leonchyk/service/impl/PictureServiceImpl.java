package net.kyivstar.leonchyk.service.impl;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.repo.PicturePagingRepository;
import net.kyivstar.leonchyk.repo.PictureRepository;
import net.kyivstar.leonchyk.service.PictureService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 11.03.2017
 */
@Component
public class PictureServiceImpl implements PictureService {

	private static String UPLOAD_ROOT = "AmazonServer";

	private final PictureRepository pictureRepository;
	private final PicturePagingRepository picturePagingRepository;

	@Autowired
	public PictureServiceImpl(
			PictureRepository pictureRepository,
			PicturePagingRepository picturePagingRepository) {

		this.pictureRepository = pictureRepository;
		this.picturePagingRepository = picturePagingRepository;
	}

	@Override
	public String fileBase64Encode(String filename) throws IOException {
		File picture = new File(System.getProperty("user.dir") + "/" + UPLOAD_ROOT + "/" + filename);
		return new String(Base64.encodeBase64(FileUtils.readFileToByteArray(picture)), "UTF-8");
	}

	@Override
	public Picture findOnePicture(String name, Webcam webcam) {
		return pictureRepository.findByNameAndWebcam(name, webcam);
	}

	@Override
	public List<Picture> findByWebcam(Webcam webcam) {
		return pictureRepository.findAllByWebcam(webcam);
	}

	@Override
	public void savePicture(MultipartFile file, Webcam webcam) throws IOException {
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOAD_ROOT + "/" + file.getOriginalFilename());
		Files.write(path, bytes);
		picturePagingRepository.save(new Picture(file.getOriginalFilename(), webcam));
	}
}
