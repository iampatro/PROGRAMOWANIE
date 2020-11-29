#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "err.h"
#include "time.h"

int main{
	int p;
  int n = rand()%10;
  double s = 0.0;

	for(int i=0; i<n; i++){
		p = fork();
  }
  if(p != 0){
  int d = 3;
  int s = 1;
  int k = rand()%5000;
    for(int i=1; i<k; i++){
	if(i%2 == 0){
	    	s -= 1/d;}
	else{
		s += 1/d;}
      	d += 2;
    }
    printf("suma procesu %d to: %f",%getp(), s);
  }



}
