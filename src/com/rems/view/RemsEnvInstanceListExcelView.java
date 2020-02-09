package com.rems.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.rems.model.DTO.RemsCreateEnvInstanceDTO;

public class RemsEnvInstanceListExcelView extends AbstractExcelView
{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HSSFSheet excelSheet = workbook.createSheet("Environment Instance(s) - Showcase");

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIME.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.DARK_TEAL.index);
		style.setFont(font);

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		// cell.setCellStyle(style);
		setExcelHeader(excelSheet, style);

		@SuppressWarnings("unchecked")
		List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOList = (List<RemsCreateEnvInstanceDTO>) model
				.get("remsCreateEnvInstanceDTOs");
		setExcelRows(excelSheet, remsCreateEnvInstanceDTOList, style2);

	}

	public void setExcelHeader(HSSFSheet excelSheet, HSSFCellStyle style) {
		HSSFRow excelHeader = excelSheet.createRow(2);

		excelSheet.setDisplayGridlines(false);

		excelHeader = excelSheet.createRow(4);
		excelHeader.createCell(1).setCellValue("Project Name");
		excelHeader.createCell(2).setCellValue("Environment Name");
		excelHeader.createCell(3).setCellValue("Application Name");
		excelHeader.createCell(4).setCellValue("Software build/Version");
		excelHeader.createCell(5).setCellValue("Operating System Version");
		excelHeader.createCell(6).setCellValue("Host Server Name");
		excelHeader.createCell(7).setCellValue("Creator");
		excelHeader.createCell(8).setCellValue("IsActive");
		excelHeader.createCell(9).setCellValue("Created");

		excelHeader.getCell(1).setCellStyle(style);
		excelHeader.getCell(2).setCellStyle(style);
		excelHeader.getCell(3).setCellStyle(style);
		excelHeader.getCell(4).setCellStyle(style);
		excelHeader.getCell(5).setCellStyle(style);
		excelHeader.getCell(6).setCellStyle(style);
		excelHeader.getCell(7).setCellStyle(style);
		excelHeader.getCell(8).setCellStyle(style);
		excelHeader.getCell(9).setCellStyle(style);

		excelSheet.autoSizeColumn(1);
		excelSheet.autoSizeColumn(2);
		excelSheet.autoSizeColumn(3);
		excelSheet.autoSizeColumn(4);
		excelSheet.autoSizeColumn(5);
		excelSheet.autoSizeColumn(6);
		excelSheet.autoSizeColumn(7);
		excelSheet.autoSizeColumn(8);
		excelSheet.autoSizeColumn(9);

	}

	public HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor = palette.findColor(r, g, b);
			if (hssfColor == null) {
				palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
				hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return hssfColor;
	}

	public void setExcelRows(HSSFSheet excelSheet, List<RemsCreateEnvInstanceDTO> remsCreateEnvInstanceDTOs,
			HSSFCellStyle style) {
		int record = 5;

		for (RemsCreateEnvInstanceDTO remsCreateEnvInstanceDTO : remsCreateEnvInstanceDTOs) {
			HSSFRow excelRow = excelSheet.createRow(record++);

			excelRow.createCell(1).setCellValue(remsCreateEnvInstanceDTO.getProjName());
			excelRow.createCell(2).setCellValue(remsCreateEnvInstanceDTO.getEnvName());
			excelRow.createCell(3).setCellValue(remsCreateEnvInstanceDTO.getAppName());
			excelRow.createCell(4).setCellValue(remsCreateEnvInstanceDTO.getSwLoadInfo());
			excelRow.createCell(5).setCellValue(remsCreateEnvInstanceDTO.getOsVer());
			excelRow.createCell(6).setCellValue(remsCreateEnvInstanceDTO.getHostServerName());
			excelRow.createCell(7).setCellValue(remsCreateEnvInstanceDTO.getUserId());
			excelRow.createCell(8).setCellValue(remsCreateEnvInstanceDTO.getActive());
			excelRow.createCell(9).setCellValue(remsCreateEnvInstanceDTO.getCreatedDate());

			excelRow.getCell(1).setCellStyle(style);
			excelRow.getCell(2).setCellStyle(style);
			excelRow.getCell(3).setCellStyle(style);
			excelRow.getCell(4).setCellStyle(style);
			excelRow.getCell(5).setCellStyle(style);
			excelRow.getCell(6).setCellStyle(style);
			excelRow.getCell(7).setCellStyle(style);
			excelRow.getCell(8).setCellStyle(style);
			excelRow.getCell(9).setCellStyle(style);

			excelSheet.autoSizeColumn(1);
			excelSheet.autoSizeColumn(2);
			excelSheet.autoSizeColumn(3);
			excelSheet.autoSizeColumn(4);
			excelSheet.autoSizeColumn(5);
			excelSheet.autoSizeColumn(6);
			excelSheet.autoSizeColumn(7);
			excelSheet.autoSizeColumn(8);
			excelSheet.autoSizeColumn(9);

		}
	}
}
