package com.universidad.proyecto.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.universidad.proyecto.model.Student;
import lombok.extern.slf4j.Slf4j;

import static com.universidad.proyecto.util.AppConstants.CERTIFICATE_TITLE;

import java.io.ByteArrayOutputStream;

@Slf4j
public final class PDFGeneratorUtil {

    private PDFGeneratorUtil() {
    }

    public static byte[] buildCertificate(Student student) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            document.add(new Paragraph(AppConstants.CERTIFICATE_TITLE, titleFont));
            document.add(new Paragraph(" ", bodyFont));
            document.add(new Paragraph("Se certifica que:", bodyFont));
            document.add(new Paragraph(student.getFirstName() + " " + student.getLastName(), titleFont));
            document.add(new Paragraph("Programa: " + defaultValue(student.getProgram()), bodyFont));
            document.add(new Paragraph("Documento: " + defaultValue(student.getDocumentNumber()), bodyFont));
            if (student.getDateOfBirth() != null) {
                document.add(new Paragraph("Fecha de nacimiento: " + student.getDateOfBirth(), bodyFont));
            }
            document.close();
            return baos.toByteArray();
        } catch (DocumentException e) {
            log.error("Error al generar certificado", e);
            throw new IllegalStateException("No fue posible generar el certificado PDF", e);
        } catch (Exception e) {
            log.error("Error inesperado generando PDF", e);
            throw new IllegalStateException("Error inesperado generando el certificado", e);
        }
    }

    private static String defaultValue(String value) {
        return value == null ? "N/D" : value;
    }
}

