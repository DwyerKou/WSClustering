package dwyer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

public class WSDL {
	public static void main(String[] args) throws  FileNotFoundException, Exception {
		//·����ʽ��"E:/dwyer/cluster/238/all/"
		if (args.length == 0) {  
            System.out.println("������main����ʱû��ָ���κβ�����");  
            return;  
        }
		System.out.println("������main����ʱָ���Ĳ���������");  
        for (int i = 0; i < args.length; i++) {  
            System.out.println("����" + (i + 1) + "��ֵΪ��" + args[i]);  
        }  
		File file = new File(args[0]);   
		File[] array = file.listFiles();
		DwyerIO dwyerIO = new DwyerIO();
		//һ��һ������
		for(int i=0;i<array.length;i++){   
			String filePath = array[i].getPath();
			WSDLParser wsdlParser = new WSDLParser();
			if(wsdlParser.Init(filePath)){
//				//��ȡ����������
//				Set<String> WholeServiceName = wsdlParser.getWholeSeviceName();
//				String outServiceNameFile = filePath.replaceAll("all", "WholeServiceName").replaceAll(".xml", ".txt");
//				dwyerIO.writeSet(outServiceNameFile, WholeServiceName);
				
//				//��ȡ��������operation
//				Set<String> serviceName = wsdlParser.getSeviceName();
//				Set<String> operationNameSet = wsdlParser.getOperationName();
//				serviceName.addAll(operationNameSet);
//				String outServiceNameFile = filePath.replaceAll("all", "AllName").replaceAll(".xml", ".txt");
//				dwyerIO.writeSet(outServiceNameFile, serviceName);
				
				//��ȡ�������п���ļ���
				Set<String> seviceNameSet = wsdlParser.getSeviceName();
				String outServiceNameFile = filePath.replaceAll("all", "serviceName").replaceAll(".xml", ".txt");
				dwyerIO.writeSet(outServiceNameFile, seviceNameSet);
				
//				//��ȡbinding��
//				Set<String> bindingNameSet = wsdlParser.getBindingName();
//				String outBindingNameFile = filePath.replaceAll("185����ע�ķ���", "binding����ȡ/");
//				dwyerIO.writeSet(outBindingNameFile, bindingNameSet);
//				//��ȡportType��
//				Set<String> portTypeNameSet = wsdlParser.getPortTypeName();
//				String outportTypeNameFile = filePath.replaceAll("185����ע�ķ���", "portType����ȡ/");
//				dwyerIO.writeSet(outportTypeNameFile, portTypeNameSet);
//				//��ȡmessage��
//				Set<String> messageNameSet = wsdlParser.getMessageName();
//				String outMessageNameFile = filePath.replaceAll("185����ע�ķ���", "message����ȡ/");
//				dwyerIO.writeSet(outMessageNameFile, messageNameSet);
//				//��ȡoperation��
//				Set<String> operationNameSet = wsdlParser.getOperationName();
//				String outOperationNameFile = filePath.replaceAll("185����ע�ķ���", "operation����ȡ/");
//				dwyerIO.writeSet(outOperationNameFile, operationNameSet);
			}
		}
	}
}