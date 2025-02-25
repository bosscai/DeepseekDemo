package org.example.demo.deepseek;

public class Configuration {

    public static final String API_URL = "http://localhost:11434/api/generate";

    private LLM model;

    private String prompt;

    private Configuration(Builder builder) {
        this.model = builder.mLLM;
        this.prompt = builder.prompt;
    }

    public LLM getModel() {
        return model;
    }

    public void setModel(LLM model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String convert() {
        return "{" +
                "model:" + model.getName() +
                ", prompt:'" + prompt + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "model=" + model +
                ", prompt='" + prompt + '\'' +
                '}';
    }

    // 静态内部类，作为建造者
    public static class Builder {
        private LLM mLLM;
        private String prompt;

        public Builder setLLM(LLM mLLM) {
            this.mLLM = mLLM;
            return this;
        }

        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        // 构建电脑对象的方法
        public Configuration build() {
            return new Configuration(this);
        }


        @Override
        public String toString() {
            return "Builder{" +
                    "mLLM=" + mLLM +
                    ", prompt='" + prompt + '\'' +
                    '}';
        }
    }
}
