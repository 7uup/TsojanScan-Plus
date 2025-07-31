/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.BurpExtender;
import burp.IContextMenuFactory;
import burp.IContextMenuInvocation;
import burp.IHttpRequestResponse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;

public class Menu
implements IContextMenuFactory {
    BurpExtender burpExtender;

    public Menu(BurpExtender burpExtender) {
        this.burpExtender = burpExtender;
    }

    public List createMenuItems(final IContextMenuInvocation invocation) {
        ArrayList<JMenuItem> list = new ArrayList<JMenuItem>();
        JMenuItem jMenuItem_JPATH = new JMenuItem("1. JPATH Scan");
        JMenuItem jMenuItem_TP_RCE = new JMenuItem("2. Thinkphp RCE Scan");
        JMenuItem jMenuItem_TP_Log = new JMenuItem("3. Thinkphp Log Scan");
        JMenuItem jMenuItem_Weblogic_RCE = new JMenuItem("4. Weblogic RCE Scan");
        JMenuItem jMenuItem_Fastjson_RCE = new JMenuItem("5. Fastjson RCE Scan");
        JMenuItem jMenuItem_Laravel_RCE = new JMenuItem("6. Laravel RCE Scan");
        JMenuItem jMenuItem_SQLI = new JMenuItem("7. SQL Injection Scan");
        JMenuItem jMenuItem_Ueditor = new JMenuItem("8. Ueditor .net Scan");
        list.add(jMenuItem_JPATH);
        list.add(jMenuItem_TP_RCE);
        list.add(jMenuItem_TP_Log);
        list.add(jMenuItem_Weblogic_RCE);
        list.add(jMenuItem_Fastjson_RCE);
        list.add(jMenuItem_Laravel_RCE);
        list.add(jMenuItem_SQLI);
        list.add(jMenuItem_Ueditor);
        jMenuItem_JPATH.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doJPathScan(requestResponseList[0])).start();
            }
        });
        jMenuItem_TP_RCE.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doThinkphpScanRCE(requestResponseList[0])).start();
            }
        });
        jMenuItem_TP_Log.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doThinkphpScanLog(requestResponseList[0])).start();
            }
        });
        jMenuItem_Weblogic_RCE.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doWeblogicScanRCE(requestResponseList[0])).start();
            }
        });
        jMenuItem_Fastjson_RCE.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doFastjsonScan(requestResponseList[0])).start();
            }
        });
        jMenuItem_Laravel_RCE.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doLaravelScan(requestResponseList[0])).start();
            }
        });
        jMenuItem_SQLI.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doSQLIScan(requestResponseList[0])).start();
            }
        });
        jMenuItem_Ueditor.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                IHttpRequestResponse[] requestResponseList = invocation.getSelectedMessages();
                new Thread(() -> Menu.this.burpExtender.doUeditorScan(requestResponseList[0])).start();
            }
        });
        return list;
    }
}

