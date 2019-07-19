package demo.htlp;

import org.jsoup.nodes.Element;

public class ConnHelp {

    //根据标签名称和属性名称获取文本
    public static String getTextByTagAndAttr(String tagName, String attrName, String attrVal,char type,String mAttrName, Element root){
        if(root!=null){
            if(tagName.equals(root.tagName())&&attrVal.equals(root.attributes().get(attrName))){
                if(type=='C'){
                    return root.text();
                }else if(type=='K'){
                    return root.attributes().get(mAttrName);
                }

            }else if(root.children().size()>0){
                for(int i=0;i<root.children().size();i++){
                    String txt=getTextByTagAndAttr(tagName,attrName,attrVal,type,mAttrName,root.children().get(i));
                    if(!txt.equals("")){
                        return txt;
                    }
                }
            }
        }
        return "";
    }

    //根据标签获取对应的属性值和节点内容
    public static String getAttrValByTag(String tagName,String muName,char type,Element root){
        if(root!=null){
            if(tagName.equals(root.tagName())){
                if(type=='K'){
                    return root.attributes().get(muName);
                }else if(type=='C'){
                    return root.text();
                }
            }else if(root.childNodes().size()>0){
                for(int i=0;i<root.children().size();i++){
                    String txt=getAttrValByTag(tagName,muName,type,root.children().get(i));
                    if(!txt.equals("")){
                        return txt;
                    }
                }
            }
        }
        return "";
    }
    //根据标签以及父节点获取对应的属性值和节点内容
    public static String getAttrValByTag(String pTagName,String pAttrName,String pAttrVal,String tagName,String muName,char type,Element root){
        if(root!=null){
            if(pTagName.equals(root.tagName())&&pAttrVal.equals(root.attributes().get(pAttrName))){
                return getAttrValByTag(tagName,muName,type,root);
            }else if(root.children().size()>0){
                for(int i=0;i<root.children().size();i++) {
                    String txt = getAttrValByTag(pTagName, pAttrName, pAttrVal, tagName, muName, type,root.children().get(i));
                    if(!txt.equals("")){
                        return txt;
                    }
                }
            }
        }
        return "";
    }
}
