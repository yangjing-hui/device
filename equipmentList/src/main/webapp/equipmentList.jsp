<%@page import="java.io.BufferedReader" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.InputStreamReader" %>
<%@page import="java.io.File" %>
<%@page import="java.util.Scanner" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="equipment.Equipment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="./css/equipmentList.css">

</head>
<body>
     <%
	 	String path=application.getRealPath("equipments.txt");
		File bookList = new File(path);//创建新的File对象，用于读入设备列表信息
		BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream (path),"UTF-8"));//以追加模式写入文件
 
     	List<Equipment> equipmentList = new ArrayList<>();
     	List<String> infoList = new ArrayList<>();
		while (br.ready()) {
         infoList.add(br.readLine());
         // 转成char加到StringBuffer对象中
     	}
     	for (String s : infoList) {
			equipmentList.add(new Equipment(s));
     	}
    	br.close();
    	
        if(session.isNew()){//此处一定要加入判断session是否需要新建，否则会造成只能保存一条操作的情况！
     	 	List<Equipment> addlist = new ArrayList<Equipment>();
     	 	List<Equipment> editlist = new ArrayList<Equipment>();
     	 	List<Equipment> deletelist = new ArrayList<Equipment>();
     	  	session.setAttribute("addlist", addlist);
     	  	session.setAttribute("editlist", editlist);
     	  	session.setAttribute("deletelist", deletelist);
         }

%>
 		  <!--表格元素-->
 		<table  id="tb1">
 		           <tr>
                <th id="th0" class="as">站点名称</th>
                <th id="th1" class="as">设备名称</th>
                <th id="th2" class="as">设备编码</th>
                <th id="th3" class="as">设备分类</th>
				<th id="th4" class="as">设备类型</th>
				<th id="th5" class="as">安装时间</th>
				<th id="th6" class="as">设备状态</th>
				<th id="th7" class="as">操作</th>				
            </tr>
      <% for (Equipment equipment : equipmentList) { %>
        <tr>
            <td><%= equipment.getSitename()%></td><td><%= equipment.getName()%></td><td><%= equipment.getNumber()%></td>
            <td><%= equipment.getClassname()%></td><td><%= equipment.getType()%></td>
            <td><%= equipment.getInstall_time()%></td><td><%= equipment.getStatus()%></td>
            <td>
                <a href="javascript:void(0);" onclick="update(this,'<%=equipment.getNumber()%>')">修改</a>
			<!-- 在设备表格中的删除链接中添加URL和设备编号 -->
			<a href="delete?number=<%=equipment.getNumber()%>">删除</a>
            </td>
        </tr>
        <%}%>
 		</table>

 
		
		<form method="POST" id="dataForm" action="EquipmentAddServlet" accept-charset="UTF-8">
			<label for="name1">站点名称：</label>
						<select size="1" id="name1" name="name1">
						<option value="" selected>...</option>
						<option value="昌都生态监测站">昌都生态监测站</option>
						<option value="申扎生态监测站">申扎生态监测站</option>
						<option value="生喀生态监测站">生喀生态监测站</option>
						<option value="定日生态监测站">定日生态监测站</option>
						<option value="山南生态监测站">山南生态监测站</option>
						<option value="那曲生态监测站">那曲生态监测站</option>
						<option value="改则生态监测站">改则生态监测站</option>
						<option value="芒康生态监测站">芒康生态监测站</option>
					</select><br>
			<label for="name2">设备名称：</label>
						<input type="text" id="name2" name="name2" size="30"><br>
			<label for="code">设备编码：</label>
						<input type="text" id="code" name="code" size="30"><br>
			<label for="select">设备分类：</label>
						<select size="1" id="select" name="select">
						<option value="" selected>...</option>
						<option value="土壤">土壤</option>
						<option value="光照">光照</option>
						</select><br>
			<label for="type1">设备类型：</label>
						<select size="1" id="type1" name="type1">
						<option value="" selected>...</option>
						<option value="冻土检测">冻土检测</option>
						<option value="生物检测">生物检测</option>
						<option value="光照检测">光照检测</option>
						</select><br>
			<label for="time">安装时间：</label>
						<input type="text" id="time" name="time" size="30"><br>
			<label for="type2">设备状态：</label>
					<select size="1" id="type2" name="type2">
						<option value="" selected>...</option>
						<option value="正常">正常</option>
						<option value="不正常">不正常</option>
					</select><br>
			<input type="button" onclick="resets()" value="取消">
			<input type="submit" value="提交" id="ft">
			<input type="button" value="操作信息" onclick="window.location='OperLists.jsp'" id="optionButton">
		</form>


    </body>
</html>

<script type="text/javascript" src="./js/text.js" ></script>