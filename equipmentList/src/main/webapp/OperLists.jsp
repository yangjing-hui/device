<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="equipment.Equipment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>操作记录</title>
</head>
<body>

<%if(session.getAttribute("addlist")!=null){ %>
	<table border="1">
	<caption>新增设备</caption>
	 <thead>
    <tr>
        <th>站点名称</th><th>设备名称</th><th>设备编码</th><th>设备分类</th><th>设备类型</th>
        <th>安装时间</th><th>设备状态</th>
    </tr>
        </thead>
		<%
		List<Equipment> addlist=(List<Equipment>) session.getAttribute("addlist");
		%>     
			<%for(int i=0;i<addlist.size();i++){
        %>
   		<tr>
			<td><%=addlist.get(i).getSitename()%></td>
			<td><%=addlist.get(i).getName()%></td>
			<td><%=addlist.get(i).getNumber()%></td>
			<td><%=addlist.get(i).getClassname()%></td>
			<td><%=addlist.get(i).getType()%></td>
			<td><%=addlist.get(i).getInstall_time()%></td>
			<td><%=addlist.get(i).getStatus()%></td>
		</tr>
			<%}%>

		<br>
	</table>
	<%}%>
	<br>
	<br>
<%if(session.getAttribute("editlist")!=null){ %>
<table border="1">
	<caption>修改设备</caption>
	 <thead>
    <tr>
        <th>站点名称</th><th>设备名称</th><th>设备编码</th><th>设备分类</th><th>设备类型</th>
        <th>安装时间</th><th>设备状态</th><th>版本</th>
    </tr>
        </thead>
	<%
		List<Equipment> editlist=(List<Equipment>) session.getAttribute("editlist");
		%>
			<%for(int i=0;i<editlist.size();i++){
        %>
   			<tr>
			<td><%=editlist.get(i).getSitename()%></td>
			<td><%=editlist.get(i).getName()%></td>
			<td><%=editlist.get(i).getNumber()%></td>
			<td><%=editlist.get(i).getClassname()%></td>
			<td><%=editlist.get(i).getType()%></td>
			<td><%=editlist.get(i).getInstall_time()%></td>
			<td><%=editlist.get(i).getStatus()%></td>
			<td>新值</td>
			</tr>
			<%}%>
		<br>
	</table>
	<%} %>
	<br>
	<br>
<%if(session.getAttribute("deletelist")!=null){ %>
<table border="1">
	<caption>删除设备</caption>
	 <thead>
	     <tr>
        <th>站点名称</th><th>设备名称</th><th>设备编码</th><th>设备分类</th><th>设备类型</th>
        <th>安装时间</th><th>设备状态</th>
    </tr>
        </thead>
		<%
		List<Equipment> deletelist=(List<Equipment>) session.getAttribute("deletelist");
		%>     
			<%for(int i=0;i<deletelist.size();i++){
        %>
       		<tr>
			<td><%=deletelist.get(i).getSitename()%></td>
			<td><%=deletelist.get(i).getName()%></td>
			<td><%=deletelist.get(i).getNumber()%></td>
			<td><%=deletelist.get(i).getClassname()%></td>
			<td><%=deletelist.get(i).getType()%></td>
			<td><%=deletelist.get(i).getInstall_time()%></td>
			<td><%=deletelist.get(i).getStatus()%></td>
			</tr>
			<%}%>
		<br>
	</table>
	<%} %>
</body>
</html>