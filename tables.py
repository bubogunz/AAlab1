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

    cost_prim = []
    cost_kruskal = []
    cost_naivekruskal = []

    for line in prim:
        list_line = line.split(" ")
        if list_line[0] == "Time":
            data_prim.append(float(list_line[2]))
        if list_line[0] == "MST":
            cost_prim.append(int(list_line[2]))

    for line in naivekruskal:
        list_line = line.split(" ")
        if list_line[0] == "Time":
            data_naivekruskal.append(float(list_line[2]))
        if list_line[0] == "MST":
            cost_naivekruskal.append(int(list_line[2]))

    for line in kruskal:
        list_line = line.split(" ")
        if list_line[0] == "Time":
            data_kruskal.append(float(list_line[2]))
        if list_line[0] == "MST":
            cost_kruskal.append(int(list_line[2]))

    w_prim = open("table_prim", "w")
    w_kruskal = open("table_kruskal", "w")
    w_naivekruskal = open("table_naivekruskal", "w")

    w_prim.write("\\begin{table}[H]\n\centering\n\\begin{tabular}{|c|c|c|c|}\n")
    w_prim.write("\\hline\n\\textbf{N.} & \\textbf{Graph Size} & \\textbf{Time (s)} & \\textbf{MST cost}\\\\ \n")

    w_kruskal.write("\\begin{table}[H]\n\centering\n\\begin{tabular}{|c|c|c|c|}\n")
    w_kruskal.write("\\hline\n\\textbf{N.} & \\textbf{Graph Size} & \\textbf{Time (s)} & \\textbf{MST cost}\\\\ \n")

    w_naivekruskal.write("\\begin{table}[H]\n\centering\n\\begin{tabular}{|c|c|c|c|}\n")
    w_naivekruskal.write("\\hline\n\\textbf{N.} & \\textbf{Graph Size} & \\textbf{Time (s)} & \\textbf{MST cost}\\\\ \n")

    count = 0
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 10 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline") 
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 20 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 40 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 80 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 100 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 200 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 400 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 800 & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 1k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 2k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 4k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 8k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 10k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 20k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 40k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 80k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1
    w_prim.write("\\hline")
    for i in range(4):
        w_prim.write(f"\\hline\n{count+1} & 100k & {data_prim[count]} & {cost_prim[count]}\\\\\n")
        count = count + 1

    w_prim.write("\\hline\n\\end{tabular}\n\\end{table}")


    count = 0
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 10 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")    
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 20 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")   
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 40 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 80 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 100 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 200 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 400 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 800 & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 1k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 2k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 4k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 8k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 10k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 20k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 40k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 80k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1
    w_kruskal.write("\\hline")
    for i in range(4):
        w_kruskal.write(f"\\hline\n{count+1} & 100k & {data_kruskal[count]} & {cost_kruskal[count]}\\\\\n")
        count = count + 1

    w_kruskal.write("\\hline\n\\end{tabular}\n\\end{table}")



    count = 0
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 10 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")    
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 20 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline") 
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 40 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 80 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 100 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 200 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 400 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 800 & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 1k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 2k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 4k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 8k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 10k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 20k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 40k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 80k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1
    w_naivekruskal.write("\\hline")
    for i in range(4):
        w_naivekruskal.write(f"\\hline\n{count+1} & 100k & {data_naivekruskal[count]} & {cost_naivekruskal[count]}\\\\\n")
        count = count + 1

    w_naivekruskal.write("\\hline\n\\end{tabular}\n\\end{table}")

prim.close()
naivekruskal.close()
kruskal.close()

w_kruskal.close()
w_naivekruskal.close()
w_prim.close()
    