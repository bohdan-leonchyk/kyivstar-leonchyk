package net.kyivstar.leonchyk.service;

import net.kyivstar.leonchyk.entity.Picture;
import net.kyivstar.leonchyk.entity.Webcam;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	Picture findOnePicture(Integer id);
	List<Picture> findAllPictures();
	Page<Picture> findPage(Pageable pageable);
	Resource findOneFilePicture(String filename);
	void savePicture(MultipartFile file, Webcam webcam) throws IOException;
	void deletePicture(Integer id);
	void createFilePicture(MultipartFile file, Webcam webcam) throws IOException;
	void deleteFilePicture(String filename) throws IOException;
	Picture getLastAdded(Webcam webcam);

}
