package com.adbansys.generadorBitacora.xlsx;

import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class stylesEmployee {
	public static class Builder{
		private short defaultColor;
		private XSSFColor customizedColor;
		private FillPatternType patternType;
		private XSSFFont font;
		private String format;
		private HorizontalAlignment horizontalAlignment;
		private VerticalAlignment verticalAlignment;
		private BorderStyle borderStyle;

		
		public Builder setDefaultColor(short defaultColor) {
			this.defaultColor = defaultColor;
			return this;
		}
		
		public Builder setCustomizedColor(String customizedColor) {
			try {
				byte[] rgb = Hex.decodeHex(customizedColor);
				this.customizedColor = new XSSFColor(rgb);
				return this;
			} catch (Exception e) {
				throw new RuntimeException("Error al decodificar el color.");
			}
		}
		
		public Builder setPatternType(FillPatternType patternType) {
			this.patternType = patternType;
			return this;
		}
		
		public Builder setFont(XSSFFont font) {
			this.font = font;
			return this;
		}
		
		public Builder setFormat(String format) {
			this.format = format;
			return this;
		}
		
		public Builder setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
			this.horizontalAlignment = horizontalAlignment;
			return this;
		}
		
		public Builder setVerticalAlignment(VerticalAlignment verticalAlignment) {
			this.verticalAlignment = verticalAlignment;
			return this;
		}
		
		public Builder setBorderStyle(BorderStyle borderStyle) {
			this.borderStyle = borderStyle;
			return this;
		}
		
		public XSSFCellStyle build(XSSFWorkbook libro) {
			XSSFCellStyle cellStyle = libro.createCellStyle();
			cellStyle.setFillForegroundColor(customizedColor);
			if(this.defaultColor != 0) {
				cellStyle.setFillForegroundColor(defaultColor);
			}
			if(this.customizedColor != null) {
				cellStyle.setFillForegroundColor(customizedColor);
			}
			if(this.patternType != null) {
				cellStyle.setFillPattern(patternType);
			}
			if(this.font != null) {
				cellStyle.setFont(font);
			}
			if(this.format != null) {
				cellStyle.setDataFormat(libro.createDataFormat().getFormat(format));
			}
			if(this.horizontalAlignment != null) {
				cellStyle.setAlignment(horizontalAlignment);
			}
			if(this.verticalAlignment != null) {
				cellStyle.setVerticalAlignment(verticalAlignment);
			}
			if(this.borderStyle != null) {
				cellStyle.setBorderTop(borderStyle);
				cellStyle.setBorderRight(borderStyle);
				cellStyle.setBorderBottom(borderStyle);
				cellStyle.setBorderLeft(borderStyle);
			}
			
			return cellStyle;
		}
	}
}
