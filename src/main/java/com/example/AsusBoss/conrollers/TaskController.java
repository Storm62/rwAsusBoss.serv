package com.example.AsusBoss.conrollers;

import com.example.AsusBoss.forms.AddTaskForm;
import com.example.AsusBoss.forms.Info;
import com.example.AsusBoss.tasks.Tasker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;


@Controller
public class TaskController {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private Tasker tasker;
    @Autowired
    private Info info;

    @ResponseBody
    @GetMapping ("/task/{number}")
    public String getTask(@PathVariable("number") int number) {
        String t = dateFormat.format(new Date());
        System.out.print(t + "\t" + number + " get:\t" );

        info.setLastTime(number);

        return tasker.getTask(number);
    }
    @ResponseBody
    @GetMapping ("/clear/{number}")
    public void clearTask( @PathVariable("number") int number) {
        String t = dateFormat.format(new Date());
        System.out.println(t + "\t" + number + " clear.\t" );

        tasker.clearTask(number);
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("addtask", new AddTaskForm());
        return "addtask";
    }
    @PostMapping("/add")
    public String addSubmit(@ModelAttribute AddTaskForm addtask, Model model) {
        String t = dateFormat.format(new Date());
        System.out.print(t + "\n");

        model.addAttribute("addtask", addtask);
        tasker.writeTask(addtask.getNum(), addtask.getTime(),addtask.getTelnet(),addtask.getFtpPath(),addtask.getSaveAs(),addtask.getRunProc());
        return "addResult";
    }

    @GetMapping("/info")
    public String info(@ModelAttribute Info info, Model model) {

        model.addAttribute("info", info);
        return "infoPage";
    }


}
