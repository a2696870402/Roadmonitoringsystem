package test;

import damage.damage_info;
import summary.summary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sumServlet")
public class sumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gb2312");
        operateSql operate = new operateSql();
        calculate c = new calculate();
        PrintWriter out=response.getWriter();
        List<summary> list_s = new ArrayList<summary>(); //summary数组循环计算结果
        try {
            list_s = operate.getSummary();
        }catch (Exception e){
            out.print("<script>alert('找不到Summary的值，该值为null,请查看!');window.location.href='planenessServlet'</script>");
            return;
        }
        for (int l = 0; l < list_s.size(); l++) {
            double total = 0;
            double pci = 100;
            List<String> list_road = new ArrayList<>();//损害大类数组
            List<Double> list_u = new ArrayList<Double>(); //大类u数组
            List<Double> list_w = new ArrayList<Double>(); //大类w数组
            double iri = -1;
            try {
                iri = operate.getIRI(list_s.get(l).getS_date(), list_s.get(l).getRoad_id());
            } catch (Exception e) {
                iri = -1;
                System.out.println("没找到该iri，设置为-1");
                continue;
            }
            if (iri == -1)
                continue;
            double rqi = c.getRQI(iri);
            String re;
            String main_road;
            String[] string;
            try {
                re = operate.getDateAndID(list_s.get(l).getS_date(), list_s.get(l).getRoad_id());
                main_road = operate.getMain_Road(list_s.get(l).getS_date(), list_s.get(l).getRoad_id());
                string = re.split(",");
                if (string==null||string.equals("")){
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
                //不进行下面的操作；
            }
            try {
                java.sql.Date date = java.sql.Date.valueOf(string[0]);
                list_road = operate.getMain_classArray(date, string[1]);
                for (int i = 0; i < list_road.size(); i++) {
                    List<damage_info> list = new ArrayList<damage_info>();  //某损害大类
                    list = operate.getDamage_infoArrry(list_road.get(i), main_road);
                    list_u.add(c.getDpTotal(list));
                    total += list_u.get(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            for (int j = 0; j < list_u.size(); j++) {
                if (total > 0) {
                    double u = list_u.get(j) / total;
                    double w = 3 * u * u * u - 5.5 * u * u + 3.5 * u;
                    list_w.add(w);
                } else {
                    list_w.add(0.0);
                }
            }
            for (int k = 0; k < list_w.size(); k++) {
                pci -= list_u.get(k) * list_w.get(k);
            }
            int dj;
            try {
                dj = operate.getDj(list_s.get(l).getRoad_id());
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            double pqi = c.getPQI(rqi, pci, dj);
            String PQI_DJ="",QRI_DJ="",PCI_DJ="";
            switch (dj)
            {
                case 1:
                {
                    //QRI等级
                    if(rqi>=4.10)
                    {
                        QRI_DJ="A";
                    }
                    else if(rqi>=3.60)
                    {
                        QRI_DJ="B";
                    }
                    else if(rqi>=2.50)
                    {
                        QRI_DJ="C";
                    }
                    else
                    {
                        QRI_DJ="D";
                    }
                    //PCI等级
                    if(pci>=90)
                    {
                        PCI_DJ="A";
                    }
                    else if(pci>=75)
                    {
                        PCI_DJ="B";
                    }
                    else if(pci>=65)
                    {
                        PCI_DJ="C";
                    }
                    else
                    {
                        PCI_DJ="D";
                    }
                    //PQI等级
                    if(pqi>=90)
                    {
                        PQI_DJ="A";
                    }
                    else if(pqi>=75)
                    {
                        PQI_DJ="B";
                    }
                    else if(pqi>=65)
                    {
                        PQI_DJ="C";
                    }
                    else
                    {
                        PQI_DJ="D";
                    }
                    break;
                }
                case 2:
                {
                    //QRI等级
                    if(rqi>=3.60)
                    {
                        QRI_DJ="A";
                    }
                    else if(rqi>=3.00)
                    {
                        QRI_DJ="B";
                    }
                    else if(rqi>=2.40)
                    {
                        QRI_DJ="C";
                    }
                    else
                    {
                        QRI_DJ="D";
                    }
                    //PCI等级
                    if(pci>=85)
                    {
                        PCI_DJ="A";
                    }
                    else if(pci>=70)
                    {
                        PCI_DJ="B";
                    }
                    else if(pci>=60)
                    {
                        PCI_DJ="C";
                    }
                    else
                    {
                        PCI_DJ="D";
                    }
                    //PQI等级
                    if(pqi>=85)
                    {
                        PQI_DJ="A";
                    }
                    else if(pqi>=70)
                    {
                        PQI_DJ="B";
                    }
                    else if(pqi>=60)
                    {
                        PQI_DJ="C";
                    }
                    else
                    {
                        PQI_DJ="D";
                    }
                    break;
                }
                case 3:
                {
                    //QRI等级
                    if(rqi>=3.40)
                    {
                        QRI_DJ="A";
                    }
                    else if(rqi>=2.80)
                    {
                        QRI_DJ="B";
                    }
                    else if(rqi>=2.20)
                    {
                        QRI_DJ="C";
                    }
                    else
                    {
                        QRI_DJ="D";
                    }
                    //PCI等级
                    if(pci>=80)
                    {
                        PCI_DJ="A";
                    }
                    else if(pci>=65)
                    {
                        PCI_DJ="B";
                    }
                    else if(pci>=60)
                    {
                        PCI_DJ="C";
                    }
                    else
                    {
                        PCI_DJ="D";
                    }
                    //PQI等级
                    if(pqi>=80)
                    {
                        PQI_DJ="A";
                    }
                    else if(pqi>=65)
                    {
                        PQI_DJ="B";
                    }
                    else if(pqi>=60)
                    {
                        PQI_DJ="C";
                    }
                    else
                    {
                        PQI_DJ="D";
                    }
                }
            }
            try {
                operate.updateSummary(list_s.get(l).getS_date(), list_s.get(l).getRoad_id(), pqi, PQI_DJ, rqi, QRI_DJ, pci,PCI_DJ);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        out.print("<script>window.location.href='summaryServlet'</script>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
