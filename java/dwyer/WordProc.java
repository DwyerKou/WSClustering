package dwyer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//����������������ʺϳɵĳ�����
public class WordProc {
	//�����������ȥ�������
	Set<String> proc(String line){
		int begin = 0;//��ĸ��ʼλ��
		Set<String> s = new HashSet<String>();
		while(!Character.isLetter(line.charAt(begin)) && begin < line.length())
			begin++;
		for(int i=begin + 1;i<line.length();++i){
			if(!Character.isLetter(line.charAt(i))) {
				String longWord = line.substring(begin, i);
				s.addAll(caseProc(longWord));
				begin = i + 1;
				while(begin < line.length() && !Character.isLetter(line.charAt(begin)) )
					begin++;
				if(begin >= line.length()) break;
				i = begin;
			}	
		}
		if(begin < line.length()) 
			s.addAll(caseProc(line.substring(begin)));
		return s;
	}

	//��������������ִ�Сд
	Set<String> caseProc(String word){
		Set<String> s = new HashSet<String>();
		if (word.isEmpty()) return s;;
		
		//����˼·���ҵ�ÿ����дתСд��Сдת��д��λ��!!!��¼��һ��vector��
		ArrayList<Integer> allPos = new ArrayList<Integer>();
		allPos.add(0);
		for (int i = 1;i < word.length() - 1;++i) {
			//��дתСд��Сдת��д��λ�ö�Ҫ����
			if (Character.isUpperCase(word.charAt(i)) && Character.isLowerCase(word.charAt(i + 1)))
				allPos.add(i);
			if (Character.isLowerCase(word.charAt(i)) && Character.isUpperCase(word.charAt(i + 1))){
				allPos.add(i + 1);
				i++;
			}
		}
		allPos.add(word.length());

		for(int i = 0;i<allPos.size() - 1;i++){
			StringBuffer sb = new StringBuffer();
			String temp = word.substring(allPos.get(i), allPos.get(i + 1));
			for(int j=0;j<temp.length();++j){
				if(Character.isUpperCase(temp.charAt(j)))
					sb.append(Character.toLowerCase(temp.charAt(j)));
				else
					sb.append(temp.charAt(j));
			}
			s.add(sb.toString());
		}
		return s;
	}
}
