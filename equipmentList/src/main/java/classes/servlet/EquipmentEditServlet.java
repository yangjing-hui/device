package classes.servlet;

import equipment.Equipment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/edit")
public class EquipmentEditServlet extends HttpServlet {
	
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取设备编号参数
        
        
    }
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        doGet(req,resp);
        String data = req.getParameter("data");
        String sitename = req.getParameter("name1");
        String name = req.getParameter("name2");
        String number = req.getParameter("code");
        String classname = req.getParameter("select");
        String type = req.getParameter("type1");
        String install_time = req.getParameter("time");
        String status = req.getParameter("type2");
        
        // 构造要写入文件的数据
        String newdata = sitename + " " + name + " " + number + " " + classname + " " + type + " " + install_time + " " + status + "\n";
      
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
        	List<Equipment> editlist = new ArrayList<Equipment>();
        	session.setAttribute("editlist", editlist);
        }
        // 从Session中获取之前存储的操作信息列表（如果存在）
        

        	
        
        
        
        // 逐行读取文件并判断设备编号是否匹配删除的设备
        while ((currentLine = reader.readLine()) != null) {
            // 根据设备编号进行匹配，如果匹配，则跳过该行不写入临时文件
            if (currentLine.contains(data)) {
            	writer.write(newdata);
            	infoList.add(newdata);
            	continue;
            }
            
            // 写入临时文件
            writer.write(currentLine);
            writer.newLine();
        }
        
        // 关闭读写器
        writer.close();
        reader.close();
        
        // 删除原始文件
        file.delete();
        
        // 重命名临时文件为原始文件
        tempFile.renameTo(file);
        
    	List<Equipment> editlist = (List<Equipment>) session.getAttribute("editlist");
    	for (String s : infoList) {
    		editlist.add(new Equipment(s));
     	}
        
        // 重定向到设备列表页面或其他页面
        resp.sendRedirect("equipmentList.jsp");
    }

}
