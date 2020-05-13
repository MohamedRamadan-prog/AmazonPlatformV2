package amazon.layer.service;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface ReportManagerService {

	void generatePdfInvoice(Long orderId)
			throws FileNotFoundException, JRException;

}
