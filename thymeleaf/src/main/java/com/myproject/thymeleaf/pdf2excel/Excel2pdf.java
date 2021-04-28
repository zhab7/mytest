package com.myproject.thymeleaf.pdf2excel;

import com.aspose.cells.License;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.pdf.Document;
import com.aspose.pdf.ExcelSaveOptions;
import javassist.*;

import java.io.*;

/**
 * @author zhanjianjian
 * @since 2021/2/20
 */
public class Excel2pdf {

    /*public static boolean getLicense() {
        boolean result = false;
        try {
            //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
//            InputStream is = Excel2pdf.class.getClassLoader().getResourceAsStream("../xml/license1.xml");
//            InputStream is = Excel2pdf.class.getClassLoader().getResourceAsStream("D:\\project\\thymeleaf\\mytest\\thymeleaf\\src\\main\\resources\\xml\\license.xml");
            InputStream inputStream = new FileInputStream("D:\\project\\thymeleaf\\mytest\\thymeleaf\\src\\main\\resources\\xml\\license1.xml");
            License aposeLic = new License();
//            aposeLic.setLicense(is);
            aposeLic.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/

    public static void excel2pdf(String address) {

        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        try {
            // 输出路径
            File pdfFile = new File("C:\\Users\\user\\Desktop\\2222.pdf");
            // 原始excel路径
            Workbook wb = new Workbook(address);
            FileOutputStream fos = new FileOutputStream(pdfFile);
            wb.save(fos, SaveFormat.PDF);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            // 风吹北巷南街伤，花落南国北亭凉。
        }
    }

    public static void main(String[] args) throws Exception {
//        Excel2pdf.excel2pdf("C:\\Users\\user\\Desktop\\ToExcel.xlsx");

        String sourceFilePath="C:\\Users\\user\\Desktop\\ToExcel.xlsx";
        String desFilePath="C:\\Users\\user\\Desktop\\sssaaaa.pdf";

        excel2pdf(sourceFilePath, desFilePath);

//        String desFilePath="C:\\Users\\user\\Desktop\\ToExcelss111s.xlsx";
//        String sourceFilePath="C:\\Users\\user\\Desktop\\6666.pdf";
//        pdf2excel(sourceFilePath, desFilePath);


    }

    public static void pdf2excel(String sourceFilePath, String desFilePath) throws Exception {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        Document document = new Document(sourceFilePath);
        Workbook wb = new Workbook(sourceFilePath);
        FileOutputStream fileOS = new FileOutputStream(desFilePath);
        ExcelSaveOptions excelsave = new ExcelSaveOptions();
        document.save(fileOS, excelsave);
    }

    public static void excel2pdf(String sourceFilePath, String desFilePath ){
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        try {
            Workbook wb = new Workbook(sourceFilePath);// 原始excel路径

            FileOutputStream fileOS = new FileOutputStream(desFilePath);
            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);


            int[] autoDrawSheets={3};
            //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
            autoDraw(wb,autoDrawSheets);

            int[] showSheets={0};
            //隐藏workbook中不需要的sheet页。
            printSheetPage(wb,showSheets);
            wb.save(fileOS, pdfSaveOptions);
            fileOS.flush();
            fileOS.close();
            System.out.println("完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取license 去除水印
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Excel2pdf.class.getClassLoader().getResourceAsStream("xml/license1.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置打印的sheet 自动拉伸比例
     * @param wb
     * @param page 自动拉伸的页的sheet数组
     */
    public static void autoDraw(Workbook wb,int[] page){
        if(null!=page&&page.length>0){
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).getHorizontalPageBreaks().clear();
                wb.getWorksheets().get(i).getVerticalPageBreaks().clear();
            }
        }
    }


    /**
     * 隐藏workbook中不需要的sheet页。
     * @param wb
     * @param page 显示页的sheet数组
     */
    public static void printSheetPage(Workbook wb,int[] page){
        for (int i= 1; i < wb.getWorksheets().getCount(); i++)  {
            wb.getWorksheets().get(i).setVisible(false);
        }
        if(null==page||page.length==0){
            wb.getWorksheets().get(0).setVisible(true);
        }else{
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).setVisible(true);
            }
        }
    }
}
