package earthquake.site.repositories;
import earthquake.site.forms.SearchForm;
import earthquake.site.entities.EarthquakeWebpages;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class WebpagesRepository extends GenericJpaBaseRepository<Integer, EarthquakeWebpages> {

    public Iterable<EarthquakeWebpages> getByCondition(SearchForm form) {
        String query = "select entity from EarthquakeWebpages entity";
        boolean firstFlag = true;
        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

        HashMap<String, Object> attrsMap = new HashMap<>();
        Field[] fields = form.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String attrname = field.getName();
            try {
                Object value = field.get(form);
                if (value != null) {
                    attrsMap.put(attrname, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        for (Iterator<Map.Entry<String, Object>> iterator = attrsMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            if (firstFlag) {
                firstFlag = false;
                query += " where ";
            } else {
                query += " and ";
            }
            if (key.equals("crawledStartTime")) {
                query += "entity.crawledtime > " + format.format(entry.getValue());
            } else if (key.equals("crawledEndTime")) {
                query += "entity.crawledtime < " + format.format(entry.getValue());
            } else if (key.equals("publishedStartTime")) {
                query += "entity.publishedtime > " + format.format(entry.getValue());
            } else if (key.equals("publishedEndTime")) {
                query += "entity.publishedtime < " + format.format(entry.getValue());

            } else if (key.equals("title") || key.equals("content") || key.equals("typename") || key.equals("summary")) {
                query += "entity." + key + " like '%" + entry.getValue() + "%'";

            } else if (key.equals("eventid")) {
                query += "entity.eventid = " + entry.getValue();

            } else if (key.equals("order") || key.equals("orderName")) {
                query = query.substring(0, query.lastIndexOf(" and "));
            }
        }
        if (!firstFlag) {
            attrsMap.putIfAbsent("order", "2");
            attrsMap.putIfAbsent("orderName", "crawledtime");
            String sqlOrder = "";
            if (attrsMap.get("order").equals("2")) {
                sqlOrder = "desc";
            }
            query += " order by entity." + attrsMap.get("orderName") + " " + sqlOrder;
        }
        System.out.println(query);
        TypedQuery<EarthquakeWebpages> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }
}
