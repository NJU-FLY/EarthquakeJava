package earthquake.site.dao;

import earthquake.site.entity.EarthquakeHistoryEntity;
import earthquake.site.entity.EarthquakeInfo;
import earthquake.site.forms.BriefSearchForm;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.persistence.TypedQuery;

@Repository
public class HistoryRepository extends GenericJpaBaseRepository<Integer, EarthquakeHistoryEntity, BriefSearchForm> {
    public List<EarthquakeHistoryEntity> getHistoryEarthquake(String city){
        String query = "select entity from EarthquakeHistoryEntity entity where entity.province like '%" + city + "%'";
        TypedQuery<EarthquakeHistoryEntity> typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }
}
