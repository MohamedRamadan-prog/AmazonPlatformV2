package amazon.layer.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void saveImage(MultipartFile productImage , Long productId);
}
