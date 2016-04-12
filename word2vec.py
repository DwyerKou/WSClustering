# -*- coding: utf-8 -*-
# 这个用来生成词向量模型

import os
import gensim
import logging
import gensim.models.word2vec
logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)
sentences = []
# train word2vec on the two sentences


def proc(file_path):
    print(file_path)
    sen = []
    for word in open(file_path):
        length = len(word)
        if word.endswith('\r\n'):
            word = word[0:length-2]
        elif word.endswith('\n'):
            word = word[0:length-1]
        sen.append(word)
    sentences.append(sen)


def dir_read(root):
    list_dirs = os.walk(root)
    for root, dirs, files in list_dirs:
        for f in files:
            proc(os.path.join(root, f))


def Word2Vec(file_path):
    dir_read(file_path)
    model = gensim.models.Word2Vec(sentences, size=100, min_count=1, workers=1)
    model.save('ServiceNameVec.txt')
