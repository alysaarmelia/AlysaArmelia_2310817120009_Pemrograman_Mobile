deret1 = input()
deret2 = input()
deret3 = deret1

if (len(deret1) != len(deret2)):
    print ("Panjang kalimat berbeda, pesan palsu")
    exit(1)

baris = 0
bintang = 0
pagar = 0

while(baris < len(deret1)):
    if deret1[baris] == " " and deret2[baris] ==" ":
        deret3 = deret3[:baris]+' '+deret3[:baris+1]
    
    else:
        if deret1[baris] == deret2[baris]:
            deret3 = deret3[:baris]+'*'+deret3[:baris+1]
            bintang+=1

        elif deret1[baris] != deret2[baris]:
            deret3 = deret3[:baris]+'#'+deret3[:baris+1]
            pagar+=1
    
    print(deret3[baris], end="")
    baris+=1

print()
print('* =', bintang)
print('# =', pagar)

if bintang > pagar:
    print("Pesan Asli")

elif bintang < pagar:
    print("Pesan Palsu")