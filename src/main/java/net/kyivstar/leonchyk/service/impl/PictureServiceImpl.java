package net.kyivstar.leonchyk.service.impl;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.entity.Webcam;
import net.kyivstar.leonchyk.repo.PicturePagingRepository;
import net.kyivstar.leonchyk.repo.PictureRepository;
import net.kyivstar.leonchyk.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
	private final ResourceLoader resourceLoader;

	@Autowired
	public PictureServiceImpl(
			PictureRepository pictureRepository,
			PicturePagingRepository picturePagingRepository,
			ResourceLoader resourceLoader) {

		this.pictureRepository = pictureRepository;
		this.picturePagingRepository = picturePagingRepository;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public Picture findOnePicture(Integer id) {
		return pictureRepository.findOne(id);
	}

	@Override
	public List<Picture> findAllPictures() {
		return pictureRepository.findAll();
	}

	@Override
	public void savePicture(MultipartFile file, Webcam webcam) throws IOException {
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOAD_ROOT + "/" + file.getOriginalFilename());
		Files.write(path, bytes);
		picturePagingRepository.save(new Picture(file.getOriginalFilename(), webcam));
	}

	@Override
	public void deletePicture(Integer id) {
		pictureRepository.delete(id);
	}

	@Override
	public void createFilePicture(MultipartFile file, Webcam webcam) throws IOException {
		if (!file.isEmpty()) {
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
			picturePagingRepository.save(new Picture(file.getOriginalFilename(), webcam));
		}
	}

	@Override
	public void deleteFilePicture(String filename) throws IOException {
		final Picture byName = picturePagingRepository.findByName(filename);
		picturePagingRepository.delete(byName);
		Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
	}

	@Override
	public Picture getLastAdded(Webcam webcam) {
		return picturePagingRepository.findFirstByOrderByIdDesc(webcam);
	}

	@Override
	public Page<Picture> findPage(Pageable pageable) {
		return picturePagingRepository.findAll(pageable);
	}

	@Override
	public Resource findOneFilePicture(String filename) {
		return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + filename);
	}
}
