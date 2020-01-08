<%@ page import="planeness.planeness" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="JDB.JDBConnection" %><%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2019/12/16
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>--%>
<%--        <meta charset="utf-8">--%>
<%--        <!--引入Bootstrap中的css样式-->--%>
<%--        <link rel="stylesheet" href="css/bootstrap.min.css">--%>
<%--        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">--%>
<%--        <meta name="format-detection" content="telephone=no">--%>
<%--        <title>道路养护管理</title>--%>
<%--        <script src="js/jquery.min.js"></script>--%>
<%--        <script src="js/bootstrap.min.js"></script>--%>
<%--        <script>--%>
<%--            $(function() {--%>
<%--                $(".meun-item").click(function() {--%>
<%--                    $(".meun-item").removeClass("meun-item-active");--%>
<%--                    $(this).addClass("meun-item-active");--%>
<%--                    var itmeObj = $(".meun-item").find("img");--%>
<%--                    itmeObj.each(function() {--%>
<%--                        var items = $(this).attr("src");--%>
<%--                        items = items.replace("_grey.png", ".png");--%>
<%--                        items = items.replace(".png", "_grey.png")--%>
<%--                        $(this).attr("src", items);--%>
<%--                    });--%>
<%--                    var attrObj = $(this).find("img").attr("src");--%>
<%--                    ;--%>
<%--                    attrObj = attrObj.replace("_grey.png", ".png");--%>
<%--                    $(this).find("img").attr("src", attrObj);--%>
<%--                });--%>
<%--                $("#topAD").click(function() {--%>
<%--                    $("#topA").toggleClass(" glyphicon-triangle-right");--%>
<%--                    $("#topA").toggleClass(" glyphicon-triangle-bottom");--%>
<%--                });--%>
<%--                $("#topBD").click(function() {--%>
<%--                    $("#topB").toggleClass(" glyphicon-triangle-right");--%>
<%--                    $("#topB").toggleClass(" glyphicon-triangle-bottom");--%>
<%--                });--%>
<%--                $("#topCD").click(function() {--%>
<%--                    $("#topC").toggleClass(" glyphicon-triangle-right");--%>
<%--                    $("#topC").toggleClass(" glyphicon-triangle-bottom");--%>
<%--                });--%>
<%--                $(".toggle-btn").click(function() {--%>
<%--                    $("#leftMeun").toggleClass("show");--%>
<%--                    $("#rightContent").toggleClass("pd0px");--%>
<%--                })--%>
<%--            })--%>
<%--        </script>--%>
<%--        <!--[if lt IE 9]>--%>
<%--        <script src="js/html5shiv.min.js"></script>--%>
<%--        <script src="js/respond.min.js"></script>--%>
<%--        <![endif]-->--%>
<%--        <link href="css/bootstrap.min.css" rel="stylesheet">--%>
<%--        <link href="css/common.css" rel="stylesheet" type="text/css">--%>
<%--        <link href="css/slide.css" rel="stylesheet" type="text/css">--%>
<%--        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">--%>
<%--        <link href="css/flat-ui.min.css" rel="stylesheet" type="text/css">--%>
<%--        <link href="css/jquery.nouislider.css" rel="stylesheet" type="text/css">--%>
<%--</head>--%>
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
<form>
<form method="post" action="planenessModServlet">
</form>
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
            <div class="meun-item" style="background-color:#3D4E60"><a href="planenessServlet"><img src="images/icon_rule_grey.png">道路平整度</a></div>
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
            <form method="post" action="planenessModServlet">
                <div class="tab-content">
                    <!-- 道路管理模块 -->
                    <div role="tabpanel" class="tab-pane active" id="planeness">
                        <div class="data-div">
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                            <% planeness p = (planeness)request.getSession().getAttribute("planeness");%>
                                        <div class="row">
                                            <label for="p_date"class="col-xs-2 control-label">记录日期*：</label>
                                            <div class="col-xs-2 ">
                                                <input type="date" readonly="readonly" class="form-control input-sm duiqi" id="p_date" name="p_date" value="<%=p.getP_date()%>" style="color: black">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="road_id" readonly="readonly" class="col-xs-2 control-label">道路编号*：</label>
                                            <div class="col-xs-2 ">
                                                <input type="text"  readonly="readonly" class="form-control input-sm duiqi" id="road_id"  name="road_id" value="<%=p.getRoad_id()%>" style="color: black">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="road_name" readonly="readonly" class="col-xs-2 control-label">道路名称*：</label>
                                            <div class="col-xs-2 ">
                                                <input type="text"  readonly="readonly" class="form-control input-sm duiqi" id="road_name" name="road_name" value="<%=p.getRoad_name()%>" style="color: black">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <%
                                                List<String> list1;
                                                list1 = new ArrayList<String>();
                                                List<String> list2=new ArrayList<String>();
                                                JDBConnection jdbConnection=new JDBConnection();
                                                try {
                                                    //执行sql
                                                    final ResultSet resultSet1 = jdbConnection.executeQuery("select per from workers");
                                                    while(resultSet1.next()){
                                                        String a=resultSet1.getString(1);
                                                        list1.add(a);
                                                    }
                                                }catch(SQLException ex){
                                                    ex.printStackTrace();
                                                }
                                                %>
                                            <label for="p_worker" class="col-xs-2 control-label">检测人员：</label>
                                            <div class="col-xs-2">
                                                <select class="form-control input-sm duiqi" id="p_worker" name="p_worker" value="<%=p.getP_worker()%>"style="height:30px">
                                                    <%for(int i=0;i<list1.size();i++){%>
                                                    <option><%=list1.get(i)%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="p_iri" class="col-xs-2 control-label">IRI：</label>
                                            <div class="col-xs-2">
                                                <input type="number" min=0 step="0.001" max="2000" class="form-control input-sm duiqi" id="p_iri" name="p_iri" value="<%=p.getP_iri()%>">
                                            </div>
                                        </div>
                                                <div class="container-fluid">
                                                    <p style="font-size:15px;font-weight:bolder;color:#EE3F4D">
                                                        ※ 带*为必填项目！！！
                                                    </p>
                                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-xs  btn-green" name="submit" value="确认">保存</button>
                                    <button class="btn btn-xs" ><a href="planenessServlet">返回</a></button>
                                </div>
                            </div>
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
