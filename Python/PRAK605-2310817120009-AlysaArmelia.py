matriks_n = int(input())

matriks_kolom1 = []
matriks_kolom2 = []
matriks_kolom3 = []

print ("Matriks A")

for baris in range(matriks_n):
    deret = list(map(int, input().split()))
    matriks_kolom1.append (deret)

print("Matriks B")

for baris in range(matriks_n):
    deret = list(map(int, input().split()))
    matriks_kolom2.append (deret)

for baris in range (matriks_n):
    matriks_kolom3.append ([0] * matriks_n)

for baris1 in range (matriks_n):
    for baris2 in range (matriks_n):
        for baris3 in range (matriks_n):
            matriks_kolom3 [baris1][baris2] += matriks_kolom1 [baris1][baris3] * matriks_kolom2 [baris3][baris2]

print("Matriks AXB")

for baris1 in range (matriks_n):
    for baris2 in range (matriks_n):

        print(matriks_kolom3 [baris1][baris2], end=" ")
    
    print()