package edu.fpdual.email.sender;

import edu.fpdual.email.sender.enums.MailCredentialProperties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lombok.Getter;
import lombok.Setter;

/**
 * Email sender example with and without attachment.
 *
 * @author jose.m.prieto.villar
 * @version 1.0
 */
public class Sender {

  @Setter @Getter Properties mailProp = new Properties();

  @Setter @Getter Properties credentialProp = new Properties();

  /** Build the sender class loading the properties from mail and credentials files. */
  public Sender() {
    try {
      // Loads all the properties of file "mail.properties".
      String workingDir = System.getProperty("user.dir");
      System.out.println("Directorio actual: " + workingDir);
      credentialProp.load(
          getClass().getClassLoader().getResourceAsStream("credentials.properties"));
      mailProp.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send an email with from and recipient address, subject, d a simple HTML format content and an
   * attached file.
   *
   * @param from from email address
   * @param to recipient email address
   * @param subject email subject
   * @param text email content in html format
   * @param content path where the temp file is located
   * @return a {@link boolean} indicating if the email was sent or not.
   */
  public boolean send(String from, String to, String subject, String text, String content)
      throws FileNotFoundException, IOException {
    // Get the Session object.// and pass username and password
    Session session = createSession();
    try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
      message.setSubject(subject);

      // Attach a file.
      // First Part of the body: text
      BodyPart texto = new MimeBodyPart();
      texto.setContent(text, "text/html");

      // Second Part of the body: project properties file.
      BodyPart adjunto = new MimeBodyPart();
      adjunto.setDataHandler(new DataHandler(new FileDataSource(content)));
      String[] partiendoNombre = content.split("/");
      String nombreAdjunto = partiendoNombre[partiendoNombre.length - 1];
      adjunto.setFileName(nombreAdjunto);

      // Group all part in a object
      Multipart multiPart = new MimeMultipart();
      multiPart.addBodyPart(texto);
      multiPart.addBodyPart(adjunto);

      // Set Message Content
      message.setContent(multiPart);

      System.out.println("sending...");
      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
      return true;
    } catch (MessagingException mex) {
      mex.printStackTrace();
      return false;
    }
  }

  private Session createSession() {
    Session session =
        Session.getInstance(
            mailProp,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    credentialProp.getProperty(MailCredentialProperties.USER.getName()),
                    credentialProp.getProperty(MailCredentialProperties.PASSWORD.getName()));
              }
            });

    // Used to debug SMTP issues
    session.setDebug(true);
    return session;
  }
}
