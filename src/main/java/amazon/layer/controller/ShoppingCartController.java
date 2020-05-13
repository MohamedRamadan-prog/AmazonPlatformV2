
package amazon.layer.controller;

import java.util.Hashtable;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import amazon.layer.domainn.Product;
import amazon.layer.service.ProductService;


@Controller
@RequestMapping("/cart")
public class ShoppingCartController{
		
	@Autowired
	ProductService productService;
	
	@RequestMapping("/add")
	public String addToCart( @RequestParam("id") Long productId , @RequestParam("quantity") Integer quantity , Model model,HttpSession session)
	{	
		    Product  product = productService.getProductById(productId).get();
			Hashtable cart = (Hashtable) session.getAttribute("shoppingCart");
			Set<Product> products = cart.keySet();
			for(Product prd : products)
			{
				if(prd.getId().intValue() == product.getId().intValue())
				{
					Integer currQuantity= (Integer) cart.get(prd);
					quantity += currQuantity;
					cart.remove(prd);
				}
			}
			cart.put(product, quantity);
			session.setAttribute("shoppingCart", cart);
		return "redirect:/products/list";
	}
	
	@RequestMapping("/getCartProducts")
	public String getCartProduct(Model model,HttpSession session)
	{	
			Hashtable cart = (Hashtable) session.getAttribute("shoppingCart");
			model.addAttribute("cart",cart );
		return "cartProducts";
	}
	
	@RequestMapping("/delete")
	public String dropFromCart(@RequestParam("id") Long productId,@RequestParam("value") Integer value, Model model,HttpSession session)
	{	
			Hashtable cart = (Hashtable) session.getAttribute("shoppingCart");	
			Set<Product> products=cart.keySet();
			
			for(Product product : products)
			{
				System.out.println(product.getId());
				if(product.getId() == productId)
				{
					System.out.println("true");
					cart.remove(product);
					System.out.println("done");
					break;
				}
			}				    
			session.setAttribute("shoppingCart", cart);
		return "redirect:/cart/getCartProducts";
	}
	
	@RequestMapping("/checkout")
	public String checkOut(Model model)
	{
		return "redirect:/payments/createPayment";
	}


}