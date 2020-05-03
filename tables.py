import numpy as np
import os

def write_data_cost(file_n, file_f):
    data_n = []
    data_f = []
    cost = []
    for line_n, line_f in zip(file_n, file_f):
        list_line_n = line_n.split(" ")
        list_line_f = line_f.split(" ")
        if list_line_n[0] == "Time":
            data_n.append(float(list_line_n[2]))
        if list_line_f[0] == "Time":
            data_f.append(float(list_line_f[2]))
        if list_line_n[0] == "MST":
            cost.append(int(list_line_n[2]))
    return np.array(data_n), np.array(data_f), np.array(cost) 

def write_table(file, cost, data_n, data_f, name):
    file.write("\\begin{center}\n"
    "\\begin{longtable}{|c|c|c|c|c|}\n"
    "\\caption{Risultati dell'algoritmo di " f"{name}" "} \\\\\n"
    "\\hline\n"
    "\\textbf{N.} & \\textbf{Graph Size (nodes)} & \\textbf{Time Federico (s)} & \\textbf{Time Nicola (s)} & \\textbf{MST cost} \\\\\n"
    "\\hline\n"
    "\\endfirsthead\n"
    "\\multicolumn{5}{c}%\n"
    "{\\tablename\\ \\thetable\\ -- \\textit{Continua dalla pagina precedente}} \\\\\n"
    "\\hline\n"
    "\\textbf{N.} & \\textbf{Graph Size (nodes)} & \\textbf{Time Federico (s)} & \\textbf{Time Nicola (s)} & \\textbf{MST cost} \\\\\n"
    "\\hline\n"
    "\\endhead\n"
    "\\hline \\multicolumn{5}{r}{\\textit{Continua nella pagina seguente}} \\\\\n"
    "\\endfoot\n"
    "\\hline\n"
    "\\endlastfoot\n")

    count = 0
    size = ["10", "20", "40", "80", "100", "200", "400", "800", "1k", "2k", "4k", "8k", "10k", "20k", "40k", "80k", "100k"]
    for i in range(len(size)):
        for j in range(4):
            file.write(f"{count+1} & {size[i]} & {data_f[count]} & {data_f[count]} & {cost[count]}\\\\\n")
            count = count + 1
        if(i is not len(size) - 1):
            file.write("\\hline\n")

    file.write("\\end{longtable}\n"
    "\\end{center}\n")

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
    data_prim_n, data_prim_f, cost_prim = write_data_cost(prim_n, prim_f)
    data_naivekruskal_n, data_naivekruskal_f, cost_naivekruskal = write_data_cost(naivekruskal_n, naivekruskal_f)
    data_kruskal_n, data_kruskal_f, cost_kruskal = write_data_cost(kruskal_n, kruskal_f)

    w_prim = open("table_prim.txt", "w")
    w_kruskal = open("table_kruskal.txt", "w")
    w_naivekruskal = open("table_naivekruskal.txt", "w")

    write_table(w_prim, cost_prim, data_prim_n, data_prim_f, "Prim")
    write_table(w_naivekruskal, cost_naivekruskal, data_naivekruskal_n, data_naivekruskal_f, "Naive Kruskal")
    write_table(w_kruskal, cost_kruskal, data_kruskal_n, data_kruskal_f, "Kruskal")

prim_n.close()
naivekruskal_n.close()
kruskal_n.close()

prim_f.close()
naivekruskal_f.close()
kruskal_f.close()
    