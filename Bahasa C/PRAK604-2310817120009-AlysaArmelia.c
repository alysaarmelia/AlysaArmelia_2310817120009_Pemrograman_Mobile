#include <stdio.h>
#include<string.h>

int main()
{
    char deret1[45];
    char deret2[45];
    
    scanf("%[^\n]\n", deret1);
    scanf("%[^\n]", deret2);

    int panjang_kolom1 = strlen(deret1);
    int panjang_kolom2 = strlen(deret2);

    if (panjang_kolom1 != panjang_kolom2)
    {
        printf("Panjang kalimat berbeda, pesan palsu\n");
        
        
        } else if (panjang_kolom1 == panjang_kolom2){
            
            int baris1;
            int baris2;
            int bintang = 0;
            int pagar = 0;

            for (baris1 = 0, baris2 = 0; baris1 <panjang_kolom1 && baris2 <panjang_kolom2; ++baris1, ++baris2) 
            {
                if (deret1[baris1]==' '  && deret2[baris2]==' ')
                {
                printf(" ");
                }
                
                else if (deret1[baris1] == deret2[baris2])
                {
                printf("*");
                bintang++;
                
                }else{
                printf("#");
                pagar++;
                
                }
            }
            
            printf("\n* = %d\n", bintang);
            printf("# = %d\n", pagar);
            
            if(bintang > pagar)
            {
                printf("Pesan Asli\n");
            }

            else if(bintang < pagar)
            {
                printf("Pesan Palsu\n");
            }
    }
return 0;
}