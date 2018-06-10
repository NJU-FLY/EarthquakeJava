package earthquake.test.testCode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/11.
 */
public class earthquake {
    public Document getDocument (String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        baike data = new baike();
//        Document doc = data.getDocument("http://news.ceic.ac.cn/CC20160121011313.html");
//        Elements ul = doc.select(".prop-ul");
//        Elements lis = ul.get(0).select("li");
//        for(Element li:lis){
//            String text = li.text();
//            System.out.println(text);
//        }

        Document weather = data.getDocument("http://www.weather.com.cn/weather/101180607.shtml");
        Element ul = weather.select(".c7d").get(0).getElementsByTag("ul").get(0);
        Elements lis = ul.getElementsByTag("li");
        for(Element li:lis){
            String date = li.getElementsByTag("h1").get(0).text();
            String wea = li.getElementsByTag("p").get(0).text();
            String temp = li.getElementsByTag("p").get(1).text();
            System.out.println(date);
            System.out.println(temp);
            System.out.println(wea);
        }

    }
}
