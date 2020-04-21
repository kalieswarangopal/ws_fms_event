package com.fms.event.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.fms.event.entity.Event;

@Component
public class ExcelDownloadHelper {

	public ByteArrayInputStream generateExcel(List<Event> eventList) {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Event");

			Row row = sheet.createRow(0);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			List<String> columnNames = Arrays.asList("Event ID", "Event Name", "Month", "Base location", "Council name",
					"Beneficiary name", "Status", "Venue address", "Event date", "Total volunteers",
					"Total volunteer hours", "Total travel hours", "Project", "Category", "Event description",
					"Overall volunteering hours", "Lives impacted", "Activity type");

			columnNames.forEach(columnName -> {
				int index = columnNames.indexOf(columnName);
				Cell cell = row.createCell(index);
				cell.setCellValue(columnName);
				cell.setCellStyle(headerCellStyle);
			});

			eventList.forEach(event -> {
				int index = eventList.indexOf(event);
				Row dataRow = sheet.createRow(index);
				dataRow.createCell(0).setCellValue(eventList.get(index).getEventID());
				dataRow.createCell(1).setCellValue(eventList.get(index).getEventName());
				dataRow.createCell(2).setCellValue(eventList.get(index).getMonth());
				dataRow.createCell(3).setCellValue(eventList.get(index).getBaseLocation());
				dataRow.createCell(4).setCellValue(eventList.get(index).getCouncilName());
				dataRow.createCell(5).setCellValue(eventList.get(index).getBeneficiaryName());
				dataRow.createCell(6).setCellValue(eventList.get(index).getStatus());
				dataRow.createCell(7).setCellValue(eventList.get(index).getVenueAddress());
				dataRow.createCell(8).setCellValue(eventList.get(index).getEventDate());
				dataRow.createCell(9).setCellValue(eventList.get(index).getTotalVolunteers());
				dataRow.createCell(10).setCellValue(eventList.get(index).getTotalVolunteerHours());
				dataRow.createCell(11).setCellValue(eventList.get(index).getTotalTravelHours());
				dataRow.createCell(12).setCellValue(eventList.get(index).getProject());
				dataRow.createCell(13).setCellValue(eventList.get(index).getCategory());
				dataRow.createCell(14).setCellValue(eventList.get(index).getEventDescription());
				dataRow.createCell(15).setCellValue(eventList.get(index).getOverallVolunteeringHours());
				dataRow.createCell(16).setCellValue(eventList.get(index).getLivesImpacted());
				dataRow.createCell(17).setCellValue(eventList.get(index).getActivityType());

			});

			IntStream.range(0, 18).forEach(i -> {
				sheet.autoSizeColumn(i);
			});

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
