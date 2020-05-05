package amazon.layer.controller;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void saveImage(MultipartFile productImage , Long productId);
}
