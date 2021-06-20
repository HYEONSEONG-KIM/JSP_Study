package kr.or.ddit.capchar;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 네이버 캡차 API 예제 - 캡차 이미지 수신
public class ApiExamCaptchaImage {

    public String getImageName(String getKey, String path) {
    	String clientId = "8QmjmYiEJMrhyGHrDvCI";
        String clientSecret = "J5dy8izeWq"; 

        String key = getKey; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
        String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders, path);

        return responseBody;
    }

    private  String get(String apiUrl, Map<String, String> requestHeaders, String path){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return getImage(con.getInputStream(), path);
            } else { // 에러 발생
                return error(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private  HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private  String getImage(InputStream is, String path){
        int read;
        byte[] bytes = new byte[1024];
        // 랜덤한 이름으로  파일 생성
        String filename = Long.valueOf(new Date().getTime()).toString();
        File f = new File(path + filename + ".jpg");
        try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try(OutputStream outputStream = new FileOutputStream(f)){
            f.createNewFile();
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("이미지 캡차 파일 생성에 실패 했습니다.",e);
        }
    }

    private static String error(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}