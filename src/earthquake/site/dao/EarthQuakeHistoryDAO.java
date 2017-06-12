package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRelation;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
public interface EarthQuakeHistoryDAO {

    /*
    * ���ӵ������ʷ��Ϣ
    * ʵ���� ���ǹ�ϵ�������ӵ�earth_relation����
    * int earth_id  int earth_history
    * */
    public void addEarthQuakeHistory(int earth_id, int earth_history);

    /*
    * ��ѯĳ�������ʷ��Ϣid
    * int earth_id
    * */
    public List<EarthquakeRelation> getEarthRelationByEarthId(int earth_id);


}
