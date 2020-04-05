/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @param <E>
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public abstract class ExcelDataExtractor<E> {

    private static Logger logger = LoggerFactory.getLogger(ExcelDataExtractor.class);

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#");

    private int sheet;
    private int startRow;
    private int endRow = -1;
    private int headerRow;

    private HashMap<String, Integer> headerColumns;

    public int getSheet() {
        return sheet;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    public int getStartRow() {
        return startRow;
    }


    public int getHeaderRow() {
        return headerRow;
    }

    public void setHeaderRow(int headerRow) {
        this.headerRow = headerRow;
    }

    public HashMap<String, Integer> getHeaderColumns() {
        return headerColumns;
    }

    public void setHeaderColumns(HashMap<String, Integer> headerColumns) {
        this.headerColumns = headerColumns;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public static boolean isBlank(Cell cell) {
        if (cell == null) {
            return true;
        }
        return cell.getCellType() == CellType.BLANK;
    }

    public static String getString(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return DECIMAL_FORMAT.format(cell.getNumericCellValue());
            default:
                return StringUtils.isNotBlank(cell.getStringCellValue()) ? cell.getStringCellValue().trim() : null;
        }
    }

    public static Boolean getBoolean(Cell cell) {
        switch (cell.getCellType()) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return StringUtils.isNotBlank(cell.getStringCellValue()) ? Boolean.valueOf(cell.getStringCellValue().trim().toLowerCase()) : null;
        }
    }

    public static Double getDouble(Cell cell) {
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            default:
                return 0.0;
        }
    }

    public abstract E deserialize(Row row);

    public void deserializeHeaderRow(Row row) {
        //set header columns
        if (row.getRowNum() == this.getHeaderRow()) {
            HashMap<String, Integer> headerColumns = new HashMap<>();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                headerColumns.put(ExcelDataExtractor.getString(cell), cell.getColumnIndex());
                this.setHeaderColumns(headerColumns);
            }
//            this.getHeaderColumns().forEach((k,v)->System.out.println("Column Header : " + k + " | Column Index : " + v));
        }
    }

    public static class RowResult<E> {

        private int index;
        private final E value;
        private Exception exception;
        private Integer errorColumn;

        public RowResult(int index, E value) {
            this.index = index;
            this.value = value;
            this.exception = null;
        }

        public RowResult(int index, Exception exception) {
            this.index = index;
            this.value = null;
            this.exception = exception;
        }

        public RowResult(Exception exception, int column) {
            this.value = null;
            this.exception = exception;
            this.errorColumn = column;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Integer getErrorColumn() {
            return errorColumn;
        }

        public E getValue() {
            return value;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }

        public Exception getException() {
            return exception;
        }
    }
}
