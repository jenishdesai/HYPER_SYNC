package com.HyperSync.hypersync.model;

public class poll {
    String text,option1,option2,option3,option4;

    public poll(){}

    public poll(String text, String option1, String option2, String option3,String option4) {
        this.text = text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public String getText() {
        return text;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() { return option4; }
}
