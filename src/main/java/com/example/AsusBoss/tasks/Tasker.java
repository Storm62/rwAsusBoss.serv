package com.example.AsusBoss.tasks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Tasker {
    private File jsonFile = new File("C:/Windows/Temp/tasks.json");


    private HashMap<String, String> taskMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private TypeReference typeReference = new TypeReference<HashMap<String, String>>() {};

    private String taskLine;

    public String getTask (int numb) {
        try {
            FileInputStream fis = new FileInputStream(jsonFile);
            Scanner scan = new Scanner(fis);
            if (scan.hasNextLine()) {
                taskMap.putAll ((HashMap) objectMapper.readValue(scan.nextLine(), typeReference));
                taskLine = taskMap.get(numb + ".time") + "::" + taskMap.get(numb + ".telnet") + "::"
                        + taskMap.get(numb + ".ftpPath") + "::" + taskMap.get(numb + ".saveAs") + "::" + taskMap.get(numb + ".runProc") + "\n\r";
                System.out.println(taskLine);
            }
            fis.close();
        } catch (IOException e) {
            System.err.println("ОШИБКА: проблемы с файлом");
            e.printStackTrace();
        }
        return taskLine;
    }

    public void clearTask (int numb)  {
        try {
            FileWriter fw = new FileWriter(jsonFile);
            taskMap.put(numb + ".time", "null");
            taskMap.put(numb + ".telnet", "null");
            taskMap.put(numb + ".ftpPath", "null");
            taskMap.put(numb + ".saveAs", "null");
            taskMap.put(numb + ".runProc", "null");

            System.out.println("new MAP " + numb + ": " + taskMap);
            fw.write(objectMapper.writeValueAsString(taskMap));
            fw.close();
        } catch (IOException e) {
            System.err.println("ОШИБКА: проблемы с файлом");
            e.printStackTrace();
        }

    }

    public void writeTask(String numbers, String time, String telnet, String ftpPath, String saveAs, String runProc) {
        try {
            FileWriter fw = new FileWriter(jsonFile);
            String[] numbArr = numbers.split(" ");

            for (String numb : numbArr) {
                taskMap.put(numb + ".time", checkValidity(time));
                taskMap.put(numb + ".telnet", checkValidity(telnet));
                taskMap.put(numb + ".ftpPath", checkValidity(ftpPath));
                taskMap.put(numb + ".saveAs", checkValidity(saveAs));
                taskMap.put(numb + ".runProc", checkValidity(runProc));
                System.out.println("\tnew task: " + taskMap);
            }
            fw.write(objectMapper.writeValueAsString(taskMap));
            fw.close();
        } catch (IOException e) {
            System.err.println("ОШИБКА: проблемы с файлом");
            e.printStackTrace();
        }
    }

    private String checkValidity(String line) {
        if (line.length()>0) {
            line = line.replaceAll("\\\\", "/");
        } else line="null";

        return line;
    }
}
