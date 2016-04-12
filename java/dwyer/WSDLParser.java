package dwyer;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//����һ���ļ��� ����һ��
public class WSDLParser {
	//path���԰�һ���ļ�·��
	private String path;
	private SAXReader saxReader;
	private Document document;
	private Element root;
	private WordProc wordProc;
	//���캯��
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
			System.out.println(path + "������");
			return false;
		}
	}

	//��ȡ�������ļ���
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

	//��ȡ���������������ǶϿ���
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


	//��ȡbinding���ļ���
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

	//��ȡporttype���ļ���
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

	//��ȡmessage���ļ���
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

	//��ȡmessage���ļ���
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

	//�����ж��binding����map��һ��key��Ӧһ��binding��value���Ǹ�binding�ľ�������
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
