package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRespond;

import java.util.List;

/**
 * Created by frankstar on 2017/6/9.
 */
public interface EarthquakeRespondDAO {

    /*
    * �г����е���Ӧ����
    * */
    public List<EarthquakeRespond> getAllEarthQuakeRespond();

    /*
    * ���ݵ�����ӦID��ȷ����Ӧ����
    * int respond_id
    * */
    public EarthquakeRespond getEarthQuakeRespondByRespondID(int respond_id);

    /*
    * ���ݵ���ID��ȷ����Ӧ����
    * int earth_id
    * ����ݶ����Է��ڷ����
    * */
    //public EarthquakeRespond getEarthQuakeRespondByEarthID(int earth_id);
}
