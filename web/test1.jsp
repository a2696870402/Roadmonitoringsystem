<%--
  Created by IntelliJ IDEA.
  User: ouguangji
  Date: 2019/12/11
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   <!-- 在IE运行最新的渲染模式 -->
    <link href="lib/css/BigData.css" rel="stylesheet" type="text/css" />
    <link href="lib/css/index.css" rel="stylesheet" type="text/css" />
    <link href="lib/css/index01.css" rel="stylesheet" type="text/css" />
    <script src="lib/js/jquery.js"></script>
    <link href="lib/js/bstable/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="lib/js/bstable/css/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="lib/css/Security_operation.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="lib/js/artDialog/skins/default.css" type="text/css"/>
    <script src="lib/js/laydate.js"></script>
    <script src="lib/js/Home_page.js"></script>
    <script src="lib/js2/echarts.min.js"></script>
    <meta charset="UTF-8">

    <script src="lib/js/index.js"></script>
    <script src="lib/js/bstable/js/bootstrap.min.js"></script>
    <script src="lib/js/bstable/js/bootstrap-table.js"></script>
    <script type="text/javascript" src="lib/js/jquery.pagination.js"></script>
    <script src="lib/js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5ieMMexWmzB9jivTq6oCRX9j&callback"></script>
    <script src="lib/js/Home_page.js"></script>
    <script src="lib/js/artDialog/artDialog.js"></script>
    <script src="lib/js/artDialog/plugins/iframeTools.source.js"></script>
    <script src="lib/js2/echarts.min.js"></script>
    <script src="lib/js/echarts.min.js"></script>
    <script src="lib/src/echarts.js"></script>

    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=	60a14df622c13b4575bde3a01819265d"></script>
    <!-- 下一行是左边的放大缩小移动操作台 -->
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>


    <title>大数据管理平台</title>
</head>
<script>
    <%--日检信息--%>
    $(function () {
        acaqure();
        setInterval(acaqure, 60000);
        function acaqure() {
            $.ajax({
                type: 'post',
                url: 'com01/response2',
                dataType: 'json',
                success: function (data) {
                    console.info(data);
                    var div_rijian = $("#div_rijian");
                    div_rijian.empty(); //清空数据
                    if (data) {
                        $.each(data.data, function (i, list) {
                            var table_html;
                            table_html=
                                "<div class=\"message_scroll\" style=\"height: auto\">\n"+
                                "<div class=\"scroll_top\">"+
                                "<span class=\"scroll_title\">路面日检信息</span>"+
                                "<span class=\"scroll_level scroll_level01\" id=\"level\">"+list.road_level+"</span>"+
                                "<a class=\"localize\"></a>"+
                                "<span class=\"scroll_timer\" id=\"rijian_time\">"+list.date+"</span>"+
                                "</div>"+
                                "<div style=\"color: antiquewhite\">"+
                                "<div>"+
                                "<p>检查人员：<a id=\"persion\">"+list.worker+"xiaowang</a>"+
                                "</p>"+
                                "</div>"+
                                "<div>"+
                                "<p>损坏类型:<a id=\"shlx\">"+list.main_damage+"</a></p>"+
                                "<p>路面类型:<a id=\"lmlx\">"+list.main_road+"</a></p>"+
                                "<p>道路编号:<a id=\"dlbh\">"+list.road_id+"</a></p>"+
                                "<p>道路名称:<a id=\"dlmc\">"+list.road_name+"</a></p>"+
                                "</div>"+
                                "<div class=\"text-info\">"+
                                "<p>损坏情况:<a id=\"shqk\">"+list.daily_des+"</a></p>"+
                                "<p>备注:<a id=\"beizhu\">"+list.daily_note+"</a></p>"+
                                "</div>"+
                                "</div>"+
                                "</div>";
                            div_rijian.append(table_html);
                        })
                    }
                }
            })
        }
    });
