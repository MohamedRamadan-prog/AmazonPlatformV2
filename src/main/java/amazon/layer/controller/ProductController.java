package amazon.layer.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

	@Autowired
	private StorageService storageService;

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "buyerHome";
	}

	@RequestMapping("/getSellersProduct")
	public String getSellersProducts(Model model, @RequestParam("email") String sellerEmail) {
		model.addAttribute("sellerProducts", productService.getSellerProducts(sellerEmail));
		return "sellersHome";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String getAddNewProductForm(@ModelAttribute("newProduct") ProductForm newProduct) {
		return "addProduct";
	}

	@RequestMapping(value = "/updateProductForm", method = RequestMethod.GET)
	public String updateProduct(@RequestParam(value = "id", required = false) Long productId, Model model) {
		Optional<Product> product = productService.getProductById(productId);
		model.addAttribute("product", product.get());
		return "updateProduct";
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String processUpdateProduct(@RequestParam(value = "id", required = false) Long productId,
			@ModelAttribute("product") ProductForm product) {
		MultipartFile productImage = product.getProductImage();
		storageService.saveImage(productImage, productId);

		productService.update(product, productId);
		return "redirect:/products/list";
	}

	@RequestMapping("/product")
	public String getProductDetail(@RequestParam(value = "id", required = true) Long productId, Model model) {
		Optional<Product> product = productService.getProductById(productId);
		model.addAttribute("product", product.get());
		return "product";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String processAddNewProductForm(@Valid @ModelAttribute("newProduct") ProductForm newProduct,
			BindingResult result) {

		if (result.hasErrors()) {
			return "addProduct";
		}

		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		MultipartFile productImage = newProduct.getProductImage();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Product product = productService.saveOrUpdate(newProduct, ((UserDetails) principal).getUsername());
		storageService.saveImage(productImage, product.getId());

		return "redirect:/products/list";
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.POST)
	public String removeProduct(@RequestParam(value = "id", required = false) Long productId) {

		boolean isDeleted = productService.deleteById(productId);
		// TODO
		return "redirect:/products/list";
	}

}
