<%@ page import="daily.daily" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="JDB.JDBConnection" %>
<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/16
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!--引入Bootstrap中的css样式-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <title>道路养护管理</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function() {
            $(".meun-item").click(function() {
                $(".meun-item").removeClass("meun-item-active");
                $(this).addClass("meun-item-active");
                var itmeObj = $(".meun-item").find("img");
                itmeObj.each(function() {
                    var items = $(this).attr("src");
                    items = items.replace("_grey.png", ".png");
                    items = items.replace(".png", "_grey.png")
                    $(this).attr("src", items);
                });
                var attrObj = $(this).find("img").attr("src");
                ;
                attrObj = attrObj.replace("_grey.png", ".png");
                $(this).find("img").attr("src", attrObj);
            });
            $("#topAD").click(function() {
                $("#topA").toggleClass(" glyphicon-triangle-right");
                $("#topA").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topBD").click(function() {
                $("#topB").toggleClass(" glyphicon-triangle-right");
                $("#topB").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topCD").click(function() {
                $("#topC").toggleClass(" glyphicon-triangle-right");
                $("#topC").toggleClass(" glyphicon-triangle-bottom");
            });
            $(".toggle-btn").click(function() {
                $("#leftMeun").toggleClass("show");
                $("#rightContent").toggleClass("pd0px");
            })
        })
    </script>
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet" type="text/css">
    <link href="css/slide.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/flat-ui.min.css" rel="stylesheet" type="text/css">
    <link href="css/jquery.nouislider.css" rel="stylesheet" type="text/css">
