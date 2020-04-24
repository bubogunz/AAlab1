import matplotlib.pyplot as plt
import numpy as np
import os

my_path = os.path.abspath(os.path.dirname(__file__))
path = os.path.join(my_path, "JavaProject/Prim.txt")
prim = open(path, "r")
path = os.path.join(my_path, "JavaProject/NaiveKruskal.txt")
naivekruskal = open(path, "r")
path = os.path.join(my_path, "JavaProject/Kruskal.txt")
kruskal = open(path, "r")

if prim.mode == "r" and naivekruskal.mode == "r" and kruskal.mode == "r":
    data_prim = []
    data_naivekruskal = []
    data_kruskal = []

    for line in prim:
        list_line = line.split(" ")
        if list_line[0] == "Time":
            data_prim.append(float(list_line[2]))
    data_prim = np.array(data_prim)

    for line in naivekruskal:
        list_line = line.split(" ")
        if list_line[0] == "Time":
            data_naivekruskal.append(float(list_line[2]))
    data_naivekruskal = np.array(data_naivekruskal)

    for line in kruskal:
        list_line = line.split(" ")
        if list_line[0] == "Time":
            data_kruskal.append(float(list_line[2]))
    data_kruskal = np.array(data_kruskal)

    x_tick = ["10", "20", "40", "80", "100", "200", "400", "800", "1k", "2k", "4k", "8k", "10k", "20k", "40k", "80k", "100k"]
    
    plt.plot(range(68), data_kruskal, label="Kruskal")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph")
    plt.ylabel("Time (s)")
    plt.title("Kruskal algorithm performance")
    plt.show()

    plt.plot(range(68), data_prim, label="Prim")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph")
    plt.ylabel("Time (s)")
    plt.title("Prim algorithm performance")
    plt.show()

    plt.plot(range(68), data_naivekruskal, label="Naive Kruskal")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph")
    plt.ylabel("Time (s)")
    plt.title("Naive Kruskal Algorithm performance")
    plt.show()

    plt.plot(range(68), data_kruskal, label="Kruskal")
    plt.plot(range(68), data_prim, label="Prim")
    plt.plot(range(68), data_naivekruskal, label="Naive Kruskal")
    plt.xticks(np.linspace(0, 68, 17), x_tick)
    plt.xlabel("Size of graph")
    plt.ylabel("Time (s)")
    plt.title("Algorithms performance")
    plt.legend()
    plt.show()


prim.close()
naivekruskal.close()
kruskal.close()