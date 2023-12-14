kolom1, kolom2 = map(int, input().split())

baris1 = list(map(int, input().split()))

baris2 = 0

while(baris2 < len(baris1)):
    if baris2 % kolom2 == 0 and baris2 > 0:
        print()
    print(baris1 [baris2], end=" ")
    baris2+=1