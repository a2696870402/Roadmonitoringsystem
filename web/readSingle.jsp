<%@ page import="test.single" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="JDB.JDBConnection" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/12/16
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
        <div class="meun-item"><a href="dailyServlet"><img src="images/icon_house_grey.png">日常巡查情况</a></div>
        <div class="meun-item"><a href="planenessServlet"><img src="images/icon_rule_grey.png">道路平整度</a></div>
        <div class="meun-item" ><a href="damage_infoServlet"><img src="images/icon_card_grey.png">道路设施路面损害情况</a></div>
        <div class="meun-item"><a href="damageServlet"><img src="images/icon_char_grey.png">路面主要损坏类型</a></div>
        <div class="meun-item" style="background-color:#3D4E60"><a href="singleServlet" ><img src="images/icon_chara_grey.png">损坏单项扣分</a></div>
        <div class="meun-item"><a href="sumServlet"><img src="images/icon_user_grey.png">综合指数表</a></div>
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
                    <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addSingle">添加单项扣分</button>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            &nbsp;&nbsp;&nbsp;&nbsp;损坏类型
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路面类型
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            损坏密度
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            损坏扣分
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作
                        </div>
                    </div>

                    <div class="tablebody">
                        <%
                            for(single rd:(List<single>)request.getSession().getAttribute("single"))
                            {%>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            <%=rd.getMain_damage()%>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            <%=rd.getMain_road()%>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            &nbsp;&nbsp;<%=rd.getDensity()%>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=rd.getKoufen()%>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            <button class="btn btn-success btn-xs"><a href="<%=request.getContextPath()%>/modiSingleServlet?main_damage=<%=rd.getMain_damage()%>&main_road=<%=rd.getMain_road()%>&density=<%=rd.getDensity()%>">修改</a></button>
                            <button class="btn btn-danger btn-xs" data-toggle="modal" onclick="javascript:delSingle('<%=rd.getMain_damage()%>','<%=rd.getMain_road()%>','<%=rd.getDensity()%>')">删除</button>
                            <!-- /.modal -->
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script language="javaScript">
    function delSingle(a,b,c){
        if(confirm("确定要删除该单项扣分信息？")){
            window.location.href = "deleteSingleServlet?main_damage="+a+"&main_road="+b+"&density="+c;
        }
        document.form1.action("deleteSingleServlet?main_damage="+a+"&main_road="+b+"&density="+c);
    }
</script>

<div class="modal fade" id="addSingle" role="dialog">
    <form action="addSingleServlet">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width:730px; height:280px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加单项扣分</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <form class="form-horizontal">
                            <div class="row">
                                <%
                                    List<String> list1=new ArrayList<String>();
                                    List<String> list2=new ArrayList<String>();
                                    JDBConnection jdbConnection=new JDBConnection();
                                    try {
                                        //执行sql
                                        final ResultSet resultSet1 = jdbConnection.executeQuery("select distinct main_damage from main_damage");
                                        while(resultSet1.next()){
                                            String a=resultSet1.getString(1);
                                            list1.add(a);

                                        }
                                        final ResultSet resultSet2 = jdbConnection.executeQuery("select distinct main_road from main_damage");
                                        while(resultSet2.next()){
                                            String b=resultSet2.getString(1);
                                            list2.add(b);

                                        }
                                    }catch(SQLException ex){
                                        ex.printStackTrace();
                                    }
                                %>
                                <label for="rMD" class="col-xs-2 control-label">损坏类型*：</label>
                                <div class="col-xs-3 ">
                                    <select class="form-control input-sm duiqi" id="rMD" name="main_damage" style="height:30px;">
                                        <%for(int i=0;i<list1.size();i++){%>
                                        <option><%=list1.get(i)%></option>
                                        <%}%>
                                    </select>
                                </div>
                                <label for="rMR" class="col-xs-2 control-label">路面类型*：</label>
                                <div class="col-xs-3 ">
                                    <select class="form-control input-sm duiqi" id="rMR" name="main_road" style="height:30px;">
                                        <%for(int i=0;i<list2.size();i++){%>
                                        <option><%=list2.get(i)%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <label for="rDE" class="col-xs-2 control-label">损坏密度*：</label>
                                <div class="col-xs-3">
                                    <input type="" class="form-control input-sm duiqi" id="rDE" name="density">
                                </div>
                                <label for="rKF" class="col-xs-2 control-label">损坏扣分*：</label>
                                <div class="col-xs-3">
                                    <input type="" class="form-control input-sm duiqi" id="rKF" name="koufen">
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
                <div class="modal-footer">
                    <button type="button" class="btn btn-xs btn-xs btn-white" data-dismiss="modal">取 消</button>
                    <button type="submit" class="btn btn-xs btn-xs btn-green" name="submit" value="确认">保 存</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </form>
</div>


</body>
</html>
