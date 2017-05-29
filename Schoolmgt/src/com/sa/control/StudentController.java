/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.control;

import com.sa.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sun.org.mozilla.javascript.internal.ScriptRuntime;

/**
 *
 * @author User
 */
public class StudentController {
    public static boolean save(Student student){
    boolean saved=false;
    String url="jdbc:mysql://localhost:3306/school";
    String un="root";
    String pw="1234";
    String DriverName="com.mysql.jdbc.Driver";
    
        try {
            Class.forName(DriverName);
            Connection con=DriverManager.getConnection(url, un, pw);
            String sql="INSERT INTO student (name,nic,address,mobile) VALUES (?,?,?,?)";
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,student.getName());
            ps.setString(2,student.getNic());
            ps.setString(3,student.getAddress());
            ps.setInt(4,student.getMobile());
            
            
            ps.executeUpdate();
            saved=true;
            
            
        } catch (Exception e) {
        }
    return saved;
    }
    
    public static List<Student>list(){
    List<Student> list=new ArrayList<Student>();
    //db conn
    
    String url="jdbc:mysql://localhost:3306/school";
    String un="root";
    String pw="1234";
    String DriverName="com.mysql.jdbc.Driver";
    
        try {
            Class.forName(DriverName);
            Connection con=DriverManager.getConnection(url,un,pw);
            
            String sql="SELECT * FROM student";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("name");
            String nic=rs.getString("nic");
            
            Student student=new Student();
            student.setId(id);
            student.setName(name);
            student.setNic(nic);
            
            list.add(student);
            }
            
        }
            
            catch (Exception e) {
                e.printStackTrace();
        }
    return list;
    }
    
    public static Student get(int id){
    Student student=new Student();
        try {
        String url="jdbc:mysql://localhost:3306/school";
        String un="root";
        String pw="1234";
        String driverName="com.mysql.jdbc.Driver";
        Class.forName(driverName);
        Connection con=DriverManager.getConnection(url, un, pw);
        
        String sql="SELECT * FROM student WHERE id='"+ id +"'";
        Statement stmt =con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
        String name=rs.getString("name");
        String nic=rs.getString("nic");
        String address=rs.getString("address");
        int mobile=rs.getInt("mobile");
        
        student.setName(name);
        student.setAddress(address);
        student.setNic(nic);
        student.setMobile(mobile);
        
        }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
    
    public static boolean update(Student student){
        boolean update=false;
        try {
            String url="jdbc:mysql://localhost:3306/school";
        String un="root";
        String pw="1234";
        String driverName="com.mysql.jdbc.Driver";
        Class.forName(driverName);
        Connection con=DriverManager.getConnection(url, un, pw);
        
        String sql="UPDATE student SET name==?,nic==?,address==?,mobile==?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,student.getName());
        ps.setString(2,student.getNic());
        ps.setString(3,student.getAddress());
        ps.setInt(4,student.getMobile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    
       return update;
    }
    
}
