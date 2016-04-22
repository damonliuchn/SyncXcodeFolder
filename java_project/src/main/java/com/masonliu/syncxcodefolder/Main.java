package com.masonliu.syncxcodefolder;

import com.sap.prd.mobile.ios.mios.xcodeprojreader.Dict;
import com.sap.prd.mobile.ios.mios.xcodeprojreader.Plist;
import com.sap.prd.mobile.ios.mios.xcodeprojreader.jaxb.JAXBPlistParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumeng on 16/4/17.
 */
public class Main {
    public static String PROJ_PATH = "/Users/admin/Project/XC_WORK/OptimusPrime/YouCaiRestaurant.xcodeproj";

    static String pbxFilePath;
    static List<PBXGroup> list = new ArrayList<>();
    static String fileRefSection;
    static String groupSection;
    static String all;

    public static void main(String[] args) {
        if (args != null && args.length == 1) {
            PROJ_PATH = args[0];
        }
        pbxFilePath = PROJ_PATH + "/project.pbxproj";
        //解析section
        all = StringUtil.file2String(new File(pbxFilePath), null);
        fileRefSection = StringUtil.getPatternStr(all, "Begin\\sPBXFileReference\\ssection.*?End\\sPBXFileReference\\ssection", 0, 0);
        groupSection = StringUtil.getPatternStr(all, "Begin\\sPBXGroup\\ssection.*?End\\sPBXGroup\\ssection", 0, 0);
        JAXBPlistParser jaxbPlistParser = new JAXBPlistParser();
        try {
            jaxbPlistParser.convert(pbxFilePath, PROJ_PATH + "project.xml");
            Plist plist = jaxbPlistParser.load(PROJ_PATH + "project.xml");
            Dict dict = plist.getDict().getDict("objects");
            for (Dict.Entry<String, Object> entry : dict.entrySet()) {
                Dict value = (Dict) entry.getValue();
                String isa = value.getString("isa");
                if (isa.equals("PBXGroup")) {
                    PBXGroup pbxGroup = new PBXGroup();
                    pbxGroup.setKey(entry.getKey());
                    pbxGroup.setIsa(isa);
                    pbxGroup.setName(value.getString("name"));
                    pbxGroup.setPath(value.getString("path"));
                    pbxGroup.setSourceTree(value.getString("sourceTree"));
                    pbxGroup.setChildren(value.getArray("children"));
                    list.add(pbxGroup);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //找根
        for (PBXGroup p : list) {
            if (p.getPath() == null && p.getName() == null) {
                for (Object key : p.getChildren()) {
                    PBXGroup p2 = getPBXGroupByKey((String) key);
                    if (p2.getPath() != null && !p2.getPath().contains("Tests")) {
                        System.out.println(p2.getKey());
                        modifyPath(p2.getPath(), "", p2);
                    }
                }
            }
        }
    }

    public static void modifyPath(String rootPath, String newPath, PBXGroup parentGroup) {
        for (Object key : parentGroup.getChildren()) {
            PBXGroup childGroup = getPBXGroupByKey((String) key);
            if (childGroup == null) {
                //文件 修改成 去掉 name, 然后 path 就等于filename
                String item = StringUtil.getPatternStr(fileRefSection, key + ".*?}", 0, 0);
                //if (oldPath.contains("/")) {
                String fileName = StringUtil.getPatternStr(item, "\\*.*?\\*", 2, 2);
                String fileNameTmp = fileName;
                //去除name
                String itemTmp = item.replaceAll("name.*?;", "");
                //替换path
                if (fileName.contains("+") || fileName.contains(" ")) {
                    fileNameTmp = "\"" + fileName + "\"";
                }
                itemTmp = itemTmp.replaceAll("path.*?;", "path = " + fileNameTmp + ";");
                all = all.replace(item, itemTmp);
                StringUtil.string2File(all, pbxFilePath);

                //移动文件
                String oldAllPath = FileUtil.findFile(new File(PROJ_PATH).getParentFile().getAbsolutePath() + "/" + rootPath, fileName, true);
                String newAllPath = new File(PROJ_PATH).getParentFile().getAbsolutePath() + "/" + rootPath + "/" + newPath + "/" + fileName;
                if (newPath.equals("")) {
                    newAllPath = new File(PROJ_PATH).getParentFile().getAbsolutePath() + "/" + rootPath + "/" + fileName;
                }
                if (oldAllPath == null || oldAllPath.equals(newAllPath)) {
                    System.out.println("!!!!!oldAllPath == null or oldAllPath.equals(newAllPath) :" + newAllPath);
                } else {
                    //移动文件
                    boolean a = FileUtil.move(oldAllPath, newAllPath);
                    if (!a) {
                        System.out.println("!!!!!move file error :" + oldAllPath + "-" + newAllPath);
                    } else {
                        if (new File(oldAllPath).getParentFile().listFiles().length == 0) {
                            new File(oldAllPath).getParentFile().delete();
                        }
                        System.out.println(oldAllPath + "---" + newAllPath);
                    }
                }
                // }
            } else {
                //文件夹,修改文件夹的去掉 name, path 就等于name
                String item = StringUtil.getPatternStr(groupSection, key + ".*?}", 0, 0);
                //替换path
                String folder = StringUtil.getPatternStr(item, "\\*.*?\\*", 2, 2);
                item = StringUtil.getPatternStr(groupSection, key + "\\s\\/\\*\\s" + folder + "\\s\\*\\/\\s=\\s.*?}", 0, 0);
                //去除name
                String itemTmp = item.replaceAll("name.*?;", "");
                //替换path
                if (folder.contains(" ")) {
                    folder = "\"" + folder + "\"";
                }
                String hasNameAttr = StringUtil.getPatternStr(itemTmp, "path.*?;", 0, 0);
                if (hasNameAttr == null) {
                    itemTmp = itemTmp.replaceAll("sourceTree\\s=", "path = xx;\n\t\t\tsourceTree =");
                }
                itemTmp = itemTmp.replaceAll("path.*?;", "path = " + folder + ";");
                itemTmp = itemTmp.replaceAll("sourceTree.*?;", "sourceTree = \"<group>\";");
                //System.out.println(key + "\n" + item + "\n" + itemTmp + "\n------------------------------");
                all = all.replace(item, itemTmp);
                StringUtil.string2File(all, pbxFilePath);


                String newPathTmp = newPath + "/" + folder;
                if (newPathTmp.startsWith("/")) {
                    newPathTmp = newPathTmp.substring(1, newPathTmp.length());
                }
                modifyPath(rootPath, newPathTmp, childGroup);
            }
        }
    }

    public static PBXGroup getPBXGroupByKey(String key) {
        for (PBXGroup p : list) {
            if (key.equals(p.getKey())) {
                return p;
            }
        }
        return null;
    }

}
