package org.example.demo.deepseek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;

public class OllamaAPIClient {

    private LLM llm;
    private String mPrompt;

    private static final Logger log = LoggerFactory.getLogger(OllamaAPIClient.class);

    public OllamaAPIClient(LLM llm, String mPrompt) {
        this.llm = llm;
        this.mPrompt = mPrompt;
    }

    public void runTask(OKHttpInstance.RequestCallback callback) {
        if (llm == null) {
            log.error("llm is null");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("model", llm.getName());
        map.put("prompt", mPrompt);
        OKHttpInstance.getInstance().post(Configuration.API_URL, map, callback);
    }
}
