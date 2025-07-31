/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.BshClassManager;
import bsh.ClassPathException;
import bsh.StringUtil;
import bsh.classpath.BshClassPath;
import bsh.classpath.ClassManagerImpl;
import bsh.classpath.ClassPathListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class ClassBrowser
extends JSplitPane
implements ListSelectionListener,
ClassPathListener {
    BshClassPath classPath;
    BshClassManager classManager;
    JFrame frame;
    JInternalFrame iframe;
    JList classlist;
    JList conslist;
    JList mlist;
    JList fieldlist;
    PackageTree ptree;
    JTextArea methodLine;
    JTree tree;
    String[] packagesList;
    String[] classesList;
    Constructor[] consList;
    Method[] methodList;
    Field[] fieldList;
    String selectedPackage;
    Class selectedClass;
    private static final Color LIGHT_BLUE = new Color(245, 245, 255);

    public ClassBrowser() {
        this(BshClassManager.createClassManager(null));
    }

    public ClassBrowser(BshClassManager classManager) {
        super(0, true);
        this.classManager = classManager;
        this.setBorder(null);
        SplitPaneUI ui = this.getUI();
        if (ui instanceof BasicSplitPaneUI) {
            ((BasicSplitPaneUI)ui).getDivider().setBorder(null);
        }
    }

    String[] toSortedStrings(Collection c) {
        ArrayList l = new ArrayList(c);
        String[] sa = l.toArray(new String[0]);
        return StringUtil.bubbleSort(sa);
    }

    void setClist(String packagename) {
        this.selectedPackage = packagename;
        HashSet set = this.classPath.getClassesForPackage(packagename);
        if (set == null) {
            set = new HashSet();
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String cname : set) {
            if (cname.indexOf("$") != -1) continue;
            list.add(BshClassPath.splitClassname(cname)[1]);
        }
        this.classesList = this.toSortedStrings(list);
        this.classlist.setListData(this.classesList);
    }

    String[] parseConstructors(Constructor[] constructors) {
        String[] sa = new String[constructors.length];
        for (int i = 0; i < sa.length; ++i) {
            Constructor con = constructors[i];
            sa[i] = StringUtil.methodString(con.getName(), con.getParameterTypes());
        }
        return sa;
    }

    String[] parseMethods(Method[] methods) {
        String[] sa = new String[methods.length];
        for (int i = 0; i < sa.length; ++i) {
            sa[i] = StringUtil.methodString(methods[i].getName(), methods[i].getParameterTypes());
        }
        return sa;
    }

    String[] parseFields(Field[] fields) {
        String[] sa = new String[fields.length];
        for (int i = 0; i < sa.length; ++i) {
            Field f = fields[i];
            sa[i] = f.getName();
        }
        return sa;
    }

    Constructor[] getPublicConstructors(Constructor[] constructors) {
        Vector<Constructor> v = new Vector<Constructor>();
        for (int i = 0; i < constructors.length; ++i) {
            if (!Modifier.isPublic(constructors[i].getModifiers())) continue;
            v.addElement(constructors[i]);
        }
        Object[] ca = new Constructor[v.size()];
        v.copyInto(ca);
        return ca;
    }

    Method[] getPublicMethods(Method[] methods) {
        Vector<Method> v = new Vector<Method>();
        for (int i = 0; i < methods.length; ++i) {
            if (!Modifier.isPublic(methods[i].getModifiers())) continue;
            v.addElement(methods[i]);
        }
        Object[] ma = new Method[v.size()];
        v.copyInto(ma);
        return ma;
    }

    Field[] getPublicFields(Field[] fields) {
        Vector<Field> v = new Vector<Field>();
        for (int i = 0; i < fields.length; ++i) {
            if (!Modifier.isPublic(fields[i].getModifiers())) continue;
            v.addElement(fields[i]);
        }
        Object[] fa = new Field[v.size()];
        v.copyInto(fa);
        return fa;
    }

    void setConslist(Class clas) {
        if (clas == null) {
            this.conslist.setListData(new Object[0]);
            return;
        }
        this.consList = this.getPublicConstructors(clas.getDeclaredConstructors());
        this.conslist.setListData(this.parseConstructors(this.consList));
    }

    void setMlist(String classname) {
        if (classname == null) {
            this.mlist.setListData(new Object[0]);
            this.setConslist(null);
            this.setClassTree(null);
            return;
        }
        try {
            this.selectedClass = this.selectedPackage.equals("<unpackaged>") ? this.classManager.classForName(classname) : this.classManager.classForName(this.selectedPackage + "." + classname);
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
        if (this.selectedClass == null) {
            System.err.println("class not found: " + classname);
            return;
        }
        this.methodList = this.getPublicMethods(this.selectedClass.getDeclaredMethods());
        this.mlist.setListData(this.parseMethods(this.methodList));
        this.setClassTree(this.selectedClass);
        this.setConslist(this.selectedClass);
        this.setFieldList(this.selectedClass);
    }

    void setFieldList(Class clas) {
        if (clas == null) {
            this.fieldlist.setListData(new Object[0]);
            return;
        }
        this.fieldList = this.getPublicFields(clas.getDeclaredFields());
        this.fieldlist.setListData(this.parseFields(this.fieldList));
    }

    void setMethodLine(Object method) {
        this.methodLine.setText(method == null ? "" : method.toString());
    }

    void setClassTree(Class clas) {
        if (clas == null) {
            this.tree.setModel(null);
            return;
        }
        DefaultMutableTreeNode bottom = null;
        DefaultMutableTreeNode top = null;
        do {
            DefaultMutableTreeNode up = new DefaultMutableTreeNode(clas.toString());
            if (top != null) {
                up.add(top);
            } else {
                bottom = up;
            }
            top = up;
        } while ((clas = clas.getSuperclass()) != null);
        this.tree.setModel(new DefaultTreeModel(top));
        TreeNode tn = bottom.getParent();
        if (tn != null) {
            TreePath tp = new TreePath(((DefaultTreeModel)this.tree.getModel()).getPathToRoot(tn));
            this.tree.expandPath(tp);
        }
    }

    JPanel labeledPane(JComponent comp, String label) {
        JPanel jp = new JPanel(new BorderLayout());
        jp.add("Center", comp);
        jp.add("North", new JLabel(label, 0));
        return jp;
    }

    public void init() throws ClassPathException {
        this.classPath = ((ClassManagerImpl)this.classManager).getClassPath();
        this.classPath.addListener(this);
        Set pset = this.classPath.getPackagesSet();
        this.ptree = new PackageTree(pset);
        this.ptree.addTreeSelectionListener(new TreeSelectionListener(){

            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getPath();
                Object[] oa = tp.getPath();
                StringBuffer selectedPackage = new StringBuffer();
                for (int i = 1; i < oa.length; ++i) {
                    selectedPackage.append(oa[i].toString());
                    if (i + 1 >= oa.length) continue;
                    selectedPackage.append(".");
                }
                ClassBrowser.this.setClist(selectedPackage.toString());
            }
        });
        this.classlist = new JList();
        this.classlist.setBackground(LIGHT_BLUE);
        this.classlist.addListSelectionListener(this);
        this.conslist = new JList();
        this.conslist.addListSelectionListener(this);
        this.mlist = new JList();
        this.mlist.setBackground(LIGHT_BLUE);
        this.mlist.addListSelectionListener(this);
        this.fieldlist = new JList();
        this.fieldlist.addListSelectionListener(this);
        JSplitPane methodConsPane = this.splitPane(0, true, this.labeledPane(new JScrollPane(this.conslist), "Constructors"), this.labeledPane(new JScrollPane(this.mlist), "Methods"));
        JSplitPane rightPane = this.splitPane(0, true, methodConsPane, this.labeledPane(new JScrollPane(this.fieldlist), "Fields"));
        JSplitPane sp = this.splitPane(1, true, this.labeledPane(new JScrollPane(this.classlist), "Classes"), rightPane);
        sp = this.splitPane(1, true, this.labeledPane(new JScrollPane(this.ptree), "Packages"), sp);
        JPanel bottompanel = new JPanel(new BorderLayout());
        this.methodLine = new JTextArea(1, 60);
        this.methodLine.setBackground(LIGHT_BLUE);
        this.methodLine.setEditable(false);
        this.methodLine.setLineWrap(true);
        this.methodLine.setWrapStyleWord(true);
        this.methodLine.setFont(new Font("Monospaced", 1, 14));
        this.methodLine.setMargin(new Insets(5, 5, 5, 5));
        this.methodLine.setBorder(new MatteBorder(1, 0, 1, 0, LIGHT_BLUE.darker().darker()));
        bottompanel.add("North", this.methodLine);
        JPanel p = new JPanel(new BorderLayout());
        this.tree = new JTree();
        this.tree.addTreeSelectionListener(new TreeSelectionListener(){

            public void valueChanged(TreeSelectionEvent e) {
                ClassBrowser.this.driveToClass(e.getPath().getLastPathComponent().toString());
            }
        });
        this.tree.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setClassTree(null);
        p.add("Center", this.tree);
        bottompanel.add("Center", p);
        bottompanel.setPreferredSize(new Dimension(150, 150));
        this.setTopComponent(sp);
        this.setBottomComponent(bottompanel);
    }

    private JSplitPane splitPane(int orientation, boolean redraw, JComponent c1, JComponent c2) {
        JSplitPane sp = new JSplitPane(orientation, redraw, c1, c2);
        sp.setBorder(null);
        SplitPaneUI ui = sp.getUI();
        if (ui instanceof BasicSplitPaneUI) {
            ((BasicSplitPaneUI)ui).getDivider().setBorder(null);
        }
        return sp;
    }

    public static void main(String[] args2) throws Exception {
        ClassBrowser cb = new ClassBrowser();
        cb.init();
        JFrame f = new JFrame("BeanShell Class Browser v1.0");
        f.getContentPane().add("Center", cb);
        cb.setFrame(f);
        f.pack();
        f.setVisible(true);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setFrame(JInternalFrame frame) {
        this.iframe = frame;
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == this.classlist) {
            String methodLineString;
            String classname = (String)this.classlist.getSelectedValue();
            this.setMlist(classname);
            if (classname == null) {
                methodLineString = "Package: " + this.selectedPackage;
            } else {
                String fullClassName = this.selectedPackage.equals("<unpackaged>") ? classname : this.selectedPackage + "." + classname;
                methodLineString = fullClassName + " (from " + this.classPath.getClassSource(fullClassName) + ")";
            }
            this.setMethodLine(methodLineString);
        } else if (e.getSource() == this.mlist) {
            int i = this.mlist.getSelectedIndex();
            if (i == -1) {
                this.setMethodLine(null);
            } else {
                this.setMethodLine(this.methodList[i]);
            }
        } else if (e.getSource() == this.conslist) {
            int i = this.conslist.getSelectedIndex();
            if (i == -1) {
                this.setMethodLine(null);
            } else {
                this.setMethodLine(this.consList[i]);
            }
        } else if (e.getSource() == this.fieldlist) {
            int i = this.fieldlist.getSelectedIndex();
            if (i == -1) {
                this.setMethodLine(null);
            } else {
                this.setMethodLine(this.fieldList[i]);
            }
        }
    }

    public void driveToClass(String classname) {
        String[] sa = BshClassPath.splitClassname(classname);
        String packn = sa[0];
        String classn = sa[1];
        if (this.classPath.getClassesForPackage(packn).size() == 0) {
            return;
        }
        this.ptree.setSelectedPackage(packn);
        for (int i = 0; i < this.classesList.length; ++i) {
            if (!this.classesList[i].equals(classn)) continue;
            this.classlist.setSelectedIndex(i);
            this.classlist.ensureIndexIsVisible(i);
            break;
        }
    }

    public void toFront() {
        if (this.frame != null) {
            this.frame.toFront();
        } else if (this.iframe != null) {
            this.iframe.toFront();
        }
    }

    public void classPathChanged() {
        Set pset = this.classPath.getPackagesSet();
        this.ptree.setPackages(pset);
        this.setClist(null);
    }

    class PackageTree
    extends JTree {
        TreeNode root;
        DefaultTreeModel treeModel;
        Map nodeForPackage = new HashMap();

        PackageTree(Collection packages) {
            this.setPackages(packages);
            this.setRootVisible(false);
            this.setShowsRootHandles(true);
            this.setExpandsSelectedPaths(true);
        }

        public void setPackages(Collection packages) {
            this.treeModel = this.makeTreeModel(packages);
            this.setModel(this.treeModel);
        }

        DefaultTreeModel makeTreeModel(Collection packages) {
            HashMap packageTree = new HashMap();
            for (String pack : packages) {
                String[] sa = StringUtil.split(pack, ".");
                HashMap level = packageTree;
                for (int i = 0; i < sa.length; ++i) {
                    String name = sa[i];
                    HashMap map = (HashMap)level.get(name);
                    if (map == null) {
                        map = new HashMap();
                        level.put(name, map);
                    }
                    level = map;
                }
            }
            this.root = this.makeNode(packageTree, "root");
            this.mapNodes(this.root);
            return new DefaultTreeModel(this.root);
        }

        MutableTreeNode makeNode(Map map, String nodeName) {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(nodeName);
            for (String name : map.keySet()) {
                Map val = (Map)map.get(name);
                if (val.size() == 0) {
                    DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(name);
                    root.add(leaf);
                    continue;
                }
                MutableTreeNode node = this.makeNode(val, name);
                root.add(node);
            }
            return root;
        }

        void mapNodes(TreeNode node) {
            this.addNodeMap(node);
            Enumeration e = node.children();
            while (e.hasMoreElements()) {
                TreeNode tn = (TreeNode)e.nextElement();
                this.mapNodes(tn);
            }
        }

        void addNodeMap(TreeNode node) {
            StringBuffer sb = new StringBuffer();
            for (TreeNode tn = node; tn != this.root; tn = tn.getParent()) {
                sb.insert(0, tn.toString());
                if (tn.getParent() == this.root) continue;
                sb.insert(0, ".");
            }
            String pack = sb.toString();
            this.nodeForPackage.put(pack, node);
        }

        void setSelectedPackage(String pack) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.nodeForPackage.get(pack);
            if (node == null) {
                return;
            }
            TreePath tp = new TreePath(this.treeModel.getPathToRoot(node));
            this.setSelectionPath(tp);
            ClassBrowser.this.setClist(pack);
            this.scrollPathToVisible(tp);
        }
    }
}

