package amazon.layer.service;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	ServletContext servletContext;

	@Override
	public void saveImage(MultipartFile productImage, Long productId) {

		if (productImage != null && !productImage.isEmpty()) {
			try {
				File file = new File(servletContext.getRealPath("products/") + productId + ".png");
				productImage.transferTo(file);

			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
	}

}