</script>
<body >
<div class="data_bodey">
    <div class="index_nav" >
        <ul style="height: 30px; margin-bottom: 0px;">
            <li class="l_left total_chose_fr nav_active" href="">实时显示</li>
            <li class="l_left total_chose_fr nav_active"><a href="roadServlet">后台管理</a></li>
        </ul>
        <div class="clear"></div>
    </div>
    <div class="index_tabs" >
        <div class="inner" style="height: 109%;">
            <div class="left_cage">
                <div class="dataAllBorder01 cage_cl" style="margin-top: 9% !important; height: 24%;">
                    <video autoplay="autoplay" loop="loop" class="dataAllBorder02 video_cage">
                        <source class="video" title="主监控位" src="lib/video/test_mv02.mov"/>
                    </video>
                </div>
                <div class="dataAllBorder01 cage_cl" style="margin-top: 1.5% !important; height: 69%; position: relative;">
                    <div class="dataAllBorder02" style="padding: 2.0%; overflow: hidden">
                        <div class="message_scroll_box" id="div_rijian"  style="margin-top: 0px;">
                            <%--<div class="message_scroll"style="height: auto">--%>

                            <%--</div>--%>
                        </div>

                    </div>
                </div>

            </div>
            <div class="center_cage">
                <div class="dataAllBorder01 cage_cl" style="margin-top: 3.5% !important; height: 62.7%; position: relative;">
                    <div class="dataAllBorder02" style="position: relative; overflow: hidden;">
                        <!--标题栏-->
                        <div class="map_title_box" style="height: 6%">
                            <div class="map_title_innerbox">
                                <div class="map_title">实时地图</div>
                            </div>
                        </div>
                        <%--显示地图--%>
                        <div class="map" id="map"  >
                        </div>
                    </div>
                </div>

                <div class="dataAllBorder01 cage_cl" style="margin-top: 0.6% !important; height: 32.1%;">
                    <div class="dataAllBorder02" id="map_title_innerbox">
                        <div class="map_title_box">
                            <div class="map_title_innerbox">
                                <div class="map_title" style="background-image: url(lib/img/second_title.png);">年检报告</div>
                            </div>
                            <div >
                                <select id="selLayer" style="width:100px;">
                                    <option value="">道路</option>
                                </select>
                            </div>
                        </div>
                        <table id="table" style="width: 100%">

                        </table>
                    </div>

                </div>
            </div>
            <div class="right_cage">
                <%--显示动态图--%>
                <div class="dataAllBorder01 cage_cl" style="margin-top: 9% !important; height: 40%">
                    <div class="dataAllBorder02 cage_cl" style="background-color: #5bc0de">
                        <div style="height: 85%;align-content: center" id="main_charts" class="weherChart">

                        </div>
                    </div>
                </div>
                <div class="dataAllBorder01 cage_cl check_increase" style=" margin-top: 1.5% !important; height: auto">
                    <div class="dataAllBorder02 over_hide dataAllBorder20" id="over_hide1"  style="">
                        <%--<div class="dataAllBorder01 cage_cl" style="margin-top: 1.5% !important; height: 32%; position: relative;" onload="userdata()">--%>

                        <%--</div>--%>
                    </div>
                </div>
                <div class="dataAllBorder01 cage_cl check_decrease" style="overflow:scroll; margin-top: 1.5% !important; height: 50%; position: relative;">
                    <div class="dataAllBorder02" style="padding: 2.0%; overflow: hidden;height: auto">
                        <div class="message_scroll" style="height: auto;text-align: center">
                            <a style="color: white;text-align: center">检查人员</a>
                        </div>
                        <div class="message_scroll" style="height: auto;text-align: center">
                            <table id="user_table" class="dataAllBorder01" style="color: white">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script language="JavaScript">
    $(function () {
        $('#user_table').bootstrapTable({
            method: "post",
            striped: true,
            url: "com01/userdatare",
            dataType: "json",
            pagination: false, //分页
            pageSize: 7,
            pageNumber: number,
            search: false, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
                {
                    field: 'name',
                    align: 'center',
                    valign: 'middle',
                    style:'color:white'
                }
            ]
        });
        BootstrapTable();
        init_echarts();
    });
