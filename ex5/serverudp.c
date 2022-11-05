
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
int main(int argc, char **argv)
{
	if (argc !=2)
	{
		printf("Usage: %s <port>\n",argv[0]);
		exit(0);
	}
	
	char *ip = "127.0.0.1";
	int port = atoi(argv[1]);
	
	int sockfd;
	struct sockaddr_in server_addr, client_addr;
	char buffer[1024];
	socklen_t addr_size;
	int n;
	
	sockfd=socket(AF_INET,SOCK_DGRAM,0);
	if(sockfd <0)
	{
		printf("Socket error");
		exit(1);
	}
	
	memset(&server_addr,'\0',sizeof(server_addr));
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(port);
	server_addr.sin_addr.s_addr = inet_addr(ip);
	
	n=bind(sockfd,(struct sockaddr*)&server_addr,sizeof(server_addr));
	if(n<0)
	{
		printf("bind error");
		exit(1);
	}
	addr_size = sizeof(client_addr);
	int i;
	recvfrom(sockfd,&i,sizeof(i),0,(struct sockaddr*)&client_addr,&addr_size);
	int a[i];
	recvfrom(sockfd,a,sizeof(a),0,(struct sockaddr*)&client_addr,&addr_size);
	printf("DataReceived:\n");
	for(int p=0;p<i;p++)
		printf("%d\t",a[p]);
	printf("\n");
	int j,k,l;
	char b;
	int min=a[0],max=a[0];
	for (int j=0;j<i;j++)
	{
		if (a[j]>max)
		    max=a[j];
		if(a[j]<min)
			min=a[j]; 
	}
	l=min;
	int d[10000];
	int m=0;
	for (j=0;j<max-min;j++)
	{
		b='F';
		for (k=0;k<i;k++)//3
			if(l==a[k])
				b='T';
		if(b=='F')
		{
			d[m]=l;
			m++;
		}
		l++;
	}
	d[m];
	
	sendto(sockfd,&m,sizeof(m),0,(struct sockaddr*)&client_addr,sizeof(client_addr));
	sendto(sockfd,d,sizeof(d),0,(struct sockaddr*)&client_addr,sizeof(client_addr));
	printf("data sent : \n");
	for(int i=0;i<m;i++)
		printf("%d\t",d[i]);
	printf("\n");
	return 0;	
}