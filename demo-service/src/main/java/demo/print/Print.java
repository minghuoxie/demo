package demo.print;

public class Print {
    /**
     * 打印函数
     * */
    public static void testPrint(Object str){
        System.out.println(str);
    }
    /**
     * 打印数组
     * */
    public static void testPrint(Object[] arrs,int num){
        for(int i=0;i<arrs.length;i++){
            System.out.print(arrs[i]+" ");
            if((i+1)%num==0){
                testPrint("\n");
            }
        }
    }

    /****************************辅助方法******************************/
    private static int helpPrint(int index,String str,StringBuilder strBuilder,Object[] arrs){
        int resultInt=index-1;
        String strInt="";
        for(;index<str.length();index++){
            char ch=str.charAt(index);
            if(ch>='0'&&ch<='9'){
                strInt+=ch;
                resultInt++;
            }else{
                break;
            }
        }
        if(strInt.length()>0){
            int arrInt=Integer.parseInt(strInt)-1;
            if(arrInt<arrs.length){
                strBuilder.append(arrs[arrInt]);
            }
        }else{
            strBuilder.append("?");
        }
        return resultInt;
    }
    /**
     * 打印：
     * */
    public static void testPrint(String str,Object[] arrs){
        if(null==str|str.length()<=0){
            return;
        }
        StringBuilder strBuilder=new StringBuilder();
        if(null!=arrs&&arrs.length>0){
            for(int i=0;i<str.length();i++){
                char ch=str.charAt(i);
                if(ch=='?'){
                    i=helpPrint(i+1,str,strBuilder,arrs);
                }else{
                    strBuilder.append(ch);
                }
            }
        }else{
            strBuilder.append(str);
        }
        testPrint(strBuilder.toString());
    }
    /**********************************************************/
}
