<%@ page import="damage.damage_info" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/19
  Time: 16:13
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
        <div class="meun-item"><a href="dailyServlet"><img src="images/icon_house_grey.png">日常巡查情况</a></div>
        <div class="meun-item"><a href="planenessServlet"><img src="images/icon_rule_grey.png">道路平整度</a></div>
        <div class="meun-item" style="background-color:#3D4E60"><a href="damage_infoServlet"><img src="images/icon_card_grey.png">道路设施路面损害情况</a></div>
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
                    <button class="btn btn-yellow btn-s" data-toggle="modal" data-target="#addSource"><a href="damage_infoAddServlet">添加路面损坏情况检测计划表</a></button>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;损坏日期
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            &nbsp;&nbsp;道路编号
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 ">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;道路名称
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            检查人员
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            损坏类型
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            路面类型
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            损坏面积
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作
                        </div>
                    </div>

                    <div class="tablebody">
                        <%
                            for(damage_info rd:(List<damage_info>)request.getSession().getAttribute("damage_infoList"))
                            {%>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            <%=rd.getD_date()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            <%=rd.getRoad_id()%>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            <%=rd.getRoad_name()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            &nbsp;&nbsp;<%=rd.getD_worker()%>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 ">
                            &nbsp;&nbsp;&nbsp;&nbsp;<%=rd.getMain_damage()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            <%=rd.getMain_road()%>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=rd.getD_area()%>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 ">
                            <button class="btn btn-success btn-xs"><a href="<%=request.getContextPath()%>/damage_infoModServlet?d_date=<%=rd.getD_date()%>&road_id=<%=rd.getRoad_id()%>&d_worker=<%=rd.getD_worker()%>&main_damage=<%=rd.getMain_damage()%>">修改</a></button>
                            <button class="btn btn-danger btn-xs" onclick="javascript:delDamage_info('<%=rd.getRoad_id()%>','<%=rd.getD_date()%>','<%=rd.getMain_damage()%>')">删除</button>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script language="javaScript">
    function delDamage_info(road_id,d_date,main_damage){
        if(confirm("确定要删除该条道路路面损坏情况信息？")){
            window.location.href = "damage_infoDelServlet?road_id="+road_id+"&d_date="+d_date+"&main_damage="+main_damage
            return false;
        }
        document.form1.action("<%=request.getContextPath()%>/damage_infoDelServlet?road_id="+road_id+"&d_date="+d_date);
    }
</script>
</body>
</html>
