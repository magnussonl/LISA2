/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Endpoint_logeater;

import LISA.Message.KeyPairValue;
import LISA.Message.LISAMessage;
import LISA.ServiceCore.LISAServiceCore;
import LISA.Utils.FileFunctions;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.jms.Connection;
import javax.jms.Message;

/**
 *
 * @author Linus
 */
public class LogeaterService extends LISAServiceCore {

    private static String[] strFile;
    private static List<String[]> strList = new ArrayList<String[]>();

    public LogeaterService(Connection connection, String topicInSub, String topicInPub) {
        super(connection, topicInSub, topicInPub);
    }

    @Override
    public void onMessage(Message message) {

    }

    @Override
    public void onStart() {

        strFile = FileFunctions.readFile("src/test/java/Endpoint_logeater/prodE.txt").split("\n");

        String[] tempStr;

        for (String string : strFile) {

            tempStr = string.split("\t");

            strList.add(tempStr);

        }

    }

    @Override
    public boolean action() {
        if (!strList.isEmpty()) {

            LISAMessage logMsg = new LISAMessage();

            LinkedList<KeyPairValue> datalist = new LinkedList<KeyPairValue>();

            String[] tempStrArr = strList.get(0);

            datalist.add(new KeyPairValue("data1", tempStrArr[0]));
            datalist.add(new KeyPairValue("data2", tempStrArr[1]));
            datalist.add(new KeyPairValue("data3", tempStrArr[2]));
            datalist.add(new KeyPairValue("data4", tempStrArr[3]));
            datalist.add(new KeyPairValue("data5", tempStrArr[4]));
            datalist.add(new KeyPairValue("data6", tempStrArr[5]));
            datalist.add(new KeyPairValue("data7", tempStrArr[6]));
            datalist.add(new KeyPairValue("data8", tempStrArr[7]));
            datalist.add(new KeyPairValue("data9", tempStrArr[8]));
            datalist.add(new KeyPairValue("data10", tempStrArr[9]));

            logMsg.getMessageBody().setKeyPairValues(datalist);
            
            System.out.println(logMsg.toString());

            strList.remove(0);
            return false;
        }

        return true;
    }

    @Override
    public void end() {

    }

}
