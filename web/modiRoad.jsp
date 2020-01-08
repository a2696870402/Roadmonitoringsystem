<%@ page import="road.road" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/12/12
  Time: 15:00
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
        <div class="meun-item " style="background-color:#3D4E60"><a href="roadServlet"><img src="images/icon_source.png">道路基本信息</a></div>
        <div class="meun-title">养护管理</div>
        <div class="meun-item"><a href="dailyServlet"><img src="images/icon_house_grey.png">日常巡查情况</a></div>
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
        <form action="modiRdOKServlet">
        <div class="tab-content">
            <!-- 道路管理模块 -->
            <div role="tabpanel" class="tab-pane active" id="road">
                <div class="data-div">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <form class="form-horizontal">
                                <% road r=(road)request.getSession().getAttribute("upRoad");%>
                                <div class="row">
                                    <label for="rName" class="col-xs-2 control-label">道路名称*：</label>
                                    <div class="col-xs-3 ">
                                        <input type="text" class="form-control input-sm duiqi" readonly="readonly" id="rName" name="road_name" value="<%=r.getRoad_name()%>" style="color: black">
                                    </div>
                                    <label for="rId" class="col-xs-2 control-label">道路编号*：</label>
                                    <div class="col-xs-3 ">
                                        <input type="text" class="form-control input-sm duiqi" readonly="readonly" id="rId"  name="road_id" value="<%=r.getRoad_id()%>" style="color: black">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rZx" class="col-xs-2 control-label">道路走向：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rZx" name="road_zx" value="<%=r.getRoad_zx()%>" style="color: black">
                                    </div>
                                    <label for="rQd" class="col-xs-2 control-label">起点：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rQd" name="road_qd" value="<%=r.getRoad_qd()%>" style="color: black">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rZd" class="col-xs-2 control-label">终点：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rZd" name="road_zd" value="<%=r.getRoad_zd()%>" style="color: black">
                                    </div>
                                    <label for="rSjdw" class="col-xs-2 control-label">设计单位：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rSjdw" name="road_sjdw" value="<%=r.getRoad_sjdw()%>" style="color: black">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rSgdw" class="col-xs-2 control-label">施工单位：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rSgdw" name="road_sgdw" value="<%=r.getRoad_sgdw()%>" style="color: black">
                                    </div>
                                    <label for="rDj" class="col-xs-2 control-label">道路等级*：</label>
                                    <div class="col-xs-3">
                                        <select class="form-control input-sm duiqi" id="rDj" name="road_dj" style="height:30px;color: black" value="<%=r.getRoad_dj()%>">
                                            <option value="1" <%if(r.getRoad_dj()==1) {%>selected <% } else{}%>>1</option>
                                            <option value="2" <%if(r.getRoad_dj()==2) {%>selected <% } else{}%>>2</option>
                                            <option value="3" <%if(r.getRoad_dj()==3) {%>selected <% } else{}%>>3</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rSs" class="col-xs-2 control-label">设计时速：(km/h)</label>
                                    <div class="col-xs-3">
                                        <input type="number" min="0" step="10" class="form-control input-sm duiqi" id="rSs" name="road_ss" value="<%=r.getRoad_ss()%>">
                                    </div>
                                    <label for="rKd" class="col-xs-2 control-label">宽度范围：</label>
                                    <div class="col-xs-3">
                                        <input type="number" min="0" step="0.1" class="form-control input-sm duiqi" id="rKd" name="road_kd" value="<%=r.getRoad_kd()%>">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rCd" class="col-xs-2 control-label">道路长度：</label>
                                    <div class="col-xs-3">
                                        <input type="number" step="0.1" min="0" class="form-control input-sm duiqi" id="rCd" name="road_cd" value="<%=r.getRoad_cd()%>">
                                    </div>
                                    <label for="rMj" class="col-xs-2 control-label">道路面积：</label>
                                    <div class="col-xs-3">
                                        <input type="number" step="0.1" min=0 class="form-control input-sm duiqi" id="rMj" name="road_mj" value="<%=r.getRoad_mj()%>">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rAadt" class="col-xs-2 control-label">A A D T：</label>
                                    <div class="col-xs-3">
                                        <input type="number" min="0" step="1000" class="form-control input-sm duiqi" id="rAadt" name="road_aadt" value="<%=r.getRoad_aadt()%>">
                                    </div>
                                    <label for="rJtl" class="col-xs-2 control-label">交通量：</label>
                                    <div class="col-xs-3">
                                        <input type="number" min="0" step="1000" class="form-control input-sm duiqi" id="rJtl" name="road_jtl" value="<%=r.getRoad_jtl()%>">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rXz" class="col-xs-2 control-label">所属乡镇：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rXz" name="road_xz" value="<%=r.getRoad_xz()%>">
                                    </div>
                                    <label for="rGlfl" class="col-xs-2 control-label">管理分类：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rGlfl" name="road_glfl" value="<%=r.getRoad_glfl()%>">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rGldw" class="col-xs-2 control-label">管理单位：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rGldw" name="road_gldw" value="<%=r.getRoad_gldw()%>">
                                    </div>
                                    <label for="rYhdw" class="col-xs-2 control-label">养护单位：</label>
                                    <div class="col-xs-3">
                                        <input type="" class="form-control input-sm duiqi" id="rYhdw" name="road_yhdw" value="<%=r.getRoad_yhdw()%>">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="rJzny" class="col-xs-2 control-label">建造年月*：</label>
                                    <div class="col-xs-3">
                                        <input type="date" class="form-control input-sm duiqi" id="rJzny" name="road_jzny" value="<%=r.getRoad_jzny()%>">
                                    </div>
                                    <label for="rType" class="col-xs-2 control-label">路面类型*：</label>
                                    <div class="col-xs-3">
                                        <select class="form-control input-sm duiqi" id="rType" name="road_type" style="height:30px;" value="<%=r.getRoad_type()%>">
                                            <option value="沥青路面" <%if(r.getRoad_type().equals("沥青路面")) {%>selected <% } else{}%>>沥青路面</option>
                                            <option value="水泥路面" <%if(r.getRoad_type().equals("水泥路面")) {%>selected <% } else{}%>>水泥路面</option>
                                        </select>
                                    </div>
                                </div>

                        </div>
                    </div>
                    <div class="container-fluid">
                        <p style="font-size:15px;font-weight:bolder;color:#EE3F4D">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;※ 带*为必填项目！！！
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-xs btn-xs btn-green" name="submit" value="确认">保 存</button>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </div>
</div>


</body>
</html>

