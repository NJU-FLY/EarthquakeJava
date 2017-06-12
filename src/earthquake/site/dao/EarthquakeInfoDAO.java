package earthquake.site.dao;

import earthquake.site.entity.EarthquakeInfo;

import java.util.List;

/**
 * Created by frankstar on 2017/6/8.
 */
public interface EarthquakeInfoDAO {

    /*
    *  ��ȡ���� ������Ϣ
    *  int pageNumber ҳ��
    *  ���ڷ�ҳ
    * */
    public List<EarthquakeInfo> getAllEarthQuake(int pageNumber);

    /*
    * ����ʱ���ѯ ������Ϣ
    * String time
    * */
    public List<EarthquakeInfo> getEarthQuakeByTime(String time);

    /*
    * ���ݳ��йؼ��ֲ�ѯ������Ϣ
    * String city
    * */
    public List<EarthquakeInfo> getEarthQuakeByCity(String city);

    /*
    * ���ӵ�����Ϣ
    * String earth_title
    * String earth_time
    * double longitude
    * double latitude
    * int deepth
    * double magnitude
    * String city
    * */
    public void addEarthQuakeInfo(String earth_title, String earth_time,
                                  double longitude, double latitude, int deepth, double magnitude, String city);

    /*
    * ɾ��������Ϣ
    * int earth_id
    * */
    public void deleteEarthQuake(int earth_id);


    /*
    * ����earth_id ����earth_history ����ȡ������Ϣ
    * int earth_id
    * */
    public EarthquakeInfo getEarthQuakeByID(int earth_id);

    /*
    * ����earth_id ����earth_history ����ȡ������Ϣ
    * List<Integer> earth_id
    * */
    public List<EarthquakeInfo> getEarthQuakeByID(List<Integer> earth_id);




}
