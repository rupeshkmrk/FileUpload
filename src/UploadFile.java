

import java.beans.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.sun.xml.internal.ws.wsdl.writer.document.Part;

/**
 * Servlet implementation class UploadFile
 */
public class UploadFile implements Servlet {

    /**
     * Default constructor. 
     */
    public UploadFile() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	Connection con;
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String imageLocation = request.getParameter("photo");
		String name = request.getParameter("name");
		File image = new File(imageLocation);
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc"
					+ ":mysql://localhost:3306/jdbc_demo","root","password");
			System.out.println("Connection Established");
			java.sql.PreparedStatement stmt = con.prepareStatement("insert into fileupload (name,image)" + "values(?,?)");
			stmt.setString(1, name);
			FileInputStream fis = new FileInputStream(image);
			stmt.setBinaryStream(2, (InputStream)fis);
			int count = stmt.executeUpdate();
			PrintWriter out = response.getWriter();
			if(count>0)
			{
			out.println("insert successfully");
			}
			else
			{
			out.println("not successfully");
			}
			
			
		
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Connection Failed");
		}
		
		
		
//		String name = request.getParameter("name");
		
	
	}

}
