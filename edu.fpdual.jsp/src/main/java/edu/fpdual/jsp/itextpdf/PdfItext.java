package edu.fpdual.jsp.itextpdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import edu.fpdual.jsp.web.servlet.sendMailServlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PdfItext {

  public void createPDF(String fileName, String nombre, String email, String text)
      throws IOException, DocumentException, URISyntaxException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));

    document.open();

    Paragraph paragraph = createParagraph(text);

    PdfPTable pdfPTable = new PdfPTable(2);
    pdfPTable.setSpacingBefore(10f);

    document.add(Chunk.NEWLINE);
    addTableHeaders(pdfPTable);
    addTableSimpleRows(pdfPTable, nombre, email);
    document.add(pdfPTable);
    document.add(paragraph);
    document.add(Chunk.NEWLINE);
    document.close();
  }

  private Paragraph createParagraph(String text) {
    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    // Chunk chunk = new Chunk(text, font);
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setSpacingAfter(10f);
    return paragraph;
  }

  private void addTableHeaders(PdfPTable pdfPTable) {
    Stream.of("Nombre", "Correo")
        .forEach(
            nombreColumna -> {
              PdfPCell header = new PdfPCell();
              header.setBackgroundColor(BaseColor.LIGHT_GRAY);
              header.setBorderWidth(2);
              header.setPhrase(new Phrase(nombreColumna));
              pdfPTable.addCell(header);
            });
  }

  private void addTableSimpleRows(PdfPTable pdfPTable, String nombre, String email)
      throws URISyntaxException, BadElementException, IOException {
    pdfPTable.addCell(nombre);
    pdfPTable.addCell(email);
  }
}
