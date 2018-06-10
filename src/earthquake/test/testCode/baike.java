package earthquake.test.testCode;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


/**
 * Created by Administrator on 2017/7/14.
 */
public class baike {
    public  Document getDocument (String url){
                 try {
                         return Jsoup.connect(url).get();
                   } catch (IOException e) {
                        e.printStackTrace();
                     }
               return null;
           }
    //发送http请求
    public static String getEntity(String url) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.89 Safari/537.36");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String resText = EntityUtils.toString(entity, "utf-8");
        return resText;
    }
    public void getBaike(String location)throws IOException {
        String requestStr = "http://baike.baidu.com/item/";
        String request = requestStr + location;
        String resText = getEntity(request);
        String summary = "";
        String administrative = "";
        String population = "";
        String naturalSource = "";
        String terrain = "";
        String structure = "";
        String realm = "";
        String environment = "";
        String climate = "";
        String economics = "";
        String social = "";
        String transport = "";
        Document doc = Jsoup.parse(resText);
        Elements summary_html = doc.select(".lemma-summary").get(0).select(".para");
        summary = summary_html.get(0).text();
        Elements level2 = doc.select(".level-2");
        for (Element element : level2) {
            TextNode h2Text = element.select("h2").get(0).textNodes().get(0);
            Element sib = element.nextElementSibling();
            switch (h2Text.text()) {
                case "行政区划":
//                        while (sib.hasAttr("class") && !sib.hasClass("leve-2")) {
//                            administrative = administrative.concat(sib.text());
//                            sib = sib.nextElementSibling();
//                        }
                    // 获取行政区划第一段内容，原来的有问题
                    administrative = administrative.concat(sib.text());
                    break;
                case "人口民族":
                case "人口":
                    while (sib.hasAttr("class") && !sib.hasClass("level-2") && sib.children().size() > 0) {
                        population = population.concat(sib.text());
                        sib = sib.nextElementSibling();
                    }
                    population = population.concat(sib.text());
                    break;

                case "地理环境":
                    while (sib.hasAttr("class") && !sib.hasClass("level-2")) {
                        if (sib.hasClass("level-3")) {
                            TextNode h3Text = sib.select("h3").get(0).textNodes().get(0);
                            sib = sib.nextElementSibling();
                            switch (h3Text.text()) {
                                case "境域":
                                case "地理境域":
                                case "位置":
                                case "面积":
                                    while (sib.hasClass("para") || sib.hasClass("table-view")) {
                                        realm = realm.concat(sib.text());
                                        sib = sib.nextElementSibling();
                                    }
                                    break;
                                case "地形":
                                case "地貌":
                                case "地形地貌":
                                    while (sib.hasClass("para") || sib.hasClass("table-view")) {
                                        terrain = terrain.concat(sib.text());
                                        sib = sib.nextElementSibling();
                                    }
                                    break;
                                case "地质":
                                    while (sib.hasClass("para") || sib.hasClass("table-view")) {
                                        structure = structure.concat(sib.text());
                                        sib = sib.nextElementSibling();
                                    }
                                    break;
                                case "气候":
                                case "气候特征":
                                    while (sib.hasClass("para") || sib.hasClass("table-view")) {
                                        climate = climate.concat(sib.text());
                                        sib = sib.nextElementSibling();
                                    }
                                    break;
                            }
                        } else if (sib.hasClass("anchor-list")) {
                            sib = sib.nextElementSibling();
                        } else {
                            //一直拿不到level-3意味着没有三级标题，就都放在地理环境粒
                            environment = environment.concat(sib.text());
                            sib = sib.nextElementSibling();
                        }
                    }

                    break;
                case "自然资源":
                    while (sib.hasAttr("class") && !sib.hasClass("level-2")) {
                        naturalSource = naturalSource.concat(sib.text());
                        sib = sib.nextElementSibling();
                    }
                    break;
                case "经济":
                    while (sib.hasAttr("class") && !sib.hasClass("level-2")) {
                        economics = economics.concat(sib.text());
                        sib = sib.nextElementSibling();
                    }
                    break;
                case "社会事业":
                case "社会":
                    while (sib.hasAttr("class") && !sib.hasClass("level-2")) {
                        social = social.concat(sib.text());
                        sib = sib.nextElementSibling();
                    }
                    break;
                case "交通":
                    while (sib.hasAttr("class") && !sib.hasClass("level-2")) {
                        transport = transport.concat(sib.text());
                        sib = sib.nextElementSibling();
                    }
                    break;
            }
        }
        // 获取第一个句号的位置，用于以句号为分割时截取一句话。
        int index = administrative.indexOf("。");
        // 替换引用中括号为行标志或空字符串
//        String reg = "^\\[\\d\\]$";
//        Pattern pattern = Pattern.compile(reg);
//        Matcher m = pattern.matcher(administrative);
//        m.replaceAll("");
//        System.out.println(index);
        //获取第一句话
        //System.out.println(administrative.substring(0,index+1));
        System.out.println(summary.replaceAll("\\[\\d\\]"," "));
        System.out.println(administrative.replaceAll("\\[\\d\\]","\n"));
        System.out.println(population.replaceAll("\\[\\d\\]","\n"));
        System.out.println(naturalSource.replaceAll("\\[\\d\\]","\n"));
        System.out.println(terrain.replaceAll("\\[\\d\\]","\n"));
        System.out.println(structure.replaceAll("\\[\\d\\]","\n"));
        System.out.println(realm.replaceAll("\\[\\d\\]","\n"));
        System.out.println(environment.replaceAll("\\[\\d\\]","\n"));
        System.out.println(climate.replaceAll("\\[\\d\\]","\n"));
        System.out.println(economics.replaceAll("\\[\\d\\]","\n"));
        System.out.println(social.replaceAll("\\[\\d\\]","\n"));
        System.out.println(transport.replaceAll("\\[\\d\\]","\n"));
    }
    public static void main(String[] args) throws IOException {
        baike example = new baike();
        example.getBaike("汶川县");
    }
}
