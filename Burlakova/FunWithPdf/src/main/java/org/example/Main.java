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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    enum Operation {
        exit,
        reverse,
        sortfio,
        shuffle,
        findmaxage,
        findminage
    }
    private static final float BOTTOM_MARGIN = 15 * 72 / 25.4f;
    private static final float LEFT_MARGIN = 15 * 72 / 25.4f;
    private static final float RIGHT_MARGIN = 15 * 72 / 25.4f;

    public static void main(String[] args) throws IOException {

        // Создаем объект "o" и несколько объектов "Consumer"
        Object o = new Consumer("впвпвп",16,"Какой то адрес");
        System.out.println("Hello world!");

        Consumer cons1 = new Consumer("zБурлакова Ангелина Евгеньевна",13,"Какой то адрес");
        Consumer cons2 = new Consumer("sjgjhh",11,"Какой то  ещё адрес");
        Consumer cons3= new Consumer("ААА",12,"И ещё адрес");



        PDDocument pdDoc = new PDDocument();
        Scanner scanner = new Scanner(System.in);

        PDPage page = new PDPage(PDRectangle.A4);
        PDRectangle rectangle = page.getMediaBox();
        pdDoc.addPage(page);

        float contentWidth = rectangle.getWidth() - LEFT_MARGIN - RIGHT_MARGIN;

        BaseTable table = new BaseTable(745, 0,
                BOTTOM_MARGIN, contentWidth, LEFT_MARGIN, pdDoc, page, true, true);

        // Создаем список и хеш-множество объектов Consumer
        List<Consumer> consumers = new ArrayList<Consumer>();

        consumers.add(cons1);
        consumers.add(cons2);
        consumers.add(cons3);
        consumers.add((Consumer)o);

        Set<Consumer> consumersSet = new HashSet<>();

        consumersSet.add(cons1);
        consumersSet.add(cons2);
        consumersSet.add(cons3);
        consumersSet.add((Consumer) o);

        int a;
        Operation oper;

        do {
            System.out.println("1. Перевернуть список");
            System.out.println("2. Отсортировать по ФИО");
            System.out.println("3. Перемешать список");
            System.out.println("4. Найти максимальный возраст");
            System.out.println("5. Найти минимальный возраст");
            System.out.println("0. Выход");

            a = scanner.nextInt();

            switch (a) {
                case 0:
                    oper = Operation. exit;
                    System.out.println("Программа завершена.");
                    break;
                case 1:
                    oper = Operation.reverse;
                    Collections.reverse(consumers);
                    System.out.println("Список перевернут.");
                    break;
                case 2:
                    oper = Operation.sortfio;
                    Collections.sort(consumers, Comparator.comparing(Consumer::getFio));
                    System.out.println("Список отсортирован по ФИО.");
                    break;
                case 3:
                    oper = Operation.shuffle;
                    Collections.shuffle(consumers);
                    System.out.println("Список перемешан случайным образом.");
                    break;
                case 4:
                    oper = Operation.findmaxage;
                    Consumer maxAgeConsumer = Collections.max(consumers, Comparator.comparingInt(Consumer::getAge));
                    System.out.println("Максимальный возраст: " + maxAgeConsumer.getAge());
                    break;
                case 5:
                    oper = Operation.findminage;
                    Consumer minAgeConsumer = Collections.min(consumers, Comparator.comparingInt(Consumer::getAge));
                    System.out.println("Минимальный возраст: " + minAgeConsumer.getAge());
                    break;
                default:
                    oper = null;
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }
        } while (oper != Operation.exit);

        // Создаем итератор для списка и удаляем элемент "ААА" из списка
        Iterator<Consumer> consIterator = consumers.iterator();//создаем итератор
        while(consIterator.hasNext()) {//до тех пор, пока в списке есть элементы

            Consumer nextCat = consIterator.next();//получаем следующий элемент
            if (nextCat.getFio().equals("ААА")) {
                consIterator.remove();//удаляем элемент с нужным именем
            }
        }

        InputStream fontStream =  Files.newInputStream(Path.of("C:\\Users\\gelya\\IdeaProjects\\FunWithPdf\\src\\main\\resources\\arial.ttf"));
        PDFont font = PDType0Font.load(pdDoc, fontStream, true);

        PDPageContentStream cos = new PDPageContentStream(pdDoc, page);
        InputStream pngStream = Main.class.getClassLoader().getResourceAsStream("wallpaper(2).jpg");
        PageContentStreamOptimized pcos = new PageContentStreamOptimized(cos);
        Image image = new Image(ImageIO.read(pngStream)).scaleByWidth(50);
        image.draw(pdDoc, pcos, 50, 50);

        //Создание QR кода
        StringBuffer sb = new StringBuffer();
        sb.append("Hello world1\n");
        sb.append("Hello world2\n");
        sb.append("Hello world3\n");
        sb.append("https://www.youtube.com/\n");

        InputStream qrInputStream = new ByteArrayInputStream(createQR(ErrorCorrectionLevel.H, sb.toString()));

        BufferedImage bi = null;
        try {
            bi = ImageIO.read(qrInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            qrInputStream.close();
        }

        Image qrImg = new Image(bi);

        qrImg.draw(pdDoc, pcos,  400, 400);

        //~Создание QR кода

        // Обход элементов в списке consumers с использованием цикла for-each
        for(Consumer consumer: consumers)
        {
            Row<PDPage> row = table.createRow(0);
            createCell(row, 50, consumer.getFio(), font);
            createCell(row, 50, consumer.getAdress(), font);
            createCell(row, 50, String.valueOf(consumer.getAge()), font);
        }
        // Обход элементов в HashSet consumersSet с использованием цикла for-each
        for(Consumer consumer: consumersSet)
        {
            Row<PDPage> row = table.createRow(0);
            createCell(row, 50, consumer.getFio(), font);
            createCell(row, 50, consumer.getAdress(), font);
            createCell(row, 50, String.valueOf(consumer.getAge()), font);
        }


        int fontSize = 12;
        String text = "test";

        float width = font.getStringWidth(text) * fontSize / 1000;
        float pageWidth = rectangle.getWidth();
        cos.beginText();
        cos.setFont(font, fontSize);
        cos.newLineAtOffset((pageWidth - width) / 2, 500);
        cos.showText(text);
        cos.endText();
        cos.close();


        table.draw();
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

    static private void createCell(Row<PDPage> row, int width, String value, PDFont font)
    {
        Cell<PDPage> cell = row.createCell(width, value);
        cell.setFont(font);
    }

    private static byte[] createQR(ErrorCorrectionLevel errorCorrectionLevel, String content) throws IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        if (errorCorrectionLevel != null) {
            hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        }

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
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
    private static void loadQrOnPage(){

    }
}