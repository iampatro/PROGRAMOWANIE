#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include "mpi.h"


#define REZERWA 600
#define PKS 1
#define START 2
#define LOT 3
#define KONIEC_JAZDY 4
#define KATASTROFA 5
#define TANKUJ 300
int paliwo = 300;
int LADUJ=1, NIE_LADUJ=0;
int ilosc_proces;
int nr_procesu;
int nr_poc;
int ilosc_autobusow;
int ilosc_torow=4;
int zajete_tory=0;
int tag=1;
int wys[2];
int odb[2];
MPI_Status mpi_status;
void Wyslij(int nr_poc, int stan)
{
	wys[0]=nr_poc;
	wys[1]=stan;
	MPI_Send(&wys, 2, MPI_INT, 0, tag, MPI_COMM_WORLD);
	sleep(1);
}
void Pks(int ilosc_proces){
	int nr_poc,status;
	ilosc_autobusow = ilosc_proces - 1;
	printf("Symulator PKS \n");
	printf("=========================== \n");
	sleep(2);
	while(ilosc_torow<=ilosc_autobusow){
		MPI_Recv(&odb,2,MPI_INT,MPI_ANY_SOURCE,tag,MPI_COMM_WORLD, &mpi_status);
		nr_poc=odb[0];
		status=odb[1];
		if(status==1){
			printf("autobus %d stoi na przstanku \n", nr_poc);
		}
		if(status==2){
			printf("autobus %d pozwolenie na ruszenie z toru nr %d\n", nr_poc, zajete_tory);
			zajete_tory--;
		}
		if(status==3){
			printf("autobus %d jedzie \n", nr_poc);
		}
		if(status==4){
			if(zajete_tory<ilosc_torow){
				zajete_tory++;
				MPI_Send(&LADUJ, 1, MPI_INT, nr_poc, tag, MPI_COMM_WORLD);
			}
			else{
				MPI_Send(&NIE_LADUJ, 1, MPI_INT, nr_poc, tag, MPI_COMM_WORLD);
			}
		}
		if(status==5){
			ilosc_autobusow--;
			printf(" autobus spozniony \n");
		}
	}
	printf(" STOP \n");
}
void Autobus(){
	int stan,suma,i;
	stan=LOT;
	while(1){
		if(stan==1){
			if(rand()%2==1){
			stan=START;
			paliwo=TANKUJ;
			printf("autobus %d zaraz rusza \n",nr_procesu);
			Wyslij(nr_procesu,stan);
			}
			else{
				Wyslij(nr_procesu,stan);
			}
		}
		else if(stan==2){
			printf("autobus odjechal z przystanku %d\n",nr_procesu);
			stan=LOT;
			Wyslij(nr_procesu,stan);
		}
		else if(stan==3){
			paliwo-=rand()%500;
			if(paliwo<=REZERWA){
				stan=KONIEC_JAZDY;
				Wyslij(nr_procesu,stan);
			}
			else{
				for(i=0; rand()%10000;i++);
			}
		}
		else if(stan==4){
			int temp;
			MPI_Recv(&temp, 1, MPI_INT, 0, tag, MPI_COMM_WORLD, &mpi_status);
			if(temp==LADUJ){
			stan=KOLEJ;
			printf("autobus jest na przystanku  %d\n",nr_procesu);
			}
			else
			{
				paliwo-=rand()%500;
				if(paliwo>0){
					Wyslij(nr_procesu,stan);
				}
				else{
					stan=KATASTROFA;
					printf("spozniony \n");
					Wyslij(nr_procesu,stan);
					return;
				}
			}
		}
	}
}
int main(int argc, char *argv[])
{
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD,&nr_procesu);
	MPI_Comm_size(MPI_COMM_WORLD,&ilosc_proces);
	srand(time(NULL));
	if(nr_procesu == 0)
		Pks(ilosc_proces+1);
	else
		Autobus();

	MPI_Finalize();
	return 0;
}
