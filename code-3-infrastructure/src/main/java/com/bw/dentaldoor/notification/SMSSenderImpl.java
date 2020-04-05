package com.bw.dentaldoor.notification;


import com.bw.utils.sms.SMSSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class SMSSenderImpl implements SMSSender, Runnable {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    static Executor executor = Executors.newSingleThreadExecutor();

    private String[] recipients;
    String uName = "";
    String pWord = "";
    String smsGateway = "";
    String encodedMsg = "";
    String sendingURL = "";
    private String from = "";
    private String message = "";

    public SMSSenderImpl(String smsGateway, String pWord, String uName) {
        this.pWord = pWord;
        this.uName = uName;
        this.smsGateway = smsGateway;
    }

    public void sendSms(String[] recipients, String message, String from) {
        this.recipients = recipients;
        this.message = message;
        this.from = from;
        executor.execute(this::run);
    }

    public void run() {
        logger.info("sending sms");

        try {
            this.encodedMsg = URLEncoder.encode(this.message, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex.getMessage(), ex);
        }

        String to = Arrays.toString(this.recipients).replace("[", "").replace("]", "").replace(" ", "");
        this.sendingURL = this.smsGateway + "username=" + this.uName + "&password=" + this.pWord + "&sender=" + this.from + "&recipient=" + to + "&message=" + this.encodedMsg;
        logger.info(String.format("-------------------->>>>>>> %s", this.sendingURL));

        try {
            URL messageURL = new URL(this.sendingURL);

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(messageURL.openStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }

                bufferedReader.close();
                logger.info(response.toString());
                logger.info(response.toString());
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        } catch (MalformedURLException var8) {
            logger.info("Malformed URL Exception, please contact sms service provider");
            var8.printStackTrace();
        }

    }
}
