package com.example.AsusBoss.conrollers;

import com.example.AsusBoss.forms.AddTaskForm;
import com.example.AsusBoss.forms.Info;
import com.example.AsusBoss.service.Tasker;
import com.example.AsusBoss.service.TlgBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class TaskController {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private Tasker tasker;
    private Info info;
    TlgBot bot;

    @Autowired
    public TaskController(Tasker tasker, Info info, TlgBot bot) {
        this.tasker = tasker;
        this.info = info;
        this.bot = bot;
    }

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

    @ResponseBody
    @GetMapping ("/boss/{number}/{stat}/{name}")
    public void bossCheck( @PathVariable("number") int number, @PathVariable("stat") boolean stat, @PathVariable("name") String name) {
        String t = dateFormat.format(new Date());
        System.out.println(t + "\t" + number + " boss checkin.\t" );

        if (stat) {
            bot.sendMsg("335231553", name + " зачекинился в " + number);
            bot.sendMsg("759471608", name + " зачекинился в " + number); // Oleg
            bot.sendMsg("346205847", name + " зачекинился в " + number); // Sergey
        } else {
            bot.sendMsg("335231553", "Кажется " + name + " ушел из " + number);
            bot.sendMsg("759471608", "Кажется " + name + " ушел из " + number); // Oleg
            bot.sendMsg("346205847", "Кажется " + name + " ушел из " + number); // Sergey
        }
    }


}
