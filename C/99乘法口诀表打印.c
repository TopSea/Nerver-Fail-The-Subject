/*
*文件名取英文
*/

#include<stdio.h>
void mul99() {
    int i = 1;
    int j = 1;
    for (i; i <= 9; i++)
    {
        for (j = 1; j <= i; j++)
        {
            printf("%d * %d = %d\t", j, i, i * j);
        }
        printf("\n");
    }
}
void main()
{
    mul99();
}
