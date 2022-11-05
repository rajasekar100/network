#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <arpa/inet.h>
#define INFINITY 9999
//#define n 10

void main()
{
	char *ip="127.0.0.1";
	int *p;
	int port;
	printf("Enter the Port Number");
	scanf("%d",&port);
	char c[512];
	int server_sock,client_sock;
	struct sockaddr_in server_addr,client_addr;
	socklen_t addr_size;
	char buffer[1024];
	int n,i,j,u, buff_len;
	auto void  dijkstra();
	server_sock=socket(AF_INET,SOCK_STREAM,0);
	if(server_sock<0)
		 exit(1);
	printf("TCP server socket created.\n");
	
	server_addr.sin_family=AF_INET;
	server_addr.sin_port=port;
	server_addr.sin_addr.s_addr=inet_addr(ip);
	
	n=bind(server_sock,(struct sockaddr*)&server_addr,sizeof(server_addr));
	if(n<0)
		exit(1);
	printf("Bind to the port number:%d\n",port);
	listen(server_sock,5);
	printf("Listening \n");	
	addr_size=sizeof(client_addr);
	client_sock=accept(server_sock, (struct sockaddr*)&client_addr, &addr_size);
	printf("Client Connected.\n");	
	recv(client_sock,&n, sizeof(n),0);
	recv(client_sock,&u, sizeof(u),0);
	//printf("%d",n);
	int G[n][n];
	//recv(client_sock,&G, sizeof(G),0);
	printf("\nEnter the adjacency matrix:\n");
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			scanf("%d",&G[i][j]);
	p=&G[0][0];

	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
		{
			G[i][j]=*p;
			p++;
		}
	}
	//printf("\n\n");
	//printf("%d\n",n);
	//printf("%d\n",u);
	/*for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
		{
			printf("%d\t",G[i][j]);
		}
		printf("\n");
	}*/
	dijkstra(G,n,u);	
void dijkstra(int G[n][n],int n,int startnode)
{
	int cost[n][n],distance[n],pred[n];
	int visited[n],count,mindistance,nextnode,i,j;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			if(G[i][j]==0)
				cost[i][j]=INFINITY;
			else
				cost[i][j]=G[i][j];
	for(i=0;i<n;i++)
	{
		distance[i]=cost[startnode][i];
		pred[i]=startnode;
		visited[i]=0;
	}
	
	distance[startnode]=0;
	visited[startnode]=1;
	count=1;
	while(count<n-1)
	{
		mindistance=INFINITY;
		for(i=0;i<n;i++)
			if(distance[i]<mindistance&&!visited[i])
			{
				mindistance=distance[i];
				nextnode=i;
			}			
			visited[nextnode]=1;
			for(i=0;i<n;i++)
				if(!visited[i])
					if(mindistance+cost[nextnode][i]<distance[i])
					{
						distance[i]=mindistance+cost[nextnode][i];
						pred[i]=nextnode;
					}
		count++;
	}
	for(i=0;i<n;i++)
		if(i!=startnode)
		{
			strcat(c,"  Distance of node :");
			sprintf(buffer,"%d",i);
			strcat(c,buffer);
			strcat(c," = ");
			sprintf(buffer,"%d",distance[i]);
			strcat(c,buffer);
			strcat(c," : Path = ");
			sprintf(buffer,"%d",i);
			strcat(c,buffer);
			
			j=i;
			do
			{
				j=pred[j];
				strcat(c," <- ");
			sprintf(buffer,"%d",j);
			strcat(c,buffer);
			
			}while(j!=startnode);
			strcat(c,"\n");
			send(client_sock,c,strlen(c),0);
			c[0]='\0';
		}
	//send(client_sock,c,strlen(c),0);
}
	close(client_sock);
	printf("Client Disconnected.\n\n");
}