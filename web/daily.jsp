<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="daily.daily" %><%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/15
  Time: 11:36
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
        <div class="tab-content">
            <!-- 道路管理模块 -->
            <div role="tabpanel" class="tab-pane active" id="main_damage">
                <div class="check-div form-inline">
                    <button class="btn btn-yellow btn-s" data-toggle="modal" data-target="#addSource"><a href="dailyAddServlet">添加日巡计划表</a></button>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 ">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日巡日期
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            &nbsp;&nbsp;道路编号
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;道路名称
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            巡查人员
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            损坏类型
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            路面类型
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            损坏位置及描述
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作
                        </div>
                    </div>

                    <div class="tablebody">
                        <%
                            for(daily rd:(List<daily>)request.getSession().getAttribute("dailyList"))
                            {%>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            <%=rd.getDaily_date()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            <%=rd.getRoad_id()%>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
                            <%=rd.getRoad_name()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            &nbsp;&nbsp;<%=rd.getDaily_worker()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            <%=rd.getMain_damage()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            <%=rd.getMain_road()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            <%=rd.getDaily_des()%>
                        </div>

                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            <button type="button" class="btn btn-success btn-xs" href="" onclick=""><a href="<%=request.getContextPath()%>/dailyModServlet?daily_date=<%=rd.getDaily_date()%>&road_id=<%=rd.getRoad_id()%>&road_name=<%=rd.getRoad_name()%>&daily_worker=<%=rd.getDaily_worker()%>&main_damage=<%=rd.getMain_damage()%>&main_road=<%=rd.getMain_road()%>&daily_des=<%=rd.getDaily_des()%>&daily_note=<%=rd.getDaily_note()%>">修改</a></button>
                            <button class="btn btn-danger btn-xs" onclick="javascript:delDaily('<%=rd.getRoad_id()%>','<%=rd.getDaily_date()%>','<%=rd.getMain_damage()%>')">删除</button>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script language="javaScript">
    function delDaily(road_id,daily_date,main_damage){
        if(confirm("确定要删除该日巡记录？")){
            window.location.href = "dailyDelServlet?road_id="+road_id+"&daily_date="+daily_date+"&main_damage="+main_damage;
            return false;
        }
        document.form1.action("dailyDelServlet?road_id="+road_id+"&daily_date="+daily_date+"&main_damage="+main_damage);
    }
</script>

</body>
</html>
