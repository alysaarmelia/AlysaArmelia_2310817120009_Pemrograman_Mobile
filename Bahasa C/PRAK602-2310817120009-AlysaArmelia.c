#include <stdio.h>

int main()
{
    int zetsu_putih;

    scanf("%d", &zetsu_putih);

    int arr[zetsu_putih];
    int ruangan;

    for (ruangan = 0; ruangan < zetsu_putih; ruangan++)
    {
        scanf("%d", &arr[ruangan]);
    }
    
    for (ruangan = 0; ruangan < zetsu_putih; ruangan++)
    {
        int hasil_akhir = arr[ruangan] * (ruangan + 1 );
        printf("%d ", hasil_akhir);
    }
return 0;
}