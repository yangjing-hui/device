package classes.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import equipment.Equipment;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.*;

@WebServlet("/EquipmentAddServlet")

public class EquipmentAddServlet extends HttpServlet {
    @Override
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String sitename = req.getParameter("name1");
        String name = req.getParameter("name2");
        String number = req.getParameter("code");
        String classname = req.getParameter("select");
        String type = req.getParameter("type1");
        String install_time = req.getParameter("time");
        String status = req.getParameter("type2");
        
        // 构造要写入文件的数据
        String data = sitename + " " + name + " " + number + " " + classname + " " + type + " " + install_time + " " + status + "\n";
      
        String filePath = getServletContext().getRealPath("equipments.txt");
        File file = new File(filePath);
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        
        // 创建读取和写入文件的读写器
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        
        String currentLine;
        List<String> infoList = new ArrayList<>();
        
        // 获取当前Session对象
        HttpSession session = req.getSession();
        if(session.isNew()){
        	List<Equipment> addlist = new ArrayList<Equipment>();
        	session.setAttribute("addlist", addlist);
        }
        // 从Session中获取之前存储的操作信息列表（如果存在）
        
 
        
        
        // 逐行读取文件并判断设备编号是否匹配删除的设备
        while ((currentLine = reader.readLine()) != null) {  
            // 写入临时文件
            writer.write(currentLine);
            writer.newLine();
        }
        writer.write(data);
        infoList.add(data);
        
        // 关闭读写器
        writer.close();
        reader.close();
        
        // 删除原始文件
        file.delete();
        
        // 重命名临时文件为原始文件
        tempFile.renameTo(file);
        
       	List<Equipment> addlist = (List<Equipment>) session.getAttribute("addlist");
    	for (String s : infoList) {
			addlist.add(new Equipment(s));
     	}
    	
        
        // 重定向到设备列表页面或其他页面
        resp.sendRedirect("equipmentList.jsp");
    }
}
