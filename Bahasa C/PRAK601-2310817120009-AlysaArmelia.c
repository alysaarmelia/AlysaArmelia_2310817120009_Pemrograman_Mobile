#include <stdio.h>

int main()
{
    int kolom1;
    int kolom2;
    int baris1;
    int baris2;

    scanf("%d %d", &kolom1, &kolom2);

    int matriks [kolom1][kolom2];
    
    for (baris1 = 0; baris1 < kolom1; baris1++)
    {
        for (baris2 = 0; baris2 < kolom2; baris2++)
        {
            scanf("%d", &matriks [baris1][baris2]);   
        }
    }

    for (baris1 = 0; baris1 < kolom1; baris1++)
    {
        for (baris2 = 0; baris2 < kolom2; baris2++)
        {
            printf("%d ", matriks[baris1][baris2]);

        }
    printf("\n");
    }
return 0;
}