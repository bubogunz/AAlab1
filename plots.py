import matplotlib.pyplot as plt
import numpy as np
import os

def write_data(file):
    data = []
    for line in file:
        list_line = line.split(" ")
        if list_line[0] == "Time":
            data.append(float(list_line[2]))
    return np.array(data)

my_path = os.path.abspath(os.path.dirname(__file__))
path = os.path.join(my_path, "JavaProject/results/Prim_N.txt")
prim_n = open(path, "r")
path = os.path.join(my_path, "JavaProject/results/NaiveKruskal_N.txt")
naivekruskal_n = open(path, "r")
path = os.path.join(my_path, "JavaProject/results/Kruskal_N.txt")
kruskal_n = open(path, "r")

path = os.path.join(my_path, "JavaProject/results/Prim_F.txt")
prim_f = open(path, "r")
path = os.path.join(my_path, "JavaProject/results/NaiveKruskal_F.txt")
naivekruskal_f = open(path, "r")
path = os.path.join(my_path, "JavaProject/results/Kruskal_F.txt")
kruskal_f = open(path, "r")

if prim_n.mode == "r" and naivekruskal_n.mode == "r" and kruskal_n.mode == "r" and prim_n.mode == "r" and \
    naivekruskal_n.mode == "r" and kruskal_n.mode == "r":
    data_prim_n = write_data(prim_n)
    data_naivekruskal_n = write_data(naivekruskal_n)
    data_kruskal_n = write_data(kruskal_n)

    data_prim_f = write_data(prim_f)
    data_naivekruskal_f = write_data(naivekruskal_f)
    data_kruskal_f = write_data(kruskal_f)

    x_tick = ["10", "20", "40", "80", "100", "200", "400", "800", "1k", "2k", "4k", "8k", "10k", "20k", "40k", "80k", "100k"]
    
    plt.figure("kruskal_g")
    plt.plot(range(68), data_kruskal_n, label="Nicola performance")
    plt.plot(range(68), data_kruskal_f, label="Federico performance")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph (nodes)")
    plt.ylabel("Time (s)")
    plt.title("Kruskal algorithm performance")
    plt.legend()
    plt.savefig(os.path.join(my_path, "relazioneAA/relazioneAA/imgs/kruskal_g.png"))
    plt.close()

    plt.plot(range(68), data_prim_n, label="Nicola performance")
    plt.plot(range(68), data_prim_f, label="Federico performance")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph (nodes)")
    plt.ylabel("Time (s)")
    plt.title("Prim algorithm performance")
    plt.legend()
    plt.savefig(os.path.join(my_path, "relazioneAA/relazioneAA/imgs/prim_g.png"))
    plt.close()

    plt.plot(range(68), data_naivekruskal_n, label="Nicola performance")
    plt.plot(range(68), data_naivekruskal_f, label="Fedrico performance")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph (nodes)")
    plt.ylabel("Time (s)")
    plt.title("Naive Kruskal Algorithm performance")
    plt.legend()
    plt.savefig(os.path.join(my_path, "relazioneAA/relazioneAA/imgs/naivekruskal_g.png"))
    plt.close()

    plt.plot(range(68), data_kruskal_n, label="Kruskal")
    plt.plot(range(68), data_prim_n, label="Prim")
    plt.plot(range(68), data_naivekruskal_n, label="Naive Kruskal")
    plt.yscale("log")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph (nodes)")
    plt.ylabel("Time (s)")
    plt.title("Nicola algorithms performance")
    plt.legend()
    plt.savefig(os.path.join(my_path, "relazioneAA/relazioneAA/imgs/compare_n.png"))
    plt.close()

    plt.plot(range(68), data_kruskal_f, label="Kruskal")
    plt.plot(range(68), data_prim_f, label="Prim")
    plt.plot(range(68), data_naivekruskal_f, label="Naive Kruskal")
    plt.yscale("log")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph (nodes)")
    plt.ylabel("Time (s)")
    plt.title("Federico algorithms performance")
    plt.legend()
    plt.savefig(os.path.join(my_path, "relazioneAA/relazioneAA/imgs/compare_f.png"))
    plt.close()


prim_n.close()
naivekruskal_n.close()
kruskal_n.close()

prim_f.close()
naivekruskal_f.close()
kruskal_f.close()