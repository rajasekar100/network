#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include<time.h>
void clk(int s);

void main()
{	
	char *ip="127.0.0.1";
	char a[100];
	int port;
	printf("Enter the Port Number");
	scanf("%d",&port);

	int sock;
	struct sockaddr_in addr;
	socklen_t addr_size;
	char buffer[1024];
	int n;
	
	sock=socket(AF_INET,SOCK_STREAM,0);
	if(sock<0)
		 exit(1);
	printf("TCP Server Socket Created.\n");

	addr.sin_family=AF_INET;
	addr.sin_port=port;
	addr.sin_addr.s_addr=inet_addr(ip);
	
	connect(sock,(struct sockaddr*)&addr,sizeof(addr));
	printf(" Connected To Server\n");
	
	int i,j,u;
	printf("Enter no. of vertices:");
	scanf("%d",&n);
	send(sock,&n,sizeof(n),0);
	//int G[n][n];

	//send(sock,&G,sizeof(G),0);
	
	printf("\nEnter the starting node:");
	scanf("%d",&u);
	send(sock,&u,sizeof(u),0);

	recv(sock,buffer,sizeof(buffer),0);

	strcat(a,buffer);

	printf("%s\n",a);
	close(sock);
	printf("Disconnect The Server");
}
