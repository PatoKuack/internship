package com.adbansys.generadorBitacora.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

@Component("/views/bitacora.xlsx")
public class Bitacora extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// generar archivo bitacora.xlsx
		response.setHeader("Content-Disposition", "attachment; filename=\'bitacora.xlsx\'");
		// generar hoja llamada 2024
		Sheet hoja = workbook.createSheet("2024");
		
	}

}

// localhost:8080/views/bitacora/?format=xlsx
