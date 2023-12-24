package org.example;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FormatDocx {
    public static void main(String[] args) throws Docx4JException, IOException {

        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
        mainDocumentPart.addParagraphOfText("Welcome");

        try(OutputStream outputStream = Files.newOutputStream(Path.of("test.docx"))){
            wordPackage.save(outputStream);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
