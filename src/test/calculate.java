package test;

import damage.damage_info;

import java.util.ArrayList;
import java.util.List;

public class calculate {
    public double getArea(String main_road,String main_damage,double d_len,double d_width,double d_height)
    {
        double area;
        if(main_road.equals("沥青路面"))
        {
            if(main_damage.equals("线裂"))
            {
                area=d_len*0.2;
            }
            else if (main_damage.equals("路框差"))
            {
                area=d_height;
            }
            else
            {
                area=d_len*d_width;
            }
        }
        else
        {
            if(main_damage.equals("线裂"))
            {
                area=d_len*0.2;
            }
            else if (main_damage.equals("接缝料损坏"))
            {
                area=d_len;
            }
            else if (main_damage.equals("错台"))
            {
                area=d_height;
            }
            else if (main_damage.equals("唧浆"))
            {
                area=2*(d_len+d_width);
            }
            else if (main_damage.equals("路框差"))
            {
                area=d_height;
            }
            else
            {
                area=d_len*d_width;
            }
        }
        return area;
    }
    public double getRQI(double iri)
    {
        double rqi;
        rqi=4.98-0.34*iri;
        if(rqi<0)
            rqi=0;
        return rqi;
    }
    public double getDpij(String main_road,String main_damage,double density)    //插值法
    {
        double score=0;
        double den1,den2;     //density处于den1和den2之间
        int sco1,sco2;
        operateSql operate=new operateSql();
        List<single> list = new ArrayList<single>();
        list=operate.getSingle(main_damage,main_road);
        for(int i=0;i<list.size()-1;i++)
        {
            if(density>0&&density<=list.get(0).getDensity())
            {
                score=density/list.get(0).getDensity()*list.get(0).getKoufen();
            }
            if(density>=list.get(i).getDensity()&&density<=list.get(i+1).getDensity())
            {
                den1=list.get(i).getDensity();
                den2=list.get(i+1).getDensity();
                sco1=list.get(i).getKoufen();
                sco2=list.get(i+1).getKoufen();
                score=(density-den1)/(den2-den1)*(sco2-sco1)+sco1;
                break;
            }
        }
        return score;
    }
    //计算u
    public double getDpTotal(List<damage_info> list)
    {
        List<Double> list_u=new ArrayList<Double>();
        List<Double> list_w=new ArrayList<Double>();
        double total=0;
        double result=0;
        for(int i=0;i<list.size();i++)
        {
            list_u.add(getDpij(list.get(i).getMain_road(),list.get(i).getMain_damage(),list.get(i).getDensity()));
            total+=list_u.get(i);
        }
        for(int j=0;j<list_u.size();j++)
        {
            if(total>0) {
                double u = list_u.get(j) / total;
                double w = 3 * u * u * u - 5.5 * u * u + 3.5 * u;
                list_w.add(w);
            }
            else
            {
                list_w.add(0.0);
            }
        }
        for(int k=0;k<list_w.size();k++)
        {
            result+=list_u.get(k)*list_w.get(k);
        }
        return result;
    }
    public double getPQI(double rqi,double pci,int dj)
    {
        double pqi;
        if(dj==1)
        {
            pqi=20*0.6*rqi+0.4*pci;
        }
        else
        {
            pqi=20*0.4*rqi+0.6*pci;
        }
        return pqi;
    }
}