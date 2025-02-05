package classes.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.*;
import equipment.Equipment;

@WebServlet("/delete")
public class EquipmentDeleteServlet extends HttpServlet {
	
	List<String> infoList = new ArrayList<>();
	
    private void deleteEquipmentByNumber(String number) throws IOException {
        String filePath = getServletContext().getRealPath("equipments.txt");
        File file = new File(filePath);
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        
        // 创建读取和写入文件的读写器
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        
        String currentLine;
        
        
        
        
        
        // 逐行读取文件并判断设备编号是否匹配删除的设备
        while ((currentLine = reader.readLine()) != null) {
            // 根据设备编号进行匹配，如果匹配，则跳过该行不写入临时文件
            if (currentLine.contains(number)) {
                infoList.add(currentLine);
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
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         	
    	// 获取设备编号参数
        String number = request.getParameter("number");
        
        // 调用删除设备的方法
        deleteEquipmentByNumber(number);
        
        // 获取当前Session对象
        HttpSession session = request.getSession();
        if(session.isNew()){
        	List<Equipment> deletelist = new ArrayList<Equipment>();
        	session.setAttribute("deletelist", deletelist);
        }
        // 从Session中获取之前存储的操作信息列表（如果存在）
        
        	List<Equipment> deletelist = (List<Equipment>) session.getAttribute("deletelist");
        	for (String s : infoList) {
    			deletelist.add(new Equipment(s));
         	}
        	
        	
        // 重定向到设备列表页面
        response.sendRedirect("equipmentList.jsp");
    }

}
