package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRuleRespond;

import java.util.List;

/**
 * Created by frankstar on 2017/6/10.
 */
public interface EarthquakeRuleRespondDAO {

    /*
    * �г����еĶ�Ӧ��ϵ
    * */
    public List<EarthquakeRuleRespond> getAllEarthRuleRespond();

    /*
    *  ����rule_id ��ѯ��Ӧ��respond_id
    * */
    public int getRespondIDByRuleID(int rule_id);

    /*
    *    ����respond_id ��ѯ��Ӧ��rule_id
    * */
    public int getRuleIDByRespondID(int respond_id);
}
