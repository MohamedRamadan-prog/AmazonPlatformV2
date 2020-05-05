package amazon.layer.controller;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import amazon.layer.domainn.Product;
import amazon.layer.dto.ProductForm;
import amazon.layer.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String getAddNewProductForm(@ModelAttribute("newProduct") ProductForm newProduct) {
		return "addProduct";
	}

	@RequestMapping("/product")
	public String getProductDetail(@RequestParam(value = "id", required = false) Long productId, Model model) {
		Optional<Product> product = productService.getProductById(productId);
		model.addAttribute("product", product.get());
		return "product";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String processAddNewProductForm(@Valid @ModelAttribute("newProduct") ProductForm newProduct,
			BindingResult result, Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "addProduct";
		}

		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		Product product = productService.addProduct(newProduct, "seller@gmail.com");

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(product.getId() + ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		return "redirect:/products/list";
	}

}
