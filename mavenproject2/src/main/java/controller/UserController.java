/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import model.Travel;
import model.Userroles;
import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import service.TravelService;
import service.UserrolesService;



/**
 *
 * @author Anypomonos
 */
@Controller

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired 
    private UserrolesService userrolesService;
    @Autowired
    private TravelService travelService;
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        Userroles us=userrolesService.findByUsername(username);
        if(us!=null){
            model.addAttribute("us",us.getUsers().getUsername());
            model.addAttribute("role",us.getRole());
        }
        return "index";
    }
   
   
    @RequestMapping(value = "/users/user",method = RequestMethod.GET)
    public String listUser(Model model){
        model.addAttribute("users",new Users());
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userDetails",userService.findByUsername(username));
        String usName=SecurityContextHolder.getContext().getAuthentication().getName();
        Users us=userService.findByUsername(usName);
        model.addAttribute("userRole",userrolesService.getUser(us));
        model.addAttribute("userRolee",userrolesService.getAllUsers());
        return "user/add";
    }
    @RequestMapping(value = "/admin/user",method = RequestMethod.GET)
    public String listUserAdmin(Model model){
        model.addAttribute("users",new Users());
        model.addAttribute("userDetails",userService.getAllUsers());
        //String usName=SecurityContextHolder.getContext().getAuthentication().getName();
        //Users us=userService.findByUsername(usName);
        //model.addAttribute("userRole",userrolesService.getUser(us));
        LocalDate time=LocalDate.ofYearDay(2016, 1);//1 yil oncesini listelemek icin buradaki 2016 degistirilerek halledilebilir
        Date dt=Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant());
       // model.addAttribute("dt",dt);
        model.addAttribute("userRolee",userrolesService.getAllUsers());
        model.addAttribute("inf",travelService.getTravels(dt));
        return "admin/add";
    }
    @RequestMapping(value ="/users/add" , method = RequestMethod.POST)
    public String editPerson(@RequestParam("id")int id,@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("recordnum")String recordnum,@RequestParam("email")String email,@RequestParam("phone")String phone,@RequestParam("department")String department,@RequestParam("departmanager")String departmanager,@RequestParam("role")String role){
        
        Users user=userService.findByUsername(username);
        Userroles us=userrolesService.findByUsername(username);
        if(id==0){
                if(user==null) user=new Users();
                if(us==null) us=new Userroles();
                user.setDepartmanager(departmanager);
                user.setDepartment(department);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setRecordnum(recordnum);
                user.setUsername(username);
                
                userService.addUser(user);
                us.setRole(role);
                us.setUsers(user);
                userrolesService.addUser(us);
        }else{
                user.setDepartmanager(departmanager);
                user.setDepartment(department);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setRecordnum(recordnum);
                user.setUsername(username);
                user.setId(id);
                userService.updateUser(user);
                us.setRole(role);
                us.setUsers(user);
                userrolesService.updateUser(us);
            }
        
        return "redirect:/users/user";
    }
    @RequestMapping(value ="/admin/add" , method = RequestMethod.POST)
    public String editPersonAdmin(@RequestParam("id")int id,@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("recordnum")String recordnum,@RequestParam("email")String email,@RequestParam("phone")String phone,@RequestParam("department")String department,@RequestParam("departmanager")String departmanager,@RequestParam("role")String role){
        
        Users user=userService.findByUsername(username);
        Userroles us=userrolesService.findByUsername(username);
        if(id==0){
                if(user==null) user=new Users();
                if(us==null) us=new Userroles();
                user.setDepartmanager(departmanager);
                user.setDepartment(department);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setRecordnum(recordnum);
                user.setUsername(username);
                
                userService.addUser(user);
                us.setRole(role);
                us.setUsers(user);
                userrolesService.addUser(us);
        }else{
                user.setDepartmanager(departmanager);
                user.setDepartment(department);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setRecordnum(recordnum);
                user.setUsername(username);
                user.setId(id);
                userService.updateUser(user);
                us.setRole(role);
                us.setUsers(user);
                userrolesService.updateUser(us);
            }
        
        return "redirect:/admin/user";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) 
    {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     dateFormat.setLenient(false);
     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
   @RequestMapping(value="/users/addtravel",method = RequestMethod.POST)
    public String editTravel(@RequestParam("id")int id,@RequestParam("username")String username,@RequestParam("place")String place,@RequestParam("goal")String goal,@RequestParam("startdate")@DateTimeFormat(pattern = "yyyy.MM.dd")Date startdate,@RequestParam("finishdate")@DateTimeFormat(pattern = "yyyy.MM.dd")Date finishdate,@RequestParam("cost")int cost,@RequestParam("information")String information,@RequestParam("codepr")String codepr){
        Travel travel=travelService.findById(id);
        Users user=userService.findByUsername(username);
        if(user!=null){
            if(id==0){
                if(travel==null) travel=new Travel();
                travel.setCost(cost);
                travel.setFinishdate(finishdate);
                travel.setGoal(goal);
                travel.setInformation(information);
                travel.setPlace(place);
                travel.setStartdate(startdate);
                travel.setUsers(user);
                travel.setCodepr(codepr);
                travelService.addTravel(travel);
            }else{
                travel.setCost(cost);
                travel.setFinishdate(finishdate);
                travel.setGoal(goal);
                travel.setInformation(information);
                travel.setPlace(place);
                travel.setStartdate(startdate);
                travel.setUsers(user);
                travel.setTravelid(id);
                travel.setCodepr(codepr);
                travelService.updateTravel(travel);
            }
        }
        return "redirect:/users/ustravel";
    }
    @RequestMapping(value="/admin/addtravel",method = RequestMethod.POST)
    public String editTravelAdmin(@RequestParam("id")int id,@RequestParam("username")String username,@RequestParam("place")String place,@RequestParam("goal")String goal,@RequestParam("startdate")@DateTimeFormat(pattern = "yyyy.MM.dd")Date startdate,@RequestParam("finishdate")@DateTimeFormat(pattern = "yyyy.MM.dd")Date finishdate,@RequestParam("cost")int cost,@RequestParam("information")String information,@RequestParam("codepr")String codepr){
        Travel travel=travelService.findById(id);
        Users user=userService.findByUsername(username);
        //if(finishdate.compareTo(startdate)<0){//if finishdate small than startdate

        if(user!=null){
            if(id==0){
                if(travel==null) travel=new Travel();
                travel.setCost(cost);
                travel.setFinishdate(finishdate);
                travel.setGoal(goal);
                travel.setInformation(information);
                travel.setPlace(place);
                travel.setStartdate(startdate);
                travel.setUsers(user);
                travel.setCodepr(codepr);
                travelService.addTravel(travel);
            }else{
                travel.setCost(cost);
                travel.setFinishdate(finishdate);
                travel.setGoal(goal);
                travel.setInformation(information);
                travel.setPlace(place);
                travel.setStartdate(startdate);
                travel.setUsers(user);
                travel.setTravelid(id);
                travel.setCodepr(codepr);
                travelService.updateTravel(travel);
            }
        }
        return "redirect:/admin/ustravel";
    }
    
    @RequestMapping("/users/remove/{username}")
    public String removeUser(@PathVariable("username")String username){
        userService.deleteUser(username);
        return "redirect:/users/user";
    }
    @RequestMapping("/admin/remove/{username}")
    public String removeUserAdmin(@PathVariable("username")String username){
        userService.deleteUser(username);
        return "redirect:/admin/user";
    }
    @RequestMapping(value="/users/listbydate",method = RequestMethod.POST)
    public String listTravelBySelectedDate(Model model,@RequestParam("listByDate1")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date1,@RequestParam("listByDate2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date2){
        model.addAttribute("travels",new Travel());
        model.addAttribute("travelDetailss",travelService.getTravels(date1,date2));
        return "user/travel";
    }
     @RequestMapping(value="/admin/listbydate",method = RequestMethod.POST)
    public String listTravelBySelectedDateAdmin(Model model,@RequestParam("listByDate1")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date1,@RequestParam("listByDate2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date2){
        model.addAttribute("travels",new Travel());
        model.addAttribute("travelDetailss",travelService.getTravels(date1,date2));
        return "admin/travel";
    }
    @RequestMapping("/users/edit/{username}")
    public String editUser(@PathVariable("username")String username,Model model){
        model.addAttribute("users",userService.findByUsername(username));
        
        model.addAttribute("userDetails",userService.findByUsername(username));
        
        return "user/add";
    }
    @RequestMapping("/admin/edit/{username}")
    public String editUserAdmin(@PathVariable("username")String username,Model model){
        model.addAttribute("users",userService.findByUsername(username));
        model.addAttribute("userDetails",userService.getAllUsers());
        
        return "admin/add";
    }
    @RequestMapping(value="/users/ustravel",method=RequestMethod.GET)
    public String listTravel(Model model){
        model.addAttribute("travels", new Travel());
        String usName=SecurityContextHolder.getContext().getAuthentication().getName();
        Users us=userService.findByUsername(usName);
        model.addAttribute("travelDetails",travelService.getTravel(usName));//user by name get travels
        LocalDate time=LocalDate.ofYearDay(2016, 1);
        Date dt=Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant());
        model.addAttribute("dt",dt);
        model.addAttribute("travelDetailss",travelService.getTravels(dt));//list by date
        model.addAttribute("selectUsers",userService.getAllUsers());
        model.addAttribute("selectedUser",us);
        return "user/travel";
    }
    @RequestMapping(value="/admin/ustravel",method=RequestMethod.GET)
    public String listTravelAdmin(Model model){
        model.addAttribute("travels", new Travel());
        String usName=SecurityContextHolder.getContext().getAuthentication().getName();
        Users us=userService.findByUsername(usName);
        List<Users> selectUsers=userService.getAllUsers();
        LocalDate time=LocalDate.ofYearDay(2016, 1);
        Date dt=Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant());
        model.addAttribute("dt",dt);
        model.addAttribute("travelDetailss",travelService.getTravels(dt));//list by date
        model.addAttribute("selectUsers",selectUsers);
       
        return "admin/travel";
    }
    @RequestMapping("/users/removetravel/{travelid}")
    public String removeTravel(@PathVariable("travelid")int id){
        travelService.deleteTravel(id);
        return "redirect:/users/ustravel";
    }
    @RequestMapping("/admin/removetravel/{travelid}")
    public String removeTravelAdmin(@PathVariable("travelid")int id){
        travelService.deleteTravel(id);
        return "redirect:/admin/ustravel";
    }
    @RequestMapping("/users/edittravel/{travelid}")
    public String editTravel(@PathVariable("travelid")int id,Model model){
        model.addAttribute("travels",travelService.findById(id));
        model.addAttribute("travelDetails",travelService.getTravels());
        //Travel tr=travelService.findById(id);
        //String username=tr.getUsers().getUsername();
        //model.addAttribute("selectUsers","");//userService.findByUsername(username)
        return "user/travel";
    }
    @RequestMapping("/admin/edittravel/{travelid}")
    public String editTravelAdmin(@PathVariable("travelid")int id,Model model){
        model.addAttribute("travels",travelService.findById(id));
        model.addAttribute("travelDetails",travelService.getTravels());
        //Travel tr=travelService.findById(id);
        //String username=tr.getUsers().getUsername();
        //model.addAttribute("selectUsers","");//userService.findByUsername(username)
        return "admin/travel";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)  
    public String login(ModelMap model) {  

     return "login";  

    }  
    @RequestMapping(value="/accessDenied", method = RequestMethod.GET)  
    public String accessDenied(ModelMap model) {  

     return "accessDenied";  

    }  
    @RequestMapping(value="/loginError", method = RequestMethod.GET)  
    public String loginError(ModelMap model) {  
     model.addAttribute("error", "true");  
     return "login";  

    }  


 
}
    
    

