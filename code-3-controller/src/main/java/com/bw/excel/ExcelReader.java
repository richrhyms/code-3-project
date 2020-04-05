///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bw.excel;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Named;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
// */
//@Named
//public class ExcelReader {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    public <E> List<ExcelDataExtractor.RowResult<E>> readAll(
//            InputStream stream,
//            ExcelDataExtractor<E> descriptor,
//            RowResultEventHandler<E> rowResultEventHandler) throws IOException {
//        return readAll(WorkbookFactory.create(stream), descriptor, rowResultEventHandler);
//    }
//
//    public <E> List<ExcelDataExtractor.RowResult<E>> readAll(Workbook workbook, ExcelDataExtractor<E> descriptor,
//                                                             RowResultEventHandler<E> rowResultEventHandler) {
//        List<ExcelDataExtractor.RowResult<E>> list = new ArrayList<>();
//        Sheet sheet = workbook.getSheetAt(descriptor.getSheet());
//        Iterator<Row> rowIterator = sheet.iterator();
//        for (int i = 0; i < descriptor.getStartRow() && rowIterator.hasNext(); i++) {
//            rowIterator.next();
//        }
//        int index = descriptor.getStartRow();
//        list = read(descriptor, list, rowResultEventHandler, rowIterator, index);
//        return list;
//    }
//
//    public <E> List<ExcelDataExtractor.RowResult<E>> readAll(Sheet sheet, ExcelDataExtractor<E> descriptor,
//                                                             RowResultEventHandler<E> rowResultEventHandler) {
//        List<ExcelDataExtractor.RowResult<E>> list = new ArrayList<>();
//        Iterator<Row> rowIterator = sheet.iterator();
//        int index = descriptor.getStartRow();
//        list = read(descriptor, list, rowResultEventHandler, rowIterator, index);
//        return list;
//
//    }
//
//    private  <E> List<ExcelDataExtractor.RowResult<E>> read(ExcelDataExtractor<E> descriptor, List<ExcelDataExtractor.RowResult<E>> list,
//                                                            RowResultEventHandler<E> rowResultEventHandler, Iterator<Row> rowIterator, int index) {
//
//        while (rowIterator.hasNext()) {
//            if (descriptor.getEndRow() > 0 && index > descriptor.getEndRow()) {
//                break;
//            }
//
//            Row row = rowIterator.next();
//
//            if (!isEmptyRow(row)) {
//                ExcelDataExtractor.RowResult<E> result;
//                try {
//                    if(descriptor.getHeaderRow() == row.getRowNum()) {
//                        descriptor.deserializeHeaderRow(row);
//                        continue;
//                    }
//                    result = new ExcelDataExtractor.RowResult<>(index, descriptor.deserialize(row));
//                } catch (Exception e) {
//                    result = new ExcelDataExtractor.RowResult<>(index, e);
//                }
//                boolean canContinue = rowResultEventHandler.onRowResult(index, row, result);
//                list.add(result);
//                if (!canContinue) {
//                    break;
//                }
//                index++;
//
//            }
//
//        }
//        return list;
//    }
//
//    private boolean isEmptyRow(Row row) {
//        boolean isEmptyRow = true;
//        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
//            Cell cell = row.getCell(cellNum);
//            if (cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
//                isEmptyRow = false;
//                break;
//            }
//        }
//        return isEmptyRow;
//    }
//}
