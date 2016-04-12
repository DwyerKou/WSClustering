package dwyer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

public class WSDL {
	public static void main(String[] args) throws  FileNotFoundException, Exception {
		//路径格式："E:/dwyer/cluster/238/all/"
		if (args.length == 0) {  
            System.out.println("您调用main方法时没有指定任何参数！");  
            return;  
        }
		System.out.println("您调用main方法时指定的参数包括：");  
        for (int i = 0; i < args.length; i++) {  
            System.out.println("参数" + (i + 1) + "的值为：" + args[i]);  
        }  
		File file = new File(args[0]);   
		File[] array = file.listFiles();
		DwyerIO dwyerIO = new DwyerIO();
		//一个一个处理
		for(int i=0;i<array.length;i++){   
			String filePath = array[i].getPath();
			WSDLParser wsdlParser = new WSDLParser();
			if(wsdlParser.Init(filePath)){
//				//获取整个服务名
//				Set<String> WholeServiceName = wsdlParser.getWholeSeviceName();
//				String outServiceNameFile = filePath.replaceAll("all", "WholeServiceName").replaceAll(".xml", ".txt");
//				dwyerIO.writeSet(outServiceNameFile, WholeServiceName);
				
//				//获取服务名与operation
//				Set<String> serviceName = wsdlParser.getSeviceName();
//				Set<String> operationNameSet = wsdlParser.getOperationName();
//				serviceName.addAll(operationNameSet);
//				String outServiceNameFile = filePath.replaceAll("all", "AllName").replaceAll(".xml", ".txt");
//				dwyerIO.writeSet(outServiceNameFile, serviceName);
				
				//获取服务名切开后的集合
				Set<String> seviceNameSet = wsdlParser.getSeviceName();
				String outServiceNameFile = filePath.replaceAll("all", "serviceName").replaceAll(".xml", ".txt");
				dwyerIO.writeSet(outServiceNameFile, seviceNameSet);
				
//				//获取binding名
//				Set<String> bindingNameSet = wsdlParser.getBindingName();
//				String outBindingNameFile = filePath.replaceAll("185个标注的服务", "binding名提取/");
//				dwyerIO.writeSet(outBindingNameFile, bindingNameSet);
//				//获取portType名
//				Set<String> portTypeNameSet = wsdlParser.getPortTypeName();
//				String outportTypeNameFile = filePath.replaceAll("185个标注的服务", "portType名提取/");
//				dwyerIO.writeSet(outportTypeNameFile, portTypeNameSet);
//				//获取message名
//				Set<String> messageNameSet = wsdlParser.getMessageName();
//				String outMessageNameFile = filePath.replaceAll("185个标注的服务", "message名提取/");
//				dwyerIO.writeSet(outMessageNameFile, messageNameSet);
//				//获取operation名
//				Set<String> operationNameSet = wsdlParser.getOperationName();
//				String outOperationNameFile = filePath.replaceAll("185个标注的服务", "operation名提取/");
//				dwyerIO.writeSet(outOperationNameFile, operationNameSet);
			}
		}
	}
}