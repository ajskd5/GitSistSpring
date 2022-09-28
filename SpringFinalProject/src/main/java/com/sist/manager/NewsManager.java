package com.sist.manager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class NewsManager {
/*
 * {"lastBuildDate":"Wed, 28 Sep 2022 09:34:59 +0900",	"total":8525180,	"start":1,	"display":100,	
 * 		"items":[		{			"title":"카페24, 온라인몰 인공지능 광고 솔루션 강화…크리테오와 맞손",			
 * 		"originallink":"https:\/\/www.ddaily.co.kr\/news\/article.html?no=247566",	
 * 		"link":"https:\/\/n.news.naver.com\/mnews\/article\/138\/0002133473?sid=105",			
 * 		"description":"이를 통해 소비자 쇼핑 데이터를 분석하고, 수요가 높은 <b>상품<\/b>을 노출하는 광고 서비스를 고도화한다.양사는 카페24 플랫폼에서 온라인 쇼핑몰을 운영 중인 사업자를 위해 ▲전용 디스플레이 광고관리 서비스 ▲마케팅... ",			
 *  	"pubDate":"Wed, 28 Sep 2022 09:33:00 +0900"	
 *  }
 */
	// 메인이 아니라 호출했을때 String으로 결과값 가져오게 변경
    public String newsFind(String ss) {
        String clientId = "4bT2aPmvf4raEsprzjq0"; //애플리케이션 클라이언트 아이디
        String clientSecret = "ltwq3Du23E"; //애플리케이션 클라이언트 시크릿


        String text = null;
        try {
            text = URLEncoder.encode(ss, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        // default 결과값 = 10개 => display로 갯수 조절 (최대 100개)
        String apiURL = "https://openapi.naver.com/v1/search/news.json?display=100&query=" + text;    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


//       System.out.println(responseBody);
        return responseBody;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}