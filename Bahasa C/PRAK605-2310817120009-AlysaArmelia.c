#include <stdio.h>

int main()
{
    int matriks_n;

    scanf("%d", &matriks_n);

    int matriks_kolom1 [matriks_n][matriks_n];
    int matriks_kolom2 [matriks_n][matriks_n];
    int matriks_kolom3 [matriks_n][matriks_n];

    printf("Matriks A\n");

    int baris1;
    int baris2;
    int baris3;

    for (baris1 = 0; baris1 < matriks_n; ++baris1)
    {
        for (baris2 = 0; baris2 < matriks_n; ++baris2)
        {
            scanf("%d", &matriks_kolom1[baris1][baris2]);
        }
    }
    printf("Matriks B\n");

     for (baris1 = 0; baris1 < matriks_n; ++baris1)
    {
        for (baris2 = 0; baris2 < matriks_n; ++baris2)
        {
            scanf("%d", &matriks_kolom2[baris1][baris2]);
        }
    }

    int sum = 0;

    for (baris1 = 0; baris1 < matriks_n; baris1++)
    {
        for (baris2 = 0; baris2 < matriks_n; ++baris2)
        {
            for (baris3 = 0; baris3 < matriks_n; ++baris3)
            {
                sum+= matriks_kolom1[baris1][baris3] * matriks_kolom2[baris3][baris2];
            }
            matriks_kolom3[baris1][baris2] = sum;
            sum = 0;
        }

    }

    printf("Matriks AXB\n");

    for (baris1 = 0; baris1 < matriks_n; ++baris1)
    {
        for (baris2 = 0; baris2 < matriks_n; ++baris2)
        {
            printf("%d ", matriks_kolom3[baris1][baris2]);
        }
        printf("\n");
    }
return 0;
}