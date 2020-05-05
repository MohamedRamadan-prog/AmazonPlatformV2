package amazon.layer.controller;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	@Override
	public void saveImage(MultipartFile productImage, Long productId) {

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(productId + ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
	}

}
