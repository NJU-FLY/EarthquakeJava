package earthquake.site.dao;

import earthquake.site.entity.EarthquakeLoss;

/**
 * Created by frankstar on 2017/6/9.
 */
public interface EarthquakeLossDAO {

    /*
    * ���ݵ���ID �г�������ɵ���ʧ
    * int earth_id
    * */
    public EarthquakeLoss getEarthQuakeLossByEarthID(int earth_id);

    /*
    * ���ĳ�������ʧ��Ϣ
    * int earth_id
    * int death_people
    * int injury
    * double economy_loss
    * double destroy
    * */
    public void addEarthQuakeLoss(int earth_id, int death_people, int injury, double economy_loss, double destroy);
}
