# -*- coding: utf-8 -*-

from Tkinter import *
import subprocess
import WordProc
import word2vec
import spectral_clustering
import service_name_sim
import os
import time

# 用户初始化界面
def callback():
    wsdl_path = e1.get()
    k = e2.get()
    start = time.time()
    print(start)
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
    end = time.time()
    lb0.insert(END, "服务数量： " + str(service_num))
    lb0.insert(END, "聚类数量： " + str(k))
    lb0.insert(END, "消耗时间： " + str(end - start) + "秒")
    for j in range(0, int(k)):
        lb0.insert(END, "第" + str(j + 1) + "个聚类中服务的数量： " + str(clustering_result.count(j)))

    lb1.insert(END, "服务       所在聚类")
    for i in range(0, service_num):
        if i < 9:
            lb1.insert(END, str(i+1) + "              " + str(clustering_result[i] + 1))
        if i >= 9:
            lb1.insert(END, str(i+1) + "            " + str(clustering_result[i] + 1))

    for i in range(0, int(k)):
        lb2.insert(END, "聚类" + str(i + 1) + "中的服务")
        re = [j for j, a in enumerate(clustering_result) if a == i]
        for k in range(0, len(re)):
            lb2.insert(END, "  " + str(re[k] + 1))


# 用户UI
master = Tk()
master.title("服务聚类模块")
Label(master, text="WSDL路径:").grid(row=1, column=0, sticky=E)
Label(master, text="聚类个数:").grid(row=1, column=2, sticky=W)
e1 = Entry(master, width=25)
e2 = Entry(master, width=5)
e1.grid(row=1, column=1, sticky=W)
e2.grid(row=1, column=3, sticky=W)
button1 = Button(master, text='开始聚类', command=callback)
button1.grid(row=1, column=4)
e1.focus_set()

Label(master, text="统计结果：").grid(row=2, column=0)
lb0 = Listbox(master, width=25, height=15)
lb0.grid(row=3, column=1, columnspan=2, sticky=W)

Label(master, text="服务所在聚类：").grid(row=2, column=2)
lb1 = Listbox(master, width=15, height=15)
lb1.grid(row=3, column=3, columnspan=2, sticky=W)

Label(master, text="聚类中的服务：").grid(row=2, column=5)
lb2 = Listbox(master, width=15, height=15)
lb2.grid(row=3, column=6, columnspan=2, sticky=W)
mainloop()



