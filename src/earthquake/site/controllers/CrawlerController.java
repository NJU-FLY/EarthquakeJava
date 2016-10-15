package earthquake.site.controllers;

import earthquake.site.forms.CrawlerForm;
import earthquake.site.forms.Status;
import earthquake.site.entities.EarthquakeUrls;
import earthquake.site.repositories.UrlsRepository;
import earthquake.site.services.CrawlerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Created by fly on 2016/10/13.
 */

@Controller
@RequestMapping(value = "/crawler")
public class CrawlerController {

    @Inject
    private UrlsRepository urlsRepository;
    @Inject
    private CrawlerService crawlerService;

    @ResponseBody
    @RequestMapping(value = "")
    public CrawlerForm information() {
        String[] setting = crawlerService.readSetting();
        CrawlerForm crawlerForm = new CrawlerForm();
        Iterable<EarthquakeUrls> list = urlsRepository.getAll();
        crawlerForm.urls = new ArrayList<String>();
        for (Iterator<EarthquakeUrls> iterator = list.iterator(); iterator.hasNext(); ) {
            EarthquakeUrls urlEntity = iterator.next();
            crawlerForm.urls.add(urlEntity.getUrl());
        }
        if (Pattern.compile("\\d{14}").matcher(setting[0]).find()) {
            crawlerForm.timeSeq = setting[0];
        } else {
            crawlerForm.keywords = setting[1];
            crawlerForm.timeStr = setting[0];
        }
        return crawlerForm;
    }

    @ResponseBody
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public HashMap<String, Integer> startCrawler(@RequestBody CrawlerForm form) {
        int status = crawlerService.updateParams(form);
        HashMap<String, Integer> statusMap = new HashMap<>();
        if (status == Status.SUCCESS.getValue()) {
            status = crawlerService.startCrawler();
            statusMap.put("status", status);
            return statusMap;

        } else {
            statusMap.put("status", Status.FAIL.getValue());
            return statusMap;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/stop")
    public HashMap<String, Integer> stopCrawler() {
        int status = crawlerService.killCrawler();
        HashMap<String, Integer> statusMap = new HashMap<>();
        statusMap.put("status", status);
        return statusMap;
    }
}
