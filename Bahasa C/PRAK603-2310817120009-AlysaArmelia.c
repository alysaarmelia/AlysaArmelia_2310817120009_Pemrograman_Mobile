#include <stdio.h>

int main() 
{
    int tik;
    int matematika; 
    int n1;
    int n2;

    int keseluruhan[45] = {1};

    scanf("%d %d", &n1, &n2);

    int Limit [n1][n2];

    if (n1 == n2) 
    {

        for (tik = 0; tik < 2; ++tik) 
        {
            for (matematika = 0; matematika < n2; ++matematika) 
            {
                scanf("%d", &Limit[tik][matematika]);
            }
        }
        
        for (tik = 0; tik < 2; ++tik) 
        {
            for (matematika = 0; matematika < n2; ++matematika)
            {
                keseluruhan[matematika] = Limit[0][matematika] * Limit[1][matematika];
            }
        }

        for (matematika = 0; matematika < n2; ++matematika) 
        {
            printf("%d ", keseluruhan[matematika]);
        }
        
    } else {
        
        printf("Jumlah tidak sama\n");

    }
return 0;
}