</script>
<%--显示动态图--%>
<script src="lib/echarts.all.js"></script>
<script language="JavaScript">
    function init_echarts() {
        var mychart01=echarts.init(document.getElementById('main_charts'));
        var s1=[]; //综合指标数组
        var s2=[]; //平整度数组
        var s3=[]; //破损状况数组
        var time_data=[]; //时间数组
        var option;
        $.ajax({
            type:'post',
            url:'com01/charts',
            dataType:'json',
            success:function (data) {
                if(data!=null&&data.data.length>0){
                    $.each(data.data, function (i, list) {
                        s1.push(list.zonghe);
                        s2.push(list.pingzheng);
                        s3.push(list.poshun);
                        time_data.push(list.data);
                    });
                    mychart01.hideLoading(); //隐藏加载动画
                    option = {
                        title: {
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['综合指标','平整度','破损状况']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data: time_data
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name:'综合指标',
                                type:'line',
                                stack: '总量',
                                data:s1
                            },
                            {
                                name:'平整度',
                                type:'line',
                                stack: '总量',
                                data:s2
                            },
                            {
                                name:'破损状况',
                                type:'line',
                                stack: '总量',
                                data:s3
                            }
                        ]
                    };
                    mychart01.setOption(option);
                }
                else{
                    alert("图表请求数据为空，可能服务器暂未录入近时间的观测数据，您可以稍后再试！");
                    mychart01.hideLoading();
                }
            },
            error:function () {
                alert("图表请求失败，可能是服务器开小差了!");
                mychart01.hideLoading();
            }
        });
    }
</script>
<!--<script src="js/MainMap.js"></script>-->

<style>
    /*人为制造一个占据整个屏幕的Div,其透明度为0.7且z-index为9999使之前的页面被压在底层无法点击*/
    #fullScreen
    {
        position: fixed;
        width: 100%;
        height: 100vh;
        left: 0;
        top: 0;
        opacity: 0.7;
        background-color: black;
        z-index: 9999;
        border: 2px solid #00e765;
    }
    /*浮层,可随意设置大小宽高，但是z-index必须比上面fullScreen大才能显示出来*/
    #floatLayer
    {
        position: fixed;
        width: 500px;
        height: 500px;
        left: 34%;
        top: 15%;
        background-color: white;
        z-index: 10000;
    }
