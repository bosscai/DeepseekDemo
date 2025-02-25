package org.example.demo.deepseek;

import org.junit.Test;

public enum LLM {


    DEEPSEEK_R1_1_5B("deepseek-r1:1.5b");


    private final String name;

    LLM(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

}
