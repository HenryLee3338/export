package cn.henry.mail;

import cn.henry.common.utils.MailUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.Map;

public class MyListener implements MessageListener {

    public void onMessage(Message message) {

        System.out.println(message.toString());

        String msg = new String(message.getBody());
        Map map = JSONObject.parseObject(msg, Map.class);

        String email = (String) map.get("to");
        String subject = (String) map.get("subject");
        String content = (String) map.get("content");
        try {
            MailUtil.sendMsg(email, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