</style>
<script>
    var number;
    $(function() {
        if (window.screen.height <= 768) {
            number = 4;
        } else if (window.screen.height > 768 && window.screen.height <= 900) {
            number = 6
        } else if (window.screen.height > 1080) {
            number = 8
        }
    });
    //    翻页模块
    $(".tcdPageCode").createPage({
        pageCount:5,
        current:1,
        backFn:function(p){}
    });
    $(".chemistry_tcdPageCode").createPage({
        pageCount:4,
        current:1,
        backFn:function(p){}
    });
    $(".enterprise_tcdPageCode").createPage({
        pageCount:4,
        current:1,
        backFn:function(p){}
    });
    $(".car_tcdPageCode").createPage({
        pageCount:4,
        current:1,
        backFn:function(p){}
    });
    $(function () {
        $(".tit02Diva a").each(function (index) {
            $(this).on("click",function () {
                $(".data_map").eq(index).fadeIn().siblings(".data_map").stop().hide();
                $(this).prev('i').removeClass('i_crlieAction');
                $(this).siblings('a').prev('i').addClass('i_crlieAction');

            })
        });
        Echarts();
        $("#fresh_tool").click(function(event){
            event.stopPropagation();
            cancel();
        })

    });
    function EventClick(){
        $(".check_increase").removeClass("check_increase_act");
        $("#over_hide1").show().siblings().hide();
        $(".check_decrease").show();
        $("#cage_cl").hide();
        $("#map_title_innerbox").hide();
        $("#cage_cl1").show();
//        $("#over_hide").show();
        $("#map_title_innerbox1").show();
        $(".addition_check_in").hide();
        $("#car_check_in").hide();
        BootstrapTable();
    }
    function cancel(e){
        $(".check_increase").removeClass("check_increase_act");
        $("#over_hide").show().siblings().hide();
        $(".check_decrease").show();
        $("#cage_cl").show();
        $("#cage_cl1").hide();
//        $("#cage_cl").show();
//        $("#over_hide1").show();
//        $("#map_title_innerbox").show();
        //BootstrapTable();
    }

    $('#table').bootstrapTable({
        pagination: true, //分页
        striped: true,
        pageSize: 5,
        pageNumber: number,
        search: false, //显示搜索框
        contentType: "application/x-www-form-urlencoded",
        queryParams: null,
        columns: [
            {
                title: '日期',
                field: 'd_data',
                width: 100,
                align: 'center',
                valign: 'middle'
            },
            {
                title: '道路编号',
                field: 'road_id',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '道路名称',
                width:80,
                field: 'road_name',
                align: 'center'
            },
            {
                title: '损坏类型',
                field: 'main_damage',
                width:200,
                align: 'center'
            },
            {
                title: '损坏面积',
                field: 'd_area',
                width:80,
                align: 'center'
            },
            {
                title: '操作',
                field: 'load',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<a href="#" onclick="div_xiangxi(\''+row.d_data+'\',\''+row.d_worker+'\')">查看详情</a >';
                    var d = '<a  href="#" title="" onclick="delete_div(\'' + row.d_data + '\',\''+row.road_id+'\',\''+row.main_damage+'\')">删除</a> ';
                    return e + d;
                }
            }
        ]
    });

    function bootstrap_shuaxin() {
        var opt={
            method: "get",
            url: "com01/response3",
            dataType: "json",
        };
        $('#table').bootstrapTable('refresh',opt);
    }
    function BootstrapTable() {
        //触发 管理员table显示
        bootstrap_shuaxin();
    }
    function delete_div(data,roid_id,main_damage) {
        //发回一个删除选项
        // String d_data=request.getParameter("d_data");
        // String road_id=request.getParameter("road_id");
        // String main_damage=request.getParameter("main_damage");
        $.ajax({
            type:'post',
            data:{"d_data":data,"road_id":roid_id,"main_damage":main_damage},
            url:'com01/delete_nianjian',
            dataType:'json',
            success:function () {
                alert(data+","+roid_id+","+main_damage+" 删除成功!");
                //执行一次 ajax 即可
                //刷新页面
                bootstrap_shuaxin();
            }
        });
    }
    function div_xiangxi(d_data,d_worker) {
        //由该id传回后台得到详细信息
        //返回类型json
        var html=$("#fullScreen").html();
        $("body").append(html);
        $.ajax({
            type: 'post',
            data:{"d_date":d_data,"d_worker":d_worker},
            url: 'com01/return_nianjian_id',
            dataType: 'json',
            success: function (data) {
                console.info(data);
                var div_rijian = $("#table_xiangxi");
                div_rijian.empty(); //清空数据
                if (data) {
                    $.each(data.data, function (i, list) {
                        var table_html;
                        var d_data=list.d_data;
                        var d_worker=list.d_worker;
                        var main_damage=list.main_damage;
                        var main_road=list.main_road;
                        var road_id=list.road_id;
                        var road_name=list.road_name;
                        var d_start=list.d_start;
                        var d_Alllen=list.d_Alllen;
                        var d_Alllwidth=list.d_Alllwidth;
                        var d_len=list.d_len;
                        var d_width=list.d_width;
                        var d_height=list.d_height;
                        var d_des=list.d_des;
                        var d_area=list.d_area;
                        var d_note=list.d_note;

                        table_html=
                            "<tr>"+
                            "<th>时间</th>"+
                            "<td>"+d_data+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>工作人员</th>"+
                            "<td>"+d_worker+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>道路编号</td>"+
                            "<td>"+road_id+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>道路名称</th>"+
                            "<td>"+road_name+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>起始位置</th>"+
                            "<td>"+d_start+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>检查总长</th>"+
                            "<td>"+d_Alllen+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>损坏类型</th>"+
                            "<td>"+main_damage+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>路面类型</th>"+
                            "<td>"+main_road+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>损坏长</th>"+
                            "<td>"+d_len+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>损坏宽</th>"+
                            "<td>"+d_width+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>损坏高</th>"+
                            "<td>"+d_height+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>损坏面积</th>"+
                            "<td>"+d_area+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>检查总宽</th>"+
                            "<td>"+d_Alllwidth+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>备注</th>"+
                            "<td>"+d_note+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<th>损坏位置及损坏情况描述</th>"+
                            "<td>"+d_des+"</td>"+
                            "</tr>";
                        div_rijian.append(table_html);
                    })
                }
            }
        });
        //显示出一个框

        //显示该div
        document.getElementById("fullScreen").style.display="";
        document.getElementById("photo_xq").style.display="";
    }
    function div_xiangxi_close() {
        //关闭框
        document.getElementById("fullScreen").style.display="none";
        document.getElementById("photo_xq").style.display="none";
    }

    function TimeControl(){
        $(".message_scroll_box").animate({marginTop:96},800,
            function(){
                $(".message_scroll_box").css({marginTop:0});    //把顶部的边界清零
                $(".message_scroll_box .message_scroll:first").before($(".message_scroll_box .message_scroll:last"));    //在第一个新闻后面插入最后一个新闻

            });
    }
    var T=setInterval(TimeControl,2300);    //开始定时
    $(".message_scroll_box").mouseenter(function(){
        clearInterval(T);    //停止定时
    })
        .mouseleave(function(){
            T=setInterval(TimeControl,2500);    //再次定时
        })
