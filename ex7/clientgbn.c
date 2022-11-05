#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <time.h>
void clk(int s);

void main()
{
	
	char *ip="127.0.0.1";
	char a[100];
	int port=5566;
	
	int sock;
	struct sockaddr_in addr;
	socklen_t addr_size;
	char buffer[1024];
	int n,c,choice,k=-1;
	
	sock=socket(AF_INET,SOCK_STREAM,0);
	if(sock<0)
	{
		perror("[-]Socket error");
		exit(1);
	}
	printf("[+]TCP server socket created.\n");
	memset(&addr,'\0',sizeof(addr));
	addr.sin_family=AF_INET;
	addr.sin_port=port;
	addr.sin_addr.s_addr=inet_addr(ip);
	connect(sock,(struct sockaddr*)&addr,sizeof(addr));
	printf("client");
	printf("connect");
	
	printf("Enter the number of frames : ");
	scanf("%d",&c);
	sprintf(buffer,"%d",c);
	send(sock,buffer,strlen(buffer),0);
	
	printf("error=1, no error=0 : ");
	scanf("%d",&choice);
	sprintf(buffer,"%d",choice);
	send(sock,buffer,strlen(buffer),0);
	
	int check=0,i=0,j=0;
	k=0;
	if(choice==0)
	{
		for(j=0;j<c;j++)
		{
			recv(sock,buffer,sizeof(buffer),0);
			i=atoi(buffer);
			
			printf("received frame no : %d\n",i);
			printf("sending ack : %d\n",i);
			
			sprintf(buffer,"%d",i);
			send(sock,buffer,strlen(buffer),0);
		}
	}
	else
	{
		//choice=0;
		for(j=0;j<c;j++)//0,1,2
		{
			recv(sock,buffer,sizeof(buffer),0);
			i=atoi(buffer);
			
			if(i==check)//0==0,1==1,3!=2
			{
				//printf("i : %d check : %d",i,check);
				printf("received frame no : %d\n",i);//0,1,
				printf("sending ack frame no : %d\n",i);//0,1
				
				sprintf(buffer,"%d",i);
				send(sock,buffer,strlen(buffer),0);//send 0 ,1
				check++;//1 ,2
			}
			else
			{
				j--;
				k=999;
				send(sock,&k,sizeof(k),0);
			}
		}
	}
	close(sock);
}
void clk(int s)
{
	unsigned int x_minutes=0;
	unsigned int x_seconds=0;
	unsigned int x_milliseconds=0;
	unsigned int totaltime=0,count_down_time_in_secs=0,time_left=0;
	clock_t x_startTime,x_countTime;
	count_down_time_in_secs=s;  // 1 minute is 60, 1 hour is 3600
    x_startTime=clock();  // start clock
    time_left=count_down_time_in_secs-x_seconds;   // update timer
	while (time_left>0) 
	{
		x_countTime=clock(); // update timer difference
		x_milliseconds=x_countTime-x_startTime;
		x_seconds=(x_milliseconds/(CLOCKS_PER_SEC))-(x_minutes*60);		
		time_left=count_down_time_in_secs-x_seconds; // subtract to get difference 
	}
}

