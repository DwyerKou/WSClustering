package dwyer;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//基于一个文件名 解析一切
public class WSDLParser {
	//path可以绑定一个文件路径
	private String path;
	private SAXReader saxReader;
	private Document document;
	private Element root;
	private WordProc wordProc;
	//构造函数
	WSDLParser(){}

	Boolean Init(String path){
		try{
			this.path = path;
			saxReader = new SAXReader();
			document = saxReader.read(new File(this.path));
			root = document.getRootElement();
			wordProc = new WordProc();
			return true;
		}catch(Exception e){
			System.out.println(path + "出错啦");
			return false;
		}
	}

	//获取服务名的集合
	@SuppressWarnings("unchecked")
	Set<String> getSeviceName(){
		Set<String> s = new HashSet<String>();
		Iterator<Element> it = root.elements("service").iterator();
		while(it.hasNext()){  
			Element ele = it.next();  
			String name = ele.attributeValue("name");
			s.addAll(wordProc.proc(name));
		}
		return s;
	}

	//获取整个服务名而不是断开的
	@SuppressWarnings("unchecked")
	Set<String> getWholeSeviceName(){
		Set<String> s = new HashSet<String>();
		Iterator<Element> it = root.elements("service").iterator();
		while(it.hasNext()){  
			Element ele = it.next();  
			String name = ele.attributeValue("name");
			s.add(name);
		}
		return s;
	}


	//获取binding名的集合
	@SuppressWarnings("unchecked")
	Set<String> getBindingName(){
		Set<String> s = new HashSet<String>();
		Iterator<Element> it = root.elements("binding").iterator();
		while(it.hasNext()){  
			Element ele = it.next();  
			String name = ele.attributeValue("name");
			s.addAll(wordProc.proc(name));
		}
		return s;
	}

	//获取porttype名的集合
	@SuppressWarnings("unchecked")
	Set<String> getPortTypeName(){
		Set<String> s = new HashSet<String>();
		Iterator<Element> it = root.elements("portType").iterator();
		while(it.hasNext()){  
			Element ele = it.next();  
			String name = ele.attributeValue("name");
			s.addAll(wordProc.proc(name));
		}
		return s;
	}

	//获取message名的集合
	@SuppressWarnings("unchecked")
	Set<String> getMessageName(){
		Set<String> s = new HashSet<String>();
		Iterator<Element> it = root.elements("message").iterator();
		while(it.hasNext()){  
			Element ele = it.next();  
			String name = ele.attributeValue("name");
			s.addAll(wordProc.proc(name));
		}
		return s;
	}

	//获取message名的集合
	@SuppressWarnings("unchecked")
	Set<String> getOperationName(){
		Set<String> s = new HashSet<String>();
		Iterator<Element> it = root.elements("binding").iterator();
		while(it.hasNext()){  
			Element ele = it.next();  
			Iterator<Element> it2 = ele.elements("operation").iterator();
			while(it2.hasNext()){
				Element operationEle = it2.next();
				String name = operationEle.attributeValue("name");
				s.addAll(wordProc.proc(name));
			}
		}
		return s;
	}

	//可能有多个binding，用map，一个key对应一个binding，value就是该binding的具体描述
	int getBindingNum(){
		return root.elements("binding").size();
	}

	int getPortTypeNum(){
		return root.elements("portType").size();
	}

	int getMessageNum(){
		return root.elements("message").size();
	}

	@SuppressWarnings("unchecked")
	int getOperationNum(){
		Iterator<Element> it = root.elements("binding").iterator();
		int count = 0;
		while(it.hasNext()){
			Element ele = it.next();  
			count += ele.elements("operation").size();
		}
		return count;
	}
}
