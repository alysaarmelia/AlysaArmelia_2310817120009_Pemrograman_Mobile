n1, n2 = map(int, input().split())

if (n1 != n2):

    print ("Jumlah tidak sama")
    exit(1)

list_n1 = list(map(int, input().split()))
list_n2 = list(map(int, input().split()))

if (len(list_n1) == n1 and len (list_n2) == n2):

    tik = 0

    while (tik < n1):
        keseluruhan = list_n1[tik] * list_n2[tik]
        print(keseluruhan, end=" ")
        tik+=1