</script>
<div class="dataAllBorder01 cage_cl" id="fullScreen"  style="display: none; text-align: center;">
    <div class="dataAllBorder02" id="map_title_innerbox" style="width: auto;margin: 0 auto;">
        <div class="map_title_box">
            <div class="map_title_innerbox">
                <div class="map_title" id="photo_xq" style="display:none;background-image: url(lib/img/second_title.png);">详情</div>
            </div>
        </div>
        <table id="table_xiangxi" class="table-bordered" style="color: white; background-color: #144490;margin: 0 auto;align-items: center;width: 80%">
            <tr>
                <td>时间</td>
                <td>2019-11-01</td>
            </tr>
            <tr>
                <td>工作人员</td>
                <td>小王</td>
            </tr>

            <tr>
                <td>道路编号</td>
                <td>1001</td>
            </tr>

            <tr>
                <td>道路名称</td>
                <td>春熙路</td>
            </tr>

            <tr>
                <td>起始位置</td>
                <td>2</td>
            </tr>
            <tr>
                <td>检查总长</td>
                <td>100</td>
            </tr>
            <tr>
                <td>损坏类型</td>
                <td>xxx</td>
            </tr>

            <tr>
                <td>损坏长</td>
                <td>20</td>
            </tr>
            <tr>
                <td>损坏宽</td>
                <td>10</td>
            </tr>
            <tr>
                <td>损坏高</td>
                <td>100</td>
            </tr>
            <tr>
                <td>损坏面积</td>
                <td>90</td>
            </tr>
            <tr>
                <td>检查总宽</td>
                <td>50</td>
            </tr>
            <tr>
                <td>备注</td>
                <td>1111</td>
            </tr>
            <tr>
                <td>损坏位置及损坏情况描述</td>
                <td>无</td>
            </tr>
        </table>
        <button onclick="div_xiangxi_close()">关闭</button>
    </div>
</div>
<script type="text/javascript">
    $.ajax({
        type:'post',
        url:'${pageContext.request.contextPath}/getMap',
        dataType:'json',
        async:'true',
        success: function (data) {
            var map = new AMap.Map("map", {resizeEnable: true});
            var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
            $.each(data.res, function (i, list) {
                var marker = new AMap.Marker({
                    position: new AMap.LngLat(list.lng,list.lat),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                    map: map,
                });
                marker.content = '<strong>道路编号：' + list.road_id + '</strong></br>';
                marker.content+='<strong>道路名称：'+list.road_name+'</strong>';
                marker.content += '<div>经度：'+list.lng+'</div>';
                marker.content += '<div>纬度：'+list.lat+'</div>';
                marker.orderno=list.road_id;
                marker.on('mouseover',function(e){
                    infoWindow.setContent(e.target.content);
                    infoWindow.open(map, e.target.getPosition());
                });
                marker.on('mouseout',function(e){
                    infoWindow.close(map, e.target.getPosition());
                });
                marker.on('click',function (e) {
                    map.setZoomAndCenter(18,e.target.getPosition());
                })
            })
            map.setFitView();
        }
    });
</script>

</body>
</html>




























