package amazon.layer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import amazon.layer.domainn.Order;
import amazon.layer.repository.OrderRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportManagerServiceImpl implements ReportManagerService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ServletContext servletContext;

	@Override
	public void generatePdfInvoice(Long orderId) throws FileNotFoundException, JRException {

		String path = servletContext.getRealPath("invoices/");

		Order order = orderRepo.findById(orderId).get();
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:invoice.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getOrderLines());
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("billAddress", order.getBillingAddress().getAddressLine() + "\n"
				+ order.getBillingAddress().getCity() + "\n" + order.getBillingAddress().getState());

		parameters.put("shipAddress", order.getShippingAddress().getAddressLine() + "\n"
				+ order.getShippingAddress().getCity() + "\n" + order.getShippingAddress().getState());

		parameters.put("totalPrice", order.getTotalPrice());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/invoice.pdf");

	}

}
