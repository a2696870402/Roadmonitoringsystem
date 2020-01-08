<%--
  Created by IntelliJ IDEA.
  User: ouguangji
  Date: 2019/12/4
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%--引入文件echarts--%>
    <script src="lib/dist/echarts-en.min.js"></script>
    <script src="lib/js/jquery-3.3.1.js"></script>
    <script src="lib/js/jquery.dataTables.min.js"></script>

    <script type="text/javascript" src="lib/css/jquery.dataTables.min.css"></script>
    <link rel="stylesheet" type="text/css" href="lib/css/jquery.dataTables.min.css"/>

    <script type="text/javascript" src="lib/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="lib/js/jquery.dataTables.min.js"/>

    <title>js定时器</title>
</head>
<%--echarts导入--%>

<script type="text/javascript">
var int1=self.setInterval("myCallBack()",1000); //定义定时器1，每一秒钟执行一次
var int2=self.setInterval("MyBack2()",1000);//定义定时器2，每2秒执行一次
var int3; //定义定时器3
</script>
<body>
<button onclick="int1=window.clearInterval(int1)">停止</button>
<%--echarts使用--%>
<div id="echarts1" style="width: 600px;height: 400px;"></div>
<script type="text/javascript">
function myCallBack() {
    var myChart = echarts.init(document.getElementById("echarts1"));
    //ajax后台寻找数据
    var categories = [];
    var values = [];

    $.ajax({
        url: "com01/response",
        type: "post",
        dataType: "json",
        success: function (json) {
            categories = json.categories;
            console.dir(categories);
            values = json.values;
            console.dir(values);
            var option = {
                title:{
                    text:'第一个入门'
                },
                tooltip:{
                    show:true
                },
                legend:{
                    data:['销量']
                },
                xAxis: {
                    type: 'category',
                    data: categories
                },
                yAxis: [{
                    type:'value'
                }],
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: values
                }]
            };
            myChart.setOption(option);
        }
    });
}
</script>
<script language="JavaScript">
    // $("#table1").ready(function () {
    //     var id=document.getElementById("aa");
    //     var tr=document.getElementById("jsahdjas");
    //     tr.value=id.value
    // });
    var tables;
    $(function () {
        acaqure();
        setInterval(acaqure, 1000);
        function acaqure() {
            $.ajax({
                type: 'post',
                url: 'com01/response2',
                dataType: 'json',
                success: function (data) {
                    console.info(data);
                    var table_data = $("#table_data");
                    table_data.empty(); //清空数据
                    if (data) {
                        $.each(data.data, function (i, list) {
                            var table_html = "<tr>" +
                                "<td>" + list.date + "</td>" +
                                "<td>" + list.worker + "</td>" +
                                "<td>" + list.main_damage + "</td>" +
                                "<td>" + list.main_road + "</td>" +
                                "<td>" + list.road_id + "</td>" +
                                "<td>" + list.road_name + "</td>" +
                                "<td>" + list.daily_des + "</td>" +
                                "<td>" + list.daily_note + "</td>" +
                                "</tr>";
                            table_data.append(table_html);
                        })
                    }
                }
            })
        }
    });


</script>
<table id="table1" class="display">
    <thead>
        <tr>
            <th>日期</th>
            <th>巡查人员</th>
            <th>损坏类型</th>
            <th>路面类型</th>
            <th>道路编号</th>
            <th>道路名称</th>
            <th>损坏情况描述</th>
            <th>备注</th>
        </tr>
    </thead>
    <tbody id="table_data"></tbody>
</table>
</body>
</html>
