package earthquake.site.dao;

import earthquake.site.entity.EarthquakeRule;

import java.util.List;

/**
 * Created by frankstar on 2017/6/9.
 */
public interface EarthquakeRuleDAO {


    /*
    * �г����е���ȷ���ȼ�����
    * */
    public List<EarthquakeRule> getAllEarthQuakeRule();

    /*
    * ����rule_id��ȡ�ȼ�����
    * int rule_id
    * */
    public EarthquakeRule getEarthQuakeRuleByRuleID(int rule_id);


    // һ�µĽӿ� Ӧ�÷��ڷ����
    /*
    * ���ݵ����𼶻��ֵ���ȼ�
    *
    * double earth_magnitude
    * */
   /* public EarthquakeRule getEarthQuakeRuleByEarthMagnitude(double magnitude);

    *//*
    * �����������������ֵ���ȼ�
    * int death_people
    * *//*
    public EarthquakeRule getEarthQuakeRuleByDeathPeople(int death_people);

    *//*
    * ���ݾ�����ʧ�����ֵ���ȼ�
    * double economy_loss
    * *//*
    public EarthquakeRule getEarthQuakeRuleByEconomyLoss(double economy_loss);

    *//*
    * ���ݵ���ID ���ֵ���ȼ�
    * int earth_id
    * ����ݶ����Է��ڷ����
    * *//*
    public EarthquakeRule getEarthQuakeRuleByEarthID(int earth_id);*/


}
