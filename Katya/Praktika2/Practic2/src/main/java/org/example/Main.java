package org.example;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.utils.PageContentStreamOptimized;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Main {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_Driver = "org.postgresql.Driver";
    private static final float BOTTOM_MARGIN = 15 * 72 / 25.4f;
    private static final float LEFT_MARGIN = 15 * 72 / 25.4f;
    private static final float RIGHT_MARGIN = 15 * 72 / 25.4f;
    private static List<Consumer> consumers = new ArrayList<>();
    private static final String dataFile = "consumers.txt";

    public static void main(String[] args) throws IOException
    {
        List<Consumer> consumers = generateRandomConsumers(20);

        removeRandomConsumers(consumers, 5);

        System.out.println("Удалены следующие записи:");
        for (Consumer removedConsumer : removedConsumers) {
            System.out.println("ФИО: " + removedConsumer.getFio() + ", Адрес: " + removedConsumer.getAdress());
        }

        PDDocument pdDoc = new PDDocument();
        Scanner scanner = new Scanner(System.in);

        PDPage page = new PDPage(PDRectangle.A4);
        PDRectangle rectangle = page.getMediaBox();
        pdDoc.addPage(page);

        float contentWidth = rectangle.getWidth() - LEFT_MARGIN - RIGHT_MARGIN;

        BaseTable table = new BaseTable(760, 0, BOTTOM_MARGIN, contentWidth, LEFT_MARGIN, pdDoc, page, true, true);

        InputStream fontStream = Main.class.getClassLoader().getResourceAsStream("arial.ttf");
        PDFont font = PDType0Font.load(pdDoc, fontStream, true);

        for (Consumer consumer : consumers) {
            Row<PDPage> row = table.createRow(0);
            createCell(row, 50, consumer.getFio(), font);
            createCell(row, 50, consumer.getAdress(), font);
        }

        int fontSize = 12;
        String text = "Оставшиеся потребители";
        PDPageContentStream cos = new PDPageContentStream(pdDoc, pdDoc.getPage(0));
        PageContentStreamOptimized pcos = new PageContentStreamOptimized(cos);

        float width = font.getStringWidth(text) * fontSize / 1000;
        float pageWidth = rectangle.getWidth();
        cos.beginText();
        cos.setFont(font, fontSize);
        cos.newLineAtOffset((pageWidth - width) / 2, 775);
        cos.showText(text);
        cos.endText();

        text = "Ушедшие потребители";
        cos.beginText();
        cos.setFont(font, fontSize);
        cos.newLineAtOffset((pageWidth - width) / 2, 415);
        cos.showText(text);
        cos.endText();


        InputStream imageStream = Main.class.getClassLoader().getResourceAsStream("picture.png");
        Image img =  new Image(ImageIO.read(imageStream)).scaleByWidth(250);

        table.draw();
        img.draw(pdDoc, pcos, 175, 175);
        drawQR(pdDoc, pcos, 250 , 250);

        BaseTable table2 = new BaseTable(400, 0, BOTTOM_MARGIN - 300, contentWidth, LEFT_MARGIN, pdDoc, page, true, true);

        for (Consumer removedConsumer : removedConsumers) {
            Row<PDPage> row = table2.createRow(0);
            createCell(row, 50, removedConsumer.getFio(), font);
            createCell(row, 50, removedConsumer.getAdress(), font);
        }

        table2.draw();
        cos.close();
        try {
            File file = new File("File.pdf");
            pdDoc.save(file);
            System.out.println("Файл успешно создан.");
        } catch (IOException e) {
            System.err.println("Произошла ошибка при создании файла: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    static private void createCell(Row<PDPage> row, int width, String value, PDFont font) {
        Cell<PDPage> cell = row.createCell(width, value);
        cell.setFont(font);
    }

    static private List<Consumer> generateRandomConsumers(int count) {
        List<Consumer> consumers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String fio = generateRandomFIO();
            String address = generateRandomAddress();
            consumers.add(new Consumer(fio, address));
        }

        return consumers;
    }

    static private String generateRandomFIO() {
        String[] firstNames = {
                "Иван", "Петр", "Анна", "Елена", "Михаил", "Ольга", "Алексей", "Татьяна", "Сергей", "Алина", "Ирина", "Дмитрий"
        };
        String[] lastNames = {
                "Иванов", "Петров", "Сидоров", "Михайлов", "Смирнов", "Александров", "Кузнецов", "Васильев", "Егоров"
        };
        String[] middleNames = {
                "Иванович", "Петрович", "Андреевич", "Михайлович", "Сергеевна", "Александрович", "Дмитриевич", "Алексеевна"
        };

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String middleName = middleNames[random.nextInt(middleNames.length)];

        return lastName + " " + firstName + " " + middleName;
    }

    static private String generateRandomAddress() {
        String[] streets = {
                "Улица Пушкина", "Улица Ленина", "Улица Гагарина", "Улица Советская", "Проспект Мира",
                "Проспект Ленина", "Проспект Гагарина", "Площадь Революции", "Улица Чайковского", "Улица Горького"
        };
        String[] cities = {
                "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань",
                "Самара", "Омск", "Волгоград", "Красноярск", "Пермь"
        };
        Random random = new Random();
        String city = cities[random.nextInt(cities.length)];
        String street = streets[random.nextInt(streets.length)];
        int houseNumber = random.nextInt(100) + 1;

        return city + ", " + street + ", дом " + houseNumber;
    }

    static List<Consumer> removedConsumers = new ArrayList<>();
    static private void removeRandomConsumers(List<Consumer> consumers, int count) {
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(consumers.size());
            Consumer removedConsumer = consumers.remove(randomIndex);
            removedConsumers.add(removedConsumer);
        }
    }

    private static void drawQR(PDDocument pdDoc, PageContentStreamOptimized stream, float x, float y) throws IOException {
        StringBuffer stringBuf = new StringBuffer("https://animego.org/anime/kollekciya-dzyundzi-ito-188");
        InputStream inputStream = new ByteArrayInputStream(generateQR(stringBuf.toString()));
        BufferedImage bufImage = ImageIO.read(inputStream);
        Image img = new Image(bufImage);
        img.draw(pdDoc, stream, x, y);
    }
    private static byte[] generateQR(String content) throws IOException {
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.H;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        if (errorCorrectionLevel != null) {
            hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        }

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 75, 75, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        assert bitMatrix != null;
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", arrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            arrayOutputStream.close();
        }
        return arrayOutputStream.toByteArray();
    }
}