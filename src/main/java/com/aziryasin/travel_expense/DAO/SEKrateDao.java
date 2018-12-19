package com.aziryasin.travel_expense.DAO;

import com.aziryasin.travel_expense.Entity.SEKRateResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class SEKrateDao {

    final static String sekRateFileName="CurrencyRateCurrentMonth.xls";
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public SEKRateResponse getSEKrate(String currencyCode, String date){
        try {
            Date parsedDate = sdf.parse(date);
            SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(print.format(parsedDate));

                try{
                    FileInputStream fsIn = new FileInputStream(sekRateFileName);
                    Workbook workbook=WorkbookFactory.create(fsIn);
                    String sheetName=getSheetName(workbook,print.format(parsedDate));


                    if(sheetName.equals("")){
                        System.out.println("Enter a valid date");
                        return new SEKRateResponse("Failed","Date you entered is incorrect",0);
                    }else{
                        System.out.println(sheetName +" sheet is opened");
                        Sheet sheet=workbook.getSheet(sheetName);

                        Row row=sheet.getRow(10);
                        Cell cell=row.getCell(9);
                        System.out.println(cell.getNumericCellValue());
                        System.out.println(sheet.getRow(getRowForCountry(sheet,currencyCode)).getCell(26).getNumericCellValue());
                        return new SEKRateResponse("Success","1 "+currencyCode+" equals in SEK", ((float) sheet.getRow(getRowForCountry(sheet, currencyCode)).getCell(26).getNumericCellValue()));
                    }

                }catch (InvalidFormatException e){
                    e.printStackTrace();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return new SEKRateResponse("Failed","No value for the entered data",0);
    }

    private int getRowForCountry(Sheet sheet,String currencyCode){
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals("1 "+currencyCode)) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return 0;
    }

    private String getSheetName(Workbook workbook,String date){
        String sheetNameStart="";
        int year=Integer.parseInt(date.trim().substring(0,4));
        int month=Integer.parseInt(date.trim().substring(5,7))-1;
        if(month==0){
            year=year-1;
            month=12;
        }
        if(month<10 ){
            sheetNameStart=year+"0"+month;
        }else{
            sheetNameStart=year+""+month;
        }

        for(int i=workbook.getNumberOfSheets()-1; i>=0; i--) {
            if (workbook.getSheetName(i).substring(0, 6).equals(sheetNameStart)) {
                return workbook.getSheetName(i);
            }
        }
        return "";
    }


    //Running scheduled Task for at 00.00 on 1st day of every month,12.00 on 1st day of every month & 00.00 on 2nd day of every month
    @Scheduled(cron = "0 0 1 1 1/1 ?")
    @Scheduled(cron = "0 0 12 1 1/1 ?")
    @Scheduled(cron = "0 0 0 2 1/1 ?")
    public static void getFile(){
        System.out.println("Cron Job Started at "+ new Date(System.currentTimeMillis()));
        try {
            String url = "http://wis.ifsworld.com/internal/corporate/corporate_finance/";
            Document document = Jsoup.connect(url).get();
            Element currencyRate = document.select("a[href~=^.*?Currency%20rates.*?\\.xls]").first();
            String endPoint = currencyRate.attr("href");
            try {
                BufferedInputStream in = new BufferedInputStream(new URL(url + endPoint).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(sekRateFileName);
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