</head>
<body>
<form method="post" action="dailyModServlet">
    <div id="wrap">
        <!-- 左侧菜单栏目块 -->
        <div class="leftMeun" id="leftMeun">
            <div id="logoDiv">
                <p id="logoP"><img id="logo" alt="道路养护管理" src="images/logo.png"><span>道路养护管理</span></p>
            </div>
            <div class="meun-title">道路管理</div>
            <div class="meun-item "><a href="roadServlet"><img src="images/icon_source.png">道路基本信息</a></div>
            <div class="meun-title">养护管理</div>
            <div class="meun-item" style="background-color:#3D4E60"><a href="dailyServlet"><img src="images/icon_house_grey.png">日常巡查情况</a></div>
            <div class="meun-item"><a href="planenessServlet"><img src="images/icon_rule_grey.png">道路平整度</a></div>
            <div class="meun-item" ><a href="damage_infoServlet"><img src="images/icon_card_grey.png">道路设施路面损害情况</a></div>
            <div class="meun-item"><a href="damageServlet"><img src="images/icon_char_grey.png">路面主要损坏类型</a></div>
            <div class="meun-item"><a href="singleServlet"><img src="images/icon_chara_grey.png">损坏单项扣分</a></div>
            <div class="meun-item"><a href="sumServlet"><img src="images/icon_user_grey.png">综合指数表</a></div>
        </div>
        <div id="rightContent">
            <a class="toggle-btn" id="nimei">
                <i class="glyphicon glyphicon-align-justify"></i>
            </a>
            <!-- Tab panes -->
            <form method="post" action="dailyModServlet">
                <div class="tab-content">
                    <!-- 道路管理模块 -->
                    <div role="tabpanel" class="tab-pane active" id="planeness">
                        <div class="data-div">
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="form-horizontal">
                                            <% daily d = (daily)request.getSession().getAttribute("daily");%>
                                        <div class="row">
                                            <label for="daily_date"class="col-xs-2 control-label">日巡日期*：</label>
                                            <div class="col-xs-2 ">
                                                <input type="date"  readonly="readonly" class="form-control input-sm duiqi" id="daily_date" name="daily_date" value="<%=d.getDaily_date()%>" style="color: #0f0f0f">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="road_id" readonly="readonly" class="col-xs-2 control-label">道路编号*：</label>
                                            <div class="col-xs-2 ">
                                                <input type="text"  readonly="readonly" class="form-control input-sm duiqi" id="road_id"  name="road_id" value="<%=d.getRoad_id()%>" style="color: #0f0f0f">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="road_name" readonly="readonly" class="col-xs-2 control-label">道路名称*：</label>
                                            <div class="col-xs-2 ">
                                                <input type="text"  readonly="readonly" class="form-control input-sm duiqi" id="road_name" name="road_name" value="<%=d.getRoad_name()%>" style="color: #0f0f0f">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <%
                                                List<String> list1;
                                                list1 = new ArrayList<String>();
                                                List<String> list2=new ArrayList<String>();
                                                JDBConnection jdbConnection=new JDBConnection();
                                                try {
                                                    final ResultSet resultSet1 = jdbConnection.executeQuery("select distinct main_damage from main_damage where main_road='"+d.getMain_road()+"'");
                                                    while(resultSet1.next()){
                                                        String a=resultSet1.getString(1);
                                                        list1.add(a);
                                                    }
                                                    final ResultSet resultSet2 = jdbConnection.executeQuery("select per from workers");
                                                    while(resultSet2.next()){
                                                        String b=resultSet2.getString(1);
                                                        list2.add(b);
                                                    }
                                                }catch(SQLException ex){
                                                    ex.printStackTrace();
                                                }
                                            %>
                                            <label for="daily_worker" class="col-xs-2 control-label">巡查人员*：</label>
                                            <div class="col-xs-2 ">
                                                <select class="form-control input-sm duiqi" id="daily_worker" name="daily_worker" style="height:30px;">
                                                    <%for(int i=0;i<list2.size();i++){
                                                            if(list2.get(i).equals(d.getDaily_worker())){
                                                    %>
                                                    <option selected><%=list2.get(i)%></option>
                                                    <%}else {%>
                                                    <option><%=list2.get(i)%></option>
                                                            <%} }%>
                                                </select>
                                            </div>
                                        </div>
                                         <div class="row">
                                        <label for="main_damage" class="col-xs-2 control-label">损坏类型*：</label>
                                        <div class="col-xs-2">
                                            <select class="form-control input-sm duiqi" id="main_damage" name="main_damage" style="height:30px;">
                                                    <%for(int i=0;i<list1.size();i++){
                                                        if (list1.get(i).equals(d.getMain_damage())){
                                                    %>
                                                    <option selected><%=list1.get(i)%></option>
                                                    <%}else {%>
                                                    <option><%=list1.get(i)%></option>
                                                    <%} }%>
                                            </select>
                                        </div>
                                         </div>
                                        <div class="row">
                                        <label for="main_road" class="col-xs-2 control-label">路面类型：</label>
                                        <div class="col-xs-2">
                                            <input type="text" readonly="readonly" class="form-control input-sm duiqi" id="main_road" name="main_road" value="<%=d.getMain_road()%>" style="color: #0f0f0f">
                                        </div>
                                        </div>
                                        <div class="row">
                                        <label for="daily_des" class="col-xs-2 control-label">损坏位置及描述：</label>
                                        <div class="col-xs-2">
                                             <input type="text" class="form-control input-sm duiqi" id="daily_des" name="daily_des" value="<%=d.getDaily_des()%>">
                                        </div>
                                        </div>
                                        <div class="row">
                                        <label for="daily_note" class="col-xs-2 control-label">备注：</label>
                                        <div class="col-xs-2">
                                            <input type="text" class="form-control input-sm duiqi" id="daily_note" name="daily_note" value="<%=d.getDaily_note()%>">
                                        </div>
                                        </div>
                                </div>
                                <div class="container-fluid">
                                    <p style="font-size:15px;font-weight:bolder;color:#EE3F4D">
                                        ※ 带*为必填项目！！！
                                    </p>
                                </div>
                                <div class="modal-footer">
                                    <input id="next_to" class="btn btn-xs" type="button" style="width: 90px; margin-right:10px;" onclick="getkey(this)" value="录入下一个">
                                    <input id="save_qw" type="submit" onclick="sublim_e()" class="btn btn-xs btn-green" style="width: 90px; margin-right:10px;" name="submit" value="保存并退出">
                                    <button class="btn btn-xs" type="button"  style="width: 90px" href="" onclick=""><a href="dailyServlet">返回</a></button>
                                </div>

                                <script type="text/javascript">
                                    function sublim_e() {
                                        $('#main_damage').attr("disabled",false);
                                        document.onsubmit;
                                    }
                                    var check_box=$('#main_damage option:selected');
                                    if(check_box.text().toString()!="未添加"){
                                        //把录入下一条隐藏
                                        $("#next_to").hide();
                                        //把保存显示
                                        $("#save_qw").show();
                                        $('#main_damage').attr("disabled",true);
                                        $('#main_damage').attr("disabled","disabled");
                                    }else{
                                        //把保存隐藏
                                        $("#save_qw").hide();
                                        //把录入下一条显示
                                        $("#next_to").show();
                                    }
                                    function getkey(a) {
                                        var daily_date = 'daily_date=' + document.getElementById("daily_date").value;
                                        var daily_worker = 'daily_worker=' + document.getElementById("daily_worker").value;
                                        var road_id = 'road_id=' + document.getElementById("road_id").value;
                                        var road_name = 'road_name=' + document.getElementById("road_name").value;
                                        var main_damage = 'main_damage=' + document.getElementById("main_damage").value;
                                        var main_road = 'main_road=' + document.getElementById("main_road").value;
                                        var daily_des = 'daily_des=' + document.getElementById("daily_des").value;
                                        var daily_note = 'daily_note=' + document.getElementById("daily_note").value;
                                        window.location.href = './dailyAddNext?' + daily_date +'&' + daily_worker+'&' + road_id+'&' + road_name+'&'
                                            + main_damage+'&' + main_road+'&' + daily_des+'&'+ daily_note
                                    }
                                </script>
                    </div>
                </div>
                </div>
                </div>
            </form>
        </div>
    </div>
</form>
</body>
</html>
