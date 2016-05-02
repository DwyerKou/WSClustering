# -*- coding: utf-8 -*-
# 对已经得到的相似度矩阵进行谱聚类

from numpy import array
from sklearn import cluster


def spectral_clustering(k, service_num):
    sim_mat = []  # 相似度矩阵
    file_name = "serviceNameSim.txt"
    fr = open(file_name)

    # 读入相似度矩阵
    for line in fr.readlines():
        cur_line = line.strip().split(' ')
        flt_line = map(float, cur_line)
        sim_mat.append(flt_line)
    data = array(sim_mat)
    # 修改相似度矩阵使其符合sklearn
    for row in range(0, service_num):
        for col in range(0, service_num):
            if data[row][col] < 0:
               data[row][col] = 0
            if row == col:
                data[row][col] = 1

    spectral = cluster.SpectralClustering(n_clusters=int(k), affinity="precomputed")
    re = spectral.fit(data)
    label_clustering = re.labels_.tolist()
    return label_clustering


# 统计每个聚类中节点的数量
def get_node_num(label, total, digit):
    count = 0
    for n in range(0, total):
        if label[n] == digit:
            count += 1
    return count





