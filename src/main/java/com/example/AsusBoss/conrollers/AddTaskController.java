//package com.example.AsusBoss.conrollers;
//
//import com.example.AsusBoss.tasks.AddTaskForm;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//
//@Controller
//public class AddTaskController {
//    private File jsonFile = new File("C:/Windows/Temp/tasks.json");
//    private HashMap<String, String> taskMap = new HashMap<>();
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//
//    @GetMapping("/add")
//    public String addForm(Model model) {
//        model.addAttribute("addtask", new AddTaskForm());
//        return "addtask";
//    }
//
//    @PostMapping("/add")
//    public String addSubmit(@ModelAttribute AddTaskForm addtask, Model model) {
//        model.addAttribute("addtask", addtask);
//
//        writeTask(addtask.getNum(), addtask.getTime(),addtask.getTelnet(),addtask.getFtpPath(),addtask.getSaveAs(),addtask.getRunProc());
//        return "addResult";
//    }
//
//    public void writeTask(String numbers, String time, String telnet, String ftpPath, String saveAs, String runProc) {
//        try {
//            FileWriter fw = new FileWriter(jsonFile);
//            String[] numbArr = numbers.split(" ");
//
//            for (String numb : numbArr) {
//                taskMap.put(numb + ".time", checkValidity(time));
//                taskMap.put(numb + ".telnet", checkValidity(telnet));
//                taskMap.put(numb + ".ftp", checkValidity(ftpPath));
//                taskMap.put(numb + ".saveAs", checkValidity(saveAs));
//                taskMap.put(numb + ".runProc", checkValidity(runProc));
//                System.out.println("new task MAP " + numb + ": " + taskMap);
//            }
//            fw.write(objectMapper.writeValueAsString(taskMap));
//            fw.close();
//        } catch (IOException e) {
//            System.err.println("ОШИБКА: проблемы с файлом");
//            e.printStackTrace();
//        }
//    }
//
//    private String checkValidity(String line) {
//        if (line.length()>0) {
//            line = line.replaceAll("\\\\", "/");
//        } else line="null";
//
//        return line;
//    }
//}
