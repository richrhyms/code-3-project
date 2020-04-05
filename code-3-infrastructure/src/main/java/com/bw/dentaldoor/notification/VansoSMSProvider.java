package com.bw.dentaldoor.notification;

import com.bw.utils.sms.SMSSender;
import com.bw.utils.sms.SMSSenderFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class VansoSMSProvider implements SMSSender {

    private static final String CONTENT_TYPE = "text/xml; charset=iso-8859-1";
    private static final String ACCESPTED_LANGUAGES = "en-US,en;q=0.5";
    private static final String USER_AGENT = "Mozilla/5.0";
    private String username;
    private String password;
    private String url;
    public static String template = "<?xml version=\"1.0\"?><operation type=\"submit\"><account username=\"%s\" password=\"%s\"/><submitRequest> <deliveryReport>false</deliveryReport> <sourceAddress type=\"alphanumeric\">%s</sourceAddress> <destinationAddress type=\"international\">%s</destinationAddress> <text encoding=\"ISO-8859-1\">%s</text></submitRequest></operation>";

    public VansoSMSProvider(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public void sendSms(String[] recipients, String message, String from) {
        String[] var4 = recipients;
        int var5 = recipients.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String recipient = var4[var6];

            try {
                String requestMessage = this.generateRequestBody(from, recipient, this.toHex(message));
                URL obj = new URL(this.url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("Content-Type", "text/xml; charset=iso-8859-1");
                con.setRequestProperty("Content-Length", String.format(Locale.ENGLISH, "%d", requestMessage.getBytes().length));
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(requestMessage);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                if (SMSSenderFactory.loggingEnabled) {
                    System.out.println("\nSending 'POST' request to URL : " + this.url);
                    System.out.println("Post parameters : " + requestMessage);
                    System.out.println("Response Code : " + responseCode);
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();

                String inputLine;
                while((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                if (SMSSenderFactory.loggingEnabled) {
                    System.out.println(response.toString());
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            }
        }

    }

    private String generateRequestBody(String source, String destination, String message) {
        return String.format(Locale.ENGLISH, template, this.username, this.password, source, destination, message);
    }

    public String toHex(String arg) throws UnsupportedEncodingException {
        return String.format("%x", new BigInteger(1, arg.getBytes("iso-8859-1")));
    }
}
