package com.example.AsusBoss.conrollers;


import com.example.AsusBoss.tasks.AddTaskForm;
import com.example.AsusBoss.tasks.Tasker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;



@Controller
public class GetTaskController {
    private static Tasker tasker = new Tasker();

    @ResponseBody
    @GetMapping ("/task/{number}")
    public String getTask(@PathVariable("number") int number) {
        String t = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        System.out.print(t + "\t" + number + " get:\t" );

        return tasker.getTask(number);
    }
    @ResponseBody
    @GetMapping ("/clear/{number}")
    public String clearTask( @PathVariable("number") int number) {
        String t = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        System.out.println(t + "\t" + number + " clear.\t" );

        tasker.clearTask(number);
        return null;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        String t = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        System.out.println(t + "\t" + " clear.\t" );

        model.addAttribute("addtask", new AddTaskForm());
        return "addtask";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute AddTaskForm addtask, Model model) {
        String t = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
        System.out.println(t + "\t" + addtask.getNum() + " clear.\t" );

        model.addAttribute("addtask", addtask);
        tasker.writeTask(addtask.getNum(), addtask.getTime(),addtask.getTelnet(),addtask.getFtpPath(),addtask.getSaveAs(),addtask.getRunProc());
        return "addResult";
    }



}
