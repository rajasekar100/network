#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <arpa/inet.h>
#include <time.h>
void clk(int s);

void main()
{
	char *ip="127.0.0.1";
	int port=5566;
	char c[512];
	int server_sock,client_sock,a=0;
	
	struct sockaddr_in server_addr,client_addr;
	socklen_t addr_size;
	char buffer[1024];
	int n,i, buff_len,p,pc;
	
	server_sock=socket(AF_INET,SOCK_STREAM,0);
	if(server_sock<0)
	{
		 perror("[-]Socket error");
		 exit(1);
	}
	printf("[+]TCP server socket created.\n");
	
	server_addr.sin_family=AF_INET;
	server_addr.sin_port=port;
	server_addr.sin_addr.s_addr=inet_addr(ip);
	
	n=bind(server_sock,(struct sockaddr*)&server_addr,sizeof(server_addr));
	if(n<0)
	{
		printf("Bind error");
		exit(1);
	}
	printf("[+]Bind to the port number:%d\n",port);
	
	listen(server_sock,5);
	printf("Listening \n");
	
	client_sock=accept(server_sock, (struct sockaddr*)&client_addr, &addr_size);
	
	recv(client_sock,buffer, sizeof(buffer),0);
	p=atoi(buffer);
	char f[p];
	for (i=0;i<p;i++)
		f[i]='f';
	
	recv(client_sock, buffer, sizeof(buffer),0);
	pc=atoi(buffer);
	printf("sending\n");
	if(pc==0)
	{
		for(i=0;i<p;i++)
		{
			printf("sending frame no : %d\n",i);
			sprintf(buffer,"%d",i);
			send(client_sock,buffer,strlen(buffer),0);
			printf("waiting for ack\n");
			clk(3);
			recv(client_sock, buffer, sizeof(buffer),0);
			a=atoi(buffer);
			printf("receive ack for frame %d as %d\n",i,a);									/*server*/
		}
	}
	else
	{
	    for(i=0;i<p;i++)
		{
		    if(i==2)
		    {
				printf("sending Frame no : %d\n",i);
				printf("waiting for ack\n");
			}
			else
			{
			    printf("sending Frame no : %d\n",i);
			    sprintf(buffer,"%d",i);
				send(client_sock,buffer,strlen(buffer),0);
				
				printf("waiting for ack\n");
				clk(2);
				recv(client_sock,&a, sizeof(a),0);
				if(a!=999)
				{
					printf("recv ack for frame no %d as %d\n",i,a-48);
					f[i]='t';
				}
			}
		}
		for(int a=0;a<p;a++)
		{
			if(f[a]=='f')
			{
				printf("Resending frame %d\n",a);
				sprintf(buffer,"%d",a);
				send(client_sock,buffer,strlen(buffer),0);
				printf("waiting for ack\n");
				clk(2);
				recv(client_sock, buffer, sizeof(buffer),0);
				int b=atoi(buffer);
				printf("recv ack for farme no %d as %d\n",a,b);
				f[a]='t';
			}
		}	
	}
	close(server_sock);
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