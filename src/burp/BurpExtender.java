/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;


import burp.Listen.Ceye;
import burp.Listen.IBackend;
import burp.Listen.XyzDnsLog;
import burp.ScanFun.*;
import burp.utils.Config;
import burp.utils.Utils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BurpExtender
extends AbstractTableModel
implements IBurpExtender,
IScannerCheck,
ITab,
IMessageEditorController, IProxyListener {
    public static IBackend dnslog;
    public static PrintWriter stdout;
    static PrintWriter stderr;
    static IBurpExtenderCallbacks callbacks;
    private static IExtensionHelpers helpers;
    private String ExtenderName = "TsojanScan";
    List<IScanIssue> issueALL = new ArrayList<IScanIssue>();
    private List Udatas = new ArrayList();
    private List ulists = new ArrayList();
    private IMessageEditor HRequestTextEditor;
    private IMessageEditor HResponseTextEditor;
    private IHttpRequestResponse currentlyDisplayedItem;
    private URLTable Utable;
    private JScrollPane UscrollPane;
    private JSplitPane HjSplitPane;
    private JTabbedPane Ltable;
    private JTabbedPane Rtable;
    private JSplitPane vulQueuePane;
    private JTabbedPane mainPane;
    private static JPanel configPane;
    private static JPanel customPane;
    private static JPanel dnslogPane;
    private static JCheckBox enabled_scan;
    private static JCheckBox enabled_axis;
    private static JCheckBox enabled_nacos;
    private static JCheckBox enabled_log4j;
    private static JCheckBox enabled_text4shell;
    private static JCheckBox enabled_fastjson;
    private static JCheckBox enabled_shiro;
    private static JCheckBox enabled_springcloud;
    private static JCheckBox enabled_sqli;
    private static JCheckBox enabled_weblogic;
    private static JCheckBox enabled_springcross;
    private static JCheckBox enabled_ueditor;
    private static JCheckBox enabled_thinkphp;
    private static JCheckBox enabled_laravel;
    private static JCheckBox enabled_Jboss;
    private static JCheckBox enabled_BypassCheck;
    private static JCheckBox enable_Oss_listObject_Check;
    private static JCheckBox enabled_springenv;
    private static JCheckBox enabled_domain_blacklist;
    private static JCheckBox enabled_sleep;
    private static JTextArea domain_blacklist;
    private static JTextField domain_blacklist_add;
    private static JTextField ceyeIdentifierField;
    private static JTextField ceyeTokenField;
    public static JTextField sleep_value;
    private static JComboBox<String> dnslog_Selector;
    public static HashMap<String, List<String>> scannedDomainURL_env;
    public static HashMap<String, List<String>> scannedDomainURL_swagger;
    public static HashMap<String, List<String>> scannedDomainURL_druid;
    public static HashMap<String, List<String>> scannedDomainURL_envcross;
    public static HashMap<String, List<String>> scannedDomainURL_gateway;
    public static HashMap<String, List<String>> scannedDomainURL_Bypass;
    public static HashMap<String, List<String>> scannedDomainURL_Oss;

    public static HashMap<String, List<String>> scannedDomainURL_spel;
    public static HashMap<String, List<String>> scannedDomainURL_log4j;
    public static HashMap<String, List<String>> scannedDomainURL_text4shell;
    public static HashMap<String, List<String>> scannedDomainURL_fastjson;
    public static HashMap<String, List<String>> scannedDomainURL_sqli;
    public static List<String> scannedDomainURL_thinkphp_rce;
    public static List<String> scannedDomainURL_thinkphp_log;
    public static List<String> scannedDomainURL_weblogic_rce;
    public static List<String> scannedDomainURL_axis;
    public static List<String> scannedDomainURL_nacos;
    public static List<String> scannedDomainURL_laravel_debugrce;
    public static List<String> scannedDomainURL_laravel_env;
    public static List<String> scannedDomainURL_Ueditor_dotnet_rce;
    public static List<String> scannedDomainURL_Jboss_rce;
    private static String[] BlackListDomain_org;
    private String[] BlackFileExt = new String[]{".css", ".js", ".png", ".jpg", ".gif", ".jpeg", ".svg", ".woff", ".woff2", ".ttf", ".ico", ".iso", ".xlsx", ".docs", ".doc", ".xls", ".ios", ".apk", ".mp3", ".mp4", ".swf", "otf"};
    public ArrayList<String> Hostlist = new ArrayList();
    private IScannerCheck scanner;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);
        BurpExtender.callbacks = callbacks;
        Utils.Callback = BurpExtender.callbacks = callbacks;
        helpers = callbacks.getHelpers();
