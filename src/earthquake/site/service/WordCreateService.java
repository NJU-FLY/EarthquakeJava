package earthquake.site.service;

import earthquake.site.entity.EarthquakeAdministrativeDivision;
import earthquake.site.entity.EarthquakeHistoryEntity;
import earthquake.site.entity.EarthquakeInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/1.
 */
@Service
public class WordCreateService{

    public void createBasicInfo(
            EarthquakeInfo basicInfo,
            EarthquakeAdministrativeDivision earthquakeAdministrativeDivision,
            List<EarthquakeHistoryEntity> historyEarthquakeCity,
            Object weatherInfo,
            Object nearCountry)  throws IOException {
        HashMap map = new HashMap();
        // 以字符串的形式保存数字，以保证输出时ftl不会自动千分位转换
        int year = basicInfo.getEarthquakeTime().getYear()+1900;
        String yearStr = "" +year;

        map.put("longitude", basicInfo.getLongitude());
        map.put("latitude", basicInfo.getLatitude());
        map.put("magnitude", basicInfo.getMagnitude());
        map.put("depth", basicInfo.getDepth());
        map.put("province", basicInfo.getProvince());
        map.put("city", basicInfo.getCity());
        map.put("county", basicInfo.getCounty());
        map.put("year", yearStr);
        map.put("month", basicInfo.getEarthquakeTime().getMonth()+1);
        map.put("day", basicInfo.getEarthquakeTime().getDay()+4);
        map.put("hour", basicInfo.getEarthquakeTime().getHours());
        map.put("minute", basicInfo.getEarthquakeTime().getMinutes());
        map.put("realm", earthquakeAdministrativeDivision.getRealm().replaceAll("\\[\\d+\\]",""));
        map.put("population", earthquakeAdministrativeDivision.getPopulation().replaceAll("\\[\\d+\\]",""));
        map.put("administrative", earthquakeAdministrativeDivision.getAdministrativeArea().replaceAll("\\[\\d+\\]",""));
        map.put("environment", earthquakeAdministrativeDivision.getGeoEnvironment().replaceAll("\\[\\d+\\]",""));
        map.put("terrain",earthquakeAdministrativeDivision.getGeoTerrain().replaceAll("\\[\\d+\\]",""));
        map.put("structure", earthquakeAdministrativeDivision.getGeoStructure().replaceAll("\\[\\d+\\]",""));
        map.put("climate", earthquakeAdministrativeDivision.getClimate().replaceAll("\\[\\d+\\]",""));
        map.put("economics",earthquakeAdministrativeDivision.getEconomics().replaceAll("\\[\\d+\\]",""));
        map.put("social",earthquakeAdministrativeDivision.getSocial().replaceAll("\\[\\d+\\]",""));
        map.put("transport",earthquakeAdministrativeDivision.getTransport().replaceAll("\\[\\d+\\]",""));
        map.put("travel",earthquakeAdministrativeDivision.getTravel().replaceAll("\\[\\d+\\]",""));
        map.put("historyEarthquakeCounty",historyEarthquakeCity);
        map.put("weatherInfo",weatherInfo);
        map.put("nearCountry",nearCountry);
        System.out.println(earthquakeAdministrativeDivision.getRealm().replaceAll("\\[\\d\\]",""));
        System.out.println(basicInfo.getEarthquakeTime());
        System.out.println(basicInfo.getEarthquakeTime().getDay());
        System.out.println(basicInfo.getEarthquakeTime().getHours());
        System.out.println(nearCountry);
        System.out.println(historyEarthquakeCity);
        System.out.println("create word function start.........");
        basicCreate(basicInfo, "first.ftl", 1, map);
    }
    /**
     * 创建文件夹及文件并写入数据
     * @param {basicInfo} 基本信息，作为生成文件夹及文件名的依据
     * @param {ftlName} 模板名
     * @param {type} 生成的简报类型 1为一期，2为二期，以此类推
     * @param {map} 要写入文档的数据
     * */
    public void basicCreate(EarthquakeInfo basicInfo, String ftlName, int type, HashMap map) throws IOException{
        // 设置总路径
        //Freemark freemark = new Freemark("templates\\");
        // 设置模板名
        //freemark.setTemplateName(ftlName);
        // 文件名
        Timestamp time = basicInfo.getEarthquakeTime();
        int year = time.getYear()+1900;
        int month = time.getMonth()+1;
        int day = time.getDate();
        String province = basicInfo.getProvince();
        String city = basicInfo.getCity();
        String county = basicInfo.getCounty();
        String eventID = basicInfo.getEventId();
        Double magnitude = basicInfo.getMagnitude();
        // 日期作为输出目录，这里的日期应该作为地震发生的日期，以年命名，从基本信息basicInfo中提取
        // 本地文挡所在目录
        String directory = "C:\\Users\\shixi\\IdeaProjects\\EarthquakeJava\\target\\EarthquakeJava-1.0.0-SNAPSHOT\\output\\"+year+"\\";
        // 服务器文档所在目录
       // String directory = "C:\\Tomcat8\\webapps\\ROOT\\output\\"+year+"\\";
        // 每个地震文件夹名称
////    String secondDir = directory + year + "年"+ month +"月"+day+"日"+ province+county+magnitude+"级地震\\";
        String secondDir = directory + eventID + "\\";
        File file = new File(directory);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(secondDir);
        String fileName ="";
        // 如果创建目录成功则设置为输出路径
        if(createDir(directory)){
            if(createDir(secondDir)){
                //freemark.setFilePath(secondDir);
                switch (type) {
                    case 1:
                          //freemark.setFileName("地震一期简报" + ".doc");
                        fileName = "地震一期简报" + ".doc";
                        break;
                    case 2:
                        //freemark.setFileName("地震二期简报" + ".doc");
                        fileName = "地震二期简报" + ".doc";
                        break;
                    case 3:
                        //freemark.setFileName("地震三期简报" + ".doc");
                        fileName = "地震三期简报" + ".doc";
                }
                //freemark.setFileName(fileName);
            }else {
                System.out.println("地震文件夹已经存在");
            }
        }else {
            System.out.println("创建目录失败");
        }
        //
        createWord(map,ftlName,secondDir,fileName);
    }
    public void createWord(HashMap map,String templateName,String filePath,String fileName) throws IOException{
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        // ftl文件路径在tomcat地bin文件下面
        configuration.setDirectoryForTemplateLoading(new File("templates\\"));
        System.out.println(configuration);
        System.out.println(configuration.getTemplateLoader());
        // 模板
        Template t = null;
        try {
            //获取模板信息
            t = configuration.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文件
        File outFile = new File(filePath+fileName);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //输出数据到模板中，生成文件。
            t.process(map, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 创建单个文件
    public static boolean createFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {// 判断文件是否存在
            System.out.println("目标文件已存在" + filePath);
            return false;
        }
        if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
            System.out.println("目标文件不能为目录！");
            return false;
        }
        if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
                System.out.println("创建目标文件所在的目录失败！");
                return false;
            }
        }
        try {
            if (file.createNewFile()) {// 创建目标文件
                System.out.println("创建文件成功:" + filePath);
                return true;
            } else {
                System.out.println("创建文件失败！");
                return false;
            }
        } catch (IOException e) {// 捕获异常
            e.printStackTrace();
            System.out.println("创建文件失败！" + e.getMessage());
            return false;
        }
    }

    // 创建目录
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {// 判断目录是否存在
            System.out.println("创建目录失败，目标目录已存在！");
            return true;
        }
        if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
            destDirName = destDirName + File.separator;
        }
        if (dir.mkdirs()) {// 创建目标目录
            System.out.println("创建目录成功！" + destDirName);
            return true;
        } else {
            System.out.println("创建目录失败！");
            return false;
        }
    }
}


