# -*- coding: utf-8 -*-
# 这里用来对原始的词进行词干提取，去除停用词以及通用词

import os
from nltk.stem.porter import *

stemmer = PorterStemmer()
s = set()
remove_word = set()
remove_word.add('servic')  # 加这个将最常见的这个词去掉，预计可以提高精度
remove_word.add('ws')
remove_word.add('web')
remove_word.add('wsdl')
for word in open("stop-words_english_5_en.txt"):
    length = len(word)
    if word.endswith('\r\n'):
        word = word[0:length-2]
    elif word.endswith('\n'):
        word = word[0:length-1]
    print(word)
    remove_word.add(word)


# 该函数用来单独对某一个文件进行词干提取
def stem(file):
    s = set(remove_word)
    print(file)
    outfile = open(file.replace('serviceName', 'serviceNameStemmed'), 'w')
    for word in open(file):
        length = len(word)
        if word.endswith('\r\n'):
            word = word[0:length-2]
        elif word.endswith('\n'):
            word = word[0:length-1]
        new_word = stemmer.stem(word)
        if new_word not in s:  # # 过滤停用词、通用词，同时去除重复词
            s.add(new_word)
            outfile.write(new_word.lower() + "\n")
    outfile.close()


# 该函数一次性读出所有文件
def dir_read(root):
    i = 0
    list_dirs = os.walk(root)
    for root, dirs, files in list_dirs:
        for f in files:
            stem(os.path.join(root, f))
            i += 1
    return i


def WordProc(file_name):
    return dir_read(file_name)

