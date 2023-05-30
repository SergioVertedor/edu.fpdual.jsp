package edu.fpdual.itextpdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class PdfItext {
  /***
   * Crea un documento PDF con los parámetros que se le proporciona.
   * @param fileName String que contenga ruta del archivo y su extensión.
   * @param nombre String que contiene el nombre proporcionado en el formulario de contacto.
   * @param email String que contiene el email proporcionado en el formulario de contacto.
   * @param text String que contiene el cuerpo del mensaje proporcionado en el formulario de contacto.
   * @throws IOException
   * @throws DocumentException
   * @throws URISyntaxException
   */
  public void createPDF(String fileName, String nombre, String email, String text)
      throws IOException, DocumentException, URISyntaxException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(fileName));
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

  /***
   * Formatea el texto para que aparezca en el pdf con los datos proporcionados.
   * @param text String que contiene el email proporcionado en el formulario de contacto.
   * @return
   */
  private Paragraph createParagraph(String text) {
    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    // Chunk chunk = new Chunk(text, font);
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setSpacingAfter(10f);
    return paragraph;
  }

  /***
   * Construcción de la tabla, este método se encarga del header.
   * @param pdfPTable Objeto pdfTable encargado de la creación de la tabla proporcionado para
   *                  dar formato.
   */
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

  /***
   * Construcción de la tabla, este método se encarga del formato de los campos.
   * @param pdfPTable Objeto pdfTable encargado de la creación de la tabla proporcionado para
   *                  dar formato.
   * @param nombre String que contiene el nombre proporcionado en el formulario de contacto.
   * @param email String que contiene el email proporcionado en el formulario de contacto.
   * @throws URISyntaxException
   * @throws BadElementException
   * @throws IOException
   */
  private void addTableSimpleRows(PdfPTable pdfPTable, String nombre, String email)
      throws URISyntaxException, BadElementException, IOException {
    pdfPTable.addCell(nombre);
    pdfPTable.addCell(email);
  }
}
