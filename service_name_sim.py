# -*- coding: utf-8 -*-
# 这个用来对预处理好的文档进行基于词向量的相似度计算，并生成相似度矩阵

import gensim
import logging
import gensim.models.word2vec

logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)


# function to calculate similarity of to word
def sim_words(word_list1, word_list2, model):
    sim = 0
    count1 = 0
    for word1 in word_list1:
        count1 += 1
        count2 = 0
        if word1.endswith('\r\n'):
            word1 = word1[0:len(word1)-2]
        elif word1.endswith('\n'):
            word1 = word1[0:len(word1)-1]
        for word2 in word_list2:
            if word2.endswith('\r\n'):
                word2 = word2[0:len(word2)-2]
            elif word2.endswith('\n'):
                word2 = word2[0:len(word2)-1]
            count2 += 1
            sim += model.similarity(word1, word2)
    return sim / (count1 * count2)


def service_name_sim(path, service_num):
    model = gensim.models.Word2Vec.load('ServiceNameVec.txt')
    file_list = []
    for row in range(1, service_num + 1):
        word_list = []
        file_path = path + '/' + str(row) + '.txt'
        for word in open(file_path):
            if word.endswith('\r\n'):
                word = word[0:len(word)-2]
            elif word.endswith('\n'):
                word = word[0:len(word)-1]
            try:
                print(model[word])
            except:
                continue
            word_list.append(word)
        file_list.append(word_list)


    outfile = open('ServiceNameSim.txt', 'w')
    sim_arr = []
    for row in range(0, service_num):
        sim_row = []
        for col in range(0, service_num):
            temp = sim_words(file_list[row], file_list[col], model)
            outfile.write(str(temp) + ' ')
            sim_row.append(temp)
        outfile.write('\n')
        sim_arr.append(sim_row)
        print(row)
    outfile.close()


