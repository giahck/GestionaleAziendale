package GestionaleAziendale.GesionaleBack.ConfigurationApp;

import GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto;
import GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
@Component
public class PdfCrea {

    public byte[] createPdf(MachineGenericStatusDto machineDto) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Initialize PDF writer
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);

        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Add title
        Paragraph title = new Paragraph("Machine Details")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Add machine details
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        document.add(new Paragraph("Description: " + machineDto.getDescription()).setFont(font));
        document.add(new Paragraph("Matricola: " + machineDto.getMatricola()).setFont(font));
        document.add(new Paragraph("Nome Macchina: " + machineDto.getNomeMacchina()).setFont(font));
        document.add(new Paragraph("Marca: " + machineDto.getMarca()).setFont(font));
        document.add(new Paragraph("Modello: " + machineDto.getModello()).setFont(font));
        document.add(new Paragraph("Stato: " + machineDto.getStatoMaschine().name()).setFont(font));
        document.add(new Paragraph("\n")); // Blank line between sections

        // Close document
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}


   /* public byte[] createPdf(ListMaschinDto listMaschinDto) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Initialize PDF writer
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);

        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Add title
        Paragraph title = new Paragraph("Machine Details")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Add machine details
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        document.add(new Paragraph("ID: " + listMaschinDto.getId()).setFont(font));
        document.add(new Paragraph("Nome Macchina: " + listMaschinDto.getNomeMacchina()).setFont(font));
        document.add(new Paragraph("Marca: " + listMaschinDto.getMarca()).setFont(font));
        document.add(new Paragraph("Modello: " + listMaschinDto.getModello()).setFont(font));
        document.add(new Paragraph("Competenza: " + listMaschinDto.getCompetenza().toString()).setFont(font));
        document.add(new Paragraph("Description: " + listMaschinDto.getDescription()).setFont(font));

        // Add user details in a table
        document.add(new Paragraph("Users:").setFont(font).setBold());

        Table table = new Table(3);
        table.addCell("ID");
        table.addCell("Nome");
        table.addCell("Cognome");

        List<ListMaschinDto.UserNameDto> users = listMaschinDto.getUtenti();
        for (ListMaschinDto.UserNameDto user : users) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getNome());
            table.addCell(user.getCognome());
        }

        document.add(table);

        // Close document
        document.close();

        return byteArrayOutputStream.toByteArray();
    }*/

