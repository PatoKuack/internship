package com.adbansys.generadorBitacora.xlsx;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class serviceEmployee {
	
	HashMap<String, Object> data;
	
	@Autowired
	repositoryEmployee repository;
	
	
	public ResponseEntity<Object> inicio(){
		data = new HashMap<>();
		int workHours = 4;
		for(int x = 1; x <= LocalDate.now().lengthOfMonth(); x++) {
			Optional<recordEmployee> dateFound = repository.findRecordByFecha(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), x));
			if(!dateFound.isPresent()) {
//				if(LocalDate.now().getDayOfWeek().toString() == "SATURDAY" || 
//					LocalDate.now().getDayOfWeek().toString() == "SUNDAY" || 
//					LocalDate.now().getMonthValue() == 1 && x == 1) {
//					workHours = 0;
//					System.out.println("Horas trabajadas: " + workHours);
//				} else {
//					workHours = 4;
//				}
//				System.out.println(LocalDate.now().getDayOfWeek().toString() == "SUNDAY");
//				System.out.println(LocalDate.now().getDayOfWeek().toString());
				recordEmployee temporalRecord = new recordEmployee();
				temporalRecord.setRecordEmployee(
						"N/A", 
						"N/A", 
						"N/A", 
						"David Alejandro Ramírez Pérez", 
						LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), x),
						workHours, 
						0, 
						"", 
						"N/A", 
						false
				);
				repository.save(temporalRecord);
			}
		}
		data.put("message", "Existen " + repository.count() + " registros.");
		data.put("data", repository.findAll());
		return new ResponseEntity<>(
				data,
				HttpStatus.CONFLICT
		);
	}
	
	
	public ResponseEntity<Object> generateExcel(){
		data = new HashMap<>();
		
		Field[] campos = recordEmployee.class.getDeclaredFields();
		
		XSSFWorkbook libro = new XSSFWorkbook();
		XSSFSheet hoja = libro.createSheet("Diciembre");
		XSSFCellStyle titleStyle = new stylesEmployee.Builder().setDefaultColor(IndexedColors.LIME.getIndex())
																// .setCustomizedColor("C128CW")
																.setPatternType(FillPatternType.SOLID_FOREGROUND)
																.setBorderStyle(BorderStyle.MEDIUM)
																.setHorizontalAlignment(HorizontalAlignment.CENTER)
																.build(libro);
		XSSFCellStyle cellStyleA = new stylesEmployee.Builder().setDefaultColor(IndexedColors.LIGHT_GREEN.getIndex())
																.setPatternType(FillPatternType.SOLID_FOREGROUND)
																.build(libro);
		XSSFCellStyle cellStyleB = new stylesEmployee.Builder().setDefaultColor(IndexedColors.LIGHT_TURQUOISE.getIndex())
																.setPatternType(FillPatternType.SOLID_FOREGROUND)
																.build(libro);
		XSSFCellStyle cellDateStyleA = new stylesEmployee.Builder().setDefaultColor(IndexedColors.LIGHT_GREEN.getIndex())
																.setPatternType(FillPatternType.SOLID_FOREGROUND)
																.setFormat("dd/MM/yyyy")
																.build(libro);
		XSSFCellStyle cellDateStyleB = new stylesEmployee.Builder().setDefaultColor(IndexedColors.LIGHT_TURQUOISE.getIndex())
																.setPatternType(FillPatternType.SOLID_FOREGROUND)
																.setFormat("dd/MM/yyyy")
																.build(libro);
		XSSFRow fila = null;
		XSSFCell celda = null;
		int countRecords = (int)repository.count();
		for(int i = 0; i < countRecords; i++) {
// CABECERA
			if(i == 0) {
				fila = hoja.createRow(0);
				for(int j = 0; j < campos.length; j++) {
					String title = campos[j].getName();
					String[] words  = title.toLowerCase().split("_");
			        for(int k = 0; k < words.length; k++) {
			            if(k == 0) {
			                title = Character.toUpperCase(words[k].charAt(0)) + words[k].substring(1) + " ";
			            } else {
			                title += words[k] + " ";
			            }
			        }
					celda = fila.createCell(j);
					celda.setCellValue(title);
					celda.setCellStyle(titleStyle);
					hoja.autoSizeColumn(j);
				}
// CUERPO
			} else {
				Optional<recordEmployee> gettingRecord = repository.findById(Long.valueOf(i));
				if(gettingRecord.isPresent()) {
					recordEmployee body = repository.findById(Long.valueOf(i)).get();
					List<Object> attributes = body.getAttribute();
				
					fila = hoja.createRow(i);
					
					for(int l = 0; l < attributes.size(); l++) {
						celda = fila.createCell(l);
						if (i%2 == 0) {
							celda.setCellStyle(cellStyleA);
						} else {
							celda.setCellStyle(cellStyleB);
						}
						if(attributes.get(l) instanceof Long) {
							celda.setCellValue((Long) attributes.get(l));
						}
						if(attributes.get(l) instanceof String) {
							celda.setCellValue((String) attributes.get(l));
						}
						if(attributes.get(l) instanceof LocalDate) {
							celda.setCellValue((LocalDate) attributes.get(l));
							if (i%2 == 0) {
								celda.setCellStyle(cellDateStyleA);
							} else {
								celda.setCellStyle(cellDateStyleB);
							}
							hoja.autoSizeColumn(l);
						}
						if(attributes.get(l) instanceof Integer) {
							celda.setCellValue((int) attributes.get(l));
							hoja.autoSizeColumn(l);
							hoja.setHorizontallyCenter(true);
						}
						if(attributes.get(l).toString() == "true" || attributes.get(l).toString() == "false") {
							celda.setCellValue((boolean) attributes.get(l));
						}
					}
				}
			}
		}
		try {
			OutputStream output = new FileOutputStream("bitacoraAdbasys.xlsx");
			libro.write(output);
			libro.close();
			output.close();
			data.put("message", "Excel creado.");
			return new ResponseEntity<> (
				data,
				HttpStatus.CREATED
			);
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException("No se pudo crear el Excel.");
			data.put("error", true);
			data.put("message", "No se pudo crear el Excel.");
			return new ResponseEntity<> (
				data,
				HttpStatus.CONFLICT
			);
		}
	}
	
	// SI SE MANDA UN NÚMERO MAYOR A 24 EN HRS_EXTRA O HRS_REGULARES, 
	// SE ACTUALIZARÁ CON 0, PERO SI SE MANDA 0 NO SE TOMARÁ EN CUENTA
	public ResponseEntity<Object> updateRecord(int yearR, int monthR, int dayR, recordEmployee body) {
		data = new HashMap<>();
		Optional<recordEmployee> idFound = repository.findRecordByFecha(LocalDate.of(yearR, monthR, dayR));
		if(!idFound.isPresent()) {
			data.put("error", true);
			data.put("message", "No existe la fecha: " + idFound.get().getFecha());
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		} else {
			recordEmployee dataRecord = repository.findById(idFound.get().getId()).get();
			if(body.getIdProyecto() != null) {
				dataRecord.setIdProyecto(body.getIdProyecto());
			}
			if(body.getNombreProyecto() != null) {
				dataRecord.setNombreProyecto(body.getNombreProyecto());
			}
			if(body.getNombreCliente() != null) {
				dataRecord.setNombreCliente(body.getNombreCliente());
			}
			if(body.getNombreConsultor() != null) {
				dataRecord.setNombreConsultor(body.getNombreConsultor());
			}
			if(body.getFecha() != null) {
				dataRecord.setFecha(body.getFecha());
			}
			if(body.getHrsRegulares() != 0) {
				dataRecord.setHrsRegulares(body.getHrsRegulares());
			}
			if(body.getHrsRegulares() > 24) {
				dataRecord.setHrsRegulares(0);
			}
			if(body.getHrsExtra() != 0) {
				dataRecord.setHrsExtra(body.getHrsExtra());
			}
			if(body.getHrsExtra() > 24) {
				dataRecord.setHrsExtra(0);
			}
			if(body.getActividades() != null) {
				dataRecord.setActividades(body.getActividades());
			}
			if(body.getEntregables() != null) {
				dataRecord.setEntregables(body.getEntregables());
			}
			if(body.getCompletado() == true) {
				dataRecord.setCompletado(true);
			}
			repository.save(dataRecord);
			data.put("message", "Registro actualizado");
			data.put("data", dataRecord);
			return new ResponseEntity<> (
				data,
				HttpStatus.CREATED
			);
		}
	}
	
/*
	 public ResponseEntity<Object> postRecord(recordEmployee body){
		data = new HashMap<>();
		Optional<recordEmployee> idFound = repository.findRecordByFecha(body.getFecha());
		if(!idFound.isPresent()) {
			data.put("error", true);
			data.put("message", "No existe la fecha: " + body.getFecha());
			return new ResponseEntity<>(
					data,
					HttpStatus.CONFLICT
			);
		}
		if(body.getIdProyecto() == null) {
			body.setIdProyecto("N/A");
		}
		if(body.getNombreCliente() == null) {
			body.setNombreCliente("N/A");
		}
		if(body.getNombreProyecto() == null) {
			body.setNombreProyecto("N/A");
		}
		if(body.getEntregables() == null) {
			body.setEntregables("N/A");
		}
		if(body.getHrsRegulares() == 0) {
			body.setHrsRegulares(4);
		}
		if(body.getFecha() == null) {
			body.setFecha(LocalDate.now());
		}
		repository.save(body);
		System.out.println(body);
		data.put("message", "Registro añadido");
		data.put("data", body);
		return new ResponseEntity<> (
			data,
			HttpStatus.CREATED
		);
	 }
*/
}
