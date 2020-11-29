#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "err.h"
#include "time.h"



int main{
	int p,a,b;

	for(int i=0; i<rand()%100; i++){
		p = fork();
		while(a>b){
			a = rand()%10;
			b = rand()%100;
		}
		po(a,b,p);

	}

}

double f(x){
	return 4*x*x - 6*x + 5;
}

double po(int a, int b, int n){
	double h = (b-a)/n;
	double s = 0.0;
	double z = f(a);
	double g;

	for(int i=1; i<=n; i++){
		g = f(a+h*i);
		s += (z + g);
		z = g;
	}
	return s*0.5*h
}
