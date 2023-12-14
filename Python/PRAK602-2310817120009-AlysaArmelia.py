zetsu_putih = int(input())

arr = list(map(int, input().split()))

ruangan = 0

while(ruangan < zetsu_putih):
    hasil_akhir = arr[ruangan] * (ruangan +1)
    print (hasil_akhir, end=" ")
    ruangan += 1