package amazon.layer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import amazon.layer.domainn.User;
import amazon.layer.repository.UserRepository;
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
	private UserRepository userRepository;

	@Override
	public void generatePdfInvoice(String reportFormat) throws FileNotFoundException, JRException {

		String path = "C:\\Users\\mosad\\Desktop";
		List<User> users = userRepository.findAll();
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:invoice.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Java Techie");
		parameters.put("billAddress", "MR 123456");
		parameters.put("shipAddress", "MR 232154");

		parameters.put("totalPrice", 150.0);
		

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\invoice.html");
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\invoice.pdf");
		}

	}

}
