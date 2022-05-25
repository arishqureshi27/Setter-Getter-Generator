package com.thinking.machines.util;
import java.lang.reflect.*;
import java.io.*;

public class SetterGetterGenerator
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Usage : java classpath path_to_jar_file;. com.thinking.machines.util.setterGetterGenerator class_name");
return;
}
String className=gg[0];
try{
Class c=Class.forName(className);
Field fields[]=c.getDeclaredFields();
Field field;
TMList<String> list=new TMArrayList<String>();
String setterName;
String getterName;
String tmp;
String fieldName;
String line;
Class fieldType;

for(int e=0; e<fields.length; e++)
{
field=fields[e];
fieldName=field.getName();
fieldType=field.getType();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1);
}
else
{
tmp=fieldName;
}
setterName="set"+tmp;
getterName="get"+tmp;
line="public void "+setterName+"("+fieldType.getName()+" "+fieldName+")";
list.add(line);
list.add("{");
line="this."+fieldName+"="+fieldName+";";
list.add(line);
list.add("}");
line="public "+fieldType.getName()+" "+getterName+"()";
list.add(line);
list.add("{");
line="return this."+fieldName+";";
list.add(line);
list.add("}");
}
File file=new File("tmp.tmp");
if(file.exists())file.delete();
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
TMIterator<String> iterator=list.iterator();
while(iterator.hasNext()){
line=iterator.next();
randomAccessFile.writeBytes(line+"\r\n");
}
randomAccessFile.close();
System.out.println("setters/getters for : "+c.getName()+"generated in the file named tmp.tmp");
}
catch(ClassNotFoundException cnfe)
{
System.out.println("Unable to load class, Classpath missing");
}
catch(IOException ioe)
{
System.out.println(ioe.getMessage());
}
}
}