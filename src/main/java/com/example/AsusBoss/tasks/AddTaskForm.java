package com.example.AsusBoss.tasks;

public class AddTaskForm {

    private String num;
    private String time;
    private String telnet;
    private String ftpPath;
    private String saveAs;
    private String runProc;

    public String getNum() {
        return num;
    }
    public String getTime() {
        return time;
    }
    public String getTelnet() {
        return telnet;
    }
    public String getFtpPath() {
        return ftpPath;
    }
    public String getSaveAs() {
        return saveAs;
    }
    public String getRunProc() {
        return runProc;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setTime(String time) {
        if (!time.equals("")) {
            this.time = time;
        } else this.time="null";
    }
    public void setTelnet(String telnet) {
        if (!telnet.equals("")) {
            this.telnet = telnet;
        } else this.telnet="null";

    }
    public void setFtpPath(String ftpPath) {
        if (!ftpPath.equals("")) {
            this.ftpPath = ftpPath;
        } else this.ftpPath="null";
    }
    public void setSaveAs(String saveAs) {
        if (!saveAs.equals("")) {
            this.saveAs = saveAs;
        } else this.saveAs="null";
    }
    public void setRunProc(String runProc) {
        if (!runProc.equals("")) {
            this.runProc = runProc;
        } else this.runProc="null";
    }

}
