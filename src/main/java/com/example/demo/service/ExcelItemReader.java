package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Emp;
@Component
@StepScope
public class ExcelItemReader implements ItemReader<Emp> {

	private Iterator<Emp> dataIterator;

//	private final String fileName;

	public ExcelItemReader(@Value("#{jobParameters['fileName']}") String fileName) {
		this.dataIterator = loadExcelData(fileName).iterator();
//		this.fileName = fileName;
	}

	@Override
	public Emp read() {
		if (dataIterator != null && dataIterator.hasNext()) {
			return dataIterator.next();
		} else {
			return null;
		}
	}

	private List<Emp> loadExcelData(String fileName) {
		List<Emp> dataList = new ArrayList<>();
//        try (FileInputStream fis = new FileInputStream(new File(fileName));
//             Workbook workbook = WorkbookFactory.create(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0); // Reading the first sheet
//            for (Row row : sheet) {
//                Emp data = new Emp();
//                data.setName("rajesh");
//                data.setDescription("Brother");
////                data.setField1(row.getCell(0).getStringCellValue());
////                data.setField2(row.getCell(1).getStringCellValue());
//                // Set other fields
//                dataList.add(data);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
		Emp data = new Emp();
		data.setName("rajesh");
		data.setDescription("Brother");
		data.setId(Long.valueOf(new Random().nextLong()));
		dataList.add(data);
		return dataList;
	}
}
