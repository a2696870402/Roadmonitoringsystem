<%@ page import="java.util.List" %>
<%@ page import="road.road" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/12/11
  Time: 11:11
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
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.13&key=077c2a8bdc9a5ea44efd3c98e6b4bd03&plugin=AMap.Geocoder,AMap.RoadInfoSearch"></script>
    <script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>


    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <script type="text/javascript" src="javascript/jquery.js"></script>
    <script type="text/javascript" src="javascript/area.js"></script>
    <script type="text/javascript" src="javascript/location.js"></script>
    <script type="text/javascript" src="javascript/select2.js"></script>
    <script type="text/javascript" src="javascript/select2_locale_zh-CN.js"></script>
    <link href="cs/common.css" rel="stylesheet"/>
    <link href="cs/select2.css" rel="stylesheet"/>


    <style>
        html,
        body,
        #container {
            width:100%;
            height:50%;
            /*position:absolute;*/
        }
        #myModal
        {
            width:100%;
            height:100%;
        }
        #rName{
            position:relative;
        }
        .content{
            display:none;
            width:250px;
            height:70px;
            border-radius:10px;
            padding:20px;
            position:relative;
            top:15px;
            left:80px;
            background-color:#2F4056;
        }
        #RoadID
        {
            pointer-events: auto;
        }
        #longitutde
        {
            display:none;
        }
        #latitude
        {
            display: none;
        }
    </style>
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
        <div class="meun-item"><a href="bigdataqd.jsp"><img src="images/icon_rule.png">前台显示</a></div>
    </div>
    <div id="rightContent">
        <a class="toggle-btn" id="nimei">
            <i class="glyphicon glyphicon-align-justify"></i>
        </a>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- 道路管理模块 -->
            <div role="tabpanel" class="tab-pane active" id="road">
                <div class="check-div form-inline">
                    <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addRoad">添加道路信息</button>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 ">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;道路编号
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;道路名称
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            道路等级
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            路面类型
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作
                        </div>
                    </div>

                    <div class="tablebody">
                        <%
                            for(road rd:(List<road>)request.getSession().getAttribute("road"))
                            {%>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1" id="aaa">
                            <%=rd.getRoad_id()%>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            <%=rd.getRoad_name()%>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            &nbsp;&nbsp;&nbsp;&nbsp;<%=rd.getRoad_dj()%>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            <%=rd.getRoad_type()%>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            <button class="btn btn-success btn-xs"><a href="<%=request.getContextPath()%>/modiRoadServlet?road_id=<%=rd.getRoad_id()%>">修改</a></button>
                            <button class="btn btn-danger btn-xs" onclick="javascript:delRoad(<%=rd.getRoad_id()%>)">删除</button>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script language="javaScript">
    function delRoad(road_id){
        if(confirm("确定要删除该道路基本信息？删除该道路后会删除该道路下所有日检和年检记录")){
            alert(road_id);
            window.location.href = "deleteRoadServlet?road_id="+road_id;
        }
        document.form1.action("deleteRoadServlet?road_id="+road_id);
    }
</script>

<div class="modal fade" id="addRoad" role="dialog">
    <form action="addRoadServlet" id="form1">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width:730px; height:600px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <div class="row">
                        <div class="col-xs-4 ">
                            <h4 class="modal-title">添加道路信息</h4>
                        </div>
                        <div class="col-xs-4 " >
                            <button class="btn btn-primary btn-s" data-toggle="modal" data-target="#myModal" id="btn" type="button" href="" onclick="">道路选择</button>
                            <button class="btn btn-primary btn-s" data-toggle="modal" data-target="#downtown" id="choose" type="button">乡镇选择</button>
                            <input type="text" class="form-control input-sm duiqi" id="longitutde"  name="lng"/>
                            <input type="text" class="form-control input-sm duiqi" id="latitude"  name="lat"/>
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <form class="form-horizontal">
                            <div class="row">
                                <script>
                                    function check() {
                                        if ($("#rId").val() == null || $("#rId").val() == "") {
                                            $("#tishi").html("<font color=red>Not Null</font>");
                                        } else {
                                            $("#tishi").html("");
                                        }
                                    }
                                </script>
                                <label for="rName" class="col-xs-2 control-label">道路名称*：</label>
                                <div class="col-xs-3" id="RoadID">
                                    <input type="text" placeholder="请点击按钮选择" class="form-control input-sm duiqi" id="rName" name="road_name" value="" style="color: black" readonly="readonly">
                                </div>
                                <label for="rId" class="col-xs-2 control-label">道路编号*：</label>
                                <div class="col-xs-3 ">
                                    <input type="tel" maxlength="8" minlength="8"  class="form-control input-sm duiqi" id="rId"  name="road_id" placeholder="8位编号" onblur="check()">
                                    <span id="tishi" style="color: red;"></span>
                                </div>
                            </div>
                            <div class="row">
                                <label for="rZx" class="col-xs-2 control-label">道路走向：</label>
                                <div class="col-xs-3">
                                    <%--<input type="" class="form-control input-sm duiqi" id="rZx" name="road_zx">--%>
                                    <select class="form-control input-sm duiqi" id="rZx" name="road_zx" style="height:30px;">
                                        <option value="北——>南">北——>南</option>
                                        <option value="东——>西">东——>西</option>
                                        <option value="南——>北">南——>北</option>
                                        <option value="西——>东">西——>东</option>
                                        <option value="东北——>西南">东北——>西南</option>
                                        <option value="东南——>西北">东南——>西北</option>
                                        <option value="西南——>东北">西南——>东北</option>
                                        <option value="西北——>东南">西北——>东南</option>
                                    </select>
                                </div>
                                <label for="rQd" class="col-xs-2 control-label">起点：</label>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control input-sm duiqi" id="rQd" name="road_qd">
                                </div>
                            </div>
                            <div class="row">
                                <label for="rZd" class="col-xs-2 control-label">终点：</label>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control input-sm duiqi" id="rZd" name="road_zd">
                                </div>
                                <label for="rSjdw" class="col-xs-2 control-label">设计单位：</label>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control input-sm duiqi" id="rSjdw" name="road_sjdw">
                                </div>
                            </div>
                            <div class="row">
                                <label for="rSgdw" class="col-xs-2 control-label">施工单位：</label>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control input-sm duiqi" id="rSgdw" name="road_sgdw">
                                </div>
                                <label for="rDj" class="col-xs-2 control-label">道路等级*：</label>
                                <div class="col-xs-3">
                                    <select class="form-control input-sm duiqi" id="rDj" name="road_dj" style="height:30px;">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <label for="rSs" class="col-xs-2 control-label">设计时速：</label>
                                <div class="col-xs-3">
                                    <input type="number" min=0 step="20" class="form-control input-sm duiqi" id="rSs" name="road_ss">
                                </div>
                                <label for="rKd" class="col-xs-2 control-label">宽度范围：</label>
                                <div class="col-xs-3">
                                    <input type="number" min=0 step="0.5" class="form-control input-sm duiqi" id="rKd" name="road_kd">
                                </div>
                            </div>
                            <div class="row">
                                <label for="rCd" class="col-xs-2 control-label">道路长度：</label>
                                <div class="col-xs-3">
                                    <input type="number" min="0" class="form-control input-sm duiqi" id="rCd" name="road_cd">
                                </div>
                                <label for="rMj" class="col-xs-2 control-label">道路面积：</label>
                                <div class="col-xs-3">
                                    <input type="number" min="0" class="form-control input-sm duiqi" id="rMj" name="road_mj">
                                </div>
                            </div>
                            <div class="row">
                                <label for="rAadt" class="col-xs-2 control-label">A A D T：</label>
                                <div class="col-xs-3">
                                    <input type="number" min="0" step="5" class="form-control input-sm duiqi" id="rAadt" name="road_aadt">
                                </div>
                                <label for="rJtl" class="col-xs-2 control-label">交通量：</label>
                                <div class="col-xs-3">
                                    <input type="number" min="0" step="500" class="form-control input-sm duiqi" id="rJtl" name="road_jtl">
                                </div>
                            </div>
                            <div class="row">
                                <label for="rXz" class="col-xs-2 control-label">所属乡镇：</label>
                                <div class="col-xs-3">
                                    <input type="text" placeholder="请点击按钮选择" style="color: black" class="form-control input-sm duiqi" id="rXz" name="road_xz" disabled="disabled">
                                </div>
                                <label for="rGlfl" class="col-xs-2 control-label">管理分类：</label>
                                <div class="col-xs-3">
                                    <input type="text" style="color: black" class="form-control input-sm duiqi" id="rGlfl" name="road_glfl">
                                </div>
                            </div>
                            <div class="row">
                                <label for="rGldw" class="col-xs-2 control-label">管理单位：</label>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control input-sm duiqi" id="rGldw" name="road_gldw">
                                </div>
                                <label for="rYhdw" class="col-xs-2 control-label">养护单位：</label>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control input-sm duiqi" id="rYhdw" name="road_yhdw">
                                </div>
                            </div>
                            <div class="row">
                                <label for="rJzny" class="col-xs-2 control-label">建造年月*：</label>
                                <div class="col-xs-3">
                                    <input type="date" class="form-control input-sm duiqi" id="rJzny" name="road_jzny">
                                </div>
                                <label for="rType" class="col-xs-2 control-label">路面类型*：</label>
                                <div class="col-xs-3">
                                    <select class="form-control input-sm duiqi" id="rType" name="road_type" style="height:30px;">
                                        <option value="沥青路面">沥青路面</option>
                                        <option value="水泥路面">水泥路面</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="container-fluid">
                    <p style="font-size:15px;font-weight:bolder;color:#EE3F4D">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;※ 带*为必填项目！！！
                    </p>
                </div>
                <script>
                    function dl() {
                        if ($("#rId").val() == null || $("#rId").val() == "") {
                            check();
                        } else {
                            $("#form1").submit();
                        }
                    }
                </script>
                <div class="modal-footer">
                    <button type="button" class="btn btn-xs btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <button type="button" class="btn btn-xs btn-xs btn-green" onclick="dl()">保 存</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </form>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width:580px; height:600px;">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">
                        道路选择
                    </h4>
                </div>
                <div id="container" style="width:580px; height:600px;"></div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <!---乡镇选择模态框-->
    <div class="modal fade" id="downtown" style="width: auto;height: auto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width: auto;height: auto">
                <div class="modal-header">
                    <h4>
                        乡镇选择
                    </h4>
                </div>
                <div class="main">
                    <center>
                        <br>
                        <select  id="loc_province" style="width:120px; margin-left: 50px">
                        </select>
                        <br>
                        <br>
                        <br>
                        <select  id="loc_city" style="width:120px; margin-left: 50px">
                        </select>
                        <br>
                        <br>
                        <br>
                        <select id="loc_town" style="width:120px;margin-left: 50px">
                        </select>
                        <br>
                        <br>
                        <br>
                        <br>
                        <%--<input type="button" id="btnval" value="获取值" style="padding:5px 15px;"/>--%>
                        <input type="button" class="btn btn-primary btn-xs" id="btntext" value="确定" style="padding:8px 20px;color: black" onclick="$('#downtown').hide()"/>
                    </center>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <script type="text/javascript">
        //初始化地图对象，加载地图
        var map = new AMap.Map("container", {
            zoom:13,
            center: ["106.557709", "29.52453"],
            resizeEnable: true
        });
        //构建自定义信息窗体
        var infoWindow = new AMap.InfoWindow({
            anchor: 'middle-left',
            content: '信息窗体',
        });

        var geocoder = new AMap.Geocoder({
            city: '重庆',
            radius: 1000
        });

        function setAnchor(){
            var anchor = this.id;
            infoWindow.setAnchor(anchor)
        }
        //绑定radio点击事件
        var radios = document.querySelectorAll("#coordinate input");
        radios.forEach(function(ratio) {
            ratio.onclick = setAnchor;
        });
        function showInfoClick(e){
            infoWindow.close();
            var address;
            geocoder.getAddress(e.lnglat.getLng()+','+e.lnglat.getLat(), function(status, result) {
                if (status === 'complete'&&result.regeocode) {
                    address = result.regeocode.formattedAddress;
                    //infoWindow.setContent('<span>['+e.lnglat.getLng()+','+e.lnglat.getLat()+']</span><br/><input name="site" value="'+address+'"/><br/><button id="btn" type="submit" onclick="'+on()+'">保存站点</button>')
                    infoWindow.setContent('<span>['+e.lnglat.getLng()+','+e.lnglat.getLat()+']</span><br/><input name="site" value="'+address+'"/><br/>');
                    infoWindow.open(map,[e.lnglat.getLng(),e.lnglat.getLat()]);
                    $.post(
                        "${pageContext.request.contextPath }/addRoadServlet",
                        {
                            //road_name:address,
                            /*lng:e.lnglat.getLng(),
                            lat:e.lnglat.getLat()*/
                        },
                        "text"
                    );
                    $("#rName").val(address);
                    $("#longitutde").val(e.lnglat.getLng());
                    $("#latitude").val(e.lnglat.getLat());
                }else{alert(JSON.stringify(result))}
            });
        }

        // 事件绑定
        //log.success("绑定事件!");
        map.on('click', showInfoClick);
    </script>

    <script type="text/javascript">
        $(document).ready(function(){
            $(".contact").mouseover(function(){
                /* alert($("#est").val()); */
                $(".content").empty();
                $(".content").append($("#rName").val());
                $(".content").show("slow");
                $("#rName").mouseout(function(){
                    $(".content").hide("slow");
                });
            });
        })
    </script>
    <div class="content" style="color: white;"></div>
</div>

</body>
</html>
