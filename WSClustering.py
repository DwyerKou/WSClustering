# -*- coding: utf-8 -*-

from Tkinter import *
import subprocess
import WordProc
import word2vec
import spectral_clustering
import service_name_sim
import os

# 用户初始化界面
def callback():
    wsdl_path = e1.get()
    k = e2.get()

    # 建立目录
    wsdl_father_path = wsdl_path[0: wsdl_path.rfind('/')]
    service_name_path = wsdl_father_path + '/serviceName'
    service_name_stemmed_path = wsdl_father_path + '/serviceNameStemmed'
    if not os.path.exists(service_name_path):
        os.mkdir(service_name_path)
    if not os.path.exists(service_name_stemmed_path):
        os.mkdir(service_name_stemmed_path)

    subprocess.call(['java', '-jar', 'ServiceNameParsing.jar', wsdl_path])
    service_num = WordProc.WordProc(service_name_path)
    word2vec.Word2Vec(service_name_stemmed_path)
    service_name_sim.service_name_sim(service_name_stemmed_path, service_num)
    clustering_result = spectral_clustering.spectral_clustering(k, service_num)
    print(clustering_result)
    lb.insert(END, "服务       类")
    for i in range(0, service_num):
        lb.insert(END, str(i+1) + "              " + str(clustering_result[i]))


# 用户UI
master = Tk()
master.title("服务聚类系统")
Label(master, text="初始化：").grid(row=0, column=0, sticky=W)
Label(master, text="WSDL路径:").grid(row=1, column=1, sticky=W)
Label(master, text="聚类个数:").grid(row=2, column=1, sticky=W)
e1 = Entry(master, width=50)
e2 = Entry(master, width=5)
e1.grid(row=1, column=2, sticky=W)
e2.grid(row=2, column=2, sticky=W)
button1 = Button(master, text='开始聚类', command=callback)
button1.grid(row=3, column=2)
e1.focus_set()

Label(master, text="聚类结果：").grid(row=0, column=3, sticky=W)
lb = Listbox(master, width=50, height=10)
lb.grid(row=1, column=4, rowspan=3)

mainloop()



