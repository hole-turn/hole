package com.xlh.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.itextpdf.text.pdf.PdfReader;
import lombok.Cleanup;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @author: xielinhao
 * @title: PdfUtil
 * @projectName: hole
 * @description:
 * @date: 15:33 2021/12/2
 */
public class PdfUtil {

    public static void main(String[] args) throws IOException {
        pdf2Image("D:\\file\\测试2.pdf",150);
//
//        System.out.println(pages);
    }
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PdfUtil.class);
    public static final int DEFAULT_DPI = 150;
    /***
     * PDF文件转PNG图片，全部页数
     *
     * @param pdfFilePath pdf完整路径
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢
     */
    public static void pdf2Image(String pdfFilePath, int dpi) {
        File file = new File(pdfFilePath);

        PDDocument pdDocument;
        try {
            String imgPdfPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            // 获取图片文件名
            String imagePdfName = file.getName().substring(0, dot);

            pdDocument = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /* dpi越大转换后越清晰，相对转换速度越慢 */
            PdfReader reader = new PdfReader(pdfFilePath);
            int pages = reader.getNumberOfPages();
            StringBuffer imgFilePath;
            for (int i = 0; i < pages; i++) {
                String imgFilePathPrefix = imgPdfPath + File.separator + imagePdfName;
//                String imgFilePathPrefix = "D:\\file\\测试";
                System.out.println(imgFilePathPrefix);
                imgFilePath = new StringBuffer();
                imgFilePath.append(imgFilePathPrefix);
                imgFilePath.append("_");
                imgFilePath.append((i + 1));
                imgFilePath.append(".png");
                File dstFile = new File(imgFilePath.toString());
                BufferedImage image = renderer.renderImageWithDPI(i, dpi);
//                ImageIO.write(image, "png", dstFile);
                InputStream is = null;
                @Cleanup ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageOutputStream imOut;
                try {
                    imOut = ImageIO.createImageOutputStream(bs);
                    ImageIO.write(image, "png",imOut);
                    is= new ByteArrayInputStream(bs.toByteArray());
                    System.out.println(is);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            log.info("PDF文档转PNG图片成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * pdf转图片
     * 多页PDF会每页转换为一张图片，下面会有多页组合成一页的方法
     *
     * @param pdfFile pdf文件路径
     * @param outPath 图片输出路径
     * @param dpi 相当于图片的分辨率，值越大越清晰，但是转换时间变长
     */
    public static void pdf2multiImage(String pdfFile, String outPath, int dpi) {
        if (ObjectUtil.isEmpty(dpi)) {
            // 如果没有设置DPI，默认设置为150
            dpi = DEFAULT_DPI;
        }
        try (PDDocument pdf = PDDocument.load(new FileInputStream(pdfFile))) {
            int actSize = pdf.getNumberOfPages();
            List<BufferedImage> picList = Lists.newArrayList();
            for (int i = 0; i < actSize; i++) {
                BufferedImage image = new PDFRenderer(pdf).renderImageWithDPI(i, dpi, ImageType.RGB);
                picList.add(image);
            }
            // 组合图片
            ImageUtil.yPic(picList, outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
