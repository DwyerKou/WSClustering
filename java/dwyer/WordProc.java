package dwyer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//这个用来处理几个单词合成的长单词
public class WordProc {
	//这个方法用来去特殊符号
	Set<String> proc(String line){
		int begin = 0;//字母开始位置
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

	//这个方法用来区分大小写
	Set<String> caseProc(String word){
		Set<String> s = new HashSet<String>();
		if (word.isEmpty()) return s;;
		
		//基本思路：找到每个大写转小写与小写转大写的位置!!!记录在一个vector中
		ArrayList<Integer> allPos = new ArrayList<Integer>();
		allPos.add(0);
		for (int i = 1;i < word.length() - 1;++i) {
			//大写转小写，小写转大写的位置都要考虑
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
