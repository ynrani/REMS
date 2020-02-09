/*---------------------------------------------------------------------------------------
 * Object Name: RemsBookingController.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          09/11/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/
package com.rems.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rems.model.DTO.RemsEnvBookingsDTO;

public class RemsPdfReportListView extends AbstractPdfView
{
	@SuppressWarnings({ "rawtypes" })
	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<RemsEnvBookingsDTO> remsEnvBookingsDTOs = (List<RemsEnvBookingsDTO>) model.get("remsEnvBookingsDTOs");

		PdfPTable table = null;

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.WHITE);
		font.setSize(9);

		PdfPCell cell = null;
		document.add(new Paragraph("User Report"));
		table = new PdfPTable(8);
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(10);

		// define table header cell
		cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(remsEnvBookingsDTOs.size());
		cell.setPhrase(new Phrase("User", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Environment Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Application Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Environment User", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Environment Booked to", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Environment Connectivity", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Booking Start Date", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Booking End Date", font));
		table.addCell(cell);

		for (RemsEnvBookingsDTO remsEnvBookingsDTO : remsEnvBookingsDTOs) {
			table.addCell(remsEnvBookingsDTO.getUserId());
			table.addCell(remsEnvBookingsDTO.getEnvName());
			table.addCell(remsEnvBookingsDTO.getAppName());
			table.addCell(remsEnvBookingsDTO.getEnvUser());
			table.addCell(remsEnvBookingsDTO.getEnvBookedOut());
			table.addCell(remsEnvBookingsDTO.getEnvConn());
			table.addCell(remsEnvBookingsDTO.getStartDt());
			table.addCell(remsEnvBookingsDTO.getEndDt());
		}

		document.add(table);
	}

	@Override
	protected boolean generatesDownloadContent() {

		return false;

	}

}
