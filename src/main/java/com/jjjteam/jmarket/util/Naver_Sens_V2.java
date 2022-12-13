package com.jjjteam.jmarket.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Slf4j
public class Naver_Sens_V2 {
    @SuppressWarnings("unchecked")
    public void send_msg(String tel, String rand) {
        String hostNameUrl = "https://sens.apigw.ntruss.com";// 호스트 URL
        String requestUrl= "/sms/v2/services/";// 요청 URL
        String requestUrlType = "/messages";// 요청 URL
        String accessKey = "b84mL1tPK7JNKG0N8Cic";// 개인 인증키
//        String secretKey = "6c57b211b27b458fa553fad2830fffe2";// 2차 인증을 위해 서비스마다 할당되는 service secret
        String secretKey = "1NF7mGMQZXOmFWcLfktJR3hjkpF5p4xWBXpla1gS";// 2차 인증을 위해 서비스마다 할당되는 service secret
        String serviceId = "ncp:sms:kr:263781166659:practice";// 프로젝트에 할당된 SMS 서비스 ID
        String method = "POST";// 요청 method
        String timestamp = Long.toString(System.currentTimeMillis());// current timestamp (epoch)
        requestUrl += serviceId + requestUrlType;
        String apiUrl = hostNameUrl + requestUrl;

        log.info(tel.replaceAll("\"", ""));
        // JSON 을 활용한 body data 생성

        JSONObject bodyJson = new JSONObject();
        JSONObject toJson = new JSONObject();
        JSONArray  toArr = new JSONArray();

        toJson.put("content","Jmarket 본인인증 ["+rand+"]");		// 난수와 함께 전송
        toJson.put("to",tel.replaceAll("\"", ""));
        toArr.add(toJson);

        bodyJson.put("type","sms");	// 메시지 Type (sms | lms)
        bodyJson.put("contentType","COMM");
        bodyJson.put("countryCode","82");
        bodyJson.put("from","01032347578");	// 발신번호 * 사전에 인증/등록된 번호만 사용할 수 있습니다.
        bodyJson.put("messages", toArr);
        bodyJson.put("content", "Web발신");


        String body = bodyJson.toJSONString();


        try {

            URL url = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==202) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public static String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";


        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey;
        String encodeBase64String;
        try {
            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        } catch (UnsupportedEncodingException e) {
            encodeBase64String = e.toString();
        }


        return encodeBase64String;
    }
}