//        System.out.println(111111);
        stdout.println("=========================================\n[+]   TsojanScan_Plus Plugin Load Successful!!!!!!!!!!!!!!!\n=========================================");
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                BurpExtender.this.mainPane = new JTabbedPane();
                BurpExtender.this.vulQueuePane = new JSplitPane(0);
                BurpExtender burpExtender = BurpExtender.this;
                burpExtender.getClass();
                BurpExtender.this.Utable = burpExtender.new URLTable(BurpExtender.this);
                BurpExtender.this.UscrollPane = new JScrollPane(BurpExtender.this.Utable);
                BurpExtender.this.HjSplitPane = new JSplitPane();
                BurpExtender.this.HjSplitPane.setResizeWeight(0.5);
                BurpExtender.this.Ltable = new JTabbedPane();
                BurpExtender.this.HRequestTextEditor = callbacks.createMessageEditor(BurpExtender.this, false);
                BurpExtender.this.Ltable.addTab("Request", BurpExtender.this.HRequestTextEditor.getComponent());
                BurpExtender.this.Rtable = new JTabbedPane();
                BurpExtender.this.HResponseTextEditor = callbacks.createMessageEditor(BurpExtender.this, false);
                BurpExtender.this.Rtable.addTab("Response", BurpExtender.this.HResponseTextEditor.getComponent());
                BurpExtender.this.HjSplitPane.add((Component)BurpExtender.this.Ltable, "left");
                BurpExtender.this.HjSplitPane.add((Component)BurpExtender.this.Rtable, "right");
                BurpExtender.this.vulQueuePane.add((Component)BurpExtender.this.UscrollPane, "left");
                BurpExtender.this.vulQueuePane.add((Component)BurpExtender.this.HjSplitPane, "right");
                BurpExtender burpExtender7 = BurpExtender.this;
                JPanel unused = BurpExtender.configPane = BurpExtender.getFuzzSettingPanel();
                BurpExtender burpExtender8 = BurpExtender.this;
                JPanel unused2 = BurpExtender.customPane = BurpExtender.customPanel();
                BurpExtender burpExtender9 = BurpExtender.this;
                dnslogPane = BurpExtender.dnslogPanel();
                BurpExtender.this.mainPane.addTab("VulPanel", BurpExtender.this.vulQueuePane);
                BurpExtender.this.mainPane.addTab("ConfigPanel", configPane);
                BurpExtender.this.mainPane.addTab("CustomPanel", customPane);
                BurpExtender.this.mainPane.addTab("DnslogPanel", dnslogPane);
                BurpExtender.loadConfig();
                BurpExtender.this.loadCustom();
                BurpExtender.loadDnslog();
                callbacks.customizeUiComponent(BurpExtender.this.mainPane);
                callbacks.addSuiteTab(BurpExtender.this);
            }
        });
        callbacks.setExtensionName(this.ExtenderName);
        callbacks.registerContextMenuFactory(new Menu(this));
        callbacks.registerScannerCheck(this);
    }

    public static JPanel GetXJPanel() {
        JPanel panel1 = new JPanel();
        panel1.setAlignmentX(0.0f);
        panel1.setLayout(new BoxLayout(panel1, 0));
        panel1.setForeground(new Color(249, 130, 11));
        panel1.setBorder(new EmptyBorder(5, 0, 5, 0));
        return panel1;
    }

    public static void loadConfig() {
        enabled_scan.setSelected(Config.getBoolean("enabled_scan", true));
        enabled_axis.setSelected(Config.getBoolean("enabled_axis", true));
        enabled_nacos.setSelected(Config.getBoolean("enabled_nacos", true));
        enabled_log4j.setSelected(Config.getBoolean("enabled_log4j", true));
        enabled_text4shell.setSelected(Config.getBoolean("enabled_text4shell", true));
        enabled_fastjson.setSelected(Config.getBoolean("enabled_fastjson", true));
        enabled_shiro.setSelected(Config.getBoolean("enabled_shiro", true));
        enabled_springcloud.setSelected(Config.getBoolean("enabled_springcloud", true));
        enabled_weblogic.setSelected(Config.getBoolean("enabled_weblogic", true));
        enabled_ueditor.setSelected(Config.getBoolean("enabled_ueditor", true));
        enabled_sqli.setSelected(Config.getBoolean("enabled_sqli", true));
        enabled_springcross.setSelected(Config.getBoolean("enabled_springcross", true));
        enabled_thinkphp.setSelected(Config.getBoolean("enabled_thinkphp", true));
        enabled_laravel.setSelected(Config.getBoolean("enabled_laravel", true));
        enabled_springenv.setSelected(Config.getBoolean("enabled_springenv", true));
        enabled_Jboss.setSelected(Config.getBoolean("enabled_Jboss", true));
        enabled_BypassCheck.setSelected(Config.getBoolean("enabled_BypassCheck", true));
        enabled_BypassCheck.setSelected(Config.getBoolean("enabled_Oss_listObject_Check", true));
        domain_blacklist.setText(Config.get("domain_blacklist", ""));
    }

    public static void saveConfig() {
        Config.setBoolean("enabled_scan", enabled_scan.isSelected());
        Config.setBoolean("enabled_axis", enabled_axis.isSelected());
        Config.setBoolean("enabled_nacos", enabled_nacos.isSelected());
        Config.setBoolean("enabled_log4j", enabled_log4j.isSelected());
        Config.setBoolean("enabled_text4shell", enabled_text4shell.isSelected());
        Config.setBoolean("enabled_fastjson", enabled_fastjson.isSelected());
        Config.setBoolean("enabled_shiro", enabled_shiro.isSelected());
        Config.setBoolean("enabled_springcloud", enabled_springcloud.isSelected());
        Config.setBoolean("enabled_weblogic", enabled_weblogic.isSelected());
        Config.setBoolean("enabled_sqli", enabled_sqli.isSelected());
        Config.setBoolean("enabled_ueditor", enabled_ueditor.isSelected());
        Config.setBoolean("enabled_springcross", enabled_springcross.isSelected());
        Config.setBoolean("enabled_thinkphp", enabled_thinkphp.isSelected());
        Config.setBoolean("enabled_laravel", enabled_laravel.isSelected());
        Config.setBoolean("enabled_springenv", enabled_springenv.isSelected());
        Config.setBoolean("enabled_Jboss", enabled_Jboss.isSelected());
        Config.setBoolean("enabled_BypassCheck", enabled_BypassCheck.isSelected());
        Config.setBoolean("enabled_Oss_listObject_Check", enabled_BypassCheck.isSelected());
        JOptionPane.showMessageDialog(configPane, "Apply success!");
    }

    public static void addDomain() {
        String blackDomain = domain_blacklist_add.getText();
        if (!blackDomain.trim().equals("")) {
            String[] list = domain_blacklist.getText().split("\n");
            if (!BurpExtender.contains(list, blackDomain)) {
                Config.set("domain_blacklist", blackDomain + "\n" + domain_blacklist.getText());
                domain_blacklist.setText(domain_blacklist.getText() + "\n" + blackDomain);
            }
            domain_blacklist_add.setText("");
        }
    }

    public static void delDomain() {
        String blackDomain = domain_blacklist_add.getText();
        String[] list = domain_blacklist.getText().split("\n");
        if (BurpExtender.contains(list, blackDomain)) {
            ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(list));
            list1.remove(blackDomain);
            list = list1.toArray(new String[list1.size()]);
            String tmp = "";
            for (int i = 0; i < list.length; ++i) {
                tmp = tmp + "\n" + list[i];
            }
            tmp = tmp.substring(1, tmp.length());
            Config.set("domain_blacklist", tmp);
            domain_blacklist.setText(tmp);
            domain_blacklist_add.setText("");
        }
    }

    public static void saveCustom() {
        Config.setBoolean("enabled_sleep", enabled_sleep.isSelected());
        Config.set("enabled_sleep_value", sleep_value.getText());
        Config.setBoolean("enabled_domain_blacklist", enabled_domain_blacklist.isSelected());
        Config.set("domain_blacklist", domain_blacklist.getText());
        JOptionPane.showMessageDialog(configPane, "Apply success!");
    }

    public static void resetCustom() {
        enabled_sleep.setSelected(Config.getBoolean("enabled_sleep", true));
        sleep_value.setText("300");
        String blackListDomain_org_str = "";
        for (int i = 0; i < BlackListDomain_org.length; ++i) {
            blackListDomain_org_str = BlackListDomain_org[i] + "\n" + blackListDomain_org_str;
        }
        blackListDomain_org_str = blackListDomain_org_str.substring(0, blackListDomain_org_str.length() - 1);
        enabled_domain_blacklist.setSelected(Config.getBoolean("enabled_domain_blacklist", true));
        domain_blacklist.setText(blackListDomain_org_str);
        Config.setBoolean("enabled_sleep", enabled_sleep.isSelected());
        Config.setBoolean("enabled_domain_blacklist", enabled_domain_blacklist.isSelected());
        Config.set("enabled_sleep_value", sleep_value.getText());
        Config.set("domain_blacklist", domain_blacklist.getText());
        JOptionPane.showMessageDialog(configPane, "Reset success!");
    }

    public void loadCustom() {
        enabled_sleep.setSelected(Config.getBoolean("enabled_sleep", true));
        sleep_value.setText(Config.get("enabled_sleep_value", "500"));
        String blackListDomain_org_str = "";
        int i = 0;
        while (true) {
            if (i >= BlackListDomain_org.length) break;
            blackListDomain_org_str = BlackListDomain_org[i] + "\n" + blackListDomain_org_str;
            ++i;
        }
        blackListDomain_org_str = blackListDomain_org_str.substring(0, blackListDomain_org_str.length() - 1);
        enabled_domain_blacklist.setSelected(Config.getBoolean("enabled_domain_blacklist", true));
        if (!Config.get("domain_blacklist", "").equals("")) {
            domain_blacklist.setText(Config.get("domain_blacklist", ""));
        } else {
            domain_blacklist.setText(Config.get("domain_blacklist", "") + blackListDomain_org_str);
        }
    }

    public static void loadDnslog() {
        if (Config.get("dnslog_choose") == null) {
            dnslog = new Ceye();
            dnslog_Selector.setSelectedItem("Ceye");
            if (Config.get("ceye_identifier") != null) {
                ceyeIdentifierField.setText(Config.get("ceye_identifier"));
            }
            if (Config.get("ceye_token") != null) {
                ceyeTokenField.setText(Config.get("ceye_token"));
            }
        } else {
            switch (Config.get("dnslog_choose")) {
                case "Ceye": {
                    dnslog = new Ceye();
                    break;
                }
                case "XyzDnsLog": {
                    dnslog = new XyzDnsLog();
                }
            }
            dnslog_Selector.setSelectedItem(Config.get("dnslog_choose"));
            if (Config.get("ceye_identifier") != null) {
                ceyeIdentifierField.setText(Config.get("ceye_identifier"));
            }
            if (Config.get("ceye_token") != null) {
                ceyeTokenField.setText(Config.get("ceye_token"));
            }
        }
    }

    public static void saveDnslog() {
        Config.set("dnslog_choose", dnslog_Selector.getSelectedItem().toString());
        switch (Config.get("dnslog_choose")) {
            case "Ceye": {
                dnslog = new Ceye();
                break;
            }
            case "XyzDnsLog": {
                dnslog = new XyzDnsLog();
            }
        }
        if (ceyeIdentifierField.getText() != null && !ceyeIdentifierField.getText().equals("")) {
            Config.set("ceye_identifier", ceyeIdentifierField.getText());
        }
        if (ceyeTokenField.getText() != null && !ceyeTokenField.getText().equals("")) {
            Config.set("ceye_token", ceyeTokenField.getText());
        }
        JOptionPane.showMessageDialog(dnslogPane, "Apply success!");
    }

    private static JPanel dnslogPanel() {
        JPanel dnslogMainPanel = new JPanel();
        dnslogMainPanel.setAlignmentX(0.0f);
        dnslogMainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        dnslogMainPanel.setLayout(new BoxLayout(dnslogMainPanel, 1));
        JPanel blankPanel = BurpExtender.GetXJPanel();
        JPanel subPanel_selector = BurpExtender.GetXJPanel();
        enabled_sleep = new JCheckBox();
        subPanel_selector.add(new JLabel("Plz select dnslog: "));
        dnslog_Selector = new JComboBox<String>(BurpExtender.GetBackends());
        dnslog_Selector.setMaximumSize(dnslog_Selector.getPreferredSize());
        dnslog_Selector.setSelectedIndex(0);
        subPanel_selector.add(dnslog_Selector);
        JPanel subPanel_ceye = new JPanel();
        JPanel ceyeIdentifierPanel = BurpExtender.GetXJPanel();
        ceyeIdentifierPanel.add(new JLabel("Ceye Identifier : "));
        ceyeIdentifierField = new JTextField(30);
        ceyeIdentifierField.setMaximumSize(ceyeIdentifierField.getPreferredSize());
        ceyeIdentifierPanel.add(ceyeIdentifierField);
        JPanel ceyeTokenPanel = BurpExtender.GetXJPanel();
        ceyeTokenPanel.add(new JLabel("Ceye Token : "));
        ceyeTokenField = new JTextField(30);
        ceyeTokenField.setMaximumSize(ceyeTokenField.getPreferredSize());
        ceyeTokenPanel.add(ceyeTokenField);
        subPanel_ceye.add(ceyeIdentifierPanel);
        subPanel_ceye.add(blankPanel);
        subPanel_ceye.add(ceyeTokenPanel);
        JPanel panelTmp = BurpExtender.GetXJPanel();
        panelTmp.setLayout(new FlowLayout(0));
        JTabbedPane backendsPanel = new JTabbedPane();
        backendsPanel.addTab("Ceye", subPanel_ceye);
        panelTmp.add(backendsPanel);
        JPanel subPanel_but = BurpExtender.GetXJPanel();
        JButton applyBtn = new JButton("Apply");
        applyBtn.setMaximumSize(applyBtn.getPreferredSize());
        applyBtn.addActionListener(e -> BurpExtender.saveDnslog());
        subPanel_but.add(applyBtn);
        dnslogMainPanel.add(subPanel_but);
        dnslogMainPanel.add(subPanel_selector);
        dnslogMainPanel.add(panelTmp);
        return dnslogMainPanel;
    }

    private static JPanel customPanel() {
        JPanel customMainPanel = new JPanel();
        customMainPanel.setAlignmentX(0.0f);
        customMainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        customMainPanel.setLayout(new BoxLayout(customMainPanel, 1));
        JPanel blankPanel = BurpExtender.GetXJPanel();
        JPanel subPanel_sleep = BurpExtender.GetXJPanel();
        enabled_sleep = new JCheckBox();
        subPanel_sleep.add(new JLabel("Enable Sleep: "));
        subPanel_sleep.add(enabled_sleep);
        JPanel subPanel_sleepValue = BurpExtender.GetXJPanel();
        subPanel_sleepValue.add(new JLabel("Set Sleep Value (ms) : "));
        sleep_value = new JTextField(10);
        sleep_value.setMaximumSize(sleep_value.getPreferredSize());
        subPanel_sleepValue.add(sleep_value);
        JPanel subPanel_domainBlackList = BurpExtender.GetXJPanel();
        enabled_domain_blacklist = new JCheckBox();
        subPanel_domainBlackList.add(new JLabel("Enable Domain BlackList: "));
        subPanel_domainBlackList.add(enabled_domain_blacklist);
        JPanel subPanel_domainAdd = BurpExtender.GetXJPanel();
        domain_blacklist_add = new JTextField(30);
        domain_blacklist_add.setMaximumSize(domain_blacklist_add.getPreferredSize());
        JLabel blankLabel1 = new JLabel("\t");
        JLabel blankLabel2 = new JLabel("\t");
        JLabel blankLabel3 = new JLabel("\t");
        JButton addBtn = new JButton("Add");
        addBtn.setMaximumSize(addBtn.getPreferredSize());
        addBtn.addActionListener(e -> BurpExtender.addDomain());
        JButton delBtn = new JButton("Del");
        delBtn.setMaximumSize(delBtn.getPreferredSize());
        delBtn.addActionListener(e -> BurpExtender.delDomain());
        subPanel_domainAdd.add(domain_blacklist_add);
        subPanel_domainAdd.add(blankLabel1);
        subPanel_domainAdd.add(addBtn);
        subPanel_domainAdd.add(blankLabel2);
        subPanel_domainAdd.add(delBtn);
        JPanel subPanel_domainBlackList1 = BurpExtender.GetXJPanel();
        domain_blacklist = new JTextArea();
        domain_blacklist.setMaximumSize(new Dimension(500, 500));
        domain_blacklist.setEditable(false);
        domain_blacklist.setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(domain_blacklist);
        jScrollPane.setVerticalScrollBarPolicy(20);
        jScrollPane.setMaximumSize(new Dimension(500, 500));
        subPanel_domainBlackList1.add(jScrollPane);
        JPanel subPanel_but = BurpExtender.GetXJPanel();
        JButton applyBtn = new JButton("Apply");
        applyBtn.setMaximumSize(applyBtn.getPreferredSize());
        applyBtn.addActionListener(e -> BurpExtender.saveCustom());
        JButton resetBtn = new JButton("Reset");
        resetBtn.setMaximumSize(resetBtn.getPreferredSize());
        resetBtn.addActionListener(e -> BurpExtender.resetCustom());
        subPanel_but.add(applyBtn);
        subPanel_but.add(blankLabel3);
        subPanel_but.add(resetBtn);
        customMainPanel.add(subPanel_but);
        customMainPanel.add(blankPanel);
        customMainPanel.add(subPanel_sleep);
        customMainPanel.add(subPanel_sleepValue);
        customMainPanel.add(subPanel_domainBlackList);
        customMainPanel.add(subPanel_domainAdd);
        customMainPanel.add(subPanel_domainBlackList1);
        return customMainPanel;
    }

    private static JPanel getFuzzSettingPanel() {
        JPanel configMainPanel = new JPanel();
        configMainPanel.setAlignmentX(0.0f);
        configMainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        configMainPanel.setLayout(new BoxLayout(configMainPanel, 1));
        JPanel blankPanel = BurpExtender.GetXJPanel();
        JPanel subPanel_activate = BurpExtender.GetXJPanel();
        enabled_scan = new JCheckBox();
        subPanel_activate.add(new JLabel("\u6fc0\u6d3b\u63d2\u4ef6: "));
        subPanel_activate.add(enabled_scan);
        JPanel subPanel_axis = BurpExtender.GetXJPanel();
        enabled_axis = new JCheckBox();
        subPanel_axis.add(new JLabel("Enable Axis Scan: "));
        subPanel_axis.add(enabled_axis);
        JPanel subPanel_nacos = BurpExtender.GetXJPanel();
        enabled_nacos = new JCheckBox();
        subPanel_nacos.add(new JLabel("Enable Nacos Scan: "));
        subPanel_nacos.add(enabled_nacos);
        JPanel subPanel_log4j = BurpExtender.GetXJPanel();
        enabled_log4j = new JCheckBox();
        subPanel_log4j.add(new JLabel("Enable Log4j Scan: "));
        subPanel_log4j.add(enabled_log4j);
        JPanel subPanel_fastjson = BurpExtender.GetXJPanel();
        enabled_fastjson = new JCheckBox();
        subPanel_fastjson.add(new JLabel("Enable Fastjson Scan: "));
        subPanel_fastjson.add(enabled_fastjson);
        JPanel subPanel_text4shell = BurpExtender.GetXJPanel();
        enabled_text4shell = new JCheckBox();
        subPanel_text4shell.add(new JLabel("Enable Text4shell Scan: "));
        subPanel_text4shell.add(enabled_text4shell);
        JPanel subPanel_shiro = BurpExtender.GetXJPanel();
        enabled_shiro = new JCheckBox();
        subPanel_shiro.add(new JLabel("Enable Shiro Scan: "));
        subPanel_shiro.add(enabled_shiro);
        JPanel subPanel_springCloud = BurpExtender.GetXJPanel();
        enabled_springcloud = new JCheckBox();
        subPanel_springCloud.add(new JLabel("Enable SpringCloud Scan: "));
        subPanel_springCloud.add(enabled_springcloud);
        JPanel subPanel_sqli = BurpExtender.GetXJPanel();
        enabled_sqli = new JCheckBox();
        subPanel_sqli.add(new JLabel("Enable SQLI Scan: "));
        subPanel_sqli.add(enabled_sqli);
        JPanel subPanel_weblogic = BurpExtender.GetXJPanel();
        enabled_weblogic = new JCheckBox();
        subPanel_weblogic.add(new JLabel("Enable Weblogic Scan: "));
        subPanel_weblogic.add(enabled_weblogic);
        JPanel subPanel_crossEnv = BurpExtender.GetXJPanel();
        enabled_springcross = new JCheckBox();
        subPanel_crossEnv.add(new JLabel("Enable SpringBoot Cross: "));
        subPanel_crossEnv.add(enabled_springcross);
        JPanel subPanel_thinkPHP = BurpExtender.GetXJPanel();
        enabled_thinkphp = new JCheckBox();
        subPanel_thinkPHP.add(new JLabel("Enable ThinkPHP Scan: "));
        subPanel_thinkPHP.add(enabled_thinkphp);
        JPanel subPanel_laravel = BurpExtender.GetXJPanel();
        enabled_laravel = new JCheckBox();
        subPanel_laravel.add(new JLabel("Enable Laravel Scan: "));
        subPanel_laravel.add(enabled_laravel);
        JPanel subPanel_env = BurpExtender.GetXJPanel();
        enabled_springenv = new JCheckBox();
        subPanel_env.add(new JLabel("Enable SpringBoot Env/Swagger/Druid: "));
        subPanel_env.add(enabled_springenv);
        JPanel subPanel_ueditor = BurpExtender.GetXJPanel();
        enabled_ueditor = new JCheckBox();
        subPanel_ueditor.add(new JLabel("Enable Ueditor .net Scan: "));
        subPanel_ueditor.add(enabled_ueditor);

        JPanel Jboss_vul = BurpExtender.GetXJPanel();
        enabled_Jboss = new JCheckBox();
        Jboss_vul.add(new JLabel("Enable Jboss_vul Scan: "));
        Jboss_vul.add(enabled_Jboss);

        JPanel bypass_check = BurpExtender.GetXJPanel();
        enabled_BypassCheck = new JCheckBox();
        bypass_check.add(new JLabel("Enable bypass_check Scan: "));
        bypass_check.add(enabled_BypassCheck);


        JPanel oss_listobject_check = BurpExtender.GetXJPanel();
        enable_Oss_listObject_Check = new JCheckBox();
        oss_listobject_check.add(new JLabel("Enable Oss_listObject_Check Scan: "));
        oss_listobject_check.add(enable_Oss_listObject_Check);




        JButton applyBtn = new JButton("Apply");
        applyBtn.setMaximumSize(applyBtn.getPreferredSize());
        applyBtn.addActionListener(e -> BurpExtender.saveConfig());
        configMainPanel.add(applyBtn);
        configMainPanel.add(blankPanel);
        configMainPanel.add(subPanel_activate);
        configMainPanel.add(subPanel_axis);
        configMainPanel.add(subPanel_nacos);
        configMainPanel.add(subPanel_env);
        configMainPanel.add(subPanel_crossEnv);
        configMainPanel.add(subPanel_log4j);
        configMainPanel.add(subPanel_text4shell);
        configMainPanel.add(subPanel_shiro);
        configMainPanel.add(subPanel_springCloud);
        configMainPanel.add(subPanel_fastjson);
        configMainPanel.add(subPanel_weblogic);
        configMainPanel.add(subPanel_thinkPHP);
        configMainPanel.add(subPanel_laravel);
        configMainPanel.add(subPanel_ueditor);
        configMainPanel.add(subPanel_sqli);
        configMainPanel.add(Jboss_vul);
        configMainPanel.add(bypass_check);
        configMainPanel.add(oss_listobject_check);
        return configMainPanel;
    }


    public void doJbossScan(IHttpRequestResponse baseRequestResponse){
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        try {
            IHttpRequestResponse Jboss_finger;
            url = helpers.analyzeRequest(baseRequestResponse).getUrl();
            if (this.IsneedScan(baseRequestResponse, "Jboss Found") && !scannedDomainURL_Jboss_rce.contains(url.getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (Jboss_finger = JbossScan.Jboss_Console(baseRequestResponse, callbacks, helpers)) != null && Jboss_finger.getResponse() != null && this.IsneedAddIssuse(Jboss_finger, "Jboss Found") && Config.getBoolean("enabled_scan", true)){
                IHttpRequestResponse Jboss_CVE_2017_12149_Scan;
                IHttpRequestResponse Jboss_CVE_2017_7504_Scan;
                issues = this.Addissuse(Jboss_finger, "Jboss Found!!!", issues);
                if (this.IsneedScan(baseRequestResponse, "Jboss_CVE_2017_12149_Scan") && Config.getBoolean("enabled_scan", true) && (Jboss_CVE_2017_12149_Scan = JbossScan.Jboss_CVE_2017_12149_Scan(baseRequestResponse, callbacks, helpers, "")) != null && Jboss_CVE_2017_12149_Scan.getResponse() != null && this.IsneedAddIssuse(Jboss_CVE_2017_12149_Scan, "Jboss Vul(Jboss_CVE_2017_12149)")){
                    issues = this.Addissuse(Jboss_CVE_2017_12149_Scan, "Jboss Vul(Jboss_CVE_2017_12149)", issues);
                }
                if (this.IsneedScan(baseRequestResponse, "Jboss_CVE_2017_7504_Scan") && Config.getBoolean("enabled_scan", true) && (Jboss_CVE_2017_7504_Scan = JbossScan.Jboss_CVE_2017_7504_Scan(baseRequestResponse, callbacks, helpers, "")) != null && Jboss_CVE_2017_7504_Scan.getResponse() != null && this.IsneedAddIssuse(Jboss_CVE_2017_7504_Scan, "Jboss Vul(Jboss_CVE_2017_7504)")){
                    issues = this.Addissuse(Jboss_CVE_2017_7504_Scan, "Jboss Vul(Jboss_CVE_2017_7504)", issues);
                }

            }


        }catch (Exception e){
            stdout.println("Jboss Scan Error:"+e);
        }
    }



    public void doWeblogicScanRCE(IHttpRequestResponse baseRequestResponse) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        try {
            IHttpRequestResponse weblogicreqres_iiop;
            IHttpRequestResponse weblogicreqres_t3;
            IHttpRequestResponse weblogicreqres_weakpass;
            IHttpRequestResponse weblogicreqres_CVE_2014_4210;
            IHttpRequestResponse weblogicreqres_CVE_2017_10271;
            IHttpRequestResponse weblogicreqres_CVE_2017_3506;
            IHttpRequestResponse weblogicreqres_CVE_2018_2894;
            IHttpRequestResponse weblogicreqres_CVE_2019_2729;
            IHttpRequestResponse weblogicreqres_CVE_2020_2551;
            IHttpRequestResponse weblogicreqres_CVE_2020_14883;
            IHttpRequestResponse weblogicreqres_uddiexplorer;
            if (this.IsneedScan(baseRequestResponse, "Weblogic uddiexplorer") && (weblogicreqres_uddiexplorer = WeblogicScan.WeblogicUddiExplorerScan(baseRequestResponse, callbacks, helpers, custompath)) != null && weblogicreqres_uddiexplorer.getResponse() != null) {
                this.Addissuse(weblogicreqres_uddiexplorer, "Weblogic uddiexplorer!!!", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2020-14882") && (weblogicreqres_CVE_2020_14883 = WeblogicScan.WeblogicCVE_2020_14882Scan(baseRequestResponse, callbacks, helpers, custompath)) != null && weblogicreqres_CVE_2020_14883.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_CVE_2020_14883, "Weblogic CVE-2020-14882/14883/14750!!!", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2020-2551") && (weblogicreqres_CVE_2020_2551 = WeblogicScan.WeblogicCVE_2020_2551Scan(baseRequestResponse, callbacks, helpers, custompath)) != null && weblogicreqres_CVE_2020_2551.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_CVE_2020_2551, "Weblogic CVE-2020-2551 (IIOP)", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2019-2729") && (weblogicreqres_CVE_2019_2729 = WeblogicScan.WeblogicCVE_2019_2729Scan(baseRequestResponse, callbacks, helpers, custompath)) != null && weblogicreqres_CVE_2019_2729.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_CVE_2019_2729, "Weblogic CVE-2019-2729/2725!!!", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2018-2894") && (weblogicreqres_CVE_2018_2894 = WeblogicScan.WeblogicCVE_2018_2894Scan(baseRequestResponse, callbacks, helpers, custompath)) != null && weblogicreqres_CVE_2018_2894.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_CVE_2018_2894, "Weblogic CVE-2018-2894!!!", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2017-3506") && (weblogicreqres_CVE_2017_3506 = WeblogicScan.WeblogicCVE_2017_3506Scan(baseRequestResponse, callbacks, helpers, dnslog, custompath)) != null && weblogicreqres_CVE_2017_3506.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_CVE_2017_3506, "Weblogic CVE-2017-3506 (T3)", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2017-10271") && (weblogicreqres_CVE_2017_10271 = WeblogicScan.WeblogicCVE_2017_10271Scan(baseRequestResponse, callbacks, helpers, dnslog, custompath)) != null && weblogicreqres_CVE_2017_10271.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_CVE_2017_10271, "Weblogic CVE-2017-10271!!!", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2014-4210 SSRF") && (weblogicreqres_CVE_2014_4210 = WeblogicScan.WeblogicCVE_2014_4210Scan(baseRequestResponse, callbacks, helpers, dnslog, custompath)) != null && weblogicreqres_CVE_2014_4210.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_CVE_2014_4210, "Weblogic CVE-2014-4210 SSRF", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE_2019_2618 Weak Pass") && (weblogicreqres_weakpass = WeblogicScan.WeblogicBannerPassCVE_2019_2618Scan(baseRequestResponse, callbacks, helpers, custompath)) != null && weblogicreqres_weakpass.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_weakpass, "Weblogic CVE_2019_2618 Weak Pass!!!", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic T3") && (weblogicreqres_t3 = WeblogicScan.WeblogicT3Scan(baseRequestResponse, helpers)) != null && weblogicreqres_t3.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_t3, "Weblogic T3", issues);
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic IIOP") && (weblogicreqres_iiop = WeblogicScan.WeblogicIIOPScan(baseRequestResponse, helpers)) != null && weblogicreqres_iiop.getResponse() != null) {
                issues = this.Addissuse(weblogicreqres_iiop, "Weblogic IIOP", issues);
            }
        } catch (Exception e) {
            stdout.println("Weblogic \u626b\u63cf\u51fa\u9519" + e);
        }
    }

    public void doLaravelScan(IHttpRequestResponse baseRequestResponse) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        try {
            IHttpRequestResponse laravel_env_reqres;
            IHttpRequestResponse laravel_debug_rce_reqres = LaravelScan.LaravelDebugRCEScan(baseRequestResponse, callbacks, helpers, custompath);
            if (laravel_debug_rce_reqres != null && laravel_debug_rce_reqres.getResponse() != null) {
                issues = this.Addissuse(laravel_debug_rce_reqres, "Laravel Debug RCE", issues);
            }
            if ((laravel_env_reqres = LaravelScan.LaravelEnvScan(baseRequestResponse, callbacks, helpers, custompath)) != null && laravel_env_reqres.getResponse() != null) {
                issues = this.Addissuse(laravel_env_reqres, "Laravel Env Found", issues);
            }
        } catch (Exception e) {
            stdout.println("Laravel \u626b\u63cf\u51fa\u9519" + e);
        }
    }

    public void doUeditorScan(IHttpRequestResponse baseRequestResponse) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        try {
            IHttpRequestResponse ueditor_dotnet_rce_reqres = UeditorScan.UeditorDotNetRCEScan(baseRequestResponse, callbacks, helpers, custompath);
            if (ueditor_dotnet_rce_reqres != null && ueditor_dotnet_rce_reqres.getResponse() != null) {
                issues = this.Addissuse(ueditor_dotnet_rce_reqres, "Ueditor .net RCE Found", issues);
            }
        } catch (Exception e) {
            stdout.println("Ueditor \u626b\u63cf\u51fa\u9519" + e);
        }
    }

    public void doSQLIScan(IHttpRequestResponse baseRequestResponse) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        try {
            IHttpRequestResponse sqli_time_reqres;
            IHttpRequestResponse sqli_error_reqres;
            IHttpRequestResponse sqli_echo_reqres = SQLIScan.ParamEchoScan(baseRequestResponse, callbacks, helpers);
            if (sqli_echo_reqres != null && sqli_echo_reqres.getResponse() != null) {
                issues = this.Addissuse(sqli_echo_reqres, "SQL Error Report", issues);
            }
            if ((sqli_error_reqres = SQLIScan.ParamErrorScan(baseRequestResponse, callbacks, helpers)) != null && sqli_error_reqres.getResponse() != null) {
                issues = this.Addissuse(sqli_error_reqres, "SQL Injection (Error)", issues);
            }
            if ((sqli_time_reqres = SQLIScan.ParamTimeScan(baseRequestResponse, callbacks, helpers)) != null && sqli_time_reqres.getResponse() != null) {
                issues = this.Addissuse(sqli_time_reqres, "SQL Injection (Time)", issues);
            }
        } catch (Exception e) {
            stdout.println("SQLI \u626b\u63cf\u51fa\u9519" + e);
        }
    }

    public void doThinkphpScanRCE(IHttpRequestResponse baseRequestResponse) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        try {
            IHttpRequestResponse thinkreqres = ThinkphpScan.ThinkphpScanRCE(baseRequestResponse, helpers, callbacks, custompath);
            if (thinkreqres != null && thinkreqres.getResponse() != null) {
                issues = this.Addissuse(thinkreqres, "Thinkphp RCE", issues);
            }
        } catch (Exception e) {
            stdout.println("Thinkphp \u626b\u63cf\u51fa\u9519" + e);
        }
    }

    public void doBypassCheckScan(IHttpRequestResponse baseRequestResponse){
        IHttpRequestResponse BypassCheck;
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        if ((BypassCheck = BypassScan.ScanBypass(baseRequestResponse, callbacks, helpers)) != null && BypassCheck.getResponse() != null) {
            issues = this.Addissuse(BypassCheck, "Bypass Check Found!", issues);
        }

    }


    public void doOssListObjectScan(IHttpRequestResponse baseRequestResponse) throws InterruptedException {
        IHttpRequestResponse OssListObjectScan;
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        if ((OssListObjectScan = OssScan.OssScan(baseRequestResponse, callbacks, helpers,2)) != null && OssListObjectScan.getResponse() != null) {
            issues = this.Addissuse(OssListObjectScan, "OssListObject Found!", issues);
        }

    }

    public void doJPathScan(IHttpRequestResponse baseRequestResponse) throws FileNotFoundException {
        IHttpRequestResponse actuatorrequestResponseDruid;
        IHttpRequestResponse actuatorrequestResponseSwagger;
        IHttpRequestResponse actuatorrequestResponseEnv;
        IHttpRequestResponse bypassScan;
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        if ((actuatorrequestResponseEnv = SpringBootActuatorScan.ScanEnv(baseRequestResponse, callbacks, helpers)) != null && actuatorrequestResponseEnv.getResponse() != null) {
            issues = this.Addissuse(actuatorrequestResponseEnv, "SpringActuator Found", issues);
        }
        if ((actuatorrequestResponseSwagger = SpringBootActuatorScan.ScanSwagger(baseRequestResponse, callbacks, helpers)) != null && actuatorrequestResponseSwagger.getResponse() != null) {
            issues = this.Addissuse(actuatorrequestResponseSwagger, "Swagger-ui api Found", issues);
        }
        if ((actuatorrequestResponseDruid = SpringBootActuatorScan.ScanDruid(baseRequestResponse, callbacks, helpers)) != null && actuatorrequestResponseDruid.getResponse() != null) {
            if (helpers.analyzeResponse(actuatorrequestResponseDruid.getResponse()).getStatusCode() == 200) {
                issues = this.Addissuse(actuatorrequestResponseDruid, "Druid Unauthorized Found", issues);
            } else if (helpers.analyzeResponse(actuatorrequestResponseDruid.getResponse()).getStatusCode() == 302) {
                issues = this.Addissuse(actuatorrequestResponseDruid, "Druid Need Authentication", issues);
            }
        }
    }

    public void doThinkphpScanLog(IHttpRequestResponse baseRequestResponse) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String custompath = url.getPath();
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        if (custompath.lastIndexOf("/") == custompath.length() - 1) {
            custompath = custompath.substring(0, custompath.length() - 1);
        }
        try {
            IHttpRequestResponse thinkreqres = ThinkphpScan.ThinkphpScanLog(baseRequestResponse, helpers, callbacks, custompath);
            if (thinkreqres != null && thinkreqres.getResponse() != null) {
                issues = this.Addissuse(thinkreqres, "Thinkphp Log", issues);
            }
        } catch (Exception e) {
            stdout.println("Thinkphp \u626b\u63cf\u51fa\u9519" + e);
        }
    }

    public void doFastjsonScan(IHttpRequestResponse baseRequestResponse) {
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        try {
            IHttpRequestResponse fjsonreqres = FastJsonScan.FastjsonScan(baseRequestResponse, callbacks, helpers, dnslog);
            if (fjsonreqres != null && fjsonreqres.getResponse() != null) {
                issues = this.Addissuse(fjsonreqres, "Fastjson Deserialization vulnerability", issues);
            }
        } catch (Exception e) {
            stdout.println("Fastjson \u626b\u63cf\u51fa\u9519" + e);
        }
    }

    private List<int[]> getMatches(byte[] response, byte[] match) {
        ArrayList<int[]> matches = new ArrayList<int[]>();
        for (int start = 0; start < response.length && (start = helpers.indexOf(response, match, true, start, response.length)) != -1; start += match.length) {
            matches.add(new int[]{start, start + match.length});
        }
        return matches;
    }

    @Override
    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse) throws InterruptedException {
        String[] oss_host=new String[]{"aliyuncs.com","myqcloud.com","s3.amazonaws.com","s3.us-west-1.amazonaws.com","s3.us-east-1.amazonaws.com","storage.googleapis.com","storage.cloud.google.com","s3.eu-central-1.wasabisys.com","s3.wasabisys.com","s3.filebase.com","nyc3.digitaloceanspaces.com","sgp1.digitaloceanspaces.com","ams3.digitaloceanspaces.com","b2api.backblazeb2.com"};

        String[] perix= new String[]{".css", ".js", ".png", ".jpg", ".gif", ".jpeg", ".svg", ".woff", ".woff2", ".ttf", ".ico", ".iso", ".xlsx", ".docs", ".doc", ".xls", ".ios", ".apk", ".mp3", ".mp4", ".swf", ".otf",".pdf"};
        stdout.println("自动被动扫描开启！");
        ArrayList<IScanIssue> issues = new ArrayList<IScanIssue>();
        Short LevelCross = 1;
        Short Leveluspath = 1;
        if (!Config.getBoolean("enabled_scan", true)) {
            return null;
        }

        helpers.analyzeRequest(baseRequestResponse).getHeaders().get(1);

        String header = helpers.analyzeRequest(baseRequestResponse).getHeaders().get(1);
        String url_pre = helpers.analyzeRequest(baseRequestResponse).getHeaders().get(0);


        /**
         * Minio TOdo
         * Minio好像没啥强特征，目前先判断后缀把
         *
         */

        if (this.Istarget(baseRequestResponse)  && Config.getBoolean("enabled_scan", true) && Config.getBoolean("enabled_Oss_listObject_Check", true) && Arrays.stream(oss_host).anyMatch(header::contains)) {

            IHttpRequestResponse OsslistObjectCheck;
            try {
                if ((OsslistObjectCheck = OssScan.OssScan(baseRequestResponse, callbacks, helpers,1)) != null && OsslistObjectCheck.getResponse() != null) {
                    issues = this.Addissuse(OsslistObjectCheck, "OssListObject  Found!", issues);
                }
            }catch (Exception e){
                stdout.println("OssListObject \u626b\u63cf\u51fa\u9519" + e);
            }
        }else if (this.Istarget(baseRequestResponse)  && Config.getBoolean("enabled_scan", true) && Config.getBoolean("enabled_Oss_listObject_Check", true) && Arrays.stream(perix).anyMatch(url_pre::contains)){

            IHttpRequestResponse OsslistObjectCheck;
            try {
                if ((OsslistObjectCheck = OssScan.OssScan(baseRequestResponse, callbacks, helpers,2)) != null && OsslistObjectCheck.getResponse() != null) {
                    issues = this.Addissuse(OsslistObjectCheck, "OssListObject  Found!", issues);
                }
            }catch (Exception e){
                stdout.println("OssListObject \u626b\u63cf\u51fa\u9519" + e);
            }

        }



        if (this.Istarget(baseRequestResponse) && this.Paichu(baseRequestResponse) && Config.getBoolean("enabled_scan", true)) {
            IHttpRequestResponse ueditor_dotnet_rce_reqres;
            IHttpRequestResponse requestResponseh;
            IHttpRequestResponse requestResponsep;
            URL url;
            List<String> resheaders = helpers.analyzeResponse(baseRequestResponse.getResponse()).getHeaders();
            String requrl = helpers.analyzeRequest(baseRequestResponse).getUrl().getPath();


            if (this.IsneedScan(baseRequestResponse, "Jboss") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true) && Config.getBoolean("enabled_Jboss", true)) {
                try{
                    if (Leveluspath == 1  && Common.SimpleJudgeJava(requrl) && Common.SimpleJudgeJava2(resheaders)) {
                        IHttpRequestResponse Jboss_finger;
                        url = helpers.analyzeRequest(baseRequestResponse).getUrl();
                        if (this.IsneedScan(baseRequestResponse, "Jboss Found") && !scannedDomainURL_Jboss_rce.contains(url.getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (Jboss_finger = JbossScan.Jboss_Console(baseRequestResponse, callbacks, helpers)) != null && Jboss_finger.getResponse() != null && this.IsneedAddIssuse(Jboss_finger, "Jboss Found") && Config.getBoolean("enabled_scan", true)){
                            IHttpRequestResponse Jboss_CVE_2017_12149_Scan;
                            IHttpRequestResponse Jboss_CVE_2017_7504_Scan;
                            issues = this.Addissuse(Jboss_finger, "Jboss Found!!!", issues);
                            if (this.IsneedScan(baseRequestResponse, "Jboss_CVE_2017_12149_Scan") && Config.getBoolean("enabled_scan", true) && (Jboss_CVE_2017_12149_Scan = JbossScan.Jboss_CVE_2017_12149_Scan(baseRequestResponse, callbacks, helpers, "")) != null && Jboss_CVE_2017_12149_Scan.getResponse() != null && this.IsneedAddIssuse(Jboss_CVE_2017_12149_Scan, "Jboss Vul(Jboss_CVE_2017_12149)")){
                                issues = this.Addissuse(Jboss_CVE_2017_12149_Scan, "Jboss Vul(Jboss_CVE_2017_12149)", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Jboss_CVE_2017_7504_Scan") && Config.getBoolean("enabled_scan", true) && (Jboss_CVE_2017_7504_Scan = JbossScan.Jboss_CVE_2017_7504_Scan(baseRequestResponse, callbacks, helpers, "")) != null && Jboss_CVE_2017_7504_Scan.getResponse() != null && this.IsneedAddIssuse(Jboss_CVE_2017_7504_Scan, "Jboss Vul(Jboss_CVE_2017_7504)")){
                                issues = this.Addissuse(Jboss_CVE_2017_7504_Scan, "Jboss Vul(Jboss_CVE_2017_7504)", issues);
                            }

                        }
                    }
                }catch (Exception e){
                    stdout.println("Jboss \u626b\u63cf\u51fa\u9519" + e);
                }
            }

            if (this.IsneedScan(baseRequestResponse, "Bypass") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true)){
                try {
                    IHttpRequestResponse bypassScan;
                    if ((bypassScan= BypassScan.ScanBypass(baseRequestResponse, callbacks, helpers))!=null && bypassScan.getResponse()!=null){

                        issues = this.Addissuse(bypassScan, "Auth Bypass", issues);
                    }
                }catch (Exception e){
                    stdout.println("bypass Scan Error:"+e);
                }
            }
            if (this.IsneedScan(baseRequestResponse, "Axis") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true)) {
                try {
                    IHttpRequestResponse axisreqres;
                    if (Leveluspath == 1 && Config.getBoolean("enabled_axis", true) && Common.SimpleJudgeJava1(resheaders) && Common.SimpleJudgeJava2(requrl) && !scannedDomainURL_axis.contains((url = helpers.analyzeRequest(baseRequestResponse).getUrl()).getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (axisreqres = AxisScan.AxisScan(baseRequestResponse, callbacks, helpers)) != null && axisreqres.getResponse() != null && this.IsneedAddIssuse(axisreqres, "Axis Found")) {
                        issues = this.Addissuse(axisreqres, "Axis Found", issues);
                    }
                } catch (Exception e) {
                    stdout.println("Axis \u626b\u63cf\u51fa\u9519" + e);
                }
            }
            try {
                IHttpRequestResponse nacosreqres;
                if (Config.getBoolean("enabled_nacos", true) && Config.getBoolean("enabled_scan", true) && this.Istarget(baseRequestResponse) && this.IsneedScan(baseRequestResponse, "Nacos") && (nacosreqres = NacosScan.NacosUnauthScan(baseRequestResponse, callbacks, helpers, "")) != null && nacosreqres.getResponse() != null) {
                    issues = Common.getResbody(nacosreqres.getResponse(), helpers).contains("<title>Nacos</title>") ? this.Addissuse(nacosreqres, "Nacos Found!", issues) : this.Addissuse(nacosreqres, "Nacos Unauthorized Found!", issues);
                }
            } catch (Exception e) {
                stdout.println("Nacos \u626b\u63cf\u51fa\u9519" + e);
            }
            try {
                if (Config.getBoolean("enabled_springenv", true) && this.IsneedScan(baseRequestResponse, "SpringActuator") && Common.SimpleJudgeJava1(resheaders) && Common.SimpleJudgeJava2(requrl) && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true)) {
                    IHttpRequestResponse actuatorrequestResponseEnv;
                    IHttpRequestResponse actuatorrequestResponseSwagger;

                    ArrayList newissuseL = new ArrayList();
                    IHttpRequestResponse actuatorrequestResponseDruid = SpringBootActuatorScan.ScanDruid(baseRequestResponse, callbacks, helpers);
                    if (actuatorrequestResponseDruid != null && actuatorrequestResponseDruid.getResponse() != null) {
                        if (this.IsneedAddIssuse(actuatorrequestResponseDruid, "Druid Unauth") && helpers.analyzeResponse(actuatorrequestResponseDruid.getResponse()).getStatusCode() == 200) {
                            issues = this.Addissuse(actuatorrequestResponseDruid, "Druid Unauthorized", issues);
                        } else if (this.IsneedAddIssuse(actuatorrequestResponseDruid, "Druid Auth") && helpers.analyzeResponse(actuatorrequestResponseDruid.getResponse()).getStatusCode() == 302) {
                            issues = this.Addissuse(actuatorrequestResponseDruid, "Druid Need Authentication", issues);
                        }
                    }
                    if ((actuatorrequestResponseSwagger = SpringBootActuatorScan.ScanSwagger(baseRequestResponse, callbacks, helpers)) != null && actuatorrequestResponseSwagger.getResponse() != null) {
                        issues = this.Addissuse(actuatorrequestResponseSwagger, "Swagger-ui api", issues);
                    }
                    if ((actuatorrequestResponseEnv = SpringBootActuatorScan.ScanEnv(baseRequestResponse, callbacks, helpers)) != null && actuatorrequestResponseEnv.getResponse() != null) {
                        issues = this.Addissuse(actuatorrequestResponseEnv, "SpringActuator", issues);
                    }
                    if (LevelCross == 1 && Config.getBoolean("enabled_springcross", true) && Config.getBoolean("enabled_scan", true) && (actuatorrequestResponseEnv = SpringBootActuatorScan.CrossScan(baseRequestResponse, callbacks, helpers)) != null && actuatorrequestResponseEnv.getResponse() != null) {
                        issues = this.Addissuse(actuatorrequestResponseEnv, "SpringActuator (;)", issues);
                    }

                }
            } catch (Exception e) {
                stderr.println("\u88ab\u52a8\u626b\u63cfspring\u51fa\u73b0\u9519\u8bef" + e);
            }
            try {
                if (this.IsneedScan(baseRequestResponse, "Log4j") && Common.SimpleJudgeJava1(resheaders) && Common.SimpleJudgeJava2(requrl) && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true) && Leveluspath == 1 && Config.getBoolean("enabled_log4j", true)) {
                    IHttpRequestResponse requestResponse404;
                    if (Common.IsHaveParams(baseRequestResponse, helpers) && (requestResponsep = Log4jScan.ScanParam(baseRequestResponse, callbacks, helpers, dnslog)) != null && requestResponsep.getResponse() != null && this.IsneedAddIssuse(requestResponsep, "Log4j")) {
                        issues = this.Addissuse(requestResponsep, "Log4j Rce", issues);
                    }
                    if ((requestResponseh = Log4jScan.ScanHeader(baseRequestResponse, callbacks, helpers, dnslog)) != null && requestResponseh.getResponse() != null && this.IsneedAddIssuse(requestResponseh, "Log4j")) {
                        issues = this.Addissuse(requestResponseh, "Log4j Rce", issues);
                    }
                    if ((requestResponse404 = Log4jScan.Scan404(baseRequestResponse, callbacks, helpers, dnslog)) != null && requestResponse404.getResponse() != null && this.IsneedAddIssuse(requestResponse404, "Log4j")) {
                        issues = this.Addissuse(requestResponse404, "Log4j Rce", issues);
                    }
                }
            } catch (Exception e) {
                stderr.println("\u88ab\u52a8\u626b\u63cfLog4j\u51fa\u73b0\u9519\u8bef" + e);
            }
            try {
                if (this.IsneedScan(baseRequestResponse, "Text4shell") && Common.SimpleJudgeJava1(resheaders) && Common.SimpleJudgeJava2(requrl) && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true) && Leveluspath == 1 && Config.getBoolean("enabled_text4shell", true)) {
                    if (Common.IsHaveParams(baseRequestResponse, helpers) && (requestResponsep = Text4shellScan.ScanParam(baseRequestResponse, callbacks, helpers, dnslog)) != null && requestResponsep.getResponse() != null && this.IsneedAddIssuse(requestResponsep, "Text4shell")) {
                        issues = this.Addissuse(requestResponsep, "Text4shell Rce", issues);
                    }
                    if ((requestResponseh = Text4shellScan.ScanHeader(baseRequestResponse, callbacks, helpers, dnslog)) != null && requestResponseh.getResponse() != null && this.IsneedAddIssuse(requestResponseh, "Text4shell")) {
                        issues = this.Addissuse(requestResponseh, "Text4shell Rce", issues);
                    }
                }
            } catch (Exception e) {
                stderr.println("\u88ab\u52a8\u626b\u63cfText4shell\u51fa\u73b0\u9519\u8bef" + e);
            }
            try {
                IHttpRequestResponse shiroreqres;
                if (this.IsneedScan(baseRequestResponse, "Shiro") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true) && Config.getBoolean("enabled_shiro", true) && (shiroreqres = ShiroScan.ShiroScan(baseRequestResponse, callbacks, helpers)) != null && shiroreqres.getResponse() != null && this.IsneedAddIssuse(shiroreqres, "Shiro")) {
                    issues = this.Addissuse(shiroreqres, "Shiro", issues);
                }
            } catch (Exception e) {
                stdout.println("shiro \u5224\u65ad\u51fa\u9519" + e);
            }
            if (this.IsneedScan(baseRequestResponse, "SpringCloud") && Common.SimpleJudgeJava1(resheaders) && Common.SimpleJudgeJava2(requrl) && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true)) {
                try {
                    if (Leveluspath == 1 && Config.getBoolean("enabled_springcloud", true)) {
                        IHttpRequestResponse cloudSPELreqres;
                        IHttpRequestResponse cloudGatewayreqres = SpringCloudScan.CloudGatewayScan(baseRequestResponse, callbacks, helpers);




                        if (cloudGatewayreqres != null && cloudGatewayreqres.getResponse() != null && this.IsneedAddIssuse(cloudGatewayreqres, "SpringCloudGateway")) {
                            issues = this.Addissuse(cloudGatewayreqres, "SpringCloud Gateway Rce (Maybe)", issues);
                        }
                        if ((cloudSPELreqres = SpringCloudScan.CloudSPELScan(baseRequestResponse, callbacks, helpers, dnslog)) != null && cloudSPELreqres.getResponse() != null && this.IsneedAddIssuse(cloudSPELreqres, "SpringCloudSPEL")) {
                            issues = this.Addissuse(cloudSPELreqres, "SpringCloud Function Rce", issues);
                        }
                    }
                } catch (Exception e) {
                    stdout.println("Spring Cloud\u626b\u63cf\u51fa\u9519" + e);
                }
            }
            if (this.IsneedScan(baseRequestResponse, "Fastjson") && Common.SimpleJudgeJava1(resheaders) && Common.SimpleJudgeJava2(requrl) && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true)) {
                try {
                    IHttpRequestResponse fjreqres;
                    if (Leveluspath == 1 && Config.getBoolean("enabled_fastjson", true) && (fjreqres = FastJsonScan.FastjsonScan(baseRequestResponse, callbacks, helpers, dnslog)) != null && fjreqres.getResponse() != null && this.IsneedAddIssuse(fjreqres, "Fastjson")) {
                        issues = this.Addissuse(fjreqres, "Fastjson Deserialization vulnerability", issues);
                    }
                } catch (Exception e) {
                    stdout.println("fastjson \u626b\u63cf\u51fa\u9519" + e);
                }
            }
            if (this.IsneedScan(baseRequestResponse, "Weblogic") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true)) {
                try {
                    if (Leveluspath == 1 && Config.getBoolean("enabled_weblogic", true) && Common.SimpleJudgeJava(requrl) && Common.SimpleJudgeJava2(resheaders)) {
                        IHttpRequestResponse weblogicreqres_finger;
                        url = helpers.analyzeRequest(baseRequestResponse).getUrl();
                        if (this.IsneedScan(baseRequestResponse, "Weblogic Found") && !scannedDomainURL_weblogic_rce.contains(url.getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (weblogicreqres_finger = WeblogicScan.WeblogicFingerScan(baseRequestResponse, callbacks, helpers)) != null && weblogicreqres_finger.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_finger, "Weblogic Found") && Config.getBoolean("enabled_scan", true)) {
                            IHttpRequestResponse weblogicreqres_iiop;
                            IHttpRequestResponse weblogicreqres_t3;
                            IHttpRequestResponse weblogicreqres_weakpass;
                            IHttpRequestResponse weblogicreqres_CVE_2014_4210;
                            IHttpRequestResponse weblogicreqres_CVE_2017_10271;
                            IHttpRequestResponse weblogicreqres_CVE_2017_3506;
                            IHttpRequestResponse weblogicreqres_CVE_2018_2894;
                            IHttpRequestResponse weblogicreqres_CVE_2019_2729;
                            IHttpRequestResponse weblogicreqres_CVE_2020_2551;
                            IHttpRequestResponse weblogicreqres_CVE_2020_14883;
                            IHttpRequestResponse weblogicreqres_uddiexplorer;
                            issues = this.Addissuse(weblogicreqres_finger, "Weblogic Found", issues);
                            if (this.IsneedScan(baseRequestResponse, "Weblogic uddiexplorer") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_uddiexplorer = WeblogicScan.WeblogicUddiExplorerScan(baseRequestResponse, callbacks, helpers, "")) != null && weblogicreqres_uddiexplorer.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_uddiexplorer, "Weblogic uddiexplorer")) {
                                issues = this.Addissuse(weblogicreqres_uddiexplorer, "Weblogic uddiexplorer!!!", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2020-14882") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_CVE_2020_14883 = WeblogicScan.WeblogicCVE_2020_14882Scan(baseRequestResponse, callbacks, helpers, "")) != null && weblogicreqres_CVE_2020_14883.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_CVE_2020_14883, "Weblogic CVE-2020-14882/14883/14750")) {
                                issues = this.Addissuse(weblogicreqres_CVE_2020_14883, "Weblogic CVE-2020-14882/14883/14750!!!", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2020-2551") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_CVE_2020_2551 = WeblogicScan.WeblogicCVE_2020_2551Scan(baseRequestResponse, callbacks, helpers, "")) != null && weblogicreqres_CVE_2020_2551.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_CVE_2020_2551, "Weblogic CVE-2020-2551")) {
                                issues = this.Addissuse(weblogicreqres_CVE_2020_2551, "Weblogic CVE-2020-2551 (IIOP)", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2019-2729") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_CVE_2019_2729 = WeblogicScan.WeblogicCVE_2019_2729Scan(baseRequestResponse, callbacks, helpers, "")) != null && weblogicreqres_CVE_2019_2729.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_CVE_2019_2729, "Weblogic CVE-2019-2729/2725")) {
                                issues = this.Addissuse(weblogicreqres_CVE_2019_2729, "Weblogic CVE-2019-2729/2725!!!", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2018-2894") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_CVE_2018_2894 = WeblogicScan.WeblogicCVE_2018_2894Scan(baseRequestResponse, callbacks, helpers, "")) != null && weblogicreqres_CVE_2018_2894.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_CVE_2018_2894, "Weblogic CVE-2018-2894")) {
                                issues = this.Addissuse(weblogicreqres_CVE_2018_2894, "Weblogic CVE-2018-2894!!!", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2017-3506") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_CVE_2017_3506 = WeblogicScan.WeblogicCVE_2017_3506Scan(baseRequestResponse, callbacks, helpers, dnslog, "")) != null && weblogicreqres_CVE_2017_3506.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_CVE_2017_3506, "Weblogic CVE-2017-3506")) {
                                issues = this.Addissuse(weblogicreqres_CVE_2017_3506, "Weblogic CVE-2017-3506 (T3)", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2017-10271") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_CVE_2017_10271 = WeblogicScan.WeblogicCVE_2017_10271Scan(baseRequestResponse, callbacks, helpers, dnslog, "")) != null && weblogicreqres_CVE_2017_10271.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_CVE_2017_10271, "Weblogic CVE-2017-10271")) {
                                issues = this.Addissuse(weblogicreqres_CVE_2017_10271, "Weblogic CVE-2017-10271!!!", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE-2014-4210 SSRF") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_CVE_2014_4210 = WeblogicScan.WeblogicCVE_2014_4210Scan(baseRequestResponse, callbacks, helpers, dnslog, "")) != null && weblogicreqres_CVE_2014_4210.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_CVE_2014_4210, "Weblogic CVE-2014-4210 SSRF")) {
                                issues = this.Addissuse(weblogicreqres_CVE_2014_4210, "Weblogic CVE-2014-4210 SSRF", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic CVE_2019_2618 Weak Pass") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_weakpass = WeblogicScan.WeblogicBannerPassCVE_2019_2618Scan(baseRequestResponse, callbacks, helpers, "")) != null && weblogicreqres_weakpass.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_weakpass, "Weblogic CVE_2019_2618 Weak Pass")) {
                                issues = this.Addissuse(weblogicreqres_weakpass, "Weblogic CVE_2019_2618 Weak Pass!!!", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic T3") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_t3 = WeblogicScan.WeblogicT3Scan(baseRequestResponse, helpers)) != null && weblogicreqres_t3.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_t3, "Weblogic T3")) {
                                issues = this.Addissuse(weblogicreqres_t3, "Weblogic T3", issues);
                            }
                            if (this.IsneedScan(baseRequestResponse, "Weblogic IIOP") && Config.getBoolean("enabled_scan", true) && (weblogicreqres_iiop = WeblogicScan.WeblogicIIOPScan(baseRequestResponse, helpers)) != null && weblogicreqres_iiop.getResponse() != null && this.IsneedAddIssuse(weblogicreqres_iiop, "Weblogic IIOP")) {
                                issues = this.Addissuse(weblogicreqres_iiop, "Weblogic IIOP", issues);
                            }
                            scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
                        }
                    }
                } catch (Exception e) {
                    stdout.println("weblogic \u626b\u63cf\u51fa\u9519" + e);
                }
            }




            //jboss

            if (this.IsneedScan(baseRequestResponse, "Thinkphp") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true)) {
                try {
                    if (Leveluspath == 1 && Config.getBoolean("enabled_thinkphp", true) && (Common.SimpleJudgePhp(requrl) || Common.SimpleJudgePhp2(resheaders))) {
                        IHttpRequestResponse thinkreqres_log;
                        IHttpRequestResponse thinkreqres;
                        url = helpers.analyzeRequest(baseRequestResponse).getUrl();
                        if (!scannedDomainURL_thinkphp_rce.contains(url.getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (thinkreqres = ThinkphpScan.ThinkphpScanRCE(baseRequestResponse, helpers, callbacks, "")) != null && thinkreqres.getResponse() != null && this.IsneedAddIssuse(thinkreqres, "Thinkphp RCE")) {
                            issues = this.Addissuse(thinkreqres, "Thinkphp RCE", issues);
                        }
                        if (!scannedDomainURL_thinkphp_log.contains(url.getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (thinkreqres_log = ThinkphpScan.ThinkphpScanLog(baseRequestResponse, helpers, callbacks, "")) != null && thinkreqres_log.getResponse() != null && this.IsneedAddIssuse(thinkreqres_log, "Thinkphp Log")) {
                            issues = this.Addissuse(thinkreqres_log, "Thinkphp Log", issues);
                        }
                    }
                } catch (Exception e) {
                    stdout.println("Thinkphp \u626b\u63cf\u51fa\u9519" + e);
                }
            }
            if (this.IsneedScan(baseRequestResponse, "Laravel") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true) && Leveluspath == 1 && Config.getBoolean("enabled_laravel", true) && (Common.SimpleJudgePhp(requrl) || Common.SimpleJudgePhp2(resheaders))) {
                IHttpRequestResponse laraven_env_reqres;
                IHttpRequestResponse laravel_debugrce_reqres;
                url = helpers.analyzeRequest(baseRequestResponse).getUrl();
                if (!scannedDomainURL_laravel_debugrce.contains(url.getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (laravel_debugrce_reqres = LaravelScan.LaravelDebugRCEScan(baseRequestResponse, callbacks, helpers, "")) != null && laravel_debugrce_reqres.getResponse() != null && this.IsneedAddIssuse(laravel_debugrce_reqres, "Laravel Debug RCE")) {
                    issues = this.Addissuse(laravel_debugrce_reqres, "Laravel Debug RCE", issues);
                }
                if (!scannedDomainURL_laravel_env.contains(url.getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (laraven_env_reqres = LaravelScan.LaravelEnvScan(baseRequestResponse, callbacks, helpers, "")) != null && laraven_env_reqres.getResponse() != null && this.IsneedAddIssuse(laraven_env_reqres, "Laravel Env Found")) {
                    issues = this.Addissuse(laraven_env_reqres, "Laravel Env Found", issues);
                }
            }
            if (this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_sqli", true) && Leveluspath == 1 && Config.getBoolean("enabled_sqli", true)) {
                url = helpers.analyzeRequest(baseRequestResponse).getUrl();
                try {
                    IHttpRequestResponse sqli_time_reqres;
                    IHttpRequestResponse sqli_error_reqres;
                    IHttpRequestResponse sqli_echo_reqres = SQLIScan.ParamEchoScan(baseRequestResponse, callbacks, helpers);
                    if (sqli_echo_reqres != null && sqli_echo_reqres.getResponse() != null) {
                        issues = this.Addissuse(sqli_echo_reqres, "SQL Error Report", issues);
                    }
                    if ((sqli_error_reqres = SQLIScan.ParamErrorScan(baseRequestResponse, callbacks, helpers)) != null && sqli_error_reqres.getResponse() != null) {
                        issues = this.Addissuse(sqli_error_reqres, "SQL Injection (Error)", issues);
                    }
                    if ((sqli_time_reqres = SQLIScan.ParamTimeScan(baseRequestResponse, callbacks, helpers)) != null && sqli_time_reqres.getResponse() != null) {
                        issues = this.Addissuse(sqli_time_reqres, "SQL Injection (Time)", issues);
                    }
                } catch (UnsupportedEncodingException e) {
                    stdout.println("SQLI \u626b\u63cf\u51fa\u9519" + e);
                }
            }
            if (this.IsneedScan(baseRequestResponse, "Ueditor") && this.Istarget(baseRequestResponse) && Config.getBoolean("enabled_scan", true) && Config.getBoolean("enabled_ueditor", true) && !scannedDomainURL_Ueditor_dotnet_rce.contains((url = helpers.analyzeRequest(baseRequestResponse).getUrl()).getHost() + ":" + url.getPort()) && Config.getBoolean("enabled_scan", true) && (ueditor_dotnet_rce_reqres = UeditorScan.UeditorDotNetRCEScan(baseRequestResponse, callbacks, helpers, "")) != null && ueditor_dotnet_rce_reqres.getResponse() != null && this.IsneedAddIssuse(ueditor_dotnet_rce_reqres, "Ueditor .net RCE Found")) {
                issues = this.Addissuse(ueditor_dotnet_rce_reqres, "Ueditor .net RCE Found", issues);
            }


        }
        return issues;
    }

    @Override
    public List<IScanIssue> doActiveScan(IHttpRequestResponse baseRequestResponse, IScannerInsertionPoint insertionPoint) {
        return null;
    }

    @Override
    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue) {
        return existingIssue.getHttpService().getHost().equals(newIssue.getHttpService().getHost()) ? 0 : 1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<IScanIssue> Addissuse(IHttpRequestResponse reqres, String vulname, ArrayList<IScanIssue> issue) {
        URL url = helpers.analyzeRequest(reqres).getUrl();
        IHttpService httpService = reqres.getHttpService();
        String reqMethod = helpers.analyzeRequest(reqres).getMethod();
        stdout.println("-------------\n" + vulname + " \u5b58\u5728\uff01\uff01\uff01\n" + url + "\n-------------");
        issue.add(new CustomScanIssue(reqres.getHttpService(), url, new IHttpRequestResponse[]{reqres}, vulname, "path: " + url, "High"));
        this.issueALL.add(new CustomScanIssue(reqres.getHttpService(), url, new IHttpRequestResponse[]{reqres}, vulname, "path: " + url, "High"));
        byte[] newIHttpRequestResponse = reqres.getResponse();
        this.ulists.add(new Ulist(httpService.getHost(), httpService.getPort()));
        List list = this.Udatas;
        synchronized (list) {
            int row = this.Udatas.size();
            this.Udatas.add(new TablesData(row, reqMethod, url.toString(), helpers.analyzeResponse(newIHttpRequestResponse).getStatusCode() + "", vulname, reqres));
            this.fireTableRowsInserted(row, row);
        }
        return issue;
    }

    public static void addScannedURL(String target_domain, List<String> new_path_list, HashMap<String, List<String>> scannedDomainURL) {
        if (scannedDomainURL.containsKey(target_domain)) {
            List<String> path_list = scannedDomainURL.get(target_domain);
            List combine_list = Stream.of(path_list, new_path_list).flatMap(Collection::stream).distinct().collect(Collectors.toList());
            scannedDomainURL.replace(target_domain, path_list, combine_list);
        } else {
            scannedDomainURL.put(target_domain, new_path_list);
        }
    }

    public static boolean IsScannedURL(String domain, String path, HashMap<String, List<String>> scannedDomainURL) {
        List<String> path_list = scannedDomainURL.get(domain);
        return path_list != null && path_list.size() != 0 && path_list.contains(path);
    }

    public static List<String> MakeQueue(String domain, String header, HashMap<String, List<String>> scannedDomainURL) {
        ArrayList<String> queue = new ArrayList<String>();
        if (header.contains("GET / HTTP")) {
            ArrayList<String> headers = new ArrayList<String>();
            headers.add(header);
            queue = headers;
        } else if (header.contains("GET /?")) {
            String[] headerr = header.split("/");
            header = "GET / HTTP/" + headerr[2];
            ArrayList<String> headers = new ArrayList<String>();
            headers.add(header);
            queue = headers;
        } else {
            int begin;
            int iscanshu = 0;
            String[] exts = header.split("/");
            String ext = exts[exts.length - 1];
            if (header.contains("?")) {
                int index = header.indexOf("?");
                header = header.substring(0, index);
                iscanshu = 1;
            }
            String test = "";
            String[] headers = header.split("/");
            int i = 0;
            int count = headers.length - 3 + iscanshu;
            for (i = begin = headers.length - 2 + iscanshu; i >= begin - count; --i) {
                String fianlheader = "";
                for (int j = 0; j < i; ++j) {
                    fianlheader = fianlheader + headers[j] + "/";
                }
                fianlheader = fianlheader + " HTTP/" + ext;
                fianlheader = fianlheader.replace("POST ", "GET ");
                fianlheader = fianlheader.replace("OPTIONS ", "GET ");
                fianlheader = fianlheader.replace("PUT ", "GET ");
                fianlheader = fianlheader.replace("DELETE ", "GET ");
                fianlheader = fianlheader.replace("//", "/");
                queue.add(fianlheader);
            }
        }
        for (int i = queue.size() - 1; i >= 0; --i) {
            String tmp = (String)queue.get(i);
            Matcher m3 = Pattern.compile("GET (.*?) HTTP/1.1").matcher(tmp);
            if (!m3.find() || !BurpExtender.IsScannedURL(domain, m3.group(1), scannedDomainURL)) continue;
            queue.remove(i);
        }
        return queue;
    }

    public static List<String> MakeQueue_v2(String domain, String header, HashMap<String, List<String>> scannedDomainURL) {
        List<String> queue = new ArrayList<>();

        Matcher matcher = Pattern.compile("^(GET|POST|PUT|DELETE|OPTIONS)\\s+(/[^\\s]*)\\s+HTTP/([\\d.]+)$").matcher(header);
        if (!matcher.find()) {
            return queue;
        }

        String method = matcher.group(1);
        String path = matcher.group(2);
        String version = matcher.group(3);

        // 判断是否已扫描
        if (!BurpExtender.IsScannedURL(domain, path, scannedDomainURL)) {
            queue.add(method + " " + path + " HTTP/" + version);
        }

        return queue;
    }

    private static String[] GetBackends() {
        Backends[] backends;
        ArrayList<String> algStrs = new ArrayList<String>();
        for (Backends backend : backends = Backends.values()) {
            algStrs.add(backend.name().replace('_', '/'));
        }
        return algStrs.toArray(new String[algStrs.size()]);
    }

    public boolean IsneedScan(IHttpRequestResponse baseRequestResponse, String vul) {
        for (int count = 0; count < this.issueALL.size(); ++count) {
            if (!this.issueALL.get(count).getIssueName().contains(vul) || !(this.issueALL.get(count).getHttpService().getHost() + ":" + this.issueALL.get(count).getHttpService().getPort()).equals(baseRequestResponse.getHttpService().getHost() + ":" + baseRequestResponse.getHttpService().getPort())) continue;
            return false;
        }
        return true;
    }

    public boolean IsneedAddIssuse(IHttpRequestResponse baseRequestResponse, String vul) {
        for (int count = 0; count < this.issueALL.size(); ++count) {
            if (!this.issueALL.get(count).getIssueName().contains(vul) || !(this.issueALL.get(count).getHttpService().getHost() + ":" + this.issueALL.get(count).getHttpService().getPort()).equals(baseRequestResponse.getHttpService().getHost() + ":" + baseRequestResponse.getHttpService().getPort())) continue;
            return false;
        }
        return true;
    }

    public boolean Istarget(IHttpRequestResponse baseRequestResponse) {
        if (!Config.getBoolean("enabled_domain_blacklist", true)) {
            return true;
        }
        String[] tmp = domain_blacklist.getText().split("\n");
        for (int i = 0; i < tmp.length; ++i) {
            if (!(baseRequestResponse.getHttpService().getHost() + ":" + baseRequestResponse.getHttpService().getPort()).contains(tmp[i])) continue;
            return false;
        }
        return true;
    }

    public boolean Paichu(IHttpRequestResponse baseRequestResponse) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        String url_str = url.getProtocol() + "://" + url.getHost() + url.getPort() + url.getPath();
        for (int i = 0; i < this.BlackFileExt.length; ++i) {
            if (!url_str.toLowerCase().endsWith(this.BlackFileExt[i])) continue;
            return false;
        }
        return true;
    }

    public int IsMakeLevel(IHttpRequestResponse baseRequestResponse) {
        for (int i = 0; i < this.Hostlist.size(); ++i) {
            if (!this.Hostlist.get(i).contains(baseRequestResponse.getHttpService().getHost() + ":" + baseRequestResponse.getHttpService().getPort())) continue;
            return i;
        }
        stdout.println(baseRequestResponse.getHttpService().getHost() + ":" + baseRequestResponse.getHttpService().getPort() + "\u8fd8\u672a\u5224\u65ad\u767b\u8bb0\uff0c\u524d\u53bb\u5224\u65ad\uff5e");
        return -1;
    }

    @Override
    public IHttpService getHttpService() {
        return this.currentlyDisplayedItem.getHttpService();
    }

    @Override
    public byte[] getRequest() {
        return this.currentlyDisplayedItem.getRequest();
    }

    @Override
    public byte[] getResponse() {
        return this.currentlyDisplayedItem.getResponse();
    }

    @Override
    public String getTabCaption() {
        return "TsojanScan";
    }

    @Override
    public Component getUiComponent() {
        return this.mainPane;
    }

    @Override
    public int getRowCount() {
        return this.Udatas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: {
                return "#";
            }
            case 1: {
                return "Method";
            }
            case 2: {
                return "URL";
            }
            case 3: {
                return "Status";
            }
            case 4: {
                return "Issue";
            }
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TablesData datas = (TablesData)this.Udatas.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return datas.Id + 1;
            }
            case 1: {
                return datas.Method;
            }
            case 2: {
                return datas.URL;
            }
            case 3: {
                return datas.Status;
            }
            case 4: {
                return datas.issue;
            }
        }
        return null;
    }

    public static boolean contains(String[] stringArray, String source2) {
        List<String> tempList = Arrays.asList(stringArray);
        return tempList.contains(source2);
    }

    static {
        scannedDomainURL_env = new HashMap();
        scannedDomainURL_swagger = new HashMap();
        scannedDomainURL_druid = new HashMap();
        scannedDomainURL_envcross = new HashMap();
        scannedDomainURL_gateway = new HashMap();
        scannedDomainURL_spel = new HashMap();
        scannedDomainURL_log4j = new HashMap();
        scannedDomainURL_text4shell = new HashMap();
        scannedDomainURL_fastjson = new HashMap();
        scannedDomainURL_sqli = new HashMap();
        scannedDomainURL_thinkphp_rce = new ArrayList<String>();
        scannedDomainURL_thinkphp_log = new ArrayList<String>();
        scannedDomainURL_weblogic_rce = new ArrayList<String>();
        scannedDomainURL_axis = new ArrayList<String>();
        scannedDomainURL_nacos = new ArrayList<String>();
        scannedDomainURL_laravel_debugrce = new ArrayList<String>();
        scannedDomainURL_laravel_env = new ArrayList<String>();
        scannedDomainURL_Ueditor_dotnet_rce = new ArrayList<String>();
        scannedDomainURL_Jboss_rce = new ArrayList<String>();
        scannedDomainURL_Bypass = new HashMap();
        scannedDomainURL_Oss = new HashMap();
        BlackListDomain_org = new String[]{"172.247.14.95", "101.35.54.28:8000", "ceye.io", "api.ipify.org", "google.cn", "google.com", "google.co.jp", "gstatic.com", "ytimg.com", "doubleclick.net", "ggpht.com", "youtube.com", "googleusercontent.com", "github.com", "githubassets.com", "raw.githubusercontent.com", "e.topthink.com", "hcfy.app", "wappalyzer.com", "detectportal.firefox.com", "servicewechat.com", "ingest.sentry.io", "firefox.com", "tencent-cloud.com", "amap.com", "googleapis.com", "mozilla.cloudflare-dns.com", "mozilla.com", "netease.com", "webapp.163.com", "wx.qlogo.cn", "qq.com", "mozilla.org", "firefoxchina.cn", "baidu.com", "bdstatic.com", "firefox.cn", "mozilla.net", "fofa.info", "qpic.cn", "g-fox.cn", "firefox.com.cn", "qlogo.cn", "rss.ink"};
    }

    @Override
    public void processProxyMessage(boolean messageIsRequest, IInterceptedProxyMessage message) {
        // 我们只对请求进行处理
        stdout.println(111);
    }


    public class Ulist {
        final String host;
        final int port;

        public Ulist(String host, int port) {
            this.host = host;
            this.port = port;
        }
    }

    public class URLTable
    extends JTable {
        public URLTable(TableModel tableModel) {
            super(tableModel);
        }

        @Override
        public void changeSelection(int row, int col, boolean toggle, boolean extend) {
            TablesData dataEntry = (TablesData)BurpExtender.this.Udatas.get(this.convertRowIndexToModel(row));
            BurpExtender.this.HRequestTextEditor.setMessage(dataEntry.requestResponse.getRequest(), true);
            BurpExtender.this.HResponseTextEditor.setMessage(dataEntry.requestResponse.getResponse(), false);
            BurpExtender.this.currentlyDisplayedItem = dataEntry.requestResponse;
            super.changeSelection(row, col, toggle, extend);
        }
    }

    public static class TablesData {
        final int Id;
        final String Method;
        final String URL;
        final String Status;
        final String issue;
        final IHttpRequestResponse requestResponse;

        public TablesData(int id, String method, String url, String status, String issue, IHttpRequestResponse requestResponse) {
            this.Id = id;
            this.Method = method;
            this.URL = url;
            this.Status = status;
            this.issue = issue;
            this.requestResponse = requestResponse;
        }
    }

    public static enum Backends {
        Ceye,
        XyzDnsLog;

    }
}

