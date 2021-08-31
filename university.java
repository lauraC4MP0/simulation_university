/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excercises;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
/**
 *
 * @author lauri
 */
public class university{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        uni unal=new uni("UNAL");
        department ce=new department("Civil Engineering",unal);
        department ch=new department("Chemistry",unal);
        department bi=new department("Biologic",unal);
        unal.set_depart(ch);
        unal.set_depart(bi);
        unal.set_depart(ce);
        unal.set_pro(new professor("Annalise"));
        unal.set_pro(new professor("Sam"));
        unal.set_pro(new professor("Ly"));
        
        boolean flag=true;
        int option;
        String menu;
        
        while(flag){
            menu="University sistem\n"+unal.getName()+"\nInsert an option: \n1.Enroll student.\n2.Assing a tecaher to a departmen.\n3.Create curse.\n4.Assing alumn to curse.\n5.List curse info.\n6.List student's curses.\n7.Exit.\n\nInsert an option, please: ";
            option=Integer.valueOf(JOptionPane.showInputDialog(menu));
            switch(option){
                case 1:
                    String dep="";
                    for(department w:unal.departments){
                        dep+=w.getName();
                        dep+="\n";
                    }
                    String name;
                    String code;
                    name=JOptionPane.showInputDialog("Insert name of the student: ");
                    code=(JOptionPane.showInputDialog("Insert student code: "));
                    student s=new student(name,code);
                    String d_name=JOptionPane.showInputDialog("Insert student department name: \n"+dep);
                    unal.enrroll_e(s,unal.search_depart(d_name));
                    break;
                case 2:
                    name=JOptionPane.showInputDialog("Insert name of the professor: ");
                    String c_name=JOptionPane.showInputDialog("Insert a curse: ");
                    unal.pro_cur(unal.search_pro(name), unal.search_curse(c_name));
                    break;
                case 3:
                    name=JOptionPane.showInputDialog("insert the name of the curse: ");
                    unal.create_curse(name);
                    break;
                case 4:
                    name=JOptionPane.showInputDialog("Insert name of the student: ");
                    c_name=JOptionPane.showInputDialog("Insert the name of the curse: ");
                    unal.stud_curse(unal.search_student(name),unal.search_curse(c_name));
                    break;
                case 5:
                    c_name=JOptionPane.showInputDialog("Insert the name of the curse: ");
                    JOptionPane.showMessageDialog(null,unal.list_curse(unal.search_curse(c_name))); 
                    break;
                case 6:
                    name=JOptionPane.showInputDialog("Insert name of the student: ");
                    JOptionPane.showInternalMessageDialog(null, unal.list_curses(unal.search_student(name)));
                    break;
                case 7:
                    flag=false;
                    JOptionPane.showMessageDialog(null, "Came out successfully.");
                    break;
                    
            }
        }
        JOptionPane.showMessageDialog(null,(unal.search_depart("Civil Engineering")).getName());
    }
    
}

class uni{
    private String name;
    ArrayList<student> students=new ArrayList();
    ArrayList<department>departments=new ArrayList();
    ArrayList<professor>professors=new ArrayList();
    ArrayList<curse>curses=new ArrayList();
    
    public uni(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void set_depart(department department){
        departments.add(department);
    }
    
    public void set_students(student student){
       students.add(student);
    }
    
    public void set_pro(professor professor){
        professors.add(professor);
    }
    
    public void enrroll_e(student student, department department){
        student.setUniversity(this);
        students.add(student);
        student.setDepartment(department);
        department.set_student(student);
    }
    
    public department search_depart(String name){
        department d;
        d = null;
        for (department w: departments) {
            if (w.getName().equalsIgnoreCase(name)) {
                d=w;
                break;
            }
        }return d;
    }
    
    public String list_curse(curse curse){
        String ss="";
        for(student w:curse.students){
            ss+=w.getName();
            ss+="\n";
        }
        return "Name: "+curse.getName()+"\nProfessor: "+curse.getProfessor()+"\nStudent: "+ss;
    }
    
    public String list_curses(student student){
        String ss="";
        for(curse w:student.curses){
            ss+=w.getName();
            ss+="\n";
        }
        return "Name: "+student.getName()+"\nCode: "+student.getCode()+"\nDepartment: "+student.getDepartment()+"\nCurses: "+ss;
    }
    
    public professor search_pro(String name){
        professor p=new professor(name);
        for(professor w:professors){
            if(w.getName().equalsIgnoreCase(name)){
                p=w;
            }
        }
        return p;
    }
    
    public void set_curse(curse curse){
        curses.add(curse);
    }
    
    public void pro_cur(professor professor,curse curse){
        professor.set_curses(curse);
        curse.setProfessor(professor);
    }
    
    public curse search_curse(String name){
        curse c=new curse(name);
        for(curse w:curses){
            if(w.getName().equalsIgnoreCase(name)){
                c=w;
            }
        }return c;
    }
    
    public void create_curse(String name){
        curses.add(new curse(name));
    }
    
    public void stud_curse(student student,curse curse){
        student.set_curses(curse);
        curse.set_students(student);
        
    }
    
    public student search_student(String name){
        student s=new student(name,"");
        for(student w:students){
            if(w.getName().equalsIgnoreCase(name)){
                s=w;
            }
        }return s;
    }
    
}

class department{
    private String name;
    private uni university;
    ArrayList<professor> professors=new ArrayList();
    ArrayList<student> students=new ArrayList();
    
    public department(String name,uni uni){
        this.name=name;
        this.university=uni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public uni getUniversity() {
        return university;
    }

    public void setUniversity(uni university) {
        this.university = university;
    }
    
    public void set_pro(professor professor){
        professors.add(professor);
    }
    
    public void set_student(student student){
        students.add(student);
    }
    
}

class student{
    private String name;
    private String code;
    ArrayList<curse> curses=new ArrayList();
    private uni university;
    private department department;

    public student(String name,String code){
        this.name=name;
        this.code=code;
    }

    public department getDepartment() {
        return department;
    }

    public void setDepartment(department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public uni getUniversity() {
        return university;
    }

    public void setUniversity(uni university) {
        this.university = university;
    }
    
    public void set_curses(curse curse){
        curses.add(curse);
    }
    
    
}
class curse{
    private String name;
    professor professor;
    public ArrayList<student>students=new ArrayList(); 
    
    public curse(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public professor getProfessor() {
        return professor;
    }

    public void setProfessor(professor professor) {
        this.professor = professor;
    }
    
    public void set_students(student student){
        students.add(student);
    }
    
}

class professor{
    private String name;
    ArrayList<curse> curses=new ArrayList();
    public static int i=0;
    
    public professor(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void set_curses(curse curse){
        curses.add(curse);
    }
    
}
