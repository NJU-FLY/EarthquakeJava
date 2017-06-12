package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRelation;
import earthquake.site.entity.EarthquakeNearcityInfo;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
public interface EarthquakeNearcityDAO {

    /*
    * ���ӵ���ĸ�����Ϣ
    * ʵ���� ���ǹ�ϵ�������ӵ�earth_relation����
    * int earth_id  int earth_near
    * */
    public void addEarthQuakeNearCity(int earth_id, int earth_near);

    /*
    * ��ѯĳ����ĸ�����Ϣid
    * int earth_id
    * */
    public List<EarthquakeRelation> getEarthRelationByEarthID(int earth_id);

    /*
    * ����earth_near��ѯ������Ϣ
    * List<Integer> earth_near
    * */

    public List<EarthquakeNearcityInfo> getNearCityInfoByEarthNearID(List<Integer> earthNear);

    /*
    * ����earth_near��ѯ������Ϣ
    * int earth_near
    * */

    public EarthquakeNearcityInfo getNearCityInfoByEarthNearID(int earthNear);